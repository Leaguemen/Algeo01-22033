package ADT_Matrix;

import java.io.*;
import java.util.*;


public class ReadFile {

    public static int countLine (String namaFile) throws Exception{
        File file = new File(namaFile);
        int n = 0;
        BufferedReader br = new BufferedReader(new FileReader(file));

        while (br.readLine() != null) {
            n++;
        }
        br.close();
        return n;
    }
    
    public static String pilihFile (String namaFile) {
        /* I.S : namaFile sembarang */
        /* F.S : namaFile terdefinisi */
        boolean isFileExist = false;
        Scanner in = new Scanner (System.in);
        while (!isFileExist) {
            System.out.print("Masukkan nama file : ");
            namaFile = in.nextLine();
            String currentDirectory = System.getProperty("user.dir");
            File directory = new File(currentDirectory);

            for (File file : directory.listFiles()) {
                if (file.isFile() && file.getName().equals(namaFile)) {
                    isFileExist = true;
                }
            }
            if (!isFileExist) {
                System.out.println("File tidak ditemukan.");
            }
        }
        in.close();
        return namaFile;
    }

    public static Matrix parseFile (Matrix m, String nama_file) throws Exception {
        File file = new File(nama_file);
        int i; int k = 0;
        int j; 
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        String temp;

        while ((line = br.readLine()) != null) {
            temp = "";
            j = 0;
            for (i = 0; i < line.length(); i++) {
                if (line.charAt(i) != ' ') {
                    temp += line.charAt(i);
                } else {
                    m.memory[k][j] = Float.parseFloat(temp);
                    temp = "";
                    j++;
                }

                if (i == (line.length()-1)) {
                    m.memory[k][j] = Float.parseFloat(temp);
                    temp = "";
                    j++;
                }
            m.colEff = j;
            }
            k++;
        }
        m.rowEff = k;
        return m;
    }

    public static Matrix readMatrixFromFile (String namaFile) throws Exception {
        File file = new File(namaFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String temp = "";
        int lineLenght = countLine(namaFile);
        Matrix m = new Matrix(lineLenght-1, 0);
        int i;
        int j;

        for (i = 0; i < m.rowEff; i++) {
            temp = "";
            line = br.readLine();
            int k = 0;
            for (j = 0; j < line.length(); j++) {
                if (line.charAt(j) != ' ') {
                    temp += line.charAt(j);
                } else {
                    m.memory[i][k] = Float.parseFloat(temp);
                    temp = "";
                    k++;
                }

                if (j == (line.length()-1)) {
                    m.memory[i][k] = Float.parseFloat(temp);
                    temp = "";
                    k++;
                }
            m.colEff = k;
            }
        }
        br.close();
        return m;
    }

    public static Float[] readBottomLine (String namaFile) throws Exception{
        File file = new File(namaFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String temp = "";
        int lineLenght = countLine(namaFile);
        int i; int j; int k =0;
        Float array[] = new Float[10];

        for (i = 0; i < lineLenght; i++) {
            line = br.readLine();
            if (i == lineLenght-1) {
                for (j = 0; j < line.length(); j++) {
                    if (line.charAt(j) != ' ') {
                        temp += line.charAt(j);
                    } else {
                        array[k] = Float.parseFloat(temp);
                        temp = "";
                        k++;
                    }

                    if (j == line.length()-1) {
                        array[k] = Float.parseFloat(temp);
                    }
                }
            }
        }
        br.close();
        return array;
    }
}