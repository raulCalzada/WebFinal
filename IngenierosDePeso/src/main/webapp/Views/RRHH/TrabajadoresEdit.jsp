<%-- 
    Document   : TrabajadoresEdit
    Created on : 10 may 2023, 12:45:26
    Author     : raulc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="Utils.CRUDUsers" %>
<%@page import="Utils.CRUDEmpresas" %>
<%@page import="Utils.CRUDProjects" %>
<%@page import="Model.Project" %>
<%@page import="Model.User" %>
<%@page import="Model.Empresa" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
    <%
            CRUDUsers crudUsers= new CRUDUsers();
            String idu = null; // Declarar la variable y asignarle un valor predeterminado
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("idUser")) {
                        idu = cookie.getValue();
                        break; // Si se encuentra la cookie, se asigna el valor y se sale del bucle
                    }
                }
            }

   
            User e = (User)crudUsers.list(idu);
    %>
    <head>
        <%
            String id = (String) request.getAttribute("idUser");
            User u = (User)crudUsers.list(id);
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edicion <%= u.getUsername()%></title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
            }

            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .form-container {
                background-color: #f5f5f5;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                padding: 20px;
                width: 400px;
            }

            .form-container h1 {
                color: #000;
                font-size: 28px;
                margin: 0;
                text-align: center;
            }

            .form-container form {
                margin-top: 20px;
                text-align: center;
            }

            .form-container form input[type="text"],
            .form-container form input[type="hidden"],
            .form-container form input[type="datetime-local"],
            .form-container form select{
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                box-sizing: border-box;
            }


            .form-container form button[type="submit"],
            .form-container form a {
                display: inline-block;
                background-color: #006400;
                color: #fff;
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
            }

            .form-container form button[type="submit"] {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="form-container">
                <h1>Modificar Usuario</h1>
                <form action="Users">
                    UserName<br><!-- comment -->
                    <input type="text" name="txtUsername" value="<%= u.getUsername() %>" required> <br>
                    Nombre<br><!-- comment -->
                    <input type="text" name="txtNombre" value="<%= u.getNombre() %>" required> <br>
                    Apellidos<br><!-- comment -->
                    <input type="text" name="txtApellidos" value="<%= u.getApellidos() %>" required> <br>
                    DNI<br><!-- comment -->
                    <input type="text" name="txtDni" value="<%= u.getDni() %>" required> <br>
                    Fecha Alta<br><!-- comment -->
                    <input type="datetime-local" id="fecha" name="txtFechaAlta" class="form-control" value="<%= u.getFecha_alta() %>" step="1" required><br>
                    Fecha Baja<br><!-- comment -->
                    <input type="datetime-local" id="fecha" name="txtFechaBaja" class="form-control" value="<%= u.getFecha_baja() %>" step="1" required><br>
                    Proyecto<br><!--comment -->
                    <select type="select" name="txtProy">
                        <option value="<%= u.getProyecto().getNombre() %>"><%= u.getProyecto().getNombre() %></option>
                        <%CRUDProjects crudP = new CRUDProjects();
                        List<Project> projectList = crudP.listar();
                           for (Project p : projectList) {
                        %>
                        <option value="<%= p.getNombre() %>"> <%= p.getNombre() %></option>
                        <% } %>
                    </select><br>
                    Empresa (readonly)<br><!-- comment -->
                    <input type="text" value="<%= u.getEmpresa().getNombre_empresa() %>" readonly> <br>
                    <input type="hidden" name="txtId" value="<%= u.getId() %>">
                    <input type="hidden" name="txtTipo" value="<%= u.getTipo() %>">
                    <button type="submit" name="action" value="updateUser">Guardar</button>
                    <a href="Users?action=listar">Volver</a>
                </form>
            </div>
        </div>

    </body>
</html>
