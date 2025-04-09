package Online_Reservation_System;
import java.util.Scanner;
import java.util.Random;

public class PnrRecord {
    private int pnr_no;
    private String passenger_Name;
    private String train_no;
    private String class_Type;
    private String journey_Date;
    private String from;
    private String to;
    private static final int min = 1000;
    private static final int max = 9999;

    Scanner sc = new Scanner(System.in);

    public int getPnrNumber() {
        Random random = new Random();
        pnr_no = random.nextInt(max) + min;
        return pnr_no;
    }

    public String getPassengerName() {
        System.out.println("Enter the passenger name: ");
        passenger_Name = sc.nextLine();
        return passenger_Name;
    }

    public String getTrainNumber() {
        System.out.println("Enter the train number: ");
        train_no = sc.nextLine();
        return train_no;
    }

    public String getClassType() {
        System.out.println("Enter the class type: ");
        class_Type = sc.nextLine();
        return class_Type;
    }

    public String getJourneyDate() {
        System.out.println("Enter the Journey date (YYYY-MM-DD): ");
        journey_Date = sc.nextLine();
        return journey_Date;
    }

    public String getFrom() {
        System.out.println("Enter the starting place: ");
        from = sc.nextLine();
        return from;
    }

    public String getTo() {
        System.out.println("Enter the destination place: ");
        to = sc.nextLine();
        return to;
    }
}
