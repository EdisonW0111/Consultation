package Entity; 

import java.util.Objects;

public class Consultation implements Comparable<Consultation>{

    private String patientName;
    private String doctorName;
    private String date; // Format: YYYY-MM-DD
    private String time; // Format: HH:MM (24-hour)

    public Consultation(String patientName, String doctorName, String date, String time) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
    }

    // Getters
    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    // Compare two consultations by date, then time
    @Override
    public int compareTo(Consultation other) {
        int dateCompare = this.date.compareTo(other.date);
        if (dateCompare != 0) {
            return dateCompare; // earlier date first
        }
        return this.time.compareTo(other.time); // same date → compare time
    }

    // Check if date, time and doctor match
    public boolean sameSlot(String date, String time, String doctorName) {
        return this.date.equals(date)
                && this.time.equals(time)
                && this.doctorName.equalsIgnoreCase(doctorName);
    }

    // Override equals to compare consultations
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Consultation)) return false;
        Consultation other = (Consultation) obj;
        return Objects.equals(patientName, other.patientName)
                && Objects.equals(doctorName, other.doctorName)
                && Objects.equals(date, other.date)
                && Objects.equals(time, other.time);
    }

    // Override hashCode
    @Override
    public int hashCode() {
        return Objects.hash(patientName, doctorName, date, time);
    }

    // For easy display
    @Override
    public String toString() {
        return date + " " + time + " - " + patientName + " with Dr. " + doctorName;
    }
}
