package org.safroalex.tasks.task2.logic;

public class AntdClassForUIWithNumber {

    // Простейший публичный метод
    @Repeat(times = 1)
    public String simplePublicMethod() {
        return "This is a simple public method.";
    }

    // Защищенные методы с аннотациями и одним параметром
    @Repeat(times = 2)
    protected String protectedMethodWithInt(int a) {
        return "This is a protected method with int: " + a;
    }

    @Repeat  // использование значения по умолчанию
    protected String protectedMethodWithDouble(double b) {
        return "This is a protected method with double: " + b;
    }

    // Приватные методы с аннотациями и одним параметром
    @Repeat(times = 2)
    private String privateMethodWithString(String s) {
        return "This is a private method with String: " + s;
    }

    @Repeat(times = 3)
    private String privateMethodWithClass(Class<?> c) {
        return "This is a private method with Class: " + c.getName();
    }

    @Repeat(times = 3)
    private String privateMethodWithoutParamethers() {
        return "This is a private method WithoutParamethers" ;
    }
}
