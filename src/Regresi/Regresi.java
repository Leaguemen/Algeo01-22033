package Regresi;
import ADT_Matrix.*;
import java.util.Scanner;

public class Regresi {

    public float[] koef = new float[100];
    public float nilai;

    public Regresi (float[] koef, float nilai) {
        this.koef = koef;
        this.nilai = nilai;
    }

    public static Matrix readPoint (int n, int m) {
        // Fungsi untuk menerima matriks di awal
        Matrix mat = new Matrix(m, n+1);
        Matrix.readMatrix(mat, mat.rowEff, mat.colEff);
        return mat;
    }

    public static Float sumCol (Matrix m, int IdxCol) {
        // Fungsi untuk menghitung total nilai pada suatu kolom
        int j;
        float sum = 0;
        for (j = 0; j <= Matrix.getLastIdxRow(m); j++) {
            sum += m.memory[j][IdxCol];
        }
        return sum;
    }

    public static Float sumColTimesCol (Matrix m, int IdxCol1, int IdxCol2) {
        // Fungsi untuk menghitung nilai total dari perkalian elemen kolom dengan kolom
        int j;
        float sum = 0;
        for (j = 0; j <= Matrix.getLastIdxRow(m); j++) {
            sum += (m.memory[j][IdxCol1]*m.memory[j][IdxCol2]);
        }
        return sum;
    }

    public static Matrix normalEstimationEquation (Matrix m) {
        // Fungsi untuk membuat matriks Normal Estimation Equation untuk Multiple Linear Regression
        Matrix hasil = new Matrix(m.colEff, m.colEff+1);
        int i, j;

        // Mengisi baris pertama dan kolom pertama
        hasil.memory[0][0] = m.rowEff;
        for (i = 1; i < m.colEff+1; i++) {
            hasil.memory[0][i] = sumCol(m, i-1);
        }
        for (i = 1; i < m.colEff; i++) {
            hasil.memory[i][0] = sumCol(m, i-1);
        }

        // Mengisi elemen tersisa sisanya
        for (i = 1; i < m.colEff; i++) {
            for (j = 1; j < m.colEff+1; j++) {
                hasil.memory[i][j] = sumColTimesCol(m, i-1, j-1);
            }
        }
        return hasil;
    }

    public static Matrix prosesRegresiBerganda (int n, int m) {
        Matrix mat = readPoint(n, m);
        Matrix hasil = normalEstimationEquation(mat);
        
        return hasil;
    }

    public static float[] ambilHasil (Matrix hasil) {
        float[] solusi = Gauss.gauss(hasil);
        return solusi; 
    }

    public static float hitungNilai(float[] solusi, float[] peubah, int n) {
        float result = solusi[0];
        for (int i = 0; i < n; i++) {
            result += (solusi[i+1]*peubah[i]);
        }
        return result;
    }

    public static String tampilHasil (float[] solusi) {
        String tampil = "f(x) = " + solusi[0] + " + ";
        for (int i = 1; i < solusi.length; i++) {
            String temp = "x" + (i) + "(" + (solusi[i]) + ") ";
            if (i < solusi.length-1) {
                temp += "+ ";
            }
            tampil += temp;
        }
        return tampil;
    }

    public static String tampilHitungNilai(float result) {
        String tampil = "f(xk) = " + Float.toString(result);
        return tampil;
    }

    public static Regresi regresiBerganda(int n, float[] koefisien, float[] peubah) {
        float result = Regresi.hitungNilai(koefisien, peubah, n);
        String tampil = Regresi.tampilHasil(koefisien);
        System.out.println(tampil);
        String tampil1 = Regresi.tampilHitungNilai(result);
        System.out.println(tampil1);
        System.out.println(result);
        Regresi f = new Regresi(koefisien,result);
        return f;
    }

}