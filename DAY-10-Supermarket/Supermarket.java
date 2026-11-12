import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Supermarket {
    private static final Map<Integer, Product> inventory = new LinkedHashMap<>();

    static class Product {
        String name;
        double price;
        int quantity;
        Product(String name, double price, int quantity) {
            this.name = name; this.price = price; this.quantity = quantity;
        }
    }

    private static void showProducts() {
        System.out.println("\nID  PRODUCT              PRICE      STOCK");
        for (Map.Entry<Integer, Product> entry : inventory.entrySet()) {
            Product p = entry.getValue();
            System.out.printf("%-3d %-20s Rs.%-8.2f %d%n",
                    entry.getKey(), p.name, p.price, p.quantity);
        }
    }

    public static void main(String[] args) {
        inventory.put(101, new Product("Rice", 65.00, 20));
        inventory.put(102, new Product("Milk", 30.00, 15));
        inventory.put(103, new Product("Bread", 40.00, 10));

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== SUPERMARKET ===");
                System.out.println("1. View products  2. Purchase  3. Exit");
                System.out.print("Choose: ");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    showProducts();
                } else if (choice == 2) {
                    showProducts();
                    System.out.print("Enter product ID: ");
                    int id = scanner.nextInt();
                    Product product = inventory.get(id);
                    if (product == null) {
                        System.out.println("Product not found.");
                        continue;
                    }
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    if (quantity <= 0 || quantity > product.quantity) {
                        System.out.println("Invalid quantity or insufficient stock.");
                        continue;
                    }
                    product.quantity -= quantity;
                    double total = quantity * product.price;
                    System.out.printf("Purchased %d x %s%nTotal: Rs.%.2f%n",
                            quantity, product.name, total);
                } else if (choice == 3) {
                    System.out.println("Thank you for shopping!");
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        }
    }
}
