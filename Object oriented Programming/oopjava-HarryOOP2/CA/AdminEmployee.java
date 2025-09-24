package ca.hrm;

public class AdminEmployee extends Employee {
    public AdminEmployee(int id, int managerId, String firstName, String surname, double salary) throws InvalidEmployeeDataException {
        super(id, managerId, firstName, surname, salary, "admin");
    }

    @Override
    public String getArea() {
        return "admin";
    }
}
