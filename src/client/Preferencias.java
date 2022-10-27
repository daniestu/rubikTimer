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
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import utilities.PrincipalUtil;
import utilities.Validations;

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
        
        int fontSize = Validations.menorSize((pantalla.width * 22) / 1920, (pantalla.height * 22) / 1080);
        
        //**VARIABLES**//
        jPanelFondo = new javax.swing.JPanel();
        jPanelGeneral = new javax.swing.JPanel();
        l_temas = new javax.swing.JLabel();
        l_titulo = new javax.swing.JLabel();
        l_segundos = new javax.swing.JLabel();
        l_idioma = new javax.swing.JLabel();
        l_segundosInspeccion = new javax.swing.JLabel();
        jCheckBoxOcultarElementos = new javax.swing.JCheckBox();
        jCheckBoxOcultar_preview = new javax.swing.JCheckBox();
        jCheckBoxPulsacion_larga = new javax.swing.JCheckBox();
        jCheckBoxCrono_raton = new javax.swing.JCheckBox();
        jCheckBoxTiempo_inspeccion = new javax.swing.JCheckBox();
        jPanelTitulo = new javax.swing.JPanel();
        jPanelTemas = new javax.swing.JPanel();
        jPanelIdiomas = new javax.swing.JPanel();
        jPanelOcultarElementos = new javax.swing.JPanel();
        jPanelOcultarPreview = new javax.swing.JPanel();
        jPanelPulsacionLarga = new javax.swing.JPanel();
        jPanelCronoRaton = new javax.swing.JPanel();
        jPanelTiempo_inspeccion = new javax.swing.JPanel();
        jPanelSegundos_inspeccion = new javax.swing.JPanel();
        jButtonAceptar = new javax.swing.JButton();
        jButton1Reset = new javax.swing.JButton();
        jButton1Cancelar = new javax.swing.JButton();
        jTextFieldSegundos = new javax.swing.JTextField();
        jComboBoxTemas = new javax.swing.JComboBox();
        jComboBoxIdiomas = new javax.swing.JComboBox();
        
        setTitle("Configuración");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jPanelFondo.setBackground(new java.awt.Color(255, 219, 219));
        jPanelFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jPanelGeneral.setBackground(new java.awt.Color(255, 219, 219));
        jPanelGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,179,179)));
        jPanelGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jPanelTitulo.setBackground(new java.awt.Color(255,179,179));
        jPanelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        l_titulo.setFont(new java.awt.Font("Times new roman", 1, Validations.menorSize((pantalla.width * 30) / 1920, (pantalla.height * 30) / 1080))); // NOI18N
        l_titulo.setOpaque(true);
        l_titulo.setBackground(new java.awt.Color(255,179,179));
        l_titulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l_titulo.setText("OPCIONES");
        
        Font font = l_titulo.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        l_titulo.setFont(font.deriveFont(attributes));

        
        jPanelTitulo.add(l_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (pantalla.width * 600) / 1920, (pantalla.height * 50) / 1080));
        jPanelGeneral.add(jPanelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 5) / 1920, (pantalla.height * 5) / 1080, (pantalla.width * 590) / 1920, (pantalla.height * 45) / 1080));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jPanelTemas.setBackground(new java.awt.Color(255,219,219));
        jPanelTemas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        l_temas.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_temas.setOpaque(true);
        l_temas.setBackground(new java.awt.Color(255,219,219));
        l_temas.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        l_temas.setText("Tema");
        
        jPanelTemas.add(l_temas, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, 0, (pantalla.width * 100) / 1920, (pantalla.height * 30) / 1080));
        
        jComboBoxTemas.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton b = new JButton();
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setVisible(false);
                return b;
            }
        });
        jComboBoxTemas.setModel(cargarComboBoxTemas());
        jComboBoxTemas.setFont(new java.awt.Font("Segoe UI", 0, fontSize - 1));
        jComboBoxTemas.setRenderer(Principal.listRenderer);
        jComboBoxTemas.setFocusable(false);
        jComboBoxTemas.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                
        jPanelTemas.add(jComboBoxTemas, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 125) / 1920, 2, (pantalla.width * 150) / 1920, (pantalla.height * 26) / 1080));
        
        jPanelGeneral.add(jPanelTemas, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 5) / 1920, (pantalla.height * 50) / 1080, (pantalla.width * 590) / 1920, (pantalla.height * 30) / 1080));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jPanelIdiomas.setBackground(new java.awt.Color(255, 179, 179));
        jPanelIdiomas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        l_idioma.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_idioma.setOpaque(true);
        l_idioma.setBackground(new java.awt.Color(255, 179, 179));
        l_idioma.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        l_idioma.setText("Idioma");
        
        jPanelIdiomas.add(l_idioma, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, 0, (pantalla.width * 100) / 1920, (pantalla.height * 30) / 1080));
        
        jComboBoxIdiomas.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton b = new JButton();
                b.setBorder(BorderFactory.createEmptyBorder());
                b.setVisible(false);
                return b;
            }
        });
        jComboBoxIdiomas.setModel(cargarComboBoxIdiomas());
        jComboBoxIdiomas.setFont(new java.awt.Font("Segoe UI", 0, fontSize - 1));
        jComboBoxIdiomas.setRenderer(Principal.listRenderer);
        jComboBoxIdiomas.setFocusable(false);
        jComboBoxIdiomas.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        
        jPanelIdiomas.add(jComboBoxIdiomas, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 125) / 1920, 2, (pantalla.width * 150) / 1920, (pantalla.height * 26) / 1080));
        
        jPanelGeneral.add(jPanelIdiomas, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 5) / 1920, (pantalla.height * 80) / 1080, (pantalla.width * 590) / 1920, (pantalla.height * 30) / 1080));
        
        
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jPanelOcultarElementos.setBackground(new java.awt.Color(255,219,219));
        jPanelOcultarElementos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jCheckBoxOcultarElementos.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jCheckBoxOcultarElementos.setBackground(new java.awt.Color(255,219,219));
        jCheckBoxOcultarElementos.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jCheckBoxOcultarElementos.setText("Ocultar todos los elementos cuando se cronometra");
        jCheckBoxOcultarElementos.setFocusable(false);
        
        jPanelOcultarElementos.add(jCheckBoxOcultarElementos, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, 0, (pantalla.width * 550) / 1920, (pantalla.height * 30) / 1080));
        jPanelGeneral.add(jPanelOcultarElementos, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 5) / 1920, (pantalla.height * 110) / 1080, (pantalla.width * 590) / 1920, (pantalla.height * 30) / 1080));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jPanelOcultarPreview.setBackground(new java.awt.Color(255, 179, 179));
        jPanelOcultarPreview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jCheckBoxOcultar_preview.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jCheckBoxOcultar_preview.setBackground(new java.awt.Color(255, 179, 179));
        jCheckBoxOcultar_preview.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jCheckBoxOcultar_preview.setText("Ocultar la visualización del cubo");
        jCheckBoxOcultar_preview.setFocusable(false);
        
        jPanelOcultarPreview.add(jCheckBoxOcultar_preview, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, 0, (pantalla.width * 550) / 1920, (pantalla.height * 30) / 1080));
        jPanelGeneral.add(jPanelOcultarPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 5) / 1920, (pantalla.height * 140) / 1080, (pantalla.width * 590) / 1920, (pantalla.height * 30) / 1080));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jPanelPulsacionLarga.setBackground(new java.awt.Color(255,219,219));
        jPanelPulsacionLarga.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jCheckBoxPulsacion_larga.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jCheckBoxPulsacion_larga.setBackground(new java.awt.Color(255,219,219));
        jCheckBoxPulsacion_larga.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jCheckBoxPulsacion_larga.setText("Pulsación larga");
        jCheckBoxPulsacion_larga.setFocusable(false);
        
        jPanelPulsacionLarga.add(jCheckBoxPulsacion_larga, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, 0, (pantalla.width * 550) / 1920, (pantalla.height * 30) / 1080));
        jPanelGeneral.add(jPanelPulsacionLarga, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 5) / 1920, (pantalla.height * 170) / 1080, (pantalla.width * 590) / 1920, (pantalla.height * 30) / 1080));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jPanelCronoRaton.setBackground(new java.awt.Color(255, 179, 179));
        jPanelCronoRaton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jCheckBoxCrono_raton.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jCheckBoxCrono_raton.setBackground(new java.awt.Color(255, 179, 179));
        jCheckBoxCrono_raton.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jCheckBoxCrono_raton.setText("Usar conómetro del ratón");
        jCheckBoxCrono_raton.setFocusable(false);
        
        jPanelCronoRaton.add(jCheckBoxCrono_raton, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, 0, (pantalla.width * 550) / 1920, (pantalla.height * 30) / 1080));
        jPanelGeneral.add(jPanelCronoRaton, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 5) / 1920, (pantalla.height * 200) / 1080, (pantalla.width * 590) / 1920, (pantalla.height * 30) / 1080));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jPanelTiempo_inspeccion.setBackground(new java.awt.Color(255,219,219));
        jPanelTiempo_inspeccion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jCheckBoxTiempo_inspeccion.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jCheckBoxTiempo_inspeccion.setBackground(new java.awt.Color(255,219,219));
        jCheckBoxTiempo_inspeccion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jCheckBoxTiempo_inspeccion.setVerticalAlignment(javax.swing.JTextField.CENTER);
        jCheckBoxTiempo_inspeccion.setText("Usar tiempo de inspección");
        jCheckBoxTiempo_inspeccion.setFocusable(false);
        jCheckBoxTiempo_inspeccion.addItemListener((java.awt.event.ItemEvent evt) -> {
            jCheckBoxTiempo_inspeccionItemStateChanged(evt);
        });
        
        jPanelTiempo_inspeccion.add(jCheckBoxTiempo_inspeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, 0, (pantalla.width * 290) / 1920, (pantalla.height * 30) / 1080));
        jPanelGeneral.add(jPanelTiempo_inspeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 5) / 1920, (pantalla.height * 230) / 1080, (pantalla.width * 590) / 1920, (pantalla.height * 30) / 1080));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        jPanelSegundos_inspeccion.setBackground(new java.awt.Color(255, 179, 179));
        jPanelSegundos_inspeccion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        l_segundosInspeccion.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_segundosInspeccion.setOpaque(true);
        l_segundosInspeccion.setBackground(new java.awt.Color(255,179,179));
        l_segundosInspeccion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        l_segundosInspeccion.setText("Segundos de inspección");
        
        jTextFieldSegundos.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        jTextFieldSegundos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSegundos.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSegundosKeyTyped(evt);
            }
        });
        
        l_segundos.setFont(new java.awt.Font("Segoe UI", 0, Validations.menorSize((pantalla.width * 20) / 1920, (pantalla.height * 20) / 1080))); // NOI18N
        l_segundos.setOpaque(true);
        l_segundos.setBackground(new java.awt.Color(255,179,179));
        l_segundos.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        l_segundos.setText("(segundos)");
        
        jPanelSegundos_inspeccion.add(l_segundosInspeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, 0, (pantalla.width * 270) / 1920, (pantalla.height * 30) / 1080));
        jPanelSegundos_inspeccion.add(jTextFieldSegundos, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 280) / 1920, (pantalla.height * 2) / 1080, (pantalla.width * 50) / 1920, (pantalla.height * 26) / 1080));
        jPanelSegundos_inspeccion.add(l_segundos, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 335) / 1920, (pantalla.height * 2) / 1080, (pantalla.width * 100) / 1920, (pantalla.height * 26) / 1080));
        jPanelGeneral.add(jPanelSegundos_inspeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 5) / 1920, (pantalla.height * 260) / 1080, (pantalla.width * 590) / 1920, (pantalla.height * 30) / 1080));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        jButtonAceptar.setFocusable(false);
        jButtonAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAceptar.setMnemonic('A');
        jButtonAceptar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButtonAceptarActionPerformed(evt);
        });
        
        jButton1Reset.setText("Resetear");
        jButton1Reset.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        jButton1Reset.setFocusable(false);
        jButton1Reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1Reset.setMnemonic('R');
        jButton1Reset.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton1ResetActionPerformed(evt);
        });
        
        jButton1Cancelar.setText("Cancelar");
        jButton1Cancelar.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        jButton1Cancelar.setFocusable(false);
        jButton1Cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1Cancelar.setMnemonic('C');
        jButton1Cancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton1CancelarActionPerformed(evt);
        });
        
        jPanelFondo.add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 210) / 1920, (pantalla.height * 325) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 33) / 1080));
        jPanelFondo.add(jButton1Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 350) / 1920, (pantalla.height * 325) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 33) / 1080));
        jPanelFondo.add(jButton1Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 490) / 1920, (pantalla.height * 325) / 1080, (pantalla.width * 130) / 1920, (pantalla.height * 33) / 1080));
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        jPanelFondo.setBackground(new java.awt.Color(255, 219, 219));
        jPanelFondo.add(jPanelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920, (pantalla.height * 20) / 1080, (pantalla.width * 600) / 1920, (pantalla.height * 295) / 1080));
        getContentPane().add(jPanelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (pantalla.width * 640) / 1920, (pantalla.height * 368) / 1080));
        init();
        
        PrincipalUtil.actualizarTema(Principal.tema, 6);
        PrincipalUtil.cambiarIdioma(Principal.idioma, 5);
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
        int tema = jComboBoxTemas.getSelectedIndex();
        if (tema == 0) {
            Principal.tema = 1;
        }else if (tema == 1) {
            Principal.tema = 2;
        }else if (tema == 2) {
            Principal.tema = 3;
        }else if (tema == 3) {
            Principal.tema = 4;
        }
        
        int idioma = jComboBoxIdiomas.getSelectedIndex();
        if (idioma == 0) {
            Principal.idioma = "es";
        }else {
            Principal.idioma = "en";
        }
        
        Principal.ocultar_todo = jCheckBoxOcultarElementos.isSelected();
        
        Principal.ocultar_preview = jCheckBoxOcultar_preview.isSelected();
        
        Principal.pulsacion_larga = jCheckBoxPulsacion_larga.isSelected();
        
        Principal.cronometro_raton = jCheckBoxCrono_raton.isSelected();
        
        if (jCheckBoxTiempo_inspeccion.isSelected()) {
           Principal.inspeccionActivada = true;
           Principal.inspeccion = true;
           Principal.tiempo_inspeccion = Integer.parseInt(jTextFieldSegundos.getText());
        }else{
           Principal.inspeccionActivada = false;
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
        jComboBoxTemas.setSelectedIndex(0);
        jComboBoxIdiomas.setSelectedIndex(0);
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
            jComboBoxTemas.setSelectedIndex(0);
        }else if (Principal.tema == 2) {
            jComboBoxTemas.setSelectedIndex(1);
        }else if (Principal.tema == 3) {
            jComboBoxTemas.setSelectedIndex(2);
        }else if (Principal.tema == 4) {
            jComboBoxTemas.setSelectedIndex(3);
        }
        
        if (Principal.idioma.equals("es")) {
            jComboBoxIdiomas.setSelectedIndex(0);
        }else {
            jComboBoxIdiomas.setSelectedIndex(1);
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
        
        if (Principal.inspeccionActivada) {
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
    
    private ComboBoxModel cargarComboBoxTemas() {
        String temas[] = new String[5];
        temas[0] = "Principal";
        temas[1] = "Claro";
        temas[2] = "Oscuro";
        temas[3] = "Fantasy";
        ComboBoxModel model = new javax.swing.DefaultComboBoxModel(temas);
        return model;
    }
    
    private ComboBoxModel cargarComboBoxIdiomas() {
        String idiomas[] = new String[4];
        idiomas[0] = "Español";
        idiomas[1] = "Inglés";
        ComboBoxModel model = new javax.swing.DefaultComboBoxModel(idiomas);
        return model;
    }
    
    public static javax.swing.JPanel jPanelFondo;
    public static javax.swing.JPanel jPanelGeneral;
    public static javax.swing.JLabel l_temas;
    public static javax.swing.JLabel l_titulo;
    public static javax.swing.JLabel l_segundos;
    public static javax.swing.JLabel l_idioma;
    public static javax.swing.JLabel l_segundosInspeccion;
    public static javax.swing.JCheckBox jCheckBoxOcultarElementos;
    public static javax.swing.JCheckBox jCheckBoxOcultar_preview;
    public static javax.swing.JCheckBox jCheckBoxPulsacion_larga;
    public static javax.swing.JCheckBox jCheckBoxCrono_raton;
    public static javax.swing.JCheckBox jCheckBoxTiempo_inspeccion;
    public static javax.swing.JPanel jPanelTitulo;
    public static javax.swing.JPanel jPanelTemas;
    public static javax.swing.JPanel jPanelIdiomas;
    public static javax.swing.JPanel jPanelOcultarElementos;
    public static javax.swing.JPanel jPanelOcultarPreview;
    public static javax.swing.JPanel jPanelPulsacionLarga;
    public static javax.swing.JPanel jPanelCronoRaton;
    public static javax.swing.JPanel jPanelTiempo_inspeccion;
    public static javax.swing.JPanel jPanelSegundos_inspeccion;
    public static javax.swing.JButton jButtonAceptar;
    public static javax.swing.JButton jButton1Reset;
    public static javax.swing.JButton jButton1Cancelar;
    public static javax.swing.JTextField jTextFieldSegundos;
    public static javax.swing.JComboBox jComboBoxTemas;
    public static javax.swing.JComboBox jComboBoxIdiomas;

}
