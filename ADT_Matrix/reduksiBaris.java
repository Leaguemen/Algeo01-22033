package ADT_Matrix;

public class reduksiBaris{

    public static float getDeterminant(Matrix m){
        float result = 1;
        for(int i=0;i<m.rowEff;i++){
            // membuat yg atas elemen kiri atas menjadi leading one
            result *= m.memory[i][Gauss.firstNonZeroIdx(m, i)];
            Matrix.MultiplyRow(m, i, 1/m.memory[i][Gauss.firstNonZeroIdx(m, i)]);
            // System.out.println(i);
            if(i != m.rowEff-1){
                int idxRow =0;
                for(int k=i;k>=0;k--){
                //mengurangi row selanjutnya agar elemen paling kiri dari row selanjutnya hasilnya 0
                if(m.memory[i+1][idxRow]!=0){
                    Matrix.AddRowByRow(m,i+1, idxRow, (float)-1*(m.memory[i+1][Gauss.firstNonZeroIdx(m, i+1)]));
                }
                idxRow++;
                // Matrix.displayMatrix(m);
                // System.out.println("\n");
                }
            } 
            // System.out.println("\n");
        }
        
        int dummy=0;
        for(int i=0;i<m.rowEff;i++){
            for(int j=i+1;j<m.rowEff;j++){
                if(m.memory[j][Gauss.firstNonZeroIdx(m, i)]!=0){
                    Matrix.Swap(m, j, i, dummy);
                    result *= -1;
                }
            }
        }   
        return(result);
    }

    // public static void main(String[] args){
    //     Matrix m = new Matrix(3, 3);
    //     Matrix.readMatrix(m,3,3);
    //     System.out.println(getDeterminant(m));
    // }
    
}