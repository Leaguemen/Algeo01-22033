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

    public static boolean uniqueSolution(Matrix m){
        return(m.memory[m.rowEff-1][m.colEff-2]!=0);
    }

    public static boolean infiniteSolution(Matrix m){
        for(int i=0;i<m.rowEff;i++){
            int countZero =0;
            for(int j=0;i<m.colEff;j++){
                if (m.memory[i][j]==0){
                    countZero++;
                }
            }
            if(countZero == m.colEff){
                return true;
            }
        }
        return false;
    }

    public static boolean noSolution(Matrix m){
        int zero =0;
        for(int i=0;i<m.colEff;i++){
            if(m.memory[m.rowEff-1][i]==0){
                zero+=1;
            }
        }

        if(zero ==m.colEff-1 && m.memory[m.rowEff-1][m.colEff-1]!= 0.0){
            return true;
        }
        return false;
    }

    public static Matrix makeSubMatrix(Matrix m, int parameterAmmount){
        Matrix result = new Matrix(m.rowEff-parameterAmmount,m.colEff-parameterAmmount);
        int originalRow =0;
        for(int i=0;i<m.rowEff-parameterAmmount;i++){
            int originalCol =0;
            for(int j=parameterAmmount;j<m.colEff;j++){
                result.memory[i][j] = m.memory[originalRow][originalCol];
                originalCol++;
            }
            originalRow++;
        }
        return result;
    }
    public static void makeEchelon(Matrix m){
        for(int i=0;i<m.rowEff;i++){
            // membuat yg atas elemen kiri atas menjadi leading one
            if(m.memory[i][firstNonZeroIdx(m, i)]!=0){
            Matrix.MultiplyRow(m, i, 1/m.memory[i][firstNonZeroIdx(m, i)]);}
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
                if(m.memory[i][firstNonZeroIdx(m, i)]!=0){
                Matrix.MultiplyRow(m, i, 1/m.memory[i][firstNonZeroIdx(m, i)]);}
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
            if(uniqueSolution(m)){
                //Backward substitution
                float[] solutions = new float[m.colEff-1];
                for(int i=m.rowEff-1; i>=0;i--){
                    if(i ==m.rowEff-1){ //kalau baris terakhir
                            solutions[i]= m.memory[i][m.colEff-1];
                        }
                    else{
                        int idx=m.colEff-2;
                        float result = m.memory[i][m.colEff-1];
                        while(idx!=firstNonZeroIdx(m, i)){
                            result -= m.memory[i][idx]*solutions[idx];
                            idx--;
                        }
                        solutions[i]=result;
                    }
                }
                for(int i=1;i<=m.rowEff;i++){
                    System.out.println(String.format("X%d = %f",i,solutions[i-1]));
                }
            }
            else if(noSolution(m)){
                System.out.println("TIDAK ADA SOLUSI");
            }
            else{
                System.out.println("BANYAK SOLUSI");
                String[] solutions= new String[m.colEff-1];
                //Cek adakah unknow yang bisa langsung diketahui hasilnya
                for(int i=0;i<m.rowEff;i++){
                    int ctr =0;
                    for(int j=0;j<m.colEff-1;j++){
                        if(m.memory[i][j]!=0){
                            ctr++;
                        }
                    }
                    if(ctr == 1){
                        solutions[i]= Float.toString((m.memory[i][m.colEff-1])/(m.memory[i][firstNonZeroIdx(m, i)]));
                    }
                }
                //tentukan berapa parameter yang diperlukan
                int parameterAmmount =0;
                for(int i=0;i<m.rowEff;i++){
                    int ctr =0;
                    for(int j=0;j<m.colEff;j++){
                        if(m.memory[i][j]==0){
                            ctr++;
                        }
                    }
                    if(ctr == m.colEff){
                        parameterAmmount++;
                    }
                }
                System.out.println("Banyak parameter :"+ Integer.toString(parameterAmmount));
                //set beberapa unknow as parameter dengan syarat jangan nimpa yang sudah diketahui
                int parameterNumber = 1;
                String parameterVariabel = "P";
                int asciiChange = 0;
                int iterate =0;
                while(asciiChange<parameterAmmount){
                    if(solutions[iterate]==null){
                        solutions[iterate] = parameterVariabel + Integer.toString(parameterNumber);
                        parameterNumber++;
                        asciiChange++;
                    }
                    iterate++;
                }
                //lihat dari matrix
                for(int i=0;i<m.rowEff;i++){
                    //Checks how much non zero element in a row
                    int ctr =0;
                    for(int j=0;j<m.colEff-1;j++){
                        if(m.memory[i][j]!=0){
                            ctr++;
                        }
                    }
                    // Makes the solution
                    if(ctr >1&& solutions[i]==null){
                        String result = Float.toString(m.memory[i][m.colEff-1]);
                        for(int j=m.colEff-2;j>=0;j--){
                            if(solutions[m.colEff-2-j]!= null){
                                result += "-" + solutions[m.colEff-2-j];
                            }
                            else{ // perlu diperbaiki
                                result += Float.toString(m.memory[i][j]);
                            }
                        }
                        solutions[i]= result;
                    }
            }
            for(int i=1;i<=m.rowEff;i++){
                System.out.println(String.format("X%d = %s",i,solutions[i-1]));
            }
        }
    }
    }

    public static void main(String[] args){
        Matrix m = new Matrix(3, 4);
        Matrix.readMatrix(m,3,4);
        gauss(m);
    }

}
