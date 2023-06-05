/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Model.Connect;
import Model.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase hecha principalmente para proporcionar utilidades generales, como
 * listados de una clase llamando directamente a la base de datos. Esta clase se
 * utiliza para centralizar y organizar funciones y métodos de uso común en el
 * acceso a la base de datos. Proporciona métodos para realizar operaciones de
 * consulta, inserción, actualización y eliminación en la base de datos. También
 * puede incluir métodos para realizar cálculos o manipulaciones de datos
 * específicos relacionados con la base de datos.
 *
 * @author raulc
 */
public class CRUDEmpresas {

    Connect cn = new Connect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ResultSet rs2;
    Empresa e = new Empresa();
    private Log log;

    /**
     * Clase para realizar operaciones CRUD (crear, leer, actualizar, eliminar)
     * relacionadas con empresas. Esta clase proporciona métodos para
     * interactuar con la base de datos y realizar las operaciones mencionadas.
     * Utiliza un constructor por defecto para crear una instancia de la clase.
     *
     * TODO: Agregar una descripción más detallada de la funcionalidad del
     * constructor por defecto si es necesario.
     */
    public CRUDEmpresas() {
    }

    /**
     * Funcion para listar todos los elementos de la clase correspondiente en la
     * bbdd
     *
     * @return lista de la clase correspondiente
     * @throws SQLException Si ocurre algún error en la conexión con la base de
     * datos.
     */
    public List listar() throws SQLException {
        ArrayList<Empresa> list = new ArrayList<>();
        String sql = "SELECT * FROM rrhh.empresa";

        con = cn.conect();
        ps = con.prepareStatement(sql);

        rs = ps.executeQuery(sql);
        while (rs.next()) {
            Empresa e = new Empresa();
            e.setId_empresa(rs.getString(1));
            e.setNombre_empresa(rs.getString(2));
            list.add(e);
        }

        return list;
    }

    /**
     * Obtiene el elemento completo en base a su ID. Esta función recibe un ID y
     * busca el objeto correspondiente en la base de datos.
     *
     * @param id el ID del elemento a buscar
     * @return el objeto correspondiente al ID proporcionado
     * @throws SQLException excepción generada por la base de datos
     */
    public Empresa list(String id) throws SQLException {
        String sql = "SELECT * FROM rrhh.empresa where id_empresa=" + id;

        con = cn.conect();
        ps = con.prepareStatement(sql);

        rs = ps.executeQuery(sql);

        while (rs.next()) {
            e.setId_empresa(rs.getString(1));
            e.setNombre_empresa(rs.getString(2));
        }
        return e;
    }

    /**
     * Para editar la clase seleccionada
     *
     * @param e es la empresa editada
     * @return un booleano para confirmar que se ha realizado
     */
    public boolean edit(Empresa e) {
        try {
            String sql = "update rrhh.empresa set nombre_empresa='" + e.getNombre_empresa() + "' where id_empresa='" + e.getId_empresa() + "'";
            con = cn.conect();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException i) {
        }

        return false;

    }

    /**
     * Clase para crear una empresa en la bbdd como para crear una empresa solo
     * se requiere por parámetro un string, no le paso mas
     *
     * @param e la empresa a crear completa
     * @return boolean para validar la creacion
     */
    //como para crear una empresa solo se requiere por parámetro un string, no le paso mas
    public boolean create(String e) {
        try {
            String sql = "INSERT INTO rrhh.empresa (id_empresa, nombre_empresa) SELECT COALESCE(MAX(id_empresa), 0) + 1, '" + e + "' FROM rrhh.empresa";
            con = cn.conect();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException i) {
        }
        return false;

    }

}
