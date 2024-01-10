package AaD_04_ManejoConectores_SQL;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SQL_4_1_2 {
/* 2. A la clase anterior añádele lo necesario para incluir el nombre del departamento
en el que trabaja cada empleado. Carga en un List los empleados de los departamentos
”Ejecutivo” y ”Marketing” y posteriormente muestra sus datos por pantalla.*/


    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.56.101:3306/empleados";
        String username = "admin00";
        String password = "alumno";
        List<SQL_4_1_2> empleados = new ArrayList<>();

        try(Connection conexion = DriverManager.getConnection(url, username, password)){
            try(Statement sentencia = conexion.createStatement()){

                String consulta = "";
                try(ResultSet resultado = sentencia.executeQuery(consulta)){
                    //Recorro los resultados:
                    while(resultado.next()) {
                        int id_empleado = resultado.getInt("id_empleado");
                        String nombre = resultado.getString("nombre");
                        String apellido =resultado.getString("apellido");
                        String email = resultado.getString("email");
                        String telefono = resultado.getString("telefono");
                        Date fechaCon = resultado.getDate("fecha_contratacion");
                        Calendar fechaContratacion = new GregorianCalendar();
                        fechaContratacion.setTime(fechaCon);
                        String id_trabajo = resultado.getString("id_trabajo");
                        float salario = resultado.getFloat("salario");
                        float comision = resultado.getFloat("comision");
                        String nombreDepartamento = resultado.getString("nombre_departamento");
                        SQL_4_1_2 empleado = new SQL_4_1_2(id_empleado, nombre, apellido,email
                                ,telefono,fechaContratacion, id_trabajo, salario, comision, nombreDepartamento);
                        if(empleado.getNombreDepartamento().equals( "Marketing") || empleado.getNombreDepartamento().equals("Ejecutivo")) {
                            empleados.add(empleado);
                        }
                    }
                } catch(SQLException e){
                    System.out.println("Error con la consulta");
                    e.printStackTrace();
                    e.getMessage();
                }
                empleados.forEach(System.out::println);

            }
        }catch(SQLException e){
            System.out.println("ERROR: no se pudo acceder a la base de datos Empleados.");
            e.printStackTrace();
            e.getMessage(); //para ver el error que lanza sql
            //Estado SQL: 42S02 \\   e.getSQLState()
            //Código de error: 1146 \\   e.getErrorCode()
        }



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
    private String nombreDepartamento;

    public SQL_4_1_2(int id_empleado, String nombre, String apellido, String email, String telefono, Calendar fechaContratacion, String id_trabajo, float salario, float comision, String nombreDepartamento) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaContratacion = fechaContratacion;
        this.id_trabajo = id_trabajo;
        this.salario = salario;
        this.comision = comision;
        this.nombreDepartamento = nombreDepartamento;
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

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return  "id empleado: " + id_empleado + "\n" +
                "nombre: " + nombre + "\n" +
                "apellido: " + apellido + "\n" +
                "email: " + email + "\n" +
                "telefono: " + telefono + "\n" +
                "fecha contratacion: " + dateFormat.format(fechaContratacion.getTime()) + "\n" +
                "id trabajo: " + id_trabajo + "\n" +
                "salario: " + salario + "\n" +
                "comision: " + comision  + "\n"+
                "nombre de departamento : " + nombreDepartamento + "\n";
    }
}

