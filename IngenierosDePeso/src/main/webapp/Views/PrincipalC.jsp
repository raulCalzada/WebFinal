<%-- 
    Document   : PrincipalC
    Created on : 27 abr 2023, 12:53:08
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
        <title>Cliente</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


    </head>
    <body>
        <style>
            .nav-link {
                color: #fff;
                font-weight: bold;
                transition: all 0.3s ease-out;
            }

            .nav-link:hover {
                color: #f2f2f2;
                transform: scale(1.1);

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
            <%
            CRUDUsers crudUsers= new CRUDUsers();
            String id;
            try{
                id = (String) request.getAttribute("idUser");
            }catch(Exception e){
                id = request.getParameter("txtId");
            }   
            User u = (User)crudUsers.list(id);
            %>
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
                <style>
                    /* Estilos CSS para la página */
                    body {
                    font-family: Arial, sans-serif;
                    background-color: #f2f2f2;
                    margin: 0;
                    padding: 0;
                }

                .container {
                    max-width: 800px;
                    margin: 0 auto;
                    padding: 20px;
                    background-color: #fff;
                    border-radius: 10px;
                    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
                }

                h1 {
                    font-size: 36px;
                    margin: 0;
                    text-align: center;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }

                th, td {
                    padding: 10px;
                    text-align: left;
                    border-bottom: 1px solid #ddd;
                }

                th {
                    background-color: #f2f2f2;
                }

                td:first-child {
                    font-weight: bold;
                }

                .button {
                    display: inline-block;
                    padding: 10px 20px;
                    background-color: #4CAF50;
                    color: #fff;
                    text-decoration: none;
                    border-radius: 5px;
                    transition: background-color 0.3s;
                }

                .button:hover {
                    background-color: #3e8e41;
                }
            </style>

            <div class="container">
                <h1>Página de Perfil</h1>
                <table>
                    <tr>
                        <td>ID de Usuario:</td>
                        <td><%=u.getId()%></td>
                    </tr>
                    <tr>
                        <td>Username:</td>
                        <td><%=u.getUsername()%></td>
                    </tr>
                    <tr>
                        <td>DNI:</td>
                        <td><%=u.getDni()%></td>
                    </tr>
                    <tr>
                        <td>Nombre:</td>
                        <td><%=u.getNombre()%></td>
                    </tr>
                    <tr>
                        <td>Apellidos:</td>
                        <td><%=u.getApellidos()%></td>
                    </tr>
                    <tr>
                        <td>Fecha de Alta:</td>
                        <td><%=u.getFecha_alta()%></td>
                    </tr>
                    <tr>
                        <td>Fecha de Baja:</td>
                        <td><%=u.getFecha_baja()%></td>
                    </tr>
                    <tr>
                        <td>Empresa:</td>
                        <td><%=u.getEmpresa().getNombre_empresa()%></td>
                    </tr>
                    <tr>
                        <td>Proyecto:</td>
                        <td><%=u.getProyecto().getNombre()%></td>
                    </tr>
                </table>
                <div style="text-align: center;">
                    <a href="UserController?action=edit&id=<%= u.getId()%>" class="button">Editar Perfil</a>
                </div>
            </div>
        </body>
    </html>

