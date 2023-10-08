package tasks.task1.Task1Logic;

public class SadManStrategy implements MoveStrategy {
    private static final int ERROR_FLYING = 1;
    private static final int ERROR_WALKING = 2;

    @Override
    public String move(Point a, Point b) {
        // Рассчитываем разницу высот
        double altitudeDifference = b.getZ() - a.getZ();

        // Рассчитываем расстояние для ходьбы на плоскости xy
        double walkDistance = Point.getDistance(new Point(a.getX(), a.getY(), 0), new Point(b.getX(), b.getY(), 0));

        // Проверяем, не пытаемся ли мы лететь
        if (altitudeDifference > 0) {
            return errorMessage(ERROR_FLYING);
        }

        // Проверяем, не пытаемся ли мы ходить
        if (walkDistance > 0) {
            return errorMessage(ERROR_WALKING);
        }

        // Перемещение успешно (только вниз)
        return "Moved successfully";
    }

    @Override
    public String errorMessage(int errorCode) {
        switch (errorCode) {
            case ERROR_FLYING:
                return "Невозможно лететь.";
            case ERROR_WALKING:
                return "Невозможно идти.";
            default:
                return "Неизвестная ошибка.";
        }
    }
}
