package client;

import controller.SesionDao;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Sesion;
import utilities.PrincipalUtil;
import utilities.Validations;

/**
 *
 * @author Dani
 */

public class InformacionSesion extends JFrame{
    private final Sesion sesion;
    
    public static String deleteError= "";
    public static String renameError= "";
    public static String nameMissingError= "";
    public static String nameError= "";
    public static String deleteConfirmation= "";
    
    public InformacionSesion(Sesion sesion) {
        this.sesion = sesion;
        
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        int fontSize = Validations.menorSize((pantalla.width * 22) / 1920, (pantalla.height * 22) / 1080);
        int iconSize = Validations.menorSize((pantalla.height * 90) / 1080, (pantalla.width * 90) / 1920);
        
        
        ImageIcon iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png"));
        Image newimg = iconLogo.getImage().getScaledInstance(iconSize, iconSize,  java.awt.Image.SCALE_SMOOTH);
        iconLogo = new ImageIcon(newimg);
        
        jPanel1 = new javax.swing.JPanel();
        l_total = new javax.swing.JLabel();
        t_total = new javax.swing.JTextField();
        l_img = new javax.swing.JLabel();
        t_nombre = new javax.swing.JTextField();
        l_nombre = new javax.swing.JLabel();
        t_avg = new javax.swing.JTextField();
        l_avg = new javax.swing.JLabel();
        t_desv = new javax.swing.JTextField();
        l_desv = new javax.swing.JLabel();
        t_peor = new javax.swing.JTextField();
        l_peor = new javax.swing.JLabel();
        t_mejor = new javax.swing.JTextField();
        l_mejor = new javax.swing.JLabel();
        l_cao5 = new javax.swing.JLabel();
        l_cao12 = new javax.swing.JLabel();
        l_cao100 = new javax.swing.JLabel();
        l_bao5 = new javax.swing.JLabel();
        l_bao12 = new javax.swing.JLabel();
        l_bao100 = new javax.swing.JLabel();
        t_bao5 = new javax.swing.JTextField();
        t_bao12 = new javax.swing.JTextField();
        t_bao100 = new javax.swing.JTextField();
        t_cao5 = new javax.swing.JTextField();
        t_cao12 = new javax.swing.JTextField();
        t_cao100 = new javax.swing.JTextField();
        b_eliminar = new javax.swing.JButton();
        b_aceptar = new javax.swing.JButton();

        setTitle("Información de la sesión");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        l_total.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_total.setText("Total:");
        jPanel1.add(l_total, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920,(pantalla.height * 160) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));

        t_total.setEditable(false);
        t_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_total.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_total.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        jPanel1.add(t_total, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 160) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));
        
        l_img.setIcon(iconLogo);
        
        jPanel1.add(l_img, new org.netbeans.lib.awtextra.AbsoluteConstraints(((((pantalla.width * 380) / 1920)) - iconSize) / 2, (pantalla.height * 10) / 1080, iconSize, iconSize));
        
        jPanel1.add(l_img, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 145) / 1920, (pantalla.height * 10) / 1080, iconSize, iconSize));

        t_nombre.setEditable(false);
        t_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_nombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_nombre.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
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
        t_nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_nombreMouseClicked(evt);
            }
        });
        jPanel1.add(t_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 120) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));

        l_nombre.setFont(new java.awt.Font("Times new Roman", 1, fontSize));
        l_nombre.setHorizontalAlignment(javax.swing.JTextField.RIGHT);// NOI18N
        l_nombre.setText("Nombre:");
        jPanel1.add(l_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 120) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));

        t_avg.setEditable(false);
        t_avg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_avg.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_avg.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        t_avg.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(t_avg.getText().equals("")) ) {
                    t_avgMouseClicked(evt);
                }
            }
        });
        jPanel1.add(t_avg, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 280) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));

        l_avg.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_avg.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_avg.setText("Media:");
        jPanel1.add(l_avg, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 280) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));

        t_desv.setEditable(false);
        t_desv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_desv.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_desv.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        jPanel1.add(t_desv, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 320) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));

        l_desv.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_desv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_desv.setText("Desviación:");
        jPanel1.add(l_desv, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 320) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));
        
        t_peor.setEditable(false);
        t_peor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_peor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_peor.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        t_peor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(t_peor.getText().equals("")) ) {
                    t_peorMouseClicked(evt);
                }
                
            }
        });
        jPanel1.add(t_peor, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 240) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));

        l_peor.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_peor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_peor.setText("Peor:");
        
        jPanel1.add(l_peor, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 240) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));

        t_mejor.setEditable(false);
        t_mejor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_mejor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_mejor.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        t_mejor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(t_mejor.getText().equals("")) ) {
                    t_mejorMouseClicked(evt);
                }
                
            }
        });
        
        jPanel1.add(t_mejor, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 200) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));
        

        l_mejor.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_mejor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_mejor.setText("Mejor:");
        jPanel1.add(l_mejor, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 200) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));

        l_cao5.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_cao5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_cao5.setText("Ao5 Actual:");
        jPanel1.add(l_cao5, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 360) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));
        
        t_cao5.setEditable(false);
        t_cao5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_cao5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_cao5.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        t_cao5.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(t_cao5.getText().equals("")) ) {
                    t_cao5MouseClicked(evt);
                }
            }
        });
        jPanel1.add(t_cao5, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 360) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));
        
        l_cao12.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_cao12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_cao12.setText("Ao12 Actual:");
        jPanel1.add(l_cao12, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 400) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));
        
        t_cao12.setEditable(false);
        t_cao12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_cao12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_cao12.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        t_cao12.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(t_cao12.getText().equals("")) ) {
                    t_cao12MouseClicked(evt);
                }
            }
        });
        jPanel1.add(t_cao12, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 400) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));
        
        l_cao100.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_cao100.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_cao100.setText("Ao100 Actual:");
        jPanel1.add(l_cao100, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 440) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));
        
        t_cao100.setEditable(false);
        t_cao100.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_cao100.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_cao100.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        t_cao100.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(t_cao100.getText().equals("")) ) {
                    t_cao100MouseClicked(evt);
                }
            }
        });
        jPanel1.add(t_cao100, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 440) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));
        
        l_bao5.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_bao5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_bao5.setText("Mejor Ao5:");
        jPanel1.add(l_bao5, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 480) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));
        
        t_bao5.setEditable(false);
        t_bao5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_bao5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_bao5.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        t_bao5.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(t_bao5.getText().equals("")) ) {
                    t_bao5MouseClicked(evt);
                }
            }
        });
        jPanel1.add(t_bao5, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 480) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));
        
        l_bao12.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_bao12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_bao12.setText("Mejor Ao12:");
        jPanel1.add(l_bao12, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 520) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));
        
        t_bao12.setEditable(false);
        t_bao12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_bao12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_bao12.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        t_bao12.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(t_bao12.getText().equals("")) ) {
                    t_bao12MouseClicked(evt);
                }
            }
        });
        jPanel1.add(t_bao12, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 520) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));
        
        l_bao100.setFont(new java.awt.Font("Times new Roman", 1, fontSize)); // NOI18N
        l_bao100.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        l_bao100.setText("Mejor Ao100:");
        jPanel1.add(l_bao100, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 560) / 1080, (pantalla.width * 170) / 1920, (pantalla.height * 25) / 1080));
        
        t_bao100.setEditable(false);
        t_bao100.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_bao100.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        t_bao100.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        t_bao100.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(t_bao100.getText().equals("")) ) {
                    t_bao100MouseClicked(evt);
                }
            }
        });
        jPanel1.add(t_bao100, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 560) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));
        
        b_eliminar.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        b_eliminar.setText("Eliminar");
        b_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b_eliminar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        b_eliminar.setFocusable(false);
        b_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_eliminar.setMnemonic('W');
        b_eliminar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton2ActionPerformed(evt);
        });
        jPanel1.add(b_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920, (pantalla.height * 610) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 40) / 1080));

        b_aceptar.setFont(new java.awt.Font("Times new Roman", 0, fontSize)); // NOI18N
        b_aceptar.setText("Aceptar");
        b_aceptar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b_aceptar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b_aceptar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        b_aceptar.setFocusable(false);
        b_aceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_aceptar.setMnemonic('A');
        b_aceptar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton3ActionPerformed(evt);
        });
        jPanel1.add(b_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 50) / 1920, (pantalla.height * 610) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 40) / 1080));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (pantalla.width * 380) / 1920, (pantalla.height * 680) / 1080));
        
        init();
        
        PrincipalUtil.actualizarTema(Principal.tema, 2);
        PrincipalUtil.cambiarIdioma(Principal.idioma, 1);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void init() {
        t_nombre.setText(sesion.getNombre());
        t_total.setText(sesion.getTotalSolves()+"");
        
        if (sesion.getMejor().getDnf()) {
            t_mejor.setText("DNF");
        }else{
            t_mejor.setText(sesion.getMejor().getTiempo());
        }
        
        if (sesion.getPeor().getDnf()) {
            t_peor.setText("DNF");
        }else{
            t_peor.setText(sesion.getPeor().getTiempo());
        }
        
        t_avg.setText(sesion.getAvg().getTiempo());
        t_desv.setText(sesion.getDesviacion() + "");
        
        if (sesion.getBestAo5().getDnf()) {
            t_bao5.setText("DNF");
        }else{
            t_bao5.setText(sesion.getBestAo5().getTiempo());
        }
        
        if (sesion.getBestAo12().getDnf()) {
            t_bao12.setText("DNF");
        }else{
            t_bao12.setText(sesion.getBestAo12().getTiempo());
        }
        
        if (sesion.getBestAo100().getDnf()) {
            t_bao100.setText("DNF");
        }else{
            t_bao100.setText(sesion.getBestAo100().getTiempo());
        }
        
        if (sesion.getCurrentAo5().getDnf()) {
            t_cao5.setText("DNF");
        }else{
            t_cao5.setText(sesion.getCurrentAo5().getTiempo());
        }
        
        if (sesion.getCurrentAo12().getDnf()) {
            t_cao12.setText("DNF");
        }else{
            t_cao12.setText(sesion.getCurrentAo12().getTiempo());
        }
        
        if (sesion.getCurrentAo100().getDnf()) {
            t_cao100.setText("DNF");
        }else{
            t_cao100.setText(sesion.getCurrentAo100().getTiempo());
        }
    }
    
    private void t_mejorMouseClicked(java.awt.event.MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información del Solve")) {
                i.dispose();
            }
        }
        InformacionSolve informacionSolve = new InformacionSolve(sesion.getMejor());
    }
    
    private void t_peorMouseClicked(java.awt.event.MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información del Solve")) {
                i.dispose();
            }
        }
        InformacionSolve informacionSolve = new InformacionSolve(sesion.getPeor());
    }
    
    private void t_bao5MouseClicked(MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información AVG")) {
                i.dispose();
            }
        }
        InformacionAVG informacionAVG = new InformacionAVG(sesion.getBestAo5());
    }
    
    private void t_bao12MouseClicked(MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información AVG")) {
                i.dispose();
            }
        }
        InformacionAVG informacionAVG = new InformacionAVG(sesion.getBestAo12());
    }
    
    private void t_bao100MouseClicked(MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información AVG")) {
                i.dispose();
            }
        }
        InformacionAVG informacionAVG = new InformacionAVG(sesion.getBestAo100());
    }
    
    private void t_cao5MouseClicked(MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información AVG")) {
                i.dispose();
            }
        }
        InformacionAVG informacionAVG = new InformacionAVG(sesion.getCurrentAo5());
    }
    
    private void t_cao12MouseClicked(MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información AVG")) {
                i.dispose();
            }
        }
        InformacionAVG informacionAVG = new InformacionAVG(sesion.getCurrentAo12());
    }
    
    private void t_cao100MouseClicked(MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información AVG")) {
                i.dispose();
            }
        }
        InformacionAVG informacionAVG = new InformacionAVG(sesion.getCurrentAo100());
    }
    
    private void t_avgMouseClicked(MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información AVG")) {
                i.dispose();
            }
        }
        InformacionAVG informacionAVG = new InformacionAVG(sesion.getAvg());
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt){
        int respuesta = JOptionPane.showConfirmDialog(this, deleteConfirmation, "Mensaje de confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (respuesta == 0) {
            if (!Principal.ficheroSesion.delete()) {
                PrincipalUtil.Error(deleteError);
                return;
            }
            File f = new File("sesiones");
            if (!f.exists()) {
                f.mkdir();
            }
            File[] files = f.listFiles();
            if (files.length == 0) {
                File sesion = new File("sesiones/Default");
                FileWriter fw;
                try {
                    fw = new FileWriter(sesion);
                    fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Principal.ficheroSesion = sesion;
            }else{
                Principal.ficheroSesion = files[0];
            }
            try {
                SesionDao.cargarSesion();
                Principal.jComboBox1.setModel(PrincipalUtil.cargarModeloComboBox());
                Principal.jComboBox1.setSelectedItem(Principal.ficheroSesion.getName());

                Principal.t_tiempo.setText("00:00:00");
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        }
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (isValidName(t_nombre.getText())) {
            File fnew = new File("sesiones/"+t_nombre.getText());

            if (!Principal.ficheroSesion.renameTo(fnew)) {
                PrincipalUtil.Error(renameError);
            }else{
                Principal.ficheroSesion = new File("sesiones/"+t_nombre.getText());
                try {
                    SesionDao.cargarSesion();
                    String tiempo = Principal.t_tiempo.getText();
                    Principal.jComboBox1.setModel(PrincipalUtil.cargarModeloComboBox());
                    Principal.jComboBox1.setSelectedItem(Principal.ficheroSesion.getName());
                    Principal.t_tiempo.setText(tiempo);
                } catch (IOException | ParseException ex) {
                    ex.printStackTrace();
                }
            }
            this.dispose();
        }else{
            if (t_nombre.getText().equals("")) {
                PrincipalUtil.Error(nameMissingError);
            }else{
                PrincipalUtil.Error("'" + t_nombre.getText() + "' " + nameError);
            }
        }
    }
    
    private void t_nombreMouseClicked(MouseEvent evt) {
        t_nombre.setEditable(true);
    }
    
    private void t_nombreKeyPressed(java.awt.event.KeyEvent evt){                             
        // TODO add your handling code here:
        if (evt.getKeyCode() == 10) {
            
            if (isValidName(t_nombre.getText())) {
                File fnew = new File("sesiones/"+t_nombre.getText());
                
                if (!Principal.ficheroSesion.renameTo(fnew)) {
                    PrincipalUtil.Error(renameError);
                }else{
                    Principal.ficheroSesion = new File("sesiones/"+t_nombre.getText());
                    try {
                        SesionDao.cargarSesion();
                        Principal.jComboBox1.setModel(PrincipalUtil.cargarModeloComboBox());
                        Principal.jComboBox1.setSelectedItem(Principal.ficheroSesion.getName());
                    } catch (IOException | ParseException ex) {
                        ex.printStackTrace();
                    }
                }
                this.dispose();
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
            
    public static javax.swing.JButton b_eliminar;
    public static javax.swing.JButton b_aceptar;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JLabel l_mejor;
    public static javax.swing.JLabel l_peor;
    public static javax.swing.JLabel l_avg;
    public static javax.swing.JLabel l_desv;
    public static javax.swing.JLabel l_nombre;
    public static javax.swing.JLabel l_img;
    public static javax.swing.JLabel l_total;
    public static javax.swing.JLabel l_cao5;
    public static javax.swing.JLabel l_cao12;
    public static javax.swing.JLabel l_cao100;
    public static javax.swing.JLabel l_bao5;
    public static javax.swing.JLabel l_bao12;
    public static javax.swing.JLabel l_bao100;
    public static javax.swing.JTextField t_mejor;
    public static javax.swing.JTextField t_peor;
    public static javax.swing.JTextField t_nombre;
    public static javax.swing.JTextField t_total;
    public static javax.swing.JTextField t_avg;
    public static javax.swing.JTextField t_desv;
    public static javax.swing.JTextField t_cao5;
    public static javax.swing.JTextField t_cao12;
    public static javax.swing.JTextField t_cao100;
    public static javax.swing.JTextField t_bao5;
    public static javax.swing.JTextField t_bao12;
    public static javax.swing.JTextField t_bao100;
}
