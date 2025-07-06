package workshop.counter.actions;

import workshop.counter.Counter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public record IncrementFactory(int threadNum, List<String> keys) {

    public List<Runnable> incrementsUsing(Counter counter) {
        int maxIndex = keys.size() - 1;

        IntStream indicesStream = IntStream.rangeClosed(
                0,
                maxIndex
        );

        Function<Integer, Runnable> increment = index -> () -> {
            String key = keys.get(index);

            try {
                System.out.printf(
                        "%s thread(%s) of total(<%d>): [%d/%d] incrementing '%s'\n",
                        counter,
                        Thread.currentThread().getName(),
                        threadNum,
                        index,
                        maxIndex,
                        key
                );

                counter.increment(key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        return indicesStream.mapToObj(increment::apply).toList();
    }
}