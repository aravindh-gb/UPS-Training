import java.util.Scanner;

public class SwitchCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter two numbers: ");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();

        System.out.print("Choose operation (+, -, *, /): ");
        char operation = scanner.next().charAt(0);

        switch (operation) {
            case '+': System.out.println("Result: " + (a + b)); break;
            case '-': System.out.println("Result: " + (a - b)); break;
            case '*': System.out.println("Result: " + (a * b)); break;
            case '/':
                if (b == 0) System.out.println("Cannot divide by zero.");
                else System.out.println("Result: " + (a / b));
                break;
            default: System.out.println("Invalid operation.");
        }
        scanner.close();
    }
}
