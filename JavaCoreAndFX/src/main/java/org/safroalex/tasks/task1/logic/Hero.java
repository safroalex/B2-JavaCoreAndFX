package org.safroalex.tasks.task1.logic;

import java.util.Objects;

/**
 * Класс Hero представляет героя, который может перемещаться по различным стратегиям.
 */
public class Hero {
    private MoveStrategy moveStrategy;
    private Point position;

    /**
     * Конструктор по умолчанию. Устанавливает начальную позицию и стратегию перемещения героя.
     */
    public Hero() {
        this.moveStrategy = new RockerStrategy();
        this.position = new Point(0, 0, 0);
    }

    /**
     * Конструктор с заданной стратегией перемещения.
     *
     * @param moveType строка, определяющая тип стратегии перемещения.
     */
    public Hero(String moveType) {
        changeMovementType(moveType);
        this.position = new Point(0, 0, 0);
    }

    /**
     * Меняет стратегию перемещения героя.
     *
     * @param type строка, определяющая новый тип стратегии перемещения.
     */
    public void changeMovementType(String type) {
        switch (type.toLowerCase()) {
            case "angel" -> this.moveStrategy = new AngelStrategy();
            case "rocker" -> this.moveStrategy = new RockerStrategy();
            case "sadman" -> this.moveStrategy = new SadManStrategy();
            // В случае неизвестного типа используется стратегия "rocker".
            default -> this.moveStrategy = new RockerStrategy();
        }
    }

    /**
     * Перемещает героя в новую позицию, если перемещение успешно.
     *
     * @param a точка назначения.
     * @return строка с результатом перемещения.
     */
    public String move(Point a) {
        String moveResult = this.moveStrategy.move(this.position, a);
        if (Objects.equals(moveResult, "Moved successfully")) {
            this.position = a;
        }
        return moveResult;
    }

    /**
     * Получает текущую позицию героя.
     *
     * @return текущая позиция героя.
     */
    public Point getPosition() {
        return position;
    }
}
