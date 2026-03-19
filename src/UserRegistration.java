import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UserRegistration
{
    public static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    public static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    public static final double VIP_BASE_FEE = 100.0;
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailValid;
    private boolean minorAndBirthday;
    private boolean minor;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;

    public void registration()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the ERyder Registration.\n");
        System.out.println("Here are your two options:\n");
        System.out.println("1.Register as a Regular User\n");
        System.out.println("2.REgister as a VIP User\n");
        System.out.println("Please enter your choice(1 or 2):\n");
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice == 1)
        {
            userType = "Regular User";
        }
        else if(choice == 2)
        {
            userType = "VIP User";
        }
        else{
            System.out.println("Invalid choice.Please restart the registration process.\n");
            return;
        }
        System.out.println("Let's proceed with the registratioon...\n");
        System.out.println("Please enter your Full Name:\n");
        fullName = sc.nextLine();

        System.out.println("Please enter your Email Address:\n");
        emailAddress =  sc.nextLine();
        System.out.println("Checking your email address's validity...\n");
        emailValid = analyseEmail(emailAddress);

        System.out.println("Please enter your date of birth as YYYY-MM-DD:\n");
        dateOfBirth = sc.nextLine();
        System.out.println("Checking your age validity...");
        LocalDate dob =  LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.println("Please enter your card number:\n");
        cardNumber = sc.nextLong();
        sc.nextLine();
        System.out.println("Checking your card nymber's validity...");
        cardNumberValid = analyseCardNumber(cardNumber);

        System.out.println("Please enter your card expiry date:\n");
        cardExpiryDate = sc.nextLine();
        System.out.println("Checking your card expiry date's validity...");
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);

        System.out.println("Please enter your cvv:\n");
        cvv = sc.nextInt();
        System.out.println("Checking your cvv's validity...");
        validCVV = analyseCVV(cvv);

        finalCheckpoint();

        sc.close();
    }
    private boolean analyseEmail(String emailAddress)
    {
        if(emailAddress.contains("@") && emailAddress.contains("."))
        {
            System.out.println("Email is valid");
        }
        else{
            System.out.println("Invalid email address.Going back to the start of the registration.\n");
            registration();
        }
        return true;
    }
    private boolean analyseAge(LocalDate dob)
    {
        LocalDate currentDate =  LocalDate.now();
        Period diff = Period.between(dob,currentDate);
        boolean isBirthday = false;
        if(currentDate.getMonth() == dob.getMonth() && currentDate.getDayOfMonth() == dob.getDayOfMonth())
        {
            isBirthday = true;
        }
        if(userType.equals("VIP User") && isBirthday && diff.getYears() <= 18 && diff.getYears() > 12)
        {
            System.out.println("Happy Birthday!\n");
            System.out.println("You get 25% discount on the VIP subscription fee for being born today and being under 18!");
            minorAndBirthday = true;
        }
        else if(userType.equals("VIP User") && isBirthday == false && diff.getYears() <= 18 && diff.getYears() > 12)
        {
            System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
            minor = true;
        }
        else if(diff.getYears() <= 12 || diff.getYears() > 120)
        {
            System.out.println("Looks like you are either too young or already dead. Sorry, you can't be our user. Have a nice day.");
            System.exit(0);
        }
        return true;
    }
    private boolean analyseCardNumber(long cardNumber)
    {
        String cardNumStr = Long.toString(cardNumber);
        int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0,2));
        int firstFourDigits = Integer.parseInt(cardNumStr.substring(0,4));
        if((cardNumStr.length() == 13 || cardNumStr.length() == 15) && cardNumStr.startsWith("4"))
        {
            cardProvider = "VISA";
        }
        else if(cardNumStr.length() == 16 && firstTwoDigits >= 51 && firstTwoDigits <= 55 || firstFourDigits >= 2221 && firstFourDigits <= 2720)
        {
            cardProvider = "MasterCard";
        }
        else if(cardNumStr.length() == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37")))
        {
            cardProvider = "American Express";
        }
        else
        {
            System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.\n");
            System.out.println("Going back to the start of the registration.\n");
            registration();
        }
        return true;
    }
    private boolean analyseCardExpiryDate(String cardExpiryDate)
    {
        int month = Integer.parseInt(cardExpiryDate.substring(0,2));
        int year = Integer.parseInt(cardExpiryDate.substring(3,5))+2000;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        if(year > currentYear ||  year == currentYear && month >= currentMonth)
        {
            System.out.println("The card is still valid.");
        }
        else
        {
             System.out.println("Sorry, your card has expired. Please use a different card.\n");
             System.out.println("Going back to the start fo the registration process…");
             registration();
        }
        return true;
    }
    private boolean analyseCVV(int cvv)
    {
        String cvvStr = Integer.toString(cvv);
        if(cardProvider.equals("American Express") && cvvStr.length() == 4 || cardProvider.equals("VISA") && cvvStr.length() == 4 ||
    cardProvider.equals("MasterCard") && cvvStr.length() == 3)
    {
        System.out.println("Card CVV is valid.");
    }
    else{
        System.out.println("Invalid CVV for the given card.\n");
        System.out.println("Going back to the start of the registration process.\n");
        registration();
    }
    return true;
    }
    private void finalCheckpoint()
    {
        if(emailValid && ageValid && cardNumberValid && cardStillValid && validCVV)
        {
            chargeFees();
        }
        else
        {
            System.out.println("Sorry, your registration was unsuccessful due to the following reason(s)");
            if(!emailValid){System.out.println("Invalid email address.\n");}
            if(!ageValid){System.out.println("Invalid age\n");}
            if(!cardNumberValid){System.out.println("Invalid card number.\n");}
            if(!cardStillValid){System.out.println("Card has expired\n");}
            if(!validCVV){System.out.println("Invalid CVV.\n");}
            System.out.println("Going back to the start of the registration process.\n");
            registration();
        }
    }
    private void chargeFees()
    {
        if(minorAndBirthday)
        {
            feeToCharge = VIP_BASE_FEE * 0.75;
        }
        else if(minor)
        {
            feeToCharge =  VIP_BASE_FEE * 0.8;
        }
        else{
            feeToCharge = VIP_BASE_FEE;
        }
        String cardNumStr = Long.toString(cardNumber);
        System.out.println("Thank you for your payment.\n");
        System.out.println("A fee of "+ feeToCharge +" has been charged to your card ending with "+ cardNumStr.substring(cardNumStr.length()-4) +"");
    }
    @Override
    public String toString()
    {
        String cardNumberStr = Long.toString(cardNumber);
        String censoredPart = cardNumberStr.substring(0,cardNumberStr.length()-4).replace(".","*");
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length()-4);
        String censoredNumber = censoredPart  + lastFourDigits;
        return "Registration successful! Here are your details:\n User Type:"+ userType +"\n Full Name: "+fullName+"\nEmail Address: "+emailAddress+"\nDate of Birth: "+dateOfBirth
        +"\nCard Number: "+censoredNumber+"\nCard Provider: "+cardProvider+"\nCard Expiry Date: "+cardExpiryDate;
    }
}
