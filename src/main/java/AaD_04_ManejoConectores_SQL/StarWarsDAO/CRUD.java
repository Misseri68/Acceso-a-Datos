package AaD_04_ManejoConectores_SQL.StarWarsDAO;
import java.sql.Connection;
import java.util.List;
import AaD_04_ManejoConectores_SQL.StarWarsDAO.PersonajeSW;

    public interface CRUD {
        boolean crear(PersonajeSW personaje, Connection connection) throws PersonajeYaExisteException;

        List<PersonajeSW> obtenerTodos(Connection connection);
        PersonajeSW obtenerPorNombre(Connection connection, String nombre) throws PersonajeNoExisteException;

        void actualizar(Connection connection, PersonajeSW personaje) throws PersonajeNoExisteException;

        void eliminar(Connection connection, String nombre) throws PersonajeNoExisteException;
    }