package workshop.counter;

import workshop.counter.actions.IncrementFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CounterTestRunner {

    private final Supplier<Counter> instanceUnderTestSupplier;
    private final Supplier<Counter> modelSupplier;
    private final int threadNum;

    CounterTestRunner(
            Supplier<Counter> instanceUnderTestSupplier,
            Supplier<Counter> modelsSupplier,
            int threadNum
    ) {
        this.instanceUnderTestSupplier = instanceUnderTestSupplier;
        this.modelSupplier = modelsSupplier;
        this.threadNum = threadNum;
    }

    public void run(List<String> keys) {
        Counter model = modelSupplier.get();
        Counter instanceUnderTest = instanceUnderTestSupplier.get();
        IncrementFactory incrementFactory = new IncrementFactory(
                threadNum,
                keys
        );

        try (ExecutorService executorService = Executors.newFixedThreadPool(threadNum)) {
            System.out.println("Initializing the test model...");
            incrementFactory.incrementsUsing(model).forEach(Runnable::run);
            System.out.println("Testing instance under test...");
            incrementFactory.incrementsUsing(instanceUnderTest).forEach(executorService::execute);
            executorService.shutdown();

            boolean terminated = executorService.awaitTermination(
                    10,
                    TimeUnit.SECONDS
            );

            if (!terminated) {
                throw new RuntimeException("Timed out waiting for termination");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals(
                model.getValues(),
                instanceUnderTest.getValues()
        );
    }
}
