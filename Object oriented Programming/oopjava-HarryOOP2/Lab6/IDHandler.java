// IDHandler.java
import java.util.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

public class IDHandler {
    private List<String> ids;
    private static final Pattern ID_PATTERN = Pattern.compile("[XBD]00\\d{6}");

    public IDHandler(String input) {
        ids = new ArrayList<>();
        Matcher matcher = ID_PATTERN.matcher(input);
        while (matcher.find()) {
            ids.add(matcher.group());
        }
    }

    public List<String> convertIds() {
        Function<String, String> idToLocation = id -> {
            char prefix = id.charAt(0);
            String number = id.substring(3);
            return switch (prefix) {
                case 'D' -> "City " + number;
                case 'B' -> "Blanchardstown " + number;
                case 'X' -> "Tallaght " + number;
                default -> "Unknown " + number;
            };
        };
        return ids.stream().map(idToLocation).collect(Collectors.toList());
    }

    public List<String> filterByLocation(String location) {
        BiPredicate<String, String> locationFilter = (id, loc) -> {
            char prefix = id.charAt(0);
            return switch (loc.toLowerCase()) {
                case "city" -> prefix == 'D';
                case "blanchardstown" -> prefix == 'B';
                case "tallaght" -> prefix == 'X';
                default -> false;
            };
        };
        return ids.stream().filter(id -> locationFilter.test(id, location)).collect(Collectors.toList());
    }

    public List<String> getIds() {
        return ids;
    }
}

