import ADT_Matrix.Matrix;
public class driverTest {
        public static void main(String[] args){
        Matrix m = new Matrix(3, 3);
        Matrix.readMatrix(m,3,3);
        Matrix.displayMatrix(m);

    }
}
