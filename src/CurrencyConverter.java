import java.util.Scanner;

public class CurrencyConverter {
    private final ExchangeRateAPI api;
    private final Scanner scanner;

    public CurrencyConverter() {
        this.api = new ExchangeRateAPI();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Conversor de Moedas ===");
            CurrencyManager.displayAvailableCurrencies();

            try {
                System.out.print("Digite o valor a ser convertido: ");
                double amount = getValidAmount();

                System.out.print("Digite a moeda de origem (código): ");
                String fromCurrency = getValidCurrency("origem");

                System.out.print("Digite a moeda de destino (código): ");
                String toCurrency = getValidCurrency("destino");

                double convertedAmount = api.getConversionRate(amount, fromCurrency, toCurrency);
                displayConversionResult(amount, fromCurrency, convertedAmount, toCurrency);

                running = askToContinue();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                running = askToContinue();
            }
        }

        scanner.close();
    }

    private double getValidAmount() {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("Por favor, digite um valor maior que zero.");
                    continue;
                }
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um valor numérico válido.");
            }
        }
    }

    private String getValidCurrency(String type) {
        while (true) {
            String currency = scanner.nextLine().toUpperCase();
            if (CurrencyManager.isValidCurrency(currency)) {
                return currency;
            }
            System.out.println("Moeda inválida. Por favor, escolha uma moeda válida da lista.");
            System.out.print("Digite a moeda de " + type + " (código): ");
        }
    }

    private void displayConversionResult(double amount, String fromCurrency, double convertedAmount, String toCurrency) {
        System.out.println("\nResultado da Conversão:");
        System.out.println("--------------------------------");
        System.out.printf("%.2f %s (%s) = %.2f %s (%s)%n",
                amount,
                fromCurrency,
                CurrencyManager.getCurrencyName(fromCurrency),
                convertedAmount,
                toCurrency,
                CurrencyManager.getCurrencyName(toCurrency));
        System.out.println("--------------------------------");
    }

    private boolean askToContinue() {
        System.out.print("\nDeseja fazer outra conversão? (S/N): ");
        String response = scanner.nextLine().toUpperCase();
        return response.equals("S") || response.equals("SIM");
    }
}