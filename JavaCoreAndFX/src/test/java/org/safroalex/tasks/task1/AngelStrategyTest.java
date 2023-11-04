package org.safroalex.tasks.task1;


import org.junit.jupiter.api.Test;
import org.safroalex.tasks.task1.logic.AngelStrategy;
import org.safroalex.tasks.task1.logic.Point;

import static org.junit.jupiter.api.Assertions.*;

class AngelStrategyTest {
    //ось X - для горизонта
    //ось Y - для глубины
    //ось Z - для высоты
    //Начало координат - точка A.
    @Test
    void testMoveUpSuccessfully() {
        AngelStrategy strategy = new AngelStrategy();
        Point a = new Point(0, 0, 0);
        Point b = new Point(0, 0, 50);
        assertEquals("Moved successfully", strategy.move(a, b));
    }

    @Test
    void testDiggingError() {
        AngelStrategy strategy = new AngelStrategy();
        Point a = new Point(0, 0, 100);
        Point b = new Point(0, 0, 50);
        assertEquals("Невозможно копать или лететь вниз.", strategy.move(a, b));
    }

    @Test
    void testWalkingError() {
        AngelStrategy strategy = new AngelStrategy();
        Point a = new Point(0, 0, 100);
        Point b = new Point(10, 10, 100);
        assertEquals("Невозможно ходить.", strategy.move(a, b));
    }

    @Test
    void testMoveDiagonallySuccessfully() {
        AngelStrategy strategy = new AngelStrategy();
        Point a = new Point(0, 0, 0);
        Point b = new Point(10, 10, 10);
        assertEquals("Moved successfully", strategy.move(a, b));
    }
}
