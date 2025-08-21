
package Node;

import Entity.Consultation;


public class Node {
    private Consultation data;
    private Node next;

    public Node(Consultation data) {
        this.data = data;
        this.next = null;
    }

    public Consultation getData() {
        return data;
    }

    public void setData(Consultation data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
