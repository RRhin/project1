/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaquetFuncions;

import java.util.Random;

/**
 *
 * @author vicent
 */
public class FuncionsVectors {

    public static int[] crea_vector(int max) {
         int []v=new int[max];
        int i;
        Random rnd = new Random();
        for (i = 0; i < max; i++) {
            v[i] = rnd.nextInt(max);
        }

        return v;
    }
   
    public static void imprimeix_vector(int vec[], int max) {
        int i;
        for (i = 0; i < max; i++) {
            System.out.print(vec[i] + " ");
        }
    }

    public static boolean comprova_parell(int num) {
        //tornarà 0 si és parell o 1 si és senar

        if (num % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static int[] borra_parells(int vec[], int max) {
        int i;
        int num; //comprova si el número és parell
        Random rnd = new Random();

        for (i = 0; i < max; i++) {
            num = vec[i];
            if (!comprova_parell(num)) {
                vec[i] = rnd.nextInt(max);
                i--;
            }
        }
        return vec;
    }
    
    
}
