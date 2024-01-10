package AaD_01_Ficheros.Antiguos;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class Files_1_2_antiguo {
    public static void main(String[] args) {
        String ruta = ".\\src\\main\\java\\AaD_01_Ficheros\\DirArchivos\\";
        String rutaAaD = ".\\src\\main\\java\\AaD_01_Ficheros\\";
        String rutaAbsoluta = "C:\\Users\\ariad\\OneDrive\\2º DAM\\Acceso a Datos\\AaD\\src\\main\\java\\AaD_01_Ficheros\\DirArchivos\\AriadnaLópez.txt";
        String rutaAbsoluta2 = "C:\\Users\\ariad\\OneDrive\\2º DAM\\Acceso a Datos\\AaD\\src\\main\\java\\AaD_01_Ficheros\\DirArchivos\\DAM2AriadnaLópez.txt";

        //listarDirectorio();
        generarArchivo(ruta);
        renombrarArchivos(rutaAbsoluta);
        borrarArchivo(rutaAbsoluta2);
        //j08();
    }

    //TODO Comprobacion de argumentos ALWAYS
    public static void listarDirectorio() {
        Path dirActual = Paths.get(".").toAbsolutePath();
        try {
            Stream<Path> listado = Files.list(dirActual);
            listado.forEach( path -> {
                System.out.println(path);
                //o tmb puedes hacer (System.out::printl y ya.)


            }); //Aquí utilizo el método forEach para imprimir cada elemento de Stream<Path>, dentro de la expresión lambda
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void listarDirectorio(String directorio) {
        Path dirActual = Paths.get(directorio);
        if (Files.isDirectory(dirActual)){
            try {
                Stream<Path> listado = Files.list(dirActual);
                listado.forEach( path -> {
                    System.out.println(path);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("El path no es un directorio!");
        }

    }

    public static void generarArchivo(String directorio){
        Path archivoPath = Paths.get(directorio + "AriadnaLópez.txt");
        try {
            if (Files.exists(Path.of(archivoPath + "AriadnaLópez.txt"))) {
                throw new FileAlreadyExistsException(directorio);
            }
            Files.createFile(archivoPath);
            System.out.println();
        } catch (FileAlreadyExistsException e){
            System.err.println("El archivo ya existe!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void renombrarArchivos(String pathAbsoluto){
        try {
            Path archivo = Paths.get(pathAbsoluto);
            Path archivoDeseado = Paths.get(archivo.getParent() + "\\DAM2" + archivo.getFileName());
            Files.move(archivo,archivoDeseado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void borrarArchivo(String pathAbsoluto){
        try {
            Path archivo = Paths.get(pathAbsoluto);
            Files.delete(archivo);
        }
        catch (NoSuchFileException e){
            System.err.println("El archivo no existe!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarDirectorio(String directorio){
        Path pathDir = Paths.get(directorio);
        try{
            Stream<Path> streamDePaths = Files.list(pathDir);
            Path[] listaDePaths =  streamDePaths.toArray(Path[] :: new); //aqui llamo al constructor de la clase Path[] en la expresion lambda?
            //Se puede hacer tambien casteandolo a Path[], pero quería ver si esto también funcionaba.
            for (int i = 0; i < listaDePaths.length; i++) {
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
    }

    public static void ej08(){
        Path path = Paths.get(".\\src\\Nueva Carpeta\\ejercicios\\hola.txt");
        Path other = Paths.get(".\\src\\Nueva Carpeta\\apuntes\\adios.txt");
        Path resultado =  path.relativize(other);
        //resultado : ..\..\apuntes\adios.txt dice como llegar a la ruta segunda desde la primera
        System.out.println(path.resolve(resultado));
        //me devuelve: .\src\Nueva Carpeta\ejercicios\hola.txt\..\..\apuntes\adios.txt  junta las dos rutas.
    }









}


