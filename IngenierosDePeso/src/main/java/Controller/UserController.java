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
import Utils.Log;
import Utils.Validate;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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
 * Clase servlet para gestionar las acciones relacionadas con los usuarios,
 * login y marcajes en el módulo de RRHH. Proporciona métodos para gestionar el
 * inicio de sesión de los usuarios, registrar sus horas de marcaje y realizar
 * acciones relacionadas con la autenticación y autorización. Esta clase
 * interactúa con la capa de datos para acceder a la base de datos de usuarios y
 * realizar operaciones relacionadas con el inicio de sesión y marcaje. Además,
 * se encarga de manejar las solicitudes HTTP relacionadas con el inicio de
 * sesión, registro de marcajes y otras acciones de los usuarios. Esta clase
 * forma parte del controlador de RRHH y se utiliza en conjunto con otras clases
 * y componentes para implementar la funcionalidad completa del módulo.
 *
 * @author raulc
 *
 */
public class UserController extends HttpServlet {

    private static String RRHH = "/Views/PrincipalRRHH.jsp";
    private static String USER = "/Views/PrincipalC.jsp";
    private User user = new User();
    private int r = 0;
    private Validate v = new Validate();
    private Cookie loginCookie;
    private Log log;

    /**
     * El método processRequest está compuesto por distintas acciones de
     * llamadas de JSP con la palabra clave "action".
     *
     * Acciones disponibles: - Login: obtiene usuario y contraseña, llama a
     * Validate y nos devuelve si es User de tipo U, A o no existe, y lo guarda
     * en una cookie.
     *
     * -------AHORA LAS DEMÁS ACCIONES SON PARA EL USUARIO DE TIPO "U"------- -
     * edit: entra en la página de edición del usuario logueado. - updateUser:
     * llama a CRUDUsers y, tomando los parámetros de edición, edita al usuario.
     * - marcaje: va a la página de añadir marcaje. - addMarcaje: llama a
     * CRUDMarcaje y, tomando los parámetros dados, crea un nuevo marcaje para
     * el usuario. - seeMarcaje: entra en la página de marcajes del usuario
     * logueado. - personal: vuelve a la página de inicio del usuario logueado.
     * - logout: borra la cookie del usuario logueado y vuelve a la página de
     * inicio.
     *
     * @param request objeto HttpServletRequest que representa la solicitud HTTP
     * @param response objeto HttpServletResponse que representa la respuesta
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Log.log.info("Entramos en el UserController\n");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equals("Login")) {
            //PARA EL LOGIN
            String usname = request.getParameter("txtUsername");
            String psw = request.getParameter("txtPassword");
            user.setUsername(usname);
            user.setPassword(psw);
            Log.log.info("Intento de logueo de " + usname + "\n");

            CRUDUsers uu = new CRUDUsers();

            if (v.Valid(user) == 1) {
                //si es usuario tipo "U"
                Log.log.info("El usuario es trabajador\n");
                String u = uu.getUserId(usname, psw);
                loginCookie = new Cookie("idUser", u);
                loginCookie.setMaxAge(30 * 60);
                response.addCookie(loginCookie);
                request.getRequestDispatcher("/Views/PrincipalC.jsp").forward(request, response);
            } else if (v.Valid(user) == 2) {
                //si es usuario tipo "A"
                Log.log.info("El usuario es de RRHH\n");
                String u = uu.getUserId(usname, psw);
                loginCookie = new Cookie("idUser", u);
                loginCookie.setMaxAge(30 * 60);
                response.addCookie(loginCookie);
                request.getRequestDispatcher("/Views/PrincipalRRHH.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        } else if (action.equalsIgnoreCase("edit")) {
            Log.log.info("Procedemos a entrar en /Views/User/EditUser.jsp con el usuario de id " + loginCookie.getName().equals("idUser") + "\n");
            request.getRequestDispatcher("/Views/User/EditUser.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("updateUser")) {
            //Edita el usuario con los parámetros indicados
            Log.log.info("Procedemos a editar al usuario " + loginCookie.getName().equals("idUser") + "\n");
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
            } catch (Exception e) {

            }
            request.getRequestDispatcher("/Views/PrincipalC.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("marcaje")) {
            Log.log.info("Procedemos a entrar en /Views/User/Marcaje.jsp con el usuario de id " + loginCookie.getName().equals("idUser") + " para poder añadir un nuevo marcaje\n");
            request.getRequestDispatcher("/Views/User/Marcaje.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("addMarcaje")) {
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
            Log.log.info("Procedemos a añadir un nuevo marcaje para el usuario de id " + loginCookie.getName().equals("idUser") + ""
                    + " con fecha " + fechaFormateada + " y tipo d marcaje = " + tipoM + "para ver el marcaje\n");

            cu.setUserMarcajes(m);

            request.getRequestDispatcher("/Views/PrincipalC.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("seeMarcaje")) {
            Log.log.info("Procedemos a entrar en /Views/User/VerMarcajes.jsp con el usuario de id " + loginCookie.getName().equals("idUser") + " para ver sus marcajes\n");
            request.getRequestDispatcher("/Views/User/VerMarcajes.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("personal")) {
            Log.log.info("Volvemos a la página de inicio del usuario " + loginCookie.getName().equals("idUser") + "\n");

            request.getRequestDispatcher("/Views/PrincipalC.jsp").forward(request, response);

        } else if (action.equalsIgnoreCase("logout")) {
            Log.log.info("Cerramos sesion del usuario " + loginCookie.getName().equals("idUser") + "\n");

            loginCookie = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("idUser")) {
                        loginCookie = cookie;
                        break;
                    }
                }
            }
            if (loginCookie != null) {
                loginCookie.setValue("");
                loginCookie.setMaxAge(-1);
                response.addCookie(loginCookie);
            }

            request.getRequestDispatcher("index.jsp").forward(request, response);
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

        Log.log.info("Entramos en el UserController\n");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action.equals("Login")) {
            //PARA EL LOGIN
            String usname = request.getParameter("txtUsername");
            String psw = request.getParameter("txtPassword");
            user.setUsername(usname);
            user.setPassword(psw);
            Log.log.info("Intento de logueo de " + usname + "\n");

            CRUDUsers uu = new CRUDUsers();

            if (v.Valid(user) == 1) {
                try {
                    //si es usuario tipo "U"
                    Log.log.info("El usuario es trabajador\n");
                    String u = uu.getUserId(usname, psw);
                    loginCookie = new Cookie("idUser", u);
                    loginCookie.setMaxAge(30 * 60);
                    response.addCookie(loginCookie);
                    request.getRequestDispatcher("/Views/PrincipalC.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (v.Valid(user) == 2) {
                try {
                    //si es usuario tipo "A"
                    Log.log.info("El usuario es de RRHH\n");
                    String u = uu.getUserId(usname, psw);
                    loginCookie = new Cookie("idUser", u);
                    loginCookie.setMaxAge(30 * 60);
                    response.addCookie(loginCookie);
                    request.getRequestDispatcher("/Views/PrincipalRRHH.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                request.getRequestDispatcher("/Principal.jsp").forward(request, response);
            }
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
