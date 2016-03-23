<%-- 
    Document   : bienvenido
    Created on : 21/03/2016, 09:05:28 PM
    Author     : esedecks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenido <% out.print(session.getAttribute("usuarioName")); %>!</h1>
    </body>
</html>
