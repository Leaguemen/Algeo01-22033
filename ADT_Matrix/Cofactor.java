package ADT_Matrix;
import java.lang.Math;

public class Cofactor {
    public static float CofactorDeterminant (Matrix m) {
    /* Prekondisi: isSquare(m) */
    /* Menghitung nilai determinan m */
    // KAMUS
        float det = 0;
        // ALGORITMA
        if (m.colEff == 1) {
            return m.memory[0][0];
        } else if (m.colEff == 2) {
            return (m.memory[0][0]*m.memory[1][1] - m.memory[1][0]*m.memory[0][1]);
        } else {
            int j, last;
            last = Matrix.getLastIdxCol(m);
            for (j=0;j<=last;j++) {
                Matrix m1 = new Matrix(last,last);
                int i,k;
                for (i=0;i<=last;i++) {
                    for (k=0;k<=last;k++) {
                        if (k<j) {
                            m1.memory[i][k] = m.memory[i+1][k];
                        } else {
                            m1.memory[i][k] = m.memory[i+1][k+1];
                        }
                    }
                }
                det += (float) (Math.pow(-1,j) * m.memory[0][j] * CofactorDeterminant(m1));
            }
        }
        return det;
    }

    public static float EntryCofactor (Matrix m, int idxRow, int idxCol) {
        /* Menghasilkan kofaktor entri matriks dengan indeks idxRow dan idxCol */
        Matrix mNew = new Matrix(m.rowEff-1, m.colEff-1);
        int last, i, j, k, l;

        last = Matrix.getLastIdxCol(m);
        k = 0;
        for (i=0;i<=last;i++) {
            if (i!=idxRow) {
                l = 0;
                for (j=0;j<=last;j++) {
                    if(j!=idxCol) {
                        mNew.memory[k][l] = m.memory[i][j];
                        l++;
                    }
                }
                k++;
            }

        }
        return (float) Math.pow(-1,idxRow+idxCol) * CofactorDeterminant(mNew);
    }

    public static Matrix CofactorMatrix (Matrix m) {
        int i = 0; int j = 0;
        int last = Matrix.getLastIdxRow(m);
        Matrix mNew = new Matrix(last+1, last+1);
        for (i=0;i<=last;i++) {
            for (j=0;j<=last;j++) {
                mNew.memory[i][j] = (float) EntryCofactor(m,i,j);
            }
        }

        return mNew;
    }
}
