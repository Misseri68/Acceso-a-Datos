package AaD_01_Ficheros;

public class TeoriaStreams {
    //Hay Streams de BYTES (descienden de InputStream y OutputStream) y de CARÁCTERES. (Reader y Writer).
    //Todos los Streams deben cerrarse (try with resources)
    //Los Streams son bloqueantes (cuando un hilo lee o escribe en un Stream, el hilo está bloqueado hasta que se termine),
    //a diferencia de los buffers, que no son bloqueantes, son unos contenedores para una cantidad de datos.
    //Los buffers tienen una capacidad, un límite (primer elemento que no debe ser leido/escrito, y una posicion (indice)
    //Hay de entrada (informacion colocada en el buffer desde un canal, nos lo pasa) y salida (le pasamos la info al buffer -> canal

    //LECTURA Y ESCRITURA:
    //Entrada y salida con Streams de caracteres. Reader y Writer.
    //Hay dos clases para pasar flujos de bytes en flujos de caracteres: InputStreamReader (Input stream -> reader) y OutSW

    /*
    try (FileReader inputStream =
        new FileReader("C:\\AccesoADatos\\entrada.txt");
    FileWriter outputStream =
        new FileWriter("C:\\AccesoADatos\\salida.txt")) {
    int c;
    while ((c = inputStream.read()) !!= -1) {
        outputStream.write(c);
    }
    }
    catch(FileNotFoundException fnfEx) {
        System.out.println("El archivo de entrada no existe.");
    }
        catch(IOException ioEx) {
    System.out.println("El archivo de salida no se pudo abrir.");
}

/*Si no usaramos try with resources:
} finally {
if (inputStream !!= null) {
try {
inputStream.close();
} catch (IOException ex) {
System.out.println("Error cerrando el archivo de entrada.");
}
}
if (outputStream !!= null) {
try {
outputStream.close();
} catch (IOException ex) {
System.out.println("Error cerrando el archivo de salida.");
}
*/

//TODO buffers
    //Se usan para tamaños mayores (líneas) Envolvemos Reader y Writer para tener Buffers.
    //*
    // FileReader nos permite leer de archivos, y al meterlo dentro de BufferedReader usamos un buffer para hacerlo.
    //Usamos FileWriter para escribir en un archivo y al meterlo dentro de PrintWriter
    //obtenemos la capacidad de dar formato (println).*/


    //Si tiene un charset determinado: Charset charset = Charset.forName("UTF_8");
    //try (BufferedReader reader = Files.newBufferedReader(file, charset))
}
