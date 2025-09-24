package lab11.company;

import lab11.customers.Customer;
import lab11.emails.EmailSystem;


//req 2 e. CustomerOrganisation class is created which nextends Organisation, making it a type of organisation. It implements Customer class.
public class CustomerOrganisation extends Organisation implements Customer {
    //req 3 e. vatNumber variable added into the customerOrganisation class so that there is now an organisation that is a customer with a VAT number
    //req 3 h. field vatNumber is private

    //req 4 a. the constructor initialises vatNumber, contactName, name and email are done by the superclass constructor
    private String vatNumber;

    public CustomerOrganisation(String contactName, String name, String email, String vatNumber) {
        super(contactName, name, email);
        this.vatNumber = vatNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override

    //req 4 b (i). CustomerOrganisation uses the sendReceipt method, and wwithin this it uses the EmailSystem.sendEmail to send the receipt
    public void sendReceipt(String item, int price) {

        //req 4 b (ii). excludeVat variable created calculated by price * 0.8, includeVat created and calculated by price * 0.2. emailContent variable includes the VAT number, item 
        //and price including and excluding vat
        double excludeVAT = price * 0.8;
        double includeVAT = price * 0.2;
        String emailContent = String.format("VAT Number: %s\nItem: %s\nPrice excluding VAT: %f\nPrice including VAT: %f", vatNumber, item, excludeVAT, includeVAT);
        EmailSystem.sendEmail(email, "Receipt", emailContent);
    }
}
