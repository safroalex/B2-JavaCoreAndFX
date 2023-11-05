package org.safroalex.tasks.task1.logic;

import java.lang.Math;

public record Point(double x, double y, double z) {

    /**
     * Вычисляет расстояние между двумя точками в трехмерном пространстве.
     *
     * @param A начальная точка.
     * @param B конечная точка.
     * @return возвращает евклидово расстояние между точками A и B.
     */
    public static double getDistance(Point A, Point B) {
        return Math.sqrt(Math.pow(A.x() - B.x(), 2)
                + Math.pow(A.y() - B.y(), 2)
                + Math.pow(A.z() - B.z(), 2));
    }

    /**
     * Предоставляет текстовое представление координат точки.
     *
     * @return строка с координатами точки, разделенными двоеточием.
     */
    public String getCoordinatesMessage() {
        return x + ":" + y + ":" + z;
    }

}