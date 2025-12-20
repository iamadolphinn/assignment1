import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {

    // Fields
    private ArrayList<Book> books;
    private Scanner scanner;

    // --- Constructor ---
    public LibraryApp() {
        books = new ArrayList<Book>();
        scanner = new Scanner(System.in);
    }

    // --- Main program loop ---
    public void run() {
        boolean running = true;
        System.out.println("Welcome to Library App!");

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                printAllBooks();
            } else if (choice.equals("2")) {
                addBook();
            } else if (choice.equals("3")) {
                searchBooksByTitle();
            } else if (choice.equals("4")) {
                borrowBook();
            } else if (choice.equals("5")) {
                returnBook();
            } else if (choice.equals("6")) {
                deleteBook();
            } else if (choice.equals("7")) {
                running = false;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Exiting Library App. Goodbye!");
    }

    // --- Prints the menu ---
    private void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Print all books");
        System.out.println("2. Add new book");
        System.out.println("3. Search books by title");
        System.out.println("4. Borrow a book");
        System.out.println("5. Return a book");
        System.out.println("6. Delete a book by id");
        System.out.println("7. Quit");
    }

    // --- Prints all books ---
    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }

    // --- Adds a new book ---
    private void addBook() {
        try {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            System.out.print("Enter author: ");
            String author = scanner.nextLine();

            System.out.print("Enter year: ");
            int year = Integer.parseInt(scanner.nextLine());

            Book book = new Book(title, author, year);
            books.add(book);

            System.out.println("Book added: " + book);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // --- Search books by title ---
    private void searchBooksByTitle() {
        System.out.print("Enter part of the title: ");
        String search = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(search)) {
                System.out.println(b);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with this title.");
        }
    }

    // --- Borrow a book ---
    private void borrowBook() {
        System.out.print("Enter book id to borrow: ");
        int id = Integer.parseInt(scanner.nextLine());

        Book b = findBookById(id);
        if (b != null) {
            if (b.isAvailable()) {
                b.markAsBorrowed();
                System.out.println("Book borrowed: " + b);
            } else {
                System.out.println("The book is already borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // --- Return a book ---
    private void returnBook() {
        System.out.print("Enter book id to return: ");
        int id = Integer.parseInt(scanner.nextLine());

        Book b = findBookById(id);
        if (b != null) {
            if (!b.isAvailable()) {
                b.markAsReturned();
                System.out.println("Book returned: " + b);
            } else {
                System.out.println("The book was not borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // --- Delete a book ---
    private void deleteBook() {
        System.out.print("Enter book id to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        Book b = findBookById(id);
        if (b != null) {
            books.remove(b);
            System.out.println("Book deleted: " + b);
        } else {
            System.out.println("Book not found.");
        }
    }

    // --- Helper method to find a book by id ---
    private Book findBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    // --- Main method ---
    public static void main(String[] args) {
        LibraryApp app = new LibraryApp();
        app.run();
    }
}
