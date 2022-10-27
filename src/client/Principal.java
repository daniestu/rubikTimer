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
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import model.Cubo;
import controller.CuboDao;
import controller.SesionDao;
import controller.SolveDao;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFileChooser;
import model.AVG;
import model.Sesion;
import model.Solve;
import utilities.CronometroUtil;
import utilities.FicheroUtil;
import utilities.PrincipalUtil;
import utilities.Validations;

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
    public static boolean inspeccionActivada;
    public static boolean inspeccion;
    public static boolean comenzar;
    public static int tiempo_inspeccion;
    public static String idioma;
    
    public static double anchoActual = 0;
    public static double altoActual = 0;
    
    public static ArrayList<Solve> solves = new ArrayList();
    public static ArrayList<String> scrambles = new ArrayList<>();
    public static boolean corriendo = false;
    public static boolean corriendo_inspeccion = false;
    public static boolean sufic = false;
    public static Solve mejor;
    public static Solve peor;
    public static AVG avg = new AVG("", new ArrayList<Solve>(), false), currentAo5 = new AVG("", new ArrayList<Solve>(), false), currentAo12 = new AVG("", new ArrayList<Solve>(), false), currentAo100 = new AVG("", new ArrayList<Solve>(), false);
    public static Timer t, t2, t3, t4,t_inspeccion;
    public static int m, s, cs, ms, s_inspeccion;
    public static String movimientos[] = {"R2", "U2", "L2", "D2", "F2", "B2", "R", "U", "L", "D", "F", "B", "R'", "U'", "L'", "D'", "F'", "B'"};
    public static DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
    
    ImageIcon iconLogo;
    ImageIcon iconCerrar;
    ImageIcon iconCerrarHover;
    ImageIcon iconMinimizar;
    ImageIcon iconMinimizarHover;
    ImageIcon iconMaximizar;
    ImageIcon iconMaximizarHover;
    
    public static Color colorTiempo = new java.awt.Color(51, 51, 51);
    
    public static String importError = "Ha ocurrido un error al importar los tiempos.";
    
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
        
        CronometroUtil.actualizarTiempo(0);
    };
    public ActionListener centesima = (ActionEvent e) -> {
        ++cs;
        if (cs == 10) {
            cs = 0;
        }
        CronometroUtil.actualizarTiempo(0);
    };
    public ActionListener milesima = (ActionEvent e) -> {
        ++ms;
        if (ms == 10) {
            ms = 0;
        }
        CronometroUtil.actualizarTiempo(0);
    };
    
    public ActionListener sec_inspeccion = (ActionEvent e) -> {
        --s_inspeccion;
        CronometroUtil.actualizarTiempo(1);
    };

    Principal() {
        setIconImage(getIconImage());
        ficheroSesion = new File("sesiones/Default");
        try {
            init();
        } catch (Exception e) {
            String lang = Locale.getDefault().toString();
            if (lang.split("_")[0].equals("es")) {
                PrincipalUtil.Error("Ha ocurrido un problema al arrancar la aplicación.");
            }else {
                PrincipalUtil.Error("An error has occurred while starting the application.");
            }
            System.exit(-1);
        }
        
        resolucion = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setSize(resolucion);
        
        pantalla = this.getSize();
        t = new Timer(1000, segundo);
        t2 = new Timer(100, centesima);
        t3 = new Timer(10, milesima);
        t4 = new Timer (1000, sec);
        t_inspeccion = new Timer (1000, sec_inspeccion);

        this.setUndecorated(true);
        this.addKeyListener(this);
        this.setResizable(true);
        this.setVisible(true);

        //DECLARACION DE VARIABLES

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
        l_fondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuCerrar = new javax.swing.JMenu();
        jMenuMinimizar = new javax.swing.JMenu();
        jMenuLogo = new javax.swing.JMenu();
        m_addScramble = new javax.swing.JMenuItem();
        m_importar = new javax.swing.JMenuItem();
        m_exportar = new javax.swing.JMenuItem();
        m_new = new javax.swing.JMenuItem();
        m_add = new javax.swing.JMenuItem();
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
        
        int iconDimension = (pantalla.height * 30) / 1080;
        
        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo30x30.png"));
        Image newimg = iconLogo.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
        iconLogo = new ImageIcon(newimg);
        iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
        newimg = iconCerrar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
        iconCerrar = new ImageIcon(newimg);
        iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover.png"));
        newimg = iconCerrarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
        iconCerrarHover = new ImageIcon(newimg);
        iconMinimizar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/minimizar.png"));
        newimg = iconMinimizar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
        iconMinimizar = new ImageIcon(newimg);
        iconMinimizarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/minimizar-hover.png"));
        newimg = iconMinimizarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
        iconMinimizarHover = new ImageIcon(newimg);
        iconMaximizar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/maximizar.png"));
        newimg = iconMaximizar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
        iconMaximizar = new ImageIcon(newimg);
        iconMaximizarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/maximizar-hover.png"));
        newimg = iconMaximizarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
        iconMaximizarHover = new ImageIcon(newimg);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rubik Timer");
        setPreferredSize(pantalla);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
       
        //BARRA MENU
        jMenuBar1.setBackground(new java.awt.Color(240,240,240));
        jMenuBar1.setPreferredSize(new java.awt.Dimension(pantalla.width, (pantalla.height * 35)/1080));
        
        //MENU ITEMS
        int menuItemsSize = (pantalla.height*22)/1080;
        jMenuLogo.setIcon(iconLogo);
        jMenuLogo.enable(false);
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, menuItemsSize)); // NOI18N
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
        int fontSize = (pantalla.height * 20) / 1080;
        m_addScramble.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        m_addScramble.addActionListener((java.awt.event.ActionEvent evt) -> {
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Nuevo scramble")) {
                    i.dispose();
                }
            }
            NuevoScramble nuevoScramble = new NuevoScramble();
        });
        m_importar.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        m_importar.addActionListener((java.awt.event.ActionEvent evt) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(fileChooser);
            File f = fileChooser.getSelectedFile();
            if (f != null){
                if (!FicheroUtil.importarSolves(f, ficheroSesion)) {
                    PrincipalUtil.Error(importError);
                }
            }
        });
        m_exportar.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        m_exportar.addActionListener((java.awt.event.ActionEvent evt) -> {
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Exportar sesión")) {
                    i.dispose();
                }
            }
            Exportar exportar = new Exportar();
        });
        m_new.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        m_new.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        m_new.addActionListener((java.awt.event.ActionEvent evt) -> {
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Nueva sesión")) {
                    i.dispose();
                }
            }
            NuevaSesion nombreSesion = new NuevaSesion();
        });
        m_add.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        m_add.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        m_add.addActionListener((java.awt.event.ActionEvent evt) -> {
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Agregar tiempo")) {
                    i.dispose();
                }
            }
            NuevoSolve nuevoSolve = new NuevoSolve();
        });
        m_info.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        m_info.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
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
        m_cerrar.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        m_cerrar.addActionListener((java.awt.event.ActionEvent evt) -> {
            Frame[] frames = Principal.getFrames();
            for (Frame frame : frames) {
                frame.dispose();
            }
        });
        m_sig.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        m_sig.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        m_sig.addActionListener((java.awt.event.ActionEvent evt) -> {
            String scram = PrincipalUtil.generarScramble();
            l_scramble.setText(scram);
            Cubo c = CuboDao.generarCubo(scram);
            CuboDao.establecerCubo(c, 0);
        });
        m_ant.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        m_ant.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        m_ant.setEnabled(false);
        m_ant.addActionListener((java.awt.event.ActionEvent evt) -> {
            scrambles.remove(scrambles.size()-1);
            l_scramble.setText(scrambles.get(scrambles.size()-1));
            Cubo c = CuboDao.generarCubo(scrambles.get(scrambles.size()-1));
            CuboDao.establecerCubo(c, 0);
            
            if (scrambles.size()<2) {
                m_ant.setEnabled(false);
            }
        });
        
        m_pref.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        m_pref.setFont(new java.awt.Font("Segoe UI", 0, fontSize));
        m_pref.addActionListener((java.awt.event.ActionEvent evt) -> {
            Frame f[] = JFrame.getFrames();
            for (Frame i : f) {
                if (i.getTitle().equals("Configuración")) {
                    i.dispose();
                }
            }
            Preferencias preferencias = new Preferencias();
        });

        jMenu1.add(m_addScramble);
        jMenu1.add(m_importar);
        jMenu1.add(m_exportar);
        jMenu1.add(m_ant);
        jMenu1.add(m_sig);
        jMenu1.add(m_add);
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
        t_tiempo.setFont(new java.awt.Font("Segoe UI", 0, Validations.menorSize((pantalla.width * 230) /1920, (pantalla.height * 230) /1080)));
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelScrambleMouseClicked();
            }
            
            private void jPanelScrambleMouseClicked() {
                Cubo cubo = CuboDao.generarCubo(l_scramble.getText());
                Frame f[] = JFrame.getFrames();
                for (Frame i : f) {
                    if (i.getTitle().equals("Scramble")) {
                        i.dispose();
                    }
                }
                ScramblePreview scramblePreview = new ScramblePreview(cubo);
            }
        });

        l_scramble.setFont(new java.awt.Font("Segoe UI", 0, Validations.menorSize((pantalla.width * 50) /1920, (pantalla.height * 50) /1080)));
        String scram = PrincipalUtil.generarScramble();
        l_scramble.setText(scram);
        Cubo c = CuboDao.generarCubo(scram);
        
        l_scramble.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        int scrambleWidth = (pantalla.width * 1700) / 1920;
        int scrambleHeight = (pantalla.height * 90) / 1080;
        jPanelScramble.add(l_scramble, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, scrambleWidth, scrambleHeight));
        
        jPanelGeneral.add(jPanelScramble, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 220) / 1920, 0, scrambleWidth, scrambleHeight));
        
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
        Listado.setFont(new java.awt.Font("Segoe UI", 0, Validations.menorSize((pantalla.width * 30) / 1920, (pantalla.height * 30) / 1080))); // NOI18N
        Listado.setEnabled(true);
        Listado.setFocusable(false);
        Listado.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Listado.setModel(SolveDao.cargarSolves(solves, 0));
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
        jPanelTiempos.setName("tiempos");
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

        jPanelTiempos.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 5) / 1920, (pantalla.height * 2) / 1080, (pantalla.width * 190) / 1920, (pantalla.height * 638) / 1080));

        jPanelAside.add(jPanelTiempos, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, (pantalla.height * 395) / 1080, (pantalla.width * 200) / 1920, (pantalla.height * 643) / 1080));

        jPanelGeneral.add(jPanelAside, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, (pantalla.width * 220) / 1920, (pantalla.height * 1045) / 1080));
        
        jPanelGeneral.add(t_tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 1) / 1920, (pantalla.height * 222) / 1080, (pantalla.width * 1918) / 1920, (pantalla.height * 200) / 1080));

        //ETIQUETA DE SESIÓN Y LISTADO DE SESIONES
        campo_sesion.setFont(new java.awt.Font("Segoe UI", 0, Validations.menorSize((pantalla.height * 30) / 1080, (pantalla.width * 30) / 1920)));
        campo_sesion.setText(ficheroSesion.getName());
        campo_sesion.setEditable(false);
        campo_sesion.setFocusable(false);
        campo_sesion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        l_snombre.setFont(new java.awt.Font("Segoe UI", 0, Validations.menorSize((pantalla.height * 30) / 1080, (pantalla.width * 30) / 1920)));
        l_snombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox1.setModel(PrincipalUtil.cargarModeloComboBox());
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, Validations.menorSize((pantalla.height * 30) / 1080, (pantalla.width * 30) / 1920)));
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
        int estadisticaFontSize = Validations.menorSize((pantalla.height * 26) / 1080, (pantalla.width * 26) / 1920);
        l_total.setFont(new java.awt.Font("Times New Roman", 1, estadisticaFontSize));
        l_total.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_total_2.setFont(new java.awt.Font("Times New Roman", 0, estadisticaFontSize));
        l_total_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));

        l_best.setFont(new java.awt.Font("Times New Roman", 1, estadisticaFontSize));
        l_best.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_best_2.setFont(new java.awt.Font("Times New Roman", 0, estadisticaFontSize));
        l_best_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_best_2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(l_best_2.getText().equals("")) ) {
                    l_best_2MouseClicked(evt);
                }
            }
        });

        l_worst.setFont(new java.awt.Font("Times New Roman", 1, estadisticaFontSize));
        l_worst.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_worst_2.setFont(new java.awt.Font("Times New Roman", 0, estadisticaFontSize));
        l_worst_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_worst_2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(l_worst_2.getText().equals("")) ) {
                    l_worst_2MouseClicked(evt);
                }
                
            }
        });
        
        l_ao5.setFont(new java.awt.Font("Times New Roman", 1, estadisticaFontSize));
        l_ao5.setText(" Ao5");
        l_ao5.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_ao5_2.setFont(new java.awt.Font("Times New Roman", 0, estadisticaFontSize));
        l_ao5_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_ao5_2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(l_ao5_2.getText().equals("")) ) {
                    l_ao5_2MouseClicked(evt);
                }
            }
        });

        l_ao12.setFont(new java.awt.Font("Times New Roman", 1, estadisticaFontSize));
        l_ao12.setText(" Ao12");
        l_ao12.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_ao12_2.setFont(new java.awt.Font("Times New Roman", 0, estadisticaFontSize));
        l_ao12_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_ao12_2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(l_ao12_2.getText().equals("")) ) {
                    l_ao12_2MouseClicked(evt);
                }
            }

            private void l_ao12_2MouseClicked(MouseEvent evt) {
                Frame f[] = JFrame.getFrames();
                for (Frame i : f) {
                    if (i.getTitle().equals("Información AVG")) {
                        i.dispose();
                    }
                }
                InformacionAVG informacionAVG = new InformacionAVG(currentAo12);
            }
        });
        l_ao100.setFont(new java.awt.Font("Times New Roman", 1, estadisticaFontSize));
        l_ao100.setText(" Ao100");
        l_ao100.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_ao100_2.setFont(new java.awt.Font("Times New Roman", 0, estadisticaFontSize));
        l_ao100_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_ao100_2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if ( !(l_ao100_2.getText().equals("")) ) {
                    l_ao100_2MouseClicked(evt);
                }
            }

            private void l_ao100_2MouseClicked(MouseEvent evt) {
                Frame f[] = JFrame.getFrames();
                for (Frame i : f) {
                    if (i.getTitle().equals("Información AVG")) {
                        i.dispose();
                    }
                }
                InformacionAVG informacionAVG = new InformacionAVG(currentAo100);
            }
        });

        l_totalavg.setFont(new java.awt.Font("Times New Roman", 1, estadisticaFontSize));
        l_totalavg.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
        l_totalavg_2.setFont(new java.awt.Font("Times New Roman", 0, estadisticaFontSize));
        l_totalavg_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, 0, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));

        int estadisticaWidth = (pantalla.width * 100) / 1920;
        int estadisticaHeight = (pantalla.height * 40) / 1080;
        jPanelEstadisticas.add(l_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_best, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, estadisticaHeight, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_worst, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, estadisticaHeight*2, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_ao5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, estadisticaHeight*3, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_ao12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, estadisticaHeight*4, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_ao100, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, estadisticaHeight*5, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_totalavg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, estadisticaHeight*6, estadisticaWidth, estadisticaHeight));
        
        jPanelEstadisticas.add(l_total_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(estadisticaWidth, 0, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_best_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(estadisticaWidth, estadisticaHeight, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_worst_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(estadisticaWidth, estadisticaHeight*2, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_ao5_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(estadisticaWidth, estadisticaHeight*3, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_ao12_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(estadisticaWidth, estadisticaHeight*4, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_ao100_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(estadisticaWidth, estadisticaHeight*5, estadisticaWidth, estadisticaHeight));
        jPanelEstadisticas.add(l_totalavg_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(estadisticaWidth, estadisticaHeight*6, estadisticaWidth, estadisticaHeight));
        

        jPanelAside.add(jPanelEstadisticas, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 10) / 1920, (pantalla.height * 105) / 1080, (pantalla.width * 200) / 1920, (pantalla.height * 280) / 1080));

        jPanelAside.add(l_snombre, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 30) / 1920, (pantalla.height * 10) / 1080, (pantalla.width * 160) / 1920, (pantalla.height * 40) / 1080));
        jPanelAside.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints((pantalla.width * 4) / 1920, (pantalla.height * 55) / 1080, (pantalla.width * 212) / 1920, (pantalla.height * 40) / 1080));
        
        int tamanioCuadrado = (pantalla.height * 30) / 1080;
        int tamanioPreviewAlto = (tamanioCuadrado * 9) + 24;
        int tamanioPreviewAncho = (tamanioCuadrado * 12) + 26;
        
        jPanelPreview.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelPreview.setOpaque(false);
        
        ImageIcon fondo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/sky.jpg"));
        newimg = fondo.getImage().getScaledInstance(pantalla.width-1, pantalla.height - ((pantalla.height * 35)/1080)-1, java.awt.Image.SCALE_SMOOTH);
        l_fondo.setIcon(new ImageIcon(newimg));
        l_fondo.setVisible(false);
        
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
        
        jPanelPreview.add(u1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 3),10,tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(u2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 4),10,tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(u3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado*5),10,tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(u4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 3),10 + tamanioCuadrado,tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(u5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 4),10 + tamanioCuadrado,tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(u6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 5),10 + tamanioCuadrado,tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(u7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 3),10 + (tamanioCuadrado*2),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(u8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 4),10 + (tamanioCuadrado*2),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(u9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado*5),10 + (tamanioCuadrado*2),tamanioCuadrado,tamanioCuadrado));
        
        jPanelPreview.add(r1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14 + (tamanioCuadrado * 6),12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(r2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14 + (tamanioCuadrado * 7),12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(r3, new org.netbeans.lib.awtextra.AbsoluteConstraints(14 + (tamanioCuadrado * 8),12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(r4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14 + (tamanioCuadrado * 6),12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(r5, new org.netbeans.lib.awtextra.AbsoluteConstraints(14 + (tamanioCuadrado * 7),12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(r6, new org.netbeans.lib.awtextra.AbsoluteConstraints(14 + (tamanioCuadrado * 8),12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(r7, new org.netbeans.lib.awtextra.AbsoluteConstraints(14 + (tamanioCuadrado * 6),12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(r8, new org.netbeans.lib.awtextra.AbsoluteConstraints(14 + (tamanioCuadrado * 7),12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(r9, new org.netbeans.lib.awtextra.AbsoluteConstraints(14 + (tamanioCuadrado * 8),12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        
        jPanelPreview.add(d1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 3),14 + (tamanioCuadrado * 6),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 4),14 + (tamanioCuadrado * 6),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(d3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 5),14 + (tamanioCuadrado * 6),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(d4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 3),14 + (tamanioCuadrado * 7),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(d5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 4),14 + (tamanioCuadrado * 7),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(d6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 5),14 + (tamanioCuadrado * 7),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(d7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 3),14 + (tamanioCuadrado * 8),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(d8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 4),14 + (tamanioCuadrado * 8),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(d9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 5),14 + (tamanioCuadrado * 8),tamanioCuadrado,tamanioCuadrado));
        
        jPanelPreview.add(l1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(l2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10 + tamanioCuadrado,12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(l3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10 + (tamanioCuadrado * 2),12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(l4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(l5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10 + tamanioCuadrado,12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(l6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10 + (tamanioCuadrado * 2),12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(l7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10,12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(l8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10 + tamanioCuadrado,12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(l9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10 + (tamanioCuadrado * 2),12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        
        jPanelPreview.add(f1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 3),12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(f2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 4),12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(f3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 5),12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(f4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 3),12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(f5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 4),12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(f6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 5),12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(f7, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 3),12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(f8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 4),12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(f9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12 + (tamanioCuadrado * 5),12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        
        jPanelPreview.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16 + (tamanioCuadrado * 9),12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16 + (tamanioCuadrado * 10),12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16 + (tamanioCuadrado * 11),12 + (tamanioCuadrado * 3),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16 + (tamanioCuadrado * 9),12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16 + (tamanioCuadrado * 10),12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(b6, new org.netbeans.lib.awtextra.AbsoluteConstraints(16 + (tamanioCuadrado * 11),12 + (tamanioCuadrado * 4),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(b7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16 + (tamanioCuadrado * 9),12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(b8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16 + (tamanioCuadrado * 10),12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        jPanelPreview.add(b9, new org.netbeans.lib.awtextra.AbsoluteConstraints(16 + (tamanioCuadrado * 11),12 + (tamanioCuadrado * 5),tamanioCuadrado,tamanioCuadrado));
        
        jPanelGeneral.add(jPanelPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(pantalla.width - tamanioPreviewAncho-2, pantalla.height - tamanioPreviewAlto - ((pantalla.height * 35) / 1080), tamanioPreviewAncho, tamanioPreviewAlto - 2));
        jPanelGeneral.add(l_fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, pantalla.width -2, pantalla.height - ((pantalla.height * 35)/1080) -1));
        
        getContentPane().add(jPanelGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, pantalla.width, pantalla.height - ((pantalla.height * 35)/1080)));
        
        CuboDao.establecerCubo(c, 0);
        
        pack();
        
        PrincipalUtil.alinearPanelAside();

        this.setExtendedState(MAXIMIZED_BOTH);

        try {
            SesionDao.cargarSesion();
        } catch (IOException | ParseException ex) {
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
        } catch (IOException | ParseException ex) {
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
    
    private void l_ao5_2MouseClicked(MouseEvent evt) {
        Frame f[] = JFrame.getFrames();
        for (Frame i : f) {
            if (i.getTitle().equals("Información AVG")) {
                i.dispose();
            }
        }
        InformacionAVG informacionAVG = new InformacionAVG(currentAo5);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            tecla_presionada();
        }

    }
    
    private void tecla_presionada() {
        if (inspeccion) {
            if (!corriendo_inspeccion) {
                s_inspeccion = tiempo_inspeccion;
                t_tiempo.setText("00:00:00");
                if (pulsacion_larga) {
                    t4.start();
                    if (sufic) {
                        t_tiempo.setText(tiempo_inspeccion + "");
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
                    t_tiempo.setText(tiempo_inspeccion + "");
                    t_tiempo.setForeground(new java.awt.Color(0, 204, 0));
                    if (ocultar_todo) {
                        jPanelAside.setVisible(false);
                        jPanelScramble.setVisible(false);
                        jPanelPreview.setVisible(false); 
                    }
                    sufic = true;
                }
            } else {
                m = 0;
                s = 0;
                ms = 0;
                cs = 0;
            }
        }else{
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
                    FicheroUtil.actualizarFichero(t_tiempo.getText(), l_scramble.getText(), new Date(), false, false);
                    Listado.setModel(SolveDao.cargarSolves(solves, 0));
                    SesionDao.cargarSesion();
                    Listado.ensureIndexIsVisible(Listado.getModel().getSize() - 1);
                } catch (IOException | ParseException ex) {
                    ex.printStackTrace();
                }

                String scram = PrincipalUtil.generarScramble();
                l_scramble.setText(scram);
                Cubo c = CuboDao.generarCubo(scram);
                CuboDao.establecerCubo(c, 0);
                corriendo = false;
                
                
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
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            tecla_soltada();
        }
    }
    
    public static void tecla_soltada() {
        if (inspeccion) {
            if (!corriendo_inspeccion && sufic) {
                t_inspeccion.start();
                corriendo_inspeccion = true;
                jComboBox1.setEnabled(false);
                jMenu1.setEnabled(false);
            }else {
                if (corriendo_inspeccion) {
                    t_inspeccion.stop();
                    corriendo_inspeccion = false;
                    t_tiempo.setForeground(colorTiempo);
                    t.start();
                    t2.start();
                    t3.start();
                    corriendo = true;
                    jComboBox1.setEnabled(false);
                    jMenu1.setEnabled(false);
                    inspeccion = false;
                }
                if (!sufic) {
                    t_tiempo.setForeground(colorTiempo);
                }
            }
            t4.stop();
            sufic = false;
        }else{
            if (!corriendo && sufic) {
                t_tiempo.setForeground(colorTiempo);
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
                    t_tiempo.setForeground(colorTiempo);
                }
            }
            t4.stop();
            sufic = false;
        }
        
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
        inspeccionActivada = ls_inspeccion.equals("1");
        inspeccion = ls_inspeccion.equals("1");
        tiempo_inspeccion = Integer.parseInt(prop.getProperty("segundos-inspeccion"));
        
        idioma = prop.getProperty("idioma");
        
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
    public static javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenuCerrar;
    public static javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JMenuItem m_addScramble;
    public static javax.swing.JMenuItem m_importar;
    public static javax.swing.JMenuItem m_exportar;
    public static javax.swing.JMenuItem m_new;
    public static javax.swing.JMenuItem m_add;
    public static javax.swing.JMenuItem m_info;
    public static javax.swing.JMenuItem m_cerrar;
    public javax.swing.JMenuItem jMenuMinimizar;
    public static javax.swing.JMenuItem m_sig;
    public static javax.swing.JMenuItem m_ant;
    public static javax.swing.JMenuItem m_pref;
    public static javax.swing.JPanel jPanelGeneral;
    public javax.swing.JPopupMenu.Separator jSeparator1;
    public static javax.swing.JTextField t_tiempo;
    public static javax.swing.JLabel l_scramble;
    public static javax.swing.JLabel l_snombre;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JList Listado;
    public static javax.swing.JPanel jPanelScramble;
    public static javax.swing.JPanel jPanelAside;
    public static javax.swing.JPanel jPanelTiempos;
    public static javax.swing.JPanel jPanelEstadisticas;
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
    public static javax.swing.JLabel l_fondo;
    
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