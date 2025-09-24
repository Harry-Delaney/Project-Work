package lab11.company;

import java.util.ArrayList;

import lab11.customers.Customers;
import lab11.suppliers.Suppliers;

public class Company {
    private ArrayList<Customers> customers;
    private ArrayList<Suppliers> suppliers;
    final private String vatNumber;

    public Company(Customers customers, Suppliers suppliers, String vatNumber) {
        this.customers = new ArrayList<>();
        this.suppliers = new ArrayList<>();
        this.vatNumber = vatNumber;
    }

    public void addCustomerOrganisation(String name, String email, String vatNumber) {
        CustomerOrganisation customerOrganisation = new CustomerOrganisation(name, email, vatNumber);
        customers.add(customerOrganisation);
    }

    public void addCustomerPerson(String name, String email) {
        CustomerPerson customerPerson = new CustomerPerson(name, email);
        customers.add(customerPerson);
    }

    public void addSupplierOrganisation(String name, String email) {
        SupplierOrganisation supplierOrganisation = new SupplierOrganisation(name, email);
        suppliers.add(supplierOrganisation);
    }
}
