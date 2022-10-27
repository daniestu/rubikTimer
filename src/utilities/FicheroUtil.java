/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import client.Principal;
import controller.SesionDao;
import controller.SolveDao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.Solve;

/**
 *
 * @author Dani
 */
public class FicheroUtil {
    
    public static void actualizarFichero (String tiempo, String scramble, Date fecha, boolean sum2, boolean dnf) throws IOException, ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

        FileWriter fw = new FileWriter(Principal.ficheroSesion, true);
        int linea = establecerLinea(Principal.ficheroSesion);
        fw.write(linea + ";" + scramble.toUpperCase() + ";" + formatoFecha.format(fecha) + ";" + formatoHora.format(fecha) + ";" + tiempo + ";0;0\n");
        fw.close();
        Principal.solves.add(new Solve(linea, scramble, fecha, tiempo, sum2, dnf));
    }
    
    public static void sobreescibirFichero(ArrayList<Solve> solves) throws IOException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        
        FileWriter fw = new FileWriter(Principal.ficheroSesion);
        
        for (Solve solve : solves) {
            String sum2 = "0";
            String dnf = "0";
            if (solve.getSum2()) {
                sum2 = "1";
            }
            if (solve.getDnf()) {
                dnf = "1";
            }
            fw.write(solve.getNum() + ";" + solve.getScramble().toUpperCase() + ";" + formatoFecha.format(solve.getFecha()) + ";" + formatoHora.format(solve.getFecha()) + ";" + solve.getTiempo() + ";" + sum2 + ";" + dnf + "\n");
        }
        fw.close();
    }
    
    public static int establecerLinea(File ficheroSesion) throws FileNotFoundException, IOException {
        int cont = 0;
        BufferedReader bfr = new BufferedReader(new FileReader(ficheroSesion));
        while (bfr.readLine() != null) {
            cont++;
        }
        bfr.close();
        return cont + 1;
    }
    
    public static void establecerCFG(int tema, int ocultar_todo, int ocultar_preview, int pulsacion_larga, int cronometro_raton, int inspeccion, int seg_inspeccion, String idioma) throws IOException {
        File f = new File("CFG.INI");
        FileWriter fw = new FileWriter(f);
        fw.write("#Última sesión abierta.\nsesion=" + Principal.ficheroSesion.getName() + "\n");
        fw.write("#Tema actual.\ntema=" + tema + "\n");
        fw.write("#Ocultar todos los elementos cuando se cronometra. 0 -> NO || 1 -> SI.\nocultar-todo=" + ocultar_todo + "\n");
        fw.write("#Ocultar preview del cubo asociado a la mezcla. 0 -> NO || 1 -> SI.\nocultar-preview=" + ocultar_preview + "\n");
        fw.write("#Activar o desactivar la pulsación larga. 0 -> NO || 1 -> SI.\npulsacion-larga=" + pulsacion_larga + "\n");
        fw.write("#Usar el clicik izquierdo del ratón para empezar a cronometrar. 0 -> NO || 1 -> SI.\ncronometro-raton=" + cronometro_raton + "\n");
        fw.write("#Activar el tiempo de inspección. 0 -> NO || 1 -> SI.\ninspeccion=" + inspeccion + "\n");
        fw.write("#Tiempo seleccionado de inspección en segundos.\nsegundos-inspeccion=" + seg_inspeccion + "\n");
        fw.write("#Idioma actual.\nidioma=" + idioma);
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
                String sum2 = "0";
                String dnf = "0";
                if (i.getSum2()) {
                    sum2 = "1";
                }
                if (i.getDnf()) {
                    dnf = "1";
                }
                fw.write(i.getNum() + ";" + i.getScramble() + ";" + formatoFecha.format(i.getFecha()) + ";" + formatoHora.format(i.getFecha()) + ";" + i.getTiempo() + ";" + sum2 + ";" + dnf + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static ArrayList <String> fileToArray(File archivo) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try (BufferedReader bfr = new BufferedReader(new FileReader(archivo))){
            ArrayList <String> solves = new ArrayList<String>();
            
            String linea;
            while((linea = bfr.readLine()) != null){
                String parts[] = linea.split(";");
                if (!Validations.validarImporte(parts)) {
                    throw new Exception("Formato incorrecto");
                }
                solves.add(parts[1] + ";" + parts[2] + ";" + parts[3] + ";" + parts[4] + ";" + parts[5] + ";" + parts[6]);
            }
            return solves;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static boolean importarSolves(File archivo, File sesion) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        try {
            ArrayList <String> solves = FicheroUtil.fileToArray(archivo);
            for (String solve : solves) {
                String parts[] = solve.split(";");
                Date fecha = formato.parse(parts[1] + " " + parts[2]);
                FicheroUtil.actualizarFichero(parts[3], parts[0], fecha, parts[4].equals("1"), parts[5].equals("1"));
            }
            Principal.Listado.setModel(SolveDao.cargarSolves(Principal.solves, 0));
            SesionDao.cargarSesion();
            Principal.Listado.ensureIndexIsVisible(Principal.Listado.getModel().getSize() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean exportarSolves(File destino, File origen) {
        destino = FicheroNuevo(destino);
        try (FileWriter fw = new FileWriter(new File(destino.getAbsolutePath()), true);BufferedReader bfr = new BufferedReader(new FileReader(origen))){
            String linea;
            while ( (linea = bfr.readLine()) != null ) {                
                fw.write(linea + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    private static File FicheroNuevo(File fichero) {
        if (fichero.exists()) {
            int index = 0;
            
            String parts[] = fichero.getName().split("[.]");
            String nombreFichero = "";
            for (int i = 0; i < parts.length -1; i++) {
                if (i == 0) {
                    nombreFichero += parts[i];
                }else{
                    nombreFichero += "." + parts[i];
                }
            }
            while (true) {
                index ++;
                if ( !(new File(fichero.getParent() + "\\" + nombreFichero + "(" + index + ").txt").exists()) ) {
                    return new File(fichero.getParent() + "\\" + nombreFichero + "(" + index + ").txt");
                }
            }
        }
        return fichero;
    }
}
