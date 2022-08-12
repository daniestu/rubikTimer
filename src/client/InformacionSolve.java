/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import controller.CuboDao;
import controller.SesionDao;
import controller.SolveDao;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.Cubo;
import model.Solve;
import utilities.PrincipalUtil;

/**
 *
 * @author Dani
 */
public class InformacionSolve extends JFrame{
    private final Solve solve;
    
    public InformacionSolve(Solve solve) {
        this.solve = solve;
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setTitle("Información del Solve");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        ImageIcon iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png"));
        l_img.setIcon(iconLogo);

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        l_scramble.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        l_scramble.setText("Scramble: ");
        l_scramble.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel1.add(l_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 100, -1));

        t_scramble.setEditable(false);
        t_scramble.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        t_scramble.setText("D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2");
        t_scramble.setPreferredSize(new java.awt.Dimension(60, 25));
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
        jPanel1.add(t_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 610, -1));
        jPanel1.add(l_img, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 10, 90, 90));

        t_id.setEditable(false);
        t_id.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        t_id.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel1.add(t_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 50, -1));

        l_id.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        l_id.setText("ID: ");
        l_id.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel1.add(l_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 40, -1));

        t_tiempo.setEditable(false);
        t_tiempo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        t_tiempo.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel1.add(t_tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 130, -1));

        l_tiempo.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        l_tiempo.setText("Tiempo:");
        l_tiempo.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel1.add(l_tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 80, -1));

        t_hora.setEditable(false);
        t_hora.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        t_hora.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel1.add(t_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 130, -1));

        l_fecha2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        l_fecha2.setText("Hora: ");
        l_fecha2.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel1.add(l_fecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 60, -1));

        t_fecha.setEditable(false);
        t_fecha.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        t_fecha.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel1.add(t_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 130, -1));

        l_fecha1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        l_fecha1.setText("Fecha: ");
        l_fecha1.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel1.add(l_fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 70, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(80, 23));
        jButton2.setMinimumSize(new java.awt.Dimension(80, 23));
        jButton2.setPreferredSize(new java.awt.Dimension(80, 20));
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton2.setFocusable(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setMnemonic('W');
        jButton2.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton2ActionPerformed(evt);
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 130, 40));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jButton3.setText("Aceptar");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMaximumSize(new java.awt.Dimension(80, 23));
        jButton3.setMinimumSize(new java.awt.Dimension(80, 23));
        jButton3.setPreferredSize(new java.awt.Dimension(80, 20));
        jButton3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton3.setFocusable(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setMnemonic('A');
        jButton3.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton3ActionPerformed(evt);
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 130, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 400));
        
        t_id.setText(solve.getNum()+"");
        t_fecha.setText(formatoFecha.format(solve.getFecha()));
        t_hora.setText(formatoHora.format(solve.getFecha()));
        t_scramble.setText(solve.getScramble());
        t_tiempo.setText(solve.getTiempo());
        
        PrincipalUtil.actualizarTema(Principal.tema, 3);
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
        this.dispose();
    }
    
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
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
}
