package org.safroalex.tasks.task1.Task1Logic;

public class RockerStrategy implements MoveStrategy {
    private static final int ERROR_HIGH_ALTITUDE = 1;
    private static final int ERROR_DIGGING = 2;
    private static final int ERROR_WALK_LIMIT = 3;
    private static final int MAX_ALTITUDE = 50;
    private static final int MAX_WALK_DISTANCE = 100;

    @Override
    public String move(Point a, Point b) {
        // Рассчитываем разницу высот
        double altitudeDifference = b.getZ() - a.getZ();

        // Рассчитываем расстояние для ходьбы на плоскости xy
        double walkDistance = Point.getDistance(new Point(a.getX(), a.getY(), 0), new Point(b.getX(), b.getY(), 0));

        // Проверяем, можем ли мы лететь так высоко
        if (altitudeDifference > MAX_ALTITUDE) {
            return errorMessage(ERROR_HIGH_ALTITUDE);
        }

        // Проверяем, не пытаемся ли мы копать
        if (altitudeDifference < 0) {
            return errorMessage(ERROR_DIGGING);
        }

        // Проверяем, не превышает ли расстояние для ходьбы максимум
        if (walkDistance > MAX_WALK_DISTANCE) {
            return errorMessage(ERROR_WALK_LIMIT);
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
            case ERROR_WALK_LIMIT:
                return "Невозможно идти так далеко.";
            default:
                return "Неизвестная ошибка.";
        }
    }
}
