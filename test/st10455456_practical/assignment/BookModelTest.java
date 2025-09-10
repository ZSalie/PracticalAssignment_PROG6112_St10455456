package st10455456_practical.assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookModelTest {
    
    @Test
    public void testBookModelConstructorAndGetters() {
        BookModel book = new BookModel("B001", "Test Book", "Fiction", 12);
        
        assertEquals("B001", book.getId());
        assertEquals("Test Book", book.getTitle());
        assertEquals("Fiction", book.getGenre());
        assertEquals(12, book.getRecommendedAge());
    }
    
    @Test
    public void testSetters() {
        BookModel book = new BookModel("B001", "Test Book", "Fiction", 12);
        
        book.setTitle("New Title");
        book.setGenre("Science Fiction");
        book.setRecommendedAge(14);
        
        assertEquals("New Title", book.getTitle());
        assertEquals("Science Fiction", book.getGenre());
        assertEquals(14, book.getRecommendedAge());
    }
    
    @Test
    public void testToString() {
        BookModel book = new BookModel("B001", "Test Book", "Fiction", 12);
        String result = book.toString();
        
        assertTrue(result.contains("B001"));
        assertTrue(result.contains("Test Book"));
        assertTrue(result.contains("Fiction"));
        assertTrue(result.contains("12"));
    }
    
    @Test
    public void testEqualsAndHashCode() {
        BookModel book1 = new BookModel("B001", "Test Book", "Fiction", 12);
        BookModel book2 = new BookModel("B001", "Different Title", "Different Genre", 15);
        BookModel book3 = new BookModel("B002", "Test Book", "Fiction", 12);
        
        assertEquals(book1, book2);
        assertNotEquals(book1, book3);
    }
}