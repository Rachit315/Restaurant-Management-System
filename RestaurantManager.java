import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner; 

public class RestaurantManager { 
    private List<MenuItem> menu = new ArrayList<>(); 
    private List<Order> orderHistory = new ArrayList<>(); 
    private Scanner scanner = new Scanner(System.in); 

    public void loadDefaultMenu() { 
        // Clear existing menu items to prevent duplicates if method is called multiple times
        menu.clear();
        menu.add(new MenuItem("Burger", 5.99)); 
        menu.add(new MenuItem("Pizza", 8.99)); 
        menu.add(new MenuItem("Coke", 1.99)); 
    } 

    public void showMenu() { 
        System.out.println("\n------ MENU ------"); 
        if (menu.isEmpty()) {
            System.out.println("No items in menu.");
        } else {
            for (int i = 0; i < menu.size(); i++) { 
                System.out.println((i + 1) + ". " + menu.get(i)); 
            } 
        }
        System.out.println("------------------"); 
    } 

    public void addMenuItem() { 
        try {
            System.out.print("Enter new item name: "); 
            String name = scanner.nextLine(); 
            System.out.print("Enter price: "); 
            double price = scanner.nextDouble(); 
            scanner.nextLine(); // consume newline 
            menu.add(new MenuItem(name, price)); 
            System.out.println("Item added successfully!"); 
        } catch (Exception e) {
            System.out.println("Error adding item. Please try again.");
            scanner.nextLine(); // Clear the scanner buffer
        }
    } 

    public void takeOrder() { 
        if (menu.isEmpty()) {
            System.out.println("Cannot take order: Menu is empty.");
            return;
        }
        
        Order order = new Order(); 
        while (true) { 
            showMenu(); 
            try {
                System.out.print("Select item number to add to order (0 to finish): "); 
                int choice = scanner.nextInt(); 
                scanner.nextLine(); // consume newline 

                if (choice == 0) break; 
                if (choice < 1 || choice > menu.size()) { 
                    System.out.println("Invalid choice. Try again."); 
                } else { 
                    order.addItem(menu.get(choice - 1)); 
                    System.out.println("Item added."); 
                } 
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        } 

        if (!order.getItems().isEmpty()) { 
            order.displayBill(); 
            orderHistory.add(order); 
        } else { 
            System.out.println("Order was empty!"); 
        } 
    } 

    public void viewOrderHistory() { 
        System.out.println("\n--- ORDER HISTORY ---"); 
        if (orderHistory.isEmpty()) { 
            System.out.println("No orders yet."); 
            return; 
        } 

        int count = 1; 
        for (Order o : orderHistory) { 
            System.out.println("Order #" + count++); 
            o.displayBill(); 
        } 
    } 

    public void start() { 
        loadDefaultMenu(); // Make sure this line is executed 
        while (true) { 
            try {
                System.out.println("\n--- RESTAURANT SYSTEM ---"); 
                System.out.println("1. View Menu"); 
                System.out.println("2. Take Order"); 
                System.out.println("3. Add Menu Item"); 
                System.out.println("4. View Order History"); 
                System.out.println("5. Exit"); 
                System.out.print("Enter your choice: "); 
                int choice = scanner.nextInt(); 
                scanner.nextLine(); // consume newline 

                if (choice == 1) {
                    showMenu();
                } else if (choice == 2) {
                    takeOrder();
                } else if (choice == 3) {
                    addMenuItem();
                } else if (choice == 4) {
                    viewOrderHistory();
                } else if (choice == 5) {
                    System.out.println("Thanks for using the system. Goodbye!"); 
                    return;
                } else {
                    System.out.println("Invalid option, try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        } 
    } 
}