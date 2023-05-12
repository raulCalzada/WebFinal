/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Controller.RRHHController.Users;
import Model.Project;
import Model.User;
import Utils.CRUDUsers;
import Utils.Validate;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


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
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        
        
        if (action.equals("Login")){
        //PARA EL LOGIN
            String usname = request.getParameter("txtUsername");
            String psw = request.getParameter("txtPassword");
            user.setUsername(usname);
            user.setPassword(psw);
            
            
           
            if(v.Valid(user) == 1){
            //si es usuario tipo "U"
                CRUDUsers uu = new CRUDUsers();
                String u = uu.getUserId(usname,psw);
                request.setAttribute("idUser", u);
                request.getRequestDispatcher("/Views/PrincipalC.jsp").forward(request, response);
            
            }else if(v.Valid(user) == 2){
            //si es usuario tipo "A"
                request.getRequestDispatcher("/Views/PrincipalRRHH.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            
            
            
        }else if(action.equalsIgnoreCase("editUser")){
            //NOFUNCIONA
            request.setAttribute("idUser", request.getParameter("id"));
            request.getRequestDispatcher("/.jsp").forward(request, response);
            
            
            
            
            
            
        }else if (action.equalsIgnoreCase("updateUser")){
            //NOFUNCIONA
            User u = new User();
            CRUDUsers cu = new CRUDUsers();
            Project p = new Project();
            
            String id = request.getParameter("txtId");
            String username = request.getParameter("txtUsername");
            String name = request.getParameter("txtNombre");
            String surname = request.getParameter("txtApellidos");
            String dni = request.getParameter("txtDni");
            String fecha_alta = request.getParameter("txtFechaAlta");
            String fecha_baja = request.getParameter("txtFechaBaja");
            String proyecto = request.getParameter("txtProy");
            String tipo = request.getParameter("txtTipo");
            
            u.setId(id);
            u.setUsername(username);
            u.setNombre(name);
            u.setApellidos(surname);
            u.setDni(dni);
            u.setFecha_baja(fecha_baja);
            u.setFecha_alta(fecha_alta);
            p.setNombre(proyecto);
            u.setProyecto(p);
            u.setTipo(tipo);
            
            
            try {
                cu.edit(u, proyecto);
            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }catch (Exception e){
                
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
