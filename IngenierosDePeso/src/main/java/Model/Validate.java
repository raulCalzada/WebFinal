/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raulc
 */
public class Validate {
    Connection con;
    Connect cn = new Connect();
    PreparedStatement ps;
    ResultSet rs; 
    int r = 0;
    
    public int Valid(User user){
        String sql = "Select * from rrhh where username=? and password=?";
        try {
            con = cn.conect();
            ps= con.prepareStatement(sql);
            ps.setString(2,user.getUsername() );
            ps.setString(3, user.getPassword());
            while(rs.next()){
                r += 1;
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
}
