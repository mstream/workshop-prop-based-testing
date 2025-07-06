package workshop.counter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class ThreadUnsafeCounterImpl implements Counter {

    private final Map<String, Long> state = new HashMap<>();

    @Override
    public void increment(String name) {
        state.compute(
                name,
                (n, value) -> value == null ? 1L : value + 1
        );
    }

    @Override
    public Map<String, Long> getValues() {
        return Collections.unmodifiableMap(state);
    }

    @Override
    public String toString() {
        return "ThreadUnsafeCounter";
    }
}
