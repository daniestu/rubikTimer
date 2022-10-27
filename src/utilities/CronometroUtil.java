/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import client.Principal;

/**
 *
 * @author Dani
 */
public class CronometroUtil {
    
    public static void actualizarTiempo(int opcion) {
        String cadena;
        if (opcion == 0) {
            String seg, min;
            if (Principal.s < 10) {
                seg = "0" + Principal.s;
            } else {
                seg = "" + Principal.s;
            }
            if (Principal.m < 10) {
                min = "0" + Principal.m;
            } else {
                min = "" + Principal.m;
            }
            cadena = min + ":" + seg + ":" + Principal.cs + Principal.ms;
        } else {
            if (Principal.s_inspeccion != 0) {
                cadena = Principal.s_inspeccion + "";
            } else {
                cadena = "00:00:00";
                Principal.t_inspeccion.stop();
                Principal.corriendo = false;
                Principal.sufic = true;
                Principal.inspeccion = false;
                Principal.corriendo_inspeccion = false;
                Principal.tecla_soltada();
            }
            
        }
        Principal.t_tiempo.setText(cadena);
    }
}
