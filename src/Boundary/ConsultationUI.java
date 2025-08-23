package Boundary;

import java.util.Scanner;

public class ConsultationUI {
    
    private Scanner scanner = new Scanner(System.in);


    
    public void displayConsultationMenu() {
        System.out.println("============================================");
        System.out.println("              Consultation                  ");
        System.out.println("============================================");
        System.out.println("1. Add New Consultation Appointment");
        System.out.println("2. Update Consultation Appointment");
        System.out.println("3. Cancel Consultation");
        System.out.println("4. Search Consultation Appointment");
        System.out.println("5. List Awaiting Consultation Appointment");
        System.out.println("6. List All Consultation");
        System.out.println("7. Generate Reports");
        System.out.println("8. Exit");
        
    }
    
    public int getMenuChoice() {
        System.out.print("Choose an option: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            System.out.print("Choose an option: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    
}
