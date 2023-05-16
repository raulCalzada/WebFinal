<%-- 
    Document   : Marcajes
    Created on : 16 may 2023, 12:45:51
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
            String id = (String) request.getAttribute("idUser");
            User u = (User)crudUsers.list(id);
        %>
    </head>
    <body>
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

