package AaD_01_Ficheros.Antiguos;//Java i(input)o(output) File (referencia a la localizacion del fichero/directorio y de sus atributos)
import java.io.File;

public class _1_File_y_métodos {

    public static void main(String[] args) {


        File archivo = new File("fichero1.txt");

        if(archivo.exists()) {
            System.out.println("El archivo existe! :D!");
            System.out.println(archivo.getPath());
            System.out.println(archivo.getAbsolutePath());
            System.out.println(archivo.isFile());
            //archivo.delete();
        }
        else {
            System.out.println("Ese archivo no existe. :(");
        }
    }



/*
FILE SEPARATOR:
Se usa para que no haya rayadas entre sistemas operativos (como windows y linux que tienen distintos file separators)
String separadorWin = "\\";
String separadorWinExp = "\\\\"; (esto sería \\ porque para java \\ = \.
String separadorUnix = "/";
String rutaModificada = null;
    // Comprobamos si es ruta Windows (comparamos el String que devuelva "File.separator" con nuestro String separadorWin.)
System.out.println(File.separator.equals(separadorWin)); (false or true)
    if (File.separator.equals(separadorWin)) {
        //Modificamos el separador que coincida con el String separadorWin
    rutaModificada = ruta.replaceAll((separadorquequieressustituir, aka currentseparador)), (separador al que quieres cambiar)));
    }
*/
}
