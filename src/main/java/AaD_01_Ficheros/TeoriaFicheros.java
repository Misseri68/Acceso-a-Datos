package AaD_01_Ficheros;

import java.io.File;

public class TeoriaFicheros {
    //Existen ficheros o de TEXTO (con distintas codificaciones - UTF-8- ISO 8859-1- UTF-16) o BINARIO.
        //Propiedades del sistema: (System Properties) file.separator, user.home, user.dir, line.separator.
        //Para consultar la propiedad se usaSystem.getProperty(String);
    //TODO la clase java.io.File
    //Las instancias de File representan la ruta y elnombre del fichero, no el fichero como tal.
    // file = An abstract representation of file and directory pathnames
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
   //Se puede usar la ruta relativa que sería ".\\src\\" siendo '.' el directorio del proyecto y el resto la ruta dentro del proyecto hasta el fichero.
    //TODO: Métodos más importantes de File
/*
    * Métodos más importantes.
    *
Creación:
    - createNewFile(): crea un nuevo fichero, vacío, asociado a File si y sólo si no existe un fichero con dicho nombre.
    - mkdir(): crea un directorio con el nombre indicado en la creación del objeto File.
    - mkdirs(): crea también los directorios superiores si no existen.

Información:
    - exists(): devuelve true si el fichero/directorio existe.
    - getName(): devuelve el nombre del fichero o directorio.
    - getPath(): devuelve el camino relativo.
    - getAbsolutePath(): devuelve el camino absoluto del fichero/directorio.
    - canRead(): devuelve true si el fichero se puede leer.
    - canWrite(): devuelve true si el fichero se puede escribir.
    - length(): nos devuelve el tamaño del fichero en bytes.
    - getParent(): devuelve el nombre del directorio padre, o null si no existe.
    - isDirectory(): devuelve true si el objeto File corresponde a un directorio.
    - isFile(): devuelve true si el objeto File corresponde a un fichero normal.
    - list(): si el objeto File es un directorio, devuelve un array con un listado de Strings con los nombres de los archivos y directorios que contiene.
    - listFiles(): igual que el anterior pero el array contendrá un listado de objetos File.

Operaciones sobre el fichero:
    - delete(): borra el fichero o directorio asociado al File.
    - deleteOnExit(): se borra cuando finaliza la ejecución de la máquina virtual Java.
    - renameTo(File "nuevoNombre"): renombra- delete(): borra el fichero o  el fichero. El objeto File dejará de
        referirse al archivo renombrado, ya que el String con el nombre del archivo en el objeto File no cambia
*/
//TODO Path, Paths y Files
/*
    * Files: contiene métodos estáticos para operar con ficheros y directorios.
    Path: interfaz que representa un objeto que permite localizar un archivo en el
    sistema de archivos.
    Paths: contiene métodos estáticos para generar un Path desde un String o un
    URI.



TODO PATH

    Algunos métodos útiles de Path son:
    equals().
    isAbsolute().
    startsWith().
    endsWith().
    resolve(): crea una ruta añadiendo la ruta relativa que se recibe como argumento a la que tiene el objeto Path.
    relativice(): crea una ruta relativa para localizar el elemento que se pase como
    argumento, desde la ruta que tiene el objeto Path.


TODO FILES

    Creación:
    - createFile(): crea un archivo vacío si no existía previamente.
    - createDirectory(): crea un directorio si no existía previamente.
    - createDirectories(): crea un directorio si no existía previamente y todos
    los que se encuentren en su ruta que no existan ya.
    Información:
    - exists(): comprueba si un fichero o directorio existe.
    - notExists().
    - getLastModifiedTime(): ¿cuándo se modificó por última vez?
    - getOwner(): devuelve el propietario.
    - isDirectory().
    - isExecutable().
    - isHidden().
    - isReadable().
    - isWritable().
    - size(): tamaño en bytes.
    - newDirectoryStream(): devuelve un DirectoryStream para poder iterar
    sobre los elementos del directorio.
    Operaciones sobre el fichero:
    - setAttribute(): si queremos modificar los de un archivo. Muy versátil pero
    complejo tal y como vemos en estos ejemplos. No iremos más allá de saber
    que existe. Puedes obtener más información aquí.
    - copy(): copia todos los bytes de un Stream a otro.
    - move(): mueve un archivo. Permite varias opciones sobre cómo se comportará
    la operación.
    - delete(): elimina un fichero o directorio si existe y está vacío en el segundo
    caso.
    - deleteIfExists(): comprueba si existe antes de eliminarlo por lo que lanza
    algunas excepciones menos que el anterior.
    */


//TODO Ejemplo chatGPT Path Stream
    /*
    Los streams para Path en Java proporcionan una forma elegante y funcional de realizar operaciones en colecciones de rutas de archivos
    y directorios. Estos streams se introdujeron en Java 8 como parte del paquete java.nio.file.

    Aquí hay algunos conceptos clave relacionados con los streams para Path:

    Files.walk() y Files.list():

    Files.walk(Path start, FileVisitOption... options) permite recorrer recursivamente un directorio y sus subdirectorios.
    Files.list(Path dir) devuelve un Stream<Path> que representa los elementos (archivos y subdirectorios) en el directorio especificado.
    Operaciones de Stream:

    Una vez que tienes un Stream<Path>, puedes realizar varias operaciones funcionales en él, como map, filter, forEach, collect, etc.
    Estas operaciones facilitan el procesamiento y la manipulación de las rutas de archivos de manera más concisa y expresiva.
    Ejemplo Básico:

    Aquí hay un ejemplo simple que utiliza Files.walk() para recorrer un directorio y sus subdirectorios, y luego usa operaciones de stream para filtrar y mostrar las rutas de archivos con una extensión específica:
    java
    Copy code
    import java.io.IOException;
    import java.nio.file.FileVisitOption;
    import java.nio.file.FileVisitResult;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.EnumSet;

    public class PathStreamsExample {
        public static void main(String[] args) {
            Path directorioInicial = Paths.get("ruta/del/directorio");
            String extensionBuscada = ".txt";

            try {
                Files.walk(directorioInicial)
                    .filter(path -> path.toString().endsWith(extensionBuscada))
                    .forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    En este ejemplo, Files.walk() proporciona un Stream<Path> que representa todas las rutas de archivos y directorios dentro del directorio inicial.
    Luego, se utiliza el método filter para seleccionar solo las rutas que terminan con la extensión buscada, y
    forEach se utiliza para imprimir esas rutas.

    Estos streams para Path ofrecen una forma flexible y poderosa de trabajar con rutas de archivos y directorios en Java,
    especialmente cuando necesitas realizar operaciones de forma funcional y expresiva.
    */
}
