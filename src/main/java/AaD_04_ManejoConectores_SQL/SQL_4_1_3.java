package AaD_04_ManejoConectores_SQL;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SQL_4_1_3 {
    private static String url = "jdbc:mysql://192.168.56.101:3306/empleados";
    private static String username = "admin00";
    private static String password = "alumno";

    private static List<SQL_4_1_3> listaEmpleados = new ArrayList<>();

    public SQL_4_1_3(String nombre, String apellido, float salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
    }

    public SQL_4_1_3(int idEmpleado, String nombre, String apellido, float salario) {
        this.id_empleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.salario = salario;
    }


    /*Ahora queremos poder añadir a la clase los datos del director de cada empleado.
    Observa que es una relación reflexiva (”id_director” apunta a la tabla empleados).
    ¿Qué tipo de dato debes usar para guardar los datos del director? Aquí puedes
    observar cómo en BdD relacionales usamos claves ajenas mientras que en POO se utilizan referencias a objetos.*/
    public static void main(String[] args) {
        listaEmpleadosYJefes();
    }


    public static List<SQL_4_1_3> listaEmpleadosYJefes(){

        try(Connection conexion = DriverManager.getConnection(url, username, password)){
            try(Statement sentencia = conexion.createStatement()){

              /*  String consulta = "SELECT e.id_empleado, d.id_empleado as idEmpleadoDir, e.nombre, d.nombre as nombreDir, e.apellido, d.apellido as apellidoDir, e.email, d.email as emailDir, e.telefono, e.fecha_contratacion, e.id_trabajo, d.id_trabajo as id_TrabajoDir, e.salario, d.salario as salarioDir, e.comision, d.comision as comisionDir, e.id_director FROM empleados e JOIN empleados d ON (d.id_empleado = e.id_director);";
*/
                String consulta = "SELECT * FROM empleados";
                try(ResultSet resultado = sentencia.executeQuery(consulta)){
                    //Recorro los resultados:
                    while(resultado.next()) {
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
                        SQL_4_1_3 empleado = new SQL_4_1_3(idEmpleado, nombre, apellido, email
                                , telefono, fechaContratacion, id_trabajo, salario, comision, idDirector );
                        listaEmpleados.add(empleado);
                        /*
                        if (idDirector != 0) {
                            String nombreDir = resultado.getString("nombreDir");
                            String apellidoDir = resultado.getString("apellidoDir");
                            String emailDir = resultado.getString("emailDir");
                            String id_TrabajoDir = resultado.getString("id_TrabajoDir");
                            float salarioDir = resultado.getFloat("salarioDir");
                            float comisionDir = resultado.getFloat("comisionDir");
                            AaD_04_ManejoConectores_SQL.SQL_4_1_3  director = new AaD_04_ManejoConectores_SQL.SQL_4_1_3(idEmpleado, nombreDir, apellidoDir, emailDir, id_TrabajoDir, salarioDir, comisionDir);
                            AaD_04_ManejoConectores_SQL.SQL_4_1_3 empleado = new AaD_04_ManejoConectores_SQL.SQL_4_1_3(idEmpleado, nombre, apellido, email
                                    , telefono, fechaContratacion, id_trabajo, salario, comision );
                            resEmpl.agregarEmpleado(empleado);
                        } else {
                            AaD_04_ManejoConectores_SQL.SQL_4_1_3 empleado = new AaD_04_ManejoConectores_SQL.SQL_4_1_3(idEmpleado, nombre, apellido, email
                                    , telefono, fechaContratacion, id_trabajo, salario, comision);

                            resEmpl.agregarEmpleado(empleado);
                        }
                        */

                    }



                } catch(SQLException e){
                    System.out.println("Error con la consulta");
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        }catch(SQLException e){
            System.out.println("ERROR: no se pudo acceder a la base de datos Empleados.");
            e.printStackTrace();
            e.getMessage(); //para ver el error que lanza sql
            //Estado SQL: 42S02 \\   e.getSQLState()
            //Código de error: 1146 \\   e.getErrorCode()
        }
        return listaEmpleados;

    }



    private int id_empleado;
    private String nombre;
    private String apellido;
    private String email ;
    private String telefono;
    private Calendar fechaContratacion;
    private String id_trabajo;
    private float salario;
    private float comision;
    private int id_director;
    private SQL_4_1_3 director;

    public SQL_4_1_3(){

    }
    public SQL_4_1_3(int id_empleado, String nombreDir, String apellidoDir, String emailDir, String idTrabajoDir, float salarioDir, float comisionDir) {
        this.id_empleado = id_empleado;
        this.nombre = nombreDir;
        this.apellido = apellidoDir;
        this.email = emailDir;
        this.id_trabajo = idTrabajoDir;
        this.salario = salarioDir;
        this.comision = comisionDir;

    }
    public SQL_4_1_3(int id_empleado, String nombre, String apellido, String email, String telefono, Calendar fechaContratacion, String id_trabajo, float salario, float comision,  SQL_4_1_3 director) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaContratacion = fechaContratacion;
        this.id_trabajo = id_trabajo;
        this.salario = salario;
        this.comision = comision;
        this.director = director;
    }

    public SQL_4_1_3(int id_empleado, String nombre, String apellido, String email, String telefono, Calendar fechaContratacion, String id_trabajo, float salario, float comision, int id_director) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaContratacion = fechaContratacion;
        this.id_trabajo = id_trabajo;
        this.salario = salario;
        this.comision = comision;
        this.id_director = id_director;
    }

    public SQL_4_1_3(int id_empleado, String nombre, String apellido, String email, String telefono, Calendar fechaContratacion, String id_trabajo, float salario, float comision) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaContratacion = fechaContratacion;
        this.id_trabajo = id_trabajo;
        this.salario = salario;
        this.comision = comision;
    }

    public int getId_director() {
        return id_director;
    }

    public void setId_director(int id_director) {
        this.id_director = id_director;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Calendar getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Calendar fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getId_trabajo() {
        return id_trabajo;
    }

    public void setId_trabajo(String id_trabajo) {
        this.id_trabajo = id_trabajo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }


    public SQL_4_1_3 getDirector() {
        return director;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if(director!=null){



            return  "id empleado: " + id_empleado + "\n" +
                "nombre: " + nombre + "\n" +
                "apellido: " + apellido + "\n"
                //"email: " + email + "\n"
                + "salario: " + salario + "\n"
                    + "idDepartamento: " + salario + "\n"

                    /*
                    +
                "telefono: " + telefono + "\n" +
                "fecha contratacion: " + dateFormat.format(fechaContratacion.getTime()) + "\n" +
                "id trabajo: " + id_trabajo + "\n" +
                "salario: " + salario + "\n" +
                "comision: " + comision  + "\n"+
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+

                "Datos del director: \n" + director.toStringDirector() + "\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"

                     */
                ;}

        else {

            return  "id empleado: " + id_empleado + "\n" +
                    "nombre: " + nombre + "\n" +
                    "apellido: " + apellido + "\n"
                    + "salario: " + salario + "\n"
                    /*
                    +
                    "apellido: " + apellido + "\n" +
                    "email: " + email + "\n" +
                    "telefono: " + telefono + "\n" +
                    "fecha contratacion: " + dateFormat.format(fechaContratacion.getTime()) + "\n" +
                    "id trabajo: " + id_trabajo + "\n" +
                    "salario: " + salario + "\n" +
                    "comision: " + comision  + "\n";
                     */
            ;
        }

    }

    public String toStringDirector() {
        return
                "nombre director: " + nombre + "\n" ;
           /*    + "apellido director: " + email + apellido + "\n" +
                "email director: " + email + "\n" +
                "salario: " + salario + "\n" +
                "comision: " + comision  + "\n"
                ;
            */
    }
}


