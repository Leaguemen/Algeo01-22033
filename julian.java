import java.util.Scanner;

public class julian {
    public static void main(String[] args) {
        System.out.println("------------------PROGRAM KELUAR-----------------");
        
        System.out.println("==================PROGRAM KELUAR=================");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();

        System.out.println(x + " " + y);

        float[] a = {};
        System.out.println(a.length);

        int n = 5;
        test1(n);
        System.out.println(n);
    }

    public static void test1(int n) {
        n += 7;
    }
}
