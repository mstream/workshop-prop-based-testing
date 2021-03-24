package workshop.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MetricRegistryTestRunner {

  private final Supplier<MetricRegistry> instanceUnderTestSupplier;
  private final Supplier<MetricRegistry> modelSupplier;
  private final int threadNum;

  public MetricRegistryTestRunner(
    Supplier<MetricRegistry> instanceUnderTestSupplier,
    Supplier<MetricRegistry> modelsSupplier,
    int threadNum
  ) {
    this.instanceUnderTestSupplier = instanceUnderTestSupplier;
    this.modelSupplier = modelsSupplier;
    this.threadNum = threadNum;
  }

  public void run (List<String> increments) {
    MetricRegistry model = modelSupplier.get();
    MetricRegistry instanceUnderTest = instanceUnderTestSupplier.get();

    try {
      ExecutorService executorService = Executors.newFixedThreadPool(threadNum);

      Set<IncrementAction> modelActions =
        createIncrementActions(model, increments);

      Set<IncrementAction> instanceUnderTestActions =
        createIncrementActions(instanceUnderTest, increments);

      modelActions.forEach(Runnable::run);

      instanceUnderTestActions
      .stream()
      .parallel()
      .forEach(executorService::execute);

      executorService.shutdown();
      executorService.awaitTermination(10, TimeUnit.SECONDS);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    assertEquals(model.getValues(), instanceUnderTest.getValues());
  }

  private static Set<IncrementAction> createIncrementActions(
    MetricRegistry metricRegistry,
    List<String> increments
  ) {
    return increments
           .stream()
           .map(name -> new IncrementAction(metricRegistry, name))
           .collect(Collectors.toUnmodifiableSet());
  }

  private static class IncrementAction implements Runnable {

    private final MetricRegistry metricRegistry;
    private final String name;

    private IncrementAction(MetricRegistry metricRegistry, String name) {
      this.metricRegistry = metricRegistry;
      this.name = name;
    }

    public void run() {
      try {
        /*
        System.out.printf(
          "%s (%s): incrementing metric '%s'\n",
          metricRegistry,
          Thread.currentThread().getName(),
          name);
        */
        Thread.sleep(50);
        metricRegistry.increment(name);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
