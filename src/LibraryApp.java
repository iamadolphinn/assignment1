import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class LibraryApp {
    private List<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to Library App!");
            System.out.println("1. Print all books");
            System.out.println("2. Add new books");
            System.out.println("3. Search books by title");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Delete a book by id");
            System.out.println("7. Quit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    printAllBooks();
                    break;
                case "2":
                    addNewBook();
                    break;
                case "3":
                    searchBooksByTitle();
                    break;
                case "4":
                    borrowBook();
                    break;
                case "5":
                    returnBook();
                    break;
                case "6":
                    deleteBookById();
                    break;
                case "7":
                    running = false;
                    System.out.println("Exiting Library App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("There are no books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
    private void addNewBook() {
        try {
            System.out.println("Enter the title:");
            String title = scanner.nextLine();
            System.out.println("Enter the author:");
            String author = scanner.nextLine();
            System.out.println("Enter the year:");
            int year = scanner.nextInt();
            Book book = new Book(title, author, year);
            books.add(book);
            System.out.println("Book added successfully!" + book);
        } catch (Exception e) {
            System.out.println("Error adding book:" + e.getMessage());
        }
    }
    private void searchBooksByTitle() {
        System.out.println("Enter part of the title:");
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching" + query);
        }
    }
    private void borrowBook() {
        System.out.println("Enter book ID to borrow:");
        int id = Integer.parseInt(scanner.nextLine());
        Book book = findBookById(id);
        if (book != null) {
            if (!book.isAvailable()) {
                book.markAsReturned();
                System.out.println("Book returned" + book);
            } else {
                System.out.println("Book was not borrowed");
            }
        } else {
            System.out.println("Book was not found");
        }
    }
    private void returnBook() {
        System.out.println("Enter book id to return: ");
        int id = Integer.parseInt(scanner.nextLine());
        Book book = findBookById(id);
        if (book != null) {
            if (book.isAvailable()) {
                book.markAsReturned();
                System.out.println("Book returned" + book);
            } else {
                System.out.println("Book was not returned");
            }
        } else {
            System.out.println("Book was not found");
        }
    }
    private void deleteBookById() {
        System.out.println("Enter book ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Book book = findBookById(id);
        if (book != null) {
            books.remove(book);
            System.out.println("Book deleted: " + book);
        } else {
            System.out.println("Book was not found");
        }
    }
    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
    public static void main(String[] args){
        LibraryApp app = new LibraryApp();
        app.run();
    }
}