/**
 * Created by ittaishay on 5/27/15.
 */
public class DeliverySystem {
    private static final int max_users = 5000;
    private static final int grid_size = 100;

    private Administrator admin;
    private int totalDeliveriesCounter;
    private User[] users;
    private int[][] grid; // or change to use Google Maps
    private DeliveriesQueue activeDeliveries;

    // default constructor
    public DeliverySystem() {
        admin = new Administrator();
        totalDeliveriesCounter = 0;
        users = new User[max_users];
        grid = new int[grid_size][grid_size];
        activeDeliveries = new DeliveriesQueue();
    }
}
