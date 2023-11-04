package org.safroalex.tasks.task3.logic;

import java.util.Collection;

public class Segregate {
    static public <T1, T2, T3 extends Hierarchy.Chordate> void segregate
            (Collection<? extends Hierarchy.Chordate> src,
             Collection<? super Hierarchy.EzhStandart> collection1,
             Collection<? super Hierarchy.Manul> collection2,
             Collection<? super Hierarchy.Ryci> collection3) {
        for (Hierarchy.Chordate animal : src) {
            if (animal instanceof Hierarchy.EzhStandart) {
                collection1.add((Hierarchy.EzhStandart) animal);
            }
            if (animal instanceof Hierarchy.Manul) {
                collection2.add((Hierarchy.Manul) animal);
            }
            if (animal instanceof Hierarchy.Ryci) {
                collection3.add((Hierarchy.Ryci) animal);
            }
        }
    }
}
