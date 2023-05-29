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
        } else if (action.equalsIgnoreCase("edit")) {
            request.setAttribute("idempres", request.getParameter("id"));
            access = edit;
        } else if (action.equalsIgnoreCase("update")) {
            Empresa e = new Empresa();
            CRUDEmpresas ce = new CRUDEmpresas();

            String id = request.getParameter("txtId");
            String name = request.getParameter("txtNameEmpres");

            e.setId_empresa(id);
            e.setNombre_empresa(name);

            ce.edit(e);

            access = listar;
        } else if (action.equalsIgnoreCase("menu")) {
            access = RRHH;
        } else if (action.equals("informe")) {

            OutputStream out = response.getOutputStream();
            String desde = request.getParameter("txtFechaDesde");
            String hasta = request.getParameter("txtFechaHasta");
            Empresa e = new Empresa();
            CRUDEmpresas crudE = new CRUDEmpresas();
            CRUDUsers crudU = new CRUDUsers();

            try {
                e = crudE.list(request.getParameter("txtIdEmpresa"));
                ArrayList<User> userList = new ArrayList<>();
                userList = (ArrayList<User>) crudU.listar();
                for (int i = 0; i < userList.size(); i++) {
                    User u = new User();
                    u = crudU.list(userList.get(i).getId());
                    if (u.getTipo().equals("A")) {
                        userList.remove(i);
                    } else if (u.getEmpresa().getId_empresa().equals(request.getParameter("txtIdEmpresa"))) {
                        userList.remove(i);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Empresas.class.getName()).log(Level.SEVERE, null, ex);
            }

            String outputFile = "InformeEmpresas.pdf";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Crear el objeto PdfWriter
            PdfWriter writer = new PdfWriter(baos);

            // Crear el objeto PdfDocument
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Crear el objeto Document
            Document doc = new Document(pdfDoc);

            // Crear la tabla para el listado de trabajadores
            Table table = new Table(3); // 3 columnas para nombre, apellidos y DNI

            // Agregar encabezados de columna a la tabla
            table.addCell("Nombre");
            table.addCell("Apellidos");
            table.addCell("DNI");

            // Agregar filas con datos de los trabajadores a la tabla
            table.addCell("Juan");
            table.addCell("Pérez");
            table.addCell("12345678A");

            table.addCell("María");
            table.addCell("López");
            table.addCell("98765432B");

            // Agregar la tabla al documento
            doc.add(table);

            // Cerrar el documento
            doc.close();
            byte[] pdfBytes = baos.toByteArray();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=InformeEmpresas.pdf");

            try (OutputStream outf = response.getOutputStream()) {
                writer.flush();
                writer.close();
                outf.flush();
            }

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
