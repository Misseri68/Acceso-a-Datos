package AaD_01_Ficheros.Antiguos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class Jugador implements Serializable {
    /*
    11. Crea una clase de algo que te llame la atención. La clase debe incluir al menos
    un nombre (String), otro String, un entero, un número de coma flotante, un
    booleano. Por ejemplo los datos de una persona o de algún personaje de videojuegos, etc.
    */
    private static final long serialVersionUID = 1463121057964036141L;

    private String nombre = "";
    private String raza = "";
    private int nivel = 0;
    private float vida = 0.0f;
    private boolean estaVivo = true;
    private GregorianCalendar fecha;

    public Jugador(String nombre, String raza, int nivel, float vida, boolean estaVivo) {
        this.nombre = nombre;
        this.raza = raza;
        this.nivel = nivel;
        this.vida = vida;
        this.estaVivo = estaVivo;
    }

    public Jugador(String nombre, String raza, int nivel, float vida, boolean estaVivo, GregorianCalendar fecha){
        this.nombre = nombre;
        this.raza = raza;
        this.nivel = nivel;
        this.vida = vida;
        this.estaVivo = estaVivo;
        this.fecha = fecha;

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
    public void writeObject(String ruta){
        Path path = Paths.get(ruta);
        try {
            if (!Files.exists(path)){
                Files.createFile(path);
            }
            else{
                if(Files.size(Paths.get(ruta))>0) {
                    AppendableObjectOutputStream aoos = new AppendableObjectOutputStream(new FileOutputStream(ruta, true));
                    aoos.writeObject(this);
                    aoos.close();
                }
                else {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
                    oos.writeObject(this);
                    oos.close();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static void writeObjects(List<Jugador> listaJugadores, String ruta){
        try {
            if(Files.size(Paths.get(ruta))>0) {
                AppendableObjectOutputStream aoos = new AppendableObjectOutputStream(new FileOutputStream(ruta, true));
                for (Jugador j: listaJugadores) {
                    aoos.writeObject(j);
                }
                aoos.close();
            }
            else {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
                for (Jugador j: listaJugadores) {
                    oos.writeObject(j);
                }
                oos.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<Jugador> leerObjetos(String ruta) throws ClassNotFoundException, IOException{
        List<Jugador> listaJugadores = new ArrayList<>();
        try(
                    ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(ruta))
                )
        {
            while(true) {
                Jugador jugador = (Jugador) inStream.readObject();
                listaJugadores.add(jugador);
            }
        }catch (EOFException e) {
            // Se alcanzó el final del archivo
            return listaJugadores;
        } catch (StreamCorruptedException e) {
            // Handle StreamCorruptedException
            e.printStackTrace(); // Handle the exception according to your needs
        }
        return null;
    }
}


 class AppendableObjectOutputStream extends ObjectOutputStream {
    public AppendableObjectOutputStream(OutputStream out)
            throws IOException {
        super(out);
    }
    @Override
    protected void writeStreamHeader() throws IOException {
/// No hacer nada evita la escritura de una nueva cabecera
    }
}