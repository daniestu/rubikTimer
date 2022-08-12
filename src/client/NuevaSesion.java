/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import controller.SesionDao;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import utilities.PrincipalUtil;

/**
 *
 * @author Dani
 */
public class NuevaSesion extends JFrame{
    
    
    NuevaSesion(){
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        t_nombre = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();

        setTitle("Nueva sesión");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel1.setText("Nombre de la Sesión: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 30));

        t_nombre.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        t_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_nombreKeyPressed(evt);
            }
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_nombreKeyTyped(evt);
            }
        });
        jPanel1.add(t_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 230, 30));

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setFocusable(false);
        jButtonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancelar.setMnemonic('C');
        jButtonCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonCancelarActionPerformed(evt);
        });
        jPanel1.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 110, 30));

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setFocusable(false);
        jButtonAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAceptar.setMnemonic('A');
        jButtonAceptar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonAceptarActionPerformed(evt);
        });
        jPanel1.add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 110, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 140));

        PrincipalUtil.actualizarTema(Principal.tema, 4);
        pack();
        setLocationRelativeTo(null);
    }
     private void t_nombreKeyPressed(java.awt.event.KeyEvent evt){
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            if (isValidName(t_nombre.getText())) {
                String fnombre = Principal.ficheroSesion.getName();
                Principal.ficheroSesion = new File("sesiones/" + t_nombre.getText());
                if (Principal.ficheroSesion.exists()) {
                    JOptionPane.showMessageDialog(this, "Ya existe una sesión con ese nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
                    t_nombre.setText("");
                    Principal.ficheroSesion = new File("sesion/" + fnombre);
                }else{
                    try {
                        FileWriter fw = new FileWriter(Principal.ficheroSesion);
                        fw.close();
                        SesionDao.cargarSesion();
                        
                        Principal.jComboBox1.setModel(PrincipalUtil.cargarModeloComboBox());
                        Principal.jComboBox1.setSelectedItem(Principal.ficheroSesion.getName());
                        
                        Principal.t_tiempo.setText("00:00:00");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.dispose();
                }
            }else{
                if (t_nombre.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Introduce un nombre para la nueva sesión", "ERROR", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this, "'" + t_nombre.getText() + "' no es un nombre válido para una sesión", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    private void t_nombreKeyTyped(java.awt.event.KeyEvent evt) {                                
        // TODO add your handling code here:
        if (t_nombre.getText().length() == 20) {
            evt.consume();
        }
    } 
    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {
        if (isValidName(t_nombre.getText())) {
            String fnombre = Principal.ficheroSesion.getName();
            Principal.ficheroSesion = new File("sesiones/" + t_nombre.getText());
            if (Principal.ficheroSesion.exists()) {
                JOptionPane.showMessageDialog(this, "Ya existe una sesión con ese nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
                t_nombre.setText("");
                Principal.ficheroSesion = new File("sesiones/" + fnombre);
            }else{
                try {
                    FileWriter fw = new FileWriter(Principal.ficheroSesion);
                    fw.close();
                    SesionDao.cargarSesion();

                    Principal.jComboBox1.setModel(PrincipalUtil.cargarModeloComboBox());
                    Principal.jComboBox1.setSelectedItem(Principal.ficheroSesion.getName());
                    
                    Principal.t_tiempo.setText("00:00:00");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.dispose();
            }
        }else{
            if (t_nombre.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Introduce un nombre para la nueva sesión", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "'" + t_nombre.getText() + "' no es un nombre válido para una sesión", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static boolean isValidName(String text){
        String parts[] = text.split("[/]");
        if (parts.length > 1) {
            return false;
        }
        try{
            File file = new File(text);
            if(file.createNewFile()) file.delete();
            return true;
        }catch(Exception ex){}
        return false;
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JPanel jPanel1;
    public javax.swing.JTextField t_nombre;
    public javax.swing.JButton jButtonAceptar;
    public javax.swing.JButton jButtonCancelar;
}
