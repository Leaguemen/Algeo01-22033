import java.util.Scanner; 
public class Matrix {
    final int ROW_CAP = 100;
    final int COL_CAP = 100;

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

    public static void main(String[] args){
        Matrix m = new Matrix(3, 3);
        readMatrix(m,3,3);
        displayMatrix(m);
    }

}
