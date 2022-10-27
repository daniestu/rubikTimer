/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import controller.CuboDao;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import model.Cubo;
import utilities.PrincipalUtil;
import utilities.Validations;

/**
 *
 * @author Dani
 */
public class NuevoScramble extends JFrame{
    
    public static String scrambleError="";
    
    NuevoScramble(){
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int fontSize = Validations.menorSize((pantalla.width * 20) / 1920, (pantalla.height * 20) / 1080);
        
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        panelGeneral = new javax.swing.JPanel();
        l_scramble = new javax.swing.JLabel();
        t_scramble = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();

        setTitle("Nuevo scramble");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGeneral.setBackground(new java.awt.Color(255, 255, 255));
        panelGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        l_scramble.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_scramble.setText("Scramble:");
        panelGeneral.add(l_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 30) / 1920, (pantalla.height * 30) / 1080, (pantalla.width * 550) / 1920, (pantalla.height * 30) / 1080));

        t_scramble.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        t_scramble.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_scrambleKeyPressed(evt);
            }
        });
        panelGeneral.add(t_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 30) / 1920, (pantalla.height * 65) / 1080, (pantalla.width * 550) / 1920, (pantalla.height * 30) / 1080));

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setFocusable(false);
        jButtonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancelar.setMnemonic('C');
        jButtonCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonCancelarActionPerformed(evt);
        });
        panelGeneral.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 450) / 1920, (pantalla.height * 120) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 33) / 1080));

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setFocusable(false);
        jButtonAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAceptar.setMnemonic('A');
        jButtonAceptar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonAceptarActionPerformed(evt);
        });
        panelGeneral.add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 310) / 1920, (pantalla.height * 120) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 33) / 1080));

        getContentPane().add(panelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (pantalla.width * 610) / 1920, (pantalla.height * 180) / 1080));

        PrincipalUtil.actualizarTema(Principal.tema, 8);
        PrincipalUtil.cambiarIdioma(Principal.idioma, 6);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void submit(String scramble) {
        Principal.l_scramble.setText(scramble);
        Principal.scrambles.add(scramble);
        Cubo c = CuboDao.generarCubo(scramble);
        CuboDao.establecerCubo(c, 0);
    }
    
    private void t_scrambleKeyPressed(java.awt.event.KeyEvent evt){
        if (evt.getKeyCode() == 10) {
            if (Validations.comprobarScramble(t_scramble.getText())) {
                submit(t_scramble.getText().toUpperCase());
                this.dispose();
            } else{
                PrincipalUtil.Error(scrambleError);
            }
        }
    }
    
    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {
        if (Validations.comprobarScramble(t_scramble.getText())) {
            submit(t_scramble.getText().toUpperCase());
            this.dispose();
        } else{
            PrincipalUtil.Error(scrambleError);
        }
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    
    public static javax.swing.JLabel l_scramble;
    public static javax.swing.JPanel panelGeneral;
    public javax.swing.JTextField t_scramble;
    public static javax.swing.JButton jButtonAceptar;
    public static javax.swing.JButton jButtonCancelar;
}
