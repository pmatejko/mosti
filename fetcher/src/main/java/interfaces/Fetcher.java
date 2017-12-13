package interfaces;

import java.util.List;
import java.io.IOException;

public interface Fetcher<T, U> {
    List<U> fetch(T arg) throws IOException;
}
