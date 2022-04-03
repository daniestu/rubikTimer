/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Dani
 */
public class Solve implements Comparable<Solve>{
    private Integer num;
    private String scramble;
    private Date fecha;
    private String tiempo;

    public Integer getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Solve(Integer num, String scramble, Date fecha, String tiempo) {
        this.num = num;
        this.scramble = scramble;
        this.fecha = fecha;
        this.tiempo = tiempo;
    }

    @Override
    public int compareTo(Solve o) {
        return this.fecha.compareTo(o.fecha);
    }

}
