package lab11.company;

import lab11.emails.EmailSystem;
import lab11.suppliers.Supplier;

//req 2 d. SupplierOrganisation class is created, which is a type of Organisation by extending, and implements the class Supplier
public class SupplierOrganisation extends Organisation implements Supplier {

    //req 4 a. constrcutor initialises contactName, name and email done via the superclass constructor
    public SupplierOrganisation(String contactName, String name, String email) {
        super(contactName, name, email);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override

    //req 4 b (i). SupplierOrganisation implements sendOrder, which within it uses EmailSystem.sendEmail method to send the order.

    //req 4 b (iv). sendOrder is specific to SupllierOrganisation, and the string format for out email content includes the contactName, item and quantity
    public void sendOrder(String item, int quantity) {
        String emailContent = String.format("Contant Name: %s\nItem: %s\nQuantity: %d", contactName, item, quantity);
        EmailSystem.sendEmail(email, "Order", emailContent);
    }
}
