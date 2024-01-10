package AaD_02_XML.xmlfiles;

/*
* 3. Crea una clase Participante que permita cargar los datos de un participante del
        archivo carrera.xml.
        Sobrescribe el método toString para que se muestren los datos adecuadamente. Por ejemplo:
        *Participante:
        Código: 7-FFD-AMG
        Conductores:
        Sargento Blast
        Soldado Meekly
        Vehiculo:
        Nombre: El Súper Chatarra Special
        Número: 6
        Tiempo: 4H6M11S
        En caso de que solo haya un conductor, debe poner Conductor en vez de Conductores.
        Recuerda que puedes usar \n y \t para crear saltos de línea y tabulaciones en el
        String.
        *
        */

import java.util.ArrayList;
import java.util.List;

public class Participante {

    private String codigo = "";
    private List<String> conductores = new ArrayList<>();
    private Vehiculo vehiculo;

    public Participante(String codigo, List<String> conductores, Vehiculo vehiculo) {
        this.codigo = codigo;
        this.conductores = conductores;
        this.vehiculo = vehiculo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<String> getConductores() {
        return conductores;
    }

    public void setConductores(List<String> conductores) {
        this.conductores = conductores;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append("Participante: \n");
        stb.append("\tCódigo: ").append(codigo).append("\n");
        if (conductores.size()==1){
            stb.append("\t Conductor: ").append("\n");
        }
        else if(conductores.size()>1) stb.append("\t Conductores: ").append("\n");
        for (int i = 0; i < conductores.size(); i++) {
            stb.append("\t\t").append(conductores.get(i)).append("\n");
        }
        stb.append("\t Vehiculo").append("\n");
        stb.append("\t\tNombre: ").append(vehiculo.getNombre()).append("\n");
        stb.append("\t\tNumero: ").append(vehiculo.getNumero()).append("\n");
        stb.append("\t\tTiempo: ").append(vehiculo.getTiempo()).append("\n");
        return stb.toString();
    }
}

