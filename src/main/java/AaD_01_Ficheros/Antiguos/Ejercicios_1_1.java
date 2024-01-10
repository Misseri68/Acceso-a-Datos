package AaD_01_Ficheros.Antiguos;

import java.io.File;
import java.io.IOException;
public class Ejercicios_1_1 {
    public static void main(String[] args) {

        //listarDirectorio();
        //listarDirectorio2("./src/fichero.txt");
        //listarDirectorio2("C:\\Users\\ariad\\OneDrive\\2º DAM\\Programación de Servicios y Procesos\\1st term\\Ejercicios\\ejercicios repaso 2\\src");
        //generarArchivo(".\\src");
        //renombrarArchivo("C:\\Users\\ariad\\Desktop\\AaD_1.1\\src");
        //generarArchivo(".\\src");
        //borrarArchivo(".\\src\\fichero.txt");
        //borrarArchivo2(".\\src\\fichero.txt");

        /*try{
            generarDirectorioYFichero(".\\src\\Nueva carpeta");
        }
        catch (IOException e){};
        eliminarDirectorio(".\\src\\Nueva carpeta");
        //eliminarDirectorioSinSubCarpetas(".\\src\\Nueva carpeta");*/
        System.out.println(HerenciaShowInfo.showInfo(".\\src"));
    }

    /*
1. Crea un método listarDirectorio() que devuelva una array con el listado del contenido del directorio actual.
    ¿Este método debería ser dinámico o estático? ¿por qué?
*/
    public static String[] listarDirectorio(){
        String directorioActual = System.getProperty("user.dir");
        File file = new File(directorioActual);
        String[] listaDirectorios = file.list();
        return listaDirectorios;

        //Creo que el método debería ser estático ya que utiliza una estancia de la clase File, en este caso
        //la he llamado "file", que contiene el directorio directorioActual.

    }


    /*
2. Crea un método listarDirectorio(String directorio) que devuelva una array con el listado del contenido
    del directorio indicado como argumento siempre y
    cuando este sea un directorio y no un archivo. Pruébalo pasándole al menos una
    ruta absoluta y una relativa.
    ¿Qué devolvería en caso de que la ruta que nos proporcionan no se correspondiera con un directorio?
    NULL
    */

    public static String[] listarDirectorio2(String directorio){
        File file = new File(directorio);
        String[] listaDirectorios = file.list();
        return listaDirectorios;
    }


    /*
3. Crea un método generarArchivo que a partir de una ruta que se le pase como argumento, cree un archivo
    txt con nombre TunombreTuapellido en la ruta en la que se le ha proporcionado.
    Presta atención a los posibles errores que puedan darse.
    ¿Qué pasa si la ruta no existe? ¿puedes solucionarlo?
    El propio IDE no compila si no se previene el error
    */

    public static void generarArchivo(String ruta){
        File file = new File( ruta +  "\\fichero.txt");
            try{
                file.createNewFile();
            }
            catch (Exception e){
                System.err.println("Ruta no correcta");
            }

    }

/*
4. Crea un método renombrarArchivo que coja un archivo cuyo path absoluto se le pase como argumento y lo renombre añadiendo delante DAM2.
    Pruébalo con el archivo creado en el ejercicio anterior.
    El archivo antiguo, ¿desaparece?
    Si y no. Sigue teniendo la misma información, pero como al final del dia un file solo es una referencia a directorio/fichero, solo cambia el objeto
    file y no como tal el archivo.
*/

    public static void renombrarArchivo(String rutaAbsoluta){
        String nombreFichero="fichero.txt";
        String rutaConNombreFichero = rutaAbsoluta + "\\" + nombreFichero;
        File file = new File(rutaConNombreFichero);
        String nuevoNombreFichero =  rutaAbsoluta + "\\DAM2" + nombreFichero;
        File file2 = new File(nuevoNombreFichero);
        file.renameTo(file2);
    }

/*
5. Crea un método que se llame borrarArchivo que reciba un path absoluto y elimine el archivo indicado.
Pruébalo con el archivo del ejercicio anterior.
En la clase File hay métodos para cambiar los atributos del archivo. Prueba a
modificar el método haciendo el archivo de solo lectura antes de eliminarlo. ¿Qué
sucede? ¿por qué?
Lo borra, porque lo que hace setReadOnly() es permitir únicamente la lectura HASTA que se vuelvan a dar permisos de escribir o hasta que se borre,
que es lo que he hecho.
*/

    public static void borrarArchivo(String rutaAbsoluta) {
        File file = new File(rutaAbsoluta);
        file.delete();
    }

    public static void borrarArchivo2(String rutaAbsoluta) {
        File file = new File(rutaAbsoluta);
        file.setReadOnly();
        file.delete();
    }

/*
6. Crea un método eliminarDirectorio que reciba una ruta y elimine el directorio indicado por ella.
¿Elimina directorios con contenido?
    No. Sólo si está vacía.
    ¿cómo se puede solucionar?
    Al igual que el año pasado en linux, hacer un borrado recursivo. -r

    Modifica el método para que elimine directorios que contengan solo archivos o
    estén vacíos e indique que no puede hacerlo si contienen otros directorios*/

    public static void generarDirectorioYFichero (String ruta) throws IOException{
        File archivoNuevo = new File(ruta + "\\archivoNuevo.txt");
        File directorioNuevo = new File(ruta + "\\carpetaNueva\\");
        directorioNuevo.mkdir();
        archivoNuevo.createNewFile();
    }
    public static void eliminarDirectorio (String ruta) {
        File file = new File(ruta);
        File [] listaDeFiles = file.listFiles();
        for (int i = 0; i < listaDeFiles.length ; i++) {
            if (listaDeFiles[i].isDirectory()){
                eliminarDirectorio(listaDeFiles[i].getPath());
            }
                listaDeFiles[i].delete();
        }
    }
    //Aquí necesito una funcion que se llama a sí misma. Usará un File[] y recorrerá cada elemento con .isDirectory. En caso de que sí lo sea, cogerá
    //la ruta de ese directorio y se llamará a sí misma. Si no es directorio borrará todos los archivos.
    //Podría hacerlo de tal forma que intente borrarlo primero y que compruebe si se ha borrado el directorio, ya que en el caso de que no significa que no
    //hay mas dentro y sólo entonces se tendría que llamar a sí misma.

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
}




