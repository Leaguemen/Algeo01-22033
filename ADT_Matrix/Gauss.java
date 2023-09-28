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

    public static boolean containsString(String[] stringArray, String targetString) {
        for(int i =0; i<stringArray.length;i++){
            if(stringArray[i]==targetString){
                return(true);
            }
            else if(stringArray[i]== null){
                return(false);
            }
        }
        return false;
    }

    public static Matrix makeSubMatrix(Matrix m, int parameterAmmount){
        Matrix result = new Matrix(m.rowEff-parameterAmmount,m.colEff-parameterAmmount);
        int originalRow =0;
        for(int i=0;i<m.rowEff-parameterAmmount;i++){
            int originalCol =parameterAmmount;
            for(int j=0;j<m.colEff-parameterAmmount;j++){
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
            if(m.memory[i+1][idxRow]!=0){
                Matrix.AddRowByRow(m,i+1, idxRow, (float)-1*(m.memory[i+1][firstNonZeroIdx(m, i+1)]));
            }
            idxRow++;
            }
        }  
        }
    //swap some irregularities
        int dummy=0;
    for(int i =0;i<m.rowEff;i++){
        for(int j=i+1;j<m.rowEff;j++){
            if(m.memory[j][firstNonZeroIdx(m, i)]!=0){
                Matrix.Swap(m, j, i, dummy);
            }
        }
    }  
    }


    public static void printArrayNeatly(String[] arr) {
        // Iterate through the array elements
        for (int i = 0; i < arr.length; i++) {
            // Print the current element
            System.out.print(arr[i]);

            // Add a comma and space for all elements except the last one
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
    }

    public static float[] backwardSubstition(Matrix m){
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
        return(solutions);
    }

    public static int findIndex(float[] arr, float target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Element found, return its index
            }
        }
        // Element not found
        return -1;
    }

    public static float safeStringToFloat(String str) {
        try {
            // Attempt to parse the string to a float
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            // Handle the exception by returning a default value (e.g., 0.0) or throwing an error
            System.err.println("Error parsing the string to float: " + str);
            return 0.0f; // You can change this to another default value if needed
        }
    }
    
    public static float[] gauss(Matrix m){
        int flagAllZero = 0;
        for(int i =0; i<m.rowEff;i++){
            if(m.memory[i][0]==0){
                flagAllZero+=1;
            }
        }
        //System.out.println(flagAllZero);
        if(flagAllZero<m.rowEff){
            makeEchelon(m);
            if(uniqueSolution(m)){
                System.out.println("SOLUSI UNIK");
                float[] solutions = backwardSubstition(m);
                for(int i=1;i<=m.rowEff;i++){
                    System.out.println(String.format("X%d = %f",i,solutions[i-1]));
                }
                return solutions;
            }
            else if(noSolution(m)){
                System.out.println("TIDAK ADA SOLUSI");
                float[] keluar = new float[1];
                return keluar;
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
                //System.out.println("Banyak parameter :"+ Integer.toString(parameterAmmount));
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
                Matrix newMatrix = makeSubMatrix(m, parameterAmmount);
                makeEchelon(newMatrix);
                float[] numberSolution = backwardSubstition(newMatrix);
                String[] actualSolution = new String[m.colEff-1];
                //printArrayNeatly(actualSolution);
                //System.out.println("\n");
                //System.out.println("\n");
                //populate actual solution
                for(int i =0;i<actualSolution.length;i++){
                    if(solutions[i]==null){
                        actualSolution[i]= Float.toString(numberSolution[i-parameterAmmount]);
                    }
                    else{
                        actualSolution[i]= solutions[i];
                    }
                }

               //System.out.println("current matrix");
               //Matrix.displayMatrix(m);
               //System.out.println("");
                for(int j = 0; j<actualSolution.length;j++){
                    if(!containsString(solutions, actualSolution[j])){
                        //System.out.println("idx yang masuk : "+ Integer.toString(j));
                        int idx =0;
                        //System.out.println("array parameter solutions : ");
                        printArrayNeatly(solutions);
                        //System.out.println("");
                        while(solutions[idx] != null){
                            if(m.memory[j-parameterAmmount][idx+findIndex(numberSolution,safeStringToFloat(actualSolution[j]))]>1){
                                //System.out.println("Halo memori > 1");
                                actualSolution[j] += "-" + Float.toString(m.memory[j-parameterAmmount][idx +findIndex(numberSolution,safeStringToFloat(actualSolution[j]))]) + solutions[idx];
                            }
                            else if(m.memory[j-parameterAmmount][idx+findIndex(numberSolution,safeStringToFloat(actualSolution[j]))]==1){
                                System.out.println("Halo memori == 1");
                                actualSolution[j] += "-" + solutions[idx];
                            } 
                        idx++;                         
                        }
                    }
                }

                System.out.println("jawaban");
                printArrayNeatly(actualSolution);
                System.out.println("\n");
                float[] keluar = new float[1];
                return keluar;
            }
        }
        float[] keluar = new float[1];
        return keluar;
        
    }

    // public static void main(String[] args){
    //     Matrix m = new Matrix(3, 4);
    //     Matrix.readMatrix(m,3,4);
    //     Matrix b =makeSubMatrix(m, 1);
    //     Matrix.displayMatrix(b);
    //     gauss(m);
    // }
}
