import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    private final List<Book> books = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("Welcome to Library App!");
            System.out.println("1. Print all books");
            System.out.println("2. Add new book");
            System.out.println("3. Search books by title");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Delete a book by id");
            System.out.println("7. Quit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> printAllBooks();
                case 2 -> addNewBook();
                case 3 -> searchByTitle();
                case 4 -> borrowBook();
                case 5 -> returnBook();
                case 6 -> deleteBook();
                case 7 -> running = false;
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void addNewBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        books.add(new Book(title, author, year));
    }

    private void searchByTitle() {
        System.out.print("Enter title part: ");
        String part = scanner.nextLine().toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(part)) {
                System.out.println(book);
            }
        }
    }

    private void borrowBook() {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Book book = findById(id);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }

        if (book.isAvailable()) {
            book.markAsBorrowed();
        } else {
            System.out.println("Book is already borrowed");
        }
    }

    private void returnBook() {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Book book = findById(id);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }

        if (!book.isAvailable()) {
            book.markAsReturned();
        }
    }

    private void deleteBook() {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());

        Book book = findById(id);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }

        books.remove(book);
    }

    private Book findById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    static void main() {
        new LibraryApp().run();
    }
}
