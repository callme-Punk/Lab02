import java.io.*;
import java.util.*;

public class CatalogManager {

    public static final String DATABASE_FILE = "catalog.csv";
    public List<Item> catalog;

    public CatalogManager() {
        this.catalog = new ArrayList<>();
        loadDatabase();
    }

    // Load data from the CSV file
    private void loadDatabase() {
        try (BufferedReader br = new BufferedReader(new FileReader(DATABASE_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) { // Updated to check for 4 fields
                    catalog.add(new Item(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the database file: " + e.getMessage());
        }
    }

    // Save data back to the CSV file
    private void saveDatabase() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATABASE_FILE))) {
            for (Item item : catalog) {
                bw.write(item.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to the database file: " + e.getMessage());
        }
    }

    // Display all catalog items
    public void displayItems() {
        System.out.println("Catalog Items:");
        for (int i = 0; i < catalog.size(); i++) {
            System.out.println((i + 1) + ". " + catalog.get(i));
        }
    }

    // Add a new item
    public void addItem(String id, String name, String description, String gender) {
        if (validateInput(id, name, description, gender)) {
            catalog.add(new Item(id, name, description, gender));
            saveDatabase(); // Ensure data is saved
            System.out.println("Item added successfully.");
        } else {
            System.out.println("Invalid input. All fields must be non-empty.");
        }
    }

    // Edit an existing item
    public void editItem(int index, String name, String description, String gender) {
        if (index < 0 || index >= catalog.size()) {
            System.out.println("Invalid item index.");
            return;
        }

        if (validateInput(name, description, gender)) {
            Item item = catalog.get(index);
            item.setName(name);
            item.setDescription(description);
            item.setGender(gender);
            saveDatabase();
            System.out.println("Item updated successfully.");
        } else {
            System.out.println("Invalid input. All fields must be non-empty.");
        }
    }
    

    // Delete an item
    public void deleteItem(int index) {
        if (index < 0 || index >= catalog.size()) {
            System.out.println("Invalid item index.");
            return;
        }

        catalog.remove(index);
        saveDatabase();
        System.out.println("Item deleted successfully.");
    }

    // Validate input fields
    private boolean validateInput(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CatalogManager manager = new CatalogManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display items");
            System.out.println("2. Add item");
            System.out.println("3. Edit item");
            System.out.println("4. Delete item");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manager.displayItems();
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();
                    manager.addItem(id, name, description, gender);
                    break;
                case 3:
                    manager.displayItems();
                    System.out.print("Enter item number to edit: ");
                    int editIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    System.out.print("Enter new Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new Description: ");
                    description = scanner.nextLine();
                    System.out.print("Enter new Gender: ");
                    gender = scanner.nextLine();
                    manager.editItem(editIndex, name, description, gender);
                    break;
                case 4:
                    manager.displayItems();
                    System.out.print("Enter item number to delete: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    manager.deleteItem(deleteIndex);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

// Class representing a catalog item
class Item {
    private String id;
    private String name;
    private String description;
    private String gender;

    public Item(String id, String name, String description, String gender) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Description: " + description + ", Gender: " + gender;
    }

    public String toCSV() {
        return id + "," + name + "," + description + "," + gender;
    }
}
