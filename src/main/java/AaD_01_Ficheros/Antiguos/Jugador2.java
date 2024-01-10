package AaD_01_Ficheros.Antiguos;

import java.io.*;

import java.util.GregorianCalendar;
import java.util.Locale;

public class Jugador2 implements Externalizable {
    private static final long serialVersionUID = 1L; // Puedes cambiar el número según corresponda


    private String nombre = "";
    private String raza = "";
    private int nivel = 0;
    private float vida = 0.0f;
    private boolean estaVivo = true;
    private GregorianCalendar fecha;

    public Jugador2(String nombre, String raza, int nivel, float vida, boolean estaVivo) {
        this.nombre = nombre;
        this.raza = raza;
        this.nivel = nivel;
        this.vida = vida;
        this.estaVivo = estaVivo;
    }

    public Jugador2(String nombre, String raza, int nivel, float vida, boolean estaVivo, GregorianCalendar fecha){
        this.nombre = nombre;
        this.raza = raza;
        this.nivel = nivel;
        this.vida = vida;
        this.estaVivo = estaVivo;
        this.fecha = fecha;

    }

    public Jugador2() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public String toCsvString() {
        //TODO sin el Locale.US creaba otra columna debido al separador "." en vez de "," de la coma.
        return String.format(Locale.US, "%s;%s;%d;%f;%b", nombre, raza, nivel, vida, estaVivo);
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(nombre);
        out.writeUTF(raza);
        out.writeInt(nivel);
        out.writeFloat(vida);
        out.writeBoolean(estaVivo);
        out.writeObject(fecha);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.nombre = in.readUTF();
        this.raza = in.readUTF();
        this.nivel = in.readInt();
        this.vida = in.readFloat();
        this.estaVivo = in.readBoolean();
        this.fecha= (GregorianCalendar) in.readObject();
    }
}


class AppendableObjectOutputStream2 extends ObjectOutputStream {
    public AppendableObjectOutputStream2(OutputStream out)
            throws IOException {
        super(out);
    }
    @Override
    protected void writeStreamHeader() throws IOException {
/// No hacer nada evita la escritura de una nueva cabecera
    }
}
