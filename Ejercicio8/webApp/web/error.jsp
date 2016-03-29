<%-- 
    Document   : error
    Created on : 21/03/2016, 09:06:16 PM
    Author     : esedecks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje</title>
    </head>
    <body>
        <h1><% out.print(session.getAttribute("tituloError")); %></h1>
        <p>Mensaje: <% out.print(session.getAttribute("mensajeError")); %></p>
        <p>Ir a las siguientes páginas</p>
        <ul >
            <li><a href ="bienvenido.jsp">Página de inicio.</a></li>
            <li><a href ="AdministracionArticulos.jsp"> Administración de articulos.</a></li>
            <li><a href ="EntradaSalidaArticulos.jsp"> Administación de entrada y salida de articulos.</a></li>
        </ul>
    </body>
</html>
