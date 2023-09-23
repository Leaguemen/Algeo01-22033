package ADT_Matrix;
import java.util.Scanner;


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
}
