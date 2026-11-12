import java.util.Scanner;

public class ArmstrongNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a non-negative integer: ");
        int number = scanner.nextInt();

        if (number < 0) {
            System.out.println("Enter a non-negative integer.");
        } else {
            int digits = String.valueOf(number).length();
            int remaining = number;
            int sum = 0;

            do {
                int digit = remaining % 10;
                sum += (int) Math.pow(digit, digits);
                remaining /= 10;
            } while (remaining > 0);

            System.out.println(sum == number ? "Armstrong number" : "Not an Armstrong number");
        }
        scanner.close();
    }
}
