package AaD_04_ManejoConectores_SQL;

import AaD_04_ManejoConectores_SQL.SQL_4_1_3;

import java.sql.*;
import java.util.Calendar;

public class SQL_4_2_2 {
    /*
 2. Crea un método para insertar un nuevo empleado a partir del código del ejercicio 4.2.1.
 Tendrás un objeto de la clase que creaste y ese es el que debes insertar.
Ten en cuenta que necesitarás datos como el id del director, etc.
*     */
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.56.101:3306/empleados";
        String username = "admin00";
        String password = "alumno";

        SQL_4_1_3 empleado = new SQL_4_1_3(666, "Kurt", "Cobain", "kurt.cobain@gmail.shot", "352099231", convertirACalendar(new Date(94, 3, 5)) , "MK_MAN", 1994.00f, 0.27f);

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            //En vez de Statement, PreparedStatement
            //En vez de conexion.createStatement(), conexion.prepareStatement()
            String insertEmpleadoQuery = "INSERT INTO empleados (id_empleado, nombre, apellido, email, telefono, fecha_contratacion, id_trabajo, salario, comision) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement prepStatement = conexion.prepareStatement(insertEmpleadoQuery)) {
                prepStatement.setInt(1, empleado.getId_empleado());
                prepStatement.setString(2, empleado.getNombre());
                prepStatement.setString(3, empleado.getApellido());
                prepStatement.setString(4, empleado.getEmail());
                prepStatement.setString(5, empleado.getTelefono());
                prepStatement.setDate(6, new java.sql.Date(empleado.getFechaContratacion().getTime().getTime()));
                prepStatement.setString(7, empleado.getId_trabajo());
                prepStatement.setFloat(8, empleado.getSalario());
                prepStatement.setFloat(9, empleado.getComision());
                prepStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error con la consulta");
                e.printStackTrace();
                e.getMessage();
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
            e.getMessage();
        }
    }

    // Método para convertir un java.util.Date a Calendar
    public static Calendar convertirACalendar(Date fecha) {
        if (fecha != null) {
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(fecha);
            return calendario;
        } else {
            throw new IllegalArgumentException("Date no debería ser null.");
        }
    }



}