<%-- 
    Document   : Proyectos
    Created on : 5 may 2023, 11:36:40
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
        <title>Proyectos RRHH</title>
        <link rel="stylesheet" href="../css/RRHH.css">
    </head>
    <body>
        <div class="header">
            <div class="square">
                <h1>Proyectos</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Numero proyecto</th>
                            <th>Nombre</th>
                            <th>Empresa</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% CRUDProjects crudProjects = new CRUDProjects();
                           List<Project> projectList = crudProjects.listar();
                           Iterator<Project> itr = projectList.iterator();
                           while(itr.hasNext()) {
                               Project project = itr.next();
                        %>
                        <tr>
                            <td><%= project.getId_proyecto() %></td>
                            <td><%= project.getNombre() %></td>
                            <td><%= project.getId_empresa() %></td>
                            <!-- Para editar tomamos el valor ID_proyecto que se lo vamos a pasar al controlador -->
                            <td><a href="Projects?accion=edit&id=<%= project.getId_proyecto()%>"> Editar </a></td>
                        </tr>
                        <% } %>
                    </tbody>
                   
                </table>

            </div>

        </div>
    </body>
</html>
