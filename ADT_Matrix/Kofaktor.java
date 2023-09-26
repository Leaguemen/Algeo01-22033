package ADT_Matrix;
import java.lang.Math;

public class Kofaktor {
    public static float CofactorDeterminant (Matrix m) {
    /* Prekondisi: isSquare(m) */
    /* Menghitung nilai determinan m */
    // KAMUS
        float det = 0;
        // ALGORITMA
        if (Matrix.countElmt(m) == 1) {
            return m.memory[0][0];
        } else if (Matrix.countElmt(m) == 4) {
            return (m.memory[0][0]*m.memory[1][1] - m.memory[1][0]*m.memory[0][1]);
        } else {
            int j, last;
            last = Matrix.getLastIdxCol(m);
            for (j=0;j<=last;j++) {
                Matrix m1 = new Matrix(last,last);
                int i,k;
                for (k=0;k<j;k++) {
                    for (i=1;i<=last;i++); {
                        m1.memory[i][k] = m.memory[i][k];
                    }
                }
                for (k=j;k<last;k++) {
                    for (i=1;i<=last;i++) {
                        m1.memory[i][k] = m.memory[i][k+1];
                    }
                }
                det += (Math.pow(-1,j) * m.memory[0][j] * DeterminanKofaktor(m1));
            }
        }
        return det;
    }

    public static float EntryCofactor (Matrix m, int idxRow, int idxCol) {
        /* Menghasilkan kofaktor entri matriks dengan indeks idxRow dan idxCol */
        Matrix mNew = new Matrix(m.rowEff-1, m.colEff-1);
        int last, i, j, k, l;

        i = 0; k=0;
        last = Matrix.getLastIdxCol(m);
        while (i<last) {
            j = 0; l=0;
            while (j<last) {
                if (i==idxRow) {
                    i++;
                }
                
                if (j==idxCol) {
                    j++;
                }

                if (i<last && j<last) {
                    mNew.memory[k][l] = m.memory[i][j];
                }
                j++;
                l++;
            }
            i++;
            k++;
        }
        return (float) Math.pow(-1,idxRow+idxCol) * CofactorDeterminant(mNew);
    }

    public static Matrix CofactorMatrix (Matrix m) {
        int i = 0; int j = 0;
        int last = Matrix.getLastIdxRow(m);
        Matrix mNew = new Matrix(last, last);
        for (i=0;i<=last;i++) {
            for (j=0;j<=last;j++) {
                mNew.memory[i][j] = (float) EntryCofactor(m,i,j);
            }
        }

        return mNew;
    }
}
