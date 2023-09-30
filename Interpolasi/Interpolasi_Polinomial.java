package Interpolasi;
import java.util.Scanner;
import java.lang.Math;
import ADT_Matrix.*;

public class Interpolasi_Polinomial {

    private static Scanner scanner = new Scanner(System.in);

    public static Matrix titikInput(){
        System.out.print("Masukkan banyak titik : ");
        int titikAmmount = Integer.parseInt(scanner.nextLine());
        Matrix koleksiTitik = new Matrix(titikAmmount,2);
        Matrix.readMatrix(koleksiTitik, titikAmmount,2);
        return(koleksiTitik);
    }


    public static float interpolasi_polinomial(){
        Matrix koleksiTitik = titikInput();
        Matrix augmented = new Matrix(koleksiTitik.rowEff, koleksiTitik.rowEff+1);
        //populate the last row
        for(int i=0;i<augmented.rowEff;i++){
            augmented.memory[i][augmented.colEff-1]= koleksiTitik.memory[i][1];
        }
        //populate the other rows
        for(int i=0;i<augmented.rowEff;i++){
            for(int j=0;j<augmented.colEff-1;j++){
                augmented.memory[i][j]= (float) Math.pow((double)koleksiTitik.memory[i][0],(double)j);
            }
        }
        float[] koefisien =Gauss.gauss(augmented);
        System.out.println("Persamaannya adalah: ");
        String persamaan = "P(x)= ";
        for(int i=0;i<koefisien.length;i++){
            if(i ==0){
                persamaan += " "+Float.toString(koefisien[i]) + " +";
            }
            else if(i==1){
                persamaan += " "+Float.toString(koefisien[i]) + "X +";
            }
            else if(i== koefisien.length-1){
                persamaan += " "+Float.toString(koefisien[i]) + "X^" + Integer.toString(i);
            }
            else{
                persamaan += " "+Float.toString(koefisien[i]) + "X^" + Integer.toString(i)+" +";
            }   
        }
        System.out.println(persamaan);
        float x = (float)9.2;
        float y=0;
        for(int j=0;j<koefisien.length;j++){
            y += koefisien[j]*(float) Math.pow((double)x,(double)j); 
        }              
        System.out.println(y);
        return(y);
    }

     public static void main(String[] args){
        interpolasi_polinomial();
        scanner.close();
     }
}
