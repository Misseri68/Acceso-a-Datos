package AaD_04_ManejoConectores_SQL;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SQL_4_2_1 {
    /*
    * 1.Refactoriza el ejercicio 4.1.3 para que se haga con sentencias preparadas.
    *   La consulta debe mostrar solo los empleados de un departamento determinado. El
        departamento se filtrará por nombre, no por identificador
    */
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.56.101:3306/empleados";
        String username = "admin00";
        String password = "alumno";
        String departamento = "Ventas";

        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            //En vez de Statement, PreparedStatement
            //En vez de conexion.createStatement(), conexion.prepareStatement()
            String selectEmplDepart = "SELECT ds.nombre_departamento, e.id_empleado, e.nombre, d.nombre as nombreDir, e.apellido, d.apellido as apellidoDir, e.email, d.email as emailDir, e.telefono, e.fecha_contratacion, e.id_trabajo, d.id_trabajo as id_TrabajoDir, e.salario, d.salario as salarioDir, e.comision, d.comision as comisionDir, e.id_director FROM empleados e LEFT JOIN empleados d ON (d.id_empleado = e.id_director) JOIN departamentos ds ON (e.id_departamento = ds.id_departamento) WHERE nombre_departamento = ?;";
            try (PreparedStatement prepStatement = conexion.prepareStatement(selectEmplDepart)) {
                prepStatement.setString(1, departamento);

                try (ResultSet resultado = prepStatement.executeQuery()) {
                    //Recorro los resultados:
                    while (resultado.next()) {
                        int idEmpleado = resultado.getInt("id_empleado");
                        String nombre = resultado.getString("nombre");
                        String apellido = resultado.getString("apellido");
                        String email = resultado.getString("email");
                        String telefono = resultado.getString("telefono");
                        Date fechaCon = resultado.getDate("fecha_contratacion");
                        Calendar fechaContratacion = new GregorianCalendar();
                        fechaContratacion.setTime(fechaCon);
                        String id_trabajo = resultado.getString("id_trabajo");
                        float salario = resultado.getFloat("salario");
                        float comision = resultado.getFloat("comision");
                        int idDirector = resultado.getInt("id_director");
                        if (idDirector != 0) {
                            String nombreDir = resultado.getString("nombreDir");
                            String apellidoDir = resultado.getString("apellidoDir");
                            String emailDir = resultado.getString("emailDir");
                            String id_TrabajoDir = resultado.getString("id_TrabajoDir");
                            float salarioDir = resultado.getFloat("salarioDir");
                            float comisionDir = resultado.getFloat("comisionDir");
                            SQL_4_1_3 director = new SQL_4_1_3(idEmpleado, nombreDir, apellidoDir, emailDir, id_TrabajoDir, salarioDir, comisionDir);
                            SQL_4_1_3 empleado = new SQL_4_1_3(idEmpleado, nombre, apellido, email
                                    , telefono, fechaContratacion, id_trabajo, salario, comision, director);
                            System.out.println(empleado);
                        } else {
                            SQL_4_1_3 empleado = new SQL_4_1_3(idEmpleado, nombre, apellido, email
                                    , telefono, fechaContratacion, id_trabajo, salario, comision);
                            System.out.println(empleado);
                        }
                    }


                } catch (SQLException e) {
                    System.out.println("Error con la consulta");
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR: no se pudo acceder a la base de datos Empleados.");
            e.printStackTrace();
            e.getMessage(); //para ver el error que lanza sql
            //Estado SQL: 42S02 \\   e.getSQLState()
            //Código de error: 1146 \\   e.getErrorCode()
        }
    }




    /*
    PUBLIC CLASS MIEXCEPTION EXTENDS EXCEPTION
    PUBLIC STATIC FINAL INT identificador_MAL = -1;
    public static ifnal int nombre_mal = -2;

   publi static ifnal int no-concuerda_salario= -3*/
}