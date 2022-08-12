/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import controller.SolveDao;
import java.awt.Font;
import java.awt.Frame;
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

/**
 *
 * @author Dani
 */
public class InformacionAVG extends JFrame{
    
    private final AVG avg;
    private final ImageIcon iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png"));
    public static ImageIcon iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
    public static ImageIcon iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
        
    public InformacionAVG( AVG avg){
        this.avg = avg;
        
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
                 
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
        panelGeneral.add(l_cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 30, 30));
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
        });
        
        l_img.setIcon(iconLogo);
        panelGeneral.add(l_img, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 10, 90, 90));
        
        l_nombre.setFont(new java.awt.Font("Times new Roman", 1, 35));
        l_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);// NOI18N
        l_nombre.setText("Ao" + avg.getSolves().size() + "  " + avg.getTiempo());
        Font font = l_nombre.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        l_nombre.setFont(font.deriveFont(attributes));
        panelGeneral.add(l_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 360, 50));
        
        jScrollPane1.setBorder(null);
        
        Listado.setFont(new java.awt.Font("Segoe UI", 0, 35)); // NOI18N
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
        
        panelGeneral.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 310, 470));
        getContentPane().add(panelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 640));
        
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
    
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JPanel panelGeneral;
    public static javax.swing.JList Listado;
    public static javax.swing.JLabel l_img;
    public static javax.swing.JLabel l_cerrar;
    public static javax.swing.JLabel l_nombre;
}


