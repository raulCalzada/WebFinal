/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Model.Validate;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raulc
 */
public class Controller {
    User user= new User();
    Validate v = new Validate();
    int r;
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if (action.equals("Login")){
            String usname = req.getParameter("txtUsername");
            String psw = req.getParameter("txtPassword");
            user.setUsername(usname);
            user.setPassword(psw);
            r = v.Valid(user);
            if(r==1){
                req.getRequestDispatcher("Views/Principal.jsp").forward(req, res);
            }else{
                req.getRequestDispatcher("index.jsp").forward(req, res);
            }
        }
    }
}
