package AaD_01_Ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class Files_1_1 {
    public static void main(String[] args) throws IOException {
        String ruta = ".\\src\\main\\java\\AaD_01_Ficheros\\DirArchivos";
        String rutaAaD = ".\\src\\main\\java\\AaD_01_Ficheros";
        String rutaAbsoluta = "C:\\Users\\ariad\\OneDrive\\2º DAM\\Acceso a Datos\\AaD\\src\\main\\java\\AaD_01_Ficheros\\DirArchivos\\AriadnaLópez.txt";
        File file = new File(ruta);
        //metodoListarDirectorios(file);
        //métodoExisteFichero(rutaAaD, "01_Manejoficheros.pdf");
        //generarArchivo(ruta);
        //renombrarArchivo(rutaAbsoluta);
        //borrarArchivo(rutaAbsoluta);
        ShowInfo mostrarArchivo = new ShowInfo(rutaAbsoluta);
        System.out.println(mostrarArchivo.showInfo());
    }

    /* 1. Crea un métodolistarDirectorio()que devuelva una array con el listado de lcontenido (archivos y carpetas) del directorio actual.
    *   ¿Este método debería ser dinámico o estático? ¿por qué? */
    public static String[] metodoListarDirectorios(File file){
        if (file.exists()){
            return file.list();
        }
        return null;
    }

    /*
    * 2. Crea un métodolistarDirectorio(String directorio)que devuelva un array con el listado del contenido del directorio
    *   indicado como argumento siem-pre y cuando este sea un directorio y no un archivo. Pruébalo pasándole al
    *   menosuna ruta absoluta y una relativa.
    *   ¿Qué devolvería en caso de que la ruta que nos proporcionan no se correspon-diera con un directorio?*/
    public static String[] metodoListarDirectorios(String directorio){
        File file = new File(directorio);
        return file.list();
        //Devolverá null si es un fichero en vez de un directorio, o si ocurre un error de I/O
    }
    /* 3. Crea un métodoexisteFichero(String directorio, String fichero)quecompruebe si existe dicho fichero en el directorio indicado.*/
    public static boolean métodoExisteFichero(String directorio, String fichero){
        File file = new File(directorio + File.separatorChar + fichero);
        if (file.exists()) return true;
        else return false;
    }
    /* 4. Crea un método generarArchivo que a partir de una ruta que se le pase comoa rgumento, cree un archivo txt con nombre
    TunombreTuapellido en la ruta en la que se le ha proporcionado. Presta atención a los posibles errores que puedandarse.
    ¿Qué pasa si la ruta no existe? ¿puedes solucionarlo?*/

    public static void generarArchivo(String ruta) {
        File file = new File(ruta + File.separatorChar + "AriadnaLópez.txt");
        try {
            if (!file.createNewFile()) {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo crear el archivo");
        }
    }

/* 5. Crea un métodorenombrarArchivoque coja un archivo cuyo path absoluto sele pase como argumento y lo renombre añadiendo delanteDAM2
* .Pruébalo con el archivo creado en el ejercicio anterior.El archivo antiguo, ¿desaparece?*/

    public static void renombrarArchivo(String rutaAbsoluta){
        try {
            File archivoAntiguo = new File(rutaAbsoluta);
            String nombreFichero = archivoAntiguo.getName();
            //TODO Cuidado con el .getName, que no te devuelve la ruta, te devuevle solo el nombre del directorio!!!
            String padreRuta = archivoAntiguo.getParentFile().getAbsolutePath();
            File archivoNuevo = new File(padreRuta + File.separatorChar + "2DAM" + nombreFichero);

            System.out.println(nombreFichero);
            System.out.println(padreRuta);
            if (archivoAntiguo.renameTo(archivoNuevo)) {
                System.out.println("Archivo renombrado");
            } else {
                System.out.println("No se pudo renombrar el archivo.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*
* 6. Crea un método que se llameborrarArchivoque reciba un path absoluto yelimine el archivo indicado.
* Pruébalo con el archivo del ejercicio anterior.En la clase File hay métodos para cambiar los atributos del archivo.
* Prueba amodificar el método haciendo el archivo de solo lectura antes de eliminarlo. ¿Quésucede? ¿por qué?*/
//Lo borra, porque lo que hace setReadOnly() es permitir únicamente la lectura HASTA que se vuelvan a dar permisos de escribir o hasta que se borre,
//que es lo que he hecho.
    public static void borrarArchivo(String rutaAbsoluta){
        File file = new File(rutaAbsoluta);
        try{
            if(file.exists()){
                if(file.delete()){
                    System.out.println("Borrado!");
                }
                else {
                    System.out.println("No se ha podido borrar");
                }
            }
            else {
                throw new FileNotFoundException();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        //file.setReadOnly();
        //        file.delete();
    }

/*
* . Crea un métodoeliminarDirectorioque reciba una ruta y elimine el directorioindicado por ella.
* ¿Elimina directorios con contenido? ¿cómo se puede solucionar?
* Modifica el método para que elimine directorios que contengan solo archivos oestén vacíos e indique que no
* puede hacerlo si contienen otros directorios.
 */

    public static void eliminarDirectorioRecursivo (String ruta) {
        File file = new File(ruta);
        File [] listaDeFiles = file.listFiles();
        for (int i = 0; i < listaDeFiles.length ; i++) {
            if (listaDeFiles[i].isDirectory()){
                eliminarDirectorioRecursivo(listaDeFiles[i].getPath());
            }
            listaDeFiles[i].delete();
        }
        //Aquí necesito una funcion que se llama a sí misma. Usará un File[] y recorrerá cada elemento con .isDirectory. En caso de que sí lo sea, cogerá
        //la ruta de ese directorio y se llamará a sí misma. Si no es directorio borrará todos los archivos.
    }

    public static void eliminarDirectorioSinSubCarpetas (String ruta) {
        File file = new File(ruta);
        File[] listaDeFiles = file.listFiles();
        boolean existeDirectorio = false;
        for (int i = 0; i < listaDeFiles.length; i++) {
            if (listaDeFiles[i].isDirectory()) {
                existeDirectorio = true;
                System.err.println("No se puede borrar el directorio ya que contiene otros directorios.");
                break;
            }
        }
        if (!existeDirectorio) {
            for (int i = 0; i < listaDeFiles.length; i++) {
                listaDeFiles[i].delete();
            }
        }
    }

    /* 8. Crea una clase que herede dejava.io.Filey le añada un método que se llameshowInfo().
    * Este método devolverá unStringcon la siguiente información delfichero:
    * Nombre.
    * Ruta.
    * Ruta absoluta.
    * ¿Se puede leer?
    * ¿Se puede escribir?Tamaño.
    * ¿Es un directorio?
    * En caso afirmativo mostrará los contenidos del mismo.
    * ¿Es un fichero?
    * Cada elemento debe ir en una línea y llevar delante un texto que indique a qué se refiere.*/

}

