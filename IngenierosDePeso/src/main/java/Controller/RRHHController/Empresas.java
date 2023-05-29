/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.RRHHController;

import Model.Empresa;
import Model.User;
import Utils.CRUDEmpresas;
import Utils.CRUDUsers;
import Utils.Log;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    String listar = "/Views/RRHH/Empresas.jsp";
    String edit = "/Views/RRHH/EmpresasEdit.jsp";
    String RRHH = "/Views/PrincipalRRHH.jsp";
    private Log log;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String access = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("listar")) {
            Log.log.info("listar \n");
            access = listar;
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("edit")) {
            request.setAttribute("idempres", request.getParameter("id"));
            access = edit;
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("update")) {
            Empresa e = new Empresa();
            CRUDEmpresas ce = new CRUDEmpresas();

            String id = request.getParameter("txtId");
            String name = request.getParameter("txtNameEmpres");

            e.setId_empresa(id);
            e.setNombre_empresa(name);

            ce.edit(e);

            access = listar;
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("menu")) {
            access = RRHH;
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);
        } else if (action.equals("informe")) {
            String desde = request.getParameter("txtFechaDesde");
            String hasta = request.getParameter("txtFechaHasta");
            Empresa e = new Empresa();
            CRUDEmpresas crudE = new CRUDEmpresas();
            CRUDUsers crudU = new CRUDUsers();
            ArrayList<User> userList = new ArrayList<>();

            try {
                e = crudE.list(request.getParameter("txtIdEmpresa"));

                userList = (ArrayList<User>) crudU.listar();
                for (int i = 0; i < userList.size(); i++) {
                    User u = userList.get(i);
                    u = crudU.list(u.getId());
                    String idEmpresa = request.getParameter("txtIdEmpresa");
                    if (u.getTipo().equals("A") || !u.getEmpresa().getId_empresa().equals(idEmpresa)) {
                        userList.remove(i);
                        i--;
                    }
                }

                String contenido = "Contenido \n";
                String[] registros = {
                    "ID\tNombre\tApellido\tEdad",
                    "1\tJuan\tPérez\t25",
                    "2\tMaría\tLópez\t30",
                    "3\tCarlos\tGonzález\t28",
                    "4\tLaura\tGarcía\t22"
                };

                response.setContentType("text/plain");
                response.setHeader("Content-Disposition", "attachment; filename=\"Informe Empresa" + e.getNombre_empresa() + ".txt\"");

                PrintWriter escritor = response.getWriter();
                escritor.write(contenido);
                for (String registro : registros) {
                    escritor.write(registro + "\n");
                }
                escritor.close();

            } catch (SQLException ex) {
                Logger.getLogger(Empresas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //en base a cada else if voy a un lugar o a otro
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
