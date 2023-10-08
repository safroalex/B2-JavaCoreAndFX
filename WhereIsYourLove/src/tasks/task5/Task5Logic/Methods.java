package tasks.task5.Task5Logic;

import java.util.*;
import java.util.stream.Collectors;


public class Methods {

    public static double method1(List<Integer> list) {
        return list
                .stream()
                .mapToInt(Integer::intValue)    // mapToInt-converting a wrapper into a primitive stream
                .summaryStatistics()
                .getAverage();
    }

    public static List<String> method2(List<String> list) {
        return list
                .stream()
                .map(String::toUpperCase)       // map-give us opportunity to create function to changing
                .map(s -> "new" + s)            // each element and skipping it further
                .toList();
    }

    public static List<Integer> method3(List<Integer> list) {
        return list.stream()                                        // skipping elements further if
                .filter(n -> Collections.frequency(list, n) == 1)   // they consider the "frequency" definition
                .map(n -> n * n)                                    // Collections.frequency-how many times does
                .toList();                                          // element n occur in the list
    }

    public static List<String> method4(Collection<String> list, String firstLetter) {
        return list.stream()
                .filter(f -> f.startsWith(firstLetter))
                .sorted(String.CASE_INSENSITIVE_ORDER)               // CASE_INSENSITIVE_ORDER-sort independent
                .toList();                                           // of register
    }

    public static <T> T method5(Collection<T> list) {                // T is a generic with auto-indication of the type,
        // but you can also specify it yourself
        return list.stream()
                .reduce((a, b) -> b)             /*passes through the elements of the collection and performs the operation
                                                    to the right of the arrow, i.e. points to the last element in our case*/
                .orElseThrow(() -> new NoSuchElementException("Collection is empty")); //raise an exception
        // if reduce cannot return the result
    }

    public static int method6(int[] array) {
        return Arrays.stream(array)
                .filter(i -> i % 2 == 0)
                .reduce(0, Integer::sum);
    }

    public static Map<Character, String> method7 (List<String> list) {
        return list
                .stream()
                .collect(Collectors                 //collect-convert to collection
                        .toMap(str -> str.charAt(0), str -> str.substring(1)));
    }
}