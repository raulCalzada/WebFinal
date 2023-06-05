<%-- 
    Document   : EditUser
    Created on : 12 may 2023, 22:38:26
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
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
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
  </head>
  <body>
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
    <div class="container">
      <h1>Editar Usuario sin terminar</h1>
      <form action="UserController">
      <table>
        <tr>
          <td>ID de Usuario:</td>
          <td><input type="text" id="nombre" name="txtId" value="<%= u.getId() %>" readonly=""></td>
        </tr>
        <tr>
          <td>Username:</td>
          <td><input type="text" id="nombre" name="txtUsername" value="<%= u.getUsername() %>"></td>
        </tr>
        <tr>
          <td>DNI:</td>
          <td><input type="text" id="nombre" name="txtDni" value="<%= u.getDni() %>"></td>
        </tr>
        <tr>
          <td>Nombre:</td>
          <td><input type="text" id="nombre" name="txtNombre" value="<%= u.getNombre() %>"></td>
        </tr>
        <tr>
          <td>Apellidos:</td>
          <td><input type="text" id="nombre" name="txtApellidos" value="<%= u.getApellidos() %>"></td>
        </tr>
        <tr>
          <td>Fecha de Alta(readonly):</td>
          <td><input type="text" id="nombre" name="txtFechaAlta" value="<%= u.getFecha_alta() %>" readonly=""></td>
        </tr>
        <tr>
          <td>Fecha de Baja(readonly):</td>
          <td><input type="text" id="nombre" name="txtFechaBaja" value="<%= u.getFecha_baja() %>" readonly=""></td>
        </tr>
        <tr>
          <td>Empresa(readonly):</td>
          <td><input type="text" id="nombre" name="txtEmpresa" value="<%= u.getEmpresa().getNombre_empresa() %>" readonly=""></td>
        </tr>
        <tr>
          <td>Proyecto(readonly):</td>
          <td><input type="text" id="nombre" name="txtProy" value="<%= u.getProyecto().getNombre() %>" readonly=""></td> 
        </tr>
        <input type="hidden" name="txtTipo" value="U">
      </table>
      <br>
      <div style="text-align: center;">
        <button type="submit" name="action" value="updateUser" class="button">Guardar Cambios</button>
      </div>
      </form>
    </div>
  </body>
</html>


