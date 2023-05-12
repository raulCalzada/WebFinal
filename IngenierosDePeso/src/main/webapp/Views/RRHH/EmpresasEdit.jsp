<%-- 
    Document   : EmpresasEdit
    Created on : 10 may 2023, 12:45:39
    Author     : raulc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="Utils.CRUDEmpresas" %>
<%@page import="Model.Empresa" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  CRUDEmpresas crudEmpresas = new CRUDEmpresas();
            String id = (String) request.getAttribute("idempres");
            Empresa e = (Empresa)crudEmpresas.list(id);
        %>
        <h1>Modificar proyecto</h1>
        <form action="Empresas">
            Nombre de la Empresa:<br><!-- comment -->
            <input type="text" name="txtNameEmpres" value="<%= e.getNombre_empresa() %>">  
            <input type="hidden" name="txtId" value="<%= e.getId_empresa() %>"> 
            <button type="submit" name="action" value="update"> </button>>
            <a href="Empresas?action=listar">Volver</a>
        </form> 
    </body>
</html>

