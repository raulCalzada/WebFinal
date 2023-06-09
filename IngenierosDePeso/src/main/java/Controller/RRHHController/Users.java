/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.RRHHController;


import Model.Project;
import Model.User;
import Utils.CRUDUsers;
import Utils.FormatoFecha;
import Utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase servlet para gestionar las acciones relacionadas con los usuarios en el módulo de RRHH.
 * Proporciona métodos para crear, modificar y eliminar usuarios, así como para autenticar y autorizar el acceso de los mismos.
 * Esta clase interactúa con la capa de datos para acceder a la base de datos de usuarios y realizar operaciones relacionadas.
 * Además, se encarga de manejar las solicitudes HTTP relacionadas con los usuarios, como registrar nuevos usuarios, iniciar sesión o procesar formularios de modificación de datos.
 * Esta clase forma parte del controlador de RRHH y se utiliza en conjunto con otras clases y componentes para implementar la funcionalidad completa del módulo.
 *
 * @author raulc
 **/

public class Users extends HttpServlet {

    
    String listar="/Views/RRHH/Trabajadores.jsp";
    String edit= "/Views/RRHH/TrabajadoresEdit.jsp";
    String add="/Views/RRHH/TrabajadoresAdd.jsp";
    String listarMarcaje = "/Views/RRHH/Marcajes.jsp";
    String RRHH= "/Views/PrincipalRRHH.jsp";
    private Log log;
     /**
     * Funcionamiento similar al de UserController con los ifs
     * action para ver el listado de usuarios
     * edit para ir a la pagina de edición
     * update para editar el usuario
     * menu para volver al menu
     * add = para ir a la vista de añadir nuevo usuario
     * create = para crear un usuario
     * 

     * @throws ServletException if a servlet-specific error occurs exception for bbdd
     * @throws IOException if an I/O error occurs exception for bbdd
     **/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String access = "";
        String action =request.getParameter("action");
        if(action.equalsIgnoreCase("listar")){
            access = listar;
        }else if(action.equalsIgnoreCase("editUser")){
            request.setAttribute("idUser", request.getParameter("id"));
            access = edit;
        }else if (action.equals("add")){
            access = add;
            
            
        }else if (action.equals("create")){
            Project p = new Project();
            User u = new User();
            CRUDUsers crudU = new CRUDUsers();
            
            String id = request.getParameter("txtId");
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String name = request.getParameter("txtNombre");
            String surname = request.getParameter("txtApellidos");
            String dni = request.getParameter("txtDni");
            String fecha_alta = request.getParameter("txtFechaAlta");
            String fecha_baja = request.getParameter("txtFechaBaja");
            String proyecto = request.getParameter("txtProy");
            String tipo = request.getParameter("txtTipo");
            FormatoFecha f = new FormatoFecha();
            String alta = f.formatearFechaSeg(fecha_alta);
            String baja = f.formatearFechaSeg(fecha_baja);
            
            u.setId(id);
            u.setUsername(username);
            u.setPassword(password);
            u.setNombre(name);
            u.setApellidos(surname);
            u.setDni(dni);
            u.setFecha_baja(baja);
            u.setFecha_alta(alta);
            u.setTipo(tipo);
            
            crudU.create(u,proyecto);
            
            access = listar;
        
        }else if (action.equalsIgnoreCase("updateUser")){
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
            FormatoFecha f = new FormatoFecha();
            String alta = f.formatearFechaSeg(fecha_alta);
            String baja = f.formatearFechaSeg(fecha_baja);
            
            u.setId(id);
            u.setUsername(username);
            u.setNombre(name);
            u.setApellidos(surname);
            u.setDni(dni);
            u.setFecha_baja(baja);
            u.setFecha_alta(alta);
            p.setNombre(proyecto);
            u.setProyecto(p);
            u.setTipo(tipo);
            
            
            try {
                cu.edit(u, proyecto);
            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }catch (Exception e){
                
            }


                
            access = listar;
        
        
        
            
        }else if(action.equalsIgnoreCase("seeMarcaje")){
            User u = new User();
            request.setAttribute("idUser", request.getParameter("id"));
            access = listarMarcaje;
        }
        
        else if(action.equalsIgnoreCase("menu")){
            access = RRHH;
        }
        
        //en base a cada else if voy a un lugar o a otro
        RequestDispatcher view = request.getRequestDispatcher(access);
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
