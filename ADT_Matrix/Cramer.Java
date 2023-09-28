package ADT_Matrix;

public class Cramer {
    public static Matrix replaceColumnWithConstant(Matrix m, int idxCol) {
        int lastRow = Matrix.getLastIdxRow(m);
        int lastCol= Matrix.getLastIdxCol(m);
        Matrix mNew = new Matrix(lastRow+1,lastRow+1);
        mNew = Matrix.coefficientMatrix(m);

        int i;
        for (i=0;i<=lastRow;i++) {
            mNew.memory[i][idxCol] = m.memory[i][lastCol];
        }
        return mNew;
    }


    public static void SPLCramer (Matrix m) {
        if (Cofactor.cofactorDeterminant(Matrix.coefficientMatrix((m))) == 0) {
            System.out.println("Solusi SPL tidak dapat ditemukan dengan metode Cramer.");
        } else {
            int i;
            int last = Matrix.getLastIdxRow(m);
            for (i=0;i<=last;i++) {
                float solution = Cofactor.cofactorDeterminant(replaceColumnWithConstant(m,i))/Cofactor.cofactorDeterminant(Matrix.coefficientMatrix(m));
                System.out.print("x" + (i+1) + " = " + solution);
                if (i!=last) {
                    System.out.print(", ");
                }
        
            }
            System.out.print("\n");
        }
    }
}