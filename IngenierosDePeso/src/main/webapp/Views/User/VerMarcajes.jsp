<%-- 
    Document   : VerMarcajes
    Created on : 16 may 2023, 11:31:39
    Author     : raulc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="Utils.CRUDUsers" %>
<%@page import="Model.Project" %>
<%@page import="Model.User" %>
<%@page import="Model.Marcaje" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Marcajes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <%  CRUDUsers crudUsers= new CRUDUsers();
            String id = "idNoEncontrada";
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("idUser")) {
                        id = cookie.getValue();
                        break; // Si se encuentra la cookie, se asigna el valor y se sale del bucle
                    }
                }
            }
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
                <a style="color:white" href="UserController?action=logout&id=<%= u.getId()%>" class="nav-link dropdown-toggle" data-toggle="dropdown">Log out </a>
            </div>
        </nav>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <h2 class="mb-4">Lista de Marcajes</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Fecha</th>
                                <th>Tipo de Marcaje</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% crudUsers= new CRUDUsers();
                           List<Marcaje> marcList = crudUsers.listarMarcajes(id);
                           Iterator<Marcaje> itr = marcList.iterator();
                           while(itr.hasNext()) {
                               Marcaje m = itr.next();  
                        %>
                            <tr>
                                <td><%= m.getFecha()%></td>
                                <td><%= m.getTipo_marcaje()%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </body>
</html>
