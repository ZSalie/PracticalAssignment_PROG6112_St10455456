/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st10455456_practical.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bookstore {
    List<BookModel> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void launch() {
        System.out.println("\n--- ZAARA'S BOOKSTORE ---");
        while (true) {
            System.out.println("\n1) Capture a new book");
            System.out.println("2) Search for a book by ID");
            System.out.println("3) Search books by genre");
            System.out.println("4) Find books by age");
            System.out.println("5) Print bookstore report");
            System.out.println("6) Exit to Main Menu");
            System.out.println("7) Delete a book by ID");
            System.out.println("8) Update a book by ID");
            System.out.print("Choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": captureBook(); break;
                case "2": searchBookById(); break;
                case "3": searchByGenre(); break;
                case "4": filterBooksByAge(); break;
                case "5": printReport(); break;
                case "6": System.out.println("Returning to Main Menu..."); return;
                case "7": deleteBookById(); break;
                case "8": updateBookById(); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }
    //Add Book Method
    private void captureBook() {
        System.out.println("\nCAPTURE A NEW BOOK");
        System.out.print("Enter book ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        int age = getValidAge();

        books.add(new BookModel(id, title, genre, age));
        System.out.println("Book captured successfully.");
    }
       //Search Method for BookId
    private void searchBookById() {
        System.out.print("Enter book ID to search: ");
        String id = scanner.nextLine();
        boolean found = false;

        for (BookModel book : books) {
            if (book.getId().equalsIgnoreCase(id)) {
                System.out.println(book);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }
    //Search Method using the genre of the book
    private void searchByGenre() {
        System.out.print("Enter genre to search: ");
        String genre = scanner.nextLine();
        boolean found = false;

        for (BookModel book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found in that genre.");
        }
    }
    //Filter Method that filters Books by Age
    void filterBooksByAge() {
        System.out.print("Enter maximum recommended age: ");
        int age = getValidAge();
        boolean found = false;

        for (BookModel book : books) {
            if (book.getRecommendedAge() <= age) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found for age " + age + " or below.");
        }
    }
    //Report Method for Book
    void printReport() {
        System.out.println("\n--- BOOKSTORE REPORT ---");
        if (books.isEmpty()) {
            System.out.println("No books in the system.");
        } else {
            int count = 1;
            for (BookModel book : books) {
                System.out.println("Book " + count + ": " + book);
                count++;
            }
        }
    }
    //Delete Method for Book using BookId
    private void deleteBookById() {
        System.out.print("Enter book ID to delete: ");
        String id = scanner.nextLine();
        boolean removed = books.removeIf(book -> book.getId().equalsIgnoreCase(id));

        if (removed) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found. No deletion performed.");
        }
    }
    //Update Method for Book
    void updateBookById() {
        System.out.print("Enter book ID to update: ");
        String id = scanner.nextLine();
        BookModel targetBook = null;

        for (BookModel book : books) {
            if (book.getId().equalsIgnoreCase(id)) {
                targetBook = book;
                break;
            }
        }

        if (targetBook == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.println("Updating book: " + targetBook);
        System.out.print("Enter new title (leave blank to keep current): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            targetBook.setTitle(newTitle);
        }

        System.out.print("Enter new genre (leave blank to keep current): ");
        String newGenre = scanner.nextLine();
        if (!newGenre.isEmpty()) {
            targetBook.setGenre(newGenre);
        }

        System.out.print("Enter new recommended age (leave blank to keep current): ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) {
            try {
                int newAge = Integer.parseInt(ageInput);
                if (newAge >= 0 && newAge <= 120) {
                    targetBook.setRecommendedAge(newAge);
                } else {
                    System.out.println("Invalid age. Keeping current value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Keeping current age.");
            }
        }

        System.out.println("Book updated successfully.");
    }
    //Age Restriction Method For book
    private int getValidAge() {
        int age = -1;
        while (true) {
            System.out.print("Enter recommended age: ");
            String input = scanner.nextLine();
            try {
                age = Integer.parseInt(input);
                if (age >= 0 && age <= 120) {
                    break;
                } else {
                    System.out.println("Please enter a valid age between 0 and 120.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric age.");
            }
        }
        return age;
    }
}
