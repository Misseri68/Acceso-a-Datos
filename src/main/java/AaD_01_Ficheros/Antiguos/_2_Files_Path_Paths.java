package AaD_01_Ficheros.Antiguos;

public class _2_Files_Path_Paths {
    /*

Files: contiene métodos estáticos para operar con ficheros y directorios.
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
* */
}
