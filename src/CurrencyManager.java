import java.util.HashMap;
import java.util.Map;

public class CurrencyManager {
    private static final Map<String, String> availableCurrencies = new HashMap<>();

    static {
        availableCurrencies.put("USD", "Dólar Americano");
        availableCurrencies.put("EUR", "Euro");
        availableCurrencies.put("BRL", "Real Brasileiro");
        availableCurrencies.put("GBP", "Libra Esterlina");
        availableCurrencies.put("JPY", "Iene Japonês");
        availableCurrencies.put("CAD", "Dólar Canadense");
        availableCurrencies.put("AUD", "Dólar Australiano");
        availableCurrencies.put("CHF", "Franco Suíço");
        availableCurrencies.put("CNY", "Yuan Chinês");
        availableCurrencies.put("INR", "Rúpia Indiana");
    }

    public static void displayAvailableCurrencies() {
        System.out.println("\nMoedas disponíveis para conversão:");
        System.out.println("--------------------------------");
        availableCurrencies.forEach((code, name) -> System.out.printf("%s - %s%n", code, name));
        System.out.println("--------------------------------\n");
    }

    public static boolean isValidCurrency(String currencyCode) {
        return availableCurrencies.containsKey(currencyCode.toUpperCase());
    }

    public static String getCurrencyName(String currencyCode) {
        return availableCurrencies.getOrDefault(currencyCode.toUpperCase(), "Moeda desconhecida");
    }
}
