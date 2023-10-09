package org.safroalex.tasks.task1.logic;

public interface MoveStrategy {
    String move(Point a, Point b);

    String errorMessage(int errorWord);
}
