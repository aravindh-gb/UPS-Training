interface Payment { void pay(double amount); }
class UpiPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid Rs. " + amount + " using UPI.");
    }
}
public class InterfaceExample {
    public static void main(String[] args) {
        Payment payment = new UpiPayment();
        payment.pay(1250.00);
    }
}
