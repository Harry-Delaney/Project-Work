package lab11.company;
public class InvolvedParty {
    protected static int id = 1;
    protected String name;
    protected String email;

    public InvolvedParty(String name, String email) {
        this.name = name;
        this.email = email;
        this.id = id++;
    }
}