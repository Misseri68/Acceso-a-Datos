package AaD_04_ManejoConectores_SQL;

import AaD_04_ManejoConectores_SQL.SQL_4_1_3;

import java.sql.*;
import java.util.*;

public class
SQL_4_3{

    static String url = "jdbc:mysql://192.168.56.101:3306/empleados";
    static String username = "admin00";
    static String password = "alumno";
    //TODO: para gestionar errores sergio prefiere e.printStackTrace, e.getMEssage, e.getError....? un sout?

/*  1. Crea un programa que cargue todos los empleados en un ResultSet, ordenados por número de departamento.
        Posteriormente debes recorrer el RS para determinar donde empiezan y terminan los empleados del departamento 30.
        Una vez tengas las posiciones, muéstralos en orden inverso*/
/*. 2. Crea un programa que cargue todos los empleados en un RS y modifique sus salarios:
    el de los pares lo multiplicará por  2 y el de los impares lo dividirá entre
Posteriormente debes volver a recorrer el RS para comprobar si los salarios se encuentran entre los valores indicados para cada trabajo.
    En caso contrario los actualizará:
    Si no llega al mínimo, se le asignará el mínimo.
    Si se pasa del máximo, se le asignará el máximo.*/
/*3. Crea un programa que cargue las localizaciones en un RS y los departamentos en otro. Debe borrar aquellas localizaciones en
las que no haya ningún departamento. Si es necesario, puedes modificar los datos de la BdD para probar tu programa.*/

    public static void main(String[] args) {

        try( Connection connection = DriverManager.getConnection(url, username, password)){
            //cargarEmpleadosAResultSet(connection,30);
            //modificarSalarios(connection);
            borrarLocalizaciones(connection);
        } catch (SQLException e){
            System.out.println("ERROR: no se pudo establecer la conexión con la base de datos. \n" + e.getMessage());
        }

    }

    public static void cargarEmpleadosAResultSet(Connection connection, int numeroDepartamento){
        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)){
            String query = "SELECT id_empleado, nombre, apellido, id_departamento FROM empleados ORDER BY id_departamento;";
            try(ResultSet rs = statement.executeQuery(query)) {
                //TODO: PRIMERA FORMA DE HACERLO (Con previous())
                /*int primeraFilaRango = -1;
                int ultimaFilaRango= -1;
                while(rs.next()){
                    int cursorFila = rs.getRow();
                    if(rs.getInt("id_departamento") == numeroDepartamento){
                        if(primeraFilaRango == -1){
                            primeraFilaRango = cursorFila;
                        }
                        ultimaFilaRango = cursorFila;
                    }
                }
                if(primeraFilaRango != -1 && ultimaFilaRango != -1){
                    //Aquí pngo mas uno ya que al empezar el while moverá el cursor uno hacia atrás y se perderá este.
                    rs.absolute(ultimaFilaRango +1);
                    while(rs.previous() && rs.getRow()>= primeraFilaRango){
                        int idEmpleado= rs.getInt("id_empleado");
                        String nombre= rs.getString("nombre");
                        String apellido= rs.getString("apellido");
                        int idDepartamento=rs.getInt("id_departamento");
                        System.out.println(new AaD_04_ManejoConectores_SQL.SQL_4_1_3(idEmpleado, nombre, apellido, idDepartamento));
                    }
                }
                else System.out.println("no se ha encontrado el segmento de departamentos");*/
                //TODO: SEGUNDA FORMA (RELATIVE())
                int ultimaFilaRango= 0;
                int numeroFilasCoincidentes = 0;
                while(rs.next()){
                    int cursorFila = rs.getRow();
                    if(rs.getInt("id_departamento") == numeroDepartamento){
                        numeroFilasCoincidentes++;
                        ultimaFilaRango = cursorFila;
                    }
                }
                //Aquí pngo mas uno ya que al empezar el while moverá el cursor uno hacia atrás y se perderá este.
                rs.absolute(ultimaFilaRango+1);
                while(rs.previous() && numeroFilasCoincidentes>0){
                    int idEmpleado= rs.getInt("id_empleado");
                    String nombre= rs.getString("nombre");
                    String apellido= rs.getString("apellido");
                    int idDepartamento=rs.getInt("id_departamento");
                    System.out.println(new SQL_4_1_3(idEmpleado, nombre, apellido, idDepartamento));
                    numeroFilasCoincidentes--;
                }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }catch(SQLException e){
            System.out.println("ERR: No se ha podido establecer conexión con la base de datos \n" + e.getMessage());
        }
    }
    public static void modificarSalarios(Connection connection) {
        HashMap<String, Integer> salariosMaximos = new HashMap<>();
        HashMap<String, Integer> salariosMinimos = new HashMap<>();

        try(Statement statement = connection.createStatement()){
            String consultaSalarios = "SELECT id_trabajo, min_salario, max_salario FROM trabajos;";
            try(ResultSet rs = statement.executeQuery(consultaSalarios)){
                while(rs.next()) {
                    salariosMinimos.put(rs.getString("id_trabajo"), rs.getInt("min_salario"));
                    salariosMaximos.put(rs.getString("id_trabajo"), rs.getInt("max_salario"));
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());}

        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            String consultaActualizarSalarios = "SELECT id_empleado, id_trabajo, salario FROM empleados;";
                try(ResultSet rs = statement.executeQuery(consultaActualizarSalarios)){
                    while(rs.next()){
                        int idEmpleado = rs.getInt("id_empleado");
                        String idTrabajo = rs.getString("id_trabajo");
                        double salario = rs.getDouble("salario");
                        double salarioMinimo = salariosMinimos.get(idTrabajo);
                        double salarioMaximo = salariosMaximos.get(idTrabajo);
                        if (idEmpleado % 2 ==0) {
                            if((salario*2) > salarioMaximo){
                                rs.updateDouble("salario", (salarioMaximo));
                            }
                            else rs.updateDouble("salario",(salarioMaximo*2));
                            rs.updateRow();
                        }
                        else {
                            if((salario/2) < salarioMinimo){
                                rs.updateDouble("salario", salarioMinimo);
                            }
                            else rs.updateDouble("salario", (salarioMinimo/2));
                            rs.updateRow();
                        }
                    }
                }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void borrarLocalizaciones(Connection connection){
        Set<Integer> listaLocalDepartamentos = new HashSet<>();
        try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            String query = "SELECT DISTINCT id_localizacion FROM departamentos";
            try (ResultSet rs = statement.executeQuery(query)) {
                while (rs.next()) {
                    listaLocalDepartamentos.add(rs.getInt("id_localizacion"));
                }
            }
        }  catch(SQLException e){
            System.out.println(e.getMessage());
        }
        try(Statement statement1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            String queryLocal = "SELECT id_localizacion FROM localizaciones WHERE id_localizacion NOT IN (SELECT id_localizacion FROM departamentos);";

            try(ResultSet rs = statement1.executeQuery(queryLocal)){
                while(rs.next()){
                    rs.deleteRow();
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}




