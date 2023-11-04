package org.safroalex.tasks.task1.logic;

import java.lang.Math;

public record Point(double x, double y, double z) {

    public static double getDistance(Point A, Point B) {
        return Math.sqrt(Math.pow(A.x() - B.x(), 2)
                + Math.pow(A.y() - B.y(), 2)
                + Math.pow(A.z() - B.z(), 2));
    }

    public String getCoordinatesMessage() {
        return x + ":" + y + ":" + z;
    }

}