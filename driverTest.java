import java.util.Scanner;

import ADT_Matrix.*;
import Regresi.Regresi;

public class driverTest {
        public static void main(String[] args) {
        // Matrix m = new Matrix(3, 3);
        // Matrix.readMatrix(m,3,3);
        // Matrix.displayMatrix(m);
        // ADT_Matrix.Matrix m = new ADT_Matrix.Matrix(0, 0);
        // String fileName = "*";

        // fileName = ADT_Matrix.ReadFile.pilihFile(fileName);
        // m = ADT_Matrix.ReadFile.parseFile(m,fileName);
        // ADT_Matrix.Matrix.displayMatrix(m);
        // System.out.println(m.colEff);
        // System.out.println(m.rowEff);

        // Cobain invers
        // Matrix m = new Matrix(3, 4);
        // Matrix.readMatrix(m, 3, 4);
        // Invers.SolusiSPLDenganInvers(m);

        // Cobain Kofaktor
        // Matrix m1;
        // Matrix m = new Matrix(3, 3);
        // Matrix.readMatrix(m,3,3);
        // Matrix.displayMatrix(m);
        // System.out.println(Cofactor.cofactorDeterminant(m));
        // System.out.println(Cofactor.entryCofactor(m, 3, 3));
        // m1 = Cofactor.cofactorMatrix(m);
        // Matrix.displayMatrix(m1);

        // Cobain Cramer
        // Matrix m = new Matrix(4,5);
        // Matrix.readMatrix(m,4,5);
        // Cramer.SPLCramer(m);

        // Cobain regresi
        Scanner baca = new Scanner(System.in);
        System.out.println("Masukkan n");
        int n = baca.nextInt();
        System.out.println("Masukkan m");
        int m = baca.nextInt();
        
        float[] konstanta = Regresi.regresiBerganda(n,m);
        Regresi.tampilPersamaan(konstanta, n);
        Regresi.hitungPersamaan(konstanta, n);
        baca.close();
        // invers
        // Matrix m = new Matrix(4, 4);
        // Matrix.readMatrix(m, 4, 4);
        // Matrix.displayMatrix(Invers.InverseWithCofactor(m));
    }
}