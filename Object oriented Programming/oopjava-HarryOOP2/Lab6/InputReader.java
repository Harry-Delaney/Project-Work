import java.util.*;

public class InputReader {
    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("[END]")) break;
            input.append(line).append("\n");
        }
        return input.toString();
    }
}