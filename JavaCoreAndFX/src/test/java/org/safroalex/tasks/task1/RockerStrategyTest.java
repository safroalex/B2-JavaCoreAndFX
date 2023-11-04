package org.safroalex.tasks.task1;

import org.junit.jupiter.api.Test;
import org.safroalex.tasks.task1.logic.Point;
import org.safroalex.tasks.task1.logic.RockerStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RockerStrategyTest {

    @Test
    public void testMoveSuccessfully() {
        RockerStrategy strategy = new RockerStrategy();
        Point a = new Point(0, 0, 0);
        Point b = new Point(10, 10, 0); // Только перемещение по X и Y
        assertEquals("Moved successfully", strategy.move(a, b));
    }

    @Test
    public void testFlyingError() {
        RockerStrategy strategy = new RockerStrategy();
        Point a = new Point(0, 0, 0);
        Point b = new Point(0, 0, 1); // Изменение по Z вверх
        assertEquals("Невозможно лететь.", strategy.move(a, b));
    }

    @Test
    public void testDiggingError() {
        RockerStrategy strategy = new RockerStrategy();
        Point a = new Point(0, 0, 0);
        Point b = new Point(0, 0, -1); // Изменение по Z вниз
        assertEquals("Невозможно копать.", strategy.move(a, b));
    }
}
