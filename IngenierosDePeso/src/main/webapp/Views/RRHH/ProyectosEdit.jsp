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
    </head>
    <body>
        <%  CRUDProjects crudProjects = new CRUDProjects();
            String id = (String) request.getAttribute("idproj");
            Project project = (Project)crudProjects.list(id);
        %>
        <h1>Modificar proyecto</h1>
        <form action="Projects">
            Nombre:<br><!-- comment -->
            <input type="text" name="txtNameProyect" value="<%= project.getNombre() %>"> 
            Empresa:<br><!-- comment -->
            <input type="text" name="txtEmpresaProyect" value="<%= project.getId_empresa() %>"> 
            <input type="hidden" name="txtId" value="<%= project.getId_proyecto() %>"> 
            <button type="submit" name="action" value="update"> </button>>
            <a href="Projects?action=listar">Volver</a>
        </form> 
    </body>
</html>
