/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dani
 */
public class Sesion {
    private String nombre;
    private Solve mejor;
    private Solve peor;
    private String avg;
    private String bestAo5;
    private String bestAo12;
    private String bestAo100;
    private String currentAo5;
    private String currentAo12;
    private String currentAo100;
    int totalSolves;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Solve getMejor() {
        return mejor;
    }

    public void setMejor(Solve mejor) {
        this.mejor = mejor;
    }

    public Solve getPeor() {
        return peor;
    }

    public void setPeor(Solve peor) {
        this.peor = peor;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getBestAo5() {
        return bestAo5;
    }

    public void setBestAo5(String bestAo5) {
        this.bestAo5 = bestAo5;
    }

    public String getBestAo12() {
        return bestAo12;
    }

    public void setBestAo12(String bestAo12) {
        this.bestAo12 = bestAo12;
    }

    public String getBestAo100() {
        return bestAo100;
    }

    public void setBestAo100(String bestAo100) {
        this.bestAo100 = bestAo100;
    }

    public String getCurrentAo5() {
        return currentAo5;
    }

    public void setCurrentAo5(String currentA05) {
        this.currentAo5 = currentA05;
    }

    public String getCurrentAo12() {
        return currentAo12;
    }

    public void setCurrentAo12(String currentAo12) {
        this.currentAo12 = currentAo12;
    }

    public String getCurrentAo100() {
        return currentAo100;
    }

    public void setCurrentAo100(String currentA0100) {
        this.currentAo100 = currentA0100;
    }

    public int getTotalSolves() {
        return totalSolves;
    }

    public void setTotalSolves(int totalSolves) {
        this.totalSolves = totalSolves;
    }

    public Sesion(String nombre, Solve mejor, Solve peor, String avg, String bestAo5, String bestAo12, String bestAo100, String currentA05, String currentAo12, String currentA0100, int totalSolves) {
        this.nombre = nombre;
        this.mejor = mejor;
        this.peor = peor;
        this.avg = avg;
        this.bestAo5 = bestAo5;
        this.bestAo12 = bestAo12;
        this.bestAo100 = bestAo100;
        this.currentAo5 = currentA05;
        this.currentAo12 = currentAo12;
        this.currentAo100 = currentA0100;
        this.totalSolves = totalSolves;
    }
}
