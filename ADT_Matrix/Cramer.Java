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


    public static float[] SPLCramer (Matrix m) {
        if (Cofactor.cofactorDeterminant(Matrix.coefficientMatrix((m))) == 0) {
            float[] n = {};
            return n;
        } else {
            int i;
            int last = Matrix.getLastIdxRow(m);
            float[] solution = new float[last+1];
            for (i=0;i<=last;i++) {
                solution[i] = Cofactor.cofactorDeterminant(replaceColumnWithConstant(m,i))/Cofactor.cofactorDeterminant(Matrix.coefficientMatrix(m));        
            }
            return solution;
        }
    }
}