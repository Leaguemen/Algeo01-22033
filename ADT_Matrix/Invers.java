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
        if (Cofactor.cofactorDeterminant(m) != 0) {
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
        } else {
            Matrix hasilInvers = MakeIdentity(0,0);
            return hasilInvers;
        }
    }

    public static Matrix InverseWithCofactor (Matrix m) {
        if (Cofactor.cofactorDeterminant(m) != 0)  {
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
        } else {
            Matrix hasilInvers = new Matrix(0, 0);
            return hasilInvers;
        }
    }

    public static float[] SolusiSPLDenganInvers (Matrix m, int pilihan) {
        Matrix A = MatriksSoal(m);
        if (Cofactor.cofactorDeterminant(A) != 0) {
            Matrix Ainvers = new Matrix(A.rowEff,A.colEff);
            int i;
            Matrix b = MatriksJawaban(m);

            if (pilihan == 1) {
                Ainvers = InverseWithGaussJordan(A);
            } else {
                Ainvers = InverseWithCofactor(A);
            }
            Matrix solusi = Matrix.multiplyMatrix(Ainvers, b);

            float[] solution = new float[solusi.rowEff];
            for (i=0;i<solusi.rowEff;i++) {
                solution[i] = solusi.memory[i][0];
            }
            return solution;
        } else {
            float[] solution = {};
            return solution;
        }
    }
}
