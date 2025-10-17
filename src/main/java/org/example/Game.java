package org.example;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;

        while (playAgain) {
            int secretNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int guess = -1;

            System.out.println("Я загадал число от 1 до 100. Попробуйте угадать!");

            while (guess != secretNumber) {
                System.out.print("Ваш вариант: ");
                guess = scanner.nextInt();
                attempts++;

                if (guess < secretNumber) {
                    System.out.println("Слишком мало! Попробуйте число побольше.");
                } else if (guess > secretNumber) {
                    System.out.println("Слишком много! Попробуйте число поменьше.");
                }
            }

            System.out.println("Поздравляю! Вы угадали число за " + attempts + " попыток.");
            System.out.print("Хотите сыграть ещё раз? (д/н): ");
            playAgain = scanner.next().equalsIgnoreCase("д");
        }

        scanner.close();
    }
}
