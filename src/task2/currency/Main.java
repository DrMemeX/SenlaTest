package task2.currency;

import java.util.*;

public class Main {
    private static final Map<String, Double> DEFAULT_TO_USD = Map.of(
            "USD", 1.00,
            "EUR", 1.08,
            "GBP", 1.25,
            "JPY", 0.0067,
            "RUB", 0.012
    );

    public static void main(String[] args) {
        System.out.println("=== КОНВЕРТЕР ВАЛЮТ ===");
        System.out.println("Поддерживаемые валюты: " + DEFAULT_TO_USD.keySet());

        Scanner console = new Scanner(System.in);

        boolean playAgain;
        do {
        String from;
        while (true) {
            System.out.print("Введите буквенный код исходной валюты: ");
            from = console.nextLine().toUpperCase(Locale.ROOT);
            if (DEFAULT_TO_USD.containsKey(from)) break;
            System.out.println("Неизвестная валюта: " + from + ". Попробуйте ещё раз. Доступные: " + DEFAULT_TO_USD.keySet());
        }

        String to;
        while (true) {
            System.out.print("Введите буквенный код целевой валюты: ");
            to = console.nextLine().toUpperCase(Locale.ROOT);
            if (DEFAULT_TO_USD.containsKey(to)) break;
            System.out.println("Неизвестная валюта: " + to + ". Попробуйте ещё раз. Доступные: " + DEFAULT_TO_USD.keySet());
        }

        double inputDouble;
        while (true) {
            System.out.print("Введите сумму: ");
            String inputStr = console.nextLine().trim().replace(',', '.');
            try {
                inputDouble = Double.parseDouble(inputStr);
                if (inputDouble >= 0) break;
                System.out.println("Сумма НЕ должна быть отрицательной!");
            } catch (NumberFormatException e) {
                System.out.println("Некорректная сумма, попробуйте снова.");
            }
        }

        double usd = inputDouble * DEFAULT_TO_USD.get(from);
        double result = usd / DEFAULT_TO_USD.get(to);

        System.out.printf("%nРезультат: %.2f %s = %.2f %s%n", inputDouble, from, result, to);
            System.out.print("Хотите выполнить ещё одну конвертацию? (y/n): ");
            String answer = console.nextLine().trim().toLowerCase();
            playAgain = answer.equals("y");

        } while (playAgain);

        System.out.println("Спасибо за использование конвертера!");
    }
}
