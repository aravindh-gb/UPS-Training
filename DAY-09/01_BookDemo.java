class Book {
    String title;
    String author;
    double price;
    Book(String title, String author, double price) {
        this.title = title; this.author = author; this.price = price;
    }
    void displayDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: " + price);
    }
}
public class BookDemo {
    public static void main(String[] args) {
        new Book("Clean Code", "Robert C. Martin", 499.0).displayDetails();
    }
}
