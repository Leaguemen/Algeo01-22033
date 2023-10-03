package ADT_Matrix;

import java.util.*;

public class Invers {
    
    public static Matrix MakeIdentity (int Row, int Col) {
        Matrix identity = new Matrix(Row, Col);
        int i, j;

        for (i = 0; i < Row; i++) {
            for (j = 0; j < Col; j++) {
                identity.memory[i][j] = (i == j) ? 1 : 0;
            }
        }
        return identity;
    }

    public static Matrix MatriksSoal (Matrix m) {
        Matrix hasil = new Matrix(m.rowEff, m.rowEff);
        int i, j;

        for (i = 0; i < hasil.rowEff; i++) {
            for (j = 0; j < hasil.colEff; j++) {
                hasil.memory[i][j] = m.memory[i][j];
            }
        }
        return hasil;
    }

    public static Matrix MatriksJawaban (Matrix m) {
        Matrix hasil = new Matrix(m.rowEff, 1);
        int i;

        for (i = 0; i < hasil.rowEff; i++) {
            hasil.memory[i][0] = m.memory[i][Matrix.getLastIdxCol(m)];
        }
        return hasil;
    }

    public static Matrix InverseWithGaussJordan (Matrix m) {
        int i, j, k;
        int n = 0;
        Matrix hasilInvers = MakeIdentity(m.rowEff,m.colEff);

        for (i = 0; i < m.colEff; i++) {
            // Mencari element diagonal bukan nol
            if (m.memory[i][i] == 0) {
                boolean tidakKetemu = true;
                j = i+1;
                while (tidakKetemu && j <m.rowEff) {
                    if (m.memory[j][i] != 0) {
                        tidakKetemu = false;
                        Matrix.Swap(m, i, j, n);
                        Matrix.Swap(hasilInvers, i, j, n);
                    }
                    j++;
                }
            }

            // Menjadikan elemen diagonal manjadi 1 utama
            Matrix.MultiplyRow(hasilInvers, i, (float)1/m.memory[i][i]);
            Matrix.MultiplyRow(m, i, (float)1/m.memory[i][i]);
            
            // Mengenolkan elemen di bawah dan atas 1 utama
            for (k = 0; k < m.rowEff; k++) {
                if (k != i) {
                    float x = (-1)*m.memory[k][i];
                    Matrix.AddRowByRow(m, k, i, x);
                    Matrix.AddRowByRow(hasilInvers, k, i, x);
                }
            }
        }

        return hasilInvers;
    }

    public static Matrix InverseWithCofactor (Matrix m) {
        Matrix hasilInvers = new Matrix(m.rowEff, m.colEff);
        int i, j;

        for (i = 0; i < m.rowEff; i++) {
            for (j = 0; j < m.colEff; j++) {
                hasilInvers.memory[i][j] = ADT_Matrix.Cofactor.entryCofactor(m, i, j);
            }
        }

        hasilInvers = Matrix.transpose(hasilInvers);
        hasilInvers = Matrix.multiplyByConst(hasilInvers, (float)1/Cofactor.cofactorDeterminant(m));

        return hasilInvers;
    }

    public static void SolusiSPLDenganInvers (Matrix m) {
        Scanner in = new Scanner(System.in);
        Matrix Ainvers = new Matrix(0,0);
        int i; int pilihan = 3;
        Matrix A = MatriksSoal(m);
        Matrix b = MatriksJawaban(m);

        while (pilihan < 1 || pilihan > 2) {
            System.out.println("Pilih metode invers:\n1. Invers dengan matriks identitas\n2. Invers dengan ekspansi kofaktor");
            pilihan = in.nextInt();
            if (pilihan == 1) {
                Ainvers = InverseWithGaussJordan(A);
            } else if (pilihan == 2) {
                Ainvers = InverseWithCofactor(m);
            } else {
                System.out.println("Tidak ada pilihan tersebut");
            }
        }
        in.close();
        Matrix solusi = Matrix.multiplyMatrix(Ainvers, b);
        
        for (i = 1; i <= solusi.rowEff; i++) {
            System.out.print("x" + i + " = ");
            System.out.println(solusi.memory[i-1][0]);
        }
    }
}
