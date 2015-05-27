/**
 * Created by ittaishay on 5/27/15.
 */
public class Delivery {
    private int deliveryID;
    private User sender;
    private User receiver;
    private User intermediary;
    private Coordinate destination;
    private Parcel parcel;

    // default constructor
    public Delivery() {
        deliveryID = 0;
        sender = new User();
        receiver = new User();
        intermediary = new User();
        destination = new Coordinate();
        parcel = new Parcel();
    }
}
