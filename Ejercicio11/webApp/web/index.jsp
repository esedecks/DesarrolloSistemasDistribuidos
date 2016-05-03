<%-- 
    Document   : index
    Created on : 21/03/2016, 05:22:53 PM
    Author     : esedecks
--%>

<%@page import="interfazrmi.MetodosRemotos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.rmi.registry.Registry"%>
<%@ page import="java.rmi.registry.LocateRegistry"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrar al sistema</title>
    </head>
    <body>
         <%
            try{
                Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099); 
                MetodosRemotos metodosRemotos = (MetodosRemotos) registro.lookup("rmiServer");
                session.setAttribute("metodosRemotos", metodosRemotos);
            }catch(Exception ex){
            ex.printStackTrace();
            }
            %>
        <h1>Bienvenido!</h1>
        <form action="Controlador" method="POST">
            Usuario:<br>
            <input type="text" name="usuario"><br>
            Contraseña:<br>
            <input type="password" name="password"><br/>
            <input type="submit" name ="login" value="Log in">
        </form> 
    </body>
</html>
