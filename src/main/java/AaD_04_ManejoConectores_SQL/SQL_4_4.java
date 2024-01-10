package AaD_04_ManejoConectores_SQL;

import AaD_04_ManejoConectores_SQL.PokemonDAO.MySQLPokemoNDAO;
import AaD_04_ManejoConectores_SQL.PokemonDAO.Pokemon;
import AaD_04_ManejoConectores_SQL.PokemonDAO.PokemonNoExisteException;
import AaD_04_ManejoConectores_SQL.PokemonDAO.PokemonYaExisteException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL_4_4 {
    static String url = "jdbc:mysql://192.168.56.101:3306/pokemon_db";
    static String usr = "admin00";
    static String pwd = "alumno";

    public static void main(String[] args) throws PokemonNoExisteException, PokemonYaExisteException {

        try(Connection connection = DriverManager.getConnection(url, usr, pwd)){
            //POKEMON

        Pokemon pokemon = new Pokemon(1,"Bulbasaur",45,45,49,65,49,65,2,1);
        MySQLPokemoNDAO pkdao = new MySQLPokemoNDAO();
        pkdao.deleteById(connection,pokemon.getIdNum());
        pkdao.create(pokemon, connection, "PLANTA", "");
        pokemon.setName("BulbasaurusRex");
        pkdao.update(connection, pokemon);


            //STAR WARS





        } catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /*1. En la base de datos de Pokemon, la tabla pokemon y la tabla pokemon_type no
están relacionadas, por ello, cuando se introduce un nuevo pokemon y se debe meter también su tipo. Cada uno se hará con un INSERT por lo que ambas
operaciones se deben realizar mediante una transacción.
*
* 2. Se pide que crees una implementación de PokemonDAO para interactuar con la
BdD de Pokemon. Añade el código necesario para probar lo que has hecho.*/

/*
* 3. Implementa un DAO para SQLite de la interfaz que creaste para los personajes
de Star Wars.
Presta especial atención a si tienes que realizar algún cambio a la definición que
realizaste en su momento y apúntalo. No es grave ya que estamos aprendiendo
pero ten en cuenta que si te sucediera en un proyecto real, tendrías que modificar
todo el código.
En el Aula Virtual tienes un ejemplo de proyecto con SQLite.*/



}
