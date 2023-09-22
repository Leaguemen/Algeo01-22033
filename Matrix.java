import java.util.Scanner; 
public class Matrix {
    final static int ROW_CAP = 100;
    final static int COL_CAP = 100;

    public int[][] memory;
    public int rowEff;
    public int colEff;
    
    //constructor
    public Matrix(int rowEff,int colEff){
        this.rowEff = rowEff;
        this.colEff = colEff;
        this.memory = new int[ROW_CAP][COL_CAP];
    }

    //Methods
    public static void readMatrix(Matrix m,int nRow, int nCol){
        try(Scanner input = new Scanner(System.in)){
            for(int i =0; i < m.rowEff;i++){
                for(int j = 0; j< m.colEff;j++){
                    m.memory[i][j] = input.nextInt();
                }
            }
        }
    }

    public static int getLastIdxRow(Matrix m){
        return(m.rowEff-1);
    }

    public static int getLastIdxCol(Matrix m){
        return(m.colEff-1);
    }

    public static void displayMatrix(Matrix m){
        for(int i = 0; i<m.rowEff;i++){
            for(int j = 0; j<m.colEff;j++){
                if(j == getLastIdxCol(m)){
                    System.out.println(m.memory[i][j]);
                }
                else{
                    System.out.print(m.memory[i][j]+" ");
                }
            }
        }
    }

    public static boolean isMatrixValid(int i ,int j){
        return(i<ROW_CAP && j< COL_CAP && i >= 0 && j>= 0);
    }

    public static int getElmtDiagonal(Matrix m, int i){
        try{
            return(m.memory[i][i]);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.err.println("i dan j harus efektif");
            return(-1);
        }
    }

    /* Prekondisi : m1 berukuran sama dengan m2 */
    public static Matrix addMatrix(Matrix m, Matrix n){
        Matrix result = new Matrix(m.rowEff, m.colEff);
        for(int i = 0;i< m.rowEff;i++){
            for(int j =0; j<m.colEff;j++){
                result.memory[i][j] = m.memory[i][j]+n.memory[i][j];
            }
        }
        return result;
    }

    public static Matrix multiplyByConst(Matrix m, int x){
        Matrix result = new Matrix(m.rowEff,m.colEff);
        for(int i = 0;i< m.rowEff;i++){
            for(int j =0; j<m.colEff;j++){
                result.memory[i][j] = m.memory[i][j]*x;
            }
        }
        return(result);
    }

    public static void pMultiplyByConst(Matrix m, int k){
        for(int i = 0;i< m.rowEff;i++){
            for(int j =0; j<m.colEff;j++){
                m.memory[i][j] = m.memory[i][j]*k;
            }
        }       
    }

    public static boolean isMatrixSizeEqual(Matrix m, Matrix n){
        return((m.rowEff==n.rowEff)&&(m.colEff==n.colEff));
    }

    public static int countElmt(Matrix m){
        return m.rowEff*m.colEff;
    }

    public static boolean isIdentity(m){
        if(m.rowEff == m.colEff){
            Matrix identity = new Matrix(m.rowEff,m.colEff);
            for(int i=0;i<m.rowEff;i++){
                for(int j=0;j<m.colEff;j++){
                    if(i == j){
                        identity.memory[i][j]=1;
                    }
                }
            }
            return isMatrixEqual(m,identity);
        }
        return(false);
    }
    public static void main(String[] args){
        int b;
        Matrix m = new Matrix(3, 3);
        readMatrix(m,3,3);
        b = getElmtDiagonal(m, 101);
        System.out.println(b);
        displayMatrix(m);
    }

}
//m.readmatrix
