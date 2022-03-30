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
    
    public static void actualizarTiempo() {
        String cadena, seg, min;
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
        Principal.t_tiempo.setText(cadena);
    }
}
