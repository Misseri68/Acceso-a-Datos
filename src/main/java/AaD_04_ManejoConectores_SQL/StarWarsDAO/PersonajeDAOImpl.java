package AaD_04_ManejoConectores_SQL.StarWarsDAO;

import AaD_04_ManejoConectores_SQL.PokemonDAO.PokemonNoExisteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonajeDAOImpl implements CRUD{

    @Override
    public boolean crear(PersonajeSW personaje, Connection connection) {
        boolean creadoCorrectamente = false;
        try {
            try{
                obtenerPorNombre(connection, personaje.getName());
            } catch (PersonajeNoExisteException e){
                connection.setAutoCommit(false);
                String query =  "INSERT INTO personajesSW (name, gender, birth_year, height, mass, hair_color, skin_color, eye_color, planet, species) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                try (PreparedStatement ps = connection.prepareStatement(query)) {
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
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return creadoCorrectamente;
    }
//Falta por implmenetar porque es exactamente lo mismo que pokemon solo que hay que controlar los Strings (que sería el identificador) en veaz de un int numPokemon
    @Override
    public List<PersonajeSW> obtenerTodos(Connection connection) {
        return null;
    }

    @Override
    public PersonajeSW obtenerPorNombre(Connection connection, String nombre) throws PersonajeNoExisteException {
        return null;
    }

    @Override
    public void actualizar(Connection connection, PersonajeSW personaje) throws PersonajeNoExisteException {

    }

    @Override
    public void eliminar(Connection connection, String nombre) throws PersonajeNoExisteException {

    }
}
