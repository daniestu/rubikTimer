/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import client.Principal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import model.Solve;

/**
 *
 * @author Dani
 */
public class FicheroUtil {
    
    public static int establecerLinea(File ficheroSesion) throws FileNotFoundException, IOException {
        int cont = 0;
        BufferedReader bfr = new BufferedReader(new FileReader(ficheroSesion));
        while (bfr.readLine() != null) {
            cont++;
        }
        bfr.close();
        return cont + 1;
    }
    
    
    
    public static void establecerCFG(int tema, int ocultar_todo, int ocultar_preview, int pulsacion_larga, int cronometro_raton, int inspeccion, int seg_inspeccion) throws IOException {
        File f = new File("CFG.INI");
        FileWriter fw = new FileWriter(f);
        fw.write("#Última sesión abierta.\nsesion=" + Principal.ficheroSesion.getName() + "\n");
        fw.write("#Tema actual.\ntema=" + tema + "\n");
        fw.write("#Ocultar todos los elementos cuando se cronometra. 0 -> NO || 1 -> SI.\nocultar-todo=" + ocultar_todo + "\n");
        fw.write("#Ocultar preview del cubo asociado a la mezcla. 0 -> NO || 1 -> SI.\nocultar-preview=" + ocultar_preview + "\n");
        fw.write("#Activar o desactivar la pulsación larga. 0 -> NO || 1 -> SI.\npulsacion-larga=" + pulsacion_larga + "\n");
        fw.write("#Usar el clicik izquierdo del ratón para empezar a cronometrar. 0 -> NO || 1 -> SI.\ncronometro-raton=" + cronometro_raton + "\n");
        fw.write("#Activar el tiempo de inspección. 0 -> NO || 1 -> SI.\ninspeccion=" + inspeccion + "\n");
        fw.write("#Tiempo seleccionado de inspección en segundos.\nsegundos-inspeccion=" + seg_inspeccion);
        fw.close();
    }
    
    public static void rehacerFichero() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        int cont = 0;
        for (Solve i : Principal.solves) {
            cont++;
            i.setNum(cont);
        }
        try {
            FileWriter fw = new FileWriter(Principal.ficheroSesion);
            for (Solve i : Principal.solves) {
                fw.write(i.getNum() + ";" + i.getScramble() + ";" + formatoFecha.format(i.getFecha()) + ";" + formatoHora.format(i.getFecha()) + ";" + i.getTiempo() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
