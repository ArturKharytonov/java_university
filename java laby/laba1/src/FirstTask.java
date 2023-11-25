import java.util.Scanner;

public class FirstTask {
    private static Scanner _scanner = new Scanner(System.in);
    /**
     * Read data from console
     * And calculate Lucas numbers
     * @param args Default parameters
     */
    public static void main(String[] args) {
        System.out.print("Enter an integer: ");

        int num = _scanner.nextInt();
        int sum = LucasCalculation.sumOfLucas(num);

        System.out.println("Sum of first " + num + " Luc's numbers: " + sum);
    }
}
