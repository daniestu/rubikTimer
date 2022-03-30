/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import client.Principal;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ComboBoxModel;
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
    
    public static String mejorAVG(int num) {
        ArrayList <Solve> al = new ArrayList();
        int suma;
        String mejoravg = "";
        
        for (Solve i : Principal.solves) {
            al.add(i);
            suma=0;
            if (al.size()==num+1) {
                al.remove(0);
                suma = al.stream().map((j) -> convertirTiempoMs(j.getTiempo())).reduce(suma, Integer::sum);
                if (esMejorTiempo(mejoravg, convertirMsTiempo(suma/num))) {
                    mejoravg = convertirMsTiempo(suma/num);
                }
            }else{
                if (al.size()==num) {
                    suma = al.stream().map((j) -> convertirTiempoMs(j.getTiempo())).reduce(suma, Integer::sum);
                    mejoravg = convertirMsTiempo(suma/num);
                }
            }
        }
        
        return mejoravg;
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
        
        //scramble = "D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2 D2";
        return scramble;
    }
}
