package ca.hrm;

public abstract class Employee implements Area {
    protected int id;
    protected int managerId;
    protected String firstName;
    protected String surname;
    protected double salary;
    protected String area;

    public Employee(int id, int managerId, String firstName, String surname, double salary, String area) throws InvalidEmployeeDataException {
        if (id <= 0 || managerId < 0 || managerId == id || salary < 0 || firstName == null || firstName.isEmpty() || surname == null || surname.isEmpty()) {
            throw new InvalidEmployeeDataException("Invalid employee data.");
        }
        this.id = id;
        this.managerId = managerId;
        this.firstName = firstName;
        this.surname = surname;
        this.salary = salary;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public int getManagerId() {
        return managerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmail() {
        return (firstName + "." + surname + "@" + getArea().toLowerCase() + ".company.com").toLowerCase();
    }

    public void setSalary(double newSalary) {
        if (newSalary >= 0) {
            this.salary = newSalary;
        }
    }

    public abstract String getArea();
}
