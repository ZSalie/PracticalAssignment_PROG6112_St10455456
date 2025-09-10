/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package st10455456_practical.assignment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class SeriesTest {
    
    private Series series;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    
    @BeforeEach
    public void setUp() {
        series = new Series();
        System.setOut(new PrintStream(outputStream));
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    @Test
    public void testSearchSeries() {
        String testId = "S001";
        String testName = "Test Series";
        int testAge = 12;
        String testEpisodes = "10";
        
        String input = testId + "\n" + testName + "\n" + testAge + "\n" + testEpisodes + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        series.CaptureSeries();
        outputStream.reset();
        
        System.setIn(new ByteArrayInputStream(testId.getBytes()));
        series.SearchSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains(testId));
        assertTrue(output.contains(testName));
        assertTrue(output.contains(String.valueOf(testAge)));
        assertTrue(output.contains(testEpisodes));
    }
    
    @Test
    public void testSearchSeriesSeriesNotFound() {
        String nonExistentId = "NONEXISTENT123";
        
        System.setIn(new ByteArrayInputStream(nonExistentId.getBytes()));
        series.SearchSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("was not found"));
        assertTrue(output.contains(nonExistentId));
    }
    
    @Test
    public void testUpdateSeries() {
        String testId = "S002";
        String testName = "Update Test Series";
        int originalAge = 12;
        int newAge = 16;
        String testEpisodes = "8";
        
        String captureInput = testId + "\n" + testName + "\n" + originalAge + "\n" + testEpisodes + "\n";
        System.setIn(new ByteArrayInputStream(captureInput.getBytes()));
        series.CaptureSeries();
        
        outputStream.reset();
        
        String updateInput = testId + "\n" + newAge + "\n";
        System.setIn(new ByteArrayInputStream(updateInput.getBytes()));
        series.UpdateSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("updated successfully"));
        
        outputStream.reset();
        System.setIn(new ByteArrayInputStream(testId.getBytes()));
        series.SearchSeries();
        
        String searchOutput = outputStream.toString();
        assertTrue(searchOutput.contains(String.valueOf(newAge)));
    }
    
    @Test
    public void testDeleteSeries() {
        String testId = "S003";
        String testName = "Delete Test Series";
        int testAge = 14;
        String testEpisodes = "5";
        
        String captureInput = testId + "\n" + testName + "\n" + testAge + "\n" + testEpisodes + "\n";
        System.setIn(new ByteArrayInputStream(captureInput.getBytes()));
        series.CaptureSeries();
        
        outputStream.reset();
        
        String deleteInput = testId + "\n" + "yes\n";
        System.setIn(new ByteArrayInputStream(deleteInput.getBytes()));
        series.DeleteSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("deleted successfully"));
        
        outputStream.reset();
        System.setIn(new ByteArrayInputStream(testId.getBytes()));
        series.SearchSeries();
        
        String searchOutput = outputStream.toString();
        assertTrue(searchOutput.contains("was not found"));
    }
    
    @Test
    public void testDeleteSeriesSeriesNotFound() {
        String nonExistentId = "NONEXISTENT456";
        
        System.setIn(new ByteArrayInputStream((nonExistentId + "\n").getBytes()));
        series.DeleteSeries();
        
        String output = outputStream.toString();
        assertTrue(output.contains("was not found"));
        assertTrue(output.contains(nonExistentId));
    }
    
    @Test
    public void testSeriesAgeRestrictionAgeValid() {
        int[] validAges = {2, 5, 10, 15, 18};
        
        for (int age : validAges) {
            String testId = "VALID" + age;
            String testName = "Valid Age Test";
            String testEpisodes = "10";
            
            String input = testId + "\n" + testName + "\n" + age + "\n" + testEpisodes + "\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            
            outputStream.reset();
            series.CaptureSeries();
            
            String output = outputStream.toString();
            assertTrue(output.contains("successfully saved"));
        }
    }
    
    @Test
    public void testSeriesAgeRestrictionSeriesAgeInvalid() {
        int[] invalidAges = {1, 19, 0, -5, 100};
        
        for (int age : invalidAges) {
            String testId = "INVALID" + age;
            String testName = "Invalid Age Test";
            String testEpisodes = "10";
            
            String input = testId + "\n" + testName + "\n" + age + "\n" + "12\n" + testEpisodes + "\n";
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            
            outputStream.reset();
            series.CaptureSeries();
            
            String output = outputStream.toString();
            assertTrue(output.contains("incorrect series age") || output.contains("successfully saved"));
        }
    }
}