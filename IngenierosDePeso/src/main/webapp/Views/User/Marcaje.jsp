<%-- 
    Document   : Marcaje
    Created on : 12 may 2023, 22:38:40
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
        <title>Marcaje</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <%  CRUDUsers crudUsers= new CRUDUsers();
            String id = (String) request.getAttribute("idUser");
            User u = (User)crudUsers.list(id);
        %>
        <style>
            .nav-link {
                color: #fff;
                font-weight: bold;
                transition: all 0.3s ease-out;
            }
            .nav-link:hover {
                color: #f2f2f2;
                transform: scale(1.1);
            }
            .navbar-brand {
                color: #fff;
                font-weight: bold;
                transition: all 0.3s ease-out;
            }
            .navbar-brand:hover {
                color: #f2f2f2;
                transform: scale(1.1);
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-dark">
            <a style="color: white" href="UserController?action=personal&id=<%= u.getId()%>" class="navbar-brand" href="#">Cliente</a>
            <ul class="navbar-nav mr-auto">
                <!-- Aquí van los elementos del navbar a la izquierda -->
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link mx-auto" href="UserController?action=marcaje&id=<%= u.getId()%>">Realizar Marcaje</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link mx-auto" href="UserController?action=seeMarcaje&id=<%= u.getId()%>">Ver Marcajes</a>
                </li>
            </ul>
            <div class="dropdown ml-auto">
                <a style="color:white" href="../../index.jsp" class="nav-link dropdown-toggle" data-toggle="dropdown">Log out </a>
            </div>
        </nav>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <h2 class="mb-4">Marcaje</h2>
                    <form action="UserController">
                        <div class="form-group">
                            <label for="fecha">Fecha:</label>
                            <input type="datetime-local" id="fecha" name="txtFecha" class="form-control" step="1" required>
                        </div>
                        <div class="form-group">
                            <label for="tipo_marcaje">Tipo de marcaje:</label>
                            <select id="tipo_marcaje" name="txtTipoMarcaje" class="form-control" required>
                                <option value="E">Entrada</option>
                                <option value="S">Salida</option>
                            </select>
                        </div>
                        <input type="hidden" name="txtId" value="<%= u.getId()%>">
                        
                        <button type="submit" name="action" value="addMarcaje" class="btn btn-primary">Registrar marcaje</button>
                    </form>
                </div>
            </div>

    </body>
</html>

