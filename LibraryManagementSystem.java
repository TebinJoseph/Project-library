import java.util.ArrayList;
import java.util.Scanner;

class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(String bookId, String title, String author, String genre, String status) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                System.out.println("Error: Book ID already exists.");
                return;
            }
        }
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Error: Title and Author cannot be empty.");
            return;
        }
        if (!status.equals("Available") && !status.equals("Checked Out")) {
            System.out.println("Error: Invalid availability status.");
            return;
        }
        books.add(new Book(bookId, title, author, genre, status));
        System.out.println("Book added successfully.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBook(String searchTerm) {
        boolean found = false;
        for (Book book : books) {
            if (book.getId().toLowerCase().contains(searchTerm.toLowerCase()) ||
                book.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    public void updateBook(String bookId, String title, String author, String genre, String status) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                if (!title.isEmpty()) book.setTitle(title);
                if (!author.isEmpty()) book.setAuthor(author);
                if (!genre.isEmpty()) book.setGenre(genre);
                if (status.equals("Available") || status.equals("Checked Out")) book.setStatus(status);
                System.out.println("Book updated successfully.");
                return;
            }
        }
        System.out.println("Error: Book ID not found.");
    }

    public void deleteBook(String bookId) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(bookId)) {
                books.remove(i);
                System.out.println("Book deleted successfully.");
                return;
            }
        }
        System.out.println("Error: Book ID not found.");
    }
}

class Book {
    private String id, title, author, genre, status;

    public Book(String id, String title, String author, String genre, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Genre: " + genre + " | Status: " + status;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter Availability Status (Available/Checked Out): ");
                    String status = scanner.nextLine();
                    library.addBook(bookId, title, author, genre, status);
                    break;
                case "2":
                    library.viewBooks();
                    break;
                case "3":
                    System.out.print("Enter Book ID or Title to search: ");
                    String searchTerm = scanner.nextLine();
                    library.searchBook(searchTerm);
                    break;
                case "4":
                    System.out.print("Enter Book ID to update: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter new Title (or leave blank): ");
                    title = scanner.nextLine();
                    System.out.print("Enter new Author (or leave blank): ");
                    author = scanner.nextLine();
                    System.out.print("Enter new Genre (or leave blank): ");
                    genre = scanner.nextLine();
                    System.out.print("Enter new Status (Available/Checked Out): ");
                    status = scanner.nextLine();
                    library.updateBook(bookId, title, author, genre, status);
                    break;
                case "5":
                    System.out.print("Enter Book ID to delete: ");
                    bookId = scanner.nextLine();
                    library.deleteBook(bookId);
                    break;
                case "6":
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
