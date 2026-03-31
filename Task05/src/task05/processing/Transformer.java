package task05.processing;

@FunctionalInterface
public interface Transformer <T,R>{
    R transform(T input);
}
