package tasks.task5;

import tasks.task5.Task5Logic.Methods;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class Task5Main {
    public static void main(String[] args) {
        System.out.println("First method: ");
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        System.out.println(Methods.method1(list) + "\n");

        System.out.println("Second method: ");
        List<String> list2 = List.of("niGa", "Nigga", "NIGGA");
        System.out.println(Methods.method2(list2) + "\n");

        System.out.println("Third method: ");
        List<Integer> list3 = List.of(2, 2, 234, 3, 3, 0);
        System.out.println(Methods.method3(list3) + "\n");

        System.out.println("Fourth method: ");
        List<String> list4 = Arrays.asList("Acdde", "Abec", "Dbcda");
        System.out.println(Methods.method4(list4, "A") + "\n");

        System.out.println("Fifth method: ");
        List<Integer> list5 = List.of(234, 34, 34, 435, 0);
        List<Integer> checkException5 = List.of();
        try {
            System.out.println(Methods.method5(list5));
            System.out.println(Methods.method5(checkException5));
        }
        catch (NoSuchElementException e) {
            System.out.println(e.getMessage() + "\n");
        }

        System.out.println("Sixth method: ");
        int[] array = {10, 11, 12, 13, 14, 15};
        System.out.println(Methods.method6(array));
        int[] array2 = {11, 13, 15};
        System.out.println(Methods.method6(array2) + "\n");

        System.out.println("Seventh method: ");
        List<String> list6 = List.of("that", "for", "map");
        System.out.println(Methods.method7(list6));
    }
}