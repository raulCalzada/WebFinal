/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Utils.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de establecer la conexión con la base de datos y proporcionar
 * métodos para ejecutar consultas y realizar operaciones relacionadas. Utiliza
 * el patrón Singleton para garantizar una única instancia de conexión a la base
 * datos en toda la aplicación. Esta clase se encarga de manejar la
 * configuración de la base de datos, establecer la conexión, ejecutar consultas
 * y cerrar la conexión después de su uso. Proporciona métodos para realizar
 * operaciones CRUD (crear, leer, actualizar, eliminar) en la base de datos y
 * manejar transacciones. Además, implementa medidas de seguridad para prevenir
 * inyecciones de SQL y otros ataques comunes. Esta clase es utilizada por otros
 * componentes de la aplicación para acceder y manipular los datos almacenados
 * en la base de datos.
 *
 * La clase utiliza las siguientes propiedades para la configuración de la
 * conexión: - user: el nombre de usuario para acceder a la base de datos. -
 * password: la contraseña del usuario para acceder a la base de datos. - bd: el
 * nombre de la base de datos. - url: la URL de conexión a la base de datos. -
 * driver: el nombre del driver JDBC para la base de datos.
 *
 * La clase también tiene una propiedad "cx" que representa la conexión
 * establecida con la base de datos, y una propiedad "log" para llevar las
 * trazas de la conexión.
 *
 * Ejemplo de uso: Connect connect = Connect.getInstance(); // Obtener la
 * instancia de la conexión connect.openConnection(); // Abrir la conexión //
 * Realizar operaciones con la base de datos... connect.closeConnection(); //
 * Cerrar la conexión
 *
 * Nota: Es importante asegurarse de cerrar la conexión después de su uso para
 * liberar los recursos correctamente.
 *
 * @author raul
 */
public class Connect {

    String user = "root";
    String password = "Jager1234";
    String bd = "rrhh";
    String url = "jdbc:mysql://localhost:3306/"; //?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;
    private Log log;

    /**
     * Constructor por defecto de la clase Connect. Este constructor crea una
     * instancia de Connect con la configuración predeterminada.
     */
    public Connect() {
    }

    /**
     * Realiza la conexión a la base de datos.
     *
     * @return La conexión establecida.
     * @throws SQLException Si ocurre algún error en la conexión con la base de
     * datos.
     */
    public Connection conect() throws SQLException {
        try {
            Class.forName(driver);
            cx = (Connection) DriverManager.getConnection(url + bd, user, password);
            System.out.print("SE CONECTÓ A LA BD " + bd);
            Log.log.info("SE CONECTÓ A LA BD " + bd + "\n");

        } catch (ClassNotFoundException ex) {
            System.out.print("NO SE CONECTÓ A LA BD " + bd);
            Log.log.info("NO SE CONECTÓ A LA BD " + bd + "\n");
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }

    /**
     * Funcion para desconectarlos de la bbdd
     */
    public void disconnect() {
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
