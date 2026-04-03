public class ERyder {
    private String bikeID;
    private int batteryLevel;
    public boolean isAvailable;
    public float kmDriven;

    private static final String COMPANY_NAME = "ERyder";
    private static final double BASE_FARE = 1.0;
    private static final double PER_MINUTE_FARE = 0.5;

    private final String LINKED_ACCOUNT;
    private final long LINKED_PHONE_NUMBER;

    private int totalUsageInMinutes;
    private double totalFare;


    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, float kmDriven)
    {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        LINKED_ACCOUNT = "greetg";
        LINKED_PHONE_NUMBER = 1234567;

    }

    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, float kmDriven, String linkedAccount, long linkedPhoneNumber){
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        LINKED_ACCOUNT = linkedAccount;
        LINKED_PHONE_NUMBER = linkedPhoneNumber;
    }

    public void printRideDetails(int usageInMinutes){
        totalUsageInMinutes = usageInMinutes;
        System.out.println("======== Ride Details ========");
        System.out.println("The linked account is "+LINKED_ACCOUNT+".");
        System.out.println("The linked phone number is "+LINKED_PHONE_NUMBER+".");
        System.out.println("The bike ID is "+bikeID+".");
        System.out.println("The usage in minutes is "+totalUsageInMinutes+".");
        System.out.println("The total fare is "+calculateFare(usageInMinutes)+".");
        System.out.println("==============================\n");
    }

    private double calculateFare(int usageInMinutes){
        totalFare = BASE_FARE + (PER_MINUTE_FARE*usageInMinutes);
        return usageInMinutes*PER_MINUTE_FARE+BASE_FARE;
    }

    public double getTotalFare(int usageInMinutes){
        return calculateFare(usageInMinutes);
    }
    public void ride(){
        if(isAvailable == true && batteryLevel >= 10){
           System.out.println("You can ride this bike.");
        }
        else{
            System.out.println("You can't ride this bike. Please change one.");
        }
    }

    public void printBikeDetails(){
        System.out.println("======== Bike Details ========");
        System.out.println("The bike's ID is " + bikeID+".");
        System.out.println("The battery level is " + batteryLevel+"%.");
        if(isAvailable){System.out.println("The bike is available.");}
        else {System.out.println("The bike is not available.");}
        System.out.println("The bike's distance travelled in " + kmDriven + " km.");
        System.out.println("==============================\n");
    }

    //getters
    public String getBikeID(){
        return bikeID;
    }
    public int getBatteryLevel(){
        return batteryLevel;
    }
    //not necessary
    public boolean getIsAvailable(){
        return isAvailable;
    }
    public float getKmDriven(){
        return kmDriven;
    }
    //
    //setters
    public void setBikeID(String bikeID){
        this.bikeID = bikeID;
    }
    public void setBatteryLevel(int batteryLevel){
        if(batteryLevel>=0 && batteryLevel<=100){this.batteryLevel = batteryLevel;}
        else {System.out.println("The batteryLevel must be a integer from 0 to 100");}
    }
}
