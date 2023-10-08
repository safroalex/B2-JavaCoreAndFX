package tasks.task1.Task1Logic;

import java.util.Objects;

public class Hero {
    private MoveStrategy moveStrategy;
    private Point position;

    // Конструктор по умолчанию
    public Hero() {
        this.moveStrategy = new RockerStrategy();
        this.position = new Point(0, 0, 0);
    }

    // Конструктор с заданной стратегией перемещения
    public Hero(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
        this.position = new Point(0, 0, 0);
    }

    // Конструктор с заданным типом перемещения в виде строки
    public Hero(String moveType) {
        changeMovementType(moveType);
        this.position = new Point(0, 0, 0);
    }

    public boolean changeMovementType(String type) {
        switch (type.toLowerCase()) {
            case "angel":
                this.moveStrategy = new AngelStrategy();
                return true;
            case "rocker":
                this.moveStrategy = new RockerStrategy();
                return true;
            case "sadman":
                this.moveStrategy = new SadManStrategy();
                return true;
            default:
                this.moveStrategy = new RockerStrategy();
                return false;
        }
    }

    public String move(Point a) {
        String moveResult = this.moveStrategy.move(this.position, a);
        if (Objects.equals(moveResult, "Moved successfully")) {
            this.position = a;
        }
        return moveResult;
    }

    public Point getPosition() {
        return position;
    }
}
