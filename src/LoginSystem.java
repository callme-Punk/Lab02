import java.io.*;
import java.util.*;

public class LoginSystem {

    private static final String file = "users.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Login System");
        if (login(scanner)) {
            System.out.println("Access Granted! Welcome!");
        } else {
            System.out.println("Access Denied!");
        }

        scanner.close();
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return authenticateUser(username, password);
    }

    private static boolean authenticateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users file: " + e.getMessage());
        }
        return false;
    }
}
