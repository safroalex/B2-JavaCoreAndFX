package org.safroalex.tasks.task1.logic;

/**
 * Реализация стратегии движения "Ангела".
 * Ангел не может копать вниз и ходить по горизонтали.
 */
public class AngelStrategy implements MoveStrategy {
    private static final int ERROR_DIGGING = 1;
    private static final int ERROR_WALKING = 2;


    /**
     * Метод для выполнения движения между двумя точками.
     * Ангел может двигаться только вверх.
     *
     * @param a начальная точка
     * @param b конечная точка
     * @return Сообщение о результате перемещения.
     */
    @Override
    public String move(Point a, Point b) {
        // Рассчитываем разницу высот
        double altitudeDifference = b.z() - a.z();
        double horizontalDistance = Math.sqrt
                (Math.pow(b.x() - a.x(), 2)
                        + Math.pow(b.y() - a.y(), 2));

        // Проверяем, не пытаемся ли мы копать
        if (altitudeDifference < 0) {
            return errorMessage(ERROR_DIGGING);
        }

        // Если нет вертикального перемещения (по оси z),
        // но есть горизонтальное (по осям x или y), то это ошибка
        if (altitudeDifference == 0 && horizontalDistance > 0) {
            return errorMessage(ERROR_WALKING);
        }

        // Перемещение успешно
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
            case ERROR_DIGGING -> "Невозможно копать или лететь вниз.";
            case ERROR_WALKING -> "Невозможно ходить.";
            default -> "Неизвестная ошибка.";
        };
    }
}
