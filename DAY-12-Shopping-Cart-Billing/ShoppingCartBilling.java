import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartBilling {
    static class Item {
        String name;
        double price;
        int quantity;
        Item(String name, double price, int quantity) {
            this.name = name; this.price = price; this.quantity = quantity;
        }
        double total() { return price * quantity; }
    }

    public static void main(String[] args) {
        List<Item> cart = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Product name (or 'done'): ");
                String name = scanner.nextLine().trim();
                if (name.equalsIgnoreCase("done")) break;
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty.");
                    continue;
                }
                System.out.print("Unit price: ");
                double price = Double.parseDouble(scanner.nextLine());
                System.out.print("Quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                if (price < 0 || quantity <= 0) {
                    System.out.println("Price must be non-negative and quantity positive.");
                    continue;
                }
                cart.add(new Item(name, price, quantity));
            }
        } catch (NumberFormatException exception) {
            System.out.println("Invalid numeric input. Please run again.");
            return;
        }

        double subtotal = 0;
        System.out.println("\n========== CUSTOMER BILL ==========");
        System.out.printf("%-20s %8s %8s %10s%n", "Item", "Price", "Qty", "Total");
        for (Item item : cart) {
            System.out.printf("%-20s %8.2f %8d %10.2f%n",
                    item.name, item.price, item.quantity, item.total());
            subtotal += item.total();
        }
        double discount = subtotal >= 1000 ? subtotal * 0.05 : 0;
        double tax = (subtotal - discount) * 0.05;
        double grandTotal = subtotal - discount + tax;

        System.out.printf("%nSubtotal:    Rs. %.2f%n", subtotal);
        System.out.printf("Discount:    Rs. %.2f%n", discount);
        System.out.printf("Tax (5%%):    Rs. %.2f%n", tax);
        System.out.printf("Grand Total: Rs. %.2f%n", grandTotal);
        System.out.println("===================================");
    }
}
