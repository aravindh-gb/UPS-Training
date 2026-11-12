import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter mark (0-100): ");
        int mark = scanner.nextInt();

        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark.");
        } else if (mark >= 90) {
            System.out.println("Grade: A");
        } else if (mark >= 80) {
            System.out.println("Grade: B");
        } else if (mark >= 70) {
            System.out.println("Grade: C");
        } else if (mark >= 50) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }
        scanner.close();
    }
}
