package workshop.counter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CounterModel {
    static class Model implements Counter {
        private final List<String> increments = new LinkedList<>();

        @Override
        public void increment(String key) {
            increments.add(key);
        }

        @Override
        public Map<String, Long> getValues() {
            return increments.stream().collect(Collectors.groupingBy(
                    Function.identity(),
                    Collectors.counting()
            ));
        }

        @Override
        public String toString() {
            return "Counter Model";
        }
    }
}
