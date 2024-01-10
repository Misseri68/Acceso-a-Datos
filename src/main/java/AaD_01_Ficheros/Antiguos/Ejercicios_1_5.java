package AaD_01_Ficheros.Antiguos;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class Ejercicios_1_5 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String ruta = ".\\src\\archivoOut.bin";
        String ruta2 = ".\\src\\archivoOut2.bin";
        String rutaJugador2 = ".\\src\\archivoOut23.bin";

        //escribirObjeto(ruta);
        //escribirJugadores(ruta);
        //leerJugadores(ruta);
        //escribirObjeto2(ruta2);
        //leerJugadores2(ruta2);



        escribirJugador2(rutaJugador2);
        leerJugadores2(rutaJugador2);

    }
    /*
    * 1. En la clase del ejercicio 1.4.11 añade un método dinámico que sirva para escribir
el propio objeto en un archivo binario que se reciba como argumento. Debe
escribirlo al final*/

    public static void escribirObjeto(String ruta) {
        Jugador jugador = new Jugador("SANEEES", "BadTOM", 1, 1.0f, true);
        jugador.writeObject(ruta);
    }

    /*
    2. Sobre el ejercicio 1.5.1, añade a la clase dos métodos estáticos: uno para escribir
una lista de objetos de ese tipo en un fichero binario y otro para leer todos los
¿Qué sucede si pruebas a leer un archivo con datos incompatibles?*/

    public static void escribirJugadores(String ruta) {
        List<Jugador> listaJugadores = new ArrayList<Jugador>();
        listaJugadores.add(new Jugador("Adrianga", "Tetonga", 0, 0.1f, true));
        listaJugadores.add(new Jugador("Bri", "Bru", 33, 33.33f, false));
        listaJugadores.add(new Jugador("Hiro", "Jhin", 44, 4444.0f, true));
        Jugador.writeObjects(listaJugadores, ruta);

    }

    public static void leerJugadores(String ruta) throws IOException, ClassNotFoundException {
        List<Jugador> listaJugadores = Jugador.leerObjetos(ruta);
        for (Jugador j : listaJugadores) {
            System.out.println(" Jugador:" + j.getNombre() + " " + j.getRaza() +
                    "\n Nivel: " + j.getNivel() +
                    "\n Vida: " + j.getVida() +
                    "\n Vive? " + j.isEstaVivo());
        }
    }

    /*
    *  Añade un campo fecha y haz serializable la clase del ejercicio 1.4.11. Repite los
ejercicios 1.5.1 y 1.5.2 acordemente*/

    public static void escribirObjeto2(String ruta) {
        Jugador jugador = new Jugador("SANEEES", "BadTOM", 1, 1.0f, true, new GregorianCalendar());
        jugador.writeObject(ruta);
    }

    public static void leerObjetos2(String ruta) throws IOException, ClassNotFoundException {
        List<Jugador> listaJugadores = Jugador.leerObjetos(ruta);
        for (Jugador j : listaJugadores) {
            System.out.println(" Jugador:" + j.getNombre() + " " + j.getRaza() +
                    "\n Nivel: " + j.getNivel() +
                    "\n Vida: " + j.getVida() +
                    "\n Vive? " + j.isEstaVivo() +
                    "\n Fecha: " + j.getFecha().getGregorianChange());
        }
    }

    /*
    * . Refactoriza el ejercicio 1.5.3 para que implemente Externalizable en lugar de Serializable*/
    public static void escribirJugador2(String ruta) throws IOException {
        Jugador2 jugador = new Jugador2("Mariano", "Corrupto", 1, 1.01f, true, new GregorianCalendar());
        Path path = Paths.get(ruta);
        if (!Files.exists(path)) {Files.createFile(path);}
        if(Files.size(path)>0){
            AppendableObjectOutputStream2 aoos = new AppendableObjectOutputStream2(new FileOutputStream(ruta, true));
            aoos.writeObject(jugador);
            aoos.close();
        }
        else{
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(ruta));
            oo.writeObject(jugador);
            oo.close();}
    }

    public static void leerJugadores2(String ruta) throws IOException, ClassNotFoundException {
        try(
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta));
        ){
            while (true) {
            Jugador2 j;
            j = (Jugador2) in.readObject();
            System.out.println(" Jugador:" + j.getNombre() + " " + j.getRaza() +
                    "\n Nivel: " + j.getNivel() +
                    "\n Vida: " + j.getVida() +
                    "\n Vive? " + j.isEstaVivo() +
                    "\n Fecha: " + j.getFecha().getGregorianChange());
        }
        }catch(EOFException e){
            System.out.println("End of file.");
        }catch(StreamCorruptedException e){
            e.printStackTrace();
            System.out.println("y murio");
        }
    }
}


