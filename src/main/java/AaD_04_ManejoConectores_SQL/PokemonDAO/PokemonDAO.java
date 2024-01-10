package AaD_04_ManejoConectores_SQL.PokemonDAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface PokemonDAO {
    boolean create(Pokemon pokemon, Connection connection, String tipo1, String tipo2) throws SQLException;
    Pokemon readById(Connection connection, int idPokemon) throws PokemonNoExisteException;
    boolean update(Connection connection, Pokemon pokemon1) throws PokemonNoExisteException;
    boolean deleteById(Connection connection, int idPokemon) throws PokemonNoExisteException;
}
