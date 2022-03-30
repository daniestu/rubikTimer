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
import model.Sesion;
import model.Solve;
import utils.FicheroUtil;
import utils.PrincipalUtil;

/**
 *
 * @author Dani
 */
public class SesionDao {
    public static Sesion infoSesion() {
        String nombre = Principal.ficheroSesion.getName();
        
        String bestAo5 = PrincipalUtil.mejorAVG(5);
        String bestAo12 = PrincipalUtil.mejorAVG(12);
        String bestAo100 = PrincipalUtil.mejorAVG(100);
        
        int totalSolves = Principal.solves.size();
        
        return new Sesion(nombre, Principal.mejor, Principal.peor, Principal.avg, bestAo5, bestAo12, bestAo100, Principal.currentAo5, Principal.currentAo12, Principal.currentAo100, totalSolves);
    }
    
    public static void cargarSesion() throws FileNotFoundException, IOException {
        int num, total, cont, m, s, ms, suma, ocultar_todo, tema, ocultar_preview, pulsacion_larga, cronometro_raton, inspeccion, seg_inspeccion;
        String linea, scramble, fecha, hora, tiempo;
        BufferedReader bfr = new BufferedReader(new FileReader(Principal.ficheroSesion));
        cont = 0;
        suma = 0;

        Principal.l_ao5_2.setText("");
        Principal.l_ao12_2.setText("");
        Principal.l_ao100_2.setText("");
        Principal.l_totalavg_2.setText("");

        Principal.solves.clear();

        while ((linea = bfr.readLine()) != null) {
            String parts[] = linea.split(";");
            num = Integer.parseInt(parts[0]);
            scramble = parts[1];
            fecha = parts[2];
            hora = parts[3];
            tiempo = parts[4];

            Principal.solves.add(new Solve(num, scramble, fecha, hora, tiempo));

            if (Principal.solves.size() == 1) {
                Principal.mejor = new Solve(num, scramble, fecha, hora, tiempo);
                Principal.peor = new Solve(num, scramble, fecha, hora, tiempo);
            } else {
                if (PrincipalUtil.esMejorTiempo(Principal.mejor.getTiempo(), tiempo)) {
                    Principal.mejor = new Solve(num, scramble, fecha, hora, tiempo);
                }
                if (PrincipalUtil.esPeorTiempo(Principal.peor.getTiempo(), tiempo)) {
                    Principal.peor = new Solve(num, scramble, fecha, hora, tiempo);
                }
            }
        }
        
        if (!Principal.solves.isEmpty()) {
            for (int i = Principal.solves.size() - 1; i >= 0; i--) {

                cont++;
                suma += PrincipalUtil.convertirTiempoMs(Principal.solves.get(i).getTiempo());

                if (cont == 5) {
                    Principal.currentAo5 = PrincipalUtil.convertirMsTiempo(suma / 5);
                    Principal.l_ao5_2.setText(Principal.currentAo5);
                }
                if (cont == 12) {
                    Principal.currentAo12 = PrincipalUtil.convertirMsTiempo(suma / 12);
                    Principal.l_ao12_2.setText(Principal.currentAo12);
                }
                if (cont == 100) {
                    Principal.currentAo100 = PrincipalUtil.convertirMsTiempo(suma / 100);
                    Principal.l_ao100_2.setText(Principal.currentAo100);
                }
                if (i == 0) {
                    Principal.avg = PrincipalUtil.convertirMsTiempo(suma / Principal.solves.size());
                    Principal.l_totalavg_2.setText(Principal.avg);
                }
            }
            Principal.l_best_2.setText(Principal.mejor.getTiempo());
            Principal.l_worst_2.setText(Principal.peor.getTiempo());
        }else{
            Principal.l_best_2.setText("");
            Principal.l_worst_2.setText("");
            Principal.mejor = new Solve(null, null, null, null, "");
            Principal.peor = new Solve(null, null, null, null, "");
            Principal.currentAo5 = "";
            Principal.currentAo12 = "";
            Principal.currentAo100 = "";
            Principal.avg = "";
        }
        total = Principal.solves.size();

        Principal.l_total_2.setText("" + total);

        try {
            Principal.campo_sesion.setText(Principal.ficheroSesion.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Principal.Listado.setModel(SolveDao.cargarSolves());
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
            
            if (Principal.inspeccion) {
               inspeccion = 1; 
            }else{
               inspeccion = 0;
            }
            seg_inspeccion = Principal.tiempo_inspeccion;
            tema = Principal.tema;
            
            FicheroUtil.establecerCFG(tema, ocultar_todo, ocultar_preview, pulsacion_larga, cronometro_raton, inspeccion, seg_inspeccion);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        bfr.close();
        
        if (Principal.ocultar_preview) {
            Principal.jPanelPreview.setVisible(false);
        }else{
            Principal.jPanelPreview.setVisible(true);
        }
        
    }
}
