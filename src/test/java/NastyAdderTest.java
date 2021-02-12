import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;

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
