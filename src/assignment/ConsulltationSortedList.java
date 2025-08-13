/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
public class ConsulltationSortedList {
    String patientName;
    String doctorName;
    String timeSlot; // Format: "HH:MM" or "YYYY-MM-DD HH:MM"

    ConsulltationSortedList(String patientName, String doctorName, String timeSlot) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.timeSlot = timeSlot;
    }
}

// Node for the sorted list
class Node {

    ConsulltationSortedList data;
    Node next;

    Node(ConsulltationSortedList data) {
        this.data = data;
        this.next = null;
    }
}

// Custom Sorted List
class SortedConsultationList {

    private Node head;

    public SortedConsultationList() {
        head = null;
    }

    // Insert in sorted order (earliest time first)
    public void add(ConsulltationSortedList c) {
        // Prevent duplicate bookings
        if (isBooked(c.timeSlot)) {
            System.out.println("Time slot " + c.timeSlot + " is already booked!");
            return;
        }

        Node newNode = new Node(c);

        if (head == null || c.timeSlot.compareTo(head.data.timeSlot) < 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null
                && current.next.data.timeSlot.compareTo(c.timeSlot) < 0) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    // Check if a time slot is already booked
    public boolean isBooked(String timeSlot) {
        Node current = head;
        while (current != null) {
            if (current.data.timeSlot.equals(timeSlot)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Display all consultations
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data.timeSlot + " - "
                    + current.data.patientName + " with Dr. "
                    + current.data.doctorName);
            current = current.next;
        }
    }


}
