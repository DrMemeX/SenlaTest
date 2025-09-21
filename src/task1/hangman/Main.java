package task1.hangman;

import java.util.*;

public class Main {

    private static final String[] WORDS_RU = {
            "компьютер", "алгоритм", "интернет",
            "робот", "объект", "матрица"
    };

    private static final String[] WORDS_EN = {
            "computer", "algorithm", "internet",
            "robot", "java", "object", "matrix"
    };

    private static final int MAX_MISSES = 6;

    public static void main(String[] args) {
        System.out.println("=== ВИСЕЛИЦА ===");

        Scanner console = new Scanner(System.in);
        Random rnd = new Random();

        boolean playAgain;
        do {
        System.out.print("Выберите алфавит (ru/en): ");
        String alphabetChoice = console.nextLine().toLowerCase();

        boolean russianMode;
        if (alphabetChoice.equals("ru")) {
            russianMode = true;
            System.out.println("Играем на русском алфавите!");
        } else if (alphabetChoice.equals("en")) {
            russianMode = false;
            System.out.println("Играем на английском алфавите!");
        } else {
            System.out.println("Некорректный выбор. По умолчанию русский.");
            russianMode = true;
        }

        String[] mode = russianMode ? WORDS_RU : WORDS_EN;
        String secret = mode[rnd.nextInt(mode.length)];

        char[] skeleton = makeSkeleton(secret);

        Set<Character> used = new LinkedHashSet<>();
        Set<Character> wrong = new LinkedHashSet<>();
        int misses = 0;

        while (misses < MAX_MISSES && new String(skeleton).contains("_")) {
            drawGallows(misses);
            System.out.print("Слово: ");
            printSkeleton(skeleton);

            System.out.println("Использованные буквы: " + used);
            System.out.println("Ошибки: " + wrong);
            System.out.println("Осталось жизней: " + (MAX_MISSES - misses));

            System.out.println("Введите букву: ");
            String input = console.nextLine().toLowerCase();

            if (input.length() != 1) {
                System.out.println("Введите одну букву!");
                continue;
            }

            char guess = input.charAt(0);
            if (!isLetter(guess, russianMode)) {
                System.out.print("Введите букву ВЫБРАННОГО алфавита.");
                continue;
            }

            if (used.contains(guess)) {
                System.out.println("Эта буква уже вводилась");
                continue;
            }

            used.add(guess);

            if (secret.indexOf(guess) >= 0) {
                for (int i = 0; i < secret.length(); i++) {
                    if (secret.charAt(i) == guess) {
                        skeleton[i] = guess;
                    }
                }
            } else {
                wrong.add(guess);
                misses++;
                System.out.println("Неверно!");
            }
        }

        drawGallows(misses);
        if (new String(skeleton).equals(secret)) {
            System.out.println("Поздравляем! Вы угадали слово: " + secret);
        } else {
            System.out.println("Вы проиграли. Загаданное слово: " + secret);
        }
            System.out.print("Сыграть ещё? (y/n): ");
            String ans = console.nextLine().trim().toLowerCase();
            playAgain = ans.equals("y");

        } while (playAgain);

        System.out.println("Спасибо за игру!");
    }
    private static char[] makeSkeleton(String word) {
        char[] skeleton = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            skeleton[i] = '_';
        }
        return  skeleton;
    }

    private static void printSkeleton(char[] skeleton) {
        for (char c : skeleton) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private static boolean isLetter(char c, boolean russianMode) {
        if (russianMode) {
            return (c >= 'а' && c <= 'я')
                || (c >= 'А' && c <= 'Я')
                || c == 'ё' || c == 'Ё';

        } else {
            return (c >= 'a' && c <= 'z')
                || (c >= 'A' && c <= 'Z');
        }
    }

    private static void drawGallows(int misses) {
        String[] pic = {
                "  +---+   ",
                "  |   |   ",
                "  |       ",
                "  |       ",
                "  |       ",
                "  |       ",
                "=====     "
        };
        if (misses >= 1) pic[2] = "  |   O   ";
        if (misses >= 2) pic[3] = "  |   |   ";
        if (misses >= 3) pic[3] = "  |  /|   ";
        if (misses >= 4) pic[3] = "  |  /|\\  ";
        if (misses >= 5) pic[4] = "  |  /    ";
        if (misses >= 6) pic[4] = "  |  / \\  ";
        System.out.println();
        for (String s : pic) System.out.println(s);
    }
}
