/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import interfazrmi.OperacionesRemota;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author esedecks
 */
public class Controlador extends HttpServlet {
    OperacionesRemota metodosRemotos ; 
    HttpServletRequest request; 
    HttpServletResponse response; 
    HttpSession sesion; 
    String mensajeError;
    String tituloError; 
    String imagen = "grafica.png"; 
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request ; 
        this.response = response; 
        this.sesion = request.getSession(); 
        metodosRemotos = (OperacionesRemota)sesion.getAttribute("metodosRemotos"); 
        //recibir el nombre del boton que fue presionado
        try{
            if(request.getParameter("dosOperandos")!=null){
                atenderUnOperando();
            }
            else if(request.getParameter("unOperando")!=null){
                atenderDosOperandos();
            }
            else if(request.getParameter("listaNumeros")!=null){
                atenderLista();
            }
            else{
                
            }
            response.sendRedirect("Operaciones.jsp");
        }catch(RemoteException ex){
            ex.printStackTrace();
        }
    }
    
    public void atenderUnOperando() throws RemoteException{
        String operandoA = request.getParameter("operandoA"); 
        String operandoB = request.getParameter("operandoB"); 
        double da = Double.parseDouble(operandoA); 
        double db = Double.parseDouble(operandoB); 
        sesion.setAttribute("opA",  da );
        sesion.setAttribute("opB",  db);

        sesion.setAttribute("suma",  metodosRemotos.suma(da, db) );
        sesion.setAttribute("resta",  metodosRemotos.resta(da, db) );
        sesion.setAttribute("multiplicacion", metodosRemotos.multiplica(da, db));
        sesion.setAttribute("division", metodosRemotos.divide(da, db));
        sesion.setAttribute("modulo", metodosRemotos.modulo(da, db));
    }
    public void atenderDosOperandos() throws RemoteException{
        String operandoA = request.getParameter("operandoA"); 
        String operandoB = request.getParameter("exponente"); 
        double da = Double.parseDouble(operandoA); 
        double db = Double.parseDouble(operandoB); 
        sesion.setAttribute("opA",  da );
        sesion.setAttribute("opB",  db);

        sesion.setAttribute("potencia",  metodosRemotos.potencia(da, db) );
        sesion.setAttribute("factorial",  metodosRemotos.factorial(da) );
       
    }
    public void atenderLista() throws RemoteException{
        String lista = request.getParameter("lista");
        lista = lista.trim(); 
        System.out.print("-----<"+lista);
        
        String [] numeros = lista.split(",");
            double[] numerosDouble = new double[numeros.length];
            int i = 0;
            for(String nume : numeros){
                numerosDouble[i] = Double.parseDouble(nume); 
                i++;
            }
        sesion.setAttribute("lista", lista );
       

        sesion.setAttribute("promedio",  metodosRemotos.promedio(numerosDouble) );
        sesion.setAttribute("maximo",  metodosRemotos.maximo(numerosDouble) );
       sesion.setAttribute("minimo",  metodosRemotos.minimo(numerosDouble) );
       sesion.setAttribute("desviacion",  metodosRemotos.desviacion(numerosDouble) );
       
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
   
    
}
