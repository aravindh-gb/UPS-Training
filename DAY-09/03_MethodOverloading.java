class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}
public class MethodOverloading {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(10, 20));
        System.out.println(calculator.add(2.5, 3.5));
        System.out.println(calculator.add(1, 2, 3));
    }
}
