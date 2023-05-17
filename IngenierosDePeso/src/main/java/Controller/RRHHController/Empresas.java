/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.RRHHController;

import Model.Empresa;
import Utils.CRUDEmpresas;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author raulc
 */
public class Empresas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String listar="/Views/RRHH/Empresas.jsp";
    String edit= "/Views/RRHH/EmpresasEdit.jsp";
    String RRHH= "/Views/PrincipalRRHH.jsp";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String access = "";
        String action =request.getParameter("action");
        if(action.equalsIgnoreCase("listar")){
            access = listar;
        }else if(action.equalsIgnoreCase("edit")){
            request.setAttribute("idempres", request.getParameter("id"));
            access = edit;
        }else if (action.equalsIgnoreCase("update")){
            Empresa e = new Empresa();
            CRUDEmpresas ce = new CRUDEmpresas();
            
            String id = request.getParameter("txtId");
            String name = request.getParameter("txtNameEmpres");
            
            e.setId_empresa(id);
            e.setNombre_empresa(name);
            
            ce.edit(e);
            
            access = listar;
        }else if(action.equalsIgnoreCase("menu")){
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
