package AaD_01_Ficheros;

import java.io.File;

public class ShowInfo extends File {
    public ShowInfo(String pathname) {
        super(pathname);
    }
    /* 8. Crea una clase que herede dejava.io.Filey le añada un método que se llameshowInfo().
     * Este método devolverá un Stringcon la siguiente información delfichero:
     * Nombre.
     * Ruta.
     * Ruta absoluta.
     * ¿Se puede leer?
     * ¿Se puede escribir?
     * Tamaño.
     * ¿Es un directorio?
     * En caso afirmativo mostrará los contenidos del mismo.
     * ¿Es un fichero?
     * Cada elemento debe ir en una línea y llevar delante un texto que indique a qué se refiere.*/
    public String showInfo(){
        StringBuilder strBd = new StringBuilder();
        strBd.append("Nombre: ").append(getName()).append("\n");
        strBd.append("Ruta: ").append(getPath()).append("\n");
        strBd.append("Ruta absoluta: ").append(getAbsolutePath()).append("\n");
        strBd.append("¿Se puede leer? ").append(canRead()).append("\n");
        strBd.append("Se puede escribir? ").append(canWrite()).append("\n");
        if (isFile()){
            strBd.append("Tamaño: ").append(length()).append("\n");
            strBd.append("Es fichero? ").append(isFile()).append("\n");
            strBd.append("Es directorio? ").append(isDirectory()).append("\n");
        }
        else if(isDirectory()){
            strBd.append("Es fichero? ").append(isFile()).append("\n");
            strBd.append("Es directorio? ").append(isDirectory()).append("\n");
            if(list()!=null && list().length>0){
                strBd.append("Contenidos del directorio: ");
                String[] list = list();
                for (String fichero: list){
                    strBd.append("\t" + fichero).append("\n");
                }
            }
        }

        String stringBool = strBd.toString();

        String stringBoolTrue= stringBool.replaceAll("true", "Sí.");
        String finalString = stringBoolTrue.replaceAll("false", "No.");
        return finalString;
    }
}

/*
* public class ShowInfo extends File {
    public ShowInfo(String pathname) {
        super(pathname);
    }

 Método estático que devuelve un String con información sobre el archivo. No haría falta extendeer de File.
 *
 *
public static String showInfo(File archivo) {
    StringBuilder strBd = new StringBuilder();
    strBd.append("Nombre: ").append(archivo.getName()).append("\n");
    strBd.append("Ruta: ").append(archivo.getPath()).append("\n");
    strBd.append("Ruta absoluta: ").append(archivo.getAbsolutePath()).append("\n");
    strBd.append("¿Se puede leer? ").append(archivo.canRead()).append("\n");
    strBd.append("Se puede escribir? ").append(archivo.canWrite()).append("\n");

    if (archivo.isFile()) {
        strBd.append("Tamaño: ").append(archivo.length()).append("\n");
        strBd.append("Es fichero? ").append(archivo.isFile()).append("\n");
        strBd.append("Es directorio? ").append(archivo.isDirectory()).append("\n");
    } else if (archivo.isDirectory()) {
        strBd.append("Es fichero? ").append(archivo.isFile()).append("\n");
        strBd.append("Es directorio? ").append(archivo.isDirectory()).append("\n");

        String[] listaContenidos = archivo.list();
        if (listaContenidos != null && listaContenidos.length > 0) {
            strBd.append("Contenidos del directorio: ");
            for (String fichero : listaContenidos) {
                strBd.append("\t" + fichero).append("\n");
            }
        }
    }

    String stringBool = strBd.toString();
    String stringBoolTrue = stringBool.replaceAll("true", "Sí.");
    String finalString = stringBoolTrue.replaceAll("false", "No.");

    return finalString;
}
}*/
