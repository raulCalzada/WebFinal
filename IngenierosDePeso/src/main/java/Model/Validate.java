/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raulc
 */
public class Validate {
    Connect cn = new Connect();
    PreparedStatement ps;
    ResultSet rs; 
    int r = 0;
    
    public int Valid(User user){
        String sql = "SELECT username,password,tipo_usuario FROM rrhh.usuarios  ";
        ArrayList<String> usernames = new ArrayList<String>();
        ArrayList<String> passwords = new ArrayList<String>();
        ArrayList<String> typeUser = new ArrayList<String>();
        try {
            Connect connection = new Connect();
            Connection con = connection.conect();
            ps= con.prepareStatement(sql);
            
            rs = ps.executeQuery();
            while(rs.next()){
                usernames.add(rs.getString(1));
                passwords.add(rs.getString(2));
                typeUser.add(rs.getString(3));
            }
            String us;
            String pw;
            for(int i = 0; i <= usernames.size()-1; i++){
                us = user.username;
                pw =  user.password;
                
                if (usernames.get(i).equals(user.username) && passwords.get(i).equals(user.password)){     
                    if (typeUser.get(i).equals("U")) return 1;
                    else return 2;
  
                }
            } 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 0;
        
    }
}
