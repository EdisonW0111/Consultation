package ADT;

import Entity.Consultation;

/**
 * An interface for a sorted list of consultations.
 * The list maintains consultations in sorted order by date and time.
 * Each doctor cannot have overlapping appointments within 30 minutes.
 * 
 * @author 
 */
public interface SortedListInterface<T extends Comparable<T>> {

    /**
     * Task: Adds a new entry in sorted order.
     * If a business rule (like consultation slot clash) applies, 
     * the entry will not be added.
     *
     * @param newEntry the object to be added
     * @return true if the addition was successful, false otherwise
     */
    public boolean add(T newEntry);

    /**
     * Task: Cancels a given entry (must exactly match).
     *
     * @param targetEntry the object to cancel
     * @return true if the cancellation was successful, or false if not found
     */
    public boolean cancel(T targetEntry);

    /**
     * Task: Checks if a given entry already exists in the list.
     * For Consultation, this can enforce the 30-minute rule.
     *
     * @param targetEntry the object to check
     * @return true if the entry (or conflict) exists, false otherwise
     */
    public boolean contains(T targetEntry);

    /**
     * Task: Displays all entries in the list in sorted order.
     */
    public void display();

    /**
     * Task: Gets the number of entries currently in the list.
     *
     * @return the integer count of entries
     */
    public int size();

    /**
     * Task: Sees whether the list is empty.
     *
     * @return true if the list contains no entries, or false otherwise
     */
    public boolean isEmpty();
}