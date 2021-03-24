package workshop.metrics;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;

@Tag("metrics")
class ConcurrentMetricRegistryTest {

  private static final int SAMPLE_NUM = 100;

  private final MetricRegistryTestRunner singleThreadedRunner =
    new MetricRegistryTestRunner(
    () -> new ConcurrentMetricRegistry(),
    () -> new Model(),
    1);


  private final MetricRegistryTestRunner multiThreadedRunner =
    new MetricRegistryTestRunner(
    () -> new ConcurrentMetricRegistry(),
    () -> new Model(),
    16);

  private final Arbitrary<String> metricNameArb =
    Arbitraries.of("metric1", "metric2", "metric3");


  @Test
  void singleThreaded() {
    singleThreadedRunner.run(
      metricNameArb.sampleStream().limit(SAMPLE_NUM).collect(Collectors.toList()));
  }

  @Test
  void multiThreaded() {
    multiThreadedRunner.run(
      metricNameArb.sampleStream().limit(SAMPLE_NUM).collect(Collectors.toList()));
  }

  private static class Model implements MetricRegistry {
    private final List<String> increments = new LinkedList<>();

    @Override
    public void increment(String name) {
      increments.add(name);
    }

    @Override
    public Map<String, Long> getValues() {
      return increments
             .stream()
             .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
    }

    @Override
    public String toString() {
      return "Model";
    }
  }
}
