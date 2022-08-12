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
    private AVG avg;
    private double desviacion;
    private AVG bestAo5;
    private AVG bestAo12;
    private AVG bestAo100;
    private AVG currentAo5;
    private AVG currentAo12;
    private AVG currentAo100;
    private int totalSolves;

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

    public AVG getAvg() {
        return avg;
    }

    public void setAvg(AVG avg) {
        this.avg = avg;
    }
    
    public double getDesviacion() {
        return desviacion;
    }

    public void setDesviacion(double desviacion) {
        this.desviacion = desviacion;
    }

    public AVG getBestAo5() {
        return bestAo5;
    }

    public void setBestAo5(AVG bestAo5) {
        this.bestAo5 = bestAo5;
    }

    public AVG getBestAo12() {
        return bestAo12;
    }

    public void setBestAo12(AVG bestAo12) {
        this.bestAo12 = bestAo12;
    }

    public AVG getBestAo100() {
        return bestAo100;
    }

    public void setBestAo100(AVG bestAo100) {
        this.bestAo100 = bestAo100;
    }

    public AVG getCurrentAo5() {
        return currentAo5;
    }

    public void setCurrentAo5(AVG currentAo5) {
        this.currentAo5 = currentAo5;
    }

    public AVG getCurrentAo12() {
        return currentAo12;
    }

    public void setCurrentAo12(AVG currentAo12) {
        this.currentAo12 = currentAo12;
    }

    public AVG getCurrentAo100() {
        return currentAo100;
    }

    public void setCurrentAo100(AVG currentAo100) {
        this.currentAo100 = currentAo100;
    }

    public int getTotalSolves() {
        return totalSolves;
    }

    public void setTotalSolves(int totalSolves) {
        this.totalSolves = totalSolves;
    }

    public Sesion(String nombre, Solve mejor, Solve peor, AVG avg, double desviacion, AVG bestAo5, AVG bestAo12, AVG bestAo100, AVG currentAo5, AVG currentAo12, AVG currentAo100, int totalSolves) {
        this.nombre = nombre;
        this.mejor = mejor;
        this.peor = peor;
        this.avg = avg;
        this.desviacion = desviacion;
        this.bestAo5 = bestAo5;
        this.bestAo12 = bestAo12;
        this.bestAo100 = bestAo100;
        this.currentAo5 = currentAo5;
        this.currentAo12 = currentAo12;
        this.currentAo100 = currentAo100;
        this.totalSolves = totalSolves;
    }

    
}
