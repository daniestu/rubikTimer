/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import controller.CuboDao;
import controller.SesionDao;
import controller.SolveDao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import model.Cubo;
import model.Solve;
import utilities.FicheroUtil;
import utilities.PrincipalUtil;
import utilities.Validations;

/**
 *
 * @author Dani
 */
public class InformacionSolve extends JFrame{
    private final Solve solve;
    
    public static String saveError= "";
    
    public InformacionSolve(Solve solve) {
        this.solve = solve;
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        Border line = new LineBorder(new java.awt.Color(126, 126, 126));
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        
        jPanel1 = new javax.swing.JPanel();
        l_scramble = new javax.swing.JLabel();
        t_scramble = new javax.swing.JTextField();
        l_img = new javax.swing.JLabel();
        t_id = new javax.swing.JTextField();
        l_id = new javax.swing.JLabel();
        t_tiempo = new javax.swing.JTextField();
        l_tiempo = new javax.swing.JLabel();
        t_hora = new javax.swing.JTextField();
        l_fecha2 = new javax.swing.JLabel();
        t_fecha = new javax.swing.JTextField();
        l_fecha1 = new javax.swing.JLabel();
        b_eliminar = new javax.swing.JButton();
        b_aceptar = new javax.swing.JButton();
        b_dnf = new javax.swing.JButton();
        b_sum2 = new javax.swing.JButton();

        setTitle("Información del Solve");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        int fontSize = Validations.menorSize((pantalla.width * 20) / 1920, (pantalla.height * 20) / 1080);
        int iconSize = Validations.menorSize((pantalla.height * 90) / 1080, (pantalla.width * 90) / 1920);
        
        ImageIcon iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png"));
        Image newimg = iconLogo.getImage().getScaledInstance(iconSize, iconSize,  java.awt.Image.SCALE_SMOOTH);
        iconLogo = new ImageIcon(newimg);
        
        l_img.setIcon(iconLogo);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        l_scramble.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_scramble.setText("Scramble:");
        l_scramble.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        jPanel1.add(l_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 15) / 1920, (pantalla.height * 160) / 1080, (pantalla.width * 105) / 1920, (pantalla.height * 25) / 1080));

        t_scramble.setEditable(false);
        t_scramble.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        t_scramble.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_scrambleMouseClicked(evt);
            }

            private void t_scrambleMouseClicked(MouseEvent evt) {
                Cubo cubo = CuboDao.generarCubo(t_scramble.getText());
                Frame f[] = JFrame.getFrames();
                for (Frame i : f) {
                    if (i.getTitle().equals("Scramble")) {
                        i.dispose();
                    }
                }
                ScramblePreview scramblePreview = new ScramblePreview(cubo);
            }
        });
        jPanel1.add(t_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 125) / 1920, (pantalla.height * 160) / 1080, (pantalla.width * 605) / 1920, (pantalla.height * 25) / 1080));
        jPanel1.add(l_img, new org.netbeans.lib.awtextra.AbsoluteConstraints((((pantalla.width * 740) / 1920) - iconSize) / 2, (pantalla.height * 10) / 1080, iconSize, iconSize));

        t_id.setEditable(false);
        t_id.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jPanel1.add(t_id, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 125) / 1920, (pantalla.height * 120) / 1080, (pantalla.width * 50) / 1920, (pantalla.height * 25) / 1080));

        l_id.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_id.setText("ID:");
        l_id.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        jPanel1.add(l_id, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 15) / 1920, (pantalla.height * 120) / 1080, (pantalla.width * 105) / 1920, (pantalla.height * 25) / 1080));

        t_tiempo.setEditable(false);
        t_tiempo.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jPanel1.add(t_tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 125) / 1920, (pantalla.height * 280) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));

        l_tiempo.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_tiempo.setText("Tiempo:");
        l_tiempo.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        jPanel1.add(l_tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 15) / 1920, (pantalla.height * 280) / 1080, (pantalla.width * 105) / 1920, (pantalla.height * 25) / 1080));

        t_hora.setEditable(false);
        t_hora.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jPanel1.add(t_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 125) / 1920, (pantalla.height * 240) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));

        l_fecha2.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_fecha2.setText("Hora:");
        l_fecha2.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        jPanel1.add(l_fecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 15) / 1920, (pantalla.height * 240) / 1080, (pantalla.width * 105) / 1920, (pantalla.height * 25) / 1080));

        t_fecha.setEditable(false);
        t_fecha.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jPanel1.add(t_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 125) / 1920, (pantalla.height * 200) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 25) / 1080));

        l_fecha1.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_fecha1.setText("Fecha:");
        l_fecha1.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        jPanel1.add(l_fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 15) / 1920, (pantalla.height * 200) / 1080, (pantalla.width * 105) / 1920, (pantalla.height * 25) / 1080));

        b_sum2.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        b_sum2.setText("+2");
        b_sum2.setForeground(Color.BLACK);
        b_sum2.setBackground(Color.WHITE);
        b_sum2.setBorder(compound);
        b_sum2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b_sum2.setFocusable(false);
        b_sum2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_sum2.addActionListener((java.awt.event.ActionEvent evt) -> {
            b_sum2ActionPerformed(evt);
        });
        b_sum2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_sum2MouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_sum2MouseExited(evt);
            }

            private void b_sum2MouseEntered(MouseEvent evt) {
                b_sum2.setBorder(new CompoundBorder(new LineBorder(new java.awt.Color(51,51,51)), new EmptyBorder(5, 15, 5, 15)));
            }

            private void b_sum2MouseExited(MouseEvent evt) {
                b_sum2.setBorder(new CompoundBorder(new LineBorder(new java.awt.Color(126, 126, 126)), new EmptyBorder(5, 15, 5, 15)));
            }
        });
        jPanel1.add(b_sum2, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 125) / 1920,(pantalla.height * 320) / 1080,(pantalla.width * 60) / 1920,(pantalla.height * 25) / 1080));
        
        b_dnf.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        b_dnf.setText("DNF");
        b_dnf.setForeground(Color.BLACK);
        b_dnf.setBackground(Color.WHITE);
        b_dnf.setBorder(compound);
        b_dnf.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b_dnf.setFocusable(false);
        b_dnf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_dnf.addActionListener((java.awt.event.ActionEvent evt) -> {
            b_dnfActionPerformed(evt);
        });
        b_dnf.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b_dnfMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b_dnfMouseExited(evt);
            }

            private void b_dnfMouseEntered(MouseEvent evt) {
                b_dnf.setBorder(new CompoundBorder(new LineBorder(new java.awt.Color(51,51,51)), new EmptyBorder(5, 15, 5, 15)));
            }

            private void b_dnfMouseExited(MouseEvent evt) {
                b_dnf.setBorder(new CompoundBorder(new LineBorder(new java.awt.Color(126, 126, 126)), new EmptyBorder(5, 15, 5, 15)));
            }
        });
        jPanel1.add(b_dnf, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 200) / 1920,(pantalla.height * 320) / 1080,-1,(pantalla.height * 25) / 1080));
        
        b_eliminar.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        b_eliminar.setText("Eliminar");
        b_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b_eliminar.setFocusable(false);
        b_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_eliminar.setMnemonic('W');
        b_eliminar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton2ActionPerformed(evt);
        });
        jPanel1.add(b_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 370) / 1920, (pantalla.height * 370) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 40) / 1080));

        b_aceptar.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
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
        jPanel1.add(b_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 220) / 1920, (pantalla.height * 370) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 40) / 1080));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (pantalla.width * 740) / 1920, (pantalla.height * 440) / 1080));
        
        init();
        
        PrincipalUtil.actualizarTema(Principal.tema, 3);
        PrincipalUtil.cambiarIdioma(Principal.idioma, 2);
        pack();
        setLocationRelativeTo(null);
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            SolveDao.eliminar(solve);
            SesionDao.cargarSesion();
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if ( !(i.getTitle().equals("Rubik Timer")) ) {
                i.dispose();
            }
        }
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        save();
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if ( !(i.getTitle().equals("Rubik Timer")) ) {
                i.dispose();
            }
        }
    }
    
    public static javax.swing.JButton b_eliminar;
    public static javax.swing.JButton b_aceptar;
    public static javax.swing.JButton b_dnf;
    public static javax.swing.JButton b_sum2;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JLabel l_fecha1;
    public static javax.swing.JLabel l_fecha2;
    public static javax.swing.JLabel l_tiempo;
    public static javax.swing.JLabel l_id;
    public static javax.swing.JLabel l_img;
    public static javax.swing.JLabel l_scramble;
    public static javax.swing.JTextField t_fecha;
    public static javax.swing.JTextField t_hora;
    public static javax.swing.JTextField t_id;
    public static javax.swing.JTextField t_scramble;
    public static javax.swing.JTextField t_tiempo;

    private void b_sum2ActionPerformed(ActionEvent evt) {
        solve.setDnf(false);
        
        if (solve.getSum2()) {
            solve.setSum2(false);
            solve.setTiempo(PrincipalUtil.restar2(solve.getTiempo()));
            t_tiempo.setText(solve.getTiempo());
            b_sum2.setBackground(new java.awt.Color(238, 238, 238));
        }else{
            solve.setSum2(true);
            solve.setTiempo(PrincipalUtil.sumar2(solve.getTiempo()));
            t_tiempo.setText(solve.getTiempo() + " +");
            b_sum2.setBackground(new java.awt.Color(189, 189, 189));
        }
        b_dnf.setBackground(new java.awt.Color(238, 238, 238));
    }

    private void b_dnfActionPerformed(ActionEvent evt) {
        if (solve.getSum2()) {
            solve.setSum2(false);
            solve.setTiempo(PrincipalUtil.restar2(solve.getTiempo()));
        }
        
        if (solve.getDnf()) {
            solve.setDnf(false);
            t_tiempo.setText(solve.getTiempo());
            b_dnf.setBackground(new java.awt.Color(238, 238, 238));
        }else{
            solve.setDnf(true);
            t_tiempo.setText("DNF(" + solve.getTiempo() + ")");
            b_dnf.setBackground(new java.awt.Color(189, 189, 189));
        }
        b_sum2.setBackground(new java.awt.Color(238, 238, 238));
    }

    private void init() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        
        t_id.setText(solve.getNum()+"");
        t_fecha.setText(formatoFecha.format(solve.getFecha()));
        t_hora.setText(formatoHora.format(solve.getFecha()));
        t_scramble.setText(solve.getScramble());
        
        if (solve.getDnf()) {
            t_tiempo.setText("DNF(" + solve.getTiempo() + ")");
            b_dnf.setBackground(new java.awt.Color(189,189,189));
        } else if (solve.getSum2()) {
            t_tiempo.setText(solve.getTiempo() + " +");
            b_sum2.setBackground(new java.awt.Color(189,189,189));
        } else {
            t_tiempo.setText(solve.getTiempo());
        }
    }

    private void save() {
        Principal.solves.set(solve.getNum() - 1, solve);
        
        Principal.Listado.setModel(SolveDao.cargarSolves(Principal.solves, 0));
        try {
            FicheroUtil.sobreescibirFichero(Principal.solves);
            SesionDao.cargarSesion();
        } catch (IOException | ParseException e) {
            PrincipalUtil.Error(saveError);
        }
    }
}
