/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st10455456_practical.assignment;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author lab_services_student
 */
public class Series {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<SeriesModel> seriesList = new ArrayList<>();

    public void launchMenu() {
        System.out.println("LATEST SERIES - 2025");
        System.out.print("Enter 1 to launch menu or any other key to exit: ");
        String input = scanner.nextLine();
        if (!input.equals("1")) {
            ExitSeriesApplication();
        }

        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": CaptureSeries(); break;
                case "2": SearchSeries(); break;
                case "3": UpdateSeries(); break;
                case "4": DeleteSeries(); break;
                case "5": SeriesReport(); break;
                case "6": ExitSeriesApplication(); running = false; break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    //Menu Display Method
    private void displayMenu() {
        System.out.println("\nPlease select one of the following menu items:");
        System.out.println("(1) Capture a new series.");
        System.out.println("(2) Search for a series.");
        System.out.println("(3) Update series age restriction");
        System.out.println("(4) Delete a series.");
        System.out.println("(5) Print series report - 2026");
        System.out.println("(6) Exit Application.");
        System.out.print("Your choice: ");
    }
    //Add Series Method
    public void CaptureSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("=====================");

        System.out.print("Enter the series id: ");
        String id = scanner.nextLine();

        System.out.print("Enter the series name: ");
        String name = scanner.nextLine();

        int ageRestriction = getValidAge();

        System.out.print("Enter the number of episodes for " + name + ": ");
        String episodes = scanner.nextLine();

        SeriesModel newSeries = new SeriesModel(id, name, ageRestriction, episodes);
        seriesList.add(newSeries);

        System.out.println("Series details have been successfully saved!");
    }
    //Search Method
    public void SearchSeries() {
        System.out.print("Enter the series ID to search: ");
        String queryId = scanner.nextLine();
        boolean found = false;

        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equalsIgnoreCase(queryId)) {
                System.out.println(series);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Series with Series Id: " + queryId + " was not found!");
        }
    }
    //Update Method
    public void UpdateSeries() {
        System.out.print("Enter the series ID to update age restriction: ");
        String updateId = scanner.nextLine();
        boolean found = false;

        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equalsIgnoreCase(updateId)) {
                int newAge = getValidAge();
                series.AgeRestriction = newAge;
                System.out.println("Age restriction updated successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Series with Series Id: " + updateId + " was not found!");
        }
    }
    //Delete Method
    public void DeleteSeries() {
        System.out.print("Enter the series ID to delete: ");
        String deleteId = scanner.nextLine();
        SeriesModel toDelete = null;

        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equalsIgnoreCase(deleteId)) {
                toDelete = series;
                break;
            }
        }

        if (toDelete != null) {
            System.out.print("Are you sure you want to delete this series? (yes/no): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                seriesList.remove(toDelete);
                System.out.println("Series deleted successfully.");
            } else {
                System.out.println("Deletion cancelled.");
            }
        } else {
            System.out.println("Series with Series Id: " + deleteId + " was not found!");
        }
    }
    //Report Method
    public void SeriesReport() {
        System.out.println("\n--- SERIES REPORT - 2025 ---");
        if (seriesList.isEmpty()) {
            System.out.println("No series available.");
        } else {
            int count = 1;
            for (SeriesModel series : seriesList) {
                System.out.println("Series " + count + ": " + series);
                count++;
            }
        }
    }
    //Exit Method
    public void ExitSeriesApplication() {
        System.out.println("Exiting application. Goodbye!");
        System.exit(0);
    }
    //Age Restriction Method
    private int getValidAge() {
        int ageRestriction = -1;
        while (true) {
            System.out.print("Enter the series age restriction: ");
            String ageInput = scanner.nextLine();

            try {
                ageRestriction = Integer.parseInt(ageInput);
                if (ageRestriction >= 2 && ageRestriction <= 18) {
                    break;
                } else {
                    System.out.println("You have entered an incorrect series age!");
                }
            } catch (NumberFormatException e) {
                System.out.println("You have entered an incorrect series age!");
            }
        }
        return ageRestriction;
    }
}