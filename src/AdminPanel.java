import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminPanel
{
    List<RegisteredUsers>registeredUsers = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void userManagementOptions()
    {
        System.out.println("Welcome to E-Ryder Admininstrator Panel.\r\n" + //
                        "What doyouwant to do?\r\n" + //
                        "1.Add New Users\r\n" + //
                        "2.View Registered Users\r\n" + //
                        "3.Remove Registered Users\r\n" + //
                        "4.Update Registered Users\r\n" + //
                        "5.Demo the Bike Rental System\r\n" + //
                        "6.EXIT\r\n" + //
                        "Enter your choice:");
        int choice = sc.nextInt();
        sc.nextLine();

        switch(choice){
            case 1:
                addNewUsers();
                break;
            case 2:
                viewRegisteredUsers();
                break;
            case 3:
                removeRegisteredUsers();
                break;
            case 4:
                updateRegisteredUsers();
                break;
            case 5:
                BikeRental bikeRental = new BikeRental();
                bikeRental.simulateApplicationInput();
                break;
            case 6:
                System.out.println("Exit the program.");
                System.exit(0);
                break;
           default:
            System.out.println("Invalid choice.Please try again.");
        }
    }
    private void addNewUsers()
    {
        String fullName;
        String emailAddress;
        String dateOfBirth;
        long cardNumber;
        String cardProvider;
        String cardExpiryDate;
        int cvv;
        String userType;
        String[] lastThreeTrips = new String[3];

        System.out.println("How many users would you like to add?\n");
        int userCount = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < userCount; i++){
            System.out.println("Please enter the full name:\n");
            fullName = sc.nextLine();

            System.out.println("Please enter the email address:\n");
            emailAddress = sc.nextLine();

            System.out.println("PLease enter the date of birth:(in the form of YYYY-MM-DD)\n");
            dateOfBirth = sc.nextLine();
            //LocalDate dob = LocalDate.parse(dateOfBirth);

            System.out.println("PLease enter the card number:\n");
            cardNumber = Long.parseLong(sc.nextLine());

            System.out.println("PLease enter the card provider:\n");
            cardProvider = sc.nextLine();

            System.out.println("PLease enter the card expiry date:\n");
            cardExpiryDate = sc.nextLine();

            System.out.println("PLease enter the cvv:\n");
            cvv = Integer.parseInt(sc.nextLine());

            System.out.println("PLease enter the user type:\n");
            userType = sc.nextLine();
            System.out.println("Please enter the last three trips:");

            for (int j = 0;j < 3;j++){
                System.out.println("Enter the trip date(YYYY-MM-DD):");
                String tripDate = sc.nextLine();

                System.out.println("Enter the source and the destination:");
                String route = sc.nextLine();

                System.out.println("Enter the fee:");
                double tripFee = Double.parseDouble(sc.nextLine());

                System.out.println("Input your feedback (can be null) :");
                        String feedback = sc.nextLine();
                if(feedback.isEmpty())
                            {
                    feedback = "NULL";
                }

            StringBuilder tripBuilder = new StringBuilder();
                    tripBuilder.append("Date: ").append(tripDate)
                            .append(",Route:").append(route)
                            .append(", Fee(€): ").append(tripFee)
                            .append(", Feedback: ").append(feedback);
                    
                    lastThreeTrips[j] = tripBuilder.toString();
            }

            RegisteredUsers user = new RegisteredUsers(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);
            
            registeredUsers.add(user);
        }
        
    }
    private void viewRegisteredUsers()
    {
        if (registeredUsers.isEmpty()){
            System.out.println("No registered users to display");
        }else
            {
            for (int i = 0; i < registeredUsers.size(); i ++){
                System.out.println(registeredUsers.get(i));
                }
            }
    }

    private void removeRegisteredUsers()
    {
        Scanner scanner = new Scanner(System.in);

        if (registeredUsers.isEmpty()){
            System.out.println("No registered users to remove");
        }else{
            String emailToRemove = scanner.nextLine();
            boolean found = false;
            Iterator<RegisteredUsers> iterator = registeredUsers.iterator();

            while (iterator.hasNext()){
                RegisteredUsers user = iterator.next();

                if (user.getEmailAddress().equals(emailToRemove)){
                   found = true;
                   iterator.remove();
                   break;
                }
            }

            if (!found){
                System.out.println("No user found with this email address");
            }
        }
        scanner.close();
    }

    private void updateRegisteredUsers()
    {
        boolean found = false;
        Scanner scanner = new Scanner(System.in);
        String emailToUpdate = scanner.nextLine();
        RegisteredUsers userToUpdate = null;

        for (RegisteredUsers user : registeredUsers){
            if (user.getEmailAddress().equals(emailToUpdate)){
                userToUpdate = user;
                found = true;

            }else {
                System.out.println("No user found with this email address");
                userToUpdate = null;
            }
        }

        if (userToUpdate == null){
            System.out.println("No registered users to remove");
            return;
        }

        System.out.println("Type new full name(Press ENTER for no change):");
        String newFullName = scanner.nextLine();
        if (!newFullName.isEmpty()){
            userToUpdate.setFullName(newFullName);
        }

        System.out.println("Type new card number(Enter '0' for no change):");
        String newCardNumberInput = scanner.nextLine();
        if (!newCardNumberInput.isEmpty() && !newCardNumberInput.equals("0")){
            long newCardNumber = Long.parseLong(newCardNumberInput);
            userToUpdate.setCardNumber(newCardNumber);
        }
        scanner.close();

    }
}