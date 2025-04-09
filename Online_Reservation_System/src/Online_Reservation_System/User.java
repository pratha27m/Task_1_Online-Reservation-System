package Online_Reservation_System;
import java.util.Scanner;

public class User {
    private String user_name;
    private String pass;
    Scanner sc = new Scanner(System.in);

    public String getUsername() {
        System.out.println("Enter Username: ");
        user_name = sc.nextLine();
        return user_name;
    }

    public String getPassword() {
        System.out.println("Enter Password: ");
        pass = sc.nextLine();
        return pass;
    }
}
