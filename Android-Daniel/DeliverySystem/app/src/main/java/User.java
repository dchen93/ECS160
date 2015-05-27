/**
 * Created by ittaishay on 5/27/15.
 */
import java.util.*;

enum Transportation {
    WALK, BIKE, DRIVE
}

public class User {
    public static final int max_friends = 500;
    public static final String def_string = "";
    public static final int def_rating = 5;
    public static final int max_delivery_history_size = 100;

    private String userID;
    private String displayName;
    private String userEmail;
    private Coordinate location;
    private Coordinate deliveryDestination;
    private User[] friends;
    private int numFriends;
    private Parcel parcel;
    private Transportation transportation;
    private boolean available;
    private boolean doNotDisturb;
    private boolean emailValidated;
    private int rating;
    private Delivery[] deliveryHistory;

    // default constructor
    public User() {
        userID = def_string;
        displayName = def_string;
        userEmail = def_string;
        location = new Coordinate();
        deliveryDestination = new Coordinate();
        friends = new User[max_friends];
        numFriends = 0;
        parcel = new Parcel();
        transportation = Transportation.WALK;
        available = true;
        doNotDisturb = false;
        emailValidated = false;
        rating = def_rating;
        deliveryHistory = new Delivery[max_delivery_history_size];
    }
}
