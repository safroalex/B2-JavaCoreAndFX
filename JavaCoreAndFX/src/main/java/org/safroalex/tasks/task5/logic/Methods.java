package org.safroalex.tasks.task5.logic;

import java.util.*;
import java.util.stream.Collectors;


public class Methods {

    /**
     * Вычисляет среднее значение списка целых чисел.
     *
     * @param list Список целых чисел для обработки.
     * @return Среднее значение чисел в списке.
     */
    public static double method1(List<Integer> list) {
        return list
                .stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage();
    }

    /**
     * Преобразует список строк в верхний регистр и добавляет префикс "new" к каждой строке.
     *
     * @param list Список строк для обработки.
     * @return Обновленный список строк.
     */
    public static List<String> method2(List<String> list) {
        return list
                .stream()
                .map(String::toUpperCase)
                .map(s -> "new" + s)
                .toList();
    }

    /**
     * Возвращает список уникальных чисел, возведенных в квадрат.
     *
     * @param list Список целых чисел для обработки.
     * @return Список квадратов уникальных чисел.
     */
    public static List<Integer> method3(List<Integer> list) {
        return list.stream()
                .filter(n -> Collections.frequency(list, n) == 1)
                .map(n -> n * n)
                .toList();
    }

    /**
     * Фильтрует коллекцию строк, возвращая те, что начинаются с заданной буквы, в отсортированном порядке.
     *
     * @param list Коллекция строк для обработки.
     * @param firstLetter Первая буква для фильтрации строк.
     * @return Отфильтрованный и отсортированный список строк.
     */
    public static List<String> method4(Collection<String> list, String firstLetter) {
        return list.stream()
                .filter(f -> f.startsWith(firstLetter))
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toList();
    }

    /**
     * Возвращает последний элемент из потока элементов.
     *
     * @param <T> Тип элементов в коллекции.
     * @param list Коллекция элементов.
     * @return Последний элемент коллекции.
     * @throws NoSuchElementException Если коллекция пуста.
     */
    public static <T> T method5(Collection<T> list) {
        return list.stream()
                .reduce((a, b) -> b)
                .orElseThrow(() -> new NoSuchElementException("Collection is empty"));
    }

    /**
     * Суммирует все четные числа в массиве.
     *
     * @param array Массив целых чисел для обработки.
     * @return Сумма четных чисел в массиве.
     */
    public static int method6(int[] array) {
        return Arrays.stream(array)
                .filter(i -> i % 2 == 0)
                .reduce(0, Integer::sum);
    }

    /**
     * Создает карту, в которой каждая строка отображается на символ, являющийся ее первым символом.
     *
     * @param list Список строк для создания карты.
     * @return Карта с первыми символами строк в качестве ключей и оставшейся частью строки в качестве значений.
     */
    public static Map<Character, String> method7 (List<String> list) {
        return list.stream().collect(Collectors
                        .toMap(str -> str.charAt(0), str -> str.substring(1)));
    }
}