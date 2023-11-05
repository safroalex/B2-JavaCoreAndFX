package org.safroalex.tasks.task1.logic;

/**
 * Интерфейс для стратегий перемещения.
 * Определяет методы для перемещения и генерации сообщений об ошибках.
 */
public interface MoveStrategy {
    String move(Point a, Point b);

    String errorMessage(int errorWord);
}
