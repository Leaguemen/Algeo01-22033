
import java.util.Scanner;

import ADT_Matrix.*;

public class Regresi {

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

    public static void regresiBerganda () {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Masukkan jumlah peubah (n): ");
            int n = input.nextInt();
            System.out.print("Masukkan jumlah sampel (m): ");
            int m = input.nextInt();
            Matrix mat = readPoint(n, m);
            // System.out.print("Masukkan nilai x yang akan diestimasi: ");
            // float x = input.nextFloat();
            
            Matrix hasil = normalEstimationEquation(mat);
            Matrix.displayMatrix(hasil);
            Gauss.gauss(hasil);
            
        }
    } 
}
