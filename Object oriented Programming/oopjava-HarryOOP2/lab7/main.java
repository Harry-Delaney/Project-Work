import java.util.function.Predicate;

public class main {

    
    private static Predicate<String> getRegExpPredicate(String regex) {
        return word -> word.matches(regex);
    }

    public static void main(String[] args) {
        try {
            
            String directoryName = "lab7_text_files"; 
            TextProcessor processor = new TextProcessor(directoryName);

            
            processor.findFiles("^science.*");

            
            Predicate<String> startsWithH = getRegExpPredicate("^h.*");
            
            Predicate<String> startsWithX = getRegExpPredicate("^x.*");

            
            processor.run(startsWithH, "Words starting with H");
            processor.run(startsWithX, "Words starting with X");

           
            processor.saveResults("word_counts.txt");

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
