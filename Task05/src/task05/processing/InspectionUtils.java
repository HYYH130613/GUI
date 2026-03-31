package task05.processing;

import task05.container.Box;
import task05.container.Pair;

public class InspectionUtils <T>{
    public static <T> void logInspection(T item){
        System.out.println("=== INSPECTION LOG ===");
        System.out.println(item.getClass().getSimpleName());
        System.out.println(item.toString());
        System.out.println("======================");

    }

    public static <T> Pair<String, T> label(String id, T obj){
        System.out.println(String.format("Assigning label '%s' to: '%s'", id, obj));
        return new Pair<>(id, obj);
    }

    public static <T> void transferBetweenBoxes(Box<T> source, Box<T> destination){
        T item = source.getAndClear();
        destination.put(item);

        System.out.println("Moved: "+item);
    }

}
