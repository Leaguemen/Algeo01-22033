import ADT_Matrix.*;
import Bicubic.BicubicSpline;
import Interpolasi.Interpolasi_Polinomial;
import Regresi.Regresi;

import java.util.Scanner;

public class driverTubes1 {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String args[]) {
        boolean exit = false;
        while (!exit) {
            System.out.print("---------------MENU---------------"
                                + "\n1. Sistem Persamaan Linear"
                                + "\n2. Determinan"
                                + "\n3. Matriks Balikan"
                                + "\n4. Interpolasi Polinom"
                                + "\n5. Interpolasi Bicubic Spline"
                                + "\n6. Regresi Linear Berganda"
                                + "\n7. Keluar"
                                + "\nPilihan: ");

            int chosen = sc.nextInt();
            System.out.println();

            if (chosen == 1) {
                System.out.print("---------------SISTEM PERSAMAAN LINEAR---------------"
                                    + "\n1. Metode Eliminasi Gauss"
                                    + "\n2. Metode Eliminasi Gauss-Jordan"
                                    + "\n3. Metode Matriks Balikan"
                                    + "\n4. Metode Cramer"
                                    + "\nPilihan: ");

                int chosen1 = sc.nextInt();

                if (chosen1 == 1) {
                    
                } else if (chosen1 == 2) {

                } else if (chosen1 == 3) {

                } else if (chosen1 == 4) {

                } else {
                    System.out.println("---------------Opsi tidak tersedia.---------------");
                }
            } else if (chosen == 2) {
                System.out.print("---------------DETERMINAN---------------"
                                    + "\n1. Metode Reduksi Baris"
                                    + "\n2. Metode Kofaktor"
                                    + "\nPilihan: ");

                int chosen2 = sc.nextInt();

                if (chosen2 == 1) {
                    System.out.print("---------------METODE REDUKSI BARIS---------------"
                                        + "\nMasukkan ukuran matriks persegi (n): ");
                    int n = sc.nextInt();
                    Matrix m = new Matrix(n,n);
                    System.out.println("Masukkan matriks:");
                    Matrix.readMatrix(m, n, n);
                    float det = reduksiBaris.getDeterminant(m);
                    System.out.println("Determinan: " + det);
                } else if (chosen2 == 2) {
                    System.out.print("---------------METODE KOFAKTOR---------------"
                                        + "\nMasukkan ukuran matriks persegi (n): ");
                    int n = sc.nextInt();
                    Matrix m = new Matrix(n,n);
                    System.out.println("Masukkan matriks:");
                    Matrix.readMatrix(m, n, n);
                    float det = Cofactor.cofactorDeterminant(m);
                    System.out.println("Determinan: " + det);
                } else {
                    System.out.println("---------------Opsi tidak tersedia.---------------");
                }
            } else if (chosen == 3) {
                System.out.print("---------------MATRIKS BALIKAN----------------"
                                    + "\n1. Metode Gauss-Jordan"
                                    + "\n2. Metode Matriks Adjoin"
                                    + "\nPilihan: ");
                                    
                int chosen3 = sc.nextInt();

                if (chosen3 == 1) {
                    System.out.println("---------------METODE GAUSS-JORDAN---------------"
                                            + "\nMasukkan ukuran matriks persegi (n): ");
                    int n = sc.nextInt();
                    Matrix m = new Matrix(n,n);
                    System.out.println("Masukkan matriks:");
                    Matrix.readMatrix(m,n,n);
                    Matrix mNew = Invers.InverseWithGaussJordan(m);
                    System.out.println("Matriks hasil invers: ");
                    Matrix.displayMatrix(mNew);
                } else if (chosen3 == 2) {
                    System.out.println("---------------METODE MATRIKS ADJOIN---------------"
                                            + "\nMasukkan ukuran matriks persegi(n): ");
                    int n = sc.nextInt();
                    Matrix m = new Matrix(n,n);
                    System.out.println("Masukkan matriks");
                    Matrix.readMatrix(m, n, n);
                    Matrix mNew = Invers.InverseWithCofactor(m);
                    System.out.println("Matriks hasil invers; ");
                    Matrix.displayMatrix(mNew);
                } else {
                    System.out.println("---------------Opsi tidak tersedia.---------------");
                }
            } else if (chosen == 4) {
                Interpolasi_Polinomial.interpolasiPolinomial();
            } else if (chosen == 5) {
                BicubicSpline.interpolation();
            } else if (chosen == 6) {
                Regresi.regresiBerganda();
            } else if (chosen == 7) {
                System.out.println("---------------Keluar dari Program---------------");
                // sc.close();
                exit = true;
            } else {
                System.out.println("---------------Opsi tidak tersedia.--------------");
            }
        }
    }
}