/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.Principal;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import model.AVG;
import model.Sesion;
import model.Solve;
import utilities.FicheroUtil;
import utilities.PrincipalUtil;
import utilities.Validations;

/**
 *
 * @author Dani
 */
public class SesionDao {
    public static Sesion infoSesion() {
        String nombre = Principal.ficheroSesion.getName();
        AVG bestAo5 = Validations.mejorAVG(5);
        AVG bestAo12 = Validations.mejorAVG(12);
        AVG bestAo100 = Validations.mejorAVG(100);
        double desviacion = calcularDesviacion(Principal.solves, Principal.avg.getTiempo());
        int totalSolves = Principal.solves.size();
        
        return new Sesion(nombre, Principal.mejor, Principal.peor, Principal.avg, desviacion, bestAo5, bestAo12, bestAo100, Principal.currentAo5, Principal.currentAo12, Principal.currentAo100, totalSolves);
    }
    
    public static void cargarSesion() throws FileNotFoundException, IOException, ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        int num, total, cont, m, s, ms, suma, sumaTotal, ocultar_todo, tema, ocultar_preview, pulsacion_larga, cronometro_raton, inspeccion, seg_inspeccion;
        String linea, scramble, tiempo, idioma;
        boolean sum2, dnf;
        Date fecha;
        BufferedReader bfr = new BufferedReader(new FileReader(Principal.ficheroSesion));
        cont = 0;
        suma = 0;
        sumaTotal = 0;

        Principal.l_ao5_2.setText("");
        Principal.l_ao12_2.setText("");
        Principal.l_ao100_2.setText("");
        Principal.l_totalavg_2.setText("");
        Principal.currentAo5 = new AVG("", new ArrayList<Solve>(), false);
        Principal.currentAo12 = new AVG("", new ArrayList<Solve>(), false);
        Principal.currentAo100 = new AVG("", new ArrayList<Solve>(), false);

        Principal.solves.clear();

        while ((linea = bfr.readLine()) != null) {
            String parts[] = linea.split(";");
            num = Integer.parseInt(parts[0]);
            scramble = parts[1];
            fecha = formatoFecha.parse(parts[2] + " " + parts[3]);
            tiempo = parts[4];
            sum2 = parts[5].equals("1");
            dnf = parts[6].equals("1");
            

            Principal.solves.add(new Solve(num, scramble, fecha, tiempo, sum2, dnf));

            if (Principal.solves.size() == 1) {
                Principal.mejor = new Solve(num, scramble, fecha, tiempo, sum2, dnf);
                Principal.peor = new Solve(num, scramble, fecha, tiempo, sum2, dnf);
            } else {
                if (!dnf) {
                    if (Principal.mejor.getDnf()) {
                        Principal.mejor = new Solve(num, scramble, fecha, tiempo, sum2, dnf);
                    }else{
                        if (Validations.esMejorTiempo(Principal.mejor.getTiempo(), tiempo)) {
                            Principal.mejor = new Solve(num, scramble, fecha, tiempo, sum2, dnf);
                        }
                    }
                    if (!Principal.peor.getDnf()) {
                        if (Validations.esPeorTiempo(Principal.peor.getTiempo(), tiempo)) {
                            Principal.peor = new Solve(num, scramble, fecha, tiempo, sum2, dnf);
                        }
                    }else{
                        Principal.peor = new Solve(num, scramble, fecha, tiempo, sum2, dnf);
                    }
                }
            }
        }
        
        if (!Principal.solves.isEmpty()) {
            ArrayList<Solve> al = new ArrayList<Solve>();
            Solve mejorTiempo = Principal.solves.get(Principal.solves.size()-1);
            Solve peorTiempo = Principal.solves.get(Principal.solves.size()-1);
            int dnfCont = 0;
            
            for (int i = Principal.solves.size() - 1; i >= 0; i--) {
                if (Principal.solves.get(i).getDnf()) {
                    dnfCont++;
                    peorTiempo = Principal.solves.get(i);
                }else{
                    if (mejorTiempo.getDnf()) {
                        mejorTiempo = Principal.solves.get(i);
                    }else{
                        if (Validations.esMejorTiempo(mejorTiempo.getTiempo(), Principal.solves.get(i).getTiempo())) {
                            mejorTiempo = Principal.solves.get(i);
                        }
                    }
                    if (!peorTiempo.getDnf()) {
                        if (Validations.esPeorTiempo(peorTiempo.getTiempo(), Principal.solves.get(i).getTiempo())) {
                            peorTiempo = Principal.solves.get(i);
                        }
                    }
                    sumaTotal += PrincipalUtil.convertirTiempoMs(Principal.solves.get(i).getTiempo());
                }
                
                al.add(Principal.solves.get(i));
                cont++;
                suma += PrincipalUtil.convertirTiempoMs(Principal.solves.get(i).getTiempo());
                
                if (cont == 5) {
                    Collections.sort(al);
                    int suma5 = suma - ( (PrincipalUtil.convertirTiempoMs(mejorTiempo.getTiempo())) + (PrincipalUtil.convertirTiempoMs(peorTiempo.getTiempo())) );
                    
                    if (dnfCont >=2) {
                        Principal.l_ao5_2.setText("DNF");
                        Principal.currentAo5 = new AVG("DNF", new ArrayList<Solve>(al), true);
                    }else{
                        Principal.currentAo5 = new AVG(PrincipalUtil.convertirMsTiempo(suma5 / 3), new ArrayList<Solve>(al), false);
                        Principal.l_ao5_2.setText(Principal.currentAo5.getTiempo());
                    }
                }
                if (cont == 12) {
                    Collections.sort(al);
                    int suma12 = suma - ( (PrincipalUtil.convertirTiempoMs(mejorTiempo.getTiempo())) + (PrincipalUtil.convertirTiempoMs(peorTiempo.getTiempo())) );
                    
                    
                    if (dnfCont >=2) {
                        Principal.l_ao12_2.setText("DNF");
                        Principal.currentAo12 = new AVG("DNF", new ArrayList<Solve>(al), true);
                    }else{
                        Principal.currentAo12 = new AVG(PrincipalUtil.convertirMsTiempo(suma12 / 10), new ArrayList<Solve>(al), false);
                        Principal.l_ao12_2.setText(Principal.currentAo12.getTiempo());
                    }
                }
                if (cont == 100) {
                    Collections.sort(al);
                    int suma100 = suma - ( (PrincipalUtil.convertirTiempoMs(mejorTiempo.getTiempo())) + (PrincipalUtil.convertirTiempoMs(peorTiempo.getTiempo())) );
                    
                    
                    if (dnfCont >=2) {
                        Principal.l_ao100_2.setText("DNF");
                        Principal.currentAo100 = new AVG("DNF", new ArrayList<Solve>(al), true);
                    }else{
                        Principal.currentAo100 = new AVG(PrincipalUtil.convertirMsTiempo(suma100 / 98), new ArrayList<Solve>(al), false);
                        Principal.l_ao100_2.setText(Principal.currentAo100.getTiempo());
                    }
                }
                if (i == 0) {
                    if (sumaTotal!=0) {
                        Principal.avg = new AVG(PrincipalUtil.convertirMsTiempo(sumaTotal / (Principal.solves.size() - dnfCont)), Principal.solves, false);
                        Principal.l_totalavg_2.setText(Principal.avg.getTiempo());
                    }else{
                        Principal.avg = new AVG("DNF", Principal.solves, true);
                        Principal.l_totalavg_2.setText("DNF");
                    }
                    
                }
            }
            if (Principal.mejor.getDnf()) {
                Principal.l_best_2.setText("DNF");
            }else{
                Principal.l_best_2.setText(Principal.mejor.getTiempo());
            }
            if (Principal.peor.getDnf()) {
                Principal.l_worst_2.setText("DNF");
            }else{
                Principal.l_worst_2.setText(Principal.peor.getTiempo());
            }
            
        }else{
            Principal.l_best_2.setText("");
            Principal.l_worst_2.setText("");
            Principal.mejor = new Solve(null, null, null, "", false, false);
            Principal.peor = new Solve(null, null, null, "", false, false);
            Principal.currentAo5 = new AVG("", null, false);
            Principal.currentAo12 = new AVG("", null, false);
            Principal.currentAo100 = new AVG("", null, false);
            Principal.avg = new AVG("", null, false);
        }
        total = Principal.solves.size();

        Principal.l_total_2.setText("" + total);

        try {
            Principal.campo_sesion.setText(Principal.ficheroSesion.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Principal.Listado.setModel(SolveDao.cargarSolves(Principal.solves, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (Principal.ocultar_todo) {
               ocultar_todo = 1; 
            }else{
               ocultar_todo = 0;
            }
            
            if (Principal.ocultar_preview) {
               ocultar_preview = 1; 
            }else{
               ocultar_preview = 0;
            }
            
            if (Principal.pulsacion_larga) {
               pulsacion_larga = 1; 
            }else{
               pulsacion_larga = 0;
            }
            
            if (Principal.cronometro_raton) {
               cronometro_raton = 1; 
            }else{
               cronometro_raton = 0;
            }
            
            if (Principal.inspeccionActivada) {
               Principal.inspeccion = true;
               inspeccion = 1; 
            }else{
               inspeccion = 0;
            }
            seg_inspeccion = Principal.tiempo_inspeccion;
            tema = Principal.tema;
            
            idioma = Principal.idioma;
            
            FicheroUtil.establecerCFG(tema, ocultar_todo, ocultar_preview, pulsacion_larga, cronometro_raton, inspeccion, seg_inspeccion, idioma);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        bfr.close();
        
        if (Principal.ocultar_preview) {
            Principal.jPanelPreview.setVisible(false);
        }else{
            Principal.jPanelPreview.setVisible(true);
        }
        
        PrincipalUtil.cambiarIdioma(Principal.idioma, 0);
        
        PrincipalUtil.actualizarTema(Principal.tema, 0);
        
    }

    private static double calcularDesviacion(ArrayList<Solve> solves, String avg) {
        int suma = 0, media, size = 0;
        
        try {
            media = PrincipalUtil.convertirTiempoMs(avg);
        } catch (Exception e) {
            return 0;
        }
        
        double diferencia;
        for (Solve i : solves) {
            if (!i.getDnf()) {
                size++;
                
                if (media < PrincipalUtil.convertirTiempoMs(i.getTiempo())) {
                    diferencia = PrincipalUtil.convertirTiempoMs(i.getTiempo()) - media;
                }else{
                    diferencia = media - PrincipalUtil.convertirTiempoMs(i.getTiempo());
                }

                suma += (diferencia*diferencia);
            }
            
        }
        suma = suma / (size - 1);
        diferencia = Math.sqrt(suma)/100;
        return Math.round(diferencia*100.0)/100.0;
    }
}
