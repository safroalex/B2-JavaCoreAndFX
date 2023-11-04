package org.safroalex.tasks.task3;

import org.safroalex.tasks.task3.logic.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class Task3Main {
    public static void main(String[] args) {

        // SegregateMlecopit
        Collection<Hierarchy.Mlecopit> mlecopit = Arrays.asList(
                new Hierarchy.EzhStandart(),
                new Hierarchy.Manul(),
                new Hierarchy.Ryci()
        );

        Collection<Hierarchy.Ezhovie> mlezhovie = new ArrayList<>();
        Collection<Hierarchy.Coshki> mlcoshki = new ArrayList<>();
        Collection<Hierarchy.Predator> mlpredator = new ArrayList<>();

        Segregate.segregate(mlecopit, mlezhovie, mlcoshki, mlpredator);

        System.out.println("ezhovie size: " + mlezhovie.size());
        System.out.println("coshki size: " + mlcoshki.size());
        System.out.println("predator size: " + mlpredator.size());

        //SegregatePredator
        Collection<Hierarchy.Predator> predator = Arrays.asList(
                new Hierarchy.Ryci(),
                new Hierarchy.Manul(),
                new Hierarchy.Ryci()
                );

        Collection<Hierarchy.Chordate> prchordate = new ArrayList<>();
        Collection<Hierarchy.Manul> prmanul = new ArrayList<>();
        Collection<Hierarchy.Coshki> prcoshki = new ArrayList<>();

        Segregate.segregate(predator, prchordate, prmanul, prcoshki);

        System.out.println("chordate size: " + prchordate.size());
        System.out.println("manul size: " + prmanul.size());
        System.out.println("coshki size: " + prcoshki.size());

        //SegregateEzhovie
        Collection<Hierarchy.Ezhovie> ezhovie = Arrays.asList(
                new Hierarchy.EzhStandart(),
                new Hierarchy.EzhStandart()
        );

        Collection<Hierarchy.Nasecomoyadnye> ezezhovie
                = new ArrayList<>();
        Collection<Hierarchy.Predator> ezpredator = new ArrayList<>();
        Collection<Hierarchy.Predator> ezpredator2 = new ArrayList<>();

        Segregate.segregate(ezhovie, ezezhovie, ezpredator, ezpredator2);

        System.out.println("nasecomoyadnye size: " + ezezhovie.size());
        System.out.println("predator size: " + ezpredator.size());
        System.out.println("predator2 size: " + ezpredator2.size());
    }


}

