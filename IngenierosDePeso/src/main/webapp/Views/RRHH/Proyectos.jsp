<%-- 
    Document   : Proyectos
    Created on : 5 may 2023, 11:36:40
    Author     : raulc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@page import="Utils.CRUDProjects" %>
<%@page import="Model.Project" %>
<%@ page import="java.util.Iterator" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proyectos RRHH</title>
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
                background-color: #808080;
                color: #fff;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            a {
                color: #808080;
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
                background-color: #808080;
                color: #fff;
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
            }
            /* Estilos para el popup */
            .popup-overlay {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                display: flex;
                align-items: center;
                justify-content: center;
                z-index: 9999;
            }

            .popup-content {
                background-color: #fff;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                text-align: center;
                width: 300px;
                height: 200px;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <div class="square">
                <h1>Proyectos</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Numero proyecto</th>
                            <th>Nombre</th>
                            <th>Empresa</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% CRUDProjects crudProjects = new CRUDProjects();
                           List<Project> projectList = crudProjects.listar();
                           Iterator<Project> itr = projectList.iterator();
                           while(itr.hasNext()) {
                               Project project = itr.next();
                        %>
                        <tr>
                            <td><%= project.getId_proyecto() %></td>
                            <td><%= project.getNombre() %></td>
                            <td><%= project.getId_empresa() %></td>
                            <!-- Para editar tomamos el valor ID_proyecto que se lo vamos a pasar al controlador -->
                            <td>
                                <a href="Projects?action=edit&id=<%= project.getId_proyecto()%>"> Editar </a>
                                <a href="#" onclick="mostrarPopup('<%= project.getId_proyecto() %>', '<%= project.getNombre() %>')">Generar Informe</a></td>
                        </tr>
                        <% } %>
                    </tbody>

                </table>

                <div class="button-container">
                    <a href="Projects?action=menu">Volver al Menú</a>
                </div>
            </div>
        </div>
        <script>
            function mostrarPopup(idProyecto, nombre) {
                var popup = document.getElementById('popup');
                var popupHeader = document.getElementById('popupHeader');
                popupHeader.textContent = 'Contenido del informe para el proyecto: ' + nombre;
                popup.style.display = 'flex';
                idProyectoInput.value = idProyecto;
            }

        </script>        

        <!-- Popup -->
        <div id="popup" class="popup-overlay" style="display: none;">
            <form action="Projects">
                <div class="popup-content">
                    <header id="popupHeader"></header>
                    <br>
                    <br>
                    <input type="hidden" name="idProyecto" value="" id="idProyectoInput">
                    <div class="form-group">
                        <label for="fecha">Desde:</label>
                        <input type="datetime-local" id="fecha" name="txtFechaDesde" class="form-control" required>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="fecha">Hasta:</label>
                        <input type="datetime-local" id="fecha" name="txtFechaHasta" class="form-control" required>
                    </div>
                    <button type="submit" class="btn" name="action" value="informe">Generar Informe</button>
                </div>
            </form>
        </div>
    </body>
</html>
