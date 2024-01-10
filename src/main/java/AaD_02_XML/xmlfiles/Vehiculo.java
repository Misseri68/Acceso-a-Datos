package AaD_02_XML.xmlfiles;

public class Vehiculo {
    private String nombre = "";
    private int numero = 0;
    private String tiempo = "";

    public Vehiculo(String nombre, int numero, String tiempo) {
        setNombre(nombre);
        setNumero(numero);
        setTiempo(tiempo);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
