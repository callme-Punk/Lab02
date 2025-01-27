//import java.io.*;
//import java.util.Scanner;
//
//// Display all catalog items
//    public void displayItems() {
//        System.out.println("Catalog Items:");
//        for (int i = 0; i < catalog.size(); i++) {
//            System.out.println((i + 1) + ". " + catalog.get(i));
//        }
//    }
//
//    // Add a new item
//    public void addItem(String id, String name, String description) {
//        if (validateInput(id, name, description)) {
//            catalog.add(new Item(id, name, description));
//            saveDatabase();
//            System.out.println("Item added successfully.");
//        } else {
//            System.out.println("Invalid input. All fields must be non-empty.");
//        }
//    }
//
//    // Edit an existing item
//    public void editItem(int index, String name, String description) {
//        if (index < 0 || index >= catalog.size()) {
//            System.out.println("Invalid item index.");
//            return;
//        }
//
//        if (validateInput(name, description)) {
//            Item item = catalog.get(index);
//            item.setName(name);
//            item.setDescription(description);
//            saveDatabase();
//            System.out.println("Item updated successfully.");
//        } else {
//            System.out.println("Invalid input. All fields must be non-empty.");
//        }
//    }
//
//    // Sort the catalog by ID
//    public void sortCatalogById() {
//        catalog.sort(Comparator.comparing(Item::getId));
//        saveDatabase();
//        System.out.println("Catalog sorted by ID.");
//    }
//
//
//    import java.io.*;
//        import java.util.*;
//
//        public class LoginSystem {
//
//            private static final String USERS_FILE = "users.csv";
//
//            public static void main(String[] args) {
//                Scanner scanner = new Scanner(System.in);
//
//                System.out.println("Welcome to the Login System");
//                System.out.println("1. Register");
//                System.out.println("2. Login");
//                System.out.print("Choose an option: ");
//
//                int choice = scanner.nextInt();
//                scanner.nextLine(); // Consume newline
//
//                if (choice == 1) {
//                    register(scanner);
//                } else if (choice == 2) {
//                    if (login(scanner)) {
//                        System.out.println("Access Granted! Launching the catalog system...");
//                        CatalogManager manager = new CatalogManager();
//                        manager.displayItems();
//                    } else {
//                        System.out.println("Access Denied!");
//                    }
//                } else {
//                    System.out.println("Invalid choice. Exiting.");
//                }
//
//                scanner.close();
//            }
//
//            private static void register(Scanner scanner) {
//                System.out.print("Enter a new username: ");
//                String username = scanner.nextLine();
//
//                System.out.print("Enter a new password: ");
//                String password = scanner.nextLine();
//
//                System.out.print("Enter your ID: ");
//                String id = scanner.nextLine();
//
//                if (saveUser(username, password, id)) {
//                    System.out.println("Registration successful! You can now log in.");
//                } else {
//                    System.out.println("Registration failed. Username might already exist.");
//                }
//            }
//
//            private static boolean login(Scanner scanner) {
//                System.out.print("Enter username: ");
//                String username = scanner.nextLine();
//
//                System.out.print("Enter password: ");
//                String password = scanner.nextLine();
//
//                return authenticateUser(username, password);
//            }
//
//            private static boolean saveUser(String username, String password, String id) {
//                // Check if the user already exists
//                if (doesUserExist(username)) {
//                    return false;
//                }
//
//                try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
//                    writer.write(username + "," + password + "," + id);
//                    writer.newLine();
//                    return true;
//                } catch (IOException e) {
//                    System.err.println("Error writing to users file: " + e.getMessage());
//                    return false;
//                }
//            }
//
//            private static boolean doesUserExist(String username) {
//                try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        String[] parts = line.split(",");
//                        if (parts.length > 0 && parts[0].equals(username)) {
//                            return true;
//                        }
//                    }
//                } catch (IOException e) {
//                    System.err.println("Error reading users file: " + e.getMessage());
//                }
//                return false;
//            }
//
//            private static boolean authenticateUser(String username, String password) {
//                try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        String[] parts = line.split(",");
//                        if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
//                            return true;
//                        }
//                    }
//                } catch (IOException e) {
//                    System.err.println("Error reading users file: " + e.getMessage());
//                }
//                return false;
//            }
//        }
