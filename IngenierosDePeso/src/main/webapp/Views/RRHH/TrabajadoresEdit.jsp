<%-- 
    Document   : TrabajadoresEdit
    Created on : 10 may 2023, 12:45:26
    Author     : raulc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="Utils.CRUDUsers" %>
<%@page import="Model.Project" %>
<%@page import="Model.User" %>
<%@page import="Model.Empresa" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  CRUDUsers crudUsers= new CRUDUsers();
            String id = (String) request.getAttribute("idUser");
            User u = (User)crudUsers.list(id);
        %>
        <h1>Modificar Usuario</h1>
        <form action="Users">
            UserName:<br><!-- comment -->
            <input type="text" name="txtUsername" value="<%= u.getUsername() %>"> 
            Nombre:<br><!-- comment -->
            <input type="text" name="txtNombre" value="<%= u.getNombre() %>"> 
            Apellidos:<br><!-- comment -->
            <input type="text" name="txtApellidos" value="<%= u.getApellidos() %>"> 
            DNI:<br><!-- comment -->
            <input type="text" name="txtDni" value="<%= u.getDni() %>"> 
            Fecha Alta:<br><!-- comment -->
            <input type="text" name="txtFechaAlta" value="<%= u.getFecha_alta() %>"> 
            Fecha Baja:<br><!-- comment -->
            <input type="text" name="txtFechaBaja" value="<%= u.getFecha_baja() %>"> 
            
            Proyecto:<br><!-- comment -->
            <input type="text" name="txtProy" value="<%= u.getProyecto().getNombre() %>"> 
            Empresa:<br><!-- comment -->
            <input type="text" value="<%= u.getEmpresa().getNombre_empresa() %>"readonly> 
            <input type="hidden" name="txtId" value="<%= u.getId() %>"> 
            <input type="hidden" name="txtTipo" value="<%= u.getTipo() %>"> 
            <button type="submit" name="action" value="updateUser"> </button>>
            <a href="Users?action=listar">Volver</a>
        </form> 
    </body>
</html>
