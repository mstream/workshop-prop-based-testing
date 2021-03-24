package workshop.metrics;

import java.util.Map;

public interface MetricRegistry {
  void increment(String name);
  Map<String, Long> getValues();
}
