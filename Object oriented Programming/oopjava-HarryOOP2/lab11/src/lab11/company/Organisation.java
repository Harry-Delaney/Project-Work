package lab11.company;

//req 2 c. Organisation class is created and is a type of InvolvedParty by extending it
public class Organisation extends InvolvedParty {
    //req 3 d. variable contactName added into Organisation class so that it now has an organisation name
    //req 3 h. field is contactName is protected

    //req 4 a. The constructor initialises contactName. Nname and email are done by the superclass constructor
    protected String contactName;

    public Organisation(String contactName, String name, String email) {
        super(name, email);
        this.contactName = contactName;
    }
}
