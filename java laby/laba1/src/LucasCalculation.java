public class LucasCalculation {
    /**
     * Calculates the sum of the first 'n' Lucas numbers.
     * The Lucas sequence is a series of numbers in which each number is the sum of the two preceding ones.
     *
     * @param n The number of Lucas numbers to sum, starting from 0.
     * @return The sum of the first 'n' Lucas numbers.
     */
    public static int sumOfLucas(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++)
            sum += lucas(i);

        return sum;
    }

    /**
     * Computes the 'n'th Lucas number.
     * The Lucas sequence is a series of numbers in which each number is the sum of the two preceding ones.
     *
     * @param n The index of the Lucas number to compute (0-based).
     * @return The 'n'th Lucas number.
     */
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
