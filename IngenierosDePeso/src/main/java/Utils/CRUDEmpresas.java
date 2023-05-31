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
    
    public List listar() throws SQLException{
        ArrayList<Empresa> list = new ArrayList<>();
        String sql = "SELECT * FROM rrhh.empresa";

        con = cn.conect();
        ps= con.prepareStatement(sql);
        
        rs = ps.executeQuery(sql);
        while (rs.next()){
            Empresa e = new Empresa();
            e.setId_empresa(rs.getString(1));
            e.setNombre_empresa(rs.getString(2));
            list.add(e);
        }
        
        return list;
    }
    
    public Empresa list(String id) throws SQLException{
        String sql = "SELECT * FROM rrhh.empresa where id_empresa="+id;
        
        con = cn.conect();
        ps= con.prepareStatement(sql);
        
        rs = ps.executeQuery(sql);
        
        while (rs.next()){  
            e.setId_empresa(rs.getString(1));
            e.setNombre_empresa(rs.getString(2));
        }
        return e;
    }
    
    
    public boolean edit(Empresa e){
        try{
            String sql = "update rrhh.empresa set nombre_empresa='" +e.getNombre_empresa()+"' where id_empresa='"+e.getId_empresa()+"'";
            con = cn.conect();
            ps= con.prepareStatement(sql);
            ps.executeUpdate(); 
            return true;
        }catch (SQLException i){
        }
        
        return false;
        
    }
    //como para crear una empresa solo se requiere por parámetro un string, no le paso mas
    public boolean create(String e){
        try{
            String sql = "INSERT INTO rrhh.empresa (id_empresa, nombre_empresa) SELECT COALESCE(MAX(id_empresa), 0) + 1, '"+e+"' FROM rrhh.empresa";
            con = cn.conect();
            ps= con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        }catch (SQLException i){
        }
        return false;
        
    }
    
}
