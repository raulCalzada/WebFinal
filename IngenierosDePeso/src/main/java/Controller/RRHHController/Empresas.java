/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.RRHHController;

import Model.Empresa;
import Model.Marcaje;
import Model.User;
import Utils.CRUDEmpresas;
import Utils.CRUDUsers;
import Utils.FormatoFecha;
import Utils.Log;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

    /**
      Funcionamiento similar al de UserController con los ifs
      action para ver el listado de empresas
      edit para ir a la pagina de edición
      update para editar la empresa
      menu para volver al menu
      add = para ir a la vista de añadir nueva empresa
      create = para crear una empresa
      informe = peticion para generar informe
     **/
public class Empresas extends HttpServlet {


    String listar = "/Views/RRHH/Empresas.jsp";
    String edit = "/Views/RRHH/EmpresasEdit.jsp";
    String add = "/Views/RRHH/EmpresasAdd.jsp";
    String RRHH = "/Views/PrincipalRRHH.jsp";
    private Log log;
    private FormatoFecha utilFecha = new FormatoFecha();

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

        } else if (action.equals("add")) {
            access = add;
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);

        } else if (action.equals("create")) {

            CRUDEmpresas crudE = new CRUDEmpresas();
            crudE.create(request.getParameter("txtNameEmpres"));
            access = listar;
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
                e = crudE.list(request.getParameter("idEmpresa"));

                userList = (ArrayList<User>) crudU.listar();
                for (int i = 0; i < userList.size(); i++) {
                    User u = userList.get(i);
                    u = crudU.list(u.getId());
                    if (u.getTipo().equals("A") || !u.getEmpresa().getId_empresa().equals(e.getId_empresa())) {
                        userList.remove(i);

                    }
                }
                //iniciamos el txt----------------------------
                response.setContentType("text/plain");
                response.setHeader("Content-Disposition", "attachment; filename=\"Informe Empresa" + e.getNombre_empresa() + ".txt\"");

                PrintWriter escritor = response.getWriter();

                //escritura del TXT---------------------------
                escritor.write("\n");
                escritor.write("INFORME DE LA EMPRESA " + e.getNombre_empresa() + " \n");
                escritor.write("\n");
                escritor.write("------------------------------------------------------------------------\n");
                escritor.write("Marcajes entre las fechas " + desde + " y " + hasta + "\n");
                escritor.write("\n");
                escritor.write("\\Username\\DNI\\Nombre\\Apellidos\\FechaAlta\\FechaBaja\\Proyecto \n");
                escritor.write("------------------------------------------------------------------------\n");
                escritor.write("\n");

                ArrayList<Marcaje> marcajesList = new ArrayList<>();
                for (int i = 0; i < userList.size(); i++) {

                    User u = userList.get(i);
                    u = crudU.list(u.getId());
                    if (u.getEmpresa().getNombre_empresa().equals(e.getNombre_empresa())) {
                        escritor.write("\n------------------------------------------------------------------------\n");
                        escritor.write("\n");
                        escritor.write("\n" + u.getUsername() + "\\" + u.getDni() + "\\" + u.getNombre() + "\\" + u.getApellidos() + "\\" + u.getFecha_alta() + "\\" + u.getFecha_baja() + "\\" + u.getProyecto().getNombre() + " \n ");
                        escritor.write("Marcajes de " + u.getUsername() + "\n");

                        marcajesList = (ArrayList<Marcaje>) crudU.listarMarcajes(u.getId());
                        if (marcajesList != null) {
                            for (int y = 0; y < marcajesList.size(); y++) {
                                String auxFecha = marcajesList.get(y).getFecha();
                                if (utilFecha.fechaMenorIgualFecha(desde, auxFecha, hasta)) {
                                    escritor.write(marcajesList.get(y).getTipo_marcaje() + " fecha: " + marcajesList.get(y).getFecha() + "\n");
                                }
                            }
                        }
                    }

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
