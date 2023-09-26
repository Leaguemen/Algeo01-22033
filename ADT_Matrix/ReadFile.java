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
}
