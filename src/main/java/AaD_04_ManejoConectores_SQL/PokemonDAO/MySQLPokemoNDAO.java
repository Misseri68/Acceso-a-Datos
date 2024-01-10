package AaD_04_ManejoConectores_SQL.PokemonDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLPokemoNDAO implements PokemonDAO{
    Connection connection;
    @Override
    public boolean create(Pokemon pokemon, Connection connection, String tipo1, String tipo2) throws SQLException {
        boolean creadoCorrectamente = false;
            try {
                try{
                    readById(connection, pokemon.getIdNum());
                } catch (PokemonNoExisteException e){
                    connection.setAutoCommit(false);
                    String query = "INSERT INTO pokemon (idNum, name, HP, speed, attack, special_attack, defense, " +
                            "special_defense, evolve_id, generation) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement ps = connection.prepareStatement(query)) {
                        ps.setInt(1, pokemon.getIdNum());
                        ps.setString(2, pokemon.getName());
                        ps.setInt(3, pokemon.getHP());
                        ps.setInt(4, pokemon.getSpeed());
                        ps.setInt(5, pokemon.getAttack());
                        ps.setInt(6, pokemon.getSpecialAttack());
                        ps.setInt(7, pokemon.getDefense());
                        ps.setInt(8, pokemon.getSpecialDefense());
                        ps.setInt(9, pokemon.getEvolveId());
                        ps.setInt(10, pokemon.getGeneration());
                        ps.executeUpdate();
                    }
                    String queryTipos = "INSERT INTO pokemon_type ( pokemon_id,type_1, type_2) VALUES(?, ?, ?);";
                    try (PreparedStatement ps2 = connection.prepareStatement(queryTipos)) {
                        ps2.setInt(1, pokemon.getIdNum());
                        ps2.setString(2, tipo1);
                        ps2.setString(3, tipo2);
                        ps2.executeUpdate();
                    }
                    creadoCorrectamente = true;
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            }
            finally {
                connection.setAutoCommit(true);

            }

        return creadoCorrectamente;
    }

    @Override
    public Pokemon readById(Connection connection, int idPokemon) throws PokemonNoExisteException {
        Pokemon pokemon = null;
        String query = "SELECT idNum, name, HP, speed, attack, special_attack, defense, special_defense, evolve_id, generation FROM pokemon WHERE idNum = ?;";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idPokemon);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                pokemon = new Pokemon( rs.getInt("idNum"),
                        rs.getString("name"),
                        rs.getInt("HP"),
                        rs.getInt("speed"),
                        rs.getInt("attack"),
                        rs.getInt("special_attack"),
                        rs.getInt("defense"),
                        rs.getInt("special_defense"),
                        rs.getInt("evolve_id"),
                        rs.getInt("generation")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if (pokemon == null) throw new PokemonNoExisteException();
        return pokemon;
    }

    @Override
    public boolean update(Connection connection, Pokemon pokemon) throws PokemonNoExisteException {
        boolean actualizadoCorrectamente = false;
        String query = "UPDATE pokemon SET name=?, HP=?, speed=?, attack=?, special_attack=?, " +
                "defense=?, special_defense=?, evolve_id=?, generation=? WHERE idNum=?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            if(readById(connection, pokemon.getIdNum())==null) throw new PokemonNoExisteException();
            ps.setString(1, pokemon.getName());
            ps.setInt(2, pokemon.getHP());
            ps.setInt(3, pokemon.getSpeed());
            ps.setInt(4, pokemon.getAttack());
            ps.setInt(5, pokemon.getSpecialAttack());
            ps.setInt(6, pokemon.getDefense());
            ps.setInt(7, pokemon.getSpecialDefense());
            ps.setInt(8, pokemon.getEvolveId());
            ps.setInt(9, pokemon.getGeneration());
            ps.setInt(10, pokemon.getIdNum());
            ps.executeUpdate();
            actualizadoCorrectamente = true;
        }catch (SQLException e){
            e.getMessage();
            e.printStackTrace();
        }

        return actualizadoCorrectamente;
    }

    @Override
    public boolean deleteById(Connection connection, int idPokemon) throws PokemonNoExisteException {
        boolean borradoCorrectamente = false;
        if (readById(connection, idPokemon)== null){
            throw new PokemonNoExisteException();
        }
        else{
            try{
                connection.setAutoCommit(false);
                String query = "DELETE FROM pokemon WHERE idNum = ?";
                String query2 = "DELETE FROM pokemon_type WHERE pokemon_id = ?";
                try(PreparedStatement ps = connection.prepareStatement(query)){
                    ps.setInt(1,idPokemon);
                    ps.executeUpdate();
                }
                try(PreparedStatement ps2 = connection.prepareStatement(query2)){
                    ps2.setInt(1, idPokemon);
                    ps2.executeUpdate();
                }
                borradoCorrectamente = true;
                connection.commit();

            }catch (SQLException e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        e.printStackTrace();
                    }
            }
        }

        return borradoCorrectamente;
    }
}
