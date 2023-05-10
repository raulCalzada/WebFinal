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

    public boolean add(Project p){
        throw new UnsupportedOperationException("");
    }
    public boolean edit(Project p){
        throw new UnsupportedOperationException("");
    }
    public boolean eliminar(int id){
        throw new UnsupportedOperationException("");
    }
    
}
