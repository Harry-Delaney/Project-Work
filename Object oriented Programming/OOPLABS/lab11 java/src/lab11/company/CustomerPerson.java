package lab11.company;

import lab11.customers.Customer;
import lab11.emails.EmailSystem;
import lab11.extrole.ExternalRole;

public class CustomerPerson extends Person implements Customer {
    private String DOB = "";

    public CustomerPerson(String name, String email) {
        super(name, email, "");
    }

    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public void sendReceipt(String item, int price) {
        String emailContent = String.format("Item: %s\nPrice: %d", item, price);
        EmailSystem.sendEmail(email, "Receipt", emailContent);
    }
}
