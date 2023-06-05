# IngenierosDePeso

Este proyecto es una aplicación web que permite realizar seguimiento de marcajes de trabajadores y gestionar información relacionada con empresas, proyectos y trabajadores. 

## Requisitos previos

- Servidor Tomcat 10 instalado en la máquina virtual.
- Base de datos MySQL con puerto 3306 configurado.
- Datos de acceso a la base de datos a configurar en la clase Connect (si es necesario):
    - ¡¡¡¡Importante cambiar la contrasña de la BBDD!!!!
    
    - user = "root";
    - password = "Jager1234";
    - bd = "rrhh";
    - url = "jdbc:mysql://localhost:3306/";
    - driver = "com.mysql.cj.jdbc.Driver";

## Despliegue

1. Accede al archivo WAR ubicado en: `IngenierosDePeso\target\IngenierosDePeso-1.0-SNAPSHOT.war`.
2. Despliega el archivo WAR en tu servidor Tomcat.

## Configuración de la base de datos

- Asegúrate de tener una base de datos MySQL configurada.
- En el apartado de Estructura del proyecto, encontrarás las especificaciones para la configuración del SQL.
- Utiliza la contraseña especificada para iniciar la base de datos en tu entorno local.
- Ajusta los parámetros de localhost según tu configuración.

## Documentación

- El Javadoc se encuentra generado en la siguiente ubicación: `IngenierosDePeso\target\site\apidocs`.
- Para obtener información detallada sobre las clases, métodos y paquetes del proyecto, consulta el Javadoc.
