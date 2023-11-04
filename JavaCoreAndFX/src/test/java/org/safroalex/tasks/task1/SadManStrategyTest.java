package org.safroalex.tasks.task1;

import org.junit.jupiter.api.Test;
import org.safroalex.tasks.task1.logic.Point;
import org.safroalex.tasks.task1.logic.SadManStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SadManStrategyTest {

    @Test
    public void testMoveDownSuccessfully() {
        SadManStrategy strategy = new SadManStrategy();
        Point a = new Point(0, 0, 10);
        Point b = new Point(0, 0, 0); // Перемещение только вниз по оси Z
        assertEquals("Moved successfully", strategy.move(a, b));
    }

    @Test
    public void testFlyingError() {
        SadManStrategy strategy = new SadManStrategy();
        Point a = new Point(0, 0, 0);
        Point b = new Point(0, 0, 10); // Попытка перемещения вверх по оси Z
        assertEquals("Невозможно лететь.", strategy.move(a, b));
    }

    @Test
    public void testWalkingError() {
        SadManStrategy strategy = new SadManStrategy();
        Point a = new Point(0, 0, 0);
        Point b = new Point(10, 0, 0); // Попытка перемещения по оси X
        assertEquals("Невозможно идти.", strategy.move(a, b));
    }

    @Test
    public void testDiagonalWalkingError() {
        SadManStrategy strategy = new SadManStrategy();
        Point a = new Point(0, 0, 0);
        Point b = new Point(10, 10, -10); // Попытка перемещения по диагонали
        assertEquals("Невозможно идти.", strategy.move(a, b));
    }

    @Test
    public void testNoMovement() {
        SadManStrategy strategy = new SadManStrategy();
        Point a = new Point(0, 0, 0);
        Point b = new Point(0, 0, 0); // Нет перемещения
        assertEquals("Moved successfully", strategy.move(a, b));
    }
}
