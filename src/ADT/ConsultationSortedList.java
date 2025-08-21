package ADT;

import Entity.Consultation;
import Node.Node;

public class ConsultationSortedList implements SortedListInterface<Consultation> {

    private Node<Consultation> head;

    public ConsultationSortedList() {
        head = null;
    }

    // Add in sorted order
    @Override
    public boolean add(Consultation newEntry) {
        if (contains(newEntry)) {
            System.out.println("Doctor " + newEntry.getDoctorName() +
                    " is unavailable for " + newEntry.getDate() + " " + newEntry.getTime());
            return false;
        }

        Node<Consultation> newNode = new Node<>(newEntry);

        if (head == null || newEntry.compareTo(head.getData()) < 0) {
            newNode.setNext(head);
            head = newNode;
            return true;
        }

        Node<Consultation> current = head;
        while (current.getNext() != null &&
                newEntry.compareTo(current.getNext().getData()) > 0) {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);
        return true;
    }

    // Cancel by matching consultation
    @Override
    public boolean cancel(Consultation targetEntry) {
        if (head == null) {
            return false; // list empty
        }

        // If the head is the one to remove
        if (head.getData().sameSlot(targetEntry.getDate(), targetEntry.getTime())
                && head.getData().getDoctorName().equalsIgnoreCase(targetEntry.getDoctorName())) {
            head = head.getNext();
            return true;
        }

        Node<Consultation> current = head;
        while (current.getNext() != null) {
            Consultation nextData = current.getNext().getData();
            if (nextData.sameSlot(targetEntry.getDate(), targetEntry.getTime())
                    && nextData.getDoctorName().equalsIgnoreCase(targetEntry.getDoctorName())) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false; // not found
    }

    // Check if slot is booked (with 30 min rule)
    @Override
    public boolean contains(Consultation targetEntry) {
        Node<Consultation> current = head;
        while (current != null) {
            Consultation existing = current.getData();
            if (existing.getDoctorName().equalsIgnoreCase(targetEntry.getDoctorName())
                    && existing.getDate().equals(targetEntry.getDate())) {
                // Convert times to minutes
                int bookedMinutes = toMinutes(existing.getTime());
                int newMinutes = toMinutes(targetEntry.getTime());
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

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        int count = 0;
        Node<Consultation> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    // Display list
    @Override
    public void display() {
        Node<Consultation> current = head;
        while (current != null) {
            Consultation c = current.getData();
            System.out.println(c.getDate() + " " + c.getTime()
                    + " - " + c.getPatientName()
                    + " with Dr. " + c.getDoctorName());
            current = current.getNext();
        }
    }

}
