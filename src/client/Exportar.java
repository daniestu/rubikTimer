/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.io.File;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import utilities.FicheroUtil;
import utilities.PrincipalUtil;
import utilities.Validations;

/**
 *
 * @author Dani
 */
public class Exportar extends JFrame{
    public static Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    public static DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
    File ficheroDestino = null;
    File ficheroOrigen = Principal.ficheroSesion;
    
    public static String pathError = "";
    public static String exportError = "";
    
    Exportar(){
        this.setUndecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        panelGeneral = new javax.swing.JPanel();
        l_carpeta = new javax.swing.JLabel();
        l_titulo = new javax.swing.JLabel();
        l_sesion = new javax.swing.JLabel();
        t_ruta = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();
        jButtonExaminar = new javax.swing.JButton();
        comboSesion = new javax.swing.JComboBox();

        setTitle("Exportar sesión");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        int fontSize = Validations.menorSize((pantalla.width * 20) / 1920, (pantalla.height * 20) / 1080);
        
        panelGeneral.setBackground(new java.awt.Color(255, 255, 255));
        panelGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        l_titulo.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        l_titulo.setText("Exportar sesión");
        l_titulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panelGeneral.add(l_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,(pantalla.height * 10) / 1080,(pantalla.width * 750) / 1920,(pantalla.height * 50) / 1080));
        
        l_sesion.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_sesion.setText("Sesión: ");
        panelGeneral.add(l_sesion, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920,(pantalla.height * 80) / 1080,(pantalla.width * 100) / 1920,(pantalla.height * 30) / 1080));
        
        comboSesion.setModel(PrincipalUtil.cargarModeloComboBox());
        comboSesion.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        comboSesion.setBorder(null);
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        comboSesion.setRenderer(listRenderer);
        comboSesion.setFocusable(false);
        comboSesion.setSelectedItem(Principal.ficheroSesion.getName());
        comboSesion.addItemListener((java.awt.event.ItemEvent evt) -> {
            comboSesionItemStateChanged(evt);
        });
        panelGeneral.add(comboSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 120) / 1920,(pantalla.height * 80) / 1080,(pantalla.width * 150) / 1920,(pantalla.height * 30) / 1080));

        l_carpeta.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        l_carpeta.setText("Exportar a: ");
        panelGeneral.add(l_carpeta, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 20) / 1920,(pantalla.height * 120) / 1080,(pantalla.width * 100) / 1920,(pantalla.height * 30) / 1080));

        t_ruta.setFont(new java.awt.Font("Segoe UI", 0, fontSize)); // NOI18N
        t_ruta.setEditable(false);
        panelGeneral.add(t_ruta, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 120) / 1920,(pantalla.height * 120) / 1080,(pantalla.width * 500) / 1920,(pantalla.height * 30) / 1080));
        
        jButtonExaminar.setText("Examinar");
        jButtonExaminar.setFocusable(false);
        jButtonExaminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonExaminar.addActionListener((java.awt.event.ActionEvent evt) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showOpenDialog(fileChooser);
            ficheroDestino = fileChooser.getSelectedFile();
            if (ficheroDestino != null){
                ficheroDestino = new File(ficheroDestino.getAbsolutePath() + "\\" + comboSesion.getSelectedItem().toString() + ".txt");
                t_ruta.setText(ficheroDestino.getAbsolutePath());
            }
        });
        panelGeneral.add(jButtonExaminar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 630) / 1920,(pantalla.height * 120) / 1080,(pantalla.width * 100) / 1920,(pantalla.height * 30) / 1080));

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setFocusable(false);
        jButtonAceptar.setMnemonic('A');
        jButtonAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAceptar.addActionListener((java.awt.event.ActionEvent evt) -> {
            submit();
        });
        panelGeneral.add(jButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 460) / 1920,(pantalla.height * 170) / 1080,(pantalla.width * 130) / 1920,(pantalla.height * 30) / 1080));
        
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setFocusable(false);
        jButtonCancelar.setMnemonic('C');
        jButtonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancelar.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.dispose();
        });
        panelGeneral.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 600) / 1920,(pantalla.height * 170) / 1080,(pantalla.width * 130) / 1920,(pantalla.height * 30) / 1080));
        
        getContentPane().add(panelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (pantalla.width * 750) / 1920, (pantalla.height * 220) / 1080));
        
        PrincipalUtil.actualizarTema(Principal.tema, 9);
        PrincipalUtil.cambiarIdioma(Principal.idioma, 7);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void submit() {
        if (t_ruta.getText().equals("") || t_ruta.getText() == null) {
            PrincipalUtil.Error(pathError);
        }else {
            if (!FicheroUtil.exportarSolves(ficheroDestino, ficheroOrigen)) {
                PrincipalUtil.Error(exportError);
            }else{
                this.dispose();
            }
        }
    }
    
    public static javax.swing.JLabel l_carpeta;
    public static javax.swing.JLabel l_titulo;
    public static javax.swing.JLabel l_sesion;
    public static javax.swing.JPanel panelGeneral;
    public javax.swing.JTextField t_ruta;
    public static javax.swing.JButton jButtonAceptar;
    public static javax.swing.JButton jButtonCancelar;
    public static javax.swing.JButton jButtonExaminar;
    public static javax.swing.JComboBox comboSesion;

    private void comboSesionItemStateChanged(ItemEvent evt) {
        ficheroOrigen = new File("sesiones/" + comboSesion.getSelectedItem().toString());
        if (ficheroDestino != null) {
            File f = new File(ficheroDestino.getParent());
            t_ruta.setText(f.getAbsolutePath() + "\\" + comboSesion.getSelectedItem().toString() + ".txt");
        }
    }
}
