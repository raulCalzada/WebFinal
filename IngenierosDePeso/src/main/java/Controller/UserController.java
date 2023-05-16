/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Controller.RRHHController.Users;
import Model.Marcaje;
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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            
            
            
        }else if(action.equalsIgnoreCase("edit")){
            request.setAttribute("idUser", request.getParameter("id"));
            request.getRequestDispatcher("/Views/User/EditUser.jsp").forward(request, response);
            
            
            
            
            
            
        }else if (action.equalsIgnoreCase("updateUser")){
            //Edita el usuario con los parámetros indicados
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
            request.setAttribute("idUser", u);
            request.getRequestDispatcher("/Views/PrincipalC.jsp").forward(request, response);
        
        
        }else if(action.equalsIgnoreCase("marcaje")){
            User u = new User();
            request.setAttribute("idUser", request.getParameter("id"));
            request.getRequestDispatcher("/Views/User/Marcaje.jsp").forward(request, response);
            
            
            
            
            
        }else if(action.equalsIgnoreCase("addMarcaje")){
            //NO FUNCIONA
            User u = new User();
            Marcaje m = new Marcaje();
            CRUDUsers cu = new CRUDUsers();
            //id del usuario
            String id = request.getParameter("txtId");
            
            String tipoM = request.getParameter("txtTipoMarcaje");
            String fecha = request.getParameter("txtFecha");
            
            //cambiar el formato de la fecha
            String nuevoFormato = "yyyy-MM-dd HH:mm:ss";

            LocalDateTime date = LocalDateTime.parse(fecha);
            DateTimeFormatter formatoNuevo = DateTimeFormatter.ofPattern(nuevoFormato);
            String fechaFormateada = date.format(formatoNuevo);
            
            
            
            //------------------------------------------
            u = cu.list(id);
            
            m.setFecha(fechaFormateada);
            m.setTipo_marcaje(tipoM);
            m.setId_usuario(id);
            
            cu.setUserMarcajes(m);
            
            
            request.setAttribute("idUser", u);
            request.getRequestDispatcher("/Views/PrincipalC.jsp").forward(request, response);
            
            
            
            
            
            
            
            
        }else if(action.equalsIgnoreCase("seeMarcaje")){
            User u = new User();
            request.setAttribute("idUser", request.getParameter("id"));
            request.getRequestDispatcher("/Views/User/VerMarcajes.jsp").forward(request, response);
            
            
            
        }else if(action.equalsIgnoreCase("personal")){
            request.setAttribute("idUser", request.getParameter("id"));
            request.getRequestDispatcher("/Views/PrincipalC.jsp").forward(request, response);
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
