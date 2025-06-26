package task3;

import java.util.*;

public class Library {
    static Scanner scanner = new Scanner(System.in);
    static List<Book> books = new ArrayList<>();
    static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        users.add(new User(1, "md samad"));
        users.add(new User(2, "md sammer"));

        books.add(new Book(101, "Clean Code", "Robert C. Martin"));
        books.add(new Book(102, "Effective Java", "Joshua Bloch"));

        while (true) {
            System.out.println("\n📚 LIBRARY MENU");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. Show Books");
            System.out.println("4. Show Users");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> issueBook();
                case 2 -> ReturnBook.returnBook();
                case 3 -> showBooks();
                case 4 -> showUsers();
                case 5 -> {
                    System.out.println("👋 Exiting. Have a great day!");
                    return;
                }
                default -> System.out.println("❗ Invalid option.");
            }
        }
    }

    private static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        Book foundBook = books.stream().filter(b -> b.getId() == bookId).findFirst().orElse(null);
        if (foundBook == null) {
            System.out.println("❌ Book not found.");
            return;
        }

        if (foundBook.isIssued()) {
            System.out.println("⚠ Book is already issued to User ID: " + foundBook.getIssuedToUserId());
            return;
        }

        System.out.print("Enter User ID: ");
        int userId = Integer.parseInt(scanner.nextLine());
        boolean userExists = users.stream().anyMatch(u -> u.getUserId() == userId);

        if (!userExists) {
            System.out.println("❌ User not found.");
            return;
        }

        foundBook.issueTo(userId);
        System.out.println("✅ Book issued to User ID: " + userId);
    }

    private static void showBooks() {
        if (books.isEmpty()) {
            System.out.println("📂 No books in the library.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void showUsers() {
    if (users.isEmpty()) {
        System.out.println("📂 No users registered.");
        return;
    }

    System.out.println("📋 Registered Users:");
    for (User user : users) {
        System.out.print("ID: " + user.getUserId() + " | Name: " + user.getName());

        // Check if this user has a book issued
        Book issuedBook = books.stream()
            .filter(b -> b.isIssued() && b.getIssuedToUserId() == user.getUserId())
            .findFirst()
            .orElse(null);

        if (issuedBook != null) {
            System.out.println(" | 📕 Issued Book: " + issuedBook.getTitle() + " (ID: " + issuedBook.getId() + ")");
        } else {
            System.out.println(" | ✅ No book issued");
        }
    }
}
}