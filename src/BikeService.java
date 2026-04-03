import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
public class BikeService {
    private String emailAddress;
    private LocalDateTime tripStartTime;
    private boolean locationValid;
    UserRegistration userRegistration = new UserRegistration();
    LinkedList<ActiveRental> activeRentalsList = new LinkedList<>();
    private Deque<ERyderLog> systemLogs = new ArrayDeque<>();

    Queue<BikeRequest>bikeRequest = new ArrayDeque<>();
    public void reserveBike(String bikeID){
        if(bikeID!=null){
            for(Bike bike:BikeDatabase.bikes){
                if(bikeID.equals(bike.getBikeID())){
                    String logId = "BR" + System.currentTimeMillis();
                    String event = "Bike with bikeID " + bikeID + " was rented by " + emailAddress + " from " + bike.getLocation();
                    LocalDateTime timestamp = LocalDateTime.now();
        
                    ERyderLog rentLog = new ERyderLog(logId, event, timestamp);
                    systemLogs.push(rentLog);

                    tripStartTime = LocalDateTime.now();
                    bike.setIsAvailable(false);
                    bike.setLastUsedTime(tripStartTime);
                    System.out.println(" Reserving the bike with the "+bikeID+". Please following the on-screen instructions." + 
                                        "to locate the bike and start your pleasant journey.");

                    String tripLogId = "TS" + System.currentTimeMillis();
                    String tripEvent = "Trip started for bikeID " + bikeID + " by " + emailAddress + " at " +bike.getLocation();
                    ERyderLog tripStartLog = new ERyderLog(tripLogId, tripEvent, LocalDateTime.now());
                    systemLogs.push(tripStartLog);

                    ActiveRental activeRental = new ActiveRental(bikeID,emailAddress,tripStartTime);
                    activeRentalsList.add(activeRental);

                    BikeRequest request = new BikeRequest(emailAddress,bike.getLocation(),LocalDateTime.now());
                    bikeRequest.add(request);
                    break;
                    
                }
            }
        }
        else{
            System.out.println(" Sorry, we’re unable to reserve a bike at this time. Please try again later.");
        }
    }
    public void viewActiveRentals(){
        if(activeRentalsList.isEmpty())System.out.println("No active rentals at the moment.");
        else {
            for(ActiveRental activeRental:activeRentalsList){
                System.out.println(activeRental);
            }
        }
    }
    public String validateLocation(String location){
        for(Bike bike : BikeDatabase.bikes){
            if(location.equals(bike.getLocation()) && bike.getIsAvailable()){
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }

        }
        System.out.println("Sorry, no bikes are available at the location you" + 
                        "requested. Please try again later.");
        return null;
    }
    public void removeTrip(String bikeID){
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
    public void viewSystemLogs(){
        for(ERyderLog eRyderLog : systemLogs){
            System.out.println(eRyderLog);
        }
    }
}
