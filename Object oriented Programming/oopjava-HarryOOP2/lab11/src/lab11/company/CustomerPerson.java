package lab11.company;

import lab11.customers.Customer;
import lab11.emails.EmailSystem;
import lab11.extrole.ExternalRole;


//req 2 f. CustomerPerson clas is created being a type of person that is lso a customer by extending Person class, implememts Customer to use its provided interface.
public class CustomerPerson extends Person implements Customer {
    //req 3 f. variable DOB initialised to "" making it an empty string
    //req 3 h. field DOB is private

    //req 4 a. DOB initialised directly witha  defualt value. name and email done using superclass constructor.
    private String DOB = "";

    public CustomerPerson(String name, String email) {
        super(name, email, "");
    }

    @Override
    public int getId() {
        return id;
    }
    
    @Override

    //req 4 b (i). CustomerPerson implements sendReceipt and within the method uses EmailSystem.sendEmail to send the receipt

    // req 4 b (iii). the sendReceipt class is specific to CustomerPerson. String format creates the emailContent which only has the item and the price
    public void sendReceipt(String item, int price) {
        String emailContent = String.format("Item: %s\nPrice: %d", item, price);
        EmailSystem.sendEmail(email, "Receipt", emailContent);
    }
}
