/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Dani
 */
public class AVG {
    private String tiempo;
    private ArrayList<Solve> solves;
    private Boolean dnf;

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public ArrayList<Solve> getSolves() {
        return solves;
    }

    public void setSolves(ArrayList<Solve> solves) {
        this.solves = solves;
    }
    
    public Boolean getDnf() {
        return dnf;
    }

    public void setDnf(Boolean dnf) {
        this.dnf = dnf;
    }

    public AVG(String tiempo, ArrayList<Solve> solves, Boolean dnf) {
        this.tiempo = tiempo;
        this.solves = solves;
        this.dnf = dnf;
    }
    
}
