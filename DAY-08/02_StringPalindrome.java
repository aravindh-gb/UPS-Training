import java.util.Scanner;
public class StringPalindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = scanner.nextLine().replaceAll("\\s+", "").toLowerCase();
        String reversed = new StringBuilder(word).reverse().toString();
        System.out.println(word.equals(reversed) ? "Palindrome" : "Not a palindrome");
        scanner.close();
    }
}
