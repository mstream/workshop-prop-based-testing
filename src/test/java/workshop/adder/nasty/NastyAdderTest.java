package workshop.adder.nasty;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Tag;
import workshop.adder.Adder;
import workshop.adder.AdderContract;

@Tag("adder")
public class NastyAdderTest implements AdderContract {

  @Override
  public Adder subject() {
    return new NastyAdder();
  }

  @Override
  public Arbitrary<Long> anyLongWithinRangeOfInteger() {
    return Arbitraries.integers().map(Long::valueOf);
  }
}
