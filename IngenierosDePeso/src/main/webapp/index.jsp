<%-- 
    Document   : index
    Created on : 27 abr 2023, 12:13:11
    Author     : raulc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/Index.css">
        
    </head>
    <body class="back">
        <form action="UserController">
            <div class="header">
                <div class="cuadrado">
                    <div class="container"><img class="image" src="Public/logo.jpg"></div>
                    <p></p>
                    <p style="color: #EDFFD9">Correo</p>
                    <input type="USERNAME" placeholder="Email" name="txtUsername" >
                    <p style="color: #edffd8">Contraseña</p>
                    <input type="Password" placeholder="Password" name="txtPassword" >
                    <p></p>
                    <button class="btn" name="action" value="Login">Login</button>
                    <p></p>
                </div>
            </div>
        </form>    
    </body>
</html>

