public class Main {
    public static void main(String[] args) throws Exception{
        ERyder bike1 = new ERyder("2",200,true,2000.1f,"cxy",12345678);
        bike1.ride();
        bike1.printBikeDetails();
        bike1.printRideDetails(100);

        ERyder bike2 = new ERyder("1",100,true,1000.1f);
        bike2.ride();
        bike2.printBikeDetails();
        bike2.printRideDetails(100);
        System.out.println("Can't use the private method directly. Must use another public method.");
        System.out.println(bike2.getTotalFare(100));
    }
}
