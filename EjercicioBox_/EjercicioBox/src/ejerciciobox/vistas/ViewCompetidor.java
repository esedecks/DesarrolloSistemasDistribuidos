/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciobox.vistas;

import ejerciciobox.Cliente;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author esedecks
 */
public class ViewCompetidor extends javax.swing.JFrame {

    /**
     * Creates new form ViewCompetidor
     */
    Cliente cl ; 

    public ViewCompetidor(Cliente cl ) {
        initComponents();
        this.cl = cl ; 
        cargarCombos();
    }
    private void cargarCombos(){
        cmbCategoriaActualizar.setModel(new DefaultComboBoxModel());
        cmbCategoriaAgregar.setModel(new DefaultComboBoxModel());
        String query = "select * from categoria"; 
        try{
            cl.sendMessage(query);
            String respuesta = cl.receiveMessage();
            System.err.println("RES:"+respuesta);
            String elemento ; 
            //descomponer en lineas
            String[] lineas = respuesta.split("\\r?\\n");
            int i = 0; 
            for(String linea : lineas){
                String[] tokens = linea.split("\\|");
                if(i== 0){i++ ; continue;} 
                elemento = tokens[1]+"-"+tokens[2];  
                cmbCategoriaActualizar.addItem(elemento);
                cmbCategoriaAgregar.addItem(elemento);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtDatos = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreActualizar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPesoActualizar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEscuelaOrigenActualizar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        cmbCategoriaActualizar = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        txtIdCompetidorActualizar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtIdCompetidorBorrar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        txtIdCompetidorConsultar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreAgregar = new javax.swing.JTextField();
        txtPesoAgregar = new javax.swing.JTextField();
        txtEscuelaOrigenAgregar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cmbCategoriaAgregar = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtDatos.setColumns(20);
        txtDatos.setRows(5);
        jScrollPane1.setViewportView(txtDatos);

        jButton5.setText("Consultar Lista de Competidores");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setText("Id Competidor");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Actualizar Competidor"));

        jLabel7.setText("Nombre");

        jLabel8.setText("Peso");

        jLabel9.setText("Escuela Origen");

        txtEscuelaOrigenActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEscuelaOrigenActualizarActionPerformed(evt);
            }
        });

        jLabel10.setText("Categoria");

        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cmbCategoriaActualizar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Id Competidor");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(0, 75, Short.MAX_VALUE))
                            .addComponent(txtEscuelaOrigenActualizar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPesoActualizar)
                            .addComponent(txtNombreActualizar)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(64, 64, 64)
                        .addComponent(cmbCategoriaActualizar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(34, 34, 34)
                        .addComponent(txtIdCompetidorActualizar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtIdCompetidorActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNombreActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPesoActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEscuelaOrigenActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbCategoriaActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jButton2))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Borrar Competidor"));

        jLabel11.setText("Id Competidor");

        jButton3.setText("Borrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButton3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdCompetidorBorrar)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtIdCompetidorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Competidor"));

        jLabel3.setText("Nombre");

        jLabel4.setText("Peso");

        jLabel5.setText("Categoria");

        jLabel6.setText("Escuela Origen");

        txtEscuelaOrigenAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEscuelaOrigenAgregarActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmbCategoriaAgregar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(txtPesoAgregar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(txtNombreAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtEscuelaOrigenAgregar)
                    .addComponent(cmbCategoriaAgregar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombreAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPesoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEscuelaOrigenAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbCategoriaAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jButton4.setText("Consultar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Consultar Competidor");

        jButton6.setText("Regresar al inicio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdCompetidorConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(134, 134, 134))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(285, 285, 285))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdCompetidorConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String query = "competidor,consultarTodo";
        try{
            cl.sendMessage(query);
            String respuesta = cl.receiveMessage();
            txtDatos.setText(respuesta);

        }catch(Exception e ){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtEscuelaOrigenActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEscuelaOrigenActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEscuelaOrigenActualizarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(txtIdCompetidorActualizar);
        campos.add(txtNombreActualizar);
        campos.add(txtPesoActualizar);
        campos.add(txtEscuelaOrigenActualizar);

        allIsFill(campos);
        if(!allIsFill(campos)){
            JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
            return ;
        }
        String idCompetidor = txtIdCompetidorActualizar.getText(); 
        String nombre = txtNombreActualizar.getText();
        String peso = txtPesoActualizar.getText();
        String escuela = txtEscuelaOrigenActualizar.getText();
        String categoria =(String) cmbCategoriaActualizar.getItemAt(cmbCategoriaActualizar.getSelectedIndex()); 
        int id = getIdCategoria(categoria); 
        String query;
        query = "competidor,actualizar,"+idCompetidor+","+nombre+","+peso+","+escuela+","+id;

        System.err.println("Query : "+query);
        try{
            cl.sendMessage(query);
            String respuesta = cl.receiveMessage();
            if(respuesta.startsWith(query)){
                JOptionPane.showMessageDialog(null, "La modificación fue hecha!");
            }else if(respuesta.startsWith("No fue posible")){
                JOptionPane.showMessageDialog(null, "No es posible ejecutar "+query);
            }

        }catch(Exception e ){
            e.printStackTrace();
        }
        limpiarCampos(campos);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(txtIdCompetidorBorrar);
        allIsFill(campos);
        if(!allIsFill(campos)){
            JOptionPane.showMessageDialog(null, "Falta campo por llenar");
            return ;
        }
        String idCompetidor = txtIdCompetidorBorrar.getText();
        String query ;
        query = "competidor,eliminar,"+idCompetidor;

        System.err.println("Query : "+query);
        try{
            cl.sendMessage(query);
            String respuesta = cl.receiveMessage();
            JOptionPane.showMessageDialog(null,respuesta);

        }catch(Exception e ){
            e.printStackTrace();
        }
        limpiarCampos(campos);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtEscuelaOrigenAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEscuelaOrigenAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEscuelaOrigenAgregarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //consultar si todos los campos están llenos
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(txtNombreAgregar);
        campos.add(txtPesoAgregar);
        campos.add(txtEscuelaOrigenAgregar);
        allIsFill(campos);
        if(!allIsFill(campos)){
            JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
            return ;
        }
        String nombre = txtNombreAgregar.getText();
        String peso = txtPesoAgregar.getText();
        String escuela = txtEscuelaOrigenAgregar.getText();
        String categoria =(String) cmbCategoriaAgregar.getItemAt(cmbCategoriaAgregar.getSelectedIndex()); 
        ///obtener el id de categoria en función del competidor
        int idCategoria = getIdCategoria(categoria); 
        String query ;
        /* //alumno, [agregar, elimanr, actualizar, consultarUno,consultarTodos],*/
        query = "competidor,agregar,"+nombre+","+peso+","+escuela+","+idCategoria;
        System.err.println("Query : "+query);
        try{
            cl.sendMessage(query);
            String respuesta = cl.receiveMessage();
            System.err.println("el query es: "+respuesta);
            if(respuesta.startsWith(query)){
                JOptionPane.showMessageDialog(null, "La inserción fue hecha!");
            }else if(respuesta.startsWith("No fue posible")){
                JOptionPane.showMessageDialog(null, "No es posible insertar el alumno ");
            }
        }catch(Exception e ){
            e.printStackTrace();
        }
        limpiarCampos(campos);
    }//GEN-LAST:event_jButton1ActionPerformed
    private int getIdCategoria(String categoria){
        String[] token = categoria.split("-"); 
        int n = 0; 
        try{
            String query = "select idCategoria from categoria where nombre = '"+token[0]+"'"; 
            cl.sendMessage(query);
            String respuesta = cl.receiveMessage(); 
            System.err.println("El id de la categoria que se seleccionó fue"+ respuesta); 
            String[] line = respuesta.split("\\r?\\n"); 
            
            //QUITAR EL PIPE
            respuesta = line[1].replace("|", ""); 
            n = Integer.parseInt(respuesta); 
            System.out.println("--"+n); 
        }catch(Exception e){
            e.printStackTrace();; 
                }
        return n; 
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ArrayList<JTextField> campos = new ArrayList<>();
        campos.add(txtIdCompetidorConsultar);
        allIsFill(campos);
        if(!allIsFill(campos)){
            JOptionPane.showMessageDialog(null, "Falta campo por llenar");
            return ;
        }
        String idCompetidor = txtIdCompetidorConsultar.getText();
        String query = "competidor,consultarUno,"+idCompetidor;
        System.err.println("Query : "+query);
        try{
            cl.sendMessage(query);
            String respuesta = cl.receiveMessage();
            System.err.println("Respuesta"+respuesta);
            if(respuesta.startsWith("No fue posible ejecutar")){
                JOptionPane.showMessageDialog(null, "No fue posible ejecutar la consulta");
            }else{
                txtDatos.setText(respuesta);
            }
        }catch(Exception e ){
            e.printStackTrace();
        }
        limpiarCampos(campos);
    }//GEN-LAST:event_jButton4ActionPerformed
     private void limpiarCampos(ArrayList<JTextField> campos){
        for(JTextField campo: campos)
            campo.setText("");
        
    }
    private boolean allIsFill(ArrayList<JTextField> campos){
        boolean bandera = true; 
        for(JTextField campo : campos){
            if(campo.getText().equals(""))
                bandera = false;   
        }
        return bandera; 
    
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
            java.util.logging.Logger.getLogger(ViewCompetidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCompetidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCompetidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCompetidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ViewCompetidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbCategoriaActualizar;
    private javax.swing.JComboBox cmbCategoriaAgregar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDatos;
    private javax.swing.JTextField txtEscuelaOrigenActualizar;
    private javax.swing.JTextField txtEscuelaOrigenAgregar;
    private javax.swing.JTextField txtIdCompetidorActualizar;
    private javax.swing.JTextField txtIdCompetidorBorrar;
    private javax.swing.JTextField txtIdCompetidorConsultar;
    private javax.swing.JTextField txtNombreActualizar;
    private javax.swing.JTextField txtNombreAgregar;
    private javax.swing.JTextField txtPesoActualizar;
    private javax.swing.JTextField txtPesoAgregar;
    // End of variables declaration//GEN-END:variables
}
