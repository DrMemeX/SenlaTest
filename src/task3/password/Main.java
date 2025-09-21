package task3.password;

import java.security.SecureRandom;
import java.util.*;

public class Main {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*-_=+;:,.?";

    public static void main(String[] args) {
        System.out.println("=== ГЕНЕРАТОР ПАРОЛЕЙ ===");

        Scanner console = new Scanner(System.in);
        SecureRandom rnd = new SecureRandom();

        boolean playAgain;
        do {
        System.out.print("Введите длину пароля (8–12), Enter = случайная: ");
        String input = console.nextLine().trim();

        int length;
        if (input.isEmpty()) {
            length = 8 + rnd.nextInt(5);
            System.out.println("Длина выбрана случайно: " + length);
        } else {
            try {
                length = Integer.parseInt(input);
                if (length < 8 || length > 12) {
                    length = 8 + rnd.nextInt(5);
                    System.out.println("Некорректная длина, используем случайную: " + length);
                }
            } catch (NumberFormatException e) {
                length = 8 + rnd.nextInt(5);
                System.out.println("Некорректный ввод, используем случайную длину: " + length);
            }
        }

        String password = generatePassword(length, rnd);
        System.out.println("Сгенерированный пароль: " + password);
            System.out.print("Хотите выполнить ещё одну генерацию? (y/n): ");
            String answer = console.nextLine().trim().toLowerCase();
            playAgain = answer.equals("y");

        } while (playAgain);

        System.out.println("Спасибо за использование генератора паролей!");
    }

    private static String generatePassword(int length, SecureRandom rnd) {
        List<Character> chars = new ArrayList<>();

        chars.add(LOWER.charAt(rnd.nextInt(LOWER.length())));
        chars.add(UPPER.charAt(rnd.nextInt(UPPER.length())));
        chars.add(DIGITS.charAt(rnd.nextInt(DIGITS.length())));
        chars.add(SPECIAL.charAt(rnd.nextInt(SPECIAL.length())));

        String all = LOWER + UPPER + DIGITS + SPECIAL;

        for (int i = chars.size(); i < length; i++) {
            chars.add(all.charAt(rnd.nextInt(all.length())));
        }

        Collections.shuffle(chars, rnd);

        StringBuilder sb = new StringBuilder(length);
        for (char c : chars) sb.append(c);

        return sb.toString();

    }
}
