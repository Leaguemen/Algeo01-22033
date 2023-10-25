import ADT_Matrix.*;
import Bicubic.BicubicSpline;
import Interpolasi.Interpolasi_Polinomial;
import Regresi.Regresi;

import java.io.File;
import java.util.Scanner;

public class driverTubes1 {
    private static Scanner sc = new Scanner(System.in);

    public static Matrix inputSPLMatrix() {
        System.out.println("\nMasukkan ukuran matriks koefisien (m n): ");
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
        if (!isFileExist) {
            System.out.print("Masukkan nama file (pakai \".txt\") dari direktori test: ");
            namaFile = sc.next();

            isFileExist = ReadFile.isFileExist(namaFile);
            if (!isFileExist) {
                System.out.println("\nFile tidak ditemukan.");
                namaFile = "";
            } else {
                namaFile = "test\\" + namaFile;
            }
        }
        return namaFile;
    }

    public static void main(String args[]) throws Exception {
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
                    int pilihan = 0;
                    // pesan sambut
                    if (chosen1 == 1) {
                        System.out.println("---------------METODE ELIMINASI GAUSS---------------");
                    } else if (chosen1 == 2) {
                        System.out.println("---------------METODE ELIMINASI GAUSS-JORDAN---------------");
                    } else if (chosen1 == 3) {
                        System.out.println("----------------METODE MATRIKS BALIKAN---------------");
                        System.out.println("Pilih metode invers:\n1. Invers dengan matriks identitas\n2. Invers dengan ekspansi kofaktor");
                        pilihan = sc.nextInt();
                    } else {
                        System.out.println("---------------METODE CRAMER---------------");
                    }

                    // input matriks
                    System.out.print("Pilih cara input matriks:\n1. Input melalui terminal\n2. Input melalu file .txt\nPilihan: ");
                    int pilihanInput = sc.nextInt();
                    if (pilihanInput == 1 || pilihanInput== 2) {
                        Matrix mAug = new Matrix(0,0);
                        String namaFile = "";
                        
                        if (pilihanInput == 1) {
                            mAug = inputSPLMatrix();
                        } else if (pilihanInput == 2) {
                            namaFile = pilihFile(namaFile);
                            if (namaFile != "") {
                                mAug = ReadFile.parseFile(mAug, namaFile);
                            }
                        }

                        if (pilihanInput == 1 || namaFile != "") {
                        // Pemrosesan tiap pilihan
                            if (chosen1 == 1) {
                                solution = Gauss.gauss(mAug);
                            } else if (chosen1 == 2) {
                                solution = GaussJordan.SPLGaussJordan(mAug);
                            } else if (chosen1 == 3) {
                                solution = Invers.SolusiSPLDenganInvers(mAug, pilihan);
                                detNotZero = (solution.length != 0);
                                if (!detNotZero) {
                                    System.out.println("\nSolusi SPL tidak dapat ditemukan dengan metode invers.");
                                }
                            } else if (chosen1 == 4) {
                                solution = Cramer.SPLCramer(mAug);
                                detNotZero = (solution.length != 0);
                                if (!detNotZero) {
                                    System.out.println("\nSolusi SPL tidak dapat ditemukan dengan metode Cramer.");
                                }
                            }
                            
                            if (detNotZero) {
                                int i;
                                System.out.println();
                                // print solusi
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

                                // memberi opsi simpan ke file
                                System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                                char confirmation = sc.next().charAt(0);
                                if (confirmation == 'Y') {
                                    System.out.print("Masukkan nama file (pakai \".txt\"): ");
                                    String filename = sc.next();
                                    String stringSolution = WriteToFile.ArrayofStringtoString(solution);
                                    WriteToFile.writeFile(stringSolution, filename);
                                }
                            }
                        }
                    } else {
                        System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
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
                    // Pesan Sambut
                    if (chosen2 == 1) {
                        System.out.println("---------------METODE REDUKSI BARIS---------------");
                    } else if (chosen2 == 2) {
                        System.out.println("---------------METODE KOFAKTOR---------------");
                    }

                    // input matriks
                    System.out.print("Pilih cara input matriks:\n1. Input melalui terminal\n2. Input melalu file .txt\nPilihan: ");
                    int pilihanInput = sc.nextInt();
                    if (pilihanInput == 1 || pilihanInput== 2) {
                        Matrix m = new Matrix(0,0);
                        String namaFile = "";
                        
                        if (pilihanInput == 1) {
                            System.out.print("\nMasukkan ukuran matriks persegi (n): ");
                            int n = sc.nextInt();
                            m = new Matrix(n,n);
                            System.out.println("Masukkan matriks:");
                            Matrix.readMatrix(m, n, n);
                        } else if (pilihanInput == 2) {
                            namaFile = pilihFile(namaFile);
                            if (namaFile != "") {
                                m = ReadFile.parseFile(m, namaFile);
                            }
                        }

                        if (pilihanInput == 1 || namaFile != "") {
                            float det = 0;
                            if (chosen2 == 1) {
                                det = reduksiBaris.getDeterminant(m);
                            } else if (chosen2 == 2) {
                                det = Cofactor.cofactorDeterminant(m);
                            }

                            // tampil solusi
                            System.out.println("\nDeterminan: " + det);
                            
                            // simpan hasil
                            System.out.println();

                            System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                            char confirmation = sc.next().charAt(0);
                            if (confirmation == 'Y') {
                                System.out.print("Masukkan nama file (pakai \".txt\"): ");
                                String filename = sc.next();
                                WriteToFile.writeFile(Float.toString(det), filename);
                            }
                        } else {
                            System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                        }
                    } else {
                        System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
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
                    // Pesan Sambut
                    if (chosen3 == 1) {
                        System.out.println("---------------METODE GAUSS-JORDAN---------------");
                    } else if (chosen3 == 2) {
                        System.out.println("---------------METODE MATRIKS ADJOIN---------------");
                    }

                    // input matriks
                    System.out.print("Pilih cara input matriks:\n1. Input melalui terminal\n2. Input melalu file .txt\nPilihan: ");
                    int pilihanInput = sc.nextInt();
                    if (pilihanInput == 1 || pilihanInput== 2) {
                        Matrix m = new Matrix(0,0);
                        String namaFile = "";
                        
                        if (pilihanInput == 1) {
                            System.out.print("\nMasukkan ukuran matriks persegi (n): ");
                            int n = sc.nextInt();
                            m = new Matrix(n,n);
                            System.out.println("Masukkan matriks:");
                            Matrix.readMatrix(m, n, n);
                        } else if (pilihanInput == 2) {
                            namaFile = pilihFile(namaFile);
                            if (namaFile != "") {
                                m = ReadFile.parseFile(m, namaFile);
                            }
                        }
                        
                        // Proses mencari inverse
                        Matrix mNew = new Matrix(0, 0);
                        if (chosen3 == 1) {
                            mNew = Invers.InverseWithGaussJordan(m);
                        } else if (chosen3 == 2) {
                            mNew = Invers.InverseWithCofactor(m);
                        }

                        // Tampilkan hasil
                        if (mNew.rowEff != 0) {
                            System.out.println("Matriks hasil invers: ");
                            Matrix.displayMatrix(mNew);
                        } else {
                            System.out.println("Matriks singular");
                        }

                        // penyimpanan matriks
                        System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                        char confirmation = sc.next().charAt(0);
                        if (confirmation == 'Y') {
                            System.out.print("Masukkan nama file (pakai \".txt\"): ");
                            String filename = sc.next();
                            String stringSolution = WriteToFile.MatrixtoString(mNew);
                            WriteToFile.writeFile(stringSolution, filename);
                        }
                    } else {
                        System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                    }
                } else {
                    System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                }
            } else if (chosen == 4) {
                System.out.println("---------------INTERPOLASI POLINOMIAL---------------");
                System.out.print("Pilih cara input matriks:\n1. Input melalui terminal\n2. Input melalu file .txt\nPilihan: ");
                int pilihanInp = sc.nextInt();
                if (pilihanInp == 1 || pilihanInp == 2) {
                    Matrix koleksiTitik = new Matrix(0,0);
                    String namaFile = "";
                    float x = 0;
                    
                    if (pilihanInp == 1) {
                        koleksiTitik = Interpolasi_Polinomial.titikInput();
                        System.out.print("Masukkan x yang ingin diuji: ");
                        x = Float.parseFloat(sc.next());
                    } else if (pilihanInp == 2) {
                        namaFile = pilihFile(namaFile);
                        if (namaFile != "") {
                            koleksiTitik = ReadFile.readMatrixFromFile(namaFile);
                            float[] a = ReadFile.readBottomLine(namaFile);
                            x = a[0];
                        }
                    }
                Interpolasi_Polinomial f = Interpolasi_Polinomial.interpolasiPolinomial(x, koleksiTitik);
                
                // penyimpanan jawaban
                    if (pilihanInp == 1 || namaFile != "") {
                        System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                        char confirmation = sc.next().charAt(0);
                        if (confirmation == 'Y') {
                            System.out.print("Masukkan nama file (pakai \".txt\"): ");
                            String filename = sc.next();
                            String print = WriteToFile.ArrayofStringtoString((f.koefisien));
                            WriteToFile.writeFile(print, filename);
                            WriteToFile.writeFile(Float.toString(f.y), filename);
                        }
                    } else {
                        System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                    }
                }
            } else if (chosen == 5) {
                System.out.println("---------------INTERPOLASI BICUBIC SPLINE---------------");
                System.out.print("Pilih cara input matriks:\n1. Input melalui terminal\n2. Input melalu file .txt\nPilihan: ");
                int pilihanInput = sc.nextInt();
                if (pilihanInput == 1 || pilihanInput== 2) {
                    Matrix m = new Matrix(0,0);
                    String namaFile = "";
                    float targetX = 0;
                    float targetY = 0;
                    
                    if (pilihanInput == 1) {
                        m = new Matrix(4,4);
                        System.out.println("Masukkan matris:");
                        Matrix.readMatrix(m,4,4);
                        System.out.println("Masukkan titik yang ingin diestimasi (x y): ");
                        targetX = sc.nextFloat();
                        targetY = sc.nextFloat();
                    } else if (pilihanInput == 2) {
                        namaFile = pilihFile(namaFile);
                        if (namaFile != "") {
                            m = ReadFile.readMatrixFromFile(namaFile);
                            float[] xy = ReadFile.readBottomLine(namaFile);
                            targetX = xy[0];
                            targetY = xy[1];
                        }
                    }

                    if (pilihanInput == 1 || namaFile != "") {
                        float result = BicubicSpline.interpolation(m,targetX,targetY);

                        // penyimpanan jawaban
                        System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                        char confirmation = sc.next().charAt(0);
                        if (confirmation == 'Y') {
                            System.out.print("Masukkan nama file (pakai \".txt\"): ");
                            String filename = sc.next();
                            WriteToFile.writeFile(Float.toString(result), filename);
                        }
                    } else {
                        System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                    }
                } else {
                    System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                }
            } else if (chosen == 6) {
                System.out.println("---------------REGRESI LINEAR BERGANDA---------------");
                System.out.print("Pilih cara input matriks:\n1. Input melalui terminal\n2. Input melalu file .txt\nPilihan: ");
                int pilihanInput = sc.nextInt();
                if (pilihanInput == 1 || pilihanInput== 2) {
                    int n = 0;
                    float[] solusi = new float[100];
                    float[] peubah = new float[100];
                    String namaFile = "";
                    if (pilihanInput == 1) {
                        System.out.print("Masukkan jumlah peubah (n): ");
                        n = sc.nextInt();
                        System.out.print("Masukkan jumlah data (m): ");
                        int m = sc.nextInt();
                        System.out.println("Masukkan sebanyak m baris dalam bentuk (x1 x2 ... xn y):");
                        Matrix matrixM = Regresi.prosesRegresiBerganda(n, m);
                        solusi = Regresi.ambilHasil(matrixM);
                        System.out.println("Masukkan nilai yang akan diestemasi (x1 x2 ... xn):");
                        peubah = new float[n];
                        int i;
                        for (i=0;i<n;i++) {
                            peubah[i] = sc.nextFloat();
                        }
                    } else if (pilihanInput == 2) {
                        namaFile = pilihFile(namaFile);
                        if (namaFile != "") {
                            Matrix m = ReadFile.readMatrixFromFile(namaFile);
                            n = m.colEff-1;
                            peubah = ReadFile.readBottomLine(namaFile);
                            Matrix matrixM = Regresi.normalEstimationEquation(m);
                            solusi = Regresi.ambilHasil(matrixM);
                        }
                    }

                    // save jawaban
                    if (pilihanInput == 1 || namaFile != "") {
                        Regresi f = Regresi.regresiBerganda(n, solusi, peubah);

                        // penyimpanan jawaban
                        System.out.print("Apakah ingin disimpan ke file? (Y/N): ");
                        char confirm = sc.next().charAt(0);
                        if (confirm == 'Y') {
                            System.out.print("Masukkan nama file (pakai \".txt\"): ");
                            String filename = sc.next();
                            String ab = WriteToFile.ArrayofStringtoString(f.koef);
                            WriteToFile.writeFile(ab, filename);
                            WriteToFile.writeFile(Float.toString(f.nilai), filename);
                        }
                    } else {
                        System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                    }
                } else {
                    System.out.println("---------------OPSI TIDAK TERSEDIA---------------\n");
                }
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
