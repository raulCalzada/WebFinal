<%-- 
    Document   : Trabajadores
    Created on : 5 may 2023, 15:08:03
    Author     : raulc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="Utils.CRUDUsers" %>
<%@page import="Model.Project" %>
<%@page import="Model.User" %>
<%@page import="Model.Empresa" %>
<%@ page import="java.util.Iterator" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proyectos RRHH</title>
        <link rel="stylesheet" href="../css/RRHH.css">
    </head>
    <body>
        <div class="header">
            <div class="square">
                <h1>Usuarios</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Username</th>
                            <th>DNI</th>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Fecha_alta</th>
                            <th>Fecha_baja</th>
                            <th>Empresa</th>
                            <th>Proyecto</th>
                            <th>Editar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% CRUDUsers crudUsers= new CRUDUsers();
                           List<User> userList = crudUsers.listar();
                           Iterator<User> itr = userList.iterator();
                           while(itr.hasNext()) {
                               User u = itr.next();
                               if (u.getTipo().equals("U")){
                        %>
                        <tr>
                            <td><%= u.getId() %></td>
                            <td><%= u.getUsername() %></td>
                            <td><%= u.getDni() %></td>
                            <td><%= u.getNombre() %></td>
                            <td><%= u.getApellidos() %></td>
                            <td><%= u.getFecha_alta() %></td>
                            <td><%= u.getFecha_baja() %></td>
                            <td><%= u.getEmpresa().getNombre_empresa() %></td>
                            <td><%= u.getProyecto().getNombre() %></td>
                            <!-- Para editar tomamos el valor ID_proyecto que se lo vamos a pasar al controlador -->
                            <td><a href="Users?action=editUser&id=<%= u.getId()%>"> Editar </a></td>
                        </tr>
                        <% }} %>
                    </tbody>
                   
                </table>

            </div>

        </div>
    </body>
</html>

