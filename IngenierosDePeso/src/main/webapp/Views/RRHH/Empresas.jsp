<%-- 
    Document   : Empresas
    Created on : 5 may 2023, 15:07:20
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
        <title>Empresas RRHH</title>
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
                color: #fff;
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
                background-color: #DC143C;
                color: #fff;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            a {
                color: #DC143C;
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
                background-color: #DC143C;
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
                <h1 style="color: #000;">Empresas RRHH</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% CRUDEmpresas crudEmpresas = new CRUDEmpresas();
                           List<Empresa> empresaList = crudEmpresas.listar();
                           for (Empresa e : empresaList) {
                        %>
                        <tr>
                            <td><%= e.getNombre_empresa() %></td>
                            <td><a href="Empresas?action=edit&id=<%= e.getId_empresa()%>">Editar</a></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <div class="button-container">
                    <a href="Empresas?action=menu">Volver al Menú</a>
                </div>
            </div>
        </div>
    </body>
</html>
