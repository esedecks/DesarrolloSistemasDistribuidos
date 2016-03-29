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
        <hr />
        <h2 >Administración de articulos</h2>
        <p> Si desea administrar los articulos,
            por favor de click <a href="AdministracionArticulos.jsp">Administrar Articulos </a>
            
        </p> 
        <h2 >Entrada y salida de articulos</h2>
        <p> Si desea ingresar o dar salida a  articulos,
            por favor de click <a href="EntradaSalidaArticulos.jsp">Entrada o salida de Articulos </a>
            
        </p> 
        <h2 >Ver gráfica de movimientos</h2>
        <p> Si desea ver la gráfica de movimientos,
            por favor de click <a href="Grafica.jsp">Gráfica de movimientos de entrada y salida de Articulos </a>
            
        </p>  
    </body>
</html>
