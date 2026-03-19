public class Main
{
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
        
        String sent1 = "I was very satisfied with the service.";
        String sent2 = "The e-Bike is quite comfortable to ride.";
        String sent3 = "The battery life of the e-Bike is impressive.";
        String sent4 = "The customer support was helpful and responsive.";
        String sent5 = "I would recommend this e-Bike to my friends and family.";
        Feedback feedback = new Feedback("John", "Walker", "3422278532@qq.com");
        feedback.analyseFeedBack(false, sent1, sent2, sent3, sent4, sent5); 
        System.out.println(feedback);

        UserRegistration userRegistration = new UserRegistration();
        userRegistration.registration();
        System.out.println(userRegistration);
    }
}

