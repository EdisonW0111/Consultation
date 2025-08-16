package ADT;

import Class.Consultation;

public interface SortedListInterface {
    // Add a consultation in sorted order
    void add(String patientName, String doctorName, String date, String time);

    // Cancel a consultation by exact date, time, and doctor
    boolean cancel(String date, String time, String doctorName);

    // Check if a slot is booked (with 30-minute rule)
    boolean isBooked(String date, String time, String doctorName);

    // Display all consultations
    void display();

    // Get the number of consultations
    int size();

    // Check if list is empty
    boolean isEmpty();
}
