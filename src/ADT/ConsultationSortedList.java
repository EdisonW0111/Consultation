package ADT;

import Class.Consultation;
import Node.Node;

public class ConsultationSortedList implements SortedListInterface {

    private Node head;

    public ConsultationSortedList() {
        head = null;
    }

    // Add in sorted order
    public void add(String patientName, String doctorName, String date, String time) {
        Consultation c = new Consultation(patientName, doctorName, date, time);

        if (isBooked(date, time, doctorName)) {
            System.out.println("Doctor " + doctorName + " is unavailable for " + date + " " + time);
            return;
        }

        Node newNode = new Node(c);

        if (head == null || c.compareTo(head.getData()) < 0) {
            newNode.setNext(head);
            head = newNode;
            return;
        }

        Node current = head;
        while (current.getNext() != null && c.compareTo(current.getNext().getData()) > 0) {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);
    }

    // Check if slot is booked
    public boolean isBooked(String date, String time, String doctorName) {
        Node current = head;
        while (current != null) {
            Consultation existing = current.getData();

            if (existing.getDoctorName().equalsIgnoreCase(doctorName)
                    && existing.getDate().equals(date)) {

                // Convert times to minutes
                int bookedMinutes = toMinutes(existing.getTime());
                int newMinutes = toMinutes(time);

                if (Math.abs(bookedMinutes - newMinutes) < 30) {
                    return true; // conflict within 30 minutes
                }
            }
            current = current.getNext();
        }
        return false;
    }

    // Helper: HH:MM → minutes since midnight
    private int toMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    // Cancel a booking by date, time and doctor name
    public boolean cancel(String date, String time, String doctorName) {
        if (head == null) {
            return false; // list empty
        }

        // If the head is the one to remove
        if (head.getData().sameSlot(date, time)
                && head.getData().getDoctorName().equalsIgnoreCase(doctorName)) {
            head = head.getNext();
            return true;
        }

        Node current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().sameSlot(date, time)
                    && current.getNext().getData().getDoctorName().equalsIgnoreCase(doctorName)) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }

        return false; // not found
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    // Display list
    public void display() {
        Node current = head;
        while (current != null) {
            Consultation c = current.getData();
            System.out.println(c.getDate() + " " + c.getTime()
                    + " - " + c.getPatientName()
                    + " with Dr. " + c.getDoctorName());
            current = current.getNext();
        }
    }

}
