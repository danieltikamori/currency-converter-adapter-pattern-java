import java.util.HashMap;
import java.util.Scanner;

// Represents the Old Currency Converter class responsible for currency conversion based on predefined rates
class OldCurrencyConverter {
    private final HashMap<String, Double> conversionRates; // HashMap to store currency conversion rates

    // Constructor to initialize conversion rates for different currencies
    public OldCurrencyConverter() {
        conversionRates = new HashMap<>();
        conversionRates.put("USD", 0.80); // Conversion rate for USD
        conversionRates.put("GBP", 1.0625); // Conversion rate for GBP
        conversionRates.put("EUR", 0.92); // Conversion rate for EUR
        conversionRates.put("JPY", 150.0); // Conversion rate for JPY
        // Additional conversion rates can be added here
    }

    // Method to convert an amount from one currency to another
    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (amount == 0) {
            return 0;
        }

        // Check if the conversion rates for the specified currencies exist
        if (!conversionRates.containsKey(fromCurrency) || !conversionRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Invalid conversion");
        }

        // Retrieve the conversion rates for the specified currencies
        double fromRate = conversionRates.get(fromCurrency);
        double toRate = conversionRates.get(toCurrency);

        // Perform the currency conversion and return the result
        return amount * (toRate / fromRate);
    }
}

// Represents the Currency Adapter class that adapts the OldCurrencyConverter to a new interface
class CurrencyAdapter {
    private final OldCurrencyConverter oldConverter;

    // Constructor to initialize the Currency Adapter with the OldCurrencyConverter
    public CurrencyAdapter(OldCurrencyConverter oldConverter) {
        this.oldConverter = oldConverter;
    }

    // Method to convert an amount using the adapted OldCurrencyConverter
    public double convert(double amount, String fromCurrency, String toCurrency) {
        return oldConverter.convert(amount, fromCurrency, toCurrency);
    }
}

// Main class to demonstrate the currency conversion functionality
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        OldCurrencyConverter oldConverter = new OldCurrencyConverter(); // Create an instance of OldCurrencyConverter
        CurrencyAdapter adapter = new CurrencyAdapter(oldConverter); // Create an instance of CurrencyAdapter with OldCurrencyConverter

        // Main loop to accept user input and perform currency conversion
        while (true) {
            System.out.println("Enter a number in USD: ");
            if (scanner.hasNextDouble()) {
                double input = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character

                double eurAmount = adapter.convert(input, "USD", "EUR"); // Convert USD to EUR using the adapter
                System.out.println("USD: " + input);
                System.out.println("EUR: " + eurAmount);
                break; // Exit the loop after conversion
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.close(); // Close the scanner to release resources
    }
}

// Without Adapter
//
//import java.util.HashMap;
//import java.util.Scanner;
//
//class CurrencyConverter {
//    private static final HashMap<String, Double> conversionRates = new HashMap<>();
//
//    static {
//        conversionRates.put("USD", 0.80);
//        conversionRates.put("GBP", 1.0625);
//        conversionRates.put("EUR", 0.92);
//        conversionRates.put("JPY", 150.0);
//        // Add other conversion rates here
//    }
//
//    public static double convert(double amount, String fromCurrency, String toCurrency) {
//        if (amount == 0) {
//            return 0;
//        }
//
//        if (!conversionRates.containsKey(fromCurrency) || !conversionRates.containsKey(toCurrency)) {
//            throw new IllegalArgumentException("Invalid conversion");
//        }
//
//        double fromRate = conversionRates.get(fromCurrency);
//        double toRate = conversionRates.get(toCurrency);
//
//        return amount * (toRate / fromRate);
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("Enter a number in USD: ");
//            if (scanner.hasNextDouble()) {
//                double input = scanner.nextDouble();
//                scanner.nextLine(); // Consume the newline character
//
//                double eurAmount = CurrencyConverter.convert(input, "USD", "EUR");
//                System.out.println("USD: " + input);
//                System.out.println("EUR: " + eurAmount);
//                break;
//            } else {
//                System.out.println("Invalid input. Please enter a valid number.");
//                scanner.nextLine(); // Consume the invalid input
//            }
//        }
//        scanner.close();
//    }
//}