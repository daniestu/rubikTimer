/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import utilities.PrincipalUtil;

/**
 *
 * @author Dani
 */
public class NuevoScramble extends JFrame{
    
    
    NuevoScramble(){
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        panelGeneral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        t_scramble = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();

        setTitle("Nuevo scramble");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGeneral.setBackground(new java.awt.Color(255, 255, 255));
        panelGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel1.setText("Scramble:");
        panelGeneral.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 30));

        t_scramble.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        t_scramble.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_scrambleKeyPressed(evt);
            }
        });
        panelGeneral.add(t_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 65, 550, 30));

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setFocusable(false);
        jButtonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancelar.setMnemonic('C');
        jButtonCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonCancelarActionPerformed(evt);
        });
        panelGeneral.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 120, 33));

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setFocusable(false);
        jButtonAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAceptar.setMnemonic('A');
        jButtonAceptar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonAceptarActionPerformed(evt);
        });
        panelGeneral.add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 120, 33));

        getContentPane().add(panelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 180));

        PrincipalUtil.actualizarTema(Principal.tema, 8);
        pack();
        setLocationRelativeTo(null);
    }
    private boolean comprobarScramble() {
        String moves[] = {"R","R'","R2","L","L'","L2","U","U'","U2","D","D'","D2","F","F'","F2","B","B'","B2"};
        String scramble[] = t_scramble.getText().split(" ");
        
        for (String scramble1 : scramble) {
            if (!Arrays.asList(moves).contains(scramble1.toUpperCase())) {
                return false;
            }
        }
        return true;
    }
    
    private void t_scrambleKeyPressed(java.awt.event.KeyEvent evt){
        if (evt.getKeyCode() == 10) {
            if (comprobarScramble()) {
                Principal.l_scramble.setText(t_scramble.getText().toUpperCase());
                this.dispose();
            } else{
                JOptionPane.showMessageDialog(this, "Introduce un scramble válido. (Ejemplo: F' R' D B' F2 L F D' R F' L2 F2 B D2 L' R' B2 L2 B' U')", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {
        if (comprobarScramble()) {
            Principal.l_scramble.setText(t_scramble.getText().toUpperCase());
            this.dispose();
        } else{
            JOptionPane.showMessageDialog(this, "Introduce un scramble válido. (Ejemplo: F' R' D B' F2 L F D' R F' L2 F2 B D2 L' R' B2 L2 B' U')", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JPanel panelGeneral;
    public javax.swing.JTextField t_scramble;
    public javax.swing.JButton jButtonAceptar;
    public javax.swing.JButton jButtonCancelar;
}
