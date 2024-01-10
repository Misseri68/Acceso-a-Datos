package AaD_04_ManejoConectores_SQL;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TeoriaResultSets {}

//La clase Statements devuelve un Resultset.
// Un ResultSet:
    //Cargará las filas que devuelva la ejecución de la sentencia.
    //Permite acceder a los valores de cualquier columna de la fila actual..
    //Se le puede pasar distintos argumentos:
    /*
    * createStatement()
    * createStatement(int resultSetType, int resultSetConcurrency)
    * createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
    * */

    /*
    * TODO: ResultSet.TYPE_...
    *
    * TYPE_FORWARD_ONLY : Sólo se recorrerá hacia adelenta
    * TYPE_SCROLL_INSENSITIVE : el cursor se puede mover hacia adelante o hacia atrás o en una posición concreta. (No refleja los cambios en la BdD)
    * TYPE_SCROLL_SENSITIVE: igual que el anterior, pero sí relfeja los cambios en la BdD desde que se carga la RS.
    */

        /*
    * TODO: ResultSet.CONCUR_...
    *
    *  CONCUR_READ_ONLY: los datos en el RS no se pueden modificar.
    *  CONCUR_UPDATABLE: sí se pueden modificar los datos del RS.
    */

    /*
    * TODO: Comportamiento ante COMMIT: ResultSet.(...)_COMMIT
    * .HOLD_CURSORS_OVER_COMMIT: los objetos abiertos en el RS actual se mantendrán abiertos tras COMMIT.
    * .CLOSE_CURSORS_AT_COMMIT: los objetos abiertos en el RS se cerrarán tras COMMIT.
    */


//TODO NAVEGAR POR EL ResultSet:

    // Al crear el Statement podemos movernos por el RS con los siguientes métodos:
    /**
     * @method next(): mueve el cursor una fila hacia adelante.
     *      @return true si se posiciona en una fila, false si ya no hay más filas.
     * @method previous(): mueve el cursor una fila hacia atrás.
     *      @return true si se posiciona en una fila, false si se posiciona (antes?) de la ultima
     * @method first(): mueve cursor a la primera fila.
     *      @return true si lo consigue, false si no hay filas en el RS.
     * @method: last. =====
     *
     * @method: beforeFirst(): mueve el cursor antes de la primera fila. No hace nada si no hay filas en el RS.
     * @method: afterLast(): ...
     *
     * @method: relative(int rows): mueve el cursor relativa a su posicion actual (mover x filas desde la posicion actual)
     *      @param: nº filas que debe recorrer el cursor. Puede ser -n, 0, y n. @return true si llega a fila valida, flase si no hay más filas.
     * @method: absolute(int rows): mueve el cursor a la fila especificada. No recorre filas, salta a ella. @return true y false like above.
     */

//TODO ACTUALIZAR ResultSet
    /*
    Existen métodos update para los ocho tipos primitivos, para String, Object, URL, y los tipos de datos de SQL que hay en el paquete java.sql
    Cada tipo tiene dos versiones: Por nombre de columna, Por posición de la columna.
        updateString(String columnName, String s)
        updateString(int columnIndex, String s)

    El RS tiene que cumplir unas condiciones:
        -EL Statement tendrá ResultSet.CONCUR_UPDATABLE
        -La Query sólo peude tener una única tabla.
        -La Query no puede usar funciones.
        -La Query debe incluir los atributos que forman la PK.

    Se puede actualizar los datos del RS sin actualizar los de la BdD.
    */

 class MainUpdate {
        static String url = "jdbc:mysql://192.168.56.101:3306/empleados";
        static String username = "admin00";
        static String password = "alumno";

        public static void main(String[] args) {

            float porcentajeSubida = (float) 1.2;

            try (Connection conexion = DriverManager.getConnection(url, username, password)) {
                try (Statement sentencia = conexion.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    String consulta =
                            "SELECT id_empleado, nombre, apellido, fecha_contratacion, "
                                    + "salario FROM empleados;";
                    try (ResultSet resultado = sentencia.executeQuery(consulta)) {
                        while (resultado.next()) {
                            float salario = resultado.getFloat("salario");
                            resultado.updateFloat("salario", salario * porcentajeSubida);
                        }
                        resultado.beforeFirst();
                        // Recorremos los resultados.
                        while (resultado.next()) {
                            int id_empleado = resultado.getInt("id_empleado");
                            String nombre = resultado.getString("nombre");
                            String apellido = resultado.getString("apellido");
                            // Conversión sencilla entre los tipos de fechas.
                            Date fechaCon = resultado.getDate("fecha_contratacion");
                            Calendar fechaContratacion = new GregorianCalendar();
                            fechaContratacion.setTime(fechaCon);
                            float salario = resultado.getFloat("salario");
                            SQL_4_1_3 empleado = new SQL_4_1_3(id_empleado, nombre, apellido, salario );
                            System.out.println(empleado);
                            //   mostrarEmpleado(nombre, apellido, fechaContratacion, salario);
                        }
                    } catch (SQLException ex) {
                        System.out.println("ERR: no se pudo ejecutar la consulta.");
                    }
                }
            } catch (SQLException ex) {
                System.out.println("ERR: no se pudo acceder a la base de datos.");
            }
        }
    }

    //TODO: Trasladar los cambios a la BdD.
    /*
    * Para reflejar los cambios, usaremos updateRow().
    * while(resultado.next())
    *   float salario = resultado.getFloat("salario");
    *   salario.updateFloat ("salario", salario * porcentajeSubida);
    *   resultado.updateRow();
    */

//TODO: INSERCIONES Y BORRADOS SOBRE RS
    /*
    * deleteRow() borrar fila actual
    * Inserciones:
    *     1 rs.moveToInsertRow(); -> desplazar el cursor hasta donde se puede insertar una nueva fila.
    *
    *     2 rs.updateInt ("id_empleado", "675");
    *       rs.updateString ("nombre", "Pedro");
    *
    *     3 rs.insertRow()
    *
    *
    *
    *
    *
    * try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base_de_datos", "tu_usuario", "tu_contraseña")) {

            // Creamos un Statement con capacidad para actualizaciones
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                // Hacemos una consulta para obtener un ResultSet
                String query = "SELECT * FROM empleados";
                try (ResultSet rs = statement.executeQuery(query)) {
                    // Mover el cursor a la posición de inserción
                    rs.moveToInsertRow();

                    // Llenar la fila con los datos deseados
                    rs.updateInt("id_empleado", 675);
                    rs.updateString("nombre", "Pedro");

                    // Ejecutar la inserción
                    rs.insertRow();

                    // Imprimir un mensaje para confirmar la inserción
                    System.out.println("Fila insertada con éxito.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    * */