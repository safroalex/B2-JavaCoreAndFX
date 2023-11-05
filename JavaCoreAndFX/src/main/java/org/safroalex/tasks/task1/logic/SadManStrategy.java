package org.safroalex.tasks.task1.logic;

/**
 * Реализация стратегии движения "Печального Человека".
 * Печальный Человек может только падать вниз.
 */
public class SadManStrategy implements MoveStrategy {
    private static final int ERROR_FLYING = 1;
    private static final int ERROR_WALKING = 2;

    /**
     * Метод для выполнения движения между двумя точками.
     * Печальный человек не может двигаться горизонтально или вверх.
     *
     * @param a начальная точка
     * @param b конечная точка
     * @return Сообщение о результате перемещения.
     */
    @Override
    public String move(Point a, Point b) {
        // Рассчитываем разницу высот
        double altitudeDifference = b.z() - a.z();

        // Рассчитываем расстояние для ходьбы на плоскости xy
        double walkDistance = Point.getDistance(
                new Point(a.x(), a.y(), 0),
                new Point(b.x(), b.y(), 0));

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

    /**
     * Возвращает сообщение об ошибке на основе кода ошибки.
     *
     * @param errorCode Код ошибки.
     * @return Сообщение об ошибке.
     */
    @Override
    public String errorMessage(int errorCode) {
        return switch (errorCode) {
            case ERROR_FLYING -> "Невозможно лететь.";
            case ERROR_WALKING -> "Невозможно идти.";
            default -> "Неизвестная ошибка.";
        };
    }
}
