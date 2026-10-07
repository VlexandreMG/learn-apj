package utils;

import java.util.ArrayList;
import java.util.List;

public class ImportContext {

    private static ThreadLocal<List<String>> errors = new ThreadLocal<>();

    public static void init() {
        errors.set(new ArrayList<>());
    }

    public static void addError(String err) {
        if (errors.get() == null) {
            errors.set(new ArrayList<>());
        }
        errors.get().add(err);
    }

    public static List<String> getErrors() {
        return errors.get();
    }

    public static void clear() {
        errors.remove();
    }
}