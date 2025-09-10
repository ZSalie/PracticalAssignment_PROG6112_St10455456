/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st10455456_practical.assignment;


public class BookModel {
    //Book Variables
    private String id;
    private String title;
    private String genre;
    private int recommendedAge;

    // Constructor for Book
    public BookModel(String id, String title, String genre, int recommendedAge) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.recommendedAge = recommendedAge;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getRecommendedAge() {
        return recommendedAge;
    }

    // Setters (for update functionality)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRecommendedAge(int recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    // Display format
    @Override
    public String toString() {
        return "ID: " + id +
               ", Title: " + title +
               ", Genre: " + genre +
               ", Recommended Age: " + recommendedAge;
    }
}
