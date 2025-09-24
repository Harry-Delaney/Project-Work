package ca.hrm;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    private List<Employee> managedEmployees;

    public Manager(int id, int managerId, String firstName, String surname, double salary, String area) throws InvalidEmployeeDataException {
        super(id, managerId, firstName, surname, salary, area);
        managedEmployees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (employee.getManagerId() == this.id) {
            managedEmployees.add(employee);
        }
    }

    public List<Employee> getManagedEmployees() {
        return managedEmployees;
    }

    @Override
    public String getArea() {
        return area;
    }
}
