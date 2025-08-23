package Control;

import ADT.ConsultationSortedLinkedList;
import Boundary.ConsultationUI;
import java.util.Scanner;
import Entity.Consultation;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import ADT.SortedLinkedListInterface;

public class ConsultationManagement {

    ConsultationUI ui = new ConsultationUI();
    private Scanner scanner = new Scanner(System.in);

    private SortedLinkedListInterface consultationList = new ConsultationSortedLinkedList();

    public void addConsultation() {
        //System.out.print("Enter patient IC number: ");
        //String IC = scanner.nextLine();

        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();

        System.out.print("Enter doctor name: ");
        String doctorName = scanner.nextLine();

        String date = getValidatedDate(ui);
        String time = getValidatedTime(ui);

        Consultation newConsultation = new Consultation(patientName, doctorName, date, time);
        boolean added = consultationList.add(newConsultation);

        if (added) {
            System.out.println("Consultation appointment added successfully!");
        } else {
            System.out.println("Failed to add consultation (Slot already taken).");
        }
    }

    public String getValidatedDate(ConsultationUI ui) {
        String date;
        do {
            date = ui.getDateInput();  // get raw input from UI
            if (!isValidDate(date)) {
                System.out.println("Invalid date! Please enter today or a future date in format YYYY-MM-DD.");
            }
        } while (!isValidDate(date));  // repeat until valid
        return date;
    }

    public String getValidatedTime(ConsultationUI ui) {
        String time;
        do {
            time = ui.getTimeInput(); // get raw input from UI
            if (!isValidTime(time)) {
                System.out.println("Invalid time! Please enter in HH:MM format (24-hour).");
            }
        } while (!isValidTime(time));
        return time;
    }

    // Validator for date (must be today or future)
    public boolean isValidDate(String date) {
        try {
            LocalDate enteredDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate today = LocalDate.now();
            return !enteredDate.isBefore(today); // disallow past dates
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    //Validator for time
    public boolean isValidTime(String time) {
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void listConsultations() {
        if (consultationList.isEmpty()) {
            System.out.println("No consultations found.");
        } else {
            printCenteredTitle("ALL CONSULTATIONS", 85);
            consultationList.display();
        }
    }

    public void listAwaitingAppointment() {
        if (consultationList.isEmpty()) {
            System.out.println("No consultations found.");
        } else {
            printCenteredTitle("SCHEDULED CONSULTATIONS", 85);
            consultationList.listScheduledConsultations();
        }
    }

    public void printCenteredTitle(String title, int width) {
        int padding = (width - title.length()) / 2;
        String line = "=".repeat(width); // decorative line
        System.out.println("\n" + line);
        System.out.printf("%" + (padding + title.length()) + "s%n", title);
        System.out.println(line);
    }

    public void cancelAppointment() {

    }

    public void updateConsultation() {
        ConsultationUI ui = new ConsultationUI();
        boolean exit = false;

        while (!exit) {
            ui.UpdateConsultationAppointmentMenu();
            int choice = ui.getChoice();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    appointmentCheckin();
                    break;
                case 3:
                    appointmentComplete();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public boolean appointmentCheckin() {
        ConsultationUI ui = new ConsultationUI();
        int id = ui.getConsultationIDInput(); // assume UI has method to get ID

        // Cancel if user enters 0
        if (id == 0) {
            System.out.println("❌ Update cancelled by user.");
            return false;
        }

        Consultation target = new Consultation("temp", "temp", "0000-00-00", "00:00");
        // temp object only to carry ID
        try {
            java.lang.reflect.Field idField = Consultation.class.getDeclaredField("consultationID");
            idField.setAccessible(true);
            idField.set(target, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean success = consultationList.updateStatusToCheckIn(target);

        if (success) {
            System.out.println("Consultation " + id + " status updated to CHECKED_IN.");

        } else {
            System.out.println("Consultation " + id + " not found.");
        }
        return success;
    }

    public boolean appointmentComplete() {
        ConsultationUI ui = new ConsultationUI();
        int id = ui.getConsultationIDInput(); // assume UI has method to get ID

        Consultation target = new Consultation("temp", "temp", "0000-00-00", "00:00");
        // temp object only to carry ID
        try {
            java.lang.reflect.Field idField = Consultation.class.getDeclaredField("consultationID");
            idField.setAccessible(true);
            idField.set(target, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean success = consultationList.updateStatusToCompleted(target);

        if (success) {
            System.out.println("Consultation " + id + " status updated to COMPLETED.");
        } else {
            System.out.println("Consultation " + id + " not found or not in CHECKED_IN state.");
        }

        return true;
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
