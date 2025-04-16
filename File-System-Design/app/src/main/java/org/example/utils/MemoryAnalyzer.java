package org.example.utils;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MemoryAnalyzer {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        if (instrumentation == null) {
            throw new IllegalStateException("Instrumentation is not initialized");
        }
        return instrumentation.getObjectSize(o);
    }

    public static long getDeepSize(Object o) {
        long size = getObjectSize(o);
        Class<?> clazz = o.getClass();
        
        // Get all fields including private ones
        List<Field> fields = new ArrayList<>();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                fields.add(field);
            }
            clazz = clazz.getSuperclass();
        }

        // Calculate size of all fields
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(o);
                if (value != null) {
                    size += getObjectSize(value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return size;
    }

    public static void printMemoryUsage(Object o) {
        System.out.println("Object: " + o.getClass().getName());
        System.out.println("Shallow size: " + getObjectSize(o) + " bytes");
        System.out.println("Deep size: " + getDeepSize(o) + " bytes");
    }
} 