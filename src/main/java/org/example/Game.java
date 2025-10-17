package org.example;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final String RECORD_FILE = "records.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int bestScore = loadBestScore();

        while (playAgain) {
            int secretNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int guess = -1;

            System.out.println("Я загадал число от 1 до 100. Попробуйте угадать!");
            if (bestScore != Integer.MAX_VALUE) {
                System.out.println("Лучший результат: " + bestScore + " попыток");
            }

            while (guess != secretNumber) {
                System.out.print("Ваш вариант: ");
                guess = scanner.nextInt();
                attempts++;

                if (guess < 1 || guess > 100) {
                    System.out.println("Число должно быть от 1 до 100.");
                    continue;
                }

                if (guess < secretNumber) {
                    System.out.println("Слишком мало! Попробуйте число побольше.");
                } else if (guess > secretNumber) {
                    System.out.println("Слишком много! Попробуйте число поменьше.");
                }
            }

            System.out.println("Поздравляю! Вы угадали число за " + attempts + " попыток.");
            if (attempts < bestScore) {
                bestScore = attempts;
                saveBestScore(bestScore);
                System.out.println("🎉 Новый рекорд!");
            }

            System.out.print("Хотите сыграть ещё раз? (д/н): ");
            playAgain = scanner.next().equalsIgnoreCase("д");
        }

        scanner.close();
    }

    private static int loadBestScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RECORD_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    private static void saveBestScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECORD_FILE))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            System.err.println("Не удалось сохранить рекорд: " + e.getMessage());
        }
    }
}
