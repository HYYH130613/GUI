package task05.processing;

import task05.model.Electronics;
import task05.model.Food;

public class PackagingTransformer {


    public static final Transformer<Electronics, String> ELECTRONICS_TO_LABEL =
            (e) -> e.getName() + ", " + e.getVoltage() + "V, " + e.getWeight() + "kg";

    public static final Transformer<Food, Double> FOOD_WEIGHT_TO_GRAMS =
            (f) -> f.getWeight() * 1000.0;

    public static <T> Transformer<T, String> toStringTransformer() {
        return Object::toString;
    }


}
