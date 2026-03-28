import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class BikeRental{
    
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;
    private UserRegistration userRegistration;
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalsList;


    public BikeRental(boolean isRegisteredUser, String emailAddress, String location, LocalDateTime tripStartTime,String bikeID,boolean locationValid,UserRegistration userRegistration,LinkedList<ActiveRental> activeRentalsList)
    {
        this.isRegisteredUser = isRegisteredUser;
        this.emailAddress = emailAddress;
        this.location = location;
        this.tripStartTime = tripStartTime;
        this.bikeID = bikeID;
        this.locationValid = locationValid;
        this.userRegistration = new UserRegistration();
        this.activeRentalsList = new LinkedList<>();
    }

    public BikeRental()
    {

    }

    public void simulateApplicationInput()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("This is the simulation of the e-bike rental process.\n");
        System.out.println("Are you a registered user? (true or false)\n");
        boolean isRegisteredUser = sc.nextBoolean();
        sc.nextLine();
        System.out.println("Enter your email address:\n");
        String emailAddress = sc.nextLine();
        System.out.println("Enter your location:\n");
        String location = sc.nextLine();

        System.out.println("Simulating the analysis of the rental request.\n");
        this.bikeID = analyseRequest(isRegisteredUser,emailAddress,location);

        if(!locationValid)
        {
            sc.close();
            return;
        }else{
        System.out.println("Simulating e-bike reservation...");
        reserveBike(bikeID);
        System.out.println("Displaying the active rentals…");
        viewActiveRentals();
        System.out.println("Simulating the end of the trip…");
        removeTrip(bikeID);
        System.out.println("Displaying the active rentals after trip end…");
        viewActiveRentals();
        }
        sc.close();
    }

    private String analyseRequest(boolean isRegistered,String emailAddress,String location)
    {
        if(isRegistered)
        {
            System.out.println("Welcome back, " + emailAddress + "!\n");
        }else{
            System.out.println("You’re not our registered user. Please consider registering.\n");
            userRegistration.registration();
        }
        return validate(location);
    }

    private String validate(String location)
    {
        return bikeID;
    }

    private String validateLocation(String location)
    {
        for(Bike bike:BikeDatabase.bikes)
        {
            if(bike.getLocation().equals(location) && bike.isAvailable())
            {
                System.out.println(" A bike is available at the location you requested.\n");

                locationValid = true;

                return bike.getbikeID();
            }

        }

            System.out.println(" Sorry, no bikes are available at the location you\r\n" + //
                                "requested. Please try again later.");
            return null;
        
    }

    private void reserveBike(String bikeID)
    {
        if(bikeID != null)
        {
            for(Bike bike:BikeDatabase.bikes)
            {
                if(bike.getbikeID().equals(bikeID))
                {
                    this.tripStartTime = java.time.LocalDateTime.now();

                    bike.setAvailable(false);
                    bike.setLastUsedTime(tripStartTime);

                    System.out.println(" Reserving the bike with the (bikeID). Please following the on-screen instructionsto locate the bike and start your pleasant journey");

                    ActiveRental rental = new ActiveRental(bikeID, emailAddress,tripStartTime);
                    this.activeRentalsList.add(rental);

                    break;
                }
                else{
                    System.out.println(" Sorry, we’re unable to reserve a bike at this time. Please try again later.");
                }
            }
        }
    }

    private void viewActiveRentals()
    {
        if(activeRentalsList.isEmpty())
        {
            System.out.println("No active rentals at the moment.\n");
        }else{
            for(ActiveRental rental:activeRentalsList)
            {
                System.out.println(rental);
            }
        }
    }

    private void removeTrip(String bikeID)
    {
        if(activeRentalsList != null)
        {
            java.util.Iterator <ActiveRental> iterator = activeRentalsList.iterator();

            while(iterator.hasNext())
            {
                ActiveRental rental = iterator.next();
                if(rental.getBikeID().equals(bikeID))
                {
                    iterator.remove();

                    if (BikeDatabase.bikes != null)
                    {
                    for (Bike bike : BikeDatabase.bikes)
                    {
                        if (bike.getbikeID().equals(bikeID))
                        {
                            bike.setAvailable(true);                          
                            bike.setLastUsedTime(java.time.LocalDateTime.now());

                            break; 
                        }
                    }
                }
                System.out.println(" Your trip has ended. Thank you for riding with us.\n");
                break;
                }
            }
        }
    }
}

