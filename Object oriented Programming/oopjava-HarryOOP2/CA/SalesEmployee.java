package ca.hrm;

public class SalesEmployee extends Employee {
    public SalesEmployee(int id, int managerId, String firstName, String surname, double salary) throws InvalidEmployeeDataException {
        super(id, managerId, firstName, surname, salary, "sales");
    }

    @Override
    public String getArea() {
        return "sales";
    }
}
