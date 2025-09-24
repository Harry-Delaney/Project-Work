package lab11.company;
public class Organisation extends InvolvedParty {
    protected String contactName;

    public Organisation(String name, String email) {
        super(name, email);
        this.contactName = name;
    }
}
