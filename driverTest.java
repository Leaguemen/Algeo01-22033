import ADT_Matrix.Invers;
import ADT_Matrix.Matrix;

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
        Matrix m = new Matrix(3, 4);
        Matrix.readMatrix(m, 3, 4);
        Invers.SolusiSPLDenganInvers(m);
    }
}
