/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author esedecks
 */
public class Tarea16 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        consultarTema("https://en.wikipedia.org/wiki/Java_%28programming_language%29"); 
    }
    private static void consultarTema(String url){
        String html = obtenerURL(url); 
        Document documento = Jsoup.parse(html);
        String texto = documento.select("#mw-content-text>p").first().text(); 
        System.out.println(""+texto);
    }

    private static String obtenerURL(String url) {
        URL miURL = null; 
        StringBuilder textoSalida = new StringBuilder();
        try{
            miURL = new URL(url); 
            
        }catch(MalformedURLException ex){ex.printStackTrace();}
        URLConnection conexion = null; 
        BufferedReader br = null; 
         
        try{
            conexion =  miURL.openConnection(); 
            br = new BufferedReader(new InputStreamReader(conexion.getInputStream())) ; 
            String line = "";
            while((line = br.readLine())!= null){
                textoSalida.append(line); 
                textoSalida.append("\n"); 
            }
            
            br.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return ""; 
        }
        return textoSalida.toString(); 
    }
    
}
