package AaD_02_XML;

import AaD_02_XML.xmlfiles.Participante;
import AaD_02_XML.xmlfiles.Vehiculo;
import jakarta.servlet.http.Part;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XML_2_1 {
    public static void main(String[] args) {
        String rutaXML = new String(".\\src\\main\\java\\AaD_02_XML\\xmlfiles\\carrera.xml");
        Document doc = normalizarDocumento(rutaXML);
        //leerObjetosPilotos(doc);
        //leerObjetosPilotos(doc).forEach(System.out::println);
        //leerPilotos(doc).forEach(System.out::println);
        System.out.println(buscarPorConductor("Brutus", doc));

    }

    /*
    * Ejercicios 2.1
    1. Crea un programa que lea todos los nombres de los pilotos del archivo carrera.xml,
    les asigne un número de orden dentro de su vehículo y muestre por pantalla el
    número del vehículo, el número asignado y el nombre:
    P.ej: 6-2-Soldado Meekly*/
    public static List<String> leerPilotos(Document doc){
        List<String> pilotos = new ArrayList<>();
        NodeList participantes = doc.getElementsByTagName("participante");
        for (int i = 0; i < participantes.getLength(); i++) {
            Node participanteNode = participantes.item(i);

            if (participanteNode.getNodeType() == Node.ELEMENT_NODE){
                Element participanteElement = (Element) participanteNode;
                NodeList conductores = participanteElement.getElementsByTagName("conductor");

                //Aqui cogeré la lista con el item 0 (ya que solo hay un vehiculo por participante) y lo casteo a Elemento en la misma línea (speed).
                Element vehiculo = (Element) participanteElement.getElementsByTagName("vehiculo").item(0);
                //Como sólo me interesa el número y cada coche sólo tiene un número::
                Element numeroVehiculoElement = (Element) vehiculo.getElementsByTagName("numero").item(0);
                String numeroVehiculo = numeroVehiculoElement.getTextContent();

                //Aquí cojo los conductores y luego cada nombre de cada conductor
                for (int j = 0; j < conductores.getLength(); j++) {

                    Node conductorNode = conductores.item(j);
                    if (participanteNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element conductorElement = (Element) conductorNode;
                        NodeList nombresConductor = conductorElement.getElementsByTagName("nombre");

                        for (int k = 0; k < nombresConductor.getLength(); k++) {
                            Node nombreConductorNode = nombresConductor.item(k);
                            if (nombreConductorNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element nombreConductorElement = (Element) nombreConductorNode;
                                String nombreConductor = nombreConductorElement.getTextContent();
                                pilotos.add("Número del vehículo: " + numeroVehiculo +
                                        " número de conductor: " + (k+1) +
                                        " nombre del conductor: " +  nombreConductor);
                            }
                        }
                    }
                }
            }
        }
        return pilotos;
    }

/* 2. Crea un método estático que reciba la ruta de un fichero XML y devuelva el elemento Document normalizado.
Modifica el ejercicio 2.1.1 para que utilice el método que acabas de crear.*/
    public static Document normalizarDocumento(String rutaXML){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            try {
                dBuilder = dbf.newDocumentBuilder();
            }catch(ParserConfigurationException e) {
                e.printStackTrace();
            }
            Document doc = dBuilder.parse(rutaXML);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

/* 3- Crea una clase participante*/

    public static List<Participante> leerObjetosPilotos(Document doc){
        List<Participante> participantes = new ArrayList<>();

        NodeList participantesNode = doc.getElementsByTagName("participante");
        for (int i = 0; i < participantesNode.getLength() ; i++) {
            Node nodoParticipante = participantesNode.item(i);
            List<String> nombresConductor = new ArrayList<>();
            if (nodoParticipante.getNodeType() == Node.ELEMENT_NODE){
               Element elementoParticipante = (Element) nodoParticipante;
               String codigo = elementoParticipante.getElementsByTagName("codigo").item(0).getTextContent();
               Element vehiculo = (Element) elementoParticipante.getElementsByTagName("vehiculo").item(0);
               Element elementNombreVehiculo = (Element) vehiculo.getElementsByTagName("nombre").item(0);
               String nombreVehiculo = elementNombreVehiculo.getTextContent();
               Element elementNumVehiculo = (Element) vehiculo.getElementsByTagName("numero").item(0);
               int numeroVehiculo = Integer.parseInt(elementNumVehiculo.getTextContent());
               Element elementTiempoVehiculo = (Element) vehiculo.getElementsByTagName("tiempo").item(0);
               String tiempoVehiculo = elementTiempoVehiculo.getTextContent();
               Element elementConductor = (Element) elementoParticipante.getElementsByTagName("conductor").item(0);
               NodeList nodeNombreConductores  = elementConductor.getElementsByTagName("nombre");
               for (int j = 0; j < nodeNombreConductores.getLength(); j++) {
                   Node conductorNodo = nodeNombreConductores.item(j);
                   if (conductorNodo.getNodeType() == Node.ELEMENT_NODE) {
                       nombresConductor.add(conductorNodo.getTextContent());
                   }
               }
               Vehiculo vehiculoObjeto = new Vehiculo(nombreVehiculo, numeroVehiculo, tiempoVehiculo);
               Participante participante = new Participante(codigo, nombresConductor, vehiculoObjeto);
               participantes.add(participante);
           }
        }
        return participantes;
    }

    /*4. Crea un método que permita buscar en el archivo carrera.xml elementos por el
nombre de uno de pilotos lo devuelva en un objeto de la clase Participante.
Pruébalo desde el método main de tu proyecto.
    * */
    public static Participante buscarPorConductor(String nombre, Document doc){
        List<Participante> participantes = leerObjetosPilotos(doc);
        for (Participante participante : participantes) {
            List<String> nombresConductores= participante.getConductores();
            for (int i = 0; i < nombresConductores.size(); i++) {
                if (nombresConductores.get(i).equals(nombre)){
                    return participante;
                }
            }
        }
        return null;
    }









}
