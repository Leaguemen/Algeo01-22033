import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

import javax.print.DocFlavor.STRING;

import ADT_Matrix.*;

public class WriteToFile {

    public static String MatrixtoString(Matrix m){
      String holder="";
      for(int i =0;i<m.rowEff;i++){
        for(int j=0;j<m.colEff;j++){
          holder += Float.toString(m.memory[i][j]);
          holder += " ";
        }
        holder += "\n";
      }
      return holder;
    }

    public static String ArrayofStringtoString(float[] lis){
      String holder="";
      for(int i =0; i<lis.length;i++){
        holder+= Float.toString(lis[i]);
        if (i != lis.length - 1) {
          holder += " ";
        }
      }
      return holder;
    }

   public static void writeFile(String x,String fileName){
        try {
      File fileDirectory = new File("destination\\" + fileName);
      if (!fileDirectory.exists()){
        fileDirectory.createNewFile();
      }
      FileWriter myWriter = new FileWriter(fileDirectory);
      myWriter.write(x);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
   } 
//   public static void main(String[] args) {
//         Matrix sean = new Matrix(3, 3);
//         Matrix.readMatrix(sean, 3, 3);
//         System.out.println(MatrixtoString(sean));
//     }
}