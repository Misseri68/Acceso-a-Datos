package AaD_01_Ficheros.Antiguos;

import java.io.File;

/*
7. Crea una clase que herede de java.io.File y le añada un método que se llame showInfo(). Este método devolverá un String con la
 siguiente información del fichero:
    Nombre.
    Ruta.
    Ruta absoluta.
    ¿Se puede leer?
    ¿Se puede escribir?
    Tamaño.
    ¿Es un directorio? En caso afirmativo mostrará los contenidos del mismo.
    ¿Es un fichero?
    Cada elemento debe ir en una línea y llevar delante un texto que indique a qué se refiere.*/
public class HerenciaShowInfo extends java.io.File {
    public HerenciaShowInfo(String pathname) {
        super(pathname);
    }

    public static String showInfo(String ruta) {
        String resultado = "";
        File file = new File(ruta);
        String listaConcatenada = "     ";

        if (file.isDirectory()) {
            String[] listaElementosDir = file.list();
            for (int i = 0; i < listaElementosDir.length; i++) {
                listaConcatenada = listaConcatenada + " \n      " + listaElementosDir[i];
            }
        }
        if (file.isDirectory()) {
            resultado = "Información sobre file: \n" +
                    "Nombre: " + file.getName() + "\n" +
                    "Ruta: " + file.getPath() + "\n" +
                    "Ruta absoluta: " + file.getAbsolutePath() + "\n" +
                    "¿Se puede leer? " + file.canRead() + "\n" +
                    "¿Se puede escribir? " + file.canWrite() + "\n" +
                    "Tamaño: " + file.getTotalSpace() + "B"+ "\n" +
                    "¿Es directorio? " + file.isDirectory() + "\n" +
                    "Contenido del directorio: " + listaConcatenada + "\n" +
                    "¿Es fichero? " + file.isFile();

        } else {
            resultado = "Información sobre file: \n" +
                    "Nombre: " + file.getName() + "\n" +
                    "Ruta: " + file.getPath() + "\n" +
                    "Ruta absoluta: " + file.getAbsolutePath() + "\n" +
                    "¿Se puede leer? " + file.canRead() + "\n" +
                    "¿Se puede escribir? " + file.canWrite() + "\n" +
                    "Tamaño: " + file.getTotalSpace() + "B" + "\n" +
                    "¿Es directorio? " + file.isDirectory() + "\n" +
                    "¿Es fichero? " + file.isFile();
        }
        resultado = resultado.replaceAll("true", "Sí.");

        resultado= resultado.replaceAll("false", "No.");

        return resultado;

        //this.canRead() ? "Si" : "No:"
    }
}
