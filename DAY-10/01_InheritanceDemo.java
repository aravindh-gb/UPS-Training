class Person {
    String name;
    Person(String name) { this.name = name; }
    void introduce() { System.out.println("Name: " + name); }
}
class Student extends Person {
    int rollNumber;
    Student(String name, int rollNumber) {
        super(name); this.rollNumber = rollNumber;
    }
    void display() { introduce(); System.out.println("Roll number: " + rollNumber); }
}
public class InheritanceDemo {
    public static void main(String[] args) {
        new Student("Arun", 25).display();
    }
}
