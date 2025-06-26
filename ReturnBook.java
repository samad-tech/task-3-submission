package task3;

import java.util.*;

public class ReturnBook {
    static Scanner scanner = Library.scanner;
    static List<Book> books = Library.books;

    public static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        for (Book book : books) {
            if (book.getId() == bookId) {
                if (book.isIssued()) {
                    System.out.println("Book was issued to User ID: " + book.getIssuedToUserId());
                    book.returnBook();
                    System.out.println("✅ Book returned successfully.");
                } else {
                    System.out.println("⚠ Book was not issued.");
                }
                return;
            }
        }

        System.out.println("❌ Book not found.");
    }
}