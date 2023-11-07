package org.safroalex.tasks.task2.logic;

public class AntdClassForUIWoutNumber {

    // Простейший публичный метод
    @Repeat
    public String simplePublicMethod() {
        return "This is a simple public method.   s";
    }

    // Защищенные методы с аннотациями и одним параметром
    @Repeat
    protected String protectedMethodWithInt(int a) {
        return "This is a protected method with int: " + a;
    }

    @Repeat  // использование значения по умолчанию
    protected String protectedMethodWithDouble(double b) {
        return "This is a protected method with double: " + b;
    }

    // Приватные методы с аннотациями и одним параметром
    @Repeat
    private String privateMethodWithString(String s) {
        return "This is a private method with String: " + s;
    }

    @Repeat
    private String privateMethodWithClass(Class<?> c) {
        return "This is a private method with Class: " + c.getName();
    }
}
