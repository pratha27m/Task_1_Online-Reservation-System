package Online_Reservation_System;
import java.sql.*;
import java.util.Scanner;

public class OnlineReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User u1 = new User();
        String user_name = u1.getUsername();
        String pass = u1.getPassword();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver Loaded Successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading JDBC driver: " + e.getMessage());
            return;
        }

        String url = "jdbc:mysql://localhost:3306/prtham"; 

        try (Connection connection = DriverManager.getConnection(url, user_name, pass)) {
            System.out.println("User Connection Granted.\n");

            while (true) {
                String InsertQuery = "INSERT INTO reservations VALUES (?, ?, ?, ?, ?, ?, ?)";
                String DeleteQuery = "DELETE FROM reservations WHERE pnr_number = ?";
                String ShowQuery = "SELECT * FROM reservations";

                System.out.println("Enter the choice: ");
                System.out.println("1. Insert Record");
                System.out.println("2. Delete Record");
                System.out.println("3. Show All Records");
                System.out.println("4. Exit");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                if (choice == 1) {
                    PnrRecord p1 = new PnrRecord();
                    int pnr_no = p1.getPnrNumber();
                    String passenger_Name = p1.getPassengerName();
                    String train_no = p1.getTrainNumber();
                    String class_Type = p1.getClassType();
                    String journey_Date = p1.getJourneyDate();
                    String from = p1.getFrom();
                    String to = p1.getTo();

                    try (PreparedStatement preparedStatement = connection.prepareStatement(InsertQuery)) {
                        preparedStatement.setInt(1, pnr_no);
                        preparedStatement.setString(2, passenger_Name);
                        preparedStatement.setString(3, train_no);
                        preparedStatement.setString(4, class_Type);
                        preparedStatement.setString(5, journey_Date);
                        preparedStatement.setString(6, from);
                        preparedStatement.setString(7, to);

                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Record added successfully.");
                        } else {
                            System.out.println("No records were added.");
                        }
                    } catch (SQLException e) {
                        System.err.println("SQLException: " + e.getMessage());
                    }
                }

                else if (choice == 2) {
                    System.out.println("Enter the PNR number to delete the record: ");
                    int pnr_no = sc.nextInt();
                    try (PreparedStatement preparedStatement = connection.prepareStatement(DeleteQuery)) {
                        preparedStatement.setInt(1, pnr_no);
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Record deleted successfully.");
                        } else {
                            System.out.println("No records were deleted.");
                        }
                    } catch (SQLException e) {
                        System.err.println("SQLException: " + e.getMessage());
                    }
                }

                else if (choice == 3) {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(ShowQuery);
                         ResultSet resultSet = preparedStatement.executeQuery()) {
                        System.out.println("\nAll records printing.\n");
                        while (resultSet.next()) {
                            String pnr_no = resultSet.getString("pnr_number");
                            String passenger_Name = resultSet.getString("passenger_name");
                            String train_no = resultSet.getString("train_number");
                            String class_Type = resultSet.getString("class_type");
                            String journey_Date = resultSet.getString("journey_date");
                            String from_Location = resultSet.getString("from_location");
                            String to_Location = resultSet.getString("to_location");

                            System.out.println("PNR Number: " + pnr_no);
                            System.out.println("Passenger Name: " + passenger_Name);
                            System.out.println("Train Number: " + train_no);
                            System.out.println("Class Type: " + class_Type);
                            System.out.println("Journey Date: " + journey_Date);
                            System.out.println("From Location: " + from_Location);
                            System.out.println("To Location: " + to_Location);
                            System.out.println("--------------");
                        }
                    } catch (SQLException e) {
                        System.err.println("SQLException: " + e.getMessage());
                    }
                }

                else if (choice == 4) {
                    System.out.println("Exiting the program.\n");
                    break;
                }

                else {
                    System.out.println("Invalid Choice Entered.\n");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        sc.close();
    }
}
