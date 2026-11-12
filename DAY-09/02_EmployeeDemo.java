class Employee {
    private final int id;
    private final String name;
    private double salary;
    Employee(int id, String name, double salary) {
        this.id = id; this.name = name; setSalary(salary);
    }
    void setSalary(double salary) { if (salary >= 0) this.salary = salary; }
    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}
public class EmployeeDemo {
    public static void main(String[] args) {
        new Employee(101, "Arun", 35000).display();
    }
}
