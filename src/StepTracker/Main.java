package StepTracker;

import java.io.InputStream;
import java.util.Scanner;


import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        StepTracker steptracker = new StepTracker();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int command = scanner.nextInt();
            scanner.nextLine();
            if (command == 1) {
                System.out.println("Введите месяц.");
                String month = scanner.nextLine();
                System.out.println("Введите день.");
                int day = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Введите количество шагов.");
                int steps = scanner.nextInt();
                if (steptracker.addSteps(month, day, steps)){
                    System.out.println("Спасибо, данные получены.");
                } else {
                    System.out.println("Извините, что-то пошло не так.");
                }
            } else if (command == 2) {
                System.out.println("Пожалуйста, введите месяц.");
                String month = scanner.nextLine();
                steptracker.statistics(month);
            } else if (command == 3) {
                System.out.println("Введите вашу цель по количеству шагов за день.");
                int target = scanner.nextInt();
                scanner.nextLine();
                steptracker.setTarget(target);
            } else if (command == 4) {
                break;
            } else {
                out.println("Извините, такой команды нет.");
            }
        }

    }

    public static void printMenu(){

        out.println("Добро пожаловать в Счётчик калорий! Пожалуйста, выберите команду из списка.");
        out.println("1. - Ввести количество шагов за определённый день.");
        out.println("2. - Напечатать статистику за определённый месяц");
        out.println("3. - Изменить цель по количеству шагов в день");
        out.println("4. - Выйти из приложения.");
    }

}
