/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingenieros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raulc
 */
public class Connect {   
    String user = "root";
    String password ="Jager1234";
    String bd = "rrhh";
    String url="jdbc:mysql://localhost:3306/"; //?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC
    String driver= "com.mysql.cj.jdbc.Driver";
    Connection cx;
    
    public Connect (){
        
    }
    
    public Connection conect() throws SQLException{
        try {
            Class.forName(driver);
            cx = (Connection) DriverManager.getConnection(url+bd, user, password);
            System.out.print("SE CONECTÓ A LA BD "+bd);
            
        } catch (ClassNotFoundException ex) {
            System.out.print("NO SE CONECTÓ A LA BD "+bd);
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }
    
    public void disconnect(){
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main (String[] args) throws SQLException{
        Connect connection = new Connect();
        Connection cx = connection.conect();
        
        Statement state = cx.createStatement();
        ResultSet rs = state.executeQuery("Select * from empresa");
        while(rs.next()){
            System.out.println(rs.getString(2));
        }
        cx.close();
    }
}
