package ADT_Matrix;

import java.io.*;
import java.util.*;


public class ReadFile {
    
    public static String pilihFile (String namaFile) {
        /* I.S : namaFile sembarang */
        /* F.S : namaFile terdefinisi */
        boolean pilihFolder = true;
        int pilihan;
        String folder;

        while (pilihFolder) {
            Scanner in = new Scanner(System.in);
            System.out.println("Memasukkan nama folder atau file? (Masukkan angka)\n1. Folder\n2. File");
            pilihan = in.nextInt();
            
            if (pilihan == 1) {
                Scanner inp = new Scanner(System.in);
                System.out.print("Masukkan nama folder: ");
                folder = inp.nextLine();
                File f = new File(folder);

                if (f.exists() && f.isDirectory()) {
                    System.setProperty("user.dir", folder);
                } else {
                    System.out.println("Folder tidak ditemukan.");
                }
            } else if (pilihan == 2) { 
                Scanner input = new Scanner(System.in);
                String currentDirectory = System.getProperty("user.dir");
                File directory = new File(currentDirectory);
                System.out.print("Masukkan nama file: ");
                namaFile = input.nextLine();
                for (File file : directory.listFiles()) {
                    if (file.isFile() && file.getName().equals(namaFile)) {
                        pilihFolder = false;
                    }
                }
                
                if (pilihFolder) {
                    System.out.println("File tidak ditemukan.");
                }
            } else {
                System.out.println("Tidak ada pilihan tersebut");
            }
        }
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

    public static void readBicubicFile (String namaFile, Matrix m) throws Exception {
        File file = new File(namaFile);
        int i = 0;
        int j; int k;
        String line;
        String temp;
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((line = br.readLine()) != null) {
            temp = "";
            k = 0;
            if (i < 4) {
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
                }
                i++;
            }
        }
    }

    public static Float readX (String namaFile) throws Exception {
        File file = new File(namaFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String temp = "";
        float x = 0;
        int i = 0; int j;
        
        while ((line = br.readLine()) != null) {
            if (i == 4) {
                for (j = 0; j < line.length()-1; j++) {
                    if (line.charAt(j) != ' ') {
                        temp += line.charAt(j);
                    } else {
                        x = Float.parseFloat(temp);
                        break;
                    }
                }
            }
            i++;
        }

        return x;
    }

    public static Float readY (String namaFile) throws Exception {
        File file = new File(namaFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String temp = "";
        float y = 0;
        int i = 0; int j;

        while ((line = br.readLine()) != null) {
            if (i == 4) {
                for (j = 0; j < line.length(); j++) {
                    if (line.charAt(j) != ' ') {
                        temp += line.charAt(j);
                    } else {
                        temp = "";
                    }

                    if (j == line.length()-1) {
                        y = Float.parseFloat(temp);
                    }
                }
            }
            i++;
        }

        return y;
    }
}