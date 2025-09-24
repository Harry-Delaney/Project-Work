// javac -cp lib/lab11.jar -d bin src/lab11/company/*.java
// java -cp lib/lab11.jar;bin lab11.company.Company

//req 1
package lab11.company;

import lab11.customers.Customers;
import lab11.orders.IncomingOrder;
import lab11.orders.OutgoingOrder;
import lab11.suppliers.Suppliers;

public class Company {
    //req 2 g. In Company class we have the variables Customers and SUppliers which use the provided interfaces of customers and suppliers
    //req 3 h. fields Customers and Suppliers are private

    //req 4 a. initialsed directly, meaning the default no argument constructor is being used.
    private Customers customers = new Customers();
    private Suppliers suppliers = new Suppliers();
    //req 3 g. Private string variable that sets a constant value for vatNumber
    //req 3 h. field vatNumber is private
    final private String vatNumber = "IE1234567A";

    //req 4 c. addCustomerOrganisation allows adding an organisation customer and takes the appropriate info, creates a new object and moves it to the customers collection
    // addCustomerPerson allows adding a person customer and takes the appropriate info, and adds it to the customers collection
    //addSupplierOrganisation allows adding a person customer. takes the appropriate info and creates a new supplierorganisation object, then adds it to the customers collection

    public void addCustomerOrganisation(String contactName, String name, String email, String vatNumber) {
        customers.add(new CustomerOrganisation(contactName, name, email, vatNumber));
    }

    public void addCustomerPerson(String name, String email) {
        customers.add(new CustomerPerson(name, email));
    }

    public void addSupplierOrganisation(String contactName, String name, String email) {
        suppliers.add(new SupplierOrganisation(contactName, name, email));
    }

    public static void main(String[] args) {
        //req 5 a. creates our comapny object in main
        Company company = new Company();

        //req 5 b. adds the desired customers to the company with their respective info
        company.addCustomerPerson("Joe Bloggs", "jb@gm.com");
        company.addCustomerPerson("Jane Cloggs", "jc@hy.ie");
        company.addCustomerOrganisation("Cards", "Minnie Moggs", "mm@cards.com", null);
        
        //req 5 c. the suppliers have been added to the company with their respective info
        company.addSupplierOrganisation("Bobby Briggs", "WoodCo", "bb@wood.com");
        company.addSupplierOrganisation("Maxie Maggs", "Glue", "info@glue.com");

        //req 5 d. the 3 incoming orders are created and stored in the array IncomingOrder[] and the receipts for said order are sent out using the last line of the code
        IncomingOrder[] incomingOrders = new IncomingOrder[3];
        incomingOrders[0] = new IncomingOrder(1, "Card 100 10x15", 7);
        incomingOrders[1] = new IncomingOrder(2, "Card 100 10x15", 7);
        incomingOrders[2] = new IncomingOrder(3, "Card 1000 10x15", 50);
        company.customers.sendReceipts(incomingOrders);

        //req 5 e. the 2 outgoing orders are created and stored in the array OutgoingOrder[] and are displyed using the final line
        OutgoingOrder[] outgoingOrders = new OutgoingOrder[2];
        outgoingOrders[0] = new OutgoingOrder(4, "Wood 1x3", 10);
        outgoingOrders[1] = new OutgoingOrder(5, "Glue 5l", 2);
        company.suppliers.sendOrders(outgoingOrders);
    }
}
