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
import com.itextpdf.text.BaseColor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import interfazrmi.MetodosRemotos;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
 
public class pdfTest
{
   public static void main(String[] args) throws BadElementException, IOException, RemoteException, NotBoundException
   {
      Registry registro; 
      MetodosRemotos metodosRemotos ;
      registro = LocateRegistry.getRegistry("127.0.0.1", 1099); 
      metodosRemotos = (MetodosRemotos) registro.lookup("rmiServer");
      String reporte = metodosRemotos.getReporte(); 
      System.err.println("El reporte es: "); 
       System.err.println(reporte);
      Document document = new Document();
    /*  try
      {
        StringBuilder sb = new StringBuilder(); 
        sb.append("<html><body> "); 
        sb.append("</body></html>"); 

        String[] listaArticulos = null; //(String[]) session.getAttribute("listaArticulos"); 
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
        OutputStream file = new FileOutputStream(new File("HelloWorld.pdf"));
       // PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
        PdfWriter.getInstance(document, file);
        document.open();
        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(new StringReader(k));
       
       //  document.add(new Paragraph("A Hello World PDF document.\n Hola "));
         //Add Image
        System.err.println(System.getProperty("user.dir")); 
        Image image1 = Image.getInstance(System.getProperty("user.dir")+"/temporal/grafica.png");
      
        //Scale to new height and new width of image
        image1.scaleAbsolute(500, 300);
        
        PdfPTable table = new PdfPTable(3); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table
 
        //Set Column widths
        float[] columnWidths = {1f, 1f, 1f};
        table.setWidths(columnWidths);
 
        PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
        
        cell1.setBorderColor(BaseColor.BLUE);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
 
        PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
        cell2.setBorderColor(BaseColor.GREEN);
        cell2.setPaddingLeft(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
 
        PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
        cell3.setBorderColor(BaseColor.RED);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
 
        //To avoid having the cell border and the content overlap, if you are having thick cell borders
        //cell1.setUserBorderPadding(true);
        //cell2.setUserBorderPadding(true);
        //cell3.setUserBorderPadding(true);
 
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell1);
        document.add(table);
 
        //Add to document
        document.add(image1);
         
         document.close();
         
         //writer.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }*/
   }
}
