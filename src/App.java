
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class App {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    private static final int SHORT_URL_LENGTH = 6;

    public App() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
    }

    public String shortenUrl(String longUrl) {
        String shortUrl = generateShortUrl();
        if (!shortToLongMap.containsKey(shortUrl)) {
            shortToLongMap.put(shortUrl, longUrl);
            longToShortMap.put(longUrl, shortUrl);
            return shortUrl;
        } else {
            // Handle collision (you may want to generate a new short URL or handle differently)
            return shortenUrl(longUrl);
        }
    }

    public String expandUrl(String shortUrl) {
        return shortToLongMap.getOrDefault(shortUrl, "Short URL not found");
    }

    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            shortUrl.append(randomChar);
        }

        return shortUrl.toString();
    }

    public static void main(String[] args) {
        App linkShortener = new App();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL: ");
                    String longUrl = scanner.nextLine();
                    String shortUrl = linkShortener.shortenUrl(longUrl);
                    System.out.println("Shortened URL: " + shortUrl);
                    break;

                case 2:
                    System.out.print("Enter the short URL: ");
                    String shortUrlToExpand = scanner.nextLine();
                    String expandedUrl = linkShortener.expandUrl(shortUrlToExpand);
                    System.out.println("Expanded URL: " + expandedUrl);
                    break;

                case 3:
                    System.out.println("Exiting Link Shortener. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
}
