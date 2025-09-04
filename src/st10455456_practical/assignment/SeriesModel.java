/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st10455456_practical.assignment;

/**
 *
 * @author lab_services_student
 */
public class SeriesModel {
    //Series Variables
    public String SeriesId;
    public String SeriesName;
    public int AgeRestriction;
    public String SeriesNumberOfEpisodes;

    public SeriesModel(String id, String name, int ageRestriction, String episodes) {
        this.SeriesId = id;
        this.SeriesName = name;
        this.AgeRestriction = ageRestriction;
        this.SeriesNumberOfEpisodes = episodes;
    }

    @Override
    public String toString() {
        return "Series ID: " + SeriesId +
               ", Name: " + SeriesName +
               ", Age Restriction: " + AgeRestriction +
               ", Episodes: " + SeriesNumberOfEpisodes;
    }
}