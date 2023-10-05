package ADT_Matrix;


public class GaussJordan {
    public static boolean isHomogen(Matrix m){
        for(int i = 0; i<m.rowEff;i++){
            if(m.memory[i][m.colEff-1]!=0){
                return false;
            }
        }
        return true;
    }

    public static void echelontoEchelonBaris(Matrix m){
        //prekondisi: m adalah matrix echelon
        for(int i =m.rowEff-2;i>=0;i--){
            int idx = m.colEff-2;
            for(int j = m.rowEff-1;j>i;j--){
                Matrix.AddRowByRow(m, i, j, -1*(m.memory[i][idx]));
                idx--;
            }
        }   
    }

    public static float[] SPLGaussJordan(Matrix m){
        if(isHomogen(m)){
            float[] trivialSolution = new float[m.rowEff];
            //System.out.println("homo");
            Gauss.makeEchelon(m);
            //Matrix.displayMatrix(m);
            //System.out.println("homo");
            if(Gauss.noSolution(m)){
                System.out.println("Solusi trivial :");
                for(int i =1; i<=m.rowEff;i++){
                trivialSolution[i-1]=0;
                System.out.println("X"+Integer.toString(i)+ " = 0");
                }
                return trivialSolution;
            }
            else{
                System.out.println("BANYAK SOLUSI");
                echelontoEchelonBaris(m);
                for(int i =1; i<=m.rowEff;i++){
                trivialSolution[i-1]=0;
                }
                String[] solutions= new String[m.colEff-1];
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
                //set beberapa unknow as parameter
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
                // System.out.println(parameterAmmount);
                for(int i =0;i<m.rowEff-parameterAmmount;i++){
                    for(int j =0;j<m.colEff-1;j++){
                        if(m.memory[i][j] != 0 && solutions[j] ==null){
                            if(m.memory[i][m.colEff-1] == 0){
                                String holder = "(" ;           
                                for(int k =0; k<m.colEff-1;k++){
                                    if(m.memory[i][k]!= 0 && solutions[k]!= null){
                                        holder+="-1" + solutions[k];
                                    }
                                }
                                holder +=")";
                                holder +="/"+ Float.toString(m.memory[i][j]);
                                solutions[j] = holder;
                            }
                            else{
                                String holder = "(" + Float.toString(m.memory[i][m.colEff-1]);
                                for(int k =0; k<m.colEff-1;k++){
                                    if(m.memory[i][k]!= 0 && solutions[k]!= null){
                                        holder+="-1" + solutions[k];
                                    }
                                }
                                holder +=")";
                                holder +="/"+ Float.toString(m.memory[i][j]);
                                solutions[j] = holder;
                            }
                        }
                    }
                }
                return trivialSolution;               
            }

        }
        else{
            Gauss.makeEchelon(m);
            if(Gauss.uniqueSolution(m)){
                float[] solution = new float[m.rowEff];
                System.out.println("unique");
                echelontoEchelonBaris(m);
                for(int i =0;i<m.rowEff;i++){
                    solution[i]=m.memory[i][m.colEff-1];
                } 
                return solution;
            }
            else if(Gauss.noSolution(m)){
                System.out.println("TIDAK ADA SOLUSI");
                float[] keluar = new float[1];
                return keluar;
            }
            else{
                System.out.println("BANYAK SOLUSI");
                echelontoEchelonBaris(m);
                String[] solutions= new String[m.colEff-1];
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
                //set beberapa unknow as parameter
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
                System.out.println(parameterAmmount);
                for(int i =0;i<m.rowEff-parameterAmmount;i++){
                    for(int j =0;j<m.colEff-1;j++){
                        if(m.memory[i][j] != 0 && solutions[j] ==null){
                            if(m.memory[i][m.colEff-1] == 0){
                                String holder = "(" ;           
                                for(int k =0; k<m.colEff-1;k++){
                                    if(m.memory[i][k]!= 0 && solutions[k]!= null){
                                        holder+="-1" + solutions[k];
                                    }
                                }
                                holder +=")";
                                holder +="/"+ Float.toString(m.memory[i][j]);
                                solutions[j] = holder;
                            }
                            else{
                                String holder = "(" + Float.toString(m.memory[i][m.colEff-1]);
                                for(int k =0; k<m.colEff-1;k++){
                                    if(m.memory[i][k]!= 0 && solutions[k]!= null){
                                        holder+="-1" + solutions[k];
                                    }
                                }
                                holder +=")";
                                holder +="/"+ Float.toString(m.memory[i][j]);
                                solutions[j] = holder;
                            }
                        }
                    }
                }
                Gauss.printArrayNeatly(solutions);
                System.out.println("\n");
                float[] keluar = new float[1];
                return keluar;
            }
            
        }
    }

    public static String stringSPLGaussJordan(Matrix m){
        if(isHomogen(m)){
            float[] trivialSolution = new float[m.rowEff];
            //System.out.println("homo");
            Gauss.makeEchelon(m);
            //Matrix.displayMatrix(m);
            //System.out.println("homo");
            if(Gauss.noSolution(m)){
                System.out.println("Solusi trivial :");
                for(int i =1; i<=m.rowEff;i++){
                trivialSolution[i-1]=0;
                System.out.println("X"+Integer.toString(i)+ " = 0");
                }
                return "trivial solution";
            }
            else{
                System.out.println("BANYAK SOLUSI");
                echelontoEchelonBaris(m);
                for(int i =1; i<=m.rowEff;i++){
                trivialSolution[i-1]=0;
                }
                String[] solutions= new String[m.colEff-1];
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
                //set beberapa unknow as parameter
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
                // System.out.println(parameterAmmount);
                for(int i =0;i<m.rowEff-parameterAmmount;i++){
                    for(int j =0;j<m.colEff-1;j++){
                        if(m.memory[i][j] != 0 && solutions[j] ==null){
                            if(m.memory[i][m.colEff-1] == 0){
                                String holder = "(" ;           
                                for(int k =0; k<m.colEff-1;k++){
                                    if(m.memory[i][k]!= 0 && solutions[k]!= null){
                                        holder+="-1" + solutions[k];
                                    }
                                }
                                holder +=")";
                                holder +="/"+ Float.toString(m.memory[i][j]);
                                solutions[j] = holder;
                            }
                            else{
                                String holder = "(" + Float.toString(m.memory[i][m.colEff-1]);
                                for(int k =0; k<m.colEff-1;k++){
                                    if(m.memory[i][k]!= 0 && solutions[k]!= null){
                                        holder+="-1" + solutions[k];
                                    }
                                }
                                holder +=")";
                                holder +="/"+ Float.toString(m.memory[i][j]);
                                solutions[j] = holder;
                            }
                        }
                    }
                }
                return "trivial solution";               
            }

        }
        else{
            // System.out.println("non homo");
            Gauss.makeEchelon(m);
            if(Gauss.uniqueSolution(m)){
                float[] solution = new float[m.rowEff];
                for(int i =0;i<m.rowEff;i++){
                    solution[i]=m.memory[i][m.colEff-1];
                }
                System.out.println("unique");
                echelontoEchelonBaris(m);
                String solutionString ="";
                for(int i =0; i< m.rowEff;i++){
                    solutionString += "x" + (i+1) + " = " + Float.toString(solution[i]) + "\n";
                }
                return solutionString;
            }
            else if(Gauss.noSolution(m)){
                System.out.println("TIDAK ADA SOLUSI");
                return "TIDAK ADA SOLUSI";
            }
            else{
                System.out.println("BANYAK SOLUSI");
                echelontoEchelonBaris(m);
                String[] solutions= new String[m.colEff-1];
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
                //set beberapa unknow as parameter
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
                System.out.println(parameterAmmount);
                for(int i =0;i<m.rowEff-parameterAmmount;i++){
                    for(int j =0;j<m.colEff-1;j++){
                        if(m.memory[i][j] != 0 && solutions[j] ==null){
                            if(m.memory[i][m.colEff-1] == 0){
                                String holder = "(" ;           
                                for(int k =0; k<m.colEff-1;k++){
                                    if(m.memory[i][k]!= 0 && solutions[k]!= null){
                                        holder+="-1" + solutions[k];
                                    }
                                }
                                holder +=")";
                                holder +="/"+ Float.toString(m.memory[i][j]);
                                solutions[j] = holder;
                            }
                            else{
                                String holder = "(" + Float.toString(m.memory[i][m.colEff-1]);
                                for(int k =0; k<m.colEff-1;k++){
                                    if(m.memory[i][k]!= 0 && solutions[k]!= null){
                                        holder+="-1" + solutions[k];
                                    }
                                }
                                holder +=")";
                                holder +="/"+ Float.toString(m.memory[i][j]);
                                solutions[j] = holder;
                            }
                        }
                    }
                }
                Gauss.printArrayNeatly(solutions);
                System.out.println("\n");
                String solutionString ="";
                for(int i =0; i< m.rowEff;i++){
                    solutionString += "x" + (i+1) + " = " + solutions[i] + "\n";
                }
                return solutionString;
            }
            
        }
    }

    // public static void main(String[] args){
    //     Matrix m = new Matrix(4, 5);
    //     Matrix.readMatrix(m,4,5);
    //     SPLGaussJordan(m); 
    //     Matrix.displayMatrix(m);   
    // }
}
