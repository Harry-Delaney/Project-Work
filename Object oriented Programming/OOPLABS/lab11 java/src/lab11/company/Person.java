package lab11.company;
public class Person extends InvolvedParty {
    private String DOB;

    public Person(String name, String email, String DOB) {
        super(name, email);
        this.DOB = DOB;
    }
}
