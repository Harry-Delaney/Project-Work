//req 2 b. Person class is created

package lab11.company;
public class Person extends InvolvedParty {
    //req 3 c. DOB variable string added into class Person so that now the person has a date of birth
    //req 3 h. field DOB is private

    //req 4 a. The constructor initialises DOB, Name and email are done using the call to the superclass constructor.
    private String DOB;

    public Person(String name, String email, String DOB) {
        super(name, email);
        this.DOB = DOB;
    }
}
