public class ERyder {

    //variables
    private String bikeID;
    private int batteryLevel;
    public boolean isAvailable;
    public float kmDriven;

    //constructor
    public ERyder(String bikeID, int batteryLevel, boolean isAvailable, float kmDriven) {
        this.bikeID = bikeID;
        this.batteryLevel = batteryLevel;
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
    }
    public ERyder() {}
    public void ride()
    
    {
        if(batteryLevel >= 10 && isAvailable == true)
        {
            System.out.println("The bike is available.");
        }
        else
        {
            System.out.println("The bike is not available.");
        }
    }
    public void printBikeDetails()
    {
        System.out.println("The bike ID is " + bikeID);
        System.out.println("The battery level is " + batteryLevel);
        if(isAvailable){
            System.out.println("The bike is available.");
        }
        else{
            System.out.println("The bike is not avaliable.");
        }
        System.out.println("Its distance is " + kmDriven + " km.");
    }
    //Methods
    public String getbikeID(){
        return bikeID;
    }
    public int getbatteryLevel(){
        return batteryLevel;
    }
    public void setbikeID(String bikeID){
        this.bikeID = bikeID;
    }
    public void getbatteryLevel(int batteryLevel){
        if(batteryLevel >= 0 && batteryLevel <= 100){
            this.batteryLevel = batteryLevel;
        }
        else{
            System.out.println("The batteryLevel is from 0 to 100");
        }
    }
}