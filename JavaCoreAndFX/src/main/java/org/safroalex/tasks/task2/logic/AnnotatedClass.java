package org.safroalex.tasks.task2.logic;

/**
 * Класс, содержащий методы с аннотацией @Repeat для демонстрации
 * возможности повторного выполнения метода заданное количество раз.
 */
public class AnnotatedClass {

    /**
     * Простой публичный метод без параметров и аннотаций.
     */
    public void simplePublicMethod() {
        System.out.println("This is a simple public method.");
    }

    /**
     * Защищенный метод, аннотированный для двукратного выполнения.
     * Принимает один параметр типа int.
     * @param a целочисленное значение для демонстрации.
     */
    @Repeat(times = 2)
    protected void protectedMethodWithInt(int a) {
        System.out.println("This is a protected method with int: " + a);
    }

    /**
     * Защищенный метод с использованием аннотации @Repeat со значением по умолчанию.
     * Принимает один параметр типа double.
     * @param b значение с плавающей точкой для демонстрации.
     */
    @Repeat  // использование значения по умолчанию
    protected void protectedMethodWithDouble(double b) {
        System.out.println("This is a protected method with double: " + b);
    }

    /**
     * Приватный метод, аннотированный для трехкратного выполнения.
     * Принимает один параметр типа String.
     * @param s строка для демонстрации.
     */
    @Repeat(times = 3)
    private void privateMethodWithString(String s) {
        System.out.println("This is a private method with String: " + s);
    }

    /**
     * Приватный метод, аннотированный для трехкратного выполнения.
     * Принимает один параметр типа Class<?>.
     * @param c класс-параметр для демонстрации использования рефлексии.
     */
    @Repeat(times = 3)
    private void privateMethodWithClass(Class<?> c) {
        System.out.println("This is a private method with Class: " + c.getName());
    }
}