import ADT_Matrix.*;
import java.util.Scanner;

public class driverTubes1 {
    public static void main(String args[]) {
        boolean exit = false;
        while (!exit) {
            System.out.println("MENU"
                                + "\n1. Sistem Persamaan Linear"
                                + "\n2. Determinan"
                                + "\n3. Matriks Balikan"
                                + "\n4. Interpolasi Polinom"
                                + "\n5. Interpolasi Bicubic Spline"
                                + "\n6. Regresi Linear Berganda"
                                + "\n7. Keluar");

            Scanner sc = new Scanner(System.in);
            int chosen = sc.nextInt();

            if (chosen == 1) {
                System.out.println("MENU"
                                    + "\n1. Metode Eliminasi Gauss"
                                    + "\n2. Metode Eliminasi Gauss-Jordan"
                                    + "\n3. Metode Matriks Balikan"
                                    + "\n4. Metode Cramer");

                int chosen1 = sc.nextInt();

                if (chosen1 == 1) {
                    
                }
            } else if (chosen == 2) {
                System.out.println("MENU"
                                    + "\n1. Metode Reduksi Baris"
                                    + "\n2. Metode Kofaktor");

                int chosen2 = sc.nextInt();

                if (chosen2 == 1) {
                    int n = sc.nextInt();
                    Matrix m = new Matrix(n,n);
                    float det = reduksiBaris.getDeterminant(m);
                    System.out.println("Determinan: " + det);
                } else if (chosen2 == 2) {
                    int n = sc.nextInt();
                    Matrix m = new Matrix(n,n);
                    float det = Cofactor.cofactorDeterminant(m);
                    System.out.println("Determinan: " + det);
                } else {
                    System.out.println("---------------Opsi tidak tersedia.--------------");
                }
            } else if (chosen == 3) {
                System.out.println("MENU"
                                    + "\n1. Metode Gauss-Jordan"
                                    + "\n2. Metode Matriks Adjoin");
                                    
                int chosen3 = sc.nextInt();

                if (chosen3 == 1) {
                    int n = sc.nextInt();
                    Matrix m = new Matrix(n,n);
                    Matrix mNew = Invers.InverseWithGaussJordan(m);
                    System.out.println("Matriks hasil invers: ");
                    Matrix.displayMatrix(mNew);
                } else if (chosen3 == 2) {
                    int n = sc.nextInt();
                    Matrix m = new Matrix(n,n);
                    Matrix mNew = Invers.InverseWithCofactor(m);
                    System.out.println("Matriks hasil invers; ");
                    Matrix.displayMatrix(mNew);
                } else {
                    System.out.println("---------------Opsi tidak tersedia.--------------");
                }
            } else if (chosen == 4) {
                // belum selesai
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