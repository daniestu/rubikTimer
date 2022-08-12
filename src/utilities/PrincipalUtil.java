/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import client.InformacionAVG;
import client.InformacionSesion;
import client.InformacionSolve;
import client.NuevaSesion;
import client.NuevoScramble;
import client.NuevoSolve;
import client.Preferencias;
import client.Principal;
import client.ScramblePreview;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import model.AVG;
import model.Solve;

/**
 *
 * @author Dani
 */
public class PrincipalUtil {
    
    public static boolean esMejorTiempo(String mejor, String tiempo) {
        String partsMejor[] = mejor.split(":");
        String partsTiempo[] = tiempo.split(":");

        if (Integer.parseInt(partsMejor[0]) == Integer.parseInt(partsTiempo[0])) {
            if (Integer.parseInt(partsMejor[1]) == Integer.parseInt(partsTiempo[1])) {
                return Integer.parseInt(partsMejor[2]) >= Integer.parseInt(partsTiempo[2]);
            } else {
                return Integer.parseInt(partsMejor[1]) >= Integer.parseInt(partsTiempo[1]);
            }
        } else {
            return Integer.parseInt(partsMejor[0]) >= Integer.parseInt(partsTiempo[0]);
        }
    }

    public static boolean esPeorTiempo(String peor, String tiempo) {
        String[] partsPeor = peor.split(":");
        String partsTiempo[] = tiempo.split(":");

        if (Integer.parseInt(partsPeor[0]) == Integer.parseInt(partsTiempo[0])) {
            if (Integer.parseInt(partsPeor[1]) == Integer.parseInt(partsTiempo[1])) {
                return Integer.parseInt(partsPeor[2]) < Integer.parseInt(partsTiempo[2]);
            } else {
                return Integer.parseInt(partsPeor[1]) < Integer.parseInt(partsTiempo[1]);
            }
        } else {
            return Integer.parseInt(partsPeor[0]) < Integer.parseInt(partsTiempo[0]);
        }
    }

    public static int convertirTiempoMs(String tiempo) {
        int m, s, ms;

        String parts[] = tiempo.split(":");

        m = Integer.parseInt(parts[0]);
        s = Integer.parseInt(parts[1]);
        ms = Integer.parseInt(parts[2]);

        s += m * 60;
        ms += s * 100;

        return ms;
    }

    public static String convertirMsTiempo(int ms) {
        int m, s, lms;
        String s_m, s_s, s_ms;

        if (ms >= 100) {
            lms = ms % 100;
            s = (ms - lms) / 100;
            if (s >= 60) {
                m = s / 60;
                s = s % 60;
                if (m < 10) {
                    s_m = "0" + m;
                } else {
                    s_m = "" + m;
                }
                if (s < 10) {
                    s_s = "0" + s;
                } else {
                    s_s = "" + s;
                }
                if (lms < 10) {
                    s_ms = "0" + lms;
                } else {
                    s_ms = "" + lms;
                }
                return s_m + ":" + s_s + ":" + s_ms;
            } else {
                if (s < 10) {
                    s_s = "0" + s;
                } else {
                    s_s = s + "";
                }

                if (lms < 10) {
                    s_ms = "0" + lms;
                } else {
                    s_ms = "" + lms;
                }

                return "00:" + s_s + ":" + s_ms;
            }
        } else {
            if (ms < 10) {
                return "00:00:0" + ms;
            } else {
                return "00:00:" + ms;
            }
        }
    }
    
    public static AVG mejorAVG(int num) {
        ArrayList <Solve> al = new ArrayList();
        ArrayList <Solve> alMejores = new ArrayList();
        int suma;
        String mejoravg = "";
        
        for (Solve i : Principal.solves) {
            al.add(i);
            suma=0;
            if (al.size()==num+1) {
                al.remove(0);
                String mejorTiempo = al.get(0).getTiempo();
                String peorTiempo = al.get(0).getTiempo();
                for (Solve j : al) {
                    if (esMejorTiempo(mejorTiempo, j.getTiempo())) {
                        mejorTiempo = j.getTiempo();
                    }
                    if (esPeorTiempo(peorTiempo, j.getTiempo())) {
                        peorTiempo = j.getTiempo();
                    }
                    suma += convertirTiempoMs(j.getTiempo());
                }
                suma = suma - (convertirTiempoMs(mejorTiempo) + convertirTiempoMs(peorTiempo) );
                if (esMejorTiempo(mejoravg, convertirMsTiempo(suma/(num - 2)))) {
                    mejoravg = convertirMsTiempo(suma/(num - 2));
                    alMejores = new ArrayList<Solve>(al);
                }
            }else{
                if (al.size()==num) {
                    String mejorTiempo = al.get(0).getTiempo();
                    String peorTiempo = al.get(0).getTiempo();
                    for (Solve j : al) {
                        if (esMejorTiempo(mejorTiempo, j.getTiempo())) {
                        mejorTiempo = j.getTiempo();
                        }
                        if (esPeorTiempo(peorTiempo, j.getTiempo())) {
                            peorTiempo = j.getTiempo();
                        }
                        suma += convertirTiempoMs(j.getTiempo());
                    }
                    suma = suma - (convertirTiempoMs(mejorTiempo) + convertirTiempoMs(peorTiempo) );
                    mejoravg = convertirMsTiempo(suma/(num - 2));
                    alMejores = new ArrayList<Solve>(al);
                }
            }
        }
        AVG avg = new AVG(mejoravg, alMejores);
        return avg;
    }
    
    public static ComboBoxModel cargarModeloComboBox() {
        int cont = 0;
        String sesiones[];

        File files[] = Principal.ficheroSesion.getParentFile().listFiles();
        sesiones = new String[files.length];
        for (File file : files) {
            sesiones[cont] = file.getName();
            cont++;
        }
        ComboBoxModel model = new javax.swing.DefaultComboBoxModel(sesiones);
        return model;
    }
    public static String generarScramble() {
        Random rng = new Random();
        ArrayList<String> combinacion = new ArrayList<String>();
        String scramble, m, mant, mant2;
        int num;

        scramble = "";
        do {
            num = rng.nextInt(18);
            m = Principal.movimientos[num];
            if (!combinacion.isEmpty()) {
                mant = combinacion.get(combinacion.size() - 1);
                mant2 = null;
                if (combinacion.size() > 1) {
                    mant2 = combinacion.get(combinacion.size() - 2);
                }
                switch (mant) {
                    case "R2":
                    case "R":
                    case "R'":
                        if (!(m.equals("R2") || m.equals("R") || m.equals("R'"))) {
                            if (combinacion.size() < 2) {
                                combinacion.add(m);
                            }else{
                                if ( !( (m.equals("L2") || m.equals("L") || m.equals("L'")) && (mant2.equals("L2") || mant2.equals("L") || mant2.equals("L'")) ) ) {
                                    combinacion.add(m);
                                }
                            }
                        }
                        break;
                    case "U":
                    case "U2":
                    case "U'":
                        if (!(m.equals("U2") || m.equals("U") || m.equals("U'"))) {
                            if (combinacion.size() < 2) {
                                combinacion.add(m);
                            }else{
                                if ( !( (m.equals("D2") || m.equals("D") || m.equals("D'")) && (mant2.equals("D2") || mant2.equals("D") || mant2.equals("D'")) ) ) {
                                    combinacion.add(m);
                                }
                            }
                        }
                        break;
                    case "L":
                    case "L2":
                    case "L'":
                        if (!(m.equals("L2") || m.equals("L") || m.equals("L'"))) {
                            if (combinacion.size() < 2) {
                                combinacion.add(m);
                            }else{
                                if ( !( (m.equals("R2") || m.equals("R") || m.equals("R'")) && (mant2.equals("R2") || mant2.equals("R") || mant2.equals("R'")) ) ) {
                                    combinacion.add(m);
                                }
                            }
                        }
                        break;
                    case "D":
                    case "D2":
                    case "D'":
                        if (!(m.equals("D2") || m.equals("D") || m.equals("D'"))) {
                            if (combinacion.size() < 2) {
                                combinacion.add(m);
                            }else{
                                if ( !( (m.equals("U2") || m.equals("U") || m.equals("U'")) && (mant2.equals("U2") || mant2.equals("U") || mant2.equals("U'")) ) ) {
                                    combinacion.add(m);
                                }
                            }
                        }
                        break;
                    case "F":
                    case "F2":
                    case "F'":
                        if (!(m.equals("F2") || m.equals("F") || m.equals("F'"))) {
                            if (combinacion.size() < 2) {
                                combinacion.add(m);
                            }else{
                                if ( !( (m.equals("B2") || m.equals("B") || m.equals("B'")) && (mant2.equals("B2") || mant2.equals("B") || mant2.equals("B'")) ) ) {
                                    combinacion.add(m);
                                }
                            }
                        }
                        break;
                    case "B":
                    case "B2":
                    case "B'":
                        if (!(m.equals("B2") || m.equals("B") || m.equals("B'"))) {
                            if (combinacion.size() < 2) {
                                combinacion.add(m);
                            }else{
                                if ( !( (m.equals("F2") || m.equals("F") || m.equals("F'")) && (mant2.equals("F2") || mant2.equals("F") || mant2.equals("F'")) ) ) {
                                    combinacion.add(m);
                                }
                            }
                        }
                        break;

                }
            } else {
                combinacion.add(m);
            }
        } while (combinacion.size() < 20);

        for (String i : combinacion) {
            if (scramble.equals("")) {
                scramble = i;
            } else {
                scramble = scramble + " " + i;
            }
        }
        
        Principal.scrambles.add(scramble);
        if (Principal.scrambles.size()>1) {
            Principal.m_ant.setEnabled(true);
        }
        
        return scramble;
    }

    public static void actualizarTema(int tema, int opcion) {
        switch (opcion){
            case 0:
                switch (tema){
                    case 1:
                        Principal.jPanelGeneral.setBackground(new java.awt.Color(205, 242, 154));
                        Principal.jPanelScramble.setBackground(new java.awt.Color(255, 204, 204));
                        Principal.jPanelAside.setBackground(new java.awt.Color(255, 204, 204));
                        Principal.jPanelTiempos.setBackground(new java.awt.Color(255, 204, 204));
                        Principal.jPanelEstadisticas.setBackground(new java.awt.Color(255, 204, 204));
                        Principal.jPanelPreview.setBackground(new java.awt.Color(205, 242, 154));
                        Principal.Listado.setBackground(new java.awt.Color(255, 204, 204));
                        
                        Principal.t_tiempo.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_scramble.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_snombre.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_total.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_best.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_worst.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao5.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao12.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao100.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_totalavg.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_total_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_best_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_worst_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao5_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao100_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao12_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_totalavg_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.Listado.setForeground(new java.awt.Color(51,51,51));
                        Principal.colorTiempo = new java.awt.Color(51,51,51);
                        
                        Principal.l_total.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_best.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_worst.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao5.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao12.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao100.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_totalavg.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_total_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_best_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_worst_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao5_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao100_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao12_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_totalavg_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, 0, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.jPanelTiempos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(180, 180, 180), 1, true));
                        break;
                    case 2:
                        Principal.jPanelGeneral.setBackground(new java.awt.Color(227, 227, 227));
                        Principal.jPanelScramble.setBackground(new java.awt.Color(178, 178, 178));
                        Principal.jPanelAside.setBackground(new java.awt.Color(178, 178, 178));
                        Principal.jPanelTiempos.setBackground(new java.awt.Color(178, 178, 178));
                        Principal.jPanelEstadisticas.setBackground(new java.awt.Color(178, 178, 178));
                        Principal.jPanelPreview.setBackground(new java.awt.Color(227, 227, 227));
                        Principal.Listado.setBackground(new java.awt.Color(178, 178, 178));
                        
                        Principal.t_tiempo.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_scramble.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_snombre.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_total.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_best.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_worst.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao5.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao12.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao100.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_totalavg.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_total_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_best_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_worst_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao5_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao100_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_ao12_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.l_totalavg_2.setForeground(new java.awt.Color(51,51,51));
                        Principal.Listado.setForeground(new java.awt.Color(51,51,51));
                        Principal.colorTiempo = new java.awt.Color(51,51,51);
                        
                        Principal.l_total.setBorder(null);
                        Principal.l_best.setBorder(null);
                        Principal.l_worst.setBorder(null);
                        Principal.l_ao5.setBorder(null);
                        Principal.l_ao12.setBorder(null);
                        Principal.l_ao100.setBorder(null);
                        Principal.l_totalavg.setBorder(null);
                        Principal.l_total_2.setBorder(null);
                        Principal.l_best_2.setBorder(null);
                        Principal.l_worst_2.setBorder(null);
                        Principal.l_ao5_2.setBorder(null);
                        Principal.l_ao100_2.setBorder(null);
                        Principal.l_ao12_2.setBorder(null);
                        Principal.l_totalavg_2.setBorder(null);
                        Principal.jPanelTiempos.setBorder(null);
                        break;
                    case 3:
                        Principal.jPanelGeneral.setBackground(new java.awt.Color(53, 54, 58));
                        Principal.jPanelScramble.setBackground(new java.awt.Color(33, 34, 38));
                        Principal.jPanelAside.setBackground(new java.awt.Color(33, 34, 38));
                        Principal.jPanelTiempos.setBackground(new java.awt.Color(33, 34, 38));
                        Principal.jPanelEstadisticas.setBackground(new java.awt.Color(33, 34, 38));
                        Principal.jPanelPreview.setBackground(new java.awt.Color(53, 54, 58));
                        Principal.Listado.setBackground(new java.awt.Color(33, 34, 38));
                        
                        Principal.t_tiempo.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_scramble.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_snombre.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_total.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_best.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_worst.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_ao5.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_ao12.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_ao100.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_totalavg.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_total_2.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_best_2.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_worst_2.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_ao5_2.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_ao100_2.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_ao12_2.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.l_totalavg_2.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.Listado.setForeground(new java.awt.Color(233, 234, 237));
                        Principal.colorTiempo = new java.awt.Color(233, 234, 237);
                        
                        Principal.l_total.setBorder(null);
                        Principal.l_best.setBorder(null);
                        Principal.l_worst.setBorder(null);
                        Principal.l_ao5.setBorder(null);
                        Principal.l_ao12.setBorder(null);
                        Principal.l_ao100.setBorder(null);
                        Principal.l_totalavg.setBorder(null);
                        Principal.l_total_2.setBorder(null);
                        Principal.l_best_2.setBorder(null);
                        Principal.l_worst_2.setBorder(null);
                        Principal.l_ao5_2.setBorder(null);
                        Principal.l_ao100_2.setBorder(null);
                        Principal.l_ao12_2.setBorder(null);
                        Principal.l_totalavg_2.setBorder(null);
                        Principal.jPanelTiempos.setBorder(null);
                        break;
                }
                break;
            case 1:
                switch (tema){
                    case 1:
                        InformacionAVG.panelGeneral.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionAVG.Listado.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionAVG.l_img.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png")));
                        
                        InformacionAVG.l_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionAVG.jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(17, 61));
                        InformacionAVG.Listado.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionAVG.l_cerrar.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png")));
                        InformacionAVG.iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
                        InformacionAVG.iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
                        break;
                    case 2:
                        InformacionAVG.panelGeneral.setBackground(new java.awt.Color(178, 178, 178));
                        InformacionAVG.Listado.setBackground(new java.awt.Color(178, 178, 178));
                        InformacionAVG.l_img.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-gris90x90.png")));
                        
                        InformacionAVG.l_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionAVG.jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(17, 61));
                        InformacionAVG.Listado.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionAVG.l_cerrar.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png")));
                        InformacionAVG.iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
                        InformacionAVG.iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
                        break;
                    case 3:
                        InformacionAVG.panelGeneral.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionAVG.Listado.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionAVG.l_img.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-negro90x90.png")));
                        
                        InformacionAVG.l_nombre.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionAVG.jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
                        InformacionAVG.Listado.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionAVG.l_cerrar.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-blanco.png")));
                        InformacionAVG.iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-blanco.png"));
                        InformacionAVG.iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-blanco-hover-gris.png"));
                        break;
                }
                break;
            case 2:
                switch (tema){
                    case 1:
                        InformacionSesion.jPanel1.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.l_img.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png")));
                        
                        InformacionSesion.l_total.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_total.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_avg.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_avg.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_desv.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_desv.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_peor.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_peor.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_mejor.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_mejor.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_cao5.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_cao12.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_cao100.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_cao5.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_cao12.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_cao100.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_bao5.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_bao12.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_bao100.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_bao5.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_bao12.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_bao100.setForeground(new java.awt.Color(51, 51, 51));
                        
                        InformacionSesion.t_total.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_nombre.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_avg.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_desv.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_peor.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_mejor.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_cao5.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_cao12.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_cao100.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_bao5.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_bao12.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_bao100.setBackground(new java.awt.Color(238, 238, 238));
                        break;
                    case 2:
                        InformacionSesion.jPanel1.setBackground(new java.awt.Color(178, 178, 178));
                        InformacionSesion.l_img.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-gris90x90.png")));
                        
                        InformacionSesion.l_total.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_total.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_avg.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_avg.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_desv.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_desv.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_peor.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_peor.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_mejor.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_mejor.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_cao5.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_cao12.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_cao100.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_cao5.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_cao12.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_cao100.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_bao5.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_bao12.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.l_bao100.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_bao5.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_bao12.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSesion.t_bao100.setForeground(new java.awt.Color(51, 51, 51));
                        
                        InformacionSesion.t_total.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_nombre.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_avg.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_desv.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_peor.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_mejor.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_cao5.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_cao12.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_cao100.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_bao5.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_bao12.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSesion.t_bao100.setBackground(new java.awt.Color(238, 238, 238));
                        break;
                    case 3:
                        InformacionSesion.jPanel1.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.l_img.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-negro90x90.png")));
                        
                        InformacionSesion.l_total.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_total.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_nombre.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_nombre.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_avg.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_avg.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_desv.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_desv.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_peor.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_peor.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_mejor.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_mejor.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_cao5.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_cao12.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_cao100.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_cao5.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_cao12.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_cao100.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_bao5.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_bao12.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.l_bao100.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_bao5.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_bao12.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSesion.t_bao100.setForeground(new java.awt.Color(233, 234, 237));
                        
                        InformacionSesion.t_total.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_nombre.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_avg.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_desv.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_peor.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_mejor.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_cao5.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_cao12.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_cao100.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_bao5.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_bao12.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSesion.t_bao100.setBackground(new java.awt.Color(33, 34, 38));
                        break;
                }
                break;
            case 3:
                switch (tema){
                    case 1:
                        InformacionSolve.jPanel1.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSolve.l_img.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png")));
                        
                        InformacionSolve.l_fecha1.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.l_fecha2.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.l_tiempo.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.l_id.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.l_scramble.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.t_fecha.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.t_hora.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.t_id.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.t_scramble.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.t_tiempo.setForeground(new java.awt.Color(51, 51, 51));
                        
                        InformacionSolve.t_fecha.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSolve.t_hora.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSolve.t_id.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSolve.t_scramble.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSolve.t_tiempo.setBackground(new java.awt.Color(238, 238, 238));
                        
                        InformacionSolve.t_fecha.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        InformacionSolve.t_hora.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        InformacionSolve.t_id.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        InformacionSolve.t_scramble.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        InformacionSolve.t_tiempo.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        break;
                    case 2:
                        InformacionSolve.jPanel1.setBackground(new java.awt.Color(178, 178, 178));
                        InformacionSolve.l_img.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-gris90x90.png")));
                        
                        InformacionSolve.l_fecha1.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.l_fecha2.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.l_tiempo.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.l_id.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.l_scramble.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.t_fecha.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.t_hora.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.t_id.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.t_scramble.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionSolve.t_tiempo.setForeground(new java.awt.Color(51, 51, 51));
                        
                        InformacionSolve.t_fecha.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSolve.t_hora.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSolve.t_id.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSolve.t_scramble.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionSolve.t_tiempo.setBackground(new java.awt.Color(238, 238, 238));
                        
                        InformacionSolve.t_fecha.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        InformacionSolve.t_hora.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        InformacionSolve.t_id.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        InformacionSolve.t_scramble.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        InformacionSolve.t_tiempo.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                        break;
                    case 3:
                        InformacionSolve.jPanel1.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSolve.l_img.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-negro90x90.png")));
                        
                        InformacionSolve.l_fecha1.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSolve.l_fecha2.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSolve.l_tiempo.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSolve.l_id.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSolve.l_scramble.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSolve.t_fecha.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSolve.t_hora.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSolve.t_id.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSolve.t_scramble.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionSolve.t_tiempo.setForeground(new java.awt.Color(233, 234, 237));
                        
                        InformacionSolve.t_fecha.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSolve.t_hora.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSolve.t_id.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSolve.t_scramble.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionSolve.t_tiempo.setBackground(new java.awt.Color(33, 34, 38));
                        
                        InformacionSolve.t_fecha.setBorder(null);
                        InformacionSolve.t_hora.setBorder(null);
                        InformacionSolve.t_id.setBorder(null);
                        InformacionSolve.t_scramble.setBorder(null);
                        InformacionSolve.t_tiempo.setBorder(null);
                        break;
                }
                break;
            case 4:
                switch (tema){
                    case 1:
                        NuevaSesion.jPanel1.setBackground(new java.awt.Color(238, 238, 238));
                        NuevaSesion.jLabel1.setForeground(new java.awt.Color(51, 51, 51));
                        break;
                    case 2:
                        NuevaSesion.jPanel1.setBackground(new java.awt.Color(178, 178, 178));
                        NuevaSesion.jLabel1.setForeground(new java.awt.Color(51, 51, 51));
                        break;
                    case 3:
                        NuevaSesion.jPanel1.setBackground(new java.awt.Color(33, 34, 38));
                        NuevaSesion.jLabel1.setForeground(new java.awt.Color(238, 238, 238));
                        break;
                }
                break;
            case 5:
                switch (tema){
                    case 1:
                        NuevoSolve.jPanel1.setBackground(new java.awt.Color(238, 238, 238));
                        
                        NuevoSolve.l_scramble.setForeground(new java.awt.Color(51,51,51));
                        NuevoSolve.l_tiempo.setForeground(new java.awt.Color(51,51,51));
                        NuevoSolve.l_titulo.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 2:
                        NuevoSolve.jPanel1.setBackground(new java.awt.Color(178, 178, 178));
                        
                        NuevoSolve.l_scramble.setForeground(new java.awt.Color(51,51,51));
                        NuevoSolve.l_tiempo.setForeground(new java.awt.Color(51,51,51));
                        NuevoSolve.l_titulo.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 3:
                        NuevoSolve.jPanel1.setBackground(new java.awt.Color(33, 34, 38));
                        
                        NuevoSolve.l_scramble.setForeground(new java.awt.Color(238, 238, 238));
                        NuevoSolve.l_tiempo.setForeground(new java.awt.Color(238, 238, 238));
                        NuevoSolve.l_titulo.setForeground(new java.awt.Color(238, 238, 238));
                        break;
                }
                break;
            case 6:
                switch (tema){
                    case 1:
                        Preferencias.jPanelFondo.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelGeneral.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelTitulo.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelTemas.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelOcultarElementos.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelOcultarPreview.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelPulsacionLarga.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelCronoRaton.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelTiempo_inspeccion.setBackground(new java.awt.Color(255,179,179));
                        
                        Preferencias.l_temas.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.l_titulo.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jRadioButtonTema1.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jCheckBoxOcultarElementos.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jRadioButtonTema2.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jCheckBoxPulsacion_larga.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jCheckBoxOcultar_preview.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jCheckBoxTiempo_inspeccion.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jCheckBoxCrono_raton.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.l_segundos.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,179,179)));
                        Preferencias.jRadioButtonTema3.setBackground(new java.awt.Color(255, 219, 219));
                        
                        Preferencias.l_temas.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_titulo.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jRadioButtonTema1.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxOcultarElementos.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jRadioButtonTema2.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxPulsacion_larga.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxOcultar_preview.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxTiempo_inspeccion.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxCrono_raton.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_segundos.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jRadioButtonTema3.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 2:
                        Preferencias.jPanelFondo.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jPanelGeneral.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jPanelTitulo.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jPanelTemas.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jPanelOcultarElementos.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jPanelOcultarPreview.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jPanelPulsacionLarga.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jPanelCronoRaton.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jPanelTiempo_inspeccion.setBackground(new java.awt.Color(178, 178, 178));
                        
                        Preferencias.l_temas.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.l_titulo.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jRadioButtonTema1.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxOcultarElementos.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jRadioButtonTema2.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxPulsacion_larga.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jCheckBoxOcultar_preview.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxTiempo_inspeccion.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jCheckBoxCrono_raton.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.l_segundos.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jRadioButtonTema3.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jPanelGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(178, 178, 178)));
                        
                        Preferencias.l_temas.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_titulo.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jRadioButtonTema1.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxOcultarElementos.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jRadioButtonTema2.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxPulsacion_larga.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxOcultar_preview.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxTiempo_inspeccion.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxCrono_raton.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_segundos.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jRadioButtonTema3.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 3:
                        Preferencias.jPanelFondo.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jPanelGeneral.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jPanelTitulo.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jPanelTemas.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jPanelOcultarElementos.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jPanelOcultarPreview.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jPanelPulsacionLarga.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jPanelCronoRaton.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jPanelTiempo_inspeccion.setBackground(new java.awt.Color(33, 34, 38));
                        
                        Preferencias.l_temas.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.l_titulo.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jRadioButtonTema1.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jCheckBoxOcultarElementos.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jRadioButtonTema2.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jCheckBoxPulsacion_larga.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jCheckBoxOcultar_preview.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jCheckBoxTiempo_inspeccion.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jCheckBoxCrono_raton.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.l_segundos.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jRadioButtonTema3.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jPanelGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 34, 38)));
                        
                        Preferencias.l_temas.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.l_titulo.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jRadioButtonTema1.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxOcultarElementos.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jRadioButtonTema2.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxPulsacion_larga.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxOcultar_preview.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxTiempo_inspeccion.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxCrono_raton.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.l_segundos.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jRadioButtonTema3.setForeground(new java.awt.Color(238, 238, 238));
                        break;
                }
                break;
            case 7:
                switch (tema){
                    case 1:
                        ScramblePreview.panelGeneral.setBackground(new java.awt.Color(238, 238, 238));
                        ScramblePreview.l_cerrar.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png")));
                        ScramblePreview.iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
                        ScramblePreview.iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
                        break;
                    case 2:
                        ScramblePreview.panelGeneral.setBackground(new java.awt.Color(178, 178, 178));
                        ScramblePreview.l_cerrar.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png")));
                        ScramblePreview.iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
                        ScramblePreview.iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
                        break;
                    case 3:
                        ScramblePreview.panelGeneral.setBackground(new java.awt.Color(33, 34, 38));
                        ScramblePreview.l_cerrar.setIcon(new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-blanco.png")));
                        ScramblePreview.iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-blanco.png"));
                        ScramblePreview.iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-blanco-hover-gris.png"));
                        break;
                }
                break;
            case 8:
                switch (tema){
                    case 1:
                        NuevoScramble.panelGeneral.setBackground(new java.awt.Color(238, 238, 238));
                        NuevoScramble.jLabel1.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 2:
                        NuevoScramble.panelGeneral.setBackground(new java.awt.Color(178, 178, 178));
                        NuevoScramble.jLabel1.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 3:
                        NuevoScramble.panelGeneral.setBackground(new java.awt.Color(33, 34, 38));
                        NuevoScramble.jLabel1.setForeground(new java.awt.Color(238, 238, 238));
                        break;
                }
                break;
        }
        
    }

    public static void alinearPanelAside() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        int menuHeight = Principal.jMenuBar1.getHeight();
        int panelHeight = Principal.jPanelAside.getHeight();
        int px = pantalla.height - (menuHeight + panelHeight);
        if (px > 0) {
            Principal.jPanelGeneral.add(Principal.jPanelAside, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, Principal.jPanelAside.getWidth(), panelHeight + px));
        }else if (px < 0) {
            Principal.jPanelGeneral.add(Principal.jPanelAside, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, Principal.jPanelAside.getWidth(), panelHeight - px));
        }
    }
}
