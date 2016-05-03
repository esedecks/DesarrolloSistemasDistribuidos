/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import interfazrmi.MetodosRemotos;
import java.rmi.RemoteException;
import java.util.Date;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author esedecks
 */
public class MainFrame extends javax.swing.JFrame implements Runnable{
    
    Administracion adminWindow ; 
    EntradaSalidaArticulos entradasalidaWindow; 
    Grafica graficaWindow ; 
    //acceso a los métodos remotos
    MetodosRemotos metodosRemotos ;
    // variables de instancia
    String usuario = "esedecks";
    String correoUsuario;
    String directorio = System.getProperty("user.dir")+"/temporal/";
    String imagenGrafica = "grafica.png"; 
    Thread reloj ; 
    Date f ;
    String directorioReporte = "./temporal/Reporte.pdf"; 
    public MainFrame(String usuario, MetodosRemotos metodosRemotos) {
        initComponents();
        this.metodosRemotos = metodosRemotos; 
        this.setUsuario(usuario);
        f = new Date(); 
        lblFecha.setText("Hoy es :"+f.toLocaleString());
        this.obtenerCorreoUsuario();
        reloj = new Thread(this); 
        reloj.start();
    }
    private void obtenerCorreoUsuario (){
        try { 
           
            correoUsuario = metodosRemotos.getCorreoUsuario (this.usuario);
            //correoUsuario = "jdecks|";  correoUsuario.replace('|','\0'); 
            System.err.println("El correo es : '"+correoUsuario+"'"); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
        lblBienvenido.setText("Bienvenido " +this.usuario+"!");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBienvenido = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblBienvenido.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblBienvenido.setText("Bienvenido");

        jButton1.setText("Generar reporte y envir al correo ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblFecha.setText("Hoy es: ");

        jButton2.setText("Enviar imagen de gráfica al correo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clienteescritorio/articulos.png"))); // NOI18N

        jMenu1.setText("Archivo");

        jMenuItem4.setText("Salir");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Formularios");

        jMenuItem1.setText("Grafica de productos y movimientos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Administrar productos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Nuevas entradas de productos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Ayuda");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFecha)
                            .addComponent(lblBienvenido))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jLabel1)
                .addContainerGap(183, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBienvenido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private DefaultPieDataset getDataForChart(){
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
        return data; 
    }
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        DefaultPieDataset data = this.getDataForChart();
        /*data.setValue("Category 1", 43.2);
        data.setValue("Category 2", 27.9);
        data.setValue("Category 3", 79.5);*/
        // create a chart...
        JFreeChart chart = ChartFactory.createPieChart(
        "Gráfica de movimientos",data,true,true,true);
        /*
        SELECT a.descripcion,count(ma.idArticulo)
        FROM movArticulo ma, articulo a 
        where a.idArticulo = ma.idArticulo group by a.descripcion; 
        */
        graficaWindow = new Grafica("Gráfica", chart); 
        graficaWindow.setVisible(true);
        graficaWindow.pack();
        graficaWindow.setMetodosRemotos(metodosRemotos);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        adminWindow = new Administracion(metodosRemotos); 
        adminWindow.setVisible(true);
        adminWindow.setMetodosRemotos(metodosRemotos);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        entradasalidaWindow = new EntradaSalidaArticulos(metodosRemotos); 
        entradasalidaWindow.setVisible(true);
        entradasalidaWindow.setMetodosRemotos(metodosRemotos);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MotorDeEmails.prepararMotor();
        java.sql.Date fecha = new java.sql.Date(new Date().getTime()); 
        String mensaje = "Solicitó la gráfica generada a la fecha presente\n\r Muchas gracias por utilizar nuestro servicio"+
                                "Att: Ariel Adauta García Presidente de la Empresa de articulos :)"; 
        
        String nuevoCorreo = JOptionPane.showInputDialog(rootPane,"Está apunto de enviarse el correo a :"+correoUsuario+"\nSi desea enviarlo a otro correo escriba en la caja de texto, si no, presione aceptar.");
        if(!nuevoCorreo.equals("")){
            MotorDeEmails.setTo(nuevoCorreo);
        }
        else {
            MotorDeEmails.setTo(correoUsuario);
            nuevoCorreo = correoUsuario; 

        }
        boolean res = MotorDeEmails.enviarEmailconArchivo("Gráfica "+fecha,mensaje, directorio+imagenGrafica);
        if(res){
            JOptionPane.showMessageDialog(rootPane, "Se envio correctamente al correo "+nuevoCorreo);
        }else
           JOptionPane.showMessageDialog(rootPane, "No se pudo enviar la gráfica al correo "+correoUsuario);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MotorDeEmails.prepararMotor();
        java.sql.Date fecha = new java.sql.Date(new Date().getTime()); 
        String mensaje = "Solicitó el reporte. Muchas gracias por utilizar nuestro servicio\n"+
                                "Att: Ariel Adauta García Presidente de la Empresa de articulos :)"; 
        
        String nuevoCorreo = JOptionPane.showInputDialog(rootPane,"Está apunto de enviarse el correo a :"+correoUsuario+"\nSi desea enviarlo a otro correo escriba en la caja de texto, si no, presione aceptar.");
        if(!nuevoCorreo.equals("")){
            MotorDeEmails.setTo(nuevoCorreo);
        }
        else {
            MotorDeEmails.setTo(correoUsuario);
            nuevoCorreo = correoUsuario; 

        }
        boolean res = pdf.pdfTest.generarPDFReporte(metodosRemotos); 
        if(res == false){
            JOptionPane.showMessageDialog(rootPane, "Ocurrió un error al generar el PDF ");
            return ; 
        }
        res = MotorDeEmails.enviarEmailconArchivo("Gráfica "+fecha,mensaje,directorioReporte );
        if(res){
            JOptionPane.showMessageDialog(rootPane, "Se envio correctamente al correo "+nuevoCorreo);
        }else
           JOptionPane.showMessageDialog(rootPane, "No se pudo enviar la gráfica al correo "+correoUsuario);

    }//GEN-LAST:event_jButton1ActionPerformed

    public MetodosRemotos getMetodosRemotos() {
        return metodosRemotos;
    }

    public void setMetodosRemotos(MetodosRemotos metodosRemotos) {
        this.metodosRemotos = metodosRemotos;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new MainFrame("esedecks").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblFecha;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while(true){
            lblFecha.setText("Hoy es :"+new Date().toLocaleString()); 
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
