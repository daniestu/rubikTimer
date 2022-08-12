package client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import utilities.FicheroUtil;
import utilities.PrincipalUtil;
import utilities.TextPrompt;

/**
 *
 * @author Dani
 */
public class NuevoSolve extends JFrame{
    
    
    NuevoSolve(){
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        l_titulo = new javax.swing.JLabel();
        l_tiempo = new javax.swing.JLabel();
        l_scramble = new javax.swing.JLabel();
        t_mm = new javax.swing.JTextField();
        t_ss = new javax.swing.JTextField();
        t_ms = new javax.swing.JTextField();
        t_scramble = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();
        
        TextPrompt p_mm = new TextPrompt("MM", t_mm);
        p_mm.changeStyle(Font.ITALIC);
        TextPrompt p_ss = new TextPrompt("SS", t_ss);
        p_ss.changeStyle(Font.ITALIC);
        TextPrompt p_ms = new TextPrompt("MS", t_ms);
        p_ms.changeStyle(Font.ITALIC);
    
        setTitle("Agregar tiempo");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        l_titulo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        l_titulo.setText("Agregar tiempo");
        jPanel1.add(l_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(193, 10, 224, -1));
        
        l_tiempo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        l_tiempo.setText("Tiempo: (Ejemplo 00:15:35)");
        jPanel1.add(l_tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 55, -1, 30));
        
        t_mm.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        t_mm.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_KeyPressed(evt);
            }
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_mmKeyTyped(evt);
            }
        });
        jPanel1.add(t_mm, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 45, 30));
        
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel1.setText(":");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 90, -1, 30));
        
        t_ss.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        t_ss.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_KeyPressed(evt);
            }
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_ssKeyTyped(evt);
            }
        });
        jPanel1.add(t_ss, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 45, 30));
        
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText(":");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 90, -1, 30));
        
        t_ms.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        t_ms.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_KeyPressed(evt);
            }
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_msKeyTyped(evt);
            }
        });
        jPanel1.add(t_ms, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 45, 30));
        
        l_scramble.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        l_scramble.setText("Scramble:");
        jPanel1.add(l_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));
        
        t_scramble.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jPanel1.add(t_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 165, 550, 30));
        
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setFont(new java.awt.Font("Segoe UI", 0, 22));
        jButtonCancelar.setFocusable(false);
        jButtonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancelar.setMnemonic('C');
        jButtonCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonCancelarActionPerformed(evt);
        });
        jPanel1.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 120, 33));

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setFont(new java.awt.Font("Segoe UI", 0, 22));
        jButtonAceptar.setFocusable(false);
        jButtonAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAceptar.setMnemonic('A');
        jButtonAceptar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonAceptarActionPerformed(evt);
        });
        jPanel1.add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 120, 33));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 280));

        PrincipalUtil.actualizarTema(Principal.tema, 5);
        pack();
        setLocationRelativeTo(null);
    }
    
    private boolean comprobarTiempo() {
        if ( (t_mm.getText().length() != 2) || (t_ss.getText().length() != 2) || (t_ms.getText().length() != 2)) {
            return false;
        }
        
        int mm = Integer.parseInt(t_mm.getText());
        int ss = Integer.parseInt(t_ss.getText());
        int ms = Integer.parseInt(t_ms.getText());
        
        return !(mm < 0 || mm > 60 || ss < 0 || ss > 60 || ms < 0);
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
    
    private void t_KeyPressed(java.awt.event.KeyEvent evt){
        if (evt.getKeyCode() == 10) {
            if (comprobarTiempo()){
                if (comprobarScramble()) {
                    try {
                        String tiempo = t_mm.getText() + ":" + t_ss.getText() + ":" + t_ms.getText();
                        FicheroUtil.actualizarFichero(tiempo, t_scramble.getText());
                    } catch (IOException | ParseException ex) {
                        ex.printStackTrace();
                    }
                    this.dispose();
                } else{
                    JOptionPane.showMessageDialog(this, "Introduce un scramble válido. (Ejemplo: F' R' D B' F2 L F D' R F' L2 F2 B D2 L' R' B2 L2 B' U')", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(this, "Introduce un tiempo válido. El formato correcto es: mm:ss:ms", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void t_mmKeyTyped(java.awt.event.KeyEvent e) {
        char caracter = e.getKeyChar();
        if ( (t_mm.getText().length() == 2) || ((caracter < '0' || caracter > '9' ) && (caracter != '\b')) ) {
            e.consume();
        }
    }
    
    private void t_ssKeyTyped(java.awt.event.KeyEvent e) {
        char caracter = e.getKeyChar();
        if ( (t_ss.getText().length() == 2) || ((caracter < '0' || caracter > '9' ) && (caracter != '\b')) ) {
            e.consume();
        }
    }
    
    private void t_msKeyTyped(java.awt.event.KeyEvent e) {
        char caracter = e.getKeyChar();
        if ( (t_ms.getText().length() == 2) || ((caracter < '0' || caracter > '9' ) && (caracter != '\b')) ) {
            e.consume();
        }
    }
            
    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {
        if (comprobarTiempo()){
            if (comprobarScramble()) {
                try {
                    String tiempo = t_mm.getText() + ":" + t_ss.getText() + ":" + t_ms.getText();
                    FicheroUtil.actualizarFichero(tiempo, t_scramble.getText());
                } catch (IOException | ParseException ex) {
                    ex.printStackTrace();
                }
                this.dispose();
            } else{
                JOptionPane.showMessageDialog(this, "Introduce un scramble válido. (Ejemplo: F' R' D B' F2 L F D' R F' L2 F2 B D2 L' R' B2 L2 B' U')", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(this, "Introduce un tiempo válido. El formato correcto es: mm:ss:ms", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel l_titulo;
    public static javax.swing.JLabel l_tiempo;
    public static javax.swing.JLabel l_scramble;
    public static javax.swing.JPanel jPanel1;
    public javax.swing.JTextField t_mm;
    public javax.swing.JTextField t_ss;
    public javax.swing.JTextField t_ms;
    public javax.swing.JTextField t_scramble;
    public javax.swing.JButton jButtonAceptar;
    public javax.swing.JButton jButtonCancelar;
}
