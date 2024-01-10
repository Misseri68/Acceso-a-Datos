package AaD_01_Ficheros.Antiguos;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.*;


public class Ejercicios_1_4 {
    public static List<Character> listaVocales = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public static void main(String[] args) throws IOException {
        //ejercicio01(".\\src\\archivo1.txt");
        //aVerso(".\\src\\Lorem Ipsum.txt");
        //codifica(".\\src\\ArchivoSalida.txt");
        //codificaFiles(".\\src\\ArchivoSalida2.txt");
        //mover(".\\src\\fCopiado");
        //contar(".\\src\\Lorem ipsum.txt");
        //contarLetras(".\\src\\Lorem ipsum.txt");
        //contarPuntuacion(".\\src\\Lorem ipsum.txt");
        //cuentaLineas();
        //cuentaPalabras(".\\src\\Lorem ipsum.txt");
        //cuentaVocales(".\\src\\Lorem ipsum.txt");
        //cuentaVocalesTildes(".\\src\\Lorem ipsum.txt");
        //frecuenciaLetras(".\\src\\Lorem ipsum.txt");
        //objetosEnCSV(".\\src\\Archivo.csv");
        leerCSV("src/Archivo.csv");
    }

    /*
    * 1. Crea una clase ArchivoTXT cuyo constructor reciba un String y lo guarde como
    Path. Debe comprobar si el String es correcto, hace referencia a un archivo (no
directorio) y este existe.
*/
    public void ejercicio01(String ruta) {
        try {
            ArchivoTXT archivonuevo = new ArchivoTXT(ruta);
            System.out.println("La ruta es: " + archivonuevo.getNombrePath()); //Esto es para ver que funciona correctamente

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 2. Añade un método aVerso que lea el contenido del archivo y lo devuelva introduciendo un salto de línea después de cada punto.
     */

    public static String aVerso(String ruta) throws IOException {
        Path path = Paths.get(ruta);
        FileReader fReader = new FileReader(path.toFile());
        BufferedReader bReader = new BufferedReader(fReader);
        StringBuffer sBuffer = new StringBuffer();
        String sReturn;
        int c;
        while ((c = bReader.read()) != -1) {
            sBuffer = sBuffer.append((char) c);
            if (c == '.') {
                sBuffer = sBuffer.append("\n");
            }
        }
        sReturn = String.valueOf(sBuffer);
        return sReturn;
    }

    /*
 3. Añade otro método codifica que reciba un String indicando la ruta de otro fichero (puede existir o no). Debe leer el contenido del archivo
    original, eliminar todas las vocales y escribir el resultado en el archivo recibido como argumento.
        Usa únicamente FileReader y FileWriter. Añade otros dos métodos (variaciones de este):
        Un método codificaBuffer usando buffers.
        Un método codificaFiles obteniendo las clases con buffer a partir de la clase Files.*/


    public static void codifica(String rutaOtro) {
        Path path1 = Paths.get(".\\src\\Lorem ipsum.txt");
        Path path2 = Paths.get(rutaOtro);
        try (FileReader fReader = new FileReader(path1.toFile());
             FileWriter fWriter = new FileWriter(path2.toFile());) {
            int c;
            while ((c = fReader.read()) != -1) {
                if (!listaVocales.contains((char) c))
                    fWriter.write((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void codificaBuffer(String rutaOtro) {
        Path path1 = Paths.get(".\\src\\Lorem ipsum.txt");
        Path path2 = Paths.get(rutaOtro);
        try (FileReader fReader = new FileReader(path1.toFile());
             BufferedReader bReader = new BufferedReader(fReader);
             BufferedWriter bWriter = new BufferedWriter(new FileWriter(path2.toFile()));
        ) {
            int c;
            while ((c = bReader.read()) != -1) {
                if (!listaVocales.contains((char) c))
                    bWriter.write((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void codificaFiles(String rutaOtro) throws IOException {
        Path path1 = Paths.get(".\\src\\Lorem ipsum.txt");
        Path path2 = Paths.get(rutaOtro);
        try {
            List<String> listaLineas = Files.readAllLines(path1);
            for (int i = 0; i < listaLineas.size(); i++) {
                String lineaIndividual = listaLineas.get(i);

                String lineaIndividualCambiada = lineaIndividual.replaceAll("[AEIOUaeiou]", "");
                listaLineas.set(i, lineaIndividualCambiada);
            }
            Files.write(path2, listaLineas, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*4. Incorpora un método mover que reciba otra ruta y mueva el archivo a ella. Si el directorio en el que se encontraba queda vacío, debe eliminarse también*/

    public static void mover(String otraRuta) {
        Path otroPath = Paths.get(otraRuta);
        Path rutaOriginal = Paths.get(".\\src\\f1\\fACopiar.txt");
        try {
            if (!Files.exists(rutaOriginal)) {
                throw new FileNotFoundException();
            }
            Files.move(rutaOriginal, otroPath, REPLACE_EXISTING);
            if (rutaOriginal.getParent().toFile().list().length == 0) {
                rutaOriginal.getParent().toFile().delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Añade tres métodos:
Un método contar que cuente el número total de caracteres del archivo.
Un método contarLetras que cuente el número total de letras del fichero.
Un método contarPuntuación que cuente el número total de signos de puntuación del fichero*/
    public static void contar(String ruta) {
        Path pRuta = Paths.get(ruta);
        try (
                FileReader fReader = new FileReader(pRuta.toFile());
                BufferedReader bReader = new BufferedReader(fReader);
        ) {
            int contador = 0;
            while ((bReader.read()) != -1) {
                contador++;
            }
            System.out.println(contador);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void contarLetras(String ruta) {
        Path pRuta = Paths.get(ruta);
        try (
                FileReader fReader = new FileReader(pRuta.toFile());
                BufferedReader bReader = new BufferedReader(fReader);
        ) {
            int c;
            int contador = 0;
            while ((c = bReader.read()) != -1) {
                if (Character.isLetter(c)) {
                    contador++;
                }
            }
            System.out.println(contador);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void contarPuntuacion(String ruta) {
        Path pRuta = Paths.get(ruta);
        try (
                FileReader fReader = new FileReader(pRuta.toFile());
                BufferedReader bReader = new BufferedReader(fReader);
        ) {
            int c;
            int contador = 0;
            while ((c = bReader.read()) != -1) {
                if (((char) c) == '.' || ((char) c) == ',') {
                    contador++;
                }
            }
            System.out.println(contador);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 6. Ahora debes incluir un método cuentaLineas que cuente cuántas frases tiene (hasta cada punto), ayudándose con el método aVerso.
     */
    public static void cuentaLineas() throws IOException {
        String aVersoStr = aVerso(".\\src\\Lorem Ipsum.txt");
        StringReader sReader = new StringReader(aVersoStr);
        BufferedReader bReader = new BufferedReader(sReader);
        Stream<String> lineas = bReader.lines();
        System.out.println(lineas.count());
    }

    /*
     * 7. Incorpora otro método cuentaPalabras que cuente todas las palabras del fichero.
     */
    public static void cuentaPalabras(String ruta) {
        try {
            String str = Files.readString(Paths.get(ruta));
            int nPalabras = str.split("\\s").length;

            System.out.println(nPalabras);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    * 8. Implementa un método cuentaVocales que escriba el número de vocales de
cada palabra en un fichero numVocales.txt en el mismo directorio en el que
se encuentra el fichero original. Cada número debe ir seguido de un espacio.
Se deben tener en cuenta tanto mayúsculas como minúsculas pero se contarán
juntas. Es decir una a y una A incrementarán el mismo contador.
*/
    public static void cuentaVocales(String ruta) {
        Path pathEntrada = Paths.get(ruta);
        Path pathSalida = Paths.get(".\\src\\numVocales.txt");
        int contadorVocales = 0;
        StringBuilder sBuilder = new StringBuilder();
        try (
                FileReader fReader = new FileReader(pathEntrada.toFile());
                BufferedReader bReader = new BufferedReader(fReader);
                FileWriter fWriter = new FileWriter(pathSalida.toFile());
        ) {
            String str = Files.readString(pathEntrada);
            String[] palabras = str.split("\\s");
            for (int i = 0; i < palabras.length; i++) {
                char[] c = palabras[i].toCharArray();
                for (int j = 0; j < c.length; j++) {
                    if (listaVocales.contains(c[j])) {
                        contadorVocales++;
                    }
                }
                sBuilder.append(contadorVocales).append(" ");
                contadorVocales = 0;
            }
            fWriter.write(sBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Modifica el ejercicio anterior para que tenga en cuenta las vocales con tilde y la u con diéresis*/

    public static void cuentaVocalesTildes(String ruta) {
        Path pathEntrada = Paths.get(ruta);
        Path pathSalida = Paths.get(".\\src\\numVocales2.txt");
        int contadorVocales = 0;
        StringBuilder sBuilder = new StringBuilder();
        List<Character> nuevaListaVocales = new ArrayList<>(Arrays.asList
                ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U', 'á', 'Á', 'é', 'É', 'í', 'Í', 'ó', 'Ó', 'ú', 'Ú', 'Ü', 'ü'));

        try (
                FileReader fReader = new FileReader(pathEntrada.toFile());
                BufferedReader bReader = new BufferedReader(fReader);
                FileWriter fWriter = new FileWriter(pathSalida.toFile());
        ) {
            String str = Files.readString(pathEntrada);
            String[] palabras = str.split("\\s");
            for (int i = 0; i < palabras.length; i++) {
                char[] c = palabras[i].toCharArray();
                for (int j = 0; j < c.length; j++) {
                    if (nuevaListaVocales.contains(c[j])) {
                        contadorVocales++;
                    }
                }
                sBuilder.append(contadorVocales).append(" ");
                contadorVocales = 0;
            }
            fWriter.write(sBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 10. Implementa el método frecuenciaLetras que muestre la frecuencia de las letras (a-z incluidas mayúsculas) del fichero.
     */
    public static void frecuenciaLetras(String ruta) {
        Map<Character, Integer> frecuenciaChar = new HashMap<>();
        Path path = Paths.get(ruta);
        try (
                BufferedReader bReader = new BufferedReader(new FileReader(path.toFile()))
        ) {
            int c;
            while ((c = bReader.read()) != -1) {
                char caracterActual = (char) c;
                if (frecuenciaChar.containsKey((caracterActual))) {
                    frecuenciaChar.put(caracterActual, frecuenciaChar.get(caracterActual) + 1);
                } else {
                    frecuenciaChar.put(caracterActual, 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        frecuenciaChar.forEach((character, integer) -> System.out.println("Letra: " + character + " Frecuencia: " + integer));
    }

    /*
    11. Crea una clase de algo que te llame la atención. La clase debe incluir al menos
    un nombre (String), otro String, un entero, un número de coma flotante, un
    booleano. Por ejemplo los datos de una persona o de algún personaje de videojuegos, etc.

    Crea varios objetos de esa clase y escríbelos en un archivo.
    Los datos se guardarán en un archivo .csv separados por punto y coma.
    Prueba a abrir el fichero con una hoja de cálculo y comprobar que se cargue cada
    elemento en una fila y cada campo en una celda de la misma.
     */
    public static void objetosEnCSV(String ruta) {
        Jugador jugador1 = new Jugador("Sergio", "Dios", 999, (999.99f), true);
        Jugador jugador2 = new Jugador("Shikka", "Tiefling", 5, (44.0f), false);
        Jugador jugador3 = new Jugador("Martlock", "Ogro", 5, (33.0f), true);
        try (
                FileWriter fWriter = new FileWriter(ruta)
        ) {
            fWriter.write("Nombre;Raza;Nivel;Vida;estaVivo\n");
            fWriter.write(jugador1.toCsvString() + "\n");
            fWriter.write(jugador2.toCsvString() + "\n");
            fWriter.write(jugador3.toCsvString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
    *     12. Crea un método que permita escribir un objeto de la clase del ejercicio 1.4.11 al
        final del archivo que ya tienes creado.
    */
    public void añadirObjetos(String ruta) {
        try (
                FileWriter fWriter = new FileWriter(ruta)
        ) {
            fWriter.write(new Jugador("Adri", "Halfling", 1, 10.0f, false).toCsvString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    /*
    * 13. Lee los objetos del archivo del ejercicio 1.4.12 y muéstralos por pantalla de uno
    en uno y con un texto delante de cada campo que indique qué es. Puedes sobrescribir el método toString() para ayudarte. El archivo se debe abrir en modo
    lectura.
    Prueba a escribir un nuevo elemento, ¿qué sucede?*/

    public static void leerCSV(String ruta){
        try(
                BufferedReader br = new BufferedReader(new FileReader(ruta));
        ){
            String linea = br.readLine();
            while((linea = br.readLine()) != null) {
                if(linea.contains("Raza")) continue;
                String[] campo = linea.split(";");
                String nombre = campo[0];
                String raza = campo[1];
                int nivel = Integer.parseInt(campo[2]);
                float vida = Float.parseFloat(campo[3]);
                boolean estaVivo = Boolean.parseBoolean(campo[4]);

                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "Nombre: " + nombre + "\n" +
                "Raza: " + raza + "\n" +
                "Nivel: " + nivel  + "\n" +
                "Vida: " + vida   + "\n" +
                "Está vivo? " + estaVivo
                );
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}


    class ArchivoTXT {
        String nombrePath = "";
        Path rutaP;

        public ArchivoTXT() {
        }

        public ArchivoTXT(String nombrePath) throws IOException {
            setNombrePath(nombrePath);
            rutaP = Paths.get(nombrePath);
        }

        public void setNombrePath(String nombrePath) throws IOException {
            if (nombrePath == null) {
                throw new NullPointerException("La ruta no puede ser null.");
            } else if (!Files.exists(Paths.get(nombrePath))) {
                throw new IOException("La ruta especificada no existe");
            } else if (Files.isDirectory(Paths.get(nombrePath))) {
                throw new IOException("La ruta especificada no es de un archivo, es un directorio.");
            } else {
                this.nombrePath = nombrePath;
            }
        }

        public String getNombrePath() {
            return nombrePath;
        }

        public Path getRutaP() {
            return rutaP;
        }
}
