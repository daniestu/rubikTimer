/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import controller.SesionDao;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JFrame;
import utilities.PrincipalUtil;
import utilities.Validations;

/**
 *
 * @author Dani
 */
public class NuevaSesion extends JFrame{
    public static Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static String duplicateError = "";
    public static String nameError = "";
    public static String nameMissingError = "";
    
    NuevaSesion(){
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        jPanel1 = new javax.swing.JPanel();
        l_nombre = new javax.swing.JLabel();
        t_nombre = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();

        setTitle("Nueva sesión");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        int fontSize = Validations.menorSize((pantalla.width * 20) / 1920, (pantalla.height * 20) / 1080);
        
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        l_nombre.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_nombre.setText("Nombre de la Sesión");
        l_nombre.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        jPanel1.add(l_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 30) / 1080, (pantalla.width * 200) / 1920, (pantalla.height * 30) / 1080));

        t_nombre.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        t_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_nombreKeyReleased(evt);
            }
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_nombreKeyTyped(evt);
            }
        });
        jPanel1.add(t_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 225) / 1920, (pantalla.height * 30) / 1080, (pantalla.width * 215) / 1920, (pantalla.height * 30) / 1080));

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setFocusable(false);
        jButtonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancelar.setMnemonic('C');
        jButtonCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonCancelarActionPerformed(evt);
        });
        jPanel1.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 310) / 1920, (pantalla.height * 90) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 30) / 1080));

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setFocusable(false);
        jButtonAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAceptar.setMnemonic('A');
        jButtonAceptar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonAceptarActionPerformed(evt);
        });
        jPanel1.add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 170) / 1920, (pantalla.height * 90) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 30) / 1080));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (pantalla.width * 460) / 1920, (pantalla.height * 140) / 1080));

        PrincipalUtil.actualizarTema(Principal.tema, 4);
        PrincipalUtil.cambiarIdioma(Principal.idioma, 3);
        pack();
        setLocationRelativeTo(null);
    }
     private void t_nombreKeyReleased(java.awt.event.KeyEvent evt){
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            if (isValidName(t_nombre.getText())) {
                String fnombre = Principal.ficheroSesion.getName();
                Principal.ficheroSesion = new File("sesiones/" + t_nombre.getText());
                if (Principal.ficheroSesion.exists()) {
                    PrincipalUtil.Error(duplicateError);
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
                    PrincipalUtil.Error(nameMissingError);
                }else{
                    PrincipalUtil.Error("'" + t_nombre.getText() + "' " + nameError);
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
                PrincipalUtil.Error(duplicateError);
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

                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
                this.dispose();
            }
        }else{
            if (t_nombre.getText().equals("")) {
                PrincipalUtil.Error(nameMissingError);
            }else{
                PrincipalUtil.Error("'" + t_nombre.getText() + "' "  + nameError);
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
    
    public static javax.swing.JLabel l_nombre;
    public static javax.swing.JPanel jPanel1;
    public javax.swing.JTextField t_nombre;
    public static javax.swing.JButton jButtonAceptar;
    public static javax.swing.JButton jButtonCancelar;
}
