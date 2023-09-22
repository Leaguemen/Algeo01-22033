import java.util.Scanner;

import javax.swing.text.html.parser.Element; 
public class Matrix {
    final static int ROW_CAP = 100;
    final static int COL_CAP = 100;

    public float[][] memory;
    public int rowEff;
    public int colEff;
    
    //constructor
    public Matrix(int rowEff,int colEff){
        this.rowEff = rowEff;
        this.colEff = colEff;
        this.memory = new float[ROW_CAP][COL_CAP];
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


    public static boolean isIdxEff(Matrix m, int i, int j) {
        return (i <= getLastIdxRow(m) && j <= getLastIdxCol(m));
    }

    public static void copyMatrix(Matrix mIn, Matrix mOut) {
        mOut.rowEff = mIn.rowEff;
        mOut.colEff = mIn.colEff;

        int i, j;
        for (i = 0; i < mOut.rowEff; i++) {
            for (j = 0; j < mIn.rowEff; j++) {
                mOut.memory[i][j] = mIn.memory[i][j];
            }
        }
    }

    public static Matrix subtractMatrix(Matrix m1, Matrix m2) {
        int i, j;
        
        for (i = 0; i <= getLastIdxRow(m2); i++) {
            for (j = 0; j <= getLastIdxCol(m2); j++) {
                m1.memory[i][j] -= m2.memory[i][j];
            }
        }
        return m1;

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

    public static Matrix multiplyMatrix(Matrix m1, Matrix m2) {
        Matrix m = new Matrix(m1.rowEff, m2.colEff);
        int i, j, k;

        for (i = 0; i <= getLastIdxRow(m); i++) {
            for (j = 0; j <= getLastIdxCol(m); j++) {
                m.memory[i][j] = 0;
                for (k = 0; k <= getLastIdxCol(m1); k++) {
                    m.memory[i][j] += m1.memory[i][k]*m2.memory[k][j];
                }
            }
        }
        return m;
    }

    public static Matrix multiplyMatrixWithMod(Matrix m1,Matrix m2,int mod) {
        Matrix m = new Matrix(m1.rowEff, m2.colEff);
        int i, j, k;

        for (i = 0; i <= getLastIdxRow(m); i++) {
            for (j = 0; j <= getLastIdxCol(m); j++) {
                m.memory[i][j] = 0;
                for (k = 0; k <= getLastIdxCol(m1); k++) {
                    m.memory[i][j] += m1.memory[i][k]*m2.memory[k][j];
                }
                m.memory[i][j] %= mod;
            }
        }
        return m;
    }

    public static boolean isMatrixEqual(Matrix m1, Matrix m2) {
        boolean sama = false;
        int i = 0;
        int j;
        if (m1.rowEff == m2.rowEff && m1.colEff == m2.colEff) {
            sama = true;
            while (sama && i <= getLastIdxRow(m2)) {
                j = 0;
                while (sama && j <= getLastIdxCol(m2)) {
                    if (m1.memory[i][j] != m2.memory[i][j]) {
                        sama = false;
                    }
                    j++;
                }
                i++;
            }
        }
        return sama;
    }

    public static boolean isMatrixNotEqual(Matrix m1, Matrix m2){
        return !isMatrixEqual(m1,m2);
    }

    public static boolean isSquare(Matrix m){ //bryan
        return (m.colEff == m.rowEff);
    }

    public static boolean isSymmetric(Matrix m) {
        boolean simetri = false;
        int i, j;

        if (isSquare(m)) {
            simetri = true;
            i = 0;
            while (simetri && i < getLastIdxRow(m)) {
                j = i+1;
                while (simetri && j <= getLastIdxCol(m)) {
                    if (m.memory[i][j] != m.memory[j][i]) {
                        simetri = false;
                    }
                }
            }
        }
        return simetri;
    }

    public static Matrix transpose(Matrix m) {
        Matrix mNew = new Matrix(m.colEff, m.rowEff);
        int i, j;

        for (i = 0; i <= getLastIdxRow(mNew); i++) {
            for (j = 0; j <= getLastIdxCol(mNew); j++) {
                mNew.memory[i][j] = m.memory[j][i];
            }
        }
        return mNew;
    }

    public static void pTranspose(Matrix m) {
        int i, j;
        float temp;

        for (i = 0; i <= getLastIdxRow(m); i++) {
            for (j = i+1; j <= getLastIdxCol(m); j++) {
                temp = m.memory[i][j];
                m.memory[i][j] = m.memory[j][i];
                m.memory[j][i] = temp;
            } 
        }
    }
}
//m.readmatrix
