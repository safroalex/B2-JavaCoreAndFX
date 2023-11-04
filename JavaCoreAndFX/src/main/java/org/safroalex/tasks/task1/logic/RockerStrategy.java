package org.safroalex.tasks.task1.logic;

public class RockerStrategy implements MoveStrategy {
    private static final int ERROR_FLYING = 1;
    private static final int ERROR_DIGGING = 2;

    @Override
    public String move(Point a, Point b) {
        // Рассчитываем разницу высот
        double altitudeDifference = b.z() - a.z();

        // Проверяем, не пытаемся ли мы копать
        if (altitudeDifference < 0) {
            return errorMessage(ERROR_DIGGING);
        }

        if (altitudeDifference != 0) {
            return errorMessage(ERROR_FLYING);
        }

        // Перемещение успешно
        return "Moved successfully";
    }

    @Override
    public String errorMessage(int errorCode) {
        return switch (errorCode) {
            case ERROR_FLYING -> "Невозможно лететь.";
            case ERROR_DIGGING -> "Невозможно копать.";
            default -> "Неизвестная ошибка.";
        };
    }
}
