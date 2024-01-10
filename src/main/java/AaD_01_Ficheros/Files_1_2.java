package AaD_01_Ficheros;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Files_1_2 {
    public static void main(String[] args) throws IOException {
        String ruta = ".\\src\\main\\java\\AaD_01_Ficheros\\DirArchivos";
        String rutaAaD = ".\\src\\main\\java\\AaD_01_Ficheros";
        String rutaAbsoluta = "C:\\Users\\ariad\\OneDrive\\2º DAM\\Acceso a Datos\\AaD\\src\\main\\java\\AaD_01_Ficheros\\DirArchivos\\AriadnaLópez.txt";
        metodoListarDirectorios(rutaAaD);
        //métodoExisteFichero(rutaAaD, "01_Manejoficheros.pdf");
        //generarArchivo(ruta);
        //renombrarArchivo(rutaAbsoluta);
        //borrarArchivo(rutaAbsoluta);

    }

    // 1. Crea un métodolistarDirectorio()que devuelva una array con el listado del contenido (archivos y carpetas) del directorio actual.
    public static Path[] metodoListarDirectorios(String ruta) {
        try (Stream<Path> stream = Files.list(Paths.get(ruta))) {
            Path[] lista = stream.toArray(Path[]::new);
            return lista;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * public static void eliminarDirectorio(String directorio){
        Path pathDir = Paths.get(directorio);
        try{
            Stream<Path> streamDePaths = Files.list(pathDir);
            Path[] listaDePaths =  streamDePaths.toArray(Path[] :: new); //aqui llamo al constructor de la clase Path[] en la expresion lambda?            for (int i = 0; i < listaDePaths.length; i++) {
                if (Files.isDirectory(listaDePaths[i])){
                    eliminarDirectorio(listaDePaths[i].toString());

                }
                else {
                    Files.deleteIfExists(listaDePaths[i]);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }*/


    /*
    * Crea un objetoPathcon ”/alumno/DAM/ejercicios/hola.txt” y aplicarelativice()pasándole ”/alumno/DAM/apuntes/adios.txt”.
    *  Muestra y explica el resultado.
    * Pásale el resultado anterior a el objeto con el métodoresolve(). Muestra yexplica el resultado.
    *
    *
    * Resultado de relativize():
El método relativize() calcula la ruta relativa entre dos rutas proporcionadas. En este caso, el resultado podría ser algo como: ../../apuntes/adios.txt. Esto significa que desde la ubicación de hola.txt, puedes subir dos niveles (../../) y luego ir a la carpeta apuntes y acceder al archivo adios.txt.

Resultado de resolve():
El método resolve() combina dos rutas. En este caso, estamos tomando la ruta original (/alumno/DAM/ejercicios/hola.txt) y resolviéndola con la ruta relativa obtenida del relativize() (../../apuntes/adios.txt). El resultado final debería ser /alumno/DAM/apuntes/adios.txt, que es la ubicación completa del archivo adios.txt desde el punto de vista de la ruta original hola.txt.

Estos métodos son útiles para trabajar con rutas de archivos y directorios de manera efectiva en Java.
    * */
}