package lab11.company;

import lab11.emails.EmailSystem;
import lab11.suppliers.Supplier;

public class SupplierOrganisation extends Organisation implements Supplier {
    public SupplierOrganisation(String name, String email) {
        super(name, email);
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public void sendOrder(String item, int quantity) {
        String emailContent = String.format("Contant Name: %s\nItem: %s\nQuantity: %d", contactName, item, quantity);
        EmailSystem.sendEmail(email, "Order", emailContent);
    }
}
