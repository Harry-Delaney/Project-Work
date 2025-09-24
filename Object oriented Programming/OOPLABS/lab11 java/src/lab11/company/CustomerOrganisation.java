package lab11.company;

import lab11.customers.Customer;
import lab11.emails.EmailSystem;

public class CustomerOrganisation extends Organisation implements Customer {
    private String vatNumber;

    public CustomerOrganisation(String name, String email, String vatNumber) {
        super(name, email);
        this.vatNumber = vatNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void sendReceipt(String item, int price) {
        double excludeVAT = price * 0.8;
        double includeVAT = price * 0.2;
        String emailContent = String.format("VAT Number: %s\nItem: %s\nPrice excluding VAT: %f\nPrice including VAT: %f", vatNumber, item, excludeVAT, includeVAT);
        EmailSystem.sendEmail(email, "Receipt", emailContent);
    }
}
