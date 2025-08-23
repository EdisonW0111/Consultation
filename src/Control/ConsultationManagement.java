package Control;

import ADT.ConsultationSortedList;
import ADT.SortedListInterface;
import Boundary.ConsultationUI;
import java.util.Scanner;
import Entity.Consultation;

public class ConsultationManagement {

    private Scanner scanner = new Scanner(System.in);

    private SortedListInterface consultationList = new ConsultationSortedList();

    public void addConsultation() {
        System.out.print("Enter patient IC number: ");
        String IC = scanner.nextLine();

        String patientName = scanner.nextLine();

        System.out.print("Enter doctor name: ");
        String doctorName = scanner.nextLine();

        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Enter time (HH:MM): ");
        String time = scanner.nextLine();

        Consultation newConsultation = new Consultation(patientName, doctorName, date, time);
        boolean added = consultationList.add(newConsultation);

        if (added) {
            System.out.println("Consultation appointment added successfully!");
        } else {
            System.out.println("Failed to add consultation (Slot already taken).");
        }
    }

    public void listConsultations() {
        if (consultationList.isEmpty()) {
            System.out.println("No consultations found.");
        } else {
            System.out.println("\n--- All Consultations ---");
            consultationList.display();
        }
    }

    public void listAwaitingAppointment() {
        if (consultationList.isEmpty()) {
            System.out.println("No consultations found.");
        } else {
            System.out.println("\n--- All Consultations ---");
            consultationList.listScheduledConsultations();
        }
    }

    public void cancelAppointment() {

    }

    public void updateConsultation() {

    }

    public void searchAppointment() {
        ConsultationUI ui = new ConsultationUI();
        boolean exit = false;
        
        ui.displaySearchConsultationMenu();
        int choice = ui.getChoice();

        while (!exit) {
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public void generateReport() {

    }

    public static void main(String[] args) {
        ConsultationUI ui = new ConsultationUI();
        ConsultationManagement cm = new ConsultationManagement();
        boolean exit = false;

        while (!exit) {
            ui.displayConsultationMenu();
            int choice = ui.getChoice();

            switch (choice) {
                case 1:
                    cm.addConsultation();
                    break;
                case 2:
                    cm.updateConsultation();
                    break;
                case 3:
                    cm.cancelAppointment();
                    break;
                case 4:
                    cm.searchAppointment();
                    break;
                case 5:
                    cm.listAwaitingAppointment();
                    break;
                case 6:
                    cm.listConsultations();
                    break;
                case 7:
                    cm.generateReport();
                    break;
                case 8:
                    System.out.println("Exiting... Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
            System.out.println();
        }
    }
}
