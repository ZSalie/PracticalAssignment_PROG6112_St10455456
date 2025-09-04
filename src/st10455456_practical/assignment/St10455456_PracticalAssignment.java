/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package st10455456_practical.assignment;
import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */

public class St10455456_PracticalAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Display Method to choose between Section A and B 
        System.out.println("WELCOME TO The PRACTICAL ASSIGNMENT");
        System.out.println("Select a section to launch:");
        System.out.println("1) TV Series Manager (Section A)");
        System.out.println("2) Bookstore System (Section B)");
        System.out.print("Choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                Series seriesManager = new Series();
                seriesManager.launchMenu(); 
                break;
            case "2":
                Bookstore store = new Bookstore();
                store.launch();
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
        }
    }
}
