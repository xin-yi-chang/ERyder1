import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    List<RegisteredUsers> registeredUsers = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    UserService userService = new UserService();
    public void userManagementOptions() {
        System.out.println("Welcome to E-Ryder Administrator Panel.");
        System.out.println("What do you want to do?");
        System.out.println("1. Add New Users");
        System.out.println("2. View Registered Users");
        System.out.println("3. Remove Registered Users");
        System.out.println("4. Update Registered Users");
        System.out.println("5. Demo the Bike Rental System");
        System.out.println("6. EXIT");
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                userService.addNewUsers();
                break;
            case 2:
                userService.viewRegisteredUsers();
                break;
            case 3:
                userService.removeRegisteredUsers();
                break;
            case 4:
                userService.updateRegisteredUsers();
                break;
            case 5:
                RentalService bikeRental = new RentalService();
                bikeRental.simulateApplicationInput();
                break;
            case 6:
                System.out.println("Exiting program. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    
}