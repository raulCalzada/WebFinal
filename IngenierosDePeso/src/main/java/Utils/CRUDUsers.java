/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;


import Model.Connect;
import Model.Empresa;
import Model.Project;
import Model.User;
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
public class CRUDUsers {
    
    Connect cn = new Connect();
    Connection con;
    PreparedStatement ps;
    PreparedStatement ps2;
    ResultSet rs;
    ResultSet aux;
    ResultSet rs2;
    ResultSet rs3;
    
    Project p = new Project();
    User u = new User();
    Empresa e = new Empresa();
    
    public List listar() throws SQLException{
        ArrayList<User> list = new ArrayList<>();
        ArrayList<Project> listProj = new ArrayList<>();
        ArrayList<Empresa> listEm = new ArrayList<>();
        
        String sql = "SELECT * FROM rrhh.usuarios";
        
        con = cn.conect();
        ps= con.prepareStatement(sql);
        
        rs = ps.executeQuery(sql);
        
        while (rs.next()){
            User u = new User();
            
            u.setId(rs.getString(1));
            u.setUsername(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setDni(rs.getString(4));
            u.setNombre(rs.getString(5));
            u.setApellidos(rs.getString(6));
            u.setFecha_alta(rs.getString(7));
            u.setFecha_baja(rs.getString(8));
            u.setTipo(rs.getString(9));
            
            if (u.getTipo().equals("U")){
                Project p = new Project();
                Empresa e = new Empresa(); 
                
                ps2= con.prepareStatement(sql);
                //seleccionamos el id_proyecto correspondiente al usuario
                aux= ps2.executeQuery("SELECT id_proyecto FROM rrhh.usuarios_proyectos where id_user='"+u.getId()+"'");
                String praux = null;
                while (aux.next()){
                   praux  = aux.getString(1);
                }
                
                //seleccionamos el proyecto
                
                rs2 = ps2.executeQuery("SELECT * FROM rrhh.proyectos where id_proyecto='"+praux+"'");
                while (rs2.next()){
                    p.setId_proyecto(rs2.getString(1));
                    p.setNombre(rs2.getString(2));
                    p.setId_empresa(rs2.getString(3)); 
                }
                
                
                //seleccionamos la empresa asociada al proyecto y al trabajador
                rs3= ps2.executeQuery("SELECT * FROM rrhh.empresa where id_empresa='"+p.getId_empresa() +"'");
                while (rs3.next()){
                e.setId_empresa(rs3.getString(1));
                e.setNombre_empresa(rs3.getString(2));
                }
                u.setEmpresa(e);
                u.setProyecto(p);
            }
            list.add(u);
 
        }
        
        return list;
    }
    

    public void edit(User u, String projectName) throws SQLException {
        
        
        String sql = "update rrhh.usuarios set  username='"+u.getUsername()+"',dni='"+u.getDni()+"',nombre='"+u.getNombre()+"',apellidos='"+u.getApellidos()+"',fecha_alta='"+u.getFecha_alta()+"',fecha_baja='"+u.getFecha_baja()+"' where id_user= '"+u.getId() + "'";
        con = cn.conect();
        ps= con.prepareStatement(sql);
        ps.executeUpdate();
        
        
        
        if (u.getTipo().equals("U")){
            String sql2 = "SELECT id_proyecto FROM rrhh.proyectos where nombre='" +projectName+ "' LIMIT 1";
            
            String rsaux = null;
            ps2 = con.prepareStatement(sql2);
            rs2 = ps2.executeQuery(sql2);
            while(rs2.next()){
                rsaux = rs2.getString(1);
            }
            String sql3 = "update rrhh.usuarios_proyectos set id_proyecto='"+rsaux+"' where id_user='"+u.getId()+"'";
            ps2= con.prepareStatement(sql3);
            ps2.executeUpdate();
        }
        
    }
    
    public User list(String id) throws SQLException{
        String sql = "SELECT * FROM rrhh.usuarios where id_user="+id;
        
        con = cn.conect();
        ps= con.prepareStatement(sql);
        
        rs = ps.executeQuery(sql);
        while (rs.next()){
            u.setId(rs.getString(1));
            u.setUsername(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setDni(rs.getString(4));
            u.setNombre(rs.getString(5));
            u.setApellidos(rs.getString(6));
            u.setFecha_alta(rs.getString(7));
            u.setFecha_baja(rs.getString(8));
            u.setTipo(rs.getString(9));
            
            if (u.getTipo().equals("U")){
                Project p = new Project();
                Empresa e = new Empresa(); 
                
                ps2= con.prepareStatement(sql);
                //seleccionamos el id_proyecto correspondiente al usuario
                aux= ps2.executeQuery("SELECT id_proyecto FROM rrhh.usuarios_proyectos where id_user='"+u.getId()+"'");
                String auxid = null;
                while (aux.next()){
                    auxid = aux.getString(1);
                }
                
                //seleccionamos el proyecto
                 rs2 = ps2.executeQuery("SELECT * FROM rrhh.proyectos where id_proyecto='"+auxid+"'");
                while (rs2.next()){
                    p.setId_proyecto(rs2.getString(1));
                    p.setNombre(rs2.getString(2));
                    p.setId_empresa(rs2.getString(3));
                }
                
                
                //seleccionamos la empresa asociada al proyecto y al trabajador
                rs3= ps2.executeQuery("SELECT * FROM rrhh.empresa where id_empresa='"+p.getId_empresa() +"'");
                while (rs3.next()){
                    e.setId_empresa(rs3.getString(1));
                    e.setNombre_empresa(rs3.getString(2));
                }
                
                u.setEmpresa(e);
                u.setProyecto(p);
            }
        }
        return u;
        
    }
    public String getUserId(String usname, String psw) throws SQLException {
        User user = new User();
        String sql = "SELECT id_user FROM usuarios where username='"+usname+"' and password= '"+usname+"'";
        
        con = cn.conect();
        ps= con.prepareStatement(sql);
        
        rs = ps.executeQuery(sql);
        String id = null;
        while (rs.next()){
            id = rs.getString(1);
        }
        
        return id;
    }
    
}
