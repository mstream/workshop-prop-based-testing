package workshop.counter;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.ShrinkingMode;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

import java.util.List;

class CounterTest {

    private Counter threadUnsafe() {
        return new ThreadUnsafeCounterImpl();
    }

    private Counter threadSafe() {
        return new ThreadSafeCounterImpl();
    }

    private Counter subject() {
        return threadSafe();
    }

    @Property(shrinking = ShrinkingMode.OFF, tries = 1)
    void check(@ForAll @Size(min = 200, max = 300) List<@IntRange(min = 1, max = 3) Integer> keyIndices) {
        List<String> keys = keyIndices.stream().map(i -> "key-" + i).toList();

        CounterTestRunner testRunner = new CounterTestRunner(
                this::subject,
                CounterModel.Model::new,
                8
        );

        testRunner.run(keys);
    }
}
