/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.Principal;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import model.Solve;
import utils.FicheroUtil;

/**
 *
 * @author Dani
 */
public class SolveDao {
    
    public static ListModel cargarSolves() {
        DefaultListModel model = new DefaultListModel();

        Principal.solves.stream().forEach((s) -> {
            if (s.getNum() < 10) {
                model.addElement("0" + s.getNum() + "     " + s.getTiempo());
            } else {
                if (s.getNum() < 100) {
                    model.addElement(s.getNum() + "     " + s.getTiempo());
                } else {
                    if (s.getNum() < 1000) {
                        model.addElement(s.getNum() + "   " + s.getTiempo());
                    } else {
                        model.addElement(s.getNum() + "  " + s.getTiempo());
                    }
                }
            }
        });

        return model;
    }
    
    public static void eliminar(Solve solve) {
        Principal.solves.remove(solve.getNum() - 1);
        Principal.Listado.setModel(SolveDao.cargarSolves());

        FicheroUtil.rehacerFichero();
        Principal.t_tiempo.setText("00:00:00");
    }
}
