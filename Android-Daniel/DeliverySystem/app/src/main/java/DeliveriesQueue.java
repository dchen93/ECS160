/**
 * Created by ittaishay on 5/27/15.
 */
public class DeliveriesQueue {
    private DeliveryNode top, bottom;
    private int numActive;

    public DeliveriesQueue() {
        top = null;
        bottom = null;
        numActive = 0;
    }

    public boolean IsEmpty() {
        return top == null;
    }

    public int Size() {
        return numActive;
    }

    public Delivery Peek() {
        if (IsEmpty()) {
            // throw exception
        }
        return top.delivery;
    }

    public void Enqueue(Delivery d) {
        DeliveryNode node = new DeliveryNode(d);
        if (IsEmpty()) {
            top = node;
            bottom = node;
        }
        else {
            bottom.next = node;
            bottom = node;
        }
        numActive++;
    }

    public Delivery Dequeue(Delivery d) {
        if (IsEmpty()) {
            // throw exception
        }
        Delivery temp = top.delivery;
        top = top.next;
        numActive--;
        if (IsEmpty()) {
            bottom = null;
        }
        return temp;
    }
}

class DeliveryNode {
    protected Delivery delivery;
    protected DeliveryNode next;

    public DeliveryNode(Delivery d) {
        delivery = d;
    }
}
