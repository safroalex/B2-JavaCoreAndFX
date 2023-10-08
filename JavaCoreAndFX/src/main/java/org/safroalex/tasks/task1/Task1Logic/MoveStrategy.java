package org.safroalex.tasks.task1.Task1Logic;

public interface MoveStrategy {
    String move(Point a, Point b);

    String errorMessage(int errorWord);
}
