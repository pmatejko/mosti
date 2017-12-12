package interfaces;

public interface Fetcher<T, U> {
    U fetch(T arg);
}
