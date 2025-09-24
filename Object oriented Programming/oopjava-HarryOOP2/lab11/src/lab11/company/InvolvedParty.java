//req 2 a. class Involved Party is created

package lab11.company;
//req 3 h. fields idCounter, id, name and email are protected or private
public class InvolvedParty {
    //req 3 a. declares the private static variable idCounter starting at 1, Protected variable id is used so classes can access it.
    private static int idCounter = 1;
    //req 4 a. the constuctor initialises name and email, and id is done automatically using idCounter
    protected int id;
    //req 3 b. variables name and email are declared in InvolvedParty so now it has those variables
    protected String name;
    protected String email;

    public InvolvedParty(String name, String email) {
        this.name = name;
        this.email = email;
        this.id = idCounter++;
    }
}