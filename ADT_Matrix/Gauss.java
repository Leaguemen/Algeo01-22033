package ADT_Matrix;

public class Gauss {

    //menghitung banyaknya angka 0 dalam sebuah row
    public static int countZero(int x,Matrix m){
        int result = 0;
        for(int i=0;i<m.colEff;i++){
            if(m.memory[x][i]==0){
                result +=1;
            }
        }
        return(result);
    }

    /* 0 0 1 2      1 2 3 4
     * 1 2 3 4 -->  0 1 2 3
     * 0 1 2 3      0 0 1 2
     */


    public static void zeroDown(Matrix m){
        int x=0;
        for(int i =0;i<m.rowEff-1;i++){
            for(int j=i+1;j<m.rowEff-1;j++){
                if(m.memory[j][0]!=0){
                    Matrix.Swap(m, j, i, x);
                }
            }
        }
    }

    public static int firstNonZeroIdx(Matrix m, int row){
                int j=0; 
                while(m.memory[row][j]==0 && j< m.rowEff){//nyari elemen pertama yang tidak nol dalam sebuah row
                    j++;
                    if(m.memory[row][j]!=0){
                        break;
                    }
                }
                return(j);
    }
    public static void gauss(Matrix m){
        int flagAllZero = 0;
        for(int i =0; i<m.rowEff;i++){
            if(m.memory[i][0]==0){
                flagAllZero+=1;
            }
        }
        System.out.println(flagAllZero);
        if(flagAllZero<m.rowEff){
            for(int i=0;i<m.rowEff;i++){
                // membuat yg atas elemen kiri atas menjadi leading one
                Matrix.MultiplyRow(m, i, 1/m.memory[i][firstNonZeroIdx(m, i)]);
                if(i != m.rowEff-1){
                    int idxRow =0;
                    for(int k=i;k>=0;k--){
                    //mengurangi row selanjutnya agar elemen paling kiri dari row selanjutnya hasilnya 0
                    Matrix.AddRowByRow(m,i+1, idxRow, (float)-1*(m.memory[i+1][firstNonZeroIdx(m, i+1)]));
                    idxRow++;
                    }

                }
            }
            Matrix.displayMatrix(m);

        }
        else{
            //salah satu kondisi banyak
        }
    }

    public static void main(String[] args){
        Matrix m = new Matrix(3, 4);
        Matrix.readMatrix(m,3,4);
        gauss(m);
    }

}
