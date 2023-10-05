import ADT_Matrix.*;
import Bicubic.BicubicSpline;
import Interpolasi.Interpolasi_Polinomial;
import Regresi.Regresi;

import java.io.File;
import java.util.Scanner;

public class driverTubes1 {
    private static Scanner sc = new Scanner(System.in);

    public static Matrix inputSPLMatrix() {
        System.out.println("\nMasukkan ukuran matriks (m n): ");
        int m = sc.nextInt();
        int n = sc.nextInt();
        System.out.println("Masukkan matriks augmented dengan ukuran " + m + " * " + (n + 1) + ": ");
        Matrix mAug = new Matrix(n, n + 1);
        Matrix.readMatrix(mAug, m, n + 1);
        if (m != n) {
            int i, j;
            for(i=0;i<n-m;i++) {
                for(j=0;j<n + 1;j++) {
                    mAug.memory[i + m][j] = 0;
                }
            }
        }
        return mAug;
    }

    public static String pilihFile (String namaFile) {
        boolean isFileExist = false;
        while (!isFileExist) {
            System.out.print("Masukkan nama file : ");
            namaFile = sc.nextLine();

            isFileExist = ReadFile.isFileExist(namaFile);
            if (!isFileExist) {
                System.out.println("File tidak ditemukan.");
            }
        }
        return namaFile;
    }

    public static void main(String args[]) {
        boolean exit = false;
        while (!exit) {
            System.out.print("\n---------------MENU---------------"
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
                System.out.println();

                float[] solution = new float[0];
                boolean detNotZero = true;
                if (chosen1 >= 1 && chosen1 <= 4) {
                    System.out.print("Pilih input matriks:\n1. Input melalui teriman\n2. Input melalu file .txt\nPilihan: ");
                    int pilihanInput = sc.nextInt();

                    if (pilihanInput == 1) {
                        
                    }
                    if (chosen1 == 1) {
                        System.out.println("---------------METODE ELIMINASI GAUSS---------------");
                        Matrix mAug = inputSPLMatrix();
                        solution = Gauss.gauss(mAug);
                    } else if (chosen1 == 2) {
                        System.out.println("---------------METODE ELIMINASI GAUSS-JORDAN---------------");
                        Matrix mAug = inputSPLMatrix();
                        solution = GaussJordan.SPLGaussJordan(mAug);
                    } else if (chosen1 == 3) {
                        System.out.println("----------------METODE MATRIKS BALIKAN---------------");
                        System.out.println("Pilih metode invers:\n1. Invers dengan matriks identitas\n2. Invers dengan ekspansi kofaktor");
                        int pilihan = sc.nextInt();
                        Matrix mAug = inputSPLMatrix();
                        solution = Invers.SolusiSPLDenganInvers(mAug, pilihan);
                        detNotZero = (solution.length != 0);
                        if (!detNotZero) {
                            System.out.println("\nSolusi SPL tidak dapat ditemukan dengan metode invers.");
                        }
                    } else if (chosen1 == 4) {
                        System.out.println("---------------METODE CRAMER---------------");
                        Matrix mAug = inputSPLMatrix();
                        solution = Cramer.SPLCramer(mAug); // solusi sudah diprint oleh function
                        detNotZero = (solution.length != 0);
                        if (!detNotZero) {
                            System.out.println("\nSolusi SPL tidak dapat ditemukan dengan metode Cramer.");
                        }
                    }
                    
                    // Memberi opsi simpan ke file
                    if (detNotZero) {
                        int i;
                        System.out.println();
                        for (i = 0; i < solution.length; i++) {
                            System.out.print("x" + (i +1) + "=");
                            System.out.print(solution[i]);
                            if (i != solution.length - 1) {
                                System.out.print(", ");
                            } else {
                                System.out.println();
                            }
                        }
                        System.out.println();

                        System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                        char confirmation = sc.next().charAt(0);
                        if (confirmation == 'Y') {
                            System.out.print("Masukkan nama file (tanpa \".txt\"): ");
                            String filename = sc.next();
                            String stringSolution = WriteToFile.ArrayofStringtoString(solution);
                            WriteToFile.writeFile(stringSolution, filename);
                        }
                    }
                } else {
                    System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                }
            } else if (chosen == 2) {
                System.out.print("---------------DETERMINAN---------------"
                                    + "\n1. Metode Reduksi Baris"
                                    + "\n2. Metode Kofaktor"
                                    + "\nPilihan: ");

                int chosen2 = sc.nextInt();
                System.out.println();
                if (chosen2 == 1 || chosen2 == 2) {
                    float det = 0;
                    if (chosen2 == 1) {
                        System.out.print("---------------METODE REDUKSI BARIS---------------"
                                            + "\nMasukkan ukuran matriks persegi (n): ");
                        int n = sc.nextInt();
                        Matrix m = new Matrix(n,n);
                        System.out.println("Masukkan matriks:");
                        Matrix.readMatrix(m, n, n);
                        det = reduksiBaris.getDeterminant(m);
                        System.out.println("\nDeterminan: " + det);
                    } else if (chosen2 == 2) {
                        System.out.print("---------------METODE KOFAKTOR---------------"
                                            + "\nMasukkan ukuran matriks persegi (n): ");
                        int n = sc.nextInt();
                        Matrix m = new Matrix(n,n);
                        System.out.println("Masukkan matriks:");
                        Matrix.readMatrix(m, n, n);
                        det = Cofactor.cofactorDeterminant(m);
                        System.out.println("\nDeterminan: " + det);
                    }

                    // simpan hasil
                    System.out.println();

                    System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                    char confirmation = sc.next().charAt(0);
                    if (confirmation == 'Y') {
                        System.out.print("Masukkan nama file (tanpa \".txt\"): ");
                        String filename = sc.next();
                        WriteToFile.writeFile(Float.toString(det), filename);
                    }
                } else {
                    System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                }
            } else if (chosen == 3) {
                System.out.print("---------------MATRIKS BALIKAN----------------"
                                    + "\n1. Metode Gauss-Jordan"
                                    + "\n2. Metode Matriks Adjoin"
                                    + "\nPilihan: ");
                                    
                int chosen3 = sc.nextInt();
                System.out.println();
                if (chosen3 == 1 || chosen3 == 2) {
                    // input di sini, nanti mNew inisialisasi dengan ukuran sesuai input
                    Matrix mNew;
                    if (chosen3 == 1) {
                        System.out.println("---------------METODE GAUSS-JORDAN---------------"
                                                + "\nMasukkan ukuran matriks persegi (n): ");
                        int n = sc.nextInt();
                        Matrix m = new Matrix(n,n);
                        System.out.println("Masukkan matriks:");
                        Matrix.readMatrix(m,n,n);
                        mNew = Invers.InverseWithGaussJordan(m);
                        if (mNew.rowEff != 0) {
                            System.out.println("Matriks hasil invers: ");
                            Matrix.displayMatrix(mNew);
                        } else {
                            System.out.println("Matriks singular");
                        }
                    } else if (chosen3 == 2) {
                        System.out.println("---------------METODE MATRIKS ADJOIN---------------"
                                                + "\nMasukkan ukuran matriks persegi(n): ");
                        int n = sc.nextInt();
                        Matrix m = new Matrix(n,n);
                        System.out.println("Masukkan matriks");
                        Matrix.readMatrix(m, n, n);
                        mNew = Invers.InverseWithCofactor(m);
                        if (mNew.rowEff != 0) {
                            System.out.println("Matriks hasil invers: ");
                            Matrix.displayMatrix(mNew);
                        } else {
                            System.out.println("Matriks singular");
                        }
                    }

                    // JANGAN DIHAPUS!
                    // System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                    // char confirmation = sc.next().charAt(0);
                    // if (confirmation == 'Y') {
                    //     System.out.print("Masukkan nama file (tanpa \".txt\"): ");
                    //     String filename = sc.next();
                    //     String stringSolution = WriteToFile.MatrixtoString(mNew);
                    //     WriteToFile.writeFile(stringSolution, filename);
                    // }
                } else {
                    System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                }
            } else if (chosen == 4) {
                System.out.println("---------------INTERPOLASI POLINOMIAL---------------\n");
                float y = Interpolasi_Polinomial.interpolasiPolinomial();

                // penyimpanan jawaban
                System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                char confirmation = sc.next().charAt(0);
                if (confirmation == 'Y') {
                    System.out.print("Masukkan nama file (tanpa \".txt\"): ");
                    String filename = sc.next();
                    WriteToFile.writeFile(Float.toString(y), filename);
                }
            } else if (chosen == 5) {
                System.out.println("---------------INTERPOLASI BICUBIC SPLINE---------------\n");
                float result = BicubicSpline.interpolation();

                // penyimpanan jawaban
                System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                char confirmation = sc.next().charAt(0);
                if (confirmation == 'Y') {
                    System.out.print("Masukkan nama file (tanpa \".txt\"): ");
                    String filename = sc.next();
                    WriteToFile.writeFile(Float.toString(result), filename);
                }
            } else if (chosen == 6) {
                Regresi.regresiBerganda();

                // save jawaban
                // System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                // char confirmation = sc.next().charAt(0);
                // if (confirmation == 'Y') {
                //     System.out.print("Masukkan nama file (tanpa \".txt\"): ");
                //     String filename = sc.next();
                //     WriteToFile.writeFile(Float.toString(result), filename);
                // }
            } else if (chosen == 7) {
                System.out.println("---------------KELUAR DARI PROGRAM---------------\n");
                // sc.close();
                exit = true;
            } else {
                System.out.println("---------------OPSI TIDAK TERSEDIA--------------\n");
            }
        }
    }
}