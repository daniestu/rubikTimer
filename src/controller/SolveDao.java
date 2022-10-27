/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.Principal;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import model.Solve;
import utilities.FicheroUtil;

/**
 *
 * @author Dani
 */
public class SolveDao {
    
    public static ListModel cargarSolves(ArrayList <Solve> solves, int accion) {
        DefaultListModel model = new DefaultListModel();

        solves.stream().forEach((Solve s) -> {
            if (accion == 0) {
                if (s.getNum() < 10) {
                    if (s.getDnf()) {
                        model.addElement("0" + s.getNum() + "     DNF");
                    }else{
                        model.addElement("0" + s.getNum() + "     " + s.getTiempo());
                    }
                } else {
                    if (s.getNum() < 100) {
                        if (s.getDnf()) {
                            model.addElement(s.getNum() + "     DNF");
                        }else{
                            model.addElement(s.getNum() + "     " + s.getTiempo());
                        }
                    } else {
                        if (s.getNum() < 1000) {
                            if (s.getDnf()) {
                                model.addElement(s.getNum() + "   DNF");
                            }else{
                                model.addElement(s.getNum() + "   " + s.getTiempo());
                            }
                        } else {
                            if (s.getNum() < 10000) {
                                if (s.getDnf()) {
                                    model.addElement(s.getNum() + "  DNF");
                                }else{
                                    model.addElement(s.getNum() + "  " + s.getTiempo());
                                }
                            } else{
                                if (s.getDnf()) {
                                    model.addElement(s.getNum() + " DNF");
                                }else{
                                    model.addElement(s.getNum() + " " + s.getTiempo());
                                }
                            }
                        }
                    }
                }
            }else{
                if (s.getDnf()) {
                    model.addElement("DNF");
                }else{
                    model.addElement(s.getTiempo());
                }

            }
            
        });

        return model;
    }
    
    public static void eliminar(Solve solve) {
        Principal.solves.remove(solve.getNum() - 1);
        Principal.Listado.setModel(SolveDao.cargarSolves(Principal.solves, 0));

        FicheroUtil.rehacerFichero();
        Principal.t_tiempo.setText("00:00:00");
    }
}
