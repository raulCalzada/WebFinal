<%-- 
    Document   : ProyectosEdit
    Created on : 10 may 2023, 12:45:16
    Author     : raulc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="Utils.CRUDProjects" %>
<%@page import="Model.Project" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            .form-container form input[type="hidden"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                box-sizing: border-box;
            }

            .form-container form button[type="submit"],
            .form-container form a {
                display: inline-block;
                background-color: #808080;
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
        <%  CRUDProjects crudProjects = new CRUDProjects();
            String id = (String) request.getAttribute("idproj");
            Project project = (Project)crudProjects.list(id);
        %>
        <div class="container">
            <div class="form-container">
                <h1>Modificar proyecto</h1>
                <form action="Projects">
                    Nombre:<br><!-- comment -->
                    <input type="text" name="txtNameProyect" value="<%= project.getNombre() %>"> <br>
                    Empresa:<br><!-- comment -->
                    <input type="text" name="txtEmpresaProyect" value="<%= project.getId_empresa() %>"> <br>
                    <input type="hidden" name="txtId" value="<%= project.getId_proyecto() %>">
                    <button type="submit" name="action" value="update">Guardar</button>
                    <a href="Projects?action=listar">Volver</a>
                </form>
            </div>
        </div>
    </body>
</html>

