package org.safroalex.tasks.task1;

import org.safroalex.tasks.task1.logic.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task1Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Вывести предупреждения о разных типах перемещения
        String movementInfo = """
                Movement types and their restrictions:
                Angel: Can fly but not too high, cannot walk, cannot dig.
                Rocker: Can walk but not too far, can fly but not too high, cannot dig.
                SadMan: Cannot walk, cannot fly, can dig infinitely.""";

        System.out.println(movementInfo);


        // Создать Hero с выбранным типом движения
        String moveType;
        while (true) {
            System.out.println("Enter movement type " +
                    "(Angel, Rocker, SadMan): ");
            try {
                moveType = scanner.nextLine();
                if (moveType.equalsIgnoreCase("Angel")
                        || moveType.equalsIgnoreCase("Rocker")
                        || moveType.equalsIgnoreCase("SadMan")) {
                    break;
                } else {
                    System.out.println("Invalid type. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                scanner.next();
            }
        }
        Hero hero = new Hero(moveType);

        String continueMoving = "y";
        while (continueMoving.equalsIgnoreCase("y")) {
            double x;
            double y;
            double z;
            try {
                // Получить координаты для перемещения
                System.out.println("Enter x coordinate: ");
                x = scanner.nextDouble();
                System.out.println("Enter y coordinate: ");
                y = scanner.nextDouble();
                System.out.println("Enter z coordinate: ");
                z = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Terminating program.");
                scanner.close();
                return;
            }

            // Переместить Hero и вывести результат
            Point newPoint = new Point(x, y, z);
            String moveResult = hero.move(newPoint);
            System.out.println(moveResult);
            System.out.println("Current position: " + hero.getPosition().getCoordinatesMessage());

            // Спросить, хочет ли пользователь продолжить
            System.out.println("Do you want to continue moving? (y/n): ");
            continueMoving = scanner.next();
            scanner.nextLine();  // очистить буфер
        }

        scanner.close();
    }
}
