<%-- 
    Document   : index
    Created on : 21/03/2016, 05:22:53 PM
    Author     : esedecks
--%>

<%@page import="interfazrmi.OperacionesRemota"%>
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
                 OperacionesRemota op = (OperacionesRemota) registro.lookup("rmiServer");
                session.setAttribute("metodosRemotos", op);
            }catch(Exception ex){
            ex.printStackTrace();
            }
            %>
        <h1>Operaciones!</h1>
        <form action="Controlador" method="POST">
            Operando A<br>
            <input type="text" name="operandoA"><br>
            Operando B<br>
            <input type="text" name="operandoB"><br/>
            <input type="submit" name ="dosOperandos" value="Calcular"> <br/><br/>
            operando a: <% if(session.getAttribute("opA")!=null) out.println((double)session.getAttribute("opA"));else out.println("0.0");  %><br>
            operando b: <% if(session.getAttribute("opB")!=null) out.println((double)session.getAttribute("opB"));else out.println("0.0");  %><br>

            suma           <input type="text"  value="<% if(session.getAttribute("suma")!=null) out.println((double)session.getAttribute("suma"));else out.println("0.0");  %>"> <br>
            resta          <input type="text" value="<% if(session.getAttribute("resta")!=null) out.println((double)session.getAttribute("resta"));else out.println("0.0");  %>"><br>
            multiplicación <input type="text" value="<% if(session.getAttribute("multiplicacion")!=null) out.println((double)session.getAttribute("multiplicacion"));else out.println("0.0");  %>"><br>
            división       <input type="text" value="<% if(session.getAttribute("division")!=null) out.println((double)session.getAttribute("division"));else out.println("0.0");  %>"><br>
            módulo         <input type="text" value="<% if(session.getAttribute("modulo")!=null) out.println((double)session.getAttribute("modulo"));else out.println("0.0");  %>"><br>
            
        </form> 
        <hr>
         <form action="Controlador" method="POST">
            Operando A<br>
            <input type="text" name="operandoA"><br>
            exponente <br>
            <input type="text" name="exponente"><br/>
            <input type="submit" name ="unOperando" value="Calcular"> <br/><br/>
            operando a: <% if(session.getAttribute("opA")!=null) out.println((double)session.getAttribute("opA"));else out.println("0.0");  %><br>
            exponente: <% if(session.getAttribute("opB")!=null) out.println((double)session.getAttribute("opB"));else out.println("0.0");  %><br>

            potencial           <input type="text"  value="<% if(session.getAttribute("potencia")!=null) out.println((double)session.getAttribute("potencia"));else out.println("0.0");  %>"> <br>
            factorial          <input type="text" value="<% if(session.getAttribute("factorial")!=null) out.println((double)session.getAttribute("factorial"));else out.println("0.0");  %>"><br>
           
        </form> 
        <hr>
        <hr>
         <form action="Controlador" method="POST">
            Lista de números <br>
            <input type="text" name="lista"><br>
            
            <input type="submit" name ="listaNumeros" value="Calcular"> <br/><br/>
            lista de números <% if(session.getAttribute("lista")!=null) out.println((String)session.getAttribute("lista"));else out.println("0.0");  %><br>
           
            promedio           <input type="text"  value="<% if(session.getAttribute("promedio")!=null) out.println((double)session.getAttribute("promedio"));else out.println("0.0");  %>"> <br>
            máximo           <input type="text" value="<% if(session.getAttribute("maximo")!=null) out.println((double)session.getAttribute("maximo"));else out.println("0.0");  %>"><br>
            mínimo           <input type="text" value="<% if(session.getAttribute("minimo")!=null) out.println((double)session.getAttribute("minimo"));else out.println("0.0");  %>"><br>
            desviación estándar           <input type="text" value="<% if(session.getAttribute("desviacion")!=null) out.println((double)session.getAttribute("desviacion"));else out.println("0.0");  %>"><br>
           
        </form>
        
        
        
    </body>
</html>
