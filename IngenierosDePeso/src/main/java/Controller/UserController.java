/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.User;
import Model.Validate;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *
 * @author raulc
 */

public class UserController extends HttpServlet {

    private static String RRHH= "/Views/PrincipalRRHH.jsp";
    private static String USER = "/Views/PrincipalC.jsp";
    private User user = new User();
    private int r = 0;
    private Validate v = new Validate();
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action.equals("Login")){
            String usname = request.getParameter("txtUsername");
            String psw = request.getParameter("txtPassword");
            user.setUsername(usname);
            user.setPassword(psw);
            
            if(v.Valid(user) == 1){
                request.getRequestDispatcher("/Views/PrincipalC.jsp").forward(request, response);
            }else if(v.Valid(user) == 2){
                request.getRequestDispatcher("/Views/PrincipalRRHH.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
