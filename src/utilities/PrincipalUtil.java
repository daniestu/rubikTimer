package utilities;

import language.Idioma;
import client.Exportar;
import client.InformacionAVG;
import client.InformacionSesion;
import client.InformacionSolve;
import client.NuevaSesion;
import client.NuevoScramble;
import client.NuevoSolve;
import client.Preferencias;
import client.Principal;
import client.ScramblePreview;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author Dani
 */
public class PrincipalUtil {
    
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
    
    public static String sumar2(String tiempo) {
        int tiempoMs = convertirTiempoMs(tiempo);
        return convertirMsTiempo(tiempoMs + 200);
    }

    public static String restar2(String tiempo) {
        int tiempoMs = convertirTiempoMs(tiempo);
        return convertirMsTiempo(tiempoMs - 200);
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

    public static void Error(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void cambiarIdioma(String nombreIdioma, int opcion){
        Idioma idioma = new Idioma(nombreIdioma);
        
        switch (opcion){
            case 0:
                Principal.l_snombre.setText(idioma.getProperty("principal.sesion"));
                Principal.l_total.setText(" " + idioma.getProperty("principal.total"));
                Principal.l_best.setText(" " + idioma.getProperty("principal.mejor"));
                Principal.l_worst.setText(" " + idioma.getProperty("principal.peor"));
                Principal.l_totalavg.setText(" " + idioma.getProperty("principal.media"));
                Principal.jMenu1.setText(idioma.getProperty("principal.opciones"));
                Principal.m_addScramble.setText(idioma.getProperty("principal.scramble_personalizado"));
                Principal.m_importar.setText(idioma.getProperty("principal.importar"));
                Principal.m_exportar.setText(idioma.getProperty("principal.exportar"));
                Principal.m_ant.setText(idioma.getProperty("principal.mezcla_anterior"));
                Principal.m_sig.setText(idioma.getProperty("principal.nueva_mezcla"));
                Principal.m_add.setText(idioma.getProperty("principal.agregar_tiempo"));
                Principal.m_new.setText(idioma.getProperty("principal.nueva_sesion"));
                Principal.m_info.setText(idioma.getProperty("principal.informacion_sesion"));
                Principal.m_pref.setText(idioma.getProperty("principal.configuracion"));
                Principal.m_cerrar.setText(idioma.getProperty("principal.cerrar"));
                Principal.importError = idioma.getProperty("principal.importError");
                break;
            case 1:
                InformacionSesion.l_nombre.setText(idioma.getProperty("informacionSesion.nombre"));
                InformacionSesion.l_total.setText(idioma.getProperty("informacionSesion.total"));
                InformacionSesion.l_mejor.setText(idioma.getProperty("informacionSesion.mejor"));
                InformacionSesion.l_peor.setText(idioma.getProperty("informacionSesion.peor"));
                InformacionSesion.l_avg.setText(idioma.getProperty("informacionSesion.media"));
                InformacionSesion.l_desv.setText(idioma.getProperty("informacionSesion.desviacion"));
                InformacionSesion.l_cao5.setText(idioma.getProperty("informacionSesion.ao5Actual"));
                InformacionSesion.l_cao12.setText(idioma.getProperty("informacionSesion.ao12Actual"));
                InformacionSesion.l_cao100.setText(idioma.getProperty("informacionSesion.ao100Actual"));
                InformacionSesion.l_bao5.setText(idioma.getProperty("informacionSesion.mejorAo5"));
                InformacionSesion.l_bao12.setText(idioma.getProperty("informacionSesion.mejorAo12"));
                InformacionSesion.l_bao100.setText(idioma.getProperty("informacionSesion.mejorAo100"));
                InformacionSesion.b_aceptar.setText(idioma.getProperty("informacionSesion.aceptar"));
                InformacionSesion.b_eliminar.setText(idioma.getProperty("informacionSesion.eliminar"));
                InformacionSesion.deleteError = idioma.getProperty("informacionSesion.deleteError");
                InformacionSesion.renameError = idioma.getProperty("informacionSesion.renameError");
                InformacionSesion.nameError = idioma.getProperty("informacionSesion.nameError");
                InformacionSesion.nameMissingError = idioma.getProperty("informacionSesion.nameMissingError");
                InformacionSesion.deleteConfirmation = idioma.getProperty("informacionSesion.deleteConfirmation");
                break;
            case 2:
                InformacionSolve.l_id.setText(idioma.getProperty("informacionSolve.id"));
                InformacionSolve.l_scramble.setText(idioma.getProperty("informacionSolve.scramble"));
                InformacionSolve.l_fecha1.setText(idioma.getProperty("informacionSolve.fecha"));
                InformacionSolve.l_fecha2.setText(idioma.getProperty("informacionSolve.hora"));
                InformacionSolve.l_tiempo.setText(idioma.getProperty("informacionSolve.tiempo"));
                InformacionSolve.b_aceptar.setText(idioma.getProperty("informacionSolve.aceptar"));
                InformacionSolve.b_eliminar.setText(idioma.getProperty("informacionSolve.eliminar"));
                InformacionSolve.saveError = idioma.getProperty("informacionSolve.saveError");
                break;
            case 3:
                NuevaSesion.l_nombre.setText(idioma.getProperty("nuevaSesion.nombre"));
                NuevaSesion.jButtonAceptar.setText(idioma.getProperty("nuevaSesion.aceptar"));
                NuevaSesion.jButtonCancelar.setText(idioma.getProperty("nuevaSesion.cancelar"));
                NuevaSesion.nameError = idioma.getProperty("nuevaSesion.nameError");
                NuevaSesion.nameMissingError = idioma.getProperty("nuevaSesion.nameMissingError");
                NuevaSesion.duplicateError = idioma.getProperty("nuevaSesion.duplicateError");
                break;
            case 4:
                NuevoSolve.l_titulo.setText(idioma.getProperty("nuevoSolve.titulo"));
                NuevoSolve.l_tiempo.setText(idioma.getProperty("nuevoSolve.tiempo"));
                NuevoSolve.l_scramble.setText(idioma.getProperty("nuevoSolve.scramble"));
                NuevoSolve.jButtonAceptar.setText(idioma.getProperty("nuevoSolve.aceptar"));
                NuevoSolve.jButtonCancelar.setText(idioma.getProperty("nuevoSolve.cancelar"));
                NuevoSolve.solveError = idioma.getProperty("nuevoSolve.solveError");
                NuevoSolve.scrambleError = idioma.getProperty("nuevoSolve.scrambleError");
                break;
            case 5:
                Preferencias.l_titulo.setText(idioma.getProperty("preferencias.titulo"));
                Preferencias.l_temas.setText(idioma.getProperty("preferencias.tema"));
                Preferencias.l_idioma.setText(idioma.getProperty("preferencias.idioma"));
                Preferencias.jCheckBoxOcultarElementos.setText(idioma.getProperty("preferencias.ocultarTodo"));
                Preferencias.jCheckBoxOcultar_preview.setText(idioma.getProperty("preferencias.ocultarPreview"));
                Preferencias.jCheckBoxPulsacion_larga.setText(idioma.getProperty("preferencias.pulsacionLarga"));
                Preferencias.jCheckBoxCrono_raton.setText(idioma.getProperty("preferencias.cronometroRaton"));
                Preferencias.jCheckBoxTiempo_inspeccion.setText(idioma.getProperty("preferencias.inspeccion"));
                Preferencias.l_segundos.setText(idioma.getProperty("preferencias.inspeccion.segundos"));
                Preferencias.jButtonAceptar.setText(idioma.getProperty("preferencias.aceptar"));
                Preferencias.jButton1Reset.setText(idioma.getProperty("preferencias.resetear"));
                Preferencias.jButton1Cancelar.setText(idioma.getProperty("preferencias.cancelar"));
                break;
            case 6:
                NuevoScramble.l_scramble.setText(idioma.getProperty("scramble.scramble"));
                NuevoScramble.jButtonAceptar.setText(idioma.getProperty("scramble.aceptar"));
                NuevoScramble.jButtonCancelar.setText(idioma.getProperty("scramble.cancelar"));
                NuevoScramble.scrambleError = idioma.getProperty("scramble.error");
                break;
            case 7:
                Exportar.l_titulo.setText(idioma.getProperty("exportar.titulo"));
                Exportar.l_sesion.setText(idioma.getProperty("exportar.sesion"));
                Exportar.l_carpeta.setText(idioma.getProperty("exportar.destino"));
                Exportar.jButtonExaminar.setText(idioma.getProperty("exportar.examinar"));
                Exportar.jButtonAceptar.setText(idioma.getProperty("exportar.aceptar"));
                Exportar.jButtonCancelar.setText(idioma.getProperty("exportar.cancelar"));
                Exportar.pathError = idioma.getProperty("exportar.pathError");
                Exportar.exportError = idioma.getProperty("exportar.error");
                break;
        }
    }

    public static void actualizarTema(int tema, int opcion) {
        int logoDimension = Validations.menorSize((Principal.pantalla.height * 90) / 1080, (Principal.pantalla.width * 90) / 1920);
        int iconDimension = Validations.menorSize((Principal.pantalla.height * 30) / 1080, (Principal.pantalla.width * 30) / 1920);
        
        switch (opcion){
            case 0:
                Image newimg;
                switch (tema){
                    case 1:
                        Principal.jPanelGeneral.setBackground(new java.awt.Color(205, 242, 154));
                        Principal.jPanelScramble.setBackground(new java.awt.Color(255, 204, 204));
                        Principal.jPanelAside.setBackground(new java.awt.Color(255, 204, 204));
                        Principal.jPanelTiempos.setBackground(new java.awt.Color(255, 204, 204));
                        Principal.jPanelEstadisticas.setBackground(new java.awt.Color(255, 204, 204));
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
                        Principal.l_worst_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao5_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao100_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao12_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_totalavg_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, 0, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.jPanelTiempos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(180, 180, 180), 1, true));
                        
                        Principal.l_fondo.setVisible(false);
                        break;
                    case 2:
                        Principal.jPanelGeneral.setBackground(new java.awt.Color(227, 227, 227));
                        Principal.jPanelScramble.setBackground(new java.awt.Color(178, 178, 178));
                        Principal.jPanelAside.setBackground(new java.awt.Color(178, 178, 178));
                        Principal.jPanelTiempos.setBackground(new java.awt.Color(178, 178, 178));
                        Principal.jPanelEstadisticas.setBackground(new java.awt.Color(178, 178, 178));
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
                        
                        Principal.l_fondo.setVisible(false);
                        break;
                    case 3:
                        Principal.jPanelGeneral.setBackground(new java.awt.Color(53, 54, 58));
                        Principal.jPanelScramble.setBackground(new java.awt.Color(33, 34, 38));
                        Principal.jPanelAside.setBackground(new java.awt.Color(33, 34, 38));
                        Principal.jPanelTiempos.setBackground(new java.awt.Color(33, 34, 38));
                        Principal.jPanelEstadisticas.setBackground(new java.awt.Color(33, 34, 38));
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
                        
                        Principal.l_fondo.setVisible(false);
                        break;
                    case 4:
                        Principal.jPanelGeneral.setBackground(new java.awt.Color(205, 242, 154));
                        Principal.jPanelScramble.setBackground(new java.awt.Color(255, 197, 160));
                        Principal.jPanelAside.setBackground(new java.awt.Color(255, 197, 160));
                        Principal.jPanelTiempos.setBackground(new java.awt.Color(255, 197, 160));
                        Principal.jPanelEstadisticas.setBackground(new java.awt.Color(255, 197, 160));
                        Principal.Listado.setBackground(new java.awt.Color(255, 197, 160));
                        
                        Principal.t_tiempo.setForeground(new java.awt.Color(255, 0, 243));
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
                        Principal.colorTiempo = new java.awt.Color(255, 0, 243);
                        
                        Principal.l_total.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_best.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_worst.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao5.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao12.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao100.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, -1, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_totalavg.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, -1), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_total_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_best_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_worst_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao5_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao100_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_ao12_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, -1, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.l_totalavg_2.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, -1, 0, 0), BorderFactory.createDashedBorder(new java.awt.Color(180, 180, 180), 5, 5)));
                        Principal.jPanelTiempos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(180, 180, 180), 1, true));
                        
                        Principal.l_fondo.setVisible(true);
                        break;
                }
                break;
            case 1:
                ImageIcon iconLogo;
                ImageIcon iconCerrar;
                ImageIcon iconCerrarHover;
                switch (tema){
                    case 1:
                        
                        InformacionAVG.panelGeneral.setBackground(new java.awt.Color(238, 238, 238));
                        InformacionAVG.Listado.setBackground(new java.awt.Color(238, 238, 238));
                        
                        InformacionAVG.l_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionAVG.jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(17, 61));
                        InformacionAVG.Listado.setForeground(new java.awt.Color(51, 51, 51));
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.l_img.setIcon(new ImageIcon(newimg));
                        iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
                        newimg = iconCerrar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.l_cerrar.setIcon(new ImageIcon(newimg));
                        InformacionAVG.iconCerrar = new ImageIcon(newimg);
                        iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
                        newimg = iconCerrarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.iconCerrarHover = new ImageIcon(newimg);
                        break;
                    case 2:
                        InformacionAVG.panelGeneral.setBackground(new java.awt.Color(178, 178, 178));
                        InformacionAVG.Listado.setBackground(new java.awt.Color(178, 178, 178));
                        
                        InformacionAVG.l_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionAVG.jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(17, 61));
                        InformacionAVG.Listado.setForeground(new java.awt.Color(51, 51, 51));
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-gris90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.l_img.setIcon(new ImageIcon(newimg));
                        iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
                        newimg = iconCerrar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.l_cerrar.setIcon(new ImageIcon(newimg));
                        InformacionAVG.iconCerrar = new ImageIcon(newimg);
                        iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
                        newimg = iconCerrarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.iconCerrarHover = new ImageIcon(newimg);
                        break;
                    case 3:
                        InformacionAVG.panelGeneral.setBackground(new java.awt.Color(33, 34, 38));
                        InformacionAVG.Listado.setBackground(new java.awt.Color(33, 34, 38));
                        
                        InformacionAVG.l_nombre.setForeground(new java.awt.Color(233, 234, 237));
                        InformacionAVG.jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
                        InformacionAVG.Listado.setForeground(new java.awt.Color(233, 234, 237));
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-negro90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.l_img.setIcon(new ImageIcon(newimg));
                        iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-blanco.png"));
                        newimg = iconCerrar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.l_cerrar.setIcon(new ImageIcon(newimg));
                        InformacionAVG.iconCerrar = new ImageIcon(newimg);
                        iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-blanco-hover-gris.png"));
                        newimg = iconCerrarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.iconCerrarHover = new ImageIcon(newimg);
                        break;
                    case 4:
                        InformacionAVG.panelGeneral.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionAVG.Listado.setBackground(new java.awt.Color(255, 197, 160));
                        
                        InformacionAVG.l_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        InformacionAVG.jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
                        InformacionAVG.Listado.setForeground(new java.awt.Color(51, 51, 51));
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-naranja90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.l_img.setIcon(new ImageIcon(newimg));
                        iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
                        newimg = iconCerrar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.l_cerrar.setIcon(new ImageIcon(newimg));
                        InformacionAVG.iconCerrar = new ImageIcon(newimg);
                        iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
                        newimg = iconCerrarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionAVG.iconCerrarHover = new ImageIcon(newimg);
                        break;
                }
                break;
            case 2:
                switch (tema){
                    case 1:
                        InformacionSesion.jPanel1.setBackground(new java.awt.Color(238, 238, 238));
                        
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
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionSesion.l_img.setIcon(new ImageIcon(newimg));
                        break;
                    case 2:
                        InformacionSesion.jPanel1.setBackground(new java.awt.Color(178, 178, 178));
                        
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
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-gris90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionSesion.l_img.setIcon(new ImageIcon(newimg));
                        break;
                    case 3:
                        InformacionSesion.jPanel1.setBackground(new java.awt.Color(33, 34, 38));
                        
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
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-negro90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionSesion.l_img.setIcon(new ImageIcon(newimg));
                        break;
                    case 4:
                        InformacionSesion.jPanel1.setBackground(new java.awt.Color(255, 197, 160));
                        
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
                        
                        InformacionSesion.t_total.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_nombre.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_avg.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_desv.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_peor.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_mejor.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_cao5.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_cao12.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_cao100.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_bao5.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_bao12.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSesion.t_bao100.setBackground(new java.awt.Color(255, 197, 160));
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-naranja90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionSesion.l_img.setIcon(new ImageIcon(newimg));
                        break;
                }
                break;
            case 3:
                switch (tema){
                    case 1:
                        InformacionSolve.jPanel1.setBackground(new java.awt.Color(238, 238, 238));
                        
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
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionSolve.l_img.setIcon(new ImageIcon(newimg));
                        break;
                    case 2:
                        InformacionSolve.jPanel1.setBackground(new java.awt.Color(178, 178, 178));
                        
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
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-gris90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionSolve.l_img.setIcon(new ImageIcon(newimg));
                        break;
                    case 3:
                        InformacionSolve.jPanel1.setBackground(new java.awt.Color(33, 34, 38));
                        
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
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-negro90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionSolve.l_img.setIcon(new ImageIcon(newimg));
                        break;
                    case 4:
                        InformacionSolve.jPanel1.setBackground(new java.awt.Color(255, 197, 160));
                        
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
                        
                        InformacionSolve.t_fecha.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSolve.t_hora.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSolve.t_id.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSolve.t_scramble.setBackground(new java.awt.Color(255, 197, 160));
                        InformacionSolve.t_tiempo.setBackground(new java.awt.Color(255, 197, 160));
                        
                        InformacionSolve.t_fecha.setBorder(null);
                        InformacionSolve.t_hora.setBorder(null);
                        InformacionSolve.t_id.setBorder(null);
                        InformacionSolve.t_scramble.setBorder(null);
                        InformacionSolve.t_tiempo.setBorder(null);
                        
                        iconLogo = new ImageIcon(ClassLoader.getSystemResource("Imagenes/logo-naranja90x90.png"));
                        newimg = iconLogo.getImage().getScaledInstance(logoDimension, logoDimension,  java.awt.Image.SCALE_SMOOTH);
                        InformacionSolve.l_img.setIcon(new ImageIcon(newimg));
                        break;
                }
                break;
            case 4:
                switch (tema){
                    case 1:
                        NuevaSesion.jPanel1.setBackground(new java.awt.Color(238, 238, 238));
                        NuevaSesion.l_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        break;
                    case 2:
                        NuevaSesion.jPanel1.setBackground(new java.awt.Color(178, 178, 178));
                        NuevaSesion.l_nombre.setForeground(new java.awt.Color(51, 51, 51));
                        break;
                    case 3:
                        NuevaSesion.jPanel1.setBackground(new java.awt.Color(33, 34, 38));
                        NuevaSesion.l_nombre.setForeground(new java.awt.Color(238, 238, 238));
                        break;
                    case 4:
                        NuevaSesion.jPanel1.setBackground(new java.awt.Color(255, 197, 160));
                        NuevaSesion.l_nombre.setForeground(new java.awt.Color(51, 51, 51));
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
                    case 4:
                        NuevoSolve.jPanel1.setBackground(new java.awt.Color(255, 197, 160));
                        
                        NuevoSolve.l_scramble.setForeground(new java.awt.Color(51,51,51));
                        NuevoSolve.l_tiempo.setForeground(new java.awt.Color(51,51,51));
                        NuevoSolve.l_titulo.setForeground(new java.awt.Color(51,51,51));
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
                        Preferencias.jPanelIdiomas.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelOcultarElementos.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelOcultarPreview.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelPulsacionLarga.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelCronoRaton.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelTiempo_inspeccion.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelSegundos_inspeccion.setBackground(new java.awt.Color(255,179,179));
                        
                        Preferencias.l_idioma.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.l_temas.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.l_titulo.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jCheckBoxOcultarElementos.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jCheckBoxPulsacion_larga.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jCheckBoxOcultar_preview.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jCheckBoxTiempo_inspeccion.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jCheckBoxCrono_raton.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.l_segundos.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.l_segundosInspeccion.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,179,179)));
                        
                        Preferencias.l_temas.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_idioma.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_titulo.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxOcultarElementos.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxPulsacion_larga.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxOcultar_preview.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxTiempo_inspeccion.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxCrono_raton.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_segundos.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_segundosInspeccion.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 2:
                        Preferencias.jPanelFondo.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jPanelGeneral.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jPanelTitulo.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jPanelTemas.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jPanelIdiomas.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jPanelOcultarElementos.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jPanelOcultarPreview.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jPanelPulsacionLarga.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jPanelCronoRaton.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jPanelTiempo_inspeccion.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jPanelSegundos_inspeccion.setBackground(new java.awt.Color(178, 178, 178));
                        
                        Preferencias.l_idioma.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.l_temas.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.l_titulo.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jCheckBoxOcultarElementos.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxPulsacion_larga.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxOcultar_preview.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jCheckBoxTiempo_inspeccion.setBackground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxCrono_raton.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.l_segundos.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.l_segundosInspeccion.setBackground(new java.awt.Color(178, 178, 178));
                        Preferencias.jPanelGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(178, 178, 178)));
                        
                        Preferencias.l_temas.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_idioma.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_titulo.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxOcultarElementos.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxPulsacion_larga.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxOcultar_preview.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxTiempo_inspeccion.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxCrono_raton.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_segundos.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_segundosInspeccion.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 3:
                        Preferencias.jPanelFondo.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jPanelGeneral.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jPanelTitulo.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jPanelTemas.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jPanelIdiomas.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jPanelOcultarElementos.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jPanelOcultarPreview.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jPanelPulsacionLarga.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jPanelCronoRaton.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jPanelTiempo_inspeccion.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jPanelSegundos_inspeccion.setBackground(new java.awt.Color(33, 34, 38));
                        
                        Preferencias.l_idioma.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.l_temas.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.l_titulo.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jCheckBoxOcultarElementos.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jCheckBoxPulsacion_larga.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jCheckBoxOcultar_preview.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jCheckBoxTiempo_inspeccion.setBackground(new java.awt.Color(53, 54, 58));
                        Preferencias.jCheckBoxCrono_raton.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.l_segundos.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.l_segundosInspeccion.setBackground(new java.awt.Color(33, 34, 38));
                        Preferencias.jPanelGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(53, 54, 58)));
                        
                        Preferencias.l_temas.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.l_idioma.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.l_titulo.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxOcultarElementos.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxPulsacion_larga.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxOcultar_preview.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxTiempo_inspeccion.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.jCheckBoxCrono_raton.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.l_segundos.setForeground(new java.awt.Color(238, 238, 238));
                        Preferencias.l_segundosInspeccion.setForeground(new java.awt.Color(238, 238, 238));
                        break;
                    case 4:
                        Preferencias.jPanelFondo.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelGeneral.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelTitulo.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelTemas.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelIdiomas.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelOcultarElementos.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelOcultarPreview.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelPulsacionLarga.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelCronoRaton.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelTiempo_inspeccion.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jPanelSegundos_inspeccion.setBackground(new java.awt.Color(255,179,179));
                        
                        Preferencias.l_idioma.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.l_temas.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.l_titulo.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jCheckBoxOcultarElementos.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jCheckBoxPulsacion_larga.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jCheckBoxOcultar_preview.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jCheckBoxTiempo_inspeccion.setBackground(new java.awt.Color(255, 219, 219));
                        Preferencias.jCheckBoxCrono_raton.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.l_segundos.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.l_segundosInspeccion.setBackground(new java.awt.Color(255,179,179));
                        Preferencias.jPanelGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255,179,179)));
                        
                        Preferencias.l_temas.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_idioma.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_titulo.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxOcultarElementos.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxPulsacion_larga.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxOcultar_preview.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxTiempo_inspeccion.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.jCheckBoxCrono_raton.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_segundos.setForeground(new java.awt.Color(51,51,51));
                        Preferencias.l_segundosInspeccion.setForeground(new java.awt.Color(51,51,51));
                        break;
                }
                break;
            case 7:
                switch (tema){
                    case 1:
                        ScramblePreview.panelGeneral.setBackground(new java.awt.Color(238, 238, 238));
                        
                        iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
                        newimg = iconCerrar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        ScramblePreview.l_cerrar.setIcon(new ImageIcon(newimg));
                        ScramblePreview.iconCerrar = new ImageIcon(newimg);
                        iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
                        newimg = iconCerrarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        ScramblePreview.iconCerrarHover = new ImageIcon(newimg);
                        break;
                    case 2:
                        ScramblePreview.panelGeneral.setBackground(new java.awt.Color(178, 178, 178));
                        
                        iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
                        newimg = iconCerrar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        ScramblePreview.l_cerrar.setIcon(new ImageIcon(newimg));
                        ScramblePreview.iconCerrar = new ImageIcon(newimg);
                        iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
                        newimg = iconCerrarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        ScramblePreview.iconCerrarHover = new ImageIcon(newimg);
                        break;
                    case 3:
                        ScramblePreview.panelGeneral.setBackground(new java.awt.Color(33, 34, 38));
                        
                        iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-blanco.png"));
                        newimg = iconCerrar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        ScramblePreview.l_cerrar.setIcon(new ImageIcon(newimg));
                        ScramblePreview.iconCerrar = new ImageIcon(newimg);
                        iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-blanco-hover-gris.png"));
                        newimg = iconCerrarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        ScramblePreview.iconCerrarHover = new ImageIcon(newimg);
                        break;
                    case 4:
                        ScramblePreview.panelGeneral.setBackground(new java.awt.Color(255, 197, 160));
                        
                        iconCerrar = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar.png"));
                        newimg = iconCerrar.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        ScramblePreview.l_cerrar.setIcon(new ImageIcon(newimg));
                        ScramblePreview.iconCerrar = new ImageIcon(newimg);
                        iconCerrarHover = new ImageIcon(ClassLoader.getSystemResource("Imagenes/cerrar-hover-gris.png"));
                        newimg = iconCerrarHover.getImage().getScaledInstance(iconDimension, iconDimension,  java.awt.Image.SCALE_SMOOTH);
                        ScramblePreview.iconCerrarHover = new ImageIcon(newimg);
                        break;
                }
                break;
            case 8:
                switch (tema){
                    case 1:
                        NuevoScramble.panelGeneral.setBackground(new java.awt.Color(238, 238, 238));
                        NuevoScramble.l_scramble.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 2:
                        NuevoScramble.panelGeneral.setBackground(new java.awt.Color(178, 178, 178));
                        NuevoScramble.l_scramble.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 3:
                        NuevoScramble.panelGeneral.setBackground(new java.awt.Color(33, 34, 38));
                        NuevoScramble.l_scramble.setForeground(new java.awt.Color(238, 238, 238));
                        break;
                    case 4:
                        NuevoScramble.panelGeneral.setBackground(new java.awt.Color(255, 197, 160));
                        NuevoScramble.l_scramble.setForeground(new java.awt.Color(51,51,51));
                        break;
                }
                break;
            case 9:
                switch (tema){
                    case 1:
                        Exportar.panelGeneral.setBackground(new java.awt.Color(238, 238, 238));
                        Exportar.l_titulo.setForeground(new java.awt.Color(51,51,51));
                        Exportar.l_carpeta.setForeground(new java.awt.Color(51,51,51));
                        Exportar.l_sesion.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 2:
                        Exportar.panelGeneral.setBackground(new java.awt.Color(178, 178, 178));
                        Exportar.l_titulo.setForeground(new java.awt.Color(51,51,51));
                        Exportar.l_carpeta.setForeground(new java.awt.Color(51,51,51));
                        Exportar.l_sesion.setForeground(new java.awt.Color(51,51,51));
                        break;
                    case 3:
                        Exportar.panelGeneral.setBackground(new java.awt.Color(33, 34, 38));
                        Exportar.l_titulo.setForeground(new java.awt.Color(238, 238, 238));
                        Exportar.l_carpeta.setForeground(new java.awt.Color(238, 238, 238));
                        Exportar.l_sesion.setForeground(new java.awt.Color(238, 238, 238));
                        break;
                    case 4:
                        Exportar.panelGeneral.setBackground(new java.awt.Color(255, 197, 160));
                        Exportar.l_titulo.setForeground(new java.awt.Color(51,51,51));
                        Exportar.l_carpeta.setForeground(new java.awt.Color(51,51,51));
                        Exportar.l_sesion.setForeground(new java.awt.Color(51,51,51));
                        break;
                }
                break;
        }
        
    }
}
