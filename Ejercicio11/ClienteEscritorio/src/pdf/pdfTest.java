/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdf;

/**
 *
 * @author esedecks
 */
import com.itextpdf.text.BadElementException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import interfazrmi.MetodosRemotos;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
 
public class pdfTest
{
   public static boolean generarPDFReporte(MetodosRemotos metodosRemotos) {
    try
        {
  
            Document document = new Document();
            String style = "<style>\n" +
                            "        table, th, td {\n" +
                            "        border: 1px solid black;\n" +
                            "        border-collapse: collapse;width:100%;\n" +
                            "        }\n" +
                            "        th, td {\n" +
                            "            padding: 5px;\n" +
                            "            text-align: left;\n" +
                            "        }\n" +
                            "</style>"; 
            StringBuilder sb = new StringBuilder(); 
            sb.append("<html> ");
            sb.append("<head>"); 
            sb.append(style); 
            sb.append("</head>"); 
            sb.append("<body>");
            sb.append("<h1 align =\"center\" > Reporte de articulos " +(new java.sql.Date(new Date().getTime()))+"</h1>"); 
            sb.append("<hr/>");
            sb.append("<div aling =\"center\">"); 
            sb.append("IPN-ESCOM | Ariel Felix Adauta García | Desarrollo de Sistemas distribuidos | 2016"); 
            sb.append("</div>"); 
            sb.append("<hr/>");

            sb.append("<h2>Tabla de articulos. </h2>"); 
            sb.append("<table>"); 
            String h = "<tr>\n" +
            "    <th>Nombre articulo</th>\n" +
            "    <th>Precio</th>\n" +
            "    <th>Existencias</th>\n" +
            "  </tr>"; 
            sb.append(h); 
            String[] listaArticulos = metodosRemotos.leerArticulos().split("\\r?\\n");
            for(String articulo : listaArticulos){
                articulo = articulo.replace('|',' '); 
                System.err.println("articulo :"+articulo); 
                String info = metodosRemotos.leerInfoArticulo(articulo);
                System.err.println("info :"+info); 
                String[] tokens = info.split("\\|"); 
                sb.append("<tr>");
                sb.append("<td>"+tokens[1]+"</td><td>"+tokens[2]+"</td><td>"+tokens[3] +"</td>\n"); 
                sb.append("</tr>\n");  
        }
            
            sb.append("</table>\n");
            sb.append("<hr/>"); 
            sb.append("<h2>Tabla de movimientos</h2>");
            sb.append("<table>"); 
             h = "<tr>\n" +
                    "    <th>Nombre articulo</th>\n" +
                    "    <th>Fecha de movimeinto</th>\n" +
                    "    <th>Tipo de movimiento</th>\n" +
                    "    <th>Cantidad</th>\n"+
                    "  </tr>"; 
            sb.append(h); 
            listaArticulos = metodosRemotos.getReporte().split("\\r?\\n");
            for(String articulo : listaArticulos){
                String[] tokens = articulo.split("\\|");
                sb.append("<tr>");
                sb.append("<td>"+tokens[0]+"</td><td>"+tokens[1]+"</td><td>"+tokens[2] +"</td><td>"+tokens[3] +"</td>\n"); 
                sb.append("</tr>\n");  
        }
            
            sb.append("</table>\n");
            
            
            
            sb.append("<hr/>"); 
            sb.append("<h2>Imagen de gráficas</h2>");
           
            sb.append("</body> </html>\n"); 
            System.err.println(""+sb.toString());
        
            OutputStream file = new FileOutputStream(new File("./temporal/Reporte.pdf"));
       // PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
        PdfWriter writer = PdfWriter.getInstance(document, file);        document.open();
        InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
      
      
    
        Image image1 = Image.getInstance(System.getProperty("user.dir")+"/temporal/grafica.png");
        //Scale to new height and new width of image
        image1.scaleAbsolute(500, 300);
        //Add to document
        document.add(image1);
         
        document.close();
        file.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
         return false; 
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
         return false; 

      }catch(RemoteException e){
          e.printStackTrace();
         return false; 

      }catch(IOException e){
          e.printStackTrace();
         return false; 

      }
    return true; 
   }
   public static void main(String[] args) throws BadElementException, IOException, RemoteException, NotBoundException
   {
        Registry registro; 
        MetodosRemotos metodosRemotos ;
        registro = LocateRegistry.getRegistry("127.0.0.1", 1099); 
        metodosRemotos = (MetodosRemotos) registro.lookup("rmiServer");
        
        try
        {
  
            Document document = new Document();
            String style = "<style>\n" +
                            "        table, th, td {\n" +
                            "        border: 1px solid black;\n" +
                            "        border-collapse: collapse;width:100%;\n" +
                            "        }\n" +
                            "        th, td {\n" +
                            "            padding: 5px;\n" +
                            "            text-align: left;\n" +
                            "        }\n" +
                            "</style>"; 
            StringBuilder sb = new StringBuilder(); 
            sb.append("<html> ");
            sb.append("<head>"); 
            sb.append(style); 
            sb.append("</head>"); 
            sb.append("<body>");
            sb.append("<h1 align =\"center\" > Reporte de articulos " +(new java.sql.Date(new Date().getTime()))+"</h1>"); 
            sb.append("<h2>Tabla de articulos. </h2>"); 
            sb.append("<table>"); 
            String h = "<tr>\n" +
            "    <th>Nombre articulo</th>\n" +
            "    <th>Precio</th>\n" +
            "    <th>Existencias</th>\n" +
            "  </tr>"; 
            sb.append(h); 
            String[] listaArticulos = metodosRemotos.leerArticulos().split("\\r?\\n");
            for(String articulo : listaArticulos){
                articulo = articulo.replace('|',' '); 
                System.err.println("articulo :"+articulo); 
                String info = metodosRemotos.leerInfoArticulo(articulo);
                System.err.println("info :"+info); 
                String[] tokens = info.split("\\|"); 
                sb.append("<tr>");
                sb.append("<td>"+tokens[1]+"</td><td>"+tokens[2]+"</td><td>"+tokens[3] +"</td>\n"); 
                sb.append("</tr>\n");  
        }
            
            sb.append("</table>\n");
            sb.append("<hr/>"); 
            sb.append("<h2>Tabla de movimientos</h2>");
            sb.append("<table>"); 
             h = "<tr>\n" +
                    "    <th>Nombre articulo</th>\n" +
                    "    <th>Fecha de movimeinto</th>\n" +
                    "    <th>Tipo de movimiento</th>\n" +
                    "    <th>Cantidad</th>\n"+
                    "  </tr>"; 
            sb.append(h); 
            listaArticulos = metodosRemotos.getReporte().split("\\r?\\n");
            for(String articulo : listaArticulos){
                String[] tokens = articulo.split("\\|");
                sb.append("<tr>");
                sb.append("<td>"+tokens[0]+"</td><td>"+tokens[1]+"</td><td>"+tokens[2] +"</td><td>"+tokens[3] +"</td>\n"); 
                sb.append("</tr>\n");  
        }
            
            sb.append("</table>\n");
            
            
            
            sb.append("<hr/>"); 
            sb.append("<h2>Imagen de gráficas</h2>");
            sb.append("<img src =\"/temporal/grafica.png \" />"); 
            sb.append("</body> </html>\n"); 
            System.err.println(""+sb.toString());
        
            OutputStream file = new FileOutputStream(new File("./temporal/Reporte.pdf"));
       // PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
        PdfWriter writer = PdfWriter.getInstance(document, file);        document.open();
        InputStream is = new ByteArrayInputStream(sb.toString().getBytes());
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
      
      
    
        Image image1 = Image.getInstance(System.getProperty("user.dir")+"/temporal/grafica.png");
        //Scale to new height and new width of image
        image1.scaleAbsolute(500, 300);
        //Add to document
        document.add(image1);
         
        document.close();
        file.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
   }
}
