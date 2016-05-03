<%-- 
    Document   : EntradaSalidaArticulos
    Created on : 25/03/2016, 07:39:54 PM
    Author     : esedecks
--%>

<%@page import="interfazrmi.MetodosRemotos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrada y salida de articulos</title>
          <style>
        table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: left;
        }
</style>
    </head>
    <body>
        <h1>Entrada y salida de articulos</h1>
        <div align ="center">
            <form action="Controlador" method="POST">

                Articulo:<br>
                <select onChange="jsFunction()" id="cmbElementos"name="cmbElementos">
                    <%
                        String articulos = ((MetodosRemotos)session.getAttribute("metodosRemotos")).leerArticulos();
                         String[] articulosArray=null; 
                        if(articulos.contains("noDatos")){
                            out.println("<option  value=\"noData\">No hay Articulos</option>");
                        }else{
                            articulosArray = articulos.split("\\r?\\n"); 
                            for(String articuloString : articulosArray){
                                articuloString = articuloString.replace('|',' '); 
                                out.println("<option  value=\""+articuloString +"\">"+articuloString+"</option>");
                            }
                        }
                        session.setAttribute("listaArticulos", articulosArray);
                    
                    %>
                </select> 
               <br>
              
               Seleccione el tipo de movimiento <br> <br> 
               <input type="radio" name="movimiento" value="entrada" checked="checked"> Entrada<br>
               <input type="radio" name="movimiento" value="salida" > Salida<br>
               <br>
               Cantidad: <br>
               <input type="text" name="cantidad"><br/>
               <input type="submit" name ="entradaSalidaArticulo" value="Hacer movimiento">
           </form>
                
            <hr>
            <table style="width:100%">
            <caption>Tabla de productos</caption>
            <tr>
                <th>Nombre del articulo</th>
                <th>Precio</th>
                <th>Existencias</th>
            </tr>
       <% 
        String[] listaArticulos =(String[]) session.getAttribute("listaArticulos"); 
        for(String articulo : listaArticulos){
            articulo = articulo.replace('|',' '); 

            //System.err.println("articulo :"+articulo); 

            String info = ((MetodosRemotos)session.getAttribute("metodosRemotos")).leerInfoArticulo(articulo);
            System.err.println("info :"+info); 
            String[] tokens = info.split("\\|"); 
            out.println("<tr>");
            out.println("<td>"+tokens[1]+"</td><td>"+tokens[2]+"</td><td>"+tokens[3] +"</td>"); 
            out.println("</tr>");  
        }
       %>
       </table>
        <ul >
            <li><a href ="bienvenido.jsp">Página de inicio.</a></li>
            <li><a href ="AdministracionArticulos.jsp"> Administración de articulos.</a></li>
            <li><a href ="EntradaSalidaArticulos.jsp"> Administación de entrada y salida de articulos.</a></li>
        </ul>
    </body>
</html>
