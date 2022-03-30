/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import model.Cubo;
import controller.CuboDao;
import controller.SesionDao;
import controller.SolveDao;
import model.Sesion;
import model.Solve;
import utils.CronometroUtil;
import utils.FicheroUtil;
import utils.PrincipalUtil;

/**
 *
 * @author Dani
 */
public final class Principal extends JFrame implements KeyListener {

    public static Properties prop = new Properties();
    public static File ficheroSesion;
    
    public static Dimension resolucion = Toolkit.getDefaultToolkit().getScreenSize();
    public static Dimension pantalla;
    
    public static int tema;
    public static boolean ocultar_todo;
    public static boolean ocultar_preview;
    public static boolean pulsacion_larga;
    public static boolean cronometro_raton;
    public static boolean inspeccion;
    public static int tiempo_inspeccion;
    
    public static double anchoActual = 0;
    public static double altoActual = 0;
    
    public static ArrayList<Solve> solves = new ArrayList();
    public static ArrayList<String> scrambles = new ArrayList<>();
    public boolean corriendo = false;
    private boolean sufic = false;
    public static Solve mejor;
    public static Solve peor;
    public static String avg, currentAo5, currentAo12, currentAo100;
    public Timer t, t2, t3, t4;
    public static int m, s, cs, ms;
    public static String movimientos[] = {"R2", "U2", "L2", "D2", "F2", "B2", "R", "U", "L", "D", "F", "B", "R'", "U'", "L'", "D'", "F'", "B'"};
    public DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
    ImageIcon iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo30x30.png"));
    ImageIcon iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
    ImageIcon iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover.png"));
    ImageIcon iconMinimizar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/minimizar.png"));
    ImageIcon iconMinimizarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/minimizar-hover.png"));
    ImageIcon iconMaximizar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/maximizar.png"));
    ImageIcon iconMaximizarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/maximizar-hover.png"));
    
    public ActionListener sec = (ActionEvent e) -> {
        if (!sufic) {
            t_tiempo.setForeground(new java.awt.Color(0, 204, 0));
            sufic = true;
            tecla_presionada();
        }else{
            t_tiempo.setForeground(new java.awt.Color(0, 204, 0));
        }
    };
    
    public ActionListener segundo = (ActionEvent e) -> {
        ++s;
        if (s == 60) {
            ++m;
            s = 0;
        }
        cs = 0;
        ms = 0;
        
        CronometroUtil.actualizarTiempo();
    };
    public ActionListener centesima = (ActionEvent e) -> {
        ++cs;
        if (cs == 10) {
            cs = 0;
        }
        CronometroUtil.actualizarTiempo();
    };
    public ActionListener milesima = (ActionEvent e) -> {
        ++ms;
        if (ms == 10) {
            ms = 0;
        }
        CronometroUtil.actualizarTiempo();
    };

    Principal() {
        setIconImage(getIconImage());
        ficheroSesion = new File("sesiones/Default");

        try {
            init();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un preblema al arrancar la aplicación", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
        
        resolucion = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setSize(resolucion);
        
        pantalla = this.getSize();
        t = new Timer(1000, segundo);
        t2 = new Timer(100, centesima);
        t3 = new Timer(10, milesima);
        t4 = new Timer (1000, sec);

        this.setUndecorated(true);
        this.addKeyListener(this);
        this.setResizable(true);
        this.setVisible(true);

        //DECLARACION DE VARIABLES
        Border dashed = BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5);
        
        Border empty = BorderFactory.createEmptyBorder(0, -1, 0, 0);
        Border compoundRightBot = new CompoundBorder(empty, dashed);
        empty = BorderFactory.createEmptyBorder(0, 0, 0, -1);
        Border compoundLeftBot = new CompoundBorder(empty, dashed);
        empty = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        Border compoundBot = new CompoundBorder(empty, dashed);
        empty = BorderFactory.createEmptyBorder(0, 0, -1, -1);
        Border compoundLeft = new CompoundBorder(empty, dashed);
        empty = BorderFactory.createEmptyBorder(0, -1, -1, 0);
        Border compoundRight = new CompoundBorder(empty, dashed);

        jScrollPane1 = new javax.swing.JScrollPane();
        campo_sesion = new javax.swing.JTextField();
        Listado = new javax.swing.JList();
        jComboBox1 = new javax.swing.JComboBox();
        jPanelScramble = new javax.swing.JPanel();
        jPanelAside = new javax.swing.JPanel();
        jPanelGeneral = new javax.swing.JPanel();
        jPanelTiempos = new javax.swing.JPanel();
        jPanelEstadisticas = new javax.swing.JPanel();
        jPanelPreview = new javax.swing.JPanel();
        t_tiempo = new javax.swing.JTextField();
        l_snombre = new javax.swing.JLabel();
        l_scramble = new javax.swing.JLabel();
        l_total = new javax.swing.JLabel();
        l_best = new javax.swing.JLabel();
        l_worst = new javax.swing.JLabel();
        l_ao5 = new javax.swing.JLabel();
        l_ao12 = new javax.swing.JLabel();
        l_ao100 = new javax.swing.JLabel();
        l_totalavg = new javax.swing.JLabel();
        l_total_2 = new javax.swing.JLabel();
        l_best_2 = new javax.swing.JLabel();
        l_worst_2 = new javax.swing.JLabel();
        l_ao5_2 = new javax.swing.JLabel();
        l_ao12_2 = new javax.swing.JLabel();
        l_ao100_2 = new javax.swing.JLabel();
        l_totalavg_2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuCerrar = new javax.swing.JMenu();
        jMenuMinimizar = new javax.swing.JMenu();
        jMenuLogo = new javax.swing.JMenu();
        m_new = new javax.swing.JMenuItem();
        m_info = new javax.swing.JMenuItem();
        m_sig = new javax.swing.JMenuItem();
        m_ant = new javax.swing.JMenuItem();
        m_pref = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        m_cerrar = new javax.swing.JMenuItem();

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
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rubik Timer");
        setPreferredSize(pantalla);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
       
        //BARRA MENU
        jMenuBar1.setBackground(new java.awt.Color(240,240,240));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(pantalla.width, 35));
        
        //MENU ITEMS
        jMenuLogo.setIcon(iconLogo);
        jMenuLogo.enable(false);
        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jMenuCerrar.setIcon(iconCerrar);
        jMenuCerrar.setBackground(Color.red);
        jMenuCerrar.disable();
        jMenuCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenu2MouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenu2MouseExited(evt);
            }
        });
        
        jMenuMinimizar.setIcon(iconMinimizar);
        jMenuMinimizar.setBackground(Color.red);
        jMenuMinimizar.disable();
        jMenuMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuMinimizarMouseClicked(evt);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jMenuMinimizarMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenuMinimizarMouseExited(evt);
            }
        });

        //SUBMENU ITEMS 
        m_new.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        m_new.setText("Nueva Sesión");
        m_new.setFont(new java.awt.Font("Segoe UI", 0, 20));
        m_new.addActionListener((java.awt.event.ActionEvent evt) -> {
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Nueva sesión")) {
                    i.dispose();
                }
            }
            NombreSesion nombreSesion = new NombreSesion();
        });
        m_info.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        m_info.setText("Información de la sesión");
        m_info.setFont(new java.awt.Font("Segoe UI", 0, 20));
        m_info.addActionListener((java.awt.event.ActionEvent evt) -> {
            Sesion s1 = SesionDao.infoSesion();
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Información de la sesión")) {
                    i.dispose();
                }
            }
            InformacionSesion informacionSesion = new InformacionSesion(s1);
        });
        m_cerrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        m_cerrar.setText("Cerrar");
        m_cerrar.setFont(new java.awt.Font("Segoe UI", 0, 20));
        m_cerrar.addActionListener((java.awt.event.ActionEvent evt) -> {
            Frame[] frames = Principal.getFrames();
            for (Frame frame : frames) {
                frame.dispose();
            }
        });
        m_sig.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        m_sig.setText("Nueva mezcla");
        m_sig.setFont(new java.awt.Font("Segoe UI", 0, 20));
        m_sig.addActionListener((java.awt.event.ActionEvent evt) -> {
            String scram = PrincipalUtil.generarScramble();
            l_scramble.setText(scram);
            Cubo c = CuboDao.generarCubo(scram);
            CuboDao.establecerCubo(c);
        });
        m_ant.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        m_ant.setText("Mezcla Anterior");
        m_ant.setFont(new java.awt.Font("Segoe UI", 0, 20));
        m_ant.setEnabled(false);
        m_ant.addActionListener((java.awt.event.ActionEvent evt) -> {
            scrambles.remove(scrambles.size()-1);
            l_scramble.setText(scrambles.get(scrambles.size()-1));
            Cubo c = CuboDao.generarCubo(scrambles.get(scrambles.size()-1));
            CuboDao.establecerCubo(c);
            
            if (scrambles.size()<2) {
                m_ant.setEnabled(false);
            }
        });
        
        m_pref.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        m_pref.setText("Configuración");
        m_pref.setFont(new java.awt.Font("Segoe UI", 0, 20));
        m_pref.addActionListener((java.awt.event.ActionEvent evt) -> {
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Configuración")) {
                    i.dispose();
                }
            }
            Preferencias preferencias = new Preferencias();
        });

        jMenu1.add(m_sig);
        jMenu1.add(m_ant);
        jMenu1.add(m_new);
        jMenu1.add(m_info);
        jMenu1.add(m_pref);
        jMenu1.add(jSeparator1);
        jMenu1.add(m_cerrar);

        jMenuBar1.add(jMenuLogo);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(Box.createHorizontalGlue());
        jMenuBar1.add(jMenuMinimizar);
        jMenuBar1.add(jMenuCerrar);
        
        setJMenuBar(jMenuBar1);

        //PANEL GENERAL
        jPanelGeneral.setBackground(new java.awt.Color(205, 242, 154));
        jPanelGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelGeneral.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        //CRONOMETRO
        t_tiempo.setEditable(false);
        t_tiempo.setBackground(new java.awt.Color(205, 242, 154));
        int tamanioTiempo = (pantalla.width * 230) /1920;
        t_tiempo.setFont(new java.awt.Font("Segoe UI", 0, tamanioTiempo));
        t_tiempo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t_tiempo.setText("00:00:00");
        t_tiempo.setBorder(null);
        t_tiempo.setOpaque(false);
        t_tiempo.setFocusable(false);
        t_tiempo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        
        //PANEL DEL SCRAMBLE
        jPanelScramble.setBackground(new java.awt.Color(255, 204, 204));
        jPanelScramble.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelScramble.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelScramble.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        int tamanioScramble = (pantalla.width * 50) /1920;
        l_scramble.setFont(new java.awt.Font("Segoe UI", 0, tamanioScramble));
        String scram = PrincipalUtil.generarScramble();
        l_scramble.setText(scram);
        Cubo c = CuboDao.generarCubo(scram);
        
        l_scramble.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanelScramble.add(l_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, pantalla.width - 220, 90));

        jPanelGeneral.add(jPanelScramble, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, pantalla.width - 220, 90));
        
        //PANEL DE LA IZQUIERDA
        jScrollPane1.setBorder(null);
        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelAside.setBackground(new java.awt.Color(255, 204, 204));
        jPanelAside.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelAside.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelAside.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        Listado.setBackground(new java.awt.Color(255, 204, 204));
        Listado.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        Listado.setEnabled(true);
        Listado.setFocusable(false);
        Listado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Listado.setModel(SolveDao.cargarSolves());
        Listado.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListadoMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(Listado);

        //PANEL TIEMPOS DENTRO DEL ASIDE
        jPanelTiempos.setBackground(new java.awt.Color(255, 204, 204));
        jPanelTiempos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelTiempos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(180, 180, 180), 1, true));
        jPanelTiempos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        jPanelTiempos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 2, 190, pantalla.height - 442));

        jPanelAside.add(jPanelTiempos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 395, 200, pantalla.height - 437));

        jPanelGeneral.add(jPanelAside, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, pantalla.height - 35));
        
        jPanelGeneral.add(t_tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, ((pantalla.height - 35) / 2) - 300, pantalla.width-2, 200));

        //ETIQUETA DE SESIÓN Y LISTADO DE SESIONES
        campo_sesion.setFont(new java.awt.Font("Segoe UI", 0, 30));
        campo_sesion.setText(ficheroSesion.getName());
        campo_sesion.setEditable(false);
        campo_sesion.setFocusable(false);
        campo_sesion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        l_snombre.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        l_snombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        l_snombre.setText("Sesión");

        jComboBox1.setModel(PrincipalUtil.cargarModeloComboBox());
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 30));
        jComboBox1.setBorder(null);
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        jComboBox1.setRenderer(listRenderer);
        jComboBox1.setFocusable(false);
        jComboBox1.setSelectedItem(ficheroSesion.getName());
        jComboBox1.addItemListener((java.awt.event.ItemEvent evt) -> {
            jComboBox1ItemStateChanged(evt);
        });

        //PANEL DE ESTADISTICAS DE LA SESIÓN
        jPanelEstadisticas.setBackground(new java.awt.Color(255, 204, 204));
        jPanelEstadisticas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelEstadisticas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        l_total.setFont(new java.awt.Font("Times New Roman", 1, 26));
        l_total.setBackground(Color.red);
        l_total.setText(" Total");
        l_total.setBorder(compoundLeft);
        l_total_2.setFont(new java.awt.Font("Times New Roman", 0, 26));
        l_total_2.setBackground(Color.red);
        l_total_2.setBorder(compoundRight);

        l_best.setFont(new java.awt.Font("Times New Roman", 1, 26));
        l_best.setText(" Mejor");
        l_best.setBorder(compoundLeft);
        l_best_2.setFont(new java.awt.Font("Times New Roman", 0, 26));
        l_best_2.setBorder(compoundRight);
        l_best_2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l_best_2MouseClicked(evt);
            }
        });

        l_worst.setFont(new java.awt.Font("Times New Roman", 1, 26));
        l_worst.setText(" Peor");
        l_worst.setBorder(compoundLeft);
        l_worst_2.setFont(new java.awt.Font("Times New Roman", 0, 26));
        l_worst_2.setBorder(compoundRight);
        l_worst_2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l_worst_2MouseClicked(evt);
            }
        });
        
        l_ao5.setFont(new java.awt.Font("Times New Roman", 1, 26));
        l_ao5.setText(" Ao5");
        l_ao5.setBorder(compoundLeft);
        l_ao5_2.setFont(new java.awt.Font("Times New Roman", 0, 26));
        l_ao5_2.setBorder(compoundRight);

        l_ao12.setFont(new java.awt.Font("Times New Roman", 1, 26));
        l_ao12.setText(" Ao12");
        l_ao12.setBorder(compoundLeft);
        l_ao12_2.setFont(new java.awt.Font("Times New Roman", 0, 26));
        l_ao12_2.setBorder(compoundRight);

        l_ao100.setFont(new java.awt.Font("Times New Roman", 1, 26));
        l_ao100.setText(" Ao100");
        l_ao100.setBorder(compoundLeft);
        l_ao100_2.setFont(new java.awt.Font("Times New Roman", 0, 26));
        l_ao100_2.setBorder(compoundRight);

        l_totalavg.setFont(new java.awt.Font("Times New Roman", 1, 26));
        l_totalavg.setText(" Media");
        l_totalavg.setBorder(compoundLeftBot);
        l_totalavg_2.setFont(new java.awt.Font("Times New Roman", 0, 26));
        l_totalavg_2.setBorder(compoundRightBot);

        jPanelEstadisticas.add(l_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));
        jPanelEstadisticas.add(l_best, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 100, 40));
        jPanelEstadisticas.add(l_worst, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 100, 40));
        jPanelEstadisticas.add(l_ao5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 100, 40));
        jPanelEstadisticas.add(l_ao12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 100, 40));
        jPanelEstadisticas.add(l_ao100, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 100, 40));
        jPanelEstadisticas.add(l_totalavg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 100, 40));
        
        
        jPanelEstadisticas.add(l_total_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 100, 40));
        jPanelEstadisticas.add(l_best_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 100, 40));
        jPanelEstadisticas.add(l_worst_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 100, 40));
        jPanelEstadisticas.add(l_ao5_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 100, 40));
        jPanelEstadisticas.add(l_ao12_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 100, 40));
        jPanelEstadisticas.add(l_ao100_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 100, 40));
        jPanelEstadisticas.add(l_totalavg_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 100, 40));
        

        jPanelAside.add(jPanelEstadisticas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 105, 200, 280));

        jPanelAside.add(l_snombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 160, 40));
        jPanelAside.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 55, 212, 40));
        
        int tamanioCuadrado = (pantalla.height * 30) / 1080;
        int tamanioPreviewAlto = (tamanioCuadrado * 9) + 20;
        int tamanioPreviewAncho = (tamanioCuadrado * 12) + 20;
        
        jPanelPreview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        //jPanelPreview.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelPreview.setBackground(new java.awt.Color(205, 242, 154));
        
        
        u1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u1.setBackground(Color.white);
        u2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u2.setBackground(Color.white);
        u3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u3.setBackground(Color.white);
        u4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u4.setBackground(Color.white);
        u5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u5.setBackground(Color.white);
        u6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u6.setBackground(Color.white);
        u7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u7.setBackground(Color.white);
        u8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u8.setBackground(Color.white);
        u9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        u9.setBackground(Color.white);
        
        f1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f1.setBackground(Color.green);
        f2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f2.setBackground(Color.green);
        f3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f3.setBackground(Color.green);
        f4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f4.setBackground(Color.green);
        f5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f5.setBackground(Color.green);
        f6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f6.setBackground(Color.green);
        f7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f7.setBackground(Color.green);
        f8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f8.setBackground(Color.green);
        f9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        f9.setBackground(Color.green);
        
        d1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d1.setBackground(Color.yellow);
        d2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d2.setBackground(Color.yellow);
        d3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d3.setBackground(Color.yellow);
        d4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d4.setBackground(Color.yellow);
        d5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d5.setBackground(Color.yellow);
        d6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d6.setBackground(Color.yellow);
        d7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d7.setBackground(Color.yellow);
        d8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d8.setBackground(Color.yellow);
        d9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        d9.setBackground(Color.yellow);
        
        l1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l1.setBackground(new java.awt.Color(255, 131, 0));
        l2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l2.setBackground(new java.awt.Color(255, 131, 0));
        l3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l3.setBackground(new java.awt.Color(255, 131, 0));
        l4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l4.setBackground(new java.awt.Color(255, 131, 0));
        l5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l5.setBackground(new java.awt.Color(255, 131, 0));
        l6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l6.setBackground(new java.awt.Color(255, 131, 0));
        l7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l7.setBackground(new java.awt.Color(255, 131, 0));
        l8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l8.setBackground(new java.awt.Color(255, 131, 0));
        l9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        l9.setBackground(new java.awt.Color(255, 131, 0));
        
        r1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r1.setBackground(Color.red);
        r2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r2.setBackground(Color.red);
        r3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r3.setBackground(Color.red);
        r4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r4.setBackground(Color.red);
        r5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r5.setBackground(Color.red);
        r6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r6.setBackground(Color.red);
        r7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r7.setBackground(Color.red);
        r8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r8.setBackground(Color.red);
        r9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        r9.setBackground(Color.red);
        
        b1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b1.setBackground(Color.blue);
        b2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b2.setBackground(Color.blue);
        b3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b3.setBackground(Color.blue);
        b4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b4.setBackground(Color.blue);
        b5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b5.setBackground(Color.blue);
        b6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b6.setBackground(Color.blue);
        b7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b7.setBackground(Color.blue);
        b8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b8.setBackground(Color.blue);
        b9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        b9.setBackground(Color.blue);
        
        
        jPanelPreview.add(u1, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 12, 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(u2, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 11, 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(u3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 10, 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(u4, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 12, tamanioCuadrado+9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(u5, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 11, tamanioCuadrado+9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(u6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 10, tamanioCuadrado+9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(u7, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 12, (tamanioCuadrado * 2) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(u8, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 11, (tamanioCuadrado * 2) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(u9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 10, (tamanioCuadrado * 2) + 8, tamanioCuadrado, tamanioCuadrado));
        
        jPanelPreview.add(r1, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*6) + 12, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(r2, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*7) + 10, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(r3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*8) + 9, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(r4, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*6) + 12, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(r5, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*7) + 10, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(r6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*8) + 9, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(r7, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*6) + 12, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(r8, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*7) + 10, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(r9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*8) + 9, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        
        jPanelPreview.add(d1, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 12, (tamanioCuadrado * 6) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(d2, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 11, (tamanioCuadrado * 6) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(d3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 10, (tamanioCuadrado * 6) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(d4, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 12, (tamanioCuadrado * 7) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(d5, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 11, (tamanioCuadrado * 7) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(d6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 10, (tamanioCuadrado * 7) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(d7, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 12, (tamanioCuadrado * 8) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(d8, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 11, (tamanioCuadrado * 8) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(d9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 10, (tamanioCuadrado * 8) + 8, tamanioCuadrado, tamanioCuadrado));
        
        jPanelPreview.add(l1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(l2, new org.netbeans.lib.awtextra.AbsoluteConstraints(tamanioCuadrado + 11, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(l3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*2) + 10, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(l4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(l5, new org.netbeans.lib.awtextra.AbsoluteConstraints(tamanioCuadrado + 11, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(l6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*2) + 10, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(l7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(l8, new org.netbeans.lib.awtextra.AbsoluteConstraints(tamanioCuadrado + 11, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(l9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*2) + 10, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        
        jPanelPreview.add(f1, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 12, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(f2, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 11, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(f3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 10, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(f4, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 12, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(f5, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 11, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(f6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 10, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(f7, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*3) + 12, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(f8, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*4) + 11, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(f9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*5) + 10, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        
        jPanelPreview.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*9) + 11, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*10) + 9, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*11) + 8, (tamanioCuadrado * 3) + 10, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*9) + 11, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*10) + 9, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(b6, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*11) + 8, (tamanioCuadrado * 4) + 9, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(b7, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*9) + 11, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(b8, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*10) + 9, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        jPanelPreview.add(b9, new org.netbeans.lib.awtextra.AbsoluteConstraints((tamanioCuadrado*11) + 8, (tamanioCuadrado * 5) + 8, tamanioCuadrado, tamanioCuadrado));
        
        
        
        jPanelGeneral.add(jPanelPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(pantalla.width - tamanioPreviewAncho-2, pantalla.height - tamanioPreviewAlto - 35, tamanioPreviewAncho, tamanioPreviewAlto));
        
        getContentPane().add(jPanelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, pantalla.width, pantalla.height));
        
        CuboDao.establecerCubo(c);
        
        pack();
        /*
        En sesión que haya la opción de poner el cubo que estas utilizando (tipo de cubos)
        
        Opcion de quitar pantalla completa
        
        Poner preview en los tiempos de las sesiones
      
        
        ¿Es posible cerrar las ventanas response al clickar en la ventana main?
        */

        this.setExtendedState(MAXIMIZED_BOTH);

        try {
            SesionDao.cargarSesion();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     //Icono del programa
    @Override
    public Image getIconImage () {
        
        Image RetValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/logo.png"));
        
        return RetValue;
    }
    
    public void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {
        Frame frame [] = JFrame.getFrames();
        for (Frame f : frame) {
            f.dispose();
        }
        this.dispose();
    }
    public void jMenu2MouseEntered(java.awt.event.MouseEvent evt) {
        jMenuCerrar.setIcon(iconCerrarHover);
    } 
    public void jMenu2MouseExited(MouseEvent evt) {
        jMenuCerrar.setIcon(iconCerrar);
    }
    
    public void jMenuMinimizarMouseClicked(java.awt.event.MouseEvent evt) {
        this.setExtendedState(ICONIFIED);
        
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (!(i.getTitle().equals("Rubik Timer"))) {
                i.dispose();
            }
        }
        
    }
    public void jMenuMinimizarMouseEntered(java.awt.event.MouseEvent evt) {
        jMenuMinimizar.setIcon(iconMinimizarHover);
    } 
    public void jMenuMinimizarMouseExited(MouseEvent evt) {
        jMenuMinimizar.setIcon(iconMinimizar);
    }
   
    
            
    public void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {
        String nombre = jComboBox1.getSelectedItem().toString();
        ficheroSesion = new File("sesiones/" + nombre);
        t_tiempo.setText("00:00:00");
        try {
            SesionDao.cargarSesion();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void ListadoMouseClicked(java.awt.event.MouseEvent evt) {
        if (!solves.isEmpty() && !corriendo) {
            Solve s = solves.get(Listado.getSelectedIndex());
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Información del Solve")) {
                    i.dispose();
                }
            }
            InformacionSolve informacionSolve = new InformacionSolve(s);
        }
    }
    
    private void l_best_2MouseClicked(java.awt.event.MouseEvent evt) {
        if (mejor != null && !corriendo) {
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Información del Solve")) {
                    i.dispose();
                }
            }
            InformacionSolve informacionSolve = new InformacionSolve(mejor);
        }
    }
    
    private void l_worst_2MouseClicked(java.awt.event.MouseEvent evt) {
        if (peor != null && !corriendo) {
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Información del Solve")) {
                    i.dispose();
                }
            }
            InformacionSolve informacionSolve = new InformacionSolve(peor);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            tecla_presionada();
        }

    }
    
    private void tecla_presionada() {
        if (corriendo) {
            t.stop();
            t2.stop();
            t3.stop();

            jPanelAside.setVisible(true);
            jPanelScramble.setVisible(true);
            jPanelPreview.setVisible(true);
            jComboBox1.setEnabled(true);
            jMenu1.setEnabled(true);

            try {
                Date fecha = new Date();
                DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

                FileWriter fw = new FileWriter(ficheroSesion, true);
                int linea = FicheroUtil.establecerLinea(ficheroSesion);
                fw.write(linea + ";" + l_scramble.getText() + ";" + formatoFecha.format(fecha) + ";" + formatoHora.format(fecha) + ";" + t_tiempo.getText() + "\n");
                fw.close();
                solves.add(new Solve(linea, l_scramble.getText(), formatoFecha.format(fecha), formatoHora.format(fecha), t_tiempo.getText()));
                Listado.setModel(SolveDao.cargarSolves());
                SesionDao.cargarSesion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            String scram = PrincipalUtil.generarScramble();
            l_scramble.setText(scram);
            Cubo c = CuboDao.generarCubo(scram);
            CuboDao.establecerCubo(c);

        } else {
            m = 0;
            s = 0;
            ms = 0;
            cs = 0;
            t_tiempo.setText("00:00:00");

            if (pulsacion_larga) {
                t4.start();
                if (sufic) {
                    t_tiempo.setForeground(new java.awt.Color(0, 204, 0));

                    if (ocultar_todo) {
                        jPanelAside.setVisible(false);
                        jPanelScramble.setVisible(false);
                        jPanelPreview.setVisible(false);
                    }
                }else{
                    t_tiempo.setForeground(java.awt.Color.red);
                }
            }else{
                t_tiempo.setForeground(new java.awt.Color(0, 204, 0));
                if (ocultar_todo) {
                    jPanelAside.setVisible(false);
                    jPanelScramble.setVisible(false);
                    jPanelPreview.setVisible(false);
                }
                sufic = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            tecla_soltada();
        }
    }
    
    private void tecla_soltada() {
        if (!corriendo && sufic) {
            t_tiempo.setForeground(new java.awt.Color(0, 0, 0));
            t.start();
            t2.start();
            t3.start();
            corriendo = true;
            jComboBox1.setEnabled(false);
            jMenu1.setEnabled(false);
        } else {
            if (corriendo) {
                corriendo = false;
                jScrollPane1.getVerticalScrollBar().setValue(jScrollPane1.getVerticalScrollBar().getMaximum());
            }
            if (!sufic) {
                t_tiempo.setForeground(new java.awt.Color(0, 0, 0));
            }
        }
        t4.stop();
        sufic = false;
    }

    public void init() throws FileNotFoundException, IOException {

        try {
            File f = new File("CFG.INI");
            prop.load(new FileReader(f));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String sesion = prop.getProperty("sesion");

        ficheroSesion = new File("sesiones/" + sesion);
        
        tema = Integer.parseInt(prop.getProperty("tema"));
        
        String ls_ocultar_todo = prop.getProperty("ocultar-todo");
        ocultar_todo = !ls_ocultar_todo.equals("0");
        
        String ls_ocultar_preview = prop.getProperty("ocultar-preview");
        ocultar_preview = ls_ocultar_preview.equals("1");
        
        String ls_pulsacion_larga = prop.getProperty("pulsacion-larga");
        pulsacion_larga = !ls_pulsacion_larga.equals("0");
        
        String ls_cronometro_raton = prop.getProperty("cronometro-raton");
        cronometro_raton = ls_cronometro_raton.equals("1");
        
        String ls_inspeccion = prop.getProperty("inspeccion");
        inspeccion = ls_inspeccion.equals("1");
        tiempo_inspeccion = Integer.parseInt(prop.getProperty("segundos-inspeccion"));
        
        if (!ficheroSesion.exists()) {
            FileWriter fw;
            try {
                fw = new FileWriter(ficheroSesion);
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void formMousePressed(MouseEvent evt) {
        if (cronometro_raton) {
            tecla_presionada();
        }
    }
    
    private void formMouseReleased(MouseEvent evt) {
        if (cronometro_raton) {
            tecla_soltada();
        }
    }
    
    public javax.swing.JMenu jMenuLogo;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenuCerrar;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem m_new;
    public javax.swing.JMenuItem m_info;
    public javax.swing.JMenuItem m_cerrar;
    public javax.swing.JMenuItem jMenuMinimizar;
    public javax.swing.JMenuItem m_sig;
    public static javax.swing.JMenuItem m_ant;
    public javax.swing.JMenuItem m_pref;
    public javax.swing.JPanel jPanelGeneral;
    public javax.swing.JPopupMenu.Separator jSeparator1;
    public static javax.swing.JTextField t_tiempo;
    public javax.swing.JLabel l_scramble;
    public static javax.swing.JLabel l_snombre;
    public javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JList Listado;
    public javax.swing.JPanel jPanelScramble;
    public javax.swing.JPanel jPanelAside;
    public javax.swing.JPanel jPanelTiempos;
    public javax.swing.JPanel jPanelEstadisticas;
    public static javax.swing.JPanel jPanelPreview;
    public static javax.swing.JTextField campo_sesion;
    public static javax.swing.JLabel l_total;
    public static javax.swing.JLabel l_best;
    public static javax.swing.JLabel l_worst;
    public static javax.swing.JLabel l_ao5;
    public static javax.swing.JLabel l_ao12;
    public static javax.swing.JLabel l_ao100;
    public static javax.swing.JLabel l_totalavg;
    public static javax.swing.JLabel l_total_2;
    public static javax.swing.JLabel l_best_2;
    public static javax.swing.JLabel l_worst_2;
    public static javax.swing.JLabel l_ao5_2;
    public static javax.swing.JLabel l_ao12_2;
    public static javax.swing.JLabel l_ao100_2;
    public static javax.swing.JLabel l_totalavg_2;
    public static javax.swing.JComboBox jComboBox1;
    
    
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
