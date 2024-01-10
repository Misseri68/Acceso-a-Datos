package AaD_04_ManejoConectores_SQL.StarWarsDAO;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsertarPersonajesPS {
    static String url = "jdbc:sqlite:./src/main/resources/StarWars.db";
    static String rutaCSV = "src/main/java/AaD_04_ManejoConectores_SQL/StarWarsDAO/People.csv";
    static List<PersonajeSW> personajes;

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(url)) {

            crearTabla(connection);

            String insertQuery = "INSERT INTO personajesSW (name, gender, birth_year, height, mass, hair_color, skin_color, eye_color, planet, species) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
                connection.setAutoCommit(false);
                personajes = leerPersonajesCSV(new File(rutaCSV));

                for (PersonajeSW personaje : personajes) {
                    ps.setString(1, personaje.getName());
                    ps.setString(2, personaje.getGender());
                    ps.setString(3, personaje.getBirthYear());
                    ps.setInt(4, personaje.getHeight());
                    ps.setDouble(5, personaje.getMass());
                    ps.setString(6, personaje.getHairColor());
                    ps.setString(7, personaje.getSkinColor());
                    ps.setString(8, personaje.getEyeColor());
                    ps.setString(9, personaje.getPlanet());
                    ps.setString(10, personaje.getSpecies());
                    ps.executeUpdate();
                }
                connection.commit();
                connection.setAutoCommit(true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + e.getSQLState() + " " + e.getErrorCode());
        }
    }

    public static List<PersonajeSW> leerPersonajesCSV(File CSV) throws FileNotFoundException {
        List<PersonajeSW> personajes = new ArrayList<>();
        if (Files.exists(CSV.toPath())) {
            try (BufferedReader br = new BufferedReader(new FileReader(CSV))) {
                String[] atributos = new String[10];
                String linea;
                //Para saltarme el cabecero
                br.readLine();
                while ((linea = br.readLine()) != null) {
                    PersonajeSW personaje = new PersonajeSW();
                    atributos = linea.split(",");
                    personaje.setName(atributos[0]);
                    personaje.setGender(atributos[1]);
                    personaje.setBirthYear(atributos[2]);
                    personaje.setHeight(Integer.parseInt(atributos[3]));
                    personaje.setMass(Double.parseDouble(atributos[4]));
                    personaje.setHairColor(atributos[5]);
                    personaje.setSkinColor(atributos[6]);
                    personaje.setEyeColor(atributos[7]);
                    personaje.setPlanet(atributos[8]);
                    personaje.setSpecies(atributos[9]);
                    personajes.add(personaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else throw new FileNotFoundException();
        return personajes;
    }

    public static void crearTabla(Connection connection){
        String queryDrop = "DROP TABLE IF EXISTS personajesSW;";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(queryDrop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "CREATE TABLE IF NOT EXISTS personajesSW (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name TEXT,\n" +
                "    gender TEXT,\n" +
                "    birth_year TEXT,\n" +
                "    height INTEGER,\n" +
                "    mass REAL,\n" +
                "    hair_color TEXT,\n" +
                "    skin_color TEXT,\n" +
                "    eye_color TEXT,\n" +
                "    planet TEXT,\n" +
                "    species TEXT\n" +
                ");";
         try(Statement statement = connection.createStatement()){
            statement.executeUpdate(query);
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }

}
