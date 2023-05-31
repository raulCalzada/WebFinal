<%-- 
    Document   : PrincipalRRHH
    Created on : 1 may 2023, 16:50:59
    Author     : raulc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Utils.CRUDUsers" %>
<%@page import="Model.User" %>
<!DOCTYPE html>
<html>
    <%
            CRUDUsers crudUsers= new CRUDUsers();
            String id = null; // Declarar la variable y asignarle un valor predeterminado
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
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>RRHH - Recursos Humanos</title>
        <style>
            body {
                background: linear-gradient(to bottom, #286DA8, #CCCCCC);
                font-family: Arial, sans-serif;
                text-align: center;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #f5f5f5;
            }

            .container {
                width: 400px;
                height: 400px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                padding: 20px;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }
            .container-title {
                color: #333;
                margin-top: 0;
            }


            h1 {
                color: #333;
                margin-top: 0;
            }

            .button {
                display: inline-block;
                padding: 10px 20px;
                margin: 10px;
                background-color: #007bff;
                color: #fff;
                text-decoration: none;
                border-radius: 4px;
                font-size: 16px;
                transition: background-color 0.3s ease;
            }

            .button:hover {
                background-color: #0056b3;
            }
            .buttonred {
                display: inline-block;
                padding: 10px 20px;
                margin: 10px;
                background-color: red;
                color: #fff;
                text-decoration: none;
                border-radius: 4px;
                font-size: 16px;
                transition: background-color 0.3s ease;
            }

            .buttonred:hover {
                background-color: #0056b3;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <h1 class="container-title">Recursos Humanos</h1>
            <br>
            <a href="Projects?action=listar" class="button" >Proyectos</a>
            <a href="Empresas?action=listar" class="button" >Empresas</a>
            <a href="Users?action=listar" class="button">Trabajadores</a>
            <a href="UserController?action=logout&id=<%= u.getId()%>" class="buttonred">Salir</a>
        </div>

    </body>
</html>
