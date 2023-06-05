/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Model.Connect;
import Model.Marcaje;
import Model.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase hecha principalmente para proporcionar utilidades generales, como listados de una clase llamando directamente a la base de datos.
 * Esta clase se utiliza para centralizar y organizar funciones y métodos de uso común en el acceso a la base de datos.
 * Proporciona métodos para realizar operaciones de consulta, inserción, actualización y eliminación en la base de datos.
 * También puede incluir métodos para realizar cálculos o manipulaciones de datos específicos relacionados con la base de datos.
 *
 * @author raulc
 */
public class CRUDMarcajes {
    Connect cn = new Connect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ResultSet rs2;
    Project p = new Project();
    Marcaje m = new Marcaje();
    private Log log;
    
    
    /**
     * Funcion para listar todos los elementos de la clase correspondiente en la bbdd
     * @return lista de la clase correspondiente
     * @throws SQLException 
     */
    public List listarMarcajes() throws SQLException{
        ArrayList<Marcaje> list = new ArrayList<Marcaje>();
        String sql = "SELECT * FROM rrhh.marcajes";
        ps= con.prepareStatement(sql);
        rs = ps.executeQuery(sql);

        while (rs.next()){
            Marcaje m = new Marcaje();
            
            String idM = rs.getString(1);
            String fecha = rs.getString(2);
            String tipo = rs.getString(3);
            String idU = rs.getString(4);
            
            m.setId_marcaje(idM);
            m.setFecha(fecha);
            m.setTipo_marcaje(tipo);
            m.setId_usuario(idU);
            
            list.add(m);
        }
        
        return list;
    }
}
