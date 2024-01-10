package AaD_04_ManejoConectores_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQL_4_2_3 {
    /*
    * 3. Crea un método que permita borrar un empleado dado su identificador. Ten en
cuenta que puede haber condiciones que impidan que se elimine como por ejemplo que sea director de otro empleado.
*/
    static List<SQL_4_1_3> listaEmpleados;
    static String url = "jdbc:mysql://192.168.56.101:3306/empleados";
    static String username = "admin00";
    static String password = "alumno";
    public static void main(String[] args) {
        listaEmpleados = SQL_4_1_3.listaEmpleadosYJefes();
        SQL_4_1_3 currentEmpleado;

        for (int i = 0; i < listaEmpleados.size(); i++) {
            currentEmpleado = listaEmpleados.get(i);
            boolean borradoDirector = false;
            while (tieneJefe(currentEmpleado)) {
                //Mientras tenga jefe, buscará por la lista la id de empleado del director que coincida
                //cada vez que borre tendría que volver a cargar la lista?
                //Tendría que borrar tanto de la base de datos como de la list, o volver a cargar la list sacando el for fuera?
                for (int j = 0; j < listaEmpleados.size(); j++) {
                    SQL_4_1_3 directorEncontrado = listaEmpleados.get(j);
                    if(currentEmpleado.getId_director() == directorEncontrado.getId_empleado()){
                        currentEmpleado = directorEncontrado;
                        String nombre = currentEmpleado.getNombre();
                        deleteEmpleado(currentEmpleado);
                        borradoDirector = true;
                        System.out.println("Director " + nombre + " borrado" );
                    }
                }
            }
            //Si ha tenido que borrar (porque se cumplía la condición del while se borra) vuelve a iterar sobre el mismo
            //empleado hasta que ya no le queden jefes, y cuando ya no le queden jefes no lo borrará y significará
            //que por fin se puede borrar el empleado.
            if (borradoDirector){
                i--;
            }
            else{
                currentEmpleado = listaEmpleados.get(i);
                deleteEmpleado(currentEmpleado);
            }
        }





    }

    public static void deleteEmpleadoStatement(int id_empleado) {
        try (Connection conexion = DriverManager.getConnection(url, username, password)) {
            //En vez de Statement, PreparedStatement
            //En vez de conexion.createStatement(), conexion.prepareStatement()
            String borrarPorId = "DELETE FROM empleados WHERE id_empleado = ?";
            try (PreparedStatement prepStatement = conexion.prepareStatement(borrarPorId)) {
                prepStatement.setInt(1, id_empleado);
                prepStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error con la consulta en el metodo deleteEmpleado");
                e.printStackTrace();
                e.getMessage();
            }
        } catch (SQLException e) {
            System.out.println("Error con la conexión en el metodo deleteEmpleado");
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static void deleteEmpleado(SQL_4_1_3 empleado){
        listaEmpleados.remove(empleado);
        deleteEmpleadoStatement(empleado.getId_empleado());
    }

    public static boolean tieneJefe(SQL_4_1_3 empleado){
        if (empleado.getId_director() == 0){
            return false;
        }
        else return true;
    }

}


