import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        boolean isPrime = number >= 2;
        for (int i = 2; i * i <= number && isPrime; i++) {
            if (number % i == 0) isPrime = false;
        }

        System.out.println(isPrime ? "Prime number" : "Not a prime number");
        scanner.close();
    }
}
