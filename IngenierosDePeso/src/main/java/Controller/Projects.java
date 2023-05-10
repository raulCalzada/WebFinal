/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Project;
import Utils.CRUDProjects;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Projects extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String listar="/Views/RRHH/Proyectos.jsp";
    String edit= "/Views/RRHH/ProyectosEdit.jsp";
    
    
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
        }else if(action.equalsIgnoreCase("edit")){
            request.setAttribute("idproj", request.getParameter("id"));
            access = edit;
        }else if (action.equalsIgnoreCase("update")){
            Project p = new Project();
            CRUDProjects cpr = new CRUDProjects();
            
            String id = request.getParameter("txtId");
            String name = request.getParameter("txtNameProyect");
            String empresaId = request.getParameter("txtEmpresaProyect");
            
            p.setId_empresa(empresaId);
            p.setId_proyecto(id);
            p.setNombre(name);
            
            
            cpr.edit(p);
                
            access = listar;
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
