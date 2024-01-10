package AaD_02_XML;

import org.w3c.dom.*;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class TeoriaXML {
    public static void main(String[] args) {

        //Se debe parsear el documento.
        //Creamos el parser:
        File rutaXML = new File(".\\carrera.xml");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc;
        try{
            builder = builderFactory.newDocumentBuilder();
        }catch(ParserConfigurationException e){
            e.printStackTrace();
        }

        //Parsear el documento:

        try{
            doc = builder.parse(rutaXML);
            //normalizamos el documento
            doc.getDocumentElement().normalize();
            //Operaciones lectura:
            Element raiz = doc.getDocumentElement();
            NodeList hijos = raiz.getChildNodes();
            for (int i = 0; i < hijos.getLength() ; i++) {
                Node hijo = hijos.item(i);
                if(hijo.getNodeType() == Node.ELEMENT_NODE){
                    Element elemHijo = (Element) hijo;
                    System.out.println("Hijo " + ": " + elemHijo.getTextContent().trim());
                }
            }
            // leer por nombre de etiqueta.
            NodeList vehiculos = doc.getElementsByTagName("vehiculo");
            for (int i = 0; i < vehiculos.getLength() ; i++) {
                Node vehiculo = vehiculos.item(i);
                if (vehiculo.getNodeType() == Node.ELEMENT_NODE){
                    Element elemento = (Element) vehiculo;
                    System.out.println("Nombre: "+ getNodo("nombre", elemento));
                    System.out.println("\tNúmero: "+ getNodo("numero", elemento));
                    System.out.println("\tTiempo: "+ getNodo("tiempo", elemento));
                }
            }

            // recorrer con iterador
            DocumentTraversal trav = (DocumentTraversal) doc;
            Node elemRaiz = doc.getDocumentElement();
            NodeFilter filtrar = null; // no filtrar: coger todos los elmentos.
            boolean expandirEntidades = true; // expandir entidades XML. (cambiar caracteres especiales por los que representarían)
            NodeIterator it = trav.createNodeIterator(elemRaiz, NodeFilter.SHOW_ELEMENT, filtrar, expandirEntidades);
            for (Node node = it.nextNode(); node != null; node = it.nextNode()) {
                String name = node.getNodeName();
                System.out.println(name);
            }

        } catch(SAXException | IOException e){
            e.printStackTrace();
        }

    }
    private static String getNodo(String etiqueta, Element elem) {
        NodeList lista =
                elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node valornodo = (Node) lista.item(0);
        return valornodo.getNodeValue();
    }


    //UN NODO es la superclase de todos los tipos de nodos que hay (elementos, atributos...)







    //TODO: CREACION DE FICHERO XML
    /*
  Creamos builder cmo siempre
  DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = builderFactory.newDocumentBuilder();
         // obtenemos una implementación del DOM
    DOMImplementation implementation = builder.getDOMImplementation();

    A continuación podemos crear el documento en sí. Aprovechamos para guardar el elemento raíz porque lo usaremos bastante.
        // Creamos el documento 	/((((createDocument(String namespaceURI, String qualifiedName, DocumentType doctype)))))
    Document documento = implementation.createDocument(null, "jugadoresNFL", null);
         // establecemos la version de XML
    documento.setXmlVersion("1.0");
        // Obtenemos el elemento raíz
    Element raiz = documento.getDocumentElement();
   */

    /*AÑADIR ELEMENTOS:
        Teniendo un jugadorNFL estos atributos:
        private String nombre;
        private short numero;
        private boolean lesionado;
        private final GregorianCalendar fechaContrato
    *

    // creamos nodos de jugadores y los añadimos al elemento raíz.
for(JugadorNFL jugador : jugadores) {
Element jugXML = crearJugadorNFL(jugador, documento);
raiz.appendChild(jugXML);
}

(CONSULTAR PDF QUE ESTÁ MEJOR EXPLICADO)*/
/*
*
*
* Escribir el fichero:
* Transformer.
*
* */
}
