import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
public class RentalService {
    BikeService bikeService = new BikeService();
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private String bikeID;
    private boolean locationValid;
    private Deque<ERyderLog> systemLogs = new ArrayDeque<>();

    Queue<BikeRequest>bikeRequest = new ArrayDeque<>();
    final static double BASE_FARE=3.0;
    UserRegistration userRegistration = new UserRegistration();
    LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    RegisteredUsers newUser = null;
    public RentalService(BikeService bikeService) {
        this.bikeService = bikeService;
    }
    public void setLocationValid(boolean valid){
        locationValid=valid;
    }
    public void simulateApplicationInput(RegisteredUsers newUser){
        this.newUser = newUser;
        System.out.println("This is the simulation of the e-bike rental process.");
        System.out.println("Are you a registered user?");
        Scanner sc = new Scanner(System.in);
        isRegisteredUser = sc.nextBoolean();
        sc.nextLine();
        System.out.println("Please enter your email address");
        emailAddress = sc.nextLine();
        System.out.println("Please enter your location");
        location = sc.nextLine();
        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser,emailAddress,location);
        if(bikeID!=null)locationValid=true;
        if(!locationValid)
        {
            //sc.close();
            return;
        }
        System.out.println("Simulating e-bike reservation…");
        bikeService.reserveBike(bikeID);
        viewActiveRentals();
        System.out.println("Simulating the end of the trip…");
        removeTrip(bikeID);
        System.out.println("Displaying the active rentals after trip end…");
        viewActiveRentals();
        //sc.close();

    }
    private String analyseRequest(boolean isRegistered,String emailAddress,String location){
        if(isRegistered)System.out.println("Welcome back, "+emailAddress+"!");
        else{
            System.out.println(" You’re not our registered user. Please consider registering.");
            userRegistration.registration();
        }
        return bikeService.validateLocation(location);
    }
    private void viewActiveRentals(){
        if(activeRentalsList.isEmpty())System.out.println("No active rentals at the moment.");
        else {
            for(ActiveRental activeRental:activeRentalsList){
                System.out.println(activeRental);
            }
        }
    }
    public void removeTrip(String bikeID){
        newUser.calculateFare(BASE_FARE);
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while(iterator.hasNext()){
            ActiveRental rental = iterator.next();
            if(rental.getBikeID().equals(bikeID)){
                iterator.remove();
                break;
            }
        }
        Iterator<Bike>bikeIterator = BikeDatabase.bikes.iterator();
        while(bikeIterator.hasNext()){
            Bike bike = bikeIterator.next();
            if(bike.getBikeID().equals(bikeID)){
                bike.setIsAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Your trip has ended. Thank you for riding with us.");

                String logId = "TE" + System.currentTimeMillis();
                String event = "Trip ended for bikeID " + bikeID + " by " + emailAddress + " at " + bike.getLocation();
                LocalDateTime timestamp = LocalDateTime.now();
                ERyderLog endLog = new ERyderLog(logId, event, timestamp);
                systemLogs.push(endLog);
                
                if(!bikeRequest.isEmpty()){
                    bikeRequest.poll();
                }
                break;
            }
        }
    }
}
