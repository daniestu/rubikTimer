/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import controller.SolveDao;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import model.AVG;
import model.Solve;
import utilities.PrincipalUtil;
import utilities.Validations;


/**
 *
 * @author Dani
 */
public class InformacionAVG extends JFrame{
    
    private final AVG avg;
    public static ImageIcon iconLogo;
    public static ImageIcon iconCerrar;
    public static ImageIcon iconCerrarHover;
    public static Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    
    public InformacionAVG( AVG avg){
        this.avg = avg;
        
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        int logoDimension = Validations.menorSize((pantalla.height * 90) / 1080, (pantalla.width * 90) / 1920);
        int iconDimension = Validations.menorSize((pantalla.height * 30) / 1080, (pantalla.width * 30) / 1920);
        
        ImageIcon iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png"));
        Image newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
        iconLogo = new ImageIcon(newimg);
        ImageIcon iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
        newimg = iconCerrar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
        iconCerrar = new ImageIcon(newimg);
        ImageIcon iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
        newimg = iconCerrarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
        iconCerrarHover = new ImageIcon(newimg);
    
        jScrollPane1 = new javax.swing.JScrollPane();
        panelGeneral = new javax.swing.JPanel();
        Listado = new javax.swing.JList();
        l_img = new javax.swing.JLabel();
        l_cerrar = new javax.swing.JLabel();
        l_nombre = new javax.swing.JLabel();
        
        setTitle("Información AVG");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        panelGeneral.setBackground(new java.awt.Color(238, 238, 238));
        panelGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        l_cerrar.setIcon(iconCerrar);
        panelGeneral.add(l_cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 340) / 1920, (pantalla.height * 10) / 1080, iconDimension, iconDimension));
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
        });
        
        l_img.setIcon(iconLogo);
        panelGeneral.add(l_img, new org.netbeans.lib.awtextra.AbsoluteConstraints((((pantalla.width * 380) / 1920)-logoDimension) / 2, (pantalla.height * 10) / 1080, logoDimension, logoDimension));
        
        l_nombre.setFont(new java.awt.Font("Times new Roman", 1, (pantalla.width * 35) / 1920));
        l_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);// NOI18N
        l_nombre.setText("Ao" + avg.getSolves().size() + "  " + avg.getTiempo());
        Font font = l_nombre.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        l_nombre.setFont(font.deriveFont(attributes));
        panelGeneral.add(l_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, (pantalla.height * 110)/1080, (pantalla.width * 360) / 1920, (pantalla.height * 50)/1080));
        
        jScrollPane1.setBorder(null);
        
        Listado.setFont(new java.awt.Font("Segoe UI", 0, (pantalla.width * 35) / 1920)); // NOI18N
        Listado.setEnabled(true);
        Listado.setFocusable(false);
        Listado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Listado.setModel(SolveDao.cargarSolves(avg.getSolves(), 1));
        Listado.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListadoMouseClicked(evt);
            }
        });
        DefaultListCellRenderer cellRenderer = (DefaultListCellRenderer)Listado.getCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants .CENTER);

        jScrollPane1.setViewportView(Listado);
        
        panelGeneral.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 40) / 1920, (pantalla.height * 170) / 1080, (pantalla.width * 310) / 1920, (pantalla.height * 470) / 1080));
        getContentPane().add(panelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (pantalla.width * 380) / 1920, (pantalla.height * 640)/1080));
        
        PrincipalUtil.actualizarTema(Principal.tema, 1);
        pack();
        setLocationRelativeTo(null);
    }
    
    public void ListadoMouseClicked(java.awt.event.MouseEvent evt) {
        
        Solve s = avg.getSolves().get(Listado.getSelectedIndex());
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información del Solve")) {
                i.dispose();
            }
        }
        
        InformacionSolve informacionSolve = new InformacionSolve(s);
        
    }
    
    private void l_cerrarMouseClicked(MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información AVG")) {
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
    
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JPanel panelGeneral;
    public static javax.swing.JList Listado;
    public static javax.swing.JLabel l_img;
    public static javax.swing.JLabel l_cerrar;
    public static javax.swing.JLabel l_nombre;
}


