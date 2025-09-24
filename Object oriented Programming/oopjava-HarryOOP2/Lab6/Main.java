public class Main {
    public static void main(String[] args) {
        InputReader reader = new InputReader();
        System.out.println("Enter text (end with [END]):");
        String input = reader.readInput();

        IDHandler handler = new IDHandler(input);

        System.out.println("\nRaw IDs:");
        handler.getIds().forEach(System.out::println);

        System.out.println("\nLocations and Numbers:");
        handler.convertIds().forEach(System.out::println);

        System.out.println("\nIDs filtered by location 'city':");
        handler.filterByLocation("city").forEach(System.out::println);
    }
}