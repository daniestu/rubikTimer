package client;

import controller.SesionDao;
import controller.SolveDao;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JFrame;
import utilities.FicheroUtil;
import utilities.PrincipalUtil;
import utilities.TextPrompt;
import utilities.Validations;

/**
 *
 * @author Dani
 */
public class NuevoSolve extends JFrame{
    
    public static String scrambleError="";
    public static String solveError="";
    
    NuevoSolve(){
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int fontSize = Validations.menorSize((pantalla.width * 20) / 1920, (pantalla.height * 20) / 1080);
        
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

        l_titulo.setFont(new java.awt.Font("Segoe UI", 1, Validations.menorSize((pantalla.width * 30) / 1920, (pantalla.height * 30) / 1080))); // NOI18N
        l_titulo.setText("Agregar tiempo");
        jPanel1.add(l_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 30) / 1920, (pantalla.height * 10) / 1080, (pantalla.width * 550) / 1920, (pantalla.height * 41) / 1080));
        
        l_tiempo.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_tiempo.setText("Tiempo: (Ejemplo 00:15:35)");
        jPanel1.add(l_tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 30) / 1920, (pantalla.height * 55) / 1080, (pantalla.width * 550) / 1920, (pantalla.height * 30) / 1080));
        
        t_mm.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
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
        jPanel1.add(t_mm, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 30) / 1920, (pantalla.height * 90) / 1080, (pantalla.width * 45) / 1920, (pantalla.height * 30) / 1080));
        
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jLabel1.setText(":");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 75) / 1920, (pantalla.height * 90) / 1080, (pantalla.width * 30) / 1920, (pantalla.height * 30) / 1080));
        
        t_ss.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
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
        jPanel1.add(t_ss, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 80) / 1920, (pantalla.height * 90) / 1080, (pantalla.width * 45) / 1920, (pantalla.height * 30) / 1080));
        
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jLabel2.setText(":");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 125) / 1920, (pantalla.height * 90) / 1080, (pantalla.width * 30) / 1920, (pantalla.height * 30) / 1080));
        
        t_ms.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
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
        jPanel1.add(t_ms, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 130) / 1920, (pantalla.height * 90) / 1080, (pantalla.width * 45) / 1920, (pantalla.height * 30) / 1080));
        
        l_scramble.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_scramble.setText("Scramble:");
        jPanel1.add(l_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 30) / 1920, (pantalla.height * 130) / 1080, (pantalla.width * 550) / 1920, (pantalla.height * 30) / 1080));
        
        t_scramble.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        t_scramble.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_KeyPressed(evt);
            }
        });
        jPanel1.add(t_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 30) / 1920, (pantalla.height * 165) / 1080, (pantalla.width * 550) / 1920, (pantalla.height * 30) / 1080));
        
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setFont(new java.awt.Font("Segoe UI", 0, Validations.menorSize((pantalla.width * 22) / 1920, (pantalla.height * 22) / 1080)));
        jButtonCancelar.setFocusable(false);
        jButtonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancelar.setMnemonic('C');
        jButtonCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonCancelarActionPerformed(evt);
        });
        jPanel1.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 450) / 1920, (pantalla.height * 220) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 33) / 1080));

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setFont(new java.awt.Font("Segoe UI", 0, Validations.menorSize((pantalla.width * 22) / 1920, (pantalla.height * 22) / 1080)));
        jButtonAceptar.setFocusable(false);
        jButtonAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAceptar.setMnemonic('A');
        jButtonAceptar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonAceptarActionPerformed(evt);
        });
        jPanel1.add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 310) / 1920, (pantalla.height * 220) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 33) / 1080));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (pantalla.width * 610) / 1920, (pantalla.height * 280) / 1080));

        PrincipalUtil.actualizarTema(Principal.tema, 5);
        PrincipalUtil.cambiarIdioma(Principal.idioma, 4);
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
                        FicheroUtil.actualizarFichero(tiempo, t_scramble.getText(), new Date(), false, false);
                        Principal.Listado.setModel(SolveDao.cargarSolves(Principal.solves, 0));
                        SesionDao.cargarSesion();
                        Principal.Listado.ensureIndexIsVisible(Principal.Listado.getModel().getSize() - 1);
                    } catch (IOException | ParseException ex) {
                        ex.printStackTrace();
                    }
                    this.dispose();
                } else{
                    PrincipalUtil.Error(scrambleError);
                }
            }else {
                PrincipalUtil.Error(solveError);
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
                    FicheroUtil.actualizarFichero(tiempo, t_scramble.getText(), new Date(),false, false);
                    Principal.Listado.setModel(SolveDao.cargarSolves(Principal.solves, 0));
                    SesionDao.cargarSesion();
                    Principal.Listado.ensureIndexIsVisible(Principal.Listado.getModel().getSize() - 1);
                } catch (IOException | ParseException ex) {
                    ex.printStackTrace();
                }
                this.dispose();
            } else{
                PrincipalUtil.Error(scrambleError);
            }
        }else {
            PrincipalUtil.Error(solveError);
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
    public static javax.swing.JButton jButtonAceptar;
    public static javax.swing.JButton jButtonCancelar;
}
