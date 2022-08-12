/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import controller.SesionDao;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import utilities.PrincipalUtil;

/**
 *
 * @author Dani
 */
public class Preferencias extends JFrame {
    
    public Preferencias() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new java.awt.Dimension(640, 308));
        
        //**VARIABLES**//
        jPanelFondo = new javax.swing.JPanel();
        jPanelGeneral = new javax.swing.JPanel();
        l_temas = new javax.swing.JLabel();
        l_titulo = new javax.swing.JLabel();
        l_segundos = new javax.swing.JLabel();
        jRadioButtonTema1 = new javax.swing.JRadioButton();
        jRadioButtonTema2 = new javax.swing.JRadioButton();
        jRadioButtonTema3 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jCheckBoxOcultarElementos = new javax.swing.JCheckBox();
        jCheckBoxOcultar_preview = new javax.swing.JCheckBox();
        jCheckBoxPulsacion_larga = new javax.swing.JCheckBox();
        jCheckBoxCrono_raton = new javax.swing.JCheckBox();
        jCheckBoxTiempo_inspeccion = new javax.swing.JCheckBox();
        jPanelTitulo = new javax.swing.JPanel();
        jPanelTemas = new javax.swing.JPanel();
        jPanelOcultarElementos = new javax.swing.JPanel();
        jPanelOcultarPreview = new javax.swing.JPanel();
        jPanelPulsacionLarga = new javax.swing.JPanel();
        jPanelCronoRaton = new javax.swing.JPanel();
        jPanelTiempo_inspeccion = new javax.swing.JPanel();
        jButtonAceptar = new javax.swing.JButton();
        jButton1Reset = new javax.swing.JButton();
        jButton1Cancelar = new javax.swing.JButton();
        jTextFieldSegundos = new javax.swing.JTextField();
        
        
        setTitle("Configuración");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jPanelFondo.setBackground(new java.awt.Color(255, 219, 219));
        jPanelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jPanelGeneral.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanelGeneral.setBackground(new java.awt.Color(255, 219, 219));
        jPanelGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,179,179)));
        jPanelGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jPanelTitulo.setBackground(new java.awt.Color(255,179,179));
        jPanelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        l_titulo.setFont(new java.awt.Font("Times new roman", 1, 30)); // NOI18N
        l_titulo.setOpaque(true);
        l_titulo.setBackground(new java.awt.Color(255,179,179));
        l_titulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l_titulo.setText("OPCIONES");
        
        Font font = l_titulo.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        l_titulo.setFont(font.deriveFont(attributes));

        
        jPanelTitulo.add(l_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 50));
        jPanelGeneral.add(jPanelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 590, 45));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jPanelTemas.setBackground(new java.awt.Color(255,219,219));
        jPanelTemas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        l_temas.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        l_temas.setOpaque(true);
        l_temas.setBackground(new java.awt.Color(255,219,219));
        l_temas.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        l_temas.setText("Temas");
        
        jPanelTemas.add(l_temas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 70, 30));
        
        buttonGroup1.add(jRadioButtonTema1);
        buttonGroup1.add(jRadioButtonTema2);
        buttonGroup1.add(jRadioButtonTema3);
        
        jRadioButtonTema1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jRadioButtonTema1.setBackground(new java.awt.Color(255,219,219));
        jRadioButtonTema1.setText("Principal");
        jRadioButtonTema1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jRadioButtonTema1.setFocusable(false);
        
        jPanelTemas.add(jRadioButtonTema1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 0, 110, 30));
        
        jRadioButtonTema2.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jRadioButtonTema2.setBackground(new java.awt.Color(255,219,219));
        jRadioButtonTema2.setText("Claro");
        jRadioButtonTema2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jRadioButtonTema2.setFocusable(false);
        
        jPanelTemas.add(jRadioButtonTema2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 80, 30));
        
        jRadioButtonTema3.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jRadioButtonTema3.setBackground(new java.awt.Color(255,219,219));
        jRadioButtonTema3.setText("Oscuro");
        jRadioButtonTema3.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jRadioButtonTema3.setFocusable(false);
        
        jPanelTemas.add(jRadioButtonTema3, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 0, 95, 30));
        
        jPanelGeneral.add(jPanelTemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 50, 590, 30));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jPanelOcultarElementos.setBackground(new java.awt.Color(255, 179, 179));
        jPanelOcultarElementos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jCheckBoxOcultarElementos.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jCheckBoxOcultarElementos.setBackground(new java.awt.Color(255, 179, 179));
        jCheckBoxOcultarElementos.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jCheckBoxOcultarElementos.setText("Ocultar todos los elementos cuando se cronometra");
        jCheckBoxOcultarElementos.setFocusable(false);
        
        jPanelOcultarElementos.add(jCheckBoxOcultarElementos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 520, 30));
        jPanelGeneral.add(jPanelOcultarElementos, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 80, 590, 30));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jPanelOcultarPreview.setBackground(new java.awt.Color(255, 219, 219));
        jPanelOcultarPreview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jCheckBoxOcultar_preview.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jCheckBoxOcultar_preview.setBackground(new java.awt.Color(255, 219, 219));
        jCheckBoxOcultar_preview.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jCheckBoxOcultar_preview.setText("Ocultar la visualización del cubo");
        jCheckBoxOcultar_preview.setFocusable(false);
        
        jPanelOcultarPreview.add(jCheckBoxOcultar_preview, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 335, 30));
        jPanelGeneral.add(jPanelOcultarPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 110, 590, 30));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jPanelPulsacionLarga.setBackground(new java.awt.Color(255, 179, 179));
        jPanelPulsacionLarga.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jCheckBoxPulsacion_larga.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jCheckBoxPulsacion_larga.setBackground(new java.awt.Color(255, 179, 179));
        jCheckBoxPulsacion_larga.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jCheckBoxPulsacion_larga.setText("Pulsación larga");
        jCheckBoxPulsacion_larga.setFocusable(false);
        
        jPanelPulsacionLarga.add(jCheckBoxPulsacion_larga, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 30));
        jPanelGeneral.add(jPanelPulsacionLarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 140, 590, 30));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jPanelCronoRaton.setBackground(new java.awt.Color(255, 219, 219));
        jPanelCronoRaton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jCheckBoxCrono_raton.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jCheckBoxCrono_raton.setBackground(new java.awt.Color(255, 219, 219));
        jCheckBoxCrono_raton.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jCheckBoxCrono_raton.setText("Usar conómetro del ratón");
        jCheckBoxCrono_raton.setFocusable(false);
        
        jPanelCronoRaton.add(jCheckBoxCrono_raton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 280, 30));
        jPanelGeneral.add(jPanelCronoRaton, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 170, 590, 30));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jPanelTiempo_inspeccion.setBackground(new java.awt.Color(255, 179, 179));
        jPanelTiempo_inspeccion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jCheckBoxTiempo_inspeccion.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jCheckBoxTiempo_inspeccion.setBackground(new java.awt.Color(255, 179, 179));
        jCheckBoxTiempo_inspeccion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jCheckBoxTiempo_inspeccion.setVerticalAlignment(javax.swing.JTextField.CENTER);
        jCheckBoxTiempo_inspeccion.setText("Usar tiempo de inspección");
        jCheckBoxTiempo_inspeccion.setFocusable(false);
        jCheckBoxTiempo_inspeccion.addItemListener((java.awt.event.ItemEvent evt) -> {
            jCheckBoxTiempo_inspeccionItemStateChanged(evt);
        });
        
        jTextFieldSegundos.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTextFieldSegundos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSegundos.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSegundosKeyTyped(evt);
            }
        });
        
        l_segundos.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        l_segundos.setOpaque(true);
        l_segundos.setBackground(new java.awt.Color(255,179,179));
        l_segundos.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        l_segundos.setText("(segundos)");
        
        jPanelTiempo_inspeccion.add(l_segundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 2, 100, 26));
        jPanelTiempo_inspeccion.add(jTextFieldSegundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 2, 50, 26));
        jPanelTiempo_inspeccion.add(jCheckBoxTiempo_inspeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 290, 30));
        jPanelGeneral.add(jPanelTiempo_inspeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 200, 590, 30));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setFont(new java.awt.Font("Segoe UI", 0, 22));
        jButtonAceptar.setFocusable(false);
        jButtonAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAceptar.setMnemonic('A');
        jButtonAceptar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonAceptarActionPerformed(evt);
        });
        
        jButton1Reset.setText("Resetear");
        jButton1Reset.setFont(new java.awt.Font("Segoe UI", 0, 22));
        jButton1Reset.setFocusable(false);
        jButton1Reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1Reset.setMnemonic('R');
        jButton1Reset.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton1ResetActionPerformed(evt);
        });
        
        jButton1Cancelar.setText("Cancelar");
        jButton1Cancelar.setFont(new java.awt.Font("Segoe UI", 0, 22));
        jButton1Cancelar.setFocusable(false);
        jButton1Cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1Cancelar.setMnemonic('C');
        jButton1Cancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton1CancelarActionPerformed(evt);
        });
        
        jPanelFondo.add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 265, 120, 33));
        jPanelFondo.add(jButton1Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 265, 120, 33));
        jPanelFondo.add(jButton1Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 265, 120, 33));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jPanelFondo.setBackground(new java.awt.Color(255, 219, 219));
        jPanelFondo.add(jPanelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 600, 235));
        getContentPane().add(jPanelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 308));
        init();
        
        PrincipalUtil.actualizarTema(Principal.tema, 6);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void jTextFieldSegundosKeyTyped(java.awt.event.KeyEvent e) {
        char caracter = e.getKeyChar();
        
        // Verificar si la tecla pulsada no es un digito
        if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
           e.consume();  // ignorar el evento de teclado
        }
    }  
    
    private void jCheckBoxTiempo_inspeccionItemStateChanged(java.awt.event.ItemEvent evt) {
        if (jCheckBoxTiempo_inspeccion.isSelected()) {
            jTextFieldSegundos.setEnabled(true);
            jTextFieldSegundos.requestFocus();
            l_segundos.setVisible(true);
        }else{
            jTextFieldSegundos.setEnabled(false);
            l_segundos.setVisible(false);
        }
    }
    
    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {
        if (jRadioButtonTema1.isSelected()) {
            Principal.tema = 1;
        }else if (jRadioButtonTema2.isSelected()) {
            Principal.tema = 2;
        }else if (jRadioButtonTema3.isSelected()) {
            Principal.tema = 3;
        }
        
        Principal.ocultar_todo = jCheckBoxOcultarElementos.isSelected();
        
        Principal.ocultar_preview = jCheckBoxOcultar_preview.isSelected();
        
        Principal.pulsacion_larga = jCheckBoxPulsacion_larga.isSelected();
        
        Principal.cronometro_raton = jCheckBoxCrono_raton.isSelected();
        
        if (jCheckBoxTiempo_inspeccion.isSelected()) {
           Principal.inspeccion = true;
           Principal.tiempo_inspeccion = Integer.parseInt(jTextFieldSegundos.getText());
        }else{
           Principal.inspeccion = false;
        }
        
        try {
            SesionDao.cargarSesion();
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        
        this.dispose();
        
    }
    
    private void jButton1ResetActionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButtonTema1.setSelected(true);
        jCheckBoxOcultarElementos.setSelected(true);
        jCheckBoxOcultar_preview.setSelected(false);
        jCheckBoxPulsacion_larga.setSelected(true);
        jCheckBoxCrono_raton.setSelected(false);
        jCheckBoxTiempo_inspeccion.setSelected(false);
        jTextFieldSegundos.setText("15");
    }
    private void jButton1CancelarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }
    
    private void init() {
        if (Principal.tema == 1) {
            jRadioButtonTema1.setSelected(true);
        }else if (Principal.tema == 2) {
            jRadioButtonTema2.setSelected(true);
        }else if (Principal.tema == 3) {
            jRadioButtonTema3.setSelected(true);
        }
        
        if (Principal.ocultar_todo) {
            jCheckBoxOcultarElementos.setSelected(true);
        }else{
            jCheckBoxOcultarElementos.setSelected(false);
        }
        
        if (Principal.ocultar_preview) {
            jCheckBoxOcultar_preview.setSelected(true);
        }else{
            jCheckBoxOcultar_preview.setSelected(false);
        }
        
        if (Principal.pulsacion_larga) {
            jCheckBoxPulsacion_larga.setSelected(true);
        }else{
            jCheckBoxPulsacion_larga.setSelected(false);
        }
        
        if (Principal.cronometro_raton) {
            jCheckBoxCrono_raton.setSelected(true);
        }else{
            jCheckBoxCrono_raton.setSelected(false);
        }
        
        if (Principal.inspeccion) {
            jCheckBoxTiempo_inspeccion.setSelected(true);
            jTextFieldSegundos.setEnabled(true);
            l_segundos.setVisible(true);
        }else{
            jCheckBoxTiempo_inspeccion.setSelected(false);
            jTextFieldSegundos.setEnabled(false);
            l_segundos.setVisible(false);
        }
        
        jTextFieldSegundos.setText(Principal.tiempo_inspeccion + "");
    }
    
    public static javax.swing.JPanel jPanelFondo;
    public static javax.swing.JPanel jPanelGeneral;
    public static javax.swing.JLabel l_temas;
    public static javax.swing.JLabel l_titulo;
    public static javax.swing.JLabel l_segundos;
    public static javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JRadioButton jRadioButtonTema1;
    public static javax.swing.JRadioButton jRadioButtonTema2;
    public static javax.swing.JRadioButton jRadioButtonTema3;
    public static javax.swing.JCheckBox jCheckBoxOcultarElementos;
    public static javax.swing.JCheckBox jCheckBoxOcultar_preview;
    public static javax.swing.JCheckBox jCheckBoxPulsacion_larga;
    public static javax.swing.JCheckBox jCheckBoxCrono_raton;
    public static javax.swing.JCheckBox jCheckBoxTiempo_inspeccion;
    public static javax.swing.JPanel jPanelTitulo;
    public static javax.swing.JPanel jPanelTemas;
    public static javax.swing.JPanel jPanelOcultarElementos;
    public static javax.swing.JPanel jPanelOcultarPreview;
    public static javax.swing.JPanel jPanelPulsacionLarga;
    public static javax.swing.JPanel jPanelCronoRaton;
    public static javax.swing.JPanel jPanelTiempo_inspeccion;
    public static javax.swing.JButton jButtonAceptar;
    public static javax.swing.JButton jButton1Reset;
    public static javax.swing.JButton jButton1Cancelar;
    public static javax.swing.JTextField jTextFieldSegundos;
}
