/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.Principal;
import client.ScramblePreview;
import java.awt.Color;
import model.Cubo;

/**
 *
 * @author Dani
 */
public class CuboDao {
    
    public static Cubo generarCubo (String scramble){
        Cubo c = new Cubo  (Color.white, Color.white, Color.white, Color.white, Color.white, Color.white, Color.white, Color.white, Color.white, 
                            Color.red, Color.red, Color.red, Color.red, Color.red, Color.red, Color.red, Color.red, Color.red, 
                            new java.awt.Color(255, 131, 0), new java.awt.Color(255, 131, 0), new java.awt.Color(255, 131, 0), new java.awt.Color(255, 131, 0), new java.awt.Color(255, 131, 0), new java.awt.Color(255, 131, 0), new java.awt.Color(255, 131, 0), new java.awt.Color(255, 131, 0), new java.awt.Color(255, 131, 0), 
                            Color.yellow, Color.yellow, Color.yellow, Color.yellow, Color.yellow, Color.yellow, Color.yellow, Color.yellow, Color.yellow, 
                            Color.green, Color.green, Color.green, Color.green, Color.green, Color.green, Color.green, Color.green, Color.green, 
                            Color.blue, Color.blue, Color.blue, Color.blue, Color.blue, Color.blue, Color.blue, Color.blue, Color.blue);
        
        
        String moves [] = scramble.split(" ");
        
        for (String i : moves) {
            c = generarMovimiento(c, i);
        }
        
        return c;
    }
    public static void establecerCubo (Cubo c, int opcion){
        if (opcion == 0) {
            Principal.u1.setBackground(c.getU1());
            Principal.u2.setBackground(c.getU2());
            Principal.u3.setBackground(c.getU3());
            Principal.u4.setBackground(c.getU4());
            Principal.u5.setBackground(c.getU5());
            Principal.u6.setBackground(c.getU6());
            Principal.u7.setBackground(c.getU7());
            Principal.u8.setBackground(c.getU8());
            Principal.u9.setBackground(c.getU9());

            Principal.f1.setBackground(c.getF1());
            Principal.f2.setBackground(c.getF2());
            Principal.f3.setBackground(c.getF3());
            Principal.f4.setBackground(c.getF4());
            Principal.f5.setBackground(c.getF5());
            Principal.f6.setBackground(c.getF6());
            Principal.f7.setBackground(c.getF7());
            Principal.f8.setBackground(c.getF8());
            Principal.f9.setBackground(c.getF9());

            Principal.d1.setBackground(c.getD1());
            Principal.d2.setBackground(c.getD2());
            Principal.d3.setBackground(c.getD3());
            Principal.d4.setBackground(c.getD4());
            Principal.d5.setBackground(c.getD5());
            Principal.d6.setBackground(c.getD6());
            Principal.d7.setBackground(c.getD7());
            Principal.d8.setBackground(c.getD8());
            Principal.d9.setBackground(c.getD9());

            Principal.r1.setBackground(c.getR1());
            Principal.r2.setBackground(c.getR2());
            Principal.r3.setBackground(c.getR3());
            Principal.r4.setBackground(c.getR4());
            Principal.r5.setBackground(c.getR5());
            Principal.r6.setBackground(c.getR6());
            Principal.r7.setBackground(c.getR7());
            Principal.r8.setBackground(c.getR8());
            Principal.r9.setBackground(c.getR9());

            Principal.l1.setBackground(c.getL1());
            Principal.l2.setBackground(c.getL2());
            Principal.l3.setBackground(c.getL3());
            Principal.l4.setBackground(c.getL4());
            Principal.l5.setBackground(c.getL5());
            Principal.l6.setBackground(c.getL6());
            Principal.l7.setBackground(c.getL7());
            Principal.l8.setBackground(c.getL8());
            Principal.l9.setBackground(c.getL9());

            Principal.b1.setBackground(c.getB1());
            Principal.b2.setBackground(c.getB2());
            Principal.b3.setBackground(c.getB3());
            Principal.b4.setBackground(c.getB4());
            Principal.b5.setBackground(c.getB5());
            Principal.b6.setBackground(c.getB6());
            Principal.b7.setBackground(c.getB7());
            Principal.b8.setBackground(c.getB8());
            Principal.b9.setBackground(c.getB9());
        }else if (opcion == 1) {
            ScramblePreview.u1.setBackground(c.getU1());
            ScramblePreview.u2.setBackground(c.getU2());
            ScramblePreview.u3.setBackground(c.getU3());
            ScramblePreview.u4.setBackground(c.getU4());
            ScramblePreview.u5.setBackground(c.getU5());
            ScramblePreview.u6.setBackground(c.getU6());
            ScramblePreview.u7.setBackground(c.getU7());
            ScramblePreview.u8.setBackground(c.getU8());
            ScramblePreview.u9.setBackground(c.getU9());

            ScramblePreview.f1.setBackground(c.getF1());
            ScramblePreview.f2.setBackground(c.getF2());
            ScramblePreview.f3.setBackground(c.getF3());
            ScramblePreview.f4.setBackground(c.getF4());
            ScramblePreview.f5.setBackground(c.getF5());
            ScramblePreview.f6.setBackground(c.getF6());
            ScramblePreview.f7.setBackground(c.getF7());
            ScramblePreview.f8.setBackground(c.getF8());
            ScramblePreview.f9.setBackground(c.getF9());

            ScramblePreview.d1.setBackground(c.getD1());
            ScramblePreview.d2.setBackground(c.getD2());
            ScramblePreview.d3.setBackground(c.getD3());
            ScramblePreview.d4.setBackground(c.getD4());
            ScramblePreview.d5.setBackground(c.getD5());
            ScramblePreview.d6.setBackground(c.getD6());
            ScramblePreview.d7.setBackground(c.getD7());
            ScramblePreview.d8.setBackground(c.getD8());
            ScramblePreview.d9.setBackground(c.getD9());

            ScramblePreview.r1.setBackground(c.getR1());
            ScramblePreview.r2.setBackground(c.getR2());
            ScramblePreview.r3.setBackground(c.getR3());
            ScramblePreview.r4.setBackground(c.getR4());
            ScramblePreview.r5.setBackground(c.getR5());
            ScramblePreview.r6.setBackground(c.getR6());
            ScramblePreview.r7.setBackground(c.getR7());
            ScramblePreview.r8.setBackground(c.getR8());
            ScramblePreview.r9.setBackground(c.getR9());

            ScramblePreview.l1.setBackground(c.getL1());
            ScramblePreview.l2.setBackground(c.getL2());
            ScramblePreview.l3.setBackground(c.getL3());
            ScramblePreview.l4.setBackground(c.getL4());
            ScramblePreview.l5.setBackground(c.getL5());
            ScramblePreview.l6.setBackground(c.getL6());
            ScramblePreview.l7.setBackground(c.getL7());
            ScramblePreview.l8.setBackground(c.getL8());
            ScramblePreview.l9.setBackground(c.getL9());

            ScramblePreview.b1.setBackground(c.getB1());
            ScramblePreview.b2.setBackground(c.getB2());
            ScramblePreview.b3.setBackground(c.getB3());
            ScramblePreview.b4.setBackground(c.getB4());
            ScramblePreview.b5.setBackground(c.getB5());
            ScramblePreview.b6.setBackground(c.getB6());
            ScramblePreview.b7.setBackground(c.getB7());
            ScramblePreview.b8.setBackground(c.getB8());
            ScramblePreview.b9.setBackground(c.getB9());
        }
        
    }
    
    public static Cubo generarMovimiento(Cubo c_ant, String mov){
        Color colores[] = new Color[21];
        Cubo c_new = c_ant;
        
        
        if (mov.equals("R")) {
            colores[0] = c_ant.getF9();
            colores[1] = c_ant.getF6();
            colores[2] = c_ant.getF3();
            colores[3] = c_ant.getU9();
            colores[4] = c_ant.getU6();
            colores[5] = c_ant.getU3();
            colores[6] = c_ant.getB1();
            colores[7] = c_ant.getB4();
            colores[8] = c_ant.getB7();
            colores[9] = c_ant.getD9();
            colores[10] = c_ant.getD6();
            colores[11] = c_ant.getD3();
            colores[12] = c_ant.getR7();
            colores[13] = c_ant.getR4();
            colores[14] = c_ant.getR1();
            colores[15] = c_ant.getR8();
            colores[16] = c_ant.getR5();
            colores[17] = c_ant.getR2();
            colores[18] = c_ant.getR9();
            colores[19] = c_ant.getR6();
            colores[20] = c_ant.getR3();

            c_new.setU9(colores[0]);
            c_new.setU6(colores[1]);
            c_new.setU3(colores[2]);
            c_new.setB1(colores[3]);
            c_new.setB4(colores[4]);
            c_new.setB7(colores[5]);
            c_new.setD9(colores[6]);
            c_new.setD6(colores[7]);
            c_new.setD3(colores[8]);
            c_new.setF9(colores[9]);
            c_new.setF6(colores[10]);
            c_new.setF3(colores[11]);
            c_new.setR1(colores[12]);
            c_new.setR2(colores[13]);
            c_new.setR3(colores[14]);
            c_new.setR4(colores[15]);
            c_new.setR5(colores[16]);
            c_new.setR6(colores[17]);
            c_new.setR7(colores[18]);
            c_new.setR8(colores[19]);
            c_new.setR9(colores[20]);
        }
        if (mov.equals("R'")) {
            colores[0] = c_ant.getB1();
            colores[1] = c_ant.getB4();
            colores[2] = c_ant.getB7();
            colores[3] = c_ant.getD9();
            colores[4] = c_ant.getD6();
            colores[5] = c_ant.getD3();
            colores[6] = c_ant.getF9();
            colores[7] = c_ant.getF6();
            colores[8] = c_ant.getF3();
            colores[9] = c_ant.getU9();
            colores[10] = c_ant.getU6();
            colores[11] = c_ant.getU3();
            colores[12] = c_ant.getR3();
            colores[13] = c_ant.getR6();
            colores[14] = c_ant.getR9();
            colores[15] = c_ant.getR2();
            colores[16] = c_ant.getR5();
            colores[17] = c_ant.getR8();
            colores[18] = c_ant.getR1();
            colores[19] = c_ant.getR4();
            colores[20] = c_ant.getR7();

            c_new.setU9(colores[0]);
            c_new.setU6(colores[1]);
            c_new.setU3(colores[2]);
            c_new.setB1(colores[3]);
            c_new.setB4(colores[4]);
            c_new.setB7(colores[5]);
            c_new.setD9(colores[6]);
            c_new.setD6(colores[7]);
            c_new.setD3(colores[8]);
            c_new.setF9(colores[9]);
            c_new.setF6(colores[10]);
            c_new.setF3(colores[11]);
            c_new.setR1(colores[12]);
            c_new.setR2(colores[13]);
            c_new.setR3(colores[14]);
            c_new.setR4(colores[15]);
            c_new.setR5(colores[16]);
            c_new.setR6(colores[17]);
            c_new.setR7(colores[18]);
            c_new.setR8(colores[19]);
            c_new.setR9(colores[20]);
        }
        if (mov.equals("R2")) {
            colores[0] = c_ant.getD9();
            colores[1] = c_ant.getD6();
            colores[2] = c_ant.getD3();
            colores[3] = c_ant.getF9();
            colores[4] = c_ant.getF6();
            colores[5] = c_ant.getF3();
            colores[6] = c_ant.getU9();
            colores[7] = c_ant.getU6();
            colores[8] = c_ant.getU3();
            colores[9] = c_ant.getB1();
            colores[10] = c_ant.getB4();
            colores[11] = c_ant.getB7();
            colores[12] = c_ant.getR9();
            colores[13] = c_ant.getR8();
            colores[14] = c_ant.getR7();
            colores[15] = c_ant.getR6();
            colores[16] = c_ant.getR5();
            colores[17] = c_ant.getR4();
            colores[18] = c_ant.getR3();
            colores[19] = c_ant.getR2();
            colores[20] = c_ant.getR1();

            c_new.setU9(colores[0]);
            c_new.setU6(colores[1]);
            c_new.setU3(colores[2]);
            c_new.setB1(colores[3]);
            c_new.setB4(colores[4]);
            c_new.setB7(colores[5]);
            c_new.setD9(colores[6]);
            c_new.setD6(colores[7]);
            c_new.setD3(colores[8]);
            c_new.setF9(colores[9]);
            c_new.setF6(colores[10]);
            c_new.setF3(colores[11]);
            c_new.setR1(colores[12]);
            c_new.setR2(colores[13]);
            c_new.setR3(colores[14]);
            c_new.setR4(colores[15]);
            c_new.setR5(colores[16]);
            c_new.setR6(colores[17]);
            c_new.setR7(colores[18]);
            c_new.setR8(colores[19]);
            c_new.setR9(colores[20]);
        }
        if (mov.equals("L")) {
            colores[0] = c_ant.getB9();
            colores[1] = c_ant.getB6();
            colores[2] = c_ant.getB3();
            colores[3] = c_ant.getD7();
            colores[4] = c_ant.getD4();
            colores[5] = c_ant.getD1();
            colores[6] = c_ant.getF1();
            colores[7] = c_ant.getF4();
            colores[8] = c_ant.getF7();
            colores[9] = c_ant.getU1();
            colores[10] = c_ant.getU4();
            colores[11] = c_ant.getU7();
            colores[12] = c_ant.getL7();
            colores[13] = c_ant.getL4();
            colores[14] = c_ant.getL1();
            colores[15] = c_ant.getL8();
            colores[16] = c_ant.getL5();
            colores[17] = c_ant.getL2();
            colores[18] = c_ant.getL9();
            colores[19] = c_ant.getL6();
            colores[20] = c_ant.getL3();

            c_new.setU1(colores[0]);
            c_new.setU4(colores[1]);
            c_new.setU7(colores[2]);
            c_new.setB3(colores[3]);
            c_new.setB6(colores[4]);
            c_new.setB9(colores[5]);
            c_new.setD1(colores[6]);
            c_new.setD4(colores[7]);
            c_new.setD7(colores[8]);
            c_new.setF1(colores[9]);
            c_new.setF4(colores[10]);
            c_new.setF7(colores[11]);
            c_new.setL1(colores[12]);
            c_new.setL2(colores[13]);
            c_new.setL3(colores[14]);
            c_new.setL4(colores[15]);
            c_new.setL5(colores[16]);
            c_new.setL6(colores[17]);
            c_new.setL7(colores[18]);
            c_new.setL8(colores[19]);
            c_new.setL9(colores[20]);
        }
        if (mov.equals("L'")) {
            colores[0] = c_ant.getF1();
            colores[1] = c_ant.getF4();
            colores[2] = c_ant.getF7();
            colores[3] = c_ant.getU7();
            colores[4] = c_ant.getU4();
            colores[5] = c_ant.getU1();
            colores[6] = c_ant.getB9();
            colores[7] = c_ant.getB6();
            colores[8] = c_ant.getB3();
            colores[9] = c_ant.getD1();
            colores[10] = c_ant.getD4();
            colores[11] = c_ant.getD7();
            
            colores[12] = c_ant.getL3();
            colores[13] = c_ant.getL6();
            colores[14] = c_ant.getL9();
            colores[15] = c_ant.getL2();
            colores[16] = c_ant.getL5();
            colores[17] = c_ant.getL8();
            colores[18] = c_ant.getL1();
            colores[19] = c_ant.getL4();
            colores[20] = c_ant.getL7();

            c_new.setU1(colores[0]);
            c_new.setU4(colores[1]);
            c_new.setU7(colores[2]);
            c_new.setB3(colores[3]);
            c_new.setB6(colores[4]);
            c_new.setB9(colores[5]);
            c_new.setD1(colores[6]);
            c_new.setD4(colores[7]);
            c_new.setD7(colores[8]);
            c_new.setF1(colores[9]);
            c_new.setF4(colores[10]);
            c_new.setF7(colores[11]);
            c_new.setL1(colores[12]);
            c_new.setL2(colores[13]);
            c_new.setL3(colores[14]);
            c_new.setL4(colores[15]);
            c_new.setL5(colores[16]);
            c_new.setL6(colores[17]);
            c_new.setL7(colores[18]);
            c_new.setL8(colores[19]);
            c_new.setL9(colores[20]);
        }
        if (mov.equals("L2")) {
            colores[0] = c_ant.getD1();
            colores[1] = c_ant.getD4();
            colores[2] = c_ant.getD7();
            colores[3] = c_ant.getF7();
            colores[4] = c_ant.getF4();
            colores[5] = c_ant.getF1();
            colores[6] = c_ant.getU1();
            colores[7] = c_ant.getU4();
            colores[8] = c_ant.getU7();
            colores[9] = c_ant.getB9();
            colores[10] = c_ant.getB6();
            colores[11] = c_ant.getB3();
            colores[12] = c_ant.getL9();
            colores[13] = c_ant.getL8();
            colores[14] = c_ant.getL7();
            colores[15] = c_ant.getL6();
            colores[16] = c_ant.getL5();
            colores[17] = c_ant.getL4();
            colores[18] = c_ant.getL3();
            colores[19] = c_ant.getL2();
            colores[20] = c_ant.getL1();

            c_new.setU1(colores[0]);
            c_new.setU4(colores[1]);
            c_new.setU7(colores[2]);
            c_new.setB3(colores[3]);
            c_new.setB6(colores[4]);
            c_new.setB9(colores[5]);
            c_new.setD1(colores[6]);
            c_new.setD4(colores[7]);
            c_new.setD7(colores[8]);
            c_new.setF1(colores[9]);
            c_new.setF4(colores[10]);
            c_new.setF7(colores[11]);
            c_new.setL1(colores[12]);
            c_new.setL2(colores[13]);
            c_new.setL3(colores[14]);
            c_new.setL4(colores[15]);
            c_new.setL5(colores[16]);
            c_new.setL6(colores[17]);
            c_new.setL7(colores[18]);
            c_new.setL8(colores[19]);
            c_new.setL9(colores[20]);
        }
        if (mov.equals("U")) {
            colores[0] = c_ant.getR1();
            colores[1] = c_ant.getR2();
            colores[2] = c_ant.getR3();
            colores[3] = c_ant.getB1();
            colores[4] = c_ant.getB2();
            colores[5] = c_ant.getB3();
            colores[6] = c_ant.getL1();
            colores[7] = c_ant.getL2();
            colores[8] = c_ant.getL3();
            colores[9] = c_ant.getF1();
            colores[10] = c_ant.getF2();
            colores[11] = c_ant.getF3();
            colores[12] = c_ant.getU7();
            colores[13] = c_ant.getU4();
            colores[14] = c_ant.getU1();
            colores[15] = c_ant.getU8();
            colores[16] = c_ant.getU5();
            colores[17] = c_ant.getU2();
            colores[18] = c_ant.getU9();
            colores[19] = c_ant.getU6();
            colores[20] = c_ant.getU3();

            c_new.setF1(colores[0]);
            c_new.setF2(colores[1]);
            c_new.setF3(colores[2]);
            c_new.setR1(colores[3]);
            c_new.setR2(colores[4]);
            c_new.setR3(colores[5]);
            c_new.setB1(colores[6]);
            c_new.setB2(colores[7]);
            c_new.setB3(colores[8]);
            c_new.setL1(colores[9]);
            c_new.setL2(colores[10]);
            c_new.setL3(colores[11]);
            c_new.setU1(colores[12]);
            c_new.setU2(colores[13]);
            c_new.setU3(colores[14]);
            c_new.setU4(colores[15]);
            c_new.setU5(colores[16]);
            c_new.setU6(colores[17]);
            c_new.setU7(colores[18]);
            c_new.setU8(colores[19]);
            c_new.setU9(colores[20]);
        }
        if (mov.equals("U'")) {
            colores[0] = c_ant.getL1();
            colores[1] = c_ant.getL2();
            colores[2] = c_ant.getL3();
            colores[3] = c_ant.getF1();
            colores[4] = c_ant.getF2();
            colores[5] = c_ant.getF3();
            colores[6] = c_ant.getR1();
            colores[7] = c_ant.getR2();
            colores[8] = c_ant.getR3();
            colores[9] = c_ant.getB1();
            colores[10] = c_ant.getB2();
            colores[11] = c_ant.getB3();
            colores[12] = c_ant.getU3();
            colores[13] = c_ant.getU6();
            colores[14] = c_ant.getU9();
            colores[15] = c_ant.getU2();
            colores[16] = c_ant.getU5();
            colores[17] = c_ant.getU8();
            colores[18] = c_ant.getU1();
            colores[19] = c_ant.getU4();
            colores[20] = c_ant.getU7();

            c_new.setF1(colores[0]);
            c_new.setF2(colores[1]);
            c_new.setF3(colores[2]);
            c_new.setR1(colores[3]);
            c_new.setR2(colores[4]);
            c_new.setR3(colores[5]);
            c_new.setB1(colores[6]);
            c_new.setB2(colores[7]);
            c_new.setB3(colores[8]);
            c_new.setL1(colores[9]);
            c_new.setL2(colores[10]);
            c_new.setL3(colores[11]);
            c_new.setU1(colores[12]);
            c_new.setU2(colores[13]);
            c_new.setU3(colores[14]);
            c_new.setU4(colores[15]);
            c_new.setU5(colores[16]);
            c_new.setU6(colores[17]);
            c_new.setU7(colores[18]);
            c_new.setU8(colores[19]);
            c_new.setU9(colores[20]);
        }
        if (mov.equals("U2")) {
            colores[0] = c_ant.getB1();
            colores[1] = c_ant.getB2();
            colores[2] = c_ant.getB3();
            colores[3] = c_ant.getL1();
            colores[4] = c_ant.getL2();
            colores[5] = c_ant.getL3();
            colores[6] = c_ant.getF1();
            colores[7] = c_ant.getF2();
            colores[8] = c_ant.getF3();
            colores[9] = c_ant.getR1();
            colores[10] = c_ant.getR2();
            colores[11] = c_ant.getR3();
            colores[12] = c_ant.getU9();
            colores[13] = c_ant.getU8();
            colores[14] = c_ant.getU7();
            colores[15] = c_ant.getU6();
            colores[16] = c_ant.getU5();
            colores[17] = c_ant.getU4();
            colores[18] = c_ant.getU3();
            colores[19] = c_ant.getU2();
            colores[20] = c_ant.getU1 ();

            c_new.setF1(colores[0]);
            c_new.setF2(colores[1]);
            c_new.setF3(colores[2]);
            c_new.setR1(colores[3]);
            c_new.setR2(colores[4]);
            c_new.setR3(colores[5]);
            c_new.setB1(colores[6]);
            c_new.setB2(colores[7]);
            c_new.setB3(colores[8]);
            c_new.setL1(colores[9]);
            c_new.setL2(colores[10]);
            c_new.setL3(colores[11]);
            c_new.setU1(colores[12]);
            c_new.setU2(colores[13]);
            c_new.setU3(colores[14]);
            c_new.setU4(colores[15]);
            c_new.setU5(colores[16]);
            c_new.setU6(colores[17]);
            c_new.setU7(colores[18]);
            c_new.setU8(colores[19]);
            c_new.setU9(colores[20]);
        }
        if (mov.equals("D")) {
            colores[0] = c_ant.getL7();
            colores[1] = c_ant.getL8();
            colores[2] = c_ant.getL9();
            colores[3] = c_ant.getF7();
            colores[4] = c_ant.getF8();
            colores[5] = c_ant.getF9();
            colores[6] = c_ant.getR7();
            colores[7] = c_ant.getR8();
            colores[8] = c_ant.getR9();
            colores[9] = c_ant.getB7();
            colores[10] = c_ant.getB8();
            colores[11] = c_ant.getB9();
            colores[12] = c_ant.getD7();
            colores[13] = c_ant.getD4();
            colores[14] = c_ant.getD1();
            colores[15] = c_ant.getD8();
            colores[16] = c_ant.getD5();
            colores[17] = c_ant.getD2();
            colores[18] = c_ant.getD9();
            colores[19] = c_ant.getD6();
            colores[20] = c_ant.getD3();


            c_new.setF7(colores[0]);
            c_new.setF8(colores[1]);
            c_new.setF9(colores[2]);
            c_new.setR7(colores[3]);
            c_new.setR8(colores[4]);
            c_new.setR9(colores[5]);
            c_new.setB7(colores[6]);
            c_new.setB8(colores[7]);
            c_new.setB9(colores[8]);
            c_new.setL7(colores[9]);
            c_new.setL8(colores[10]);
            c_new.setL9 (colores[11]);
            c_new.setD1(colores[12]);
            c_new.setD2(colores[13]);
            c_new.setD3(colores[14]);
            c_new.setD4(colores[15]);
            c_new.setD5(colores[16]);
            c_new.setD6(colores[17]);
            c_new.setD7(colores[18]);
            c_new.setD8(colores[19]);
            c_new.setD9(colores[20]);
        }
        if (mov.equals("D'")) {
            colores[0] = c_ant.getR7();
            colores[1] = c_ant.getR8();
            colores[2] = c_ant.getR9();
            colores[3] = c_ant.getB7();
            colores[4] = c_ant.getB8();
            colores[5] = c_ant.getB9();
            colores[6] = c_ant.getL7();
            colores[7] = c_ant.getL8();
            colores[8] = c_ant.getL9();
            colores[9] = c_ant.getF7();
            colores[10] = c_ant.getF8();
            colores[11] = c_ant.getF9();
            colores[12] = c_ant.getD3();
            colores[13] = c_ant.getD6();
            colores[14] = c_ant.getD9();
            colores[15] = c_ant.getD2();
            colores[16] = c_ant.getD5();
            colores[17] = c_ant.getD8();
            colores[18] = c_ant.getD1();
            colores[19] = c_ant.getD4();
            colores[20] = c_ant.getD7();

            c_new.setF7(colores[0]);
            c_new.setF8(colores[1]);
            c_new.setF9(colores[2]);
            c_new.setR7(colores[3]);
            c_new.setR8(colores[4]);
            c_new.setR9(colores[5]);
            c_new.setB7(colores[6]);
            c_new.setB8(colores[7]);
            c_new.setB9(colores[8]);
            c_new.setL7(colores[9]);
            c_new.setL8(colores[10]);
            c_new.setL9 (colores[11]);
            c_new.setD1(colores[12]);
            c_new.setD2(colores[13]);
            c_new.setD3(colores[14]);
            c_new.setD4(colores[15]);
            c_new.setD5(colores[16]);
            c_new.setD6(colores[17]);
            c_new.setD7(colores[18]);
            c_new.setD8(colores[19]);
            c_new.setD9(colores[20]);
        }
        if (mov.equals("D2")) {
            colores[0] = c_ant.getB7();
            colores[1] = c_ant.getB8();
            colores[2] = c_ant.getB9();
            colores[3] = c_ant.getL7();
            colores[4] = c_ant.getL8();
            colores[5] = c_ant.getL9();
            colores[6] = c_ant.getF7();
            colores[7] = c_ant.getF8();
            colores[8] = c_ant.getF9();
            colores[9] = c_ant.getR7();
            colores[10] = c_ant.getR8();
            colores[11] = c_ant.getR9();
            colores[12] = c_ant.getD9();
            colores[13] = c_ant.getD8();
            colores[14] = c_ant.getD7();
            colores[15] = c_ant.getD6();
            colores[16] = c_ant.getD5();
            colores[17] = c_ant.getD4();
            colores[18] = c_ant.getD3();
            colores[19] = c_ant.getD2();
            colores[20] = c_ant.getD1();

            c_new.setF7(colores[0]);
            c_new.setF8(colores[1]);
            c_new.setF9(colores[2]);
            c_new.setR7(colores[3]);
            c_new.setR8(colores[4]);
            c_new.setR9(colores[5]);
            c_new.setB7(colores[6]);
            c_new.setB8(colores[7]);
            c_new.setB9(colores[8]);
            c_new.setL7(colores[9]);
            c_new.setL8(colores[10]);
            c_new.setL9 (colores[11]);
            c_new.setD1(colores[12]);
            c_new.setD2(colores[13]);
            c_new.setD3(colores[14]);
            c_new.setD4(colores[15]);
            c_new.setD5(colores[16]);
            c_new.setD6(colores[17]);
            c_new.setD7(colores[18]);
            c_new.setD8(colores[19]);
            c_new.setD9(colores[20]);
        }
        if (mov.equals("F")) {
            colores[0] = c_ant.getL9();
            colores[1] = c_ant.getL6();
            colores[2] = c_ant.getL3();
            colores[3] = c_ant.getU7();
            colores[4] = c_ant.getU8();
            colores[5] = c_ant.getU9();
            colores[6] = c_ant.getR7();
            colores[7] = c_ant.getR4();
            colores[8] = c_ant.getR1();
            colores[9] = c_ant.getD1();
            colores[10] = c_ant.getD2();
            colores[11] = c_ant.getD3();
            colores[12] = c_ant.getF7();
            colores[13] = c_ant.getF4();
            colores[14] = c_ant.getF1();
            colores[15] = c_ant.getF8();
            colores[16] = c_ant.getF5();
            colores[17] = c_ant.getF2();
            colores[18] = c_ant.getF9();
            colores[19] = c_ant.getF6();
            colores[20] = c_ant.getF3();


            c_new.setU7(colores[0]);
            c_new.setU8(colores[1]);
            c_new.setU9(colores[2]);
            c_new.setR1(colores[3]);
            c_new.setR4(colores[4]);
            c_new.setR7(colores[5]);
            c_new.setD1(colores[6]);
            c_new.setD2(colores[7]);
            c_new.setD3(colores[8]);
            c_new.setL3(colores[9]);
            c_new.setL6(colores[10]);
            c_new.setL9(colores[11]);
            c_new.setF1(colores[12]);
            c_new.setF2(colores[13]);
            c_new.setF3(colores[14]);
            c_new.setF4(colores[15]);
            c_new.setF5(colores[16]);
            c_new.setF6(colores[17]);
            c_new.setF7(colores[18]);
            c_new.setF8(colores[19]);
            c_new.setF9(colores[20]);
        }
        if (mov.equals("F'")) {
            colores[0] = c_ant.getR1();
            colores[1] = c_ant.getR4();
            colores[2] = c_ant.getR7();
            colores[3] = c_ant.getD3();
            colores[4] = c_ant.getD2();
            colores[5] = c_ant.getD1();
            colores[6] = c_ant.getL3();
            colores[7] = c_ant.getL6();
            colores[8] = c_ant.getL9();
            colores[9] = c_ant.getU9();
            colores[10] = c_ant.getU8();
            colores[11] = c_ant.getU7();
            colores[12] = c_ant.getF3();
            colores[13] = c_ant.getF6();
            colores[14] = c_ant.getF9();
            colores[15] = c_ant.getF2();
            colores[16] = c_ant.getF5();
            colores[17] = c_ant.getF8();
            colores[18] = c_ant.getF1();
            colores[19] = c_ant.getF4();
            colores[20] = c_ant.getF7();

            c_new.setU7(colores[0]);
            c_new.setU8(colores[1]);
            c_new.setU9(colores[2]);
            c_new.setR1(colores[3]);
            c_new.setR4(colores[4]);
            c_new.setR7(colores[5]);
            c_new.setD1(colores[6]);
            c_new.setD2(colores[7]);
            c_new.setD3(colores[8]);
            c_new.setL3(colores[9]);
            c_new.setL6(colores[10]);
            c_new.setL9(colores[11]);
            c_new.setF1(colores[12]);
            c_new.setF2(colores[13]);
            c_new.setF3(colores[14]);
            c_new.setF4(colores[15]);
            c_new.setF5(colores[16]);
            c_new.setF6(colores[17]);
            c_new.setF7(colores[18]);
            c_new.setF8(colores[19]);
            c_new.setF9(colores[20]);
        }
        if (mov.equals("F2")) {
            colores[0] = c_ant.getD3();
            colores[1] = c_ant.getD2();
            colores[2] = c_ant.getD1();
            colores[3] = c_ant.getL9();
            colores[4] = c_ant.getL6();
            colores[5] = c_ant.getL3();
            colores[6] = c_ant.getU9();
            colores[7] = c_ant.getU8();
            colores[8] = c_ant.getU7();
            colores[9] = c_ant.getR7();
            colores[10] = c_ant.getR4();
            colores[11] = c_ant.getR1();
            colores[12] = c_ant.getF9();
            colores[13] = c_ant.getF8();
            colores[14] = c_ant.getF7();
            colores[15] = c_ant.getF6();
            colores[16] = c_ant.getF5();
            colores[17] = c_ant.getF4();
            colores[18] = c_ant.getF3();
            colores[19] = c_ant.getF2();
            colores[20] = c_ant.getF1();

            c_new.setU7(colores[0]);
            c_new.setU8(colores[1]);
            c_new.setU9(colores[2]);
            c_new.setR1(colores[3]);
            c_new.setR4(colores[4]);
            c_new.setR7(colores[5]);
            c_new.setD1(colores[6]);
            c_new.setD2(colores[7]);
            c_new.setD3(colores[8]);
            c_new.setL3(colores[9]);
            c_new.setL6(colores[10]);
            c_new.setL9(colores[11]);
            c_new.setF1(colores[12]);
            c_new.setF2(colores[13]);
            c_new.setF3(colores[14]);
            c_new.setF4(colores[15]);
            c_new.setF5(colores[16]);
            c_new.setF6(colores[17]);
            c_new.setF7(colores[18]);
            c_new.setF8(colores[19]);
            c_new.setF9(colores[20]);
        }
        if (mov.equals("B")) {
            colores[0] = c_ant.getR3();
            colores[1] = c_ant.getR6();
            colores[2] = c_ant.getR9();
            colores[3] = c_ant.getD9();
            colores[4] = c_ant.getD8();
            colores[5] = c_ant.getD7();
            colores[6] = c_ant.getL1();
            colores[7] = c_ant.getL4();
            colores[8] = c_ant.getL7();
            colores[9] = c_ant.getU3();
            colores[10] = c_ant.getU2();
            colores[11] = c_ant.getU1();
            colores[12] = c_ant.getB7();
            colores[13] = c_ant.getB4();
            colores[14] = c_ant.getB1();
            colores[15] = c_ant.getB8();
            colores[16] = c_ant.getB5();
            colores[17] = c_ant.getB2();
            colores[18] = c_ant.getB9();
            colores[19] = c_ant.getB6();
            colores[20] = c_ant.getB3();

            c_new.setU1(colores[0]);
            c_new.setU2(colores[1]);
            c_new.setU3(colores[2]);
            c_new.setR3(colores[3]);
            c_new.setR6(colores[4]);
            c_new.setR9(colores[5]);
            c_new.setD7(colores[6]);
            c_new.setD8(colores[7]);
            c_new.setD9(colores[8]);
            c_new.setL1(colores[9]);
            c_new.setL4(colores[10]);
            c_new.setL7(colores[11]);
            c_new.setB1(colores[12]);
            c_new.setB2(colores[13]);
            c_new.setB3(colores[14]);
            c_new.setB4(colores[15]);
            c_new.setB5(colores[16]);
            c_new.setB6(colores[17]);
            c_new.setB7(colores[18]);
            c_new.setB8(colores[19]);
            c_new.setB9(colores[20]);
        }
        if (mov.equals("B'")) {
            colores[0] = c_ant.getL7();
            colores[1] = c_ant.getL4();
            colores[2] = c_ant.getL1();
            colores[3] = c_ant.getU1();
            colores[4] = c_ant.getU2();
            colores[5] = c_ant.getU3();
            colores[6] = c_ant.getR9();
            colores[7] = c_ant.getR6();
            colores[8] = c_ant.getR3();
            colores[9] = c_ant.getD7();
            colores[10] = c_ant.getD8();
            colores[11] = c_ant.getD9();
            
            colores[12] = c_ant.getB3();
            colores[13] = c_ant.getB6();
            colores[14] = c_ant.getB9();
            colores[15] = c_ant.getB2();
            colores[16] = c_ant.getB5();
            colores[17] = c_ant.getB8();
            colores[18] = c_ant.getB1();
            colores[19] = c_ant.getB4();
            colores[20] = c_ant.getB7();

            c_new.setU1(colores[0]);
            c_new.setU2(colores[1]);
            c_new.setU3(colores[2]);
            c_new.setR3(colores[3]);
            c_new.setR6(colores[4]);
            c_new.setR9(colores[5]);
            c_new.setD7(colores[6]);
            c_new.setD8(colores[7]);
            c_new.setD9(colores[8]);
            c_new.setL1(colores[9]);
            c_new.setL4(colores[10]);
            c_new.setL7(colores[11]);
            c_new.setB1(colores[12]);
            c_new.setB2(colores[13]);
            c_new.setB3(colores[14]);
            c_new.setB4(colores[15]);
            c_new.setB5(colores[16]);
            c_new.setB6(colores[17]);
            c_new.setB7(colores[18]);
            c_new.setB8(colores[19]);
            c_new.setB9(colores[20]);
        }
        if (mov.equals("B2")) {
            colores[0] = c_ant.getD9();
            colores[1] = c_ant.getD8();
            colores[2] = c_ant.getD7();
            colores[3] = c_ant.getL7();
            colores[4] = c_ant.getL4();
            colores[5] = c_ant.getL1();
            colores[6] = c_ant.getU3();
            colores[7] = c_ant.getU2();
            colores[8] = c_ant.getU1();
            colores[9] = c_ant.getR9();
            colores[10] = c_ant.getR6();
            colores[11] = c_ant.getR3();
            
            colores[12] = c_ant.getB9();
            colores[13] = c_ant.getB8();
            colores[14] = c_ant.getB7();
            colores[15] = c_ant.getB6();
            colores[16] = c_ant.getB5();
            colores[17] = c_ant.getB4();
            colores[18] = c_ant.getB3();
            colores[19] = c_ant.getB2();
            colores[20] = c_ant.getB1();

            c_new.setU1(colores[0]);
            c_new.setU2(colores[1]);
            c_new.setU3(colores[2]);
            c_new.setR3(colores[3]);
            c_new.setR6(colores[4]);
            c_new.setR9(colores[5]);
            c_new.setD7(colores[6]);
            c_new.setD8(colores[7]);
            c_new.setD9(colores[8]);
            c_new.setL1(colores[9]);
            c_new.setL4(colores[10]);
            c_new.setL7(colores[11]);
            
            c_new.setB1(colores[12]);
            c_new.setB2(colores[13]);
            c_new.setB3(colores[14]);
            c_new.setB4(colores[15]);
            c_new.setB5(colores[16]);
            c_new.setB6(colores[17]);
            c_new.setB7(colores[18]);
            c_new.setB8(colores[19]);
            c_new.setB9(colores[20]);
        }
        return c_new;
    }
}
