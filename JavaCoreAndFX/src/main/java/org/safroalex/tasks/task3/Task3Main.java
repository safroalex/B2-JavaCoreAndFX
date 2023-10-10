package org.safroalex.tasks.task3;

import org.safroalex.tasks.task3.logic.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class Task3Main {
    public static void main(String[] args) {

        // SegregateMlecopit
        Collection<Hierarchy.Mlecopit> mlecopit = Arrays.asList(
                new Hierarchy.Coshki(),
                new Hierarchy.Ezhovie(),
                new Hierarchy.EzhStandart(),
                new Hierarchy.Manul(),
                new Hierarchy.Ryci()
        );

        Collection<Hierarchy.Ezhovie> mlezhovie = new ArrayList<>();
        Collection<Hierarchy.Coshki> mlcoshki = new ArrayList<>();
        Collection<Hierarchy.Predator> mlpredator = new ArrayList<>();

        Segregate mlsegregate = new Segregate();
        mlsegregate.segregate(mlecopit, mlezhovie, mlcoshki, mlpredator);

        System.out.println("Mlezhovie size: " + mlezhovie.size());
        System.out.println("Mlcoshki size: " + mlcoshki.size());
        System.out.println("Mlpredator size: " + mlpredator.size());


        //SegregatePredator
        Collection<Hierarchy.Predator> predator = Arrays.asList(
                new Hierarchy.Ryci(),
                new Hierarchy.Manul(),
                new Hierarchy.Ryci()
                );

        Collection<Hierarchy.Chordate> prchordate = new ArrayList<>();
        Collection<Hierarchy.Manul> prmanul = new ArrayList<>();
        Collection<Hierarchy.Coshki> prcoshki = new ArrayList<>();

        Segregate prsegregate = new Segregate();
        prsegregate.segregate(predator, prchordate, prmanul, prcoshki);

        System.out.println("Prchordate size: " + prchordate.size());
        System.out.println("Prmanul size: " + prmanul.size());
        System.out.println("Prcoshki size: " + prcoshki.size());


        //SegregateEzhovie
        Collection<Hierarchy.Ezhovie> ezhovie = Arrays.asList(
                new Hierarchy.Ezhovie(),
                new Hierarchy.EzhStandart(),
                new Hierarchy.EzhStandart()
        );

        Collection<Hierarchy.Nasecomoyadnye> eznasecomoyadnye
                = new ArrayList<>();
        Collection<Hierarchy.Predator> ezpredator = new ArrayList<>();
        Collection<Hierarchy.Predator> ezpredator2 = new ArrayList<>();

        Segregate ezsegregate = new Segregate();
        ezsegregate
                .segregate(ezhovie, eznasecomoyadnye, ezpredator, ezpredator2);

        System.out.println("Eznasecomoyadnye size: " + eznasecomoyadnye.size());
        System.out.println("Ezpredator size: " + ezpredator.size());
        System.out.println("Ezpredator2 size: " + ezpredator2.size());
    }


}

