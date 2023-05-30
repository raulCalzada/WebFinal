/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.RRHHController;

import Model.Empresa;
import Model.Marcaje;
import Model.Project;
import Model.User;
import Utils.CRUDEmpresas;
import Utils.CRUDProjects;
import Utils.CRUDUsers;
import Utils.FormatoFecha;
import Utils.Log;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
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
    String listar = "/Views/RRHH/Proyectos.jsp";
    String edit = "/Views/RRHH/ProyectosEdit.jsp";
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
            access = listar;
            //en base a cada else if voy a un lugar o a otro
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("edit")) {
            request.setAttribute("idproj", request.getParameter("id"));
            access = edit;
            //en base a cada else if voy a un lugar o a otro
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("update")) {
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
            //en base a cada else if voy a un lugar o a otro
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("menu")) {
            access = RRHH;
            //en base a cada else if voy a un lugar o a otro
            RequestDispatcher view = request.getRequestDispatcher(access);
            view.forward(request, response);

        } else if (action.equals("informe")) {
            String desde = request.getParameter("txtFechaDesde");
            String hasta = request.getParameter("txtFechaHasta");
            Project p = new Project();
            Empresa e = new Empresa();
            CRUDProjects crudP = new CRUDProjects();
            CRUDEmpresas crudE = new CRUDEmpresas();
            CRUDUsers crudU = new CRUDUsers();
            ArrayList<User> userList = new ArrayList<>();

            try {
                //inicializamos empresa y proyectos requeridos
                p = crudP.list(request.getParameter("idProyecto"));
                e = crudE.list(p.getId_empresa());

                //inicializamos a todos los usuarios y quitamos a los de RRHH
                userList = (ArrayList<User>) crudU.listar();
                for (int i = 0; i < userList.size(); i++) {
                    User u = userList.get(i);
                    u = crudU.list(u.getId());
                    if (u.getTipo().equals("A")) {
                        userList.remove(i);

                    }
                }
                //iniciamos el txt----------------------------
                response.setContentType("text/plain");
                response.setHeader("Content-Disposition", "attachment; filename=\"Informe Poyecto" + e.getNombre_empresa() + ".txt\"");

                PrintWriter escritor = response.getWriter();

                //escritura del TXT---------------------------
                escritor.write("\n");
                escritor.write("INFORME DEL PROYECTO " + p.getNombre() + " de la empresa "+e.getNombre_empresa()+" \n");
                escritor.write("\n");
                escritor.write("------------------------------------------------------------------------\n");
                escritor.write("Información de marcajes entre las fechas " + desde + " y " + hasta + "\n");
                escritor.write("\n");
                escritor.write("\\Username\\DNI\\Nombre\\Apellidos\\FechaAlta\\FechaBaja \n");
                escritor.write("------------------------------------------------------------------------\n");
                escritor.write("\n");

                ArrayList<Marcaje> marcajesList = new ArrayList<>();
                for (int i = 0; i < userList.size(); i++) {

                    User u = userList.get(i);
                    u = crudU.list(u.getId());
                    if (u.getProyecto().getId_proyecto().equals(p.getId_proyecto())) {
                        escritor.write("\n------------------------------------------------------------------------\n");
                        escritor.write("\n");
                        escritor.write("\n" + u.getUsername() + "\\" + u.getDni() + "\\" + u.getNombre() + "\\" + u.getApellidos() + "\\" + u.getFecha_alta() + "\\" + u.getFecha_baja() + " \n");
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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
