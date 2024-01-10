package AaD_04_ManejoConectores_SQL;

public class TeoriaTransacciones {
    /*To do lo que salga correcto: commit
        Si algo falla: rollback

    De por sí llos SGBD suelen tener autocommit pero habría que desactivarlo para tener mejor control.
        Desactivar el autocommit (Por defecto cualquier sentencia SQL que ejecutemos tiene efecto automático en la BD) conection.setAutoCommit(false);
        Realizar un commit después del éxito de todas las operaciones de la transacción.conection.commit();
        Realizar un rollback (volver al estado inicial) si se produce algún fallo. conection.rollback();
        */

    /*Se pueden hacer puntos intermedios (savepoints) y hacer rollbacks (resets) a ellos.
        Savepoint spInicial = conexion.setSavepoint();
        (código)
        Savepoint spIntermedio = conexion.setSavepoint();
        (código)
        if (....){
        conexion.rollback(spIntermedio);
        }


     */

}
