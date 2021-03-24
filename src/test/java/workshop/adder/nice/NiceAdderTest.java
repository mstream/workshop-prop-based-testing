package workshop.adder.nice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("adder")
public class NiceAdderTest {

  private final NiceAdder adder = new NiceAdder();

  @Test
  void addsTwoNumbersTogether() {
    Assertions.assertEquals(3L, adder.apply(1L, 2L));
  }
}
