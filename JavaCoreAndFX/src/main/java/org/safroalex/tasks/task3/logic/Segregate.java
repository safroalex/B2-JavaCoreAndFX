package org.safroalex.tasks.task3.logic;

import java.util.Collection;

public class Segregate {
    public <T extends Hierarchy.Chordate, U, V, W> void segregate(
            Collection<T> src,
            Collection<? super U> collection1,
            Collection<? super V> collection2,
            Collection<? super W> collection3) {

        for (T animal : src) {
            if (animal instanceof Hierarchy.Ezhovie) {
                collection1.add((U) animal);
            } else if (animal instanceof Hierarchy.Coshki) {
                collection2.add((V) animal);
            } else if (animal instanceof Hierarchy.Predator) {
                collection3.add((W) animal);
            }
        }
    }
}
