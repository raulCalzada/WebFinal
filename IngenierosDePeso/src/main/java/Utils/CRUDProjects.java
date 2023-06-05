/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Model.Connect;
import Model.Empresa;
import Model.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase hecha principalmente para proporcionar utilidades generales, como listados de una clase llamando directamente a la base de datos.
 * Esta clase se utiliza para centralizar y organizar funciones y métodos de uso común en el acceso a la base de datos.
 * Proporciona métodos para realizar operaciones de consulta, inserción, actualización y eliminación en la base de datos.
 * También puede incluir métodos para realizar cálculos o manipulaciones de datos específicos relacionados con la base de datos.
 *
 * @author raulc
 */
public class CRUDProjects{
    Connect cn = new Connect();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ResultSet rs2;
    Project p = new Project();
    private Log log;
    
    
    /**
     * Funcion para listar todos los elementos de la clase correspondiente en la bbdd
     * @return lista de la clase correspondiente
     * @throws SQLException 
     */
    public List listar() throws SQLException{
        ArrayList<Project> list = new ArrayList<>();
        
        ArrayList<Empresa> listEmpresa = new ArrayList<>();
        String sql = "SELECT * FROM rrhh.proyectos";
        String sql_empresa = "SELECT * FROM rrhh.empresa";
        
        
        
        con = cn.conect();
        ps= con.prepareStatement(sql);
        
        rs = ps.executeQuery(sql);
        
        
        
        while (rs.next()){
            Project p = new Project();
            p.setId_proyecto(rs.getString(1));
            p.setNombre(rs.getString(2));
            p.setId_empresa(rs.getString(3));
            list.add(p);
        }
        return list;
    }
    /**
     * Nos da el elemento completo en base a su id, este simplemente pide su id y la busca en la bbdd
     * @param id
     * @return el objeto correspondiente
     * @throws SQLException 
     */
    public Project list(String id) throws SQLException{
        
        String sql = "SELECT * FROM rrhh.proyectos where id_proyecto="+id;
        
        
        
        con = cn.conect();
        ps= con.prepareStatement(sql);
        
        rs = ps.executeQuery(sql);
        
        
        
        while (rs.next()){  
            p.setId_proyecto(rs.getString(1));
            p.setNombre(rs.getString(2));
            p.setId_empresa(rs.getString(3));
        }
        return p;
    }
   
    /**
     * Para editar el proyecto
     * @param p el proyecto ya editado
     * @return un booleano para garantizar que se ha hecho correctamente
     */
    public boolean edit(Project p){
        try{
            String sql = "update rrhh.proyectos set nombre='" +p.getNombre()+"', id_empresa='"+p.getId_empresa() +"' where id_proyecto='"+p.getId_proyecto()+"'";
            con = cn.conect();
            ps= con.prepareStatement(sql);
            ps.executeUpdate(); 
            return true;
        }catch (Exception e){
        }
        
        
        return false;
        
    }
    /**
     * Para crear un nuevo proyecto en la bbdd
     * @param p el proyecto a crear
     * @return un booleano para garantizar que se ha hecho correctamente
     */
    public boolean create(Project p){
       
        try{
            String nombre = p.getNombre();
            String sql = "INSERT INTO rrhh.proyectos (id_proyecto, nombre, id_empresa) SELECT MAX(id_proyecto) + 1, '"+p.getNombre()+"', "+p.getId_empresa()+" FROM rrhh.proyectos";
            con = cn.conect();
            ps= con.prepareStatement(sql);
            ps.executeUpdate(); 
            return true;
        }catch (Exception e){
        }
        return false;
        
    }
    
}
