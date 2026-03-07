public class Main {
    public static void main(String[] args) throws Exception{
        ERyder bike1 = new ERyder();
        bike1.ride();
        bike1.printBikeDetails();
        ERyder bike2 = new ERyder("1",100,true,1000.1f);
        bike2.ride();
        bike2.printBikeDetails();
    }
}
