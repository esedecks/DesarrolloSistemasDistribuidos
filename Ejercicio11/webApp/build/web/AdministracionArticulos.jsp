<%-- 
    Document   : AdministracionArticulos
    Created on : 25/03/2016, 07:18:57 PM
    Author     : esedecks
--%>

<%@page import="interfazrmi.MetodosRemotos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de articulos</title>
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
          <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    </head>
    <body>
        <h1>Bienvenido a la administración de los articulos</h1>
        <hr />
        <h2 align ="center">Agregar articulo</h2>
        <div align ="center">
            <form action="Controlador" method="POST">
                Descripción: <br>
                <input type="text" name="descripcion"><br>
                Precio:<br>
                 <input type="text" name="precio"><br/>
                 Existencias<br>
                 <input type="text" name="existencias"><br/>
                 <input type="submit" name="agregarArticulo" value="Agregar">
            </form>
        </div>
        <br/><br>
        <hr  width="50%"/>
        <br/><br>
         <h2 align="center">Eliminar o actualizar articulo</h2>
        <div align ="center">
        
            <form action="Controlador" method="POST" >
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
               <input type="submit"name="eliminarArticulo"  value="Eliminar"><br><br>
               Descripción: <br>
                <input type="text" readonly name="descripcionActualizar" id="descripcionActualizar"><br>
                
                Precio:<br>
                 <input type="text" name="precioActualizar"><br/>
                 Existencias<br>
                 <input type="text" name="existenciasActualizar"><br/>
               <input type="submit" name="actualizarArticulo" value="Actualizar">
           </form>
        </div>
        <div></div>
        <script type="text/javascript">
           function jsFunction(){
           var myselect = document.getElementById("cmbElementos");
           //alert(myselect.options[myselect.selectedIndex].value);
           
            }
            var myselect = document.getElementById("cmbElementos");
           
            $("#cmbElementos").click(function(){
            $("#descripcionActualizar").val(myselect.options[myselect.selectedIndex].value);
            
            });
        </script>
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

            System.err.println("articulo :"+articulo); 

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
