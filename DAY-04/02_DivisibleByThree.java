import java.util.Scanner;

public class DivisibleByThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        System.out.println(number % 3 == 0
                ? "Divisible by 3"
                : "Not divisible by 3");
        scanner.close();
    }
}
