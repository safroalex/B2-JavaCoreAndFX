package org.safroalex.tasks.task2;

import java.lang.reflect.Method;
import org.safroalex.tasks.task2.Task2Logic.*;
public class Task2Main {

    public static void main(String[] args) {
        AnnotatedClass obj = new AnnotatedClass();
        Method[] methods = AnnotatedClass.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Repeat.class)) {
                Repeat repeat = method.getAnnotation(Repeat.class);
                int times = repeat.times();
                method.setAccessible(true); // Для доступа к приватным и защищенным методам

                for (int i = 0; i < times; i++) {
                    try {
                        // Передаем параметры в зависимости от метода
                        if (method.getName().equals("protectedMethodWithInt")) {
                            method.invoke(obj, 1);
                        } else if (method.getName().equals("protectedMethodWithDouble")) {
                            method.invoke(obj, 1.0);
                        } else if (method.getName().equals("privateMethodWithString")) {
                            method.invoke(obj, "test");
                        } else if (method.getName().equals("privateMethodWithClass")) {
                            method.invoke(obj, AnnotatedClass.class);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < times; i++) {
                    try {
                        // Если мы ничего не знаем о методе, мы можем использовать информацию о типах его параметров
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        Object[] parameters = new Object[parameterTypes.length];

                        for (int j = 0; j < parameterTypes.length; j++) {
                            // Здесь мы просто используем некоторые дефолтные значения для разных типов
                            if (parameterTypes[j] == int.class) {
                                parameters[j] = 0;
                            } else if (parameterTypes[j] == double.class) {
                                parameters[j] = 0.0;
                            } else if (parameterTypes[j] == String.class) {
                                parameters[j] = "I really want to see!";
                            } else if (parameterTypes[j] == Class.class) {
                                parameters[j] = Object.class;
                            }
                        }

                        method.invoke(obj, parameters);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
