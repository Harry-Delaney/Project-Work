
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;



public class Company {

    String employeeID;
    static int idCount = 1;

    static private String newEmployeeID() {

        int idLength = 6;
        String newEmployeeID = "0000000";

        while (newEmployeeID.length() < idLength - idCount) {
            newEmployeeID += "1";
        }

        newEmployeeID += String.valueOf(idCount);
        idCount++;

        return newEmployeeID;

    }

    public void newEmployee (String name, String salary, String area) {
        //checks if the name is valid
        if (name == null) {
            throw new Error("Invalid Name");
        }

        if (salary == null) {
            throw new Error("Invalid Salary");
        }

        if (area == null) {
            throw new Error("Invalid Area");
        }




    }

    public void newEmployeeSet(String filename) throws IOException{

        try (Stream<String> lines = Files.lines(Path.of(filename), StandardCharsets.UTF_8)) {

        }

        //filename f= new filename("lab12_data.csv");




    }

    public void employeeSearch(String newEmployeeID) {


    }

    public void displayEmployeeInfo() {

    }

    public static void main(String[] args) {
        System.out.println(newEmployeeID());
        System.out.println(idCount);
    }
    
}
