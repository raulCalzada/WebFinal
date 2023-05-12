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
                            <th>Nombre</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% CRUDEmpresas crudEmpresas = new CRUDEmpresas();
                           List<Empresa> empresList = crudEmpresas.listar();
                           Iterator<Empresa> itr = empresList.iterator();
                           while(itr.hasNext()) {
                               Empresa e = itr.next();
                        %>
                        <tr>
                            <td><%= e.getNombre_empresa() %></td>
                            <!-- Para editar tomamos el valor ID_proyecto que se lo vamos a pasar al controlador -->
                            <td><a href="Empresas?action=edit&id=<%= e.getId_empresa()%>"> Editar </a></td>
                        </tr>
                        <% } %>
                    </tbody>
                   
                </table>

            </div>

        </div>
    </body>
</html>
