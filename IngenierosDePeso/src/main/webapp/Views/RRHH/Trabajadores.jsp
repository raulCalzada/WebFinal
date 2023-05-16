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
        <title>Usuarios RRHH</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
            }

            .header {
                background-color: #f5f5f5;
                padding: 20px;
            }

            .header h1 {
                color: #000;
                font-size: 28px;
                margin: 0;
                text-align: center;
            }

            .square {
                background-color: #f5f5f5;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                padding: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th,
            td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ccc;
            }

            th {
                background-color: #006400;
                color: #fff;
            }

            a {
                color: #006400;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }
            .button-container {
                text-align: center;
                margin-top: 20px;
            }

            .button-container a {
                display: inline-block;
                background-color: #006400;
                color: #fff;
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <div class="square">
                <h1>Usuarios RRHH</h1>
                <table>
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
                            <th>Marcajes</th>
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
                            <td><a href="Users?action=seeMarcaje&id=<%= u.getId()%>"> Ver Marcajes </a></td>
                        </tr>
                        <% }} %>
                    </tbody>

                </table>
                <div class="button-container">
                    <a href="Users?action=menu">Volver al Menú</a>
                </div>

            </div>

        </div>
    </body>
</html>

