package ca.hrm;

public class ITEmployee extends Employee {
    public ITEmployee(int id, int managerId, String firstName, String surname, double salary) throws InvalidEmployeeDataException {
        super(id, managerId, firstName, surname, salary, "it");
    }

    @Override
    public String getArea() {
        return "it";
    }
}
