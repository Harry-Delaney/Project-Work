import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.*; 

public class TextProcessor {
    
    private Path directory;
    private List<String> matchedFiles;
    private Map<String, Integer> wordCountMap;

    
    public TextProcessor(String directoryName) {
        Path dirPath = Paths.get(directoryName);
        if (!Files.exists(dirPath) || !Files.isDirectory(dirPath)) {
            throw new IllegalArgumentException("The provided directory does not exist or is not a directory.");
        }
        this.directory = dirPath;
        this.matchedFiles = new ArrayList<>();
        this.wordCountMap = new HashMap<>();
    }

    
    public void findFiles(String regex) {
        Pattern pattern = Pattern.compile(regex);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path file : stream) {
                String fileName = file.getFileName().toString();
                if (fileName.matches(regex)) {  
                    matchedFiles.add(fileName);
                }
            }
        } catch (IOException | DirectoryIteratorException e) {
            throw new IllegalArgumentException("Error while accessing the directory.", e);
        }
    }

    
    public void run(Predicate<String> predicate, String predicateName) {
        for (String fileName : matchedFiles) {
            Path filePath = directory.resolve(fileName);
            try {
                List<String> lines = Files.readAllLines(filePath);
                int count = 0;
                for (String line : lines) {
                    String[] words = line.split("\\W+"); 
                    for (String word : words) {
                        if (predicate.test(word)) {
                            count++;
                        }
                    }
                }
                wordCountMap.put(predicateName, wordCountMap.getOrDefault(predicateName, 0) + count);
            } catch (IOException e) {
                System.err.println("Error reading file: " + fileName);
            }
        }
    }

    
    public void saveResults(String fileName) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving results to file: " + fileName);
        }
    }
}
