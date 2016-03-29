/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import interfazrmi.MetodosRemotos;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


/**
 *
 * @author esedecks
 */
public class Controlador extends HttpServlet {
    MetodosRemotos metodosRemotos ; 
    HttpServletRequest request; 
    HttpServletResponse response; 
    HttpSession sesion; 
    String mensajeError;
    String tituloError; 
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
        metodosRemotos = (MetodosRemotos)sesion.getAttribute("metodosRemotos"); 
        //recibir el nombre del boton que fue presionado
        try{
            if(request.getParameter("login")!=null){
                atenderLogin();
            }
            else if(request.getParameter("agregarArticulo")!=null){
                atenderAgregarArticulo();
            }
            else if(request.getParameter("eliminarArticulo")!=null){
                atenderEliminarArticulo();
            }else if(request.getParameter("actualizarArticulo")!=null){
                atenderActualizarArticulo();

            }else if(request.getParameter("entradaSalidaArticulo")!=null){
                atenderEntradaSalidaArticulo();
            }
            else{
        
            }
        }catch(RemoteException ex){
            ex.printStackTrace();
        }
    }
    private void atenderEntradaSalidaArticulo() throws RemoteException, IOException{
        String nombreArticulo = request.getParameter("cmbElementos"); 
        String tipoMovimiento = request.getParameter("movimiento"); 
        String cantidad = request.getParameter("cantidad"); 
        if(tipoMovimiento.trim().equals("entrada")){
            tipoMovimiento = "entrada"; 
            boolean res = metodosRemotos.realizarMovimiento(nombreArticulo, tipoMovimiento, cantidad); 
            if(res){
                tituloError ="Se realizó la entrada de articulos correctamente"; 
                sesion.setAttribute("tituloError", tituloError);
                mensajeError="Se agregaron "+cantidad + "pieza(s) al producto "+nombreArticulo; 
                sesion.setAttribute("mensajeError", mensajeError);
                response.sendRedirect("error.jsp");
            }
            else{
                tituloError ="No se pudo realizar la entrada de articulos"; 
                sesion.setAttribute("tituloError", tituloError);
                mensajeError="NO se agregaron "+cantidad + "pieza(s) al producto "+nombreArticulo; 
                sesion.setAttribute("mensajeError", mensajeError);
                response.sendRedirect("error.jsp");

            }
        }else{
                tipoMovimiento = "salida";
                // si se requiere hacer salida hay que ver que sea posible 
                // leer la cantidad actual
                String existentes = metodosRemotos.leerNoExistencias(nombreArticulo).replace('|',' '); 
                existentes = existentes.substring(0, existentes.indexOf(' '));
                int piezasExistentes = Integer.parseInt(existentes); 
                int cantidadSolicitadas = Integer.parseInt(cantidad); 
                if(cantidadSolicitadas > piezasExistentes){
                    //no se puede hacer la trbasaccion se salida
                    tituloError ="No se pudo realizar la salida de articulos"; 
                    sesion.setAttribute("tituloError", tituloError);
                    mensajeError="No se puede hacer la salida hay una solicitud de piezas mayor a las existentes"; 
                    sesion.setAttribute("mensajeError", mensajeError);
                    response.sendRedirect("error.jsp");
                    return ; 
                }else{
                    boolean res= metodosRemotos.realizarMovimiento(nombreArticulo, tipoMovimiento, cantidad); 
                    if(res){
                        //no se puede hacer la trbasaccion se salida
                        tituloError ="Se dieron de baja los articulos"; 
                        sesion.setAttribute("tituloError", tituloError);
                        mensajeError="Se dieron de baja "+cantidad + "pieza(s) al producto "+nombreArticulo; 
                        sesion.setAttribute("mensajeError", mensajeError);
                        response.sendRedirect("error.jsp");
                    }else{
                        tituloError ="No se  dieron de baja los articulos"; 
                        sesion.setAttribute("tituloError", tituloError);
                        mensajeError="NO se pudo dar de baja "+cantidad + "pieza(s) al producto "+nombreArticulo; 
                        sesion.setAttribute("mensajeError", mensajeError);
                        response.sendRedirect("error.jsp");
                    }
                }
            }//fin if
    }
    private void atenderActualizarArticulo() throws RemoteException, IOException{
        String descripcion = request.getParameter("descripcionActualizar"); 
        //System.err.println(descripcion+"......"); 
        String existencias = request.getParameter("existenciasActualizar"); 
        String precio = request.getParameter("precioActualizar"); 
        boolean res =  metodosRemotos.actualizarArticulo(descripcion, existencias, precio,descripcion);
        if(res){
            tituloError ="Se actualizó correctamente el articulo"; 
            sesion.setAttribute("tituloError", tituloError);
            mensajeError="Se actualizo el articulo "+descripcion+ " con "+existencias +" existencia(s)"+ "y con un precio de $"+precio+" pesos"; 
            sesion.setAttribute("mensajeError", mensajeError);
            response.sendRedirect("error.jsp");
        }else{
            tituloError ="No se pudo actualizar correctamente el articulo"; 
            sesion.setAttribute("tituloError", tituloError);
            mensajeError="Ocurrió une error en el servidor, inténte más tarde"; 
            sesion.setAttribute("mensajeError", mensajeError);
            response.sendRedirect("error.jsp");
        }
    
    }
    private void atenderAgregarArticulo() throws RemoteException,IOException{
        String descripcion = request.getParameter("descripcion"); 
        String existencias = request.getParameter("existencias"); 
        String precio = request.getParameter("precio"); 
        boolean res =  metodosRemotos.insertarProducto(descripcion, existencias, precio);
        if(res){
            tituloError ="Se insertó correctamente el articulo"; 
            sesion.setAttribute("tituloError", tituloError);
            mensajeError="Se insertó el articulo "+descripcion+ " con "+existencias +" existencia(s)"+ "y con un precio de $"+precio+" pesos"; 
            sesion.setAttribute("mensajeError", mensajeError);
            response.sendRedirect("error.jsp");
        }else{
            tituloError ="No se pudo insertar correctamente el articulo"; 
            sesion.setAttribute("tituloError", tituloError);
            mensajeError="Ocurrió une rror en el servidor, inténte más tarde"; 
            sesion.setAttribute("mensajeError", mensajeError);
            response.sendRedirect("error.jsp");
        }

    }
    private void atenderEliminarArticulo() throws RemoteException,IOException{
        String elemento = request.getParameter("cmbElementos"); 
        System.err.println("Elemento "+elemento); 
        boolean res =  metodosRemotos.eliminarArticulo(elemento);
        if(res){
            tituloError ="Se eliminó correctamente el articulo"; 
            sesion.setAttribute("tituloError", tituloError);
            mensajeError="Se eliminó el articulo "+elemento; 
            sesion.setAttribute("mensajeError", mensajeError);
            response.sendRedirect("error.jsp");
        }else{
            tituloError ="No se pudo eliminar correctamente el articulo"; 
            sesion.setAttribute("tituloError", tituloError);
            mensajeError="Ocurrió une error en el servidor, inténte más tarde"; 
            sesion.setAttribute("mensajeError", mensajeError);
            response.sendRedirect("error.jsp");
        }

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
    private void atenderLogin( )throws  IOException{
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        System.err.println("Usuari"+usuario+ " Password "+ password); 
        boolean resultado = metodosRemotos.autenticarUsuario(usuario, password); 
        System.err.println("El resultado  es: "+resultado); 
        if(resultado == true){
            sesion.setAttribute("usuarioName", usuario);
            response.sendRedirect("bienvenido.jsp");
        }else{
            tituloError = "Error en login"; 
            sesion.setAttribute("tituloError", tituloError);
            mensajeError = "El usuario o contraseña no son válidos"; 
            sesion.setAttribute("mensajeError", mensajeError);
            response.sendRedirect("error.jsp");
        }
    }
}
