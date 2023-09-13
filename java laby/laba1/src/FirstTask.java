import java.util.Scanner;

public class FirstTask {
    private static Scanner _scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter an integer: ");

        int num = _scanner.nextInt();
        int sum = sumOfLucas(num);

        System.out.println("Sum of first " + num + " Luc's numbers: " + sum);
    }
    public static int sumOfLucas(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++)
            sum += lucas(i);

        return sum;
    }
    public static int lucas(int n) {
        if (n == 0) return 2;
        if (n == 1) return 1;

        int a = 2, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
