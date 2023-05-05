/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Model.Connect;
import com.mysql.cj.log.Log;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author raulc
 */
public class UserDao {
    public UserDao() throws SQLException {
        Connect connection = new Connect();
        Connection cx = connection.conect();
    }
}
