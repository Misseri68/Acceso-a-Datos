package AaD_01_Ficheros.Antiguos;

public class Ejercicios_1_6 {
    public static void main(String[] args) {


    }

    /*

    JAVA BEAN:
2. Completa la clase Pokemon para que sea un Java Bean que permita almacenar
    los siguientes atributos:
    Nombre.
    Nivel.
    Vida.
    Ataque.
    Defensa.
    Ataque Especial.
    Defensa Especial.
    velocidad.
    Si (como yo) no tienes ni idea de Pokemons, aquí tienes una base de datos que
    Debes controlar los valores de los parámetros, gestionar posibles errores, etc.
    La clase Pokemon debe implementar Externalizable*/
}

/*
* 1. Piensa y describe brevemente cómo harías cada una de las operaciones CRUD
sobre registros en las siguientes organizaciones de ficheros:
* (Create, Read, Update, Delete)
a) Secuencial.
* C - escribir el objeto en el archivo de guardado
* R - recorrer lista con un forEach y volcarlos en un ArrayList que nos permita ver todos los objetos
* U - seleccionar el objeto del arraylist usando su clave primaria recorriendo el array hasta que coincida la PK.
* D - seleccionar el objeto y borrarlo del archivo y del arraylist.
b) Secuencial encadenada.
*   Igual que la secuencial, solo que al borrar un registro no se requiere reorganizar (ya que el puntero de cada registro solo apunta al siguente y solo habria que modificar uno)*
c) Directa o aleatoria.
* C - se puede añadir en cualquier posicion (no como en secuencial que es al final)
* R - no hace falta recorrer el archivo, solo das la clave y te lelva a esa posicion
* U - igual que READ.
* D - al igual ^.
d) Secuencial indexada.
* C - Se crea el registro al final del archivo y se le añade al indice clave y posicion.
* R - Se busca en el indice y te lleva a la posicion con la clave.
* U - Se ubica con el indice y la clave y se actualiza.
* D - Se marca el archivo deseado, se elimina, y se reorganiza.
*
* */

