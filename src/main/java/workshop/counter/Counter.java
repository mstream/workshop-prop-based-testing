package workshop.counter;

import java.util.Map;

public interface Counter {
    void increment(String key);

    Map<String, Long> getValues();
}
