import java.io.File;
import java.util.Scanner;

public class julian {
    public static void main(String[] args) {
        // System.out.println("------------------PROGRAM KELUAR-----------------");
        
        // System.out.println("==================PROGRAM KELUAR=================");
        // Scanner in = new Scanner(System.in);
        // int x = in.nextInt();
        // int y = in.nextInt();

        // System.out.println(x + " " + y);

        // float[] a = {};
        // System.out.println(a.length);
        File currentDir = new File(".");
        File parentDir = currentDir.getParentFile();
        File directory = new File (parentDir,"Regresi");
        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().equals("Regresi.java")) {
                System.out.println("yey");
            }
        }
        int n = 5;
        test1(n);
        System.out.println(n);
    }

    public static void test1(int n) {
        n += 7;
    }
}
