<%-- 
    Document   : Grafica
    Created on : 25/03/2016, 07:50:13 PM
    Author     : esedecks
--%>

<%@page import="java.io.File"%>
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="java.rmi.RemoteException"%>
<%@page import="interfazrmi.MetodosRemotos"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style> 
        .center {
            margin: auto;
            width: 60%;
            border: 3px solid #73AD21;
            padding: 10px;
            }
        </style>
        
    </head>
    <body>
        <h1>Gráfica de movimientos de entrada y salida de articulos.</h1>
        <%
            MetodosRemotos metodosRemotos = (MetodosRemotos)session.getAttribute("metodosRemotos"); 
            DefaultPieDataset data = new DefaultPieDataset();
            try { 
                String datos = metodosRemotos.leerInfoForChart();
                String[] lines = datos.split("\\r?\\n"); 
            for(String line : lines){
                String[] tokens  = line.split("\\|"); 
                int movimientos = Integer.parseInt(tokens[1].trim()); 
                data.setValue(tokens[0]+" tiene "+movimientos+" movimientos",movimientos);
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
       
        JFreeChart chart = ChartFactory.createPieChart(
        "Gráfica de movimientos",data,true,true,true);
        String imagen = "grafica.png"; 
        String path = getServletContext().getRealPath("/"); 
        System.err.println("El path es: "+path); 
        File f = new File(path+imagen);
        ChartUtilities.saveChartAsPNG(f, chart, 600, 400);
        out.println("<div class=\"center\" >");
        out.println("<img src =\" "+imagen +"  \" align=\"middle\" >  "); 
        out.println("</div>"); 
        %>
        <hr>
        <h2>Enviar a correo gráfica</h2>
        <p>Confirme su dirección de correo electrónico<br>
        Si lo desea puede cambiar la dirección de correo electrónico.
        </p>
         <form action="Controlador" method="POST">
             Correo: <input type ="text" name="correoUsuario" value="<% out.print(session.getAttribute("correoUsuario"));   %> ">
             <input type="submit" name="btnEnviarGrafica" value="Enviar a mi correo"/>
        </form>
             
    </body>
</html>
