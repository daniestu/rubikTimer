/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import client.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import model.AVG;
import model.Solve;
import static utilities.PrincipalUtil.convertirMsTiempo;
import static utilities.PrincipalUtil.convertirTiempoMs;

/**
 *
 * @author Dani
 */
public class Validations {
    
    public static boolean comprobarScramble(String scramble) {
        String moves[] = {"R","R'","R2","L","L'","L2","U","U'","U2","D","D'","D2","F","F'","F2","B","B'","B2"};
        String scrambleMoves[] = scramble.split(" ");
        
        for (String move : scrambleMoves) {
            if (!Arrays.asList(moves).contains(move.toUpperCase())) {
                return false;
            }
        }
        return true;
    }
    
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
    
    public static AVG mejorAVG(int num) {
        ArrayList <Solve> al = new ArrayList();
        ArrayList <Solve> alMejores = new ArrayList();
        int suma;
        String mejoravg = "";
        
        for (Solve i : Principal.solves) {
            int dnfCont = 0;
            boolean dnf=false;
            al.add(i);
            suma=0;
            if (al.size()==num+1) {
                al.remove(0);
                String mejorTiempo = al.get(0).getTiempo();
                String peorTiempo = al.get(0).getTiempo();
                for (Solve j : al) {
                    
                    if (dnf) {
                        if (!j.getDnf()) {
                            if (esMejorTiempo(mejorTiempo, j.getTiempo())) {
                                mejorTiempo = j.getTiempo();
                            }
                        }else{
                            dnfCont++;
                        }

                    }else{
                        if (j.getDnf()) {
                            dnfCont++;
                            dnf = true;
                            peorTiempo = j.getTiempo();
                        }else{
                            if (esMejorTiempo(mejorTiempo, j.getTiempo())) {
                                mejorTiempo = j.getTiempo();
                            }

                            if (esPeorTiempo(peorTiempo, j.getTiempo())) {
                                peorTiempo = j.getTiempo();
                            }
                        }
                    }
                    suma += convertirTiempoMs(j.getTiempo());
                }
                suma = suma - (convertirTiempoMs(mejorTiempo) + convertirTiempoMs(peorTiempo) );
                if (dnfCont<2) {
                    if (esMejorTiempo(mejoravg, convertirMsTiempo(suma/(num - 2)))) {
                        mejoravg = convertirMsTiempo(suma/(num - 2));
                        alMejores = new ArrayList<Solve>(al);
                    }
                }
            }else{
                if (al.size()==num) {
                    String mejorTiempo = al.get(0).getTiempo();
                    String peorTiempo = al.get(0).getTiempo();
                    for (Solve j : al) {
                        if (dnf) {
                            if (!j.getDnf()) {
                                if (esMejorTiempo(mejorTiempo, j.getTiempo())) {
                                    mejorTiempo = j.getTiempo();
                                }
                            }else{
                                dnfCont++;
                            }
                            
                        }else{
                            if (j.getDnf()) {
                                dnfCont++;
                                dnf = true;
                                peorTiempo = j.getTiempo();
                            }else{
                                if (esMejorTiempo(mejorTiempo, j.getTiempo())) {
                                    mejorTiempo = j.getTiempo();
                                }

                                if (esPeorTiempo(peorTiempo, j.getTiempo())) {
                                    peorTiempo = j.getTiempo();
                                }
                            }
                        }
                        
                        
                        suma += convertirTiempoMs(j.getTiempo());
                    }
                    if (dnfCont>=2) {
                        mejoravg = "DNF";
                    }else{
                        suma = suma - (convertirTiempoMs(mejorTiempo) + convertirTiempoMs(peorTiempo) );
                        mejoravg = convertirMsTiempo(suma/(num - 2));
                    }
                    alMejores = new ArrayList<Solve>(al);
                }
            }
        }
        AVG avg;
        if (mejoravg.equals("DNF")) {
            avg = new AVG(mejoravg, alMejores, true);
        }else{
            avg = new AVG(mejoravg, alMejores, false);
        }
        return avg;
    }
    
    public static int menorSize(int size1, int size2) {
        if (size1 < size2) {
            return size1;
        }else{
            return size2;
        }
    }
    
    private static boolean comprobarTiempo(String tiempo) {
        String parts[] = tiempo.split(":");
        try {
            int mm = Integer.parseInt(parts[0]);
            int ss = Integer.parseInt(parts[1]);
            int ms = Integer.parseInt(parts[2]);

            if (mm < 0 || ss < 0 || ms < 0) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean validarImporte(String[] parts) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            String tiempo = parts[4];
            String scramble = parts[1];
            Date fecha = formato.parse(parts[2] + " " + parts[3]);
            if (!comprobarScramble(scramble)) {
                return false;
            }
            if (!comprobarTiempo(tiempo)) {
                return false;
            }
            if (!(parts[5].equals("0") || parts[5].equals("1"))) {
                return false;
            }
            if (!(parts[6].equals("0") || parts[6].equals("1"))) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
