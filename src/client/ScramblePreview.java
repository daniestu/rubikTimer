/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import controller.CuboDao;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.Cubo;
import utilities.PrincipalUtil;

/**
 *
 * @author Dani
 */
public class ScramblePreview extends JFrame{
    private final Cubo cubo;
    public static ImageIcon iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
    public static ImageIcon iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
    public static Dimension pantalla;
    
    public ScramblePreview(Cubo cubo){
        this.cubo = cubo;
        
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        setTitle("Scramble");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        panelGeneral = new javax.swing.JPanel();
        l_cerrar = new javax.swing.JLabel();
        
        u1 = new javax.swing.JPanel();
        u2 = new javax.swing.JPanel();
        u3 = new javax.swing.JPanel();
        u4 = new javax.swing.JPanel();
        u5 = new javax.swing.JPanel();
        u6 = new javax.swing.JPanel();
        u7 = new javax.swing.JPanel();
        u8 = new javax.swing.JPanel();
        u9 = new javax.swing.JPanel();
        
        r1 = new javax.swing.JPanel();
        r2 = new javax.swing.JPanel();
        r3 = new javax.swing.JPanel();
        r4 = new javax.swing.JPanel();
        r5 = new javax.swing.JPanel();
        r6 = new javax.swing.JPanel();
        r7 = new javax.swing.JPanel();
        r8 = new javax.swing.JPanel();
        r9 = new javax.swing.JPanel();
        
        d1 = new javax.swing.JPanel();
        d2 = new javax.swing.JPanel();
        d3 = new javax.swing.JPanel();
        d4 = new javax.swing.JPanel();
        d5 = new javax.swing.JPanel();
        d6 = new javax.swing.JPanel();
        d7 = new javax.swing.JPanel();
        d8 = new javax.swing.JPanel();
        d9 = new javax.swing.JPanel();
        
        l1 = new javax.swing.JPanel();
        l2 = new javax.swing.JPanel();
        l3 = new javax.swing.JPanel();
        l4 = new javax.swing.JPanel();
        l5 = new javax.swing.JPanel();
        l6 = new javax.swing.JPanel();
        l7 = new javax.swing.JPanel();
        l8 = new javax.swing.JPanel();
        l9 = new javax.swing.JPanel();
        
        f1 = new javax.swing.JPanel();
        f2 = new javax.swing.JPanel();
        f3 = new javax.swing.JPanel();
        f4 = new javax.swing.JPanel();
        f5 = new javax.swing.JPanel();
        f6 = new javax.swing.JPanel();
        f7 = new javax.swing.JPanel();
        f8 = new javax.swing.JPanel();
        f9 = new javax.swing.JPanel();
        
        b1 = new javax.swing.JPanel();
        b2 = new javax.swing.JPanel();
        b3 = new javax.swing.JPanel();
        b4 = new javax.swing.JPanel();
        b5 = new javax.swing.JPanel();
        b6 = new javax.swing.JPanel();
        b7 = new javax.swing.JPanel();
        b8 = new javax.swing.JPanel();
        b9 = new javax.swing.JPanel();
        
        pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        panelGeneral.setBackground(new java.awt.Color(238, 238, 238));
        panelGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        l_cerrar.setIcon(iconCerrar);
        l_cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l_cerrarMouseClicked(evt);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                l_cerrarMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                l_cerrarMouseExited(evt);
            }

            private void l_cerrarMouseClicked(MouseEvent evt) {
                Frame f[] = JFrame.getFrames();
                for (Frame i : f) {
                    if (i.getTitle().equals("Scramble")) {
                        i.dispose();
                    }
                }
            }

            private void l_cerrarMouseEntered(MouseEvent evt) {
                l_cerrar.setIcon(iconCerrarHover);
            }

            private void l_cerrarMouseExited(MouseEvent evt) {
                l_cerrar.setIcon(iconCerrar);
            }
        });
        
        int tamanioCuadrado = (pantalla.height * 40) / 1080;
        
        panelGeneral.add(l_cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado * 12), 10, 30, 30));
        
        u1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        f1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        d1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        l1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        r1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        b1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        panelGeneral.add(u1, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 22, 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(u2, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 21, 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(u3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 20, 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(u4, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 22, tamanioCuadrado+19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(u5, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 21, tamanioCuadrado+19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(u6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 20, tamanioCuadrado+19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(u7, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 22, (tamanioCuadrado * 2) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(u8, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 21, (tamanioCuadrado * 2) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(u9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 20, (tamanioCuadrado * 2) + 18, tamanioCuadrado, tamanioCuadrado));
        
        panelGeneral.add(r1, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*6) + 22, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(r2, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*7) + 20, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(r3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*8) + 19, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(r4, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*6) + 22, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(r5, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*7) + 20, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(r6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*8) + 19, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(r7, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*6) + 22, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(r8, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*7) + 20, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(r9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*8) + 19, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        
        panelGeneral.add(d1, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 22, (tamanioCuadrado * 6) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(d2, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 21, (tamanioCuadrado * 6) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(d3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 20, (tamanioCuadrado * 6) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(d4, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 22, (tamanioCuadrado * 7) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(d5, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 21, (tamanioCuadrado * 7) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(d6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 20, (tamanioCuadrado * 7) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(d7, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 22, (tamanioCuadrado * 8) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(d8, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 21, (tamanioCuadrado * 8) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(d9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 20, (tamanioCuadrado * 8) + 18, tamanioCuadrado, tamanioCuadrado));
        
        panelGeneral.add(l1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(l2, new org.netbeans.lib.awtextra.AbsoluteConstraints(tamanioCuadrado + 21, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(l3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*2) + 20, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(l4, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(l5, new org.netbeans.lib.awtextra.AbsoluteConstraints(tamanioCuadrado + 21, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(l6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*2) + 20, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(l7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(l8, new org.netbeans.lib.awtextra.AbsoluteConstraints(tamanioCuadrado + 21, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(l9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*2) + 20, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        
        panelGeneral.add(f1, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 22, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(f2, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 21, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(f3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 20, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(f4, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 22, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(f5, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 21, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(f6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 20, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(f7, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 22, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(f8, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 21, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(f9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 20, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        
        panelGeneral.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*9) + 21, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*10) + 19, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*11) + 18, (tamanioCuadrado * 3) + 20, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*9) + 21, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*10) + 19, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(b6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*11) + 18, (tamanioCuadrado * 4) + 19, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(b7, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*9) + 21, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(b8, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*10) + 19, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        panelGeneral.add(b9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*11) + 18, (tamanioCuadrado * 5) + 18, tamanioCuadrado, tamanioCuadrado));
        
        getContentPane().add(panelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (tamanioCuadrado * 12) + 40, (tamanioCuadrado * 9) + 40));
        
        CuboDao.establecerCubo(cubo, 1);
        
        PrincipalUtil.actualizarTema(Principal.tema, 7);
        pack();
        setLocationRelativeTo(null);
    }
    
    public static javax.swing.JPanel panelGeneral;
    public static javax.swing.JLabel l_cerrar;
    
    public static javax.swing.JPanel u1;
    public static javax.swing.JPanel u2;
    public static javax.swing.JPanel u3;
    public static javax.swing.JPanel u4;
    public static javax.swing.JPanel u5;
    public static javax.swing.JPanel u6;
    public static javax.swing.JPanel u7;
    public static javax.swing.JPanel u8;
    public static javax.swing.JPanel u9;
    
    public static javax.swing.JPanel r1;
    public static javax.swing.JPanel r2;
    public static javax.swing.JPanel r3;
    public static javax.swing.JPanel r4;
    public static javax.swing.JPanel r5;
    public static javax.swing.JPanel r6;
    public static javax.swing.JPanel r7;
    public static javax.swing.JPanel r8;
    public static javax.swing.JPanel r9;
    
    public static javax.swing.JPanel d1;
    public static javax.swing.JPanel d2;
    public static javax.swing.JPanel d3;
    public static javax.swing.JPanel d4;
    public static javax.swing.JPanel d5;
    public static javax.swing.JPanel d6;
    public static javax.swing.JPanel d7;
    public static javax.swing.JPanel d8;
    public static javax.swing.JPanel d9;
    
    public static javax.swing.JPanel l1;
    public static javax.swing.JPanel l2;
    public static javax.swing.JPanel l3;
    public static javax.swing.JPanel l4;
    public static javax.swing.JPanel l5;
    public static javax.swing.JPanel l6;
    public static javax.swing.JPanel l7;
    public static javax.swing.JPanel l8;
    public static javax.swing.JPanel l9;
    
    public static javax.swing.JPanel f1;
    public static javax.swing.JPanel f2;
    public static javax.swing.JPanel f3;
    public static javax.swing.JPanel f4;
    public static javax.swing.JPanel f5;
    public static javax.swing.JPanel f6;
    public static javax.swing.JPanel f7;
    public static javax.swing.JPanel f8;
    public static javax.swing.JPanel f9;
    
    public static javax.swing.JPanel b1;
    public static javax.swing.JPanel b2;
    public static javax.swing.JPanel b3;
    public static javax.swing.JPanel b4;
    public static javax.swing.JPanel b5;
    public static javax.swing.JPanel b6;
    public static javax.swing.JPanel b7;
    public static javax.swing.JPanel b8;
    public static javax.swing.JPanel b9;
}
