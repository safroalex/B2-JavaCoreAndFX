package tasks.task2.Task2Logic;

public class AnnotatedClass {

    // Простейший публичный метод
    public void simplePublicMethod() {
        System.out.println("This is a simple public method.");
    }

    // Защищенные методы с аннотациями и одним параметром
    @Repeat(times = 2)
    protected void protectedMethodWithInt(int a) {
        System.out.println("This is a protected method with int: " + a);
    }

    @Repeat  // использование значения по умолчанию
    protected void protectedMethodWithDouble(double b) {
        System.out.println("This is a protected method with double: " + b);
    }

    // Приватные методы с аннотациями и одним параметром
    @Repeat(times = 3)
    private void privateMethodWithString(String s) {
        System.out.println("This is a private method with String: " + s);
    }

    @Repeat(times = 3)
    private void privateMethodWithClass(Class<?> c) {
        System.out.println("This is a private method with Class: " + c.getName());
    }
}