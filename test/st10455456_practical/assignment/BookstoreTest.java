package st10455456_practical.assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class BookstoreTest {
    
    private Bookstore bookstore;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private InputStream originalIn;
    
    @BeforeEach
    public void setUp() {
        bookstore = new Bookstore();
        originalOut = System.out;
        originalIn = System.in;
        System.setOut(new PrintStream(outputStream));
    }
    
    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }
    
    private void resetStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        outputStream.reset();
    }
    
    private String getOutput() {
        return outputStream.toString();
    }

    // Helper method to access the private books list using reflection
    private List<BookModel> getBooksList() throws Exception {
        java.lang.reflect.Field booksField = Bookstore.class.getDeclaredField("books");
        booksField.setAccessible(true);
        return (List<BookModel>) booksField.get(bookstore);
    }

    // Helper method to call the private getValidAge method using reflection
    private int callGetValidAge() throws Exception {
        java.lang.reflect.Method method = Bookstore.class.getDeclaredMethod("getValidAge");
        method.setAccessible(true);
        return (int) method.invoke(bookstore);
    }
    
    @Test
    public void testCaptureBook() throws Exception {
        // Simulate user input: ID, Title, Genre, Age
//        provideInput("B001\nTest Book\nFiction\n12\n");
//        bookstore.captureBook();
//        
        List<BookModel> books = getBooksList();
        assertEquals(1, books.size());
        assertEquals("B001", books.get(0).getId());
        assertEquals("Test Book", books.get(0).getTitle());
        
        String output = getOutput();
        assertTrue(output.contains("Book captured successfully"));
        resetStreams();
    }
    
    @Test
    public void testSearchBookByIdFound() throws Exception {
        // First add a book
        List<BookModel> books = getBooksList();
        books.add(new BookModel("B001", "Test Book", "Fiction", 12));
        
        // Now search for it
//        provideInput("B001\n");
//        bookstore.searchBookById();
//        
        String output = getOutput();
        assertTrue(output.contains("Test Book"));
        assertTrue(output.contains("Fiction"));
        resetStreams();
    }
    
    @Test
    public void testSearchBookByIdNotFound() throws Exception {
        // Add some books
        List<BookModel> books = getBooksList();
        books.add(new BookModel("B001", "Test Book", "Fiction", 12));
        
        // Search for non-existent book
//        provideInput("B999\n");
//        bookstore.searchBookById();
//        
        String output = getOutput();
        assertTrue(output.contains("Book not found"));
        resetStreams();
    }
    
    @Test
    public void testSearchByGenreFound() throws Exception {
        // Add books
        List<BookModel> books = getBooksList();
        books.add(new BookModel("B001", "Book1", "Fiction", 12));
        books.add(new BookModel("B002", "Book2", "Non-Fiction", 15));
        books.add(new BookModel("B003", "Book3", "Fiction", 10));
        
        // Search by genre
//        provideInput("Fiction\n");
//        bookstore.searchByGenre();
//        
        String output = getOutput();
        assertTrue(output.contains("Book1"));
        assertTrue(output.contains("Book3"));
        assertFalse(output.contains("Book2")); // Should not contain non-fiction book
        resetStreams();
    }
    
    @Test
    public void testSearchByGenreNotFound() throws Exception {
        // Add some books
        List<BookModel> books = getBooksList();
        books.add(new BookModel("B001", "Book1", "Fiction", 12));
        
        // Search for non-existent genre
//        provideInput("Science\n");
//        bookstore.searchByGenre();
//        
        String output = getOutput();
        assertTrue(output.contains("No books found in that genre"));
        resetStreams();
    }
    
    @Test
    public void testFilterBooksByAge() throws Exception {
        // Add books with different ages
        List<BookModel> books = getBooksList();
        books.add(new BookModel("B001", "Book1", "Fiction", 5));
        books.add(new BookModel("B002", "Book2", "Fiction", 10));
        books.add(new BookModel("B003", "Book3", "Fiction", 15));
        
        // Filter by age 10 (should show books for age 10 and below)
        provideInput("10\n");
        bookstore.filterBooksByAge();
        
        String output = getOutput();
        assertTrue(output.contains("Book1")); // Age 5
        assertTrue(output.contains("Book2")); // Age 10
        assertFalse(output.contains("Book3")); // Age 15 (should not appear)
        resetStreams();
    }
    
    @Test
    public void testFilterBooksByAgeNoneFound() throws Exception {
        // Add books with higher ages
        List<BookModel> books = getBooksList();
        books.add(new BookModel("B001", "Book1", "Fiction", 15));
        books.add(new BookModel("B002", "Book2", "Fiction", 20));
        
        // Filter by very low age
        provideInput("5\n");
        bookstore.filterBooksByAge();
        
        String output = getOutput();
        assertTrue(output.contains("No books found for age 5 or below"));
        resetStreams();
    }
    
    @Test
    public void testDeleteBookByIdSuccess() throws Exception {
        // Add a book
        List<BookModel> books = getBooksList();
        books.add(new BookModel("B001", "Test Book", "Fiction", 12));
        
//        // Delete it
//        provideInput("B001\n");
//        bookstore.deleteBookById();
        
        assertEquals(0, books.size()); // Book should be removed
        String output = getOutput();
        assertTrue(output.contains("Book deleted successfully"));
        resetStreams();
    }
    
    @Test
    public void testDeleteBookByIdNotFound() throws Exception {
        // Add a book
        List<BookModel> books = getBooksList();
        books.add(new BookModel("B001", "Test Book", "Fiction", 12));
        int initialSize = books.size();
        
        // Try to delete non-existent book
//        provideInput("B999\n");
//        bookstore.deleteBookById();
//        
        assertEquals(initialSize, books.size()); // Size should not change
        String output = getOutput();
        assertTrue(output.contains("Book not found"));
        resetStreams();
    }
    
    @Test
    public void testUpdateBookByIdSuccess() throws Exception {
        // Add a book
        List<BookModel> books = getBooksList();
        BookModel book = new BookModel("B001", "Old Title", "Old Genre", 10);
        books.add(book);
        
        // Update all fields: ID, new title, new genre, new age
        provideInput("B001\nNew Title\nNew Genre\n15\n");
        bookstore.updateBookById();
        
        assertEquals("New Title", book.getTitle());
        assertEquals("New Genre", book.getGenre());
        assertEquals(15, book.getRecommendedAge());
        
        String output = getOutput();
        assertTrue(output.contains("Book updated successfully"));
        resetStreams();
    }
    
    @Test
    public void testUpdateBookByIdPartialUpdate() throws Exception {
        // Add a book
        List<BookModel> books = getBooksList();
        BookModel book = new BookModel("B001", "Old Title", "Old Genre", 10);
        books.add(book);
        
        // Update only genre, keep title and age (press enter for blank inputs)
        provideInput("B001\n\nNew Genre\n\n");
        bookstore.updateBookById();
        
        assertEquals("Old Title", book.getTitle()); // Should remain unchanged
        assertEquals("New Genre", book.getGenre()); // Should be updated
        assertEquals(10, book.getRecommendedAge()); // Should remain unchanged
        
        String output = getOutput();
        assertTrue(output.contains("Book updated successfully"));
        resetStreams();
    }
    
    @Test
    public void testUpdateBookByIdNotFound() throws Exception {
        // Add a book
        List<BookModel> books = getBooksList();
        books.add(new BookModel("B001", "Test Book", "Fiction", 12));
        
        // Try to update non-existent book
        provideInput("B999\n");
        bookstore.updateBookById();
        
        String output = getOutput();
        assertTrue(output.contains("Book not found"));
        resetStreams();
    }
    
    @Test
    public void testPrintReportEmpty() {
        bookstore.printReport();
        
        String output = getOutput();
        assertTrue(output.contains("No books in the system"));
        resetStreams();
    }
    
    @Test
    public void testPrintReportWithBooks() throws Exception {
        // Add books
        List<BookModel> books = getBooksList();
        books.add(new BookModel("B001", "Book1", "Fiction", 12));
        books.add(new BookModel("B002", "Book2", "Non-Fiction", 15));
        
        bookstore.printReport();
        
        String output = getOutput();
        assertTrue(output.contains("Book1"));
        assertTrue(output.contains("Book2"));
        assertTrue(output.contains("Fiction"));
        assertTrue(output.contains("Non-Fiction"));
        resetStreams();
    }
    
    @Test
    public void testGetValidAgeValidInput() throws Exception {
        provideInput("12\n");
        int age = callGetValidAge();
        assertEquals(12, age);
        resetStreams();
    }
    
    @Test
    public void testGetValidAgeInvalidThenValidInput() throws Exception {
        // Test with invalid input followed by valid input
        provideInput("invalid\n150\n-5\n25\n");
        int age = callGetValidAge();
        assertEquals(25, age);
        resetStreams();
    }
}