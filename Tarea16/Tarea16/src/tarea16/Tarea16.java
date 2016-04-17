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
        /*llamada al método para consultar tema
            recibe la dirección URL de la cual se quiere obtener información.
        */
        consultarTema("https://en.wikipedia.org/wiki/Java_%28programming_language%29"); 
    }
    /*método consultarTema*/
    private static void consultarTema(String url){
        /*se llama al método obtener URL, regresa un string con todo el html*/
        String html = obtenerURL(url); 
        /*Se crea un objeto de tipo  org.jsoup.nodes.Document, este se crea 
        con el método estático parse para analizar gramaticalmente
        el string que contiene el html*/
        Document documento = Jsoup.parse(html);
        /*Del objeto documento se utiliza el método select en cuentra elemento que 
        coincidan con el un parámetro de CSS, en este caso particular para obtener 
        el primer párrafo y qye este lo regrese en un String*/
        String texto = documento.select("#mw-content-text>p").first().text(); 
        /*Se muestra el resultado*/
        System.out.println(""+texto);
    }
    /*método estático para obtener la URL*/
    private static String obtenerURL(String url) {
        /*se declara un objeto de tipo URL*/
        URL miURL = null; 
        /*se crea un objeto StringBuilder*/
        StringBuilder textoSalida = new StringBuilder();
        try{
            /*
            Crear un obeto URL desde una representación de un String
            El parámetro que recibe es la cadena a analizar gramaticalmente
            */
            miURL = new URL(url); 
        }/*Se manejan las excepciones en caso de que no sean una URL válida*/
        catch(MalformedURLException ex){ex.printStackTrace();}
        /*Se declara un objeto de tipo URLConnection*/
        URLConnection conexion = null; 
         /*Se declara un objeto de tipo BufferedReader*/
        BufferedReader br = null; 
         
        try{
            /*se abre una conexión desde la URL y se crea un objeto URLConnection*/
            conexion =  miURL.openConnection(); 
            /*Se asocia un flujo de entrada para poder leer la infomación de la 
            URL remota
            */
            br = new BufferedReader(new InputStreamReader(conexion.getInputStream())) ; 
            String line = "";
            /*Se lee todo el texto y se guarda en textoSalida que es un StringBuilder*/
            while((line = br.readLine())!= null){
                textoSalida.append(line); 
                textoSalida.append("\n"); 
            }
            /*Se cierra la conexión*/
            br.close();
        }catch(IOException ioe){
            /*Se maneja las excepciones en caso de que no haya sido posible leer la
            información de la URL remota*/
            ioe.printStackTrace();
            return ""; 
        }
        /*se pasa a String el contenido de textoSalida*/
        return textoSalida.toString(); 
    }
    
}
