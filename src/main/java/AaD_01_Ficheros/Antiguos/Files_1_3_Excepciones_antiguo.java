package AaD_01_Ficheros.Antiguos;

public class Files_1_3_Excepciones_antiguo {
    public static void main(String[] args) {
        int var1 = 0;
        int var2 = 2;
    try{
        System.out.println(var1/var1);
    }catch(ArithmeticException e){
        e.printStackTrace();
    }
        //ej02, "10" no son lanzables  e "int e" lo declara dentro del catch, no compilará.
        //ej03,  "Prueba()" será capturado y lanzará el sout de catch y el sout de finally.
        //ej04, como "Derivada" extiende de "Base" y está en un catch después de "Base", no será capturada ya que "Base engloba a todas sus herencias
            //y las capturará a todas, por lo que "Derivada" no se podrá capturar y no compilará.
        //ej05 Será apturado por el catch y luego pasará por el finally y saltará el sout de b.
        //ej06 Dará 5, ya que el penultimo

    }
}
