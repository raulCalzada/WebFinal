/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Utils;

import Model.Connect;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para validar los logins de usuarios.
 * Esta clase se encarga de recibir los datos de usuario y validarlos, incluyendo la autenticación mediante cookies y la consulta de información en la base de datos.
 * Proporciona métodos para verificar la validez de un login, autenticar al usuario y obtener datos relacionados con el usuario autenticado.
 *
 * @author raulc
 */

public class Validate {
    Connect cn = new Connect();
    PreparedStatement ps;
    ResultSet rs; 
    int r = 0;
    private Log log;
    
    /**
     * Esta clase recibe un usuario y valida diciendo que tipo de usuario es para el login
     * @param user
     * @return número que va a ser recogido indicando el tipo de usuario que es
     */
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
            con.close();
            for(int i = 0; i <= usernames.size()-1; i++){
                us = user.getUsername();
                pw =  user.getPassword();
                
                if (usernames.get(i).equals(user.getUsername()) && passwords.get(i).equals(user.getPassword())){     
                    if (typeUser.get(i).equals("U")){
                        Log.log.info("Usuario "+us+" de tipo U\n");
                        return 1;
                    }
                    else{
                        Log.log.info("Usuario "+us+" de tipo A\n");
                        return 2;
                    }
  
                }
            } 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return 0;
        
    }
}
