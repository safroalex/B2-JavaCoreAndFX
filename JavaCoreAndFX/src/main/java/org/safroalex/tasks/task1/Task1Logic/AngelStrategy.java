package org.safroalex.tasks.task1.Task1Logic;

public class AngelStrategy implements MoveStrategy {
    private static final int ERROR_HIGH_ALTITUDE = 1;
    private static final int ERROR_DIGGING = 2;
    private static final int ERROR_WALKING = 3;
    private static final int MAX_ALTITUDE = 100;

    @Override
    public String move(Point a, Point b) {
        // Рассчитываем разницу высот
        double altitudeDifference = b.getZ() - a.getZ();

        // Проверяем, можем ли мы лететь так высоко
        if (altitudeDifference > MAX_ALTITUDE) {
            return errorMessage(ERROR_HIGH_ALTITUDE);
        }

        // Проверяем, не пытаемся ли мы копать
        if (altitudeDifference < 0) {
            return errorMessage(ERROR_DIGGING);
        }

        // Проверяем, не пытаемся ли мы ходить
        if (altitudeDifference == 0) {
            return errorMessage(ERROR_WALKING);
        }

        // Перемещение успешно
        return "Moved successfully";
    }

    @Override
    public String errorMessage(int errorCode) {
        switch (errorCode) {
            case ERROR_HIGH_ALTITUDE:
                return "Невозможно лететь так высоко.";
            case ERROR_DIGGING:
                return "Невозможно копать.";
            case ERROR_WALKING:
                return "Невозможно ходить.";
            default:
                return "Неизвестная ошибка.";
        }
    }
}
