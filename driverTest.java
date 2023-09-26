import ADT_Matrix.Invers;
import ADT_Matrix.Matrix;
import ADT_Matrix.Cofactor;;

public class driverTest {
        public static void main(String[] args) throws Exception{
        // Matrix m = new Matrix(3, 3);
        // Matrix.readMatrix(m,3,3);
        // Matrix.displayMatrix(m);
        // ADT_Matrix.Matrix m = new ADT_Matrix.Matrix(0, 0);
        // String fileName = "*";

        // fileName = ADT_Matrix.ReadFile.pilihFile(fileName);
        // m = ADT_Matrix.ReadFile.parseFile(m,fileName);
        // ADT_Matrix.Matrix.displayMatrix(m);
        // System.out.println(m.colEff);
        // System.out.println(m.rowEff);

        // Cobain invers
        // Matrix m = new Matrix(3, 4);
        // Matrix.readMatrix(m, 3, 4);
        // Invers.SolusiSPLDenganInvers(m);

        // Cobain Kofaktor
        Matrix m1;
        Matrix m = new Matrix(3, 3);
        Matrix.readMatrix(m,3,3);
        Matrix.displayMatrix(m);
        System.out.println(Cofactor.CofactorDeterminant(m));
        System.out.println(Cofactor.EntryCofactor(m, 3, 3));
        m1 = Cofactor.CofactorMatrix(m);
        Matrix.displayMatrix(m1);
    }
}
