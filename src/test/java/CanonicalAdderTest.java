import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;

public class CanonicalAdderTest implements AdderContract {

    @Override
    public Adder subject() {
        return new CanonicalAdder();
    }

    @Override
    public Arbitrary<Long> anyLongWithinRangeOfInteger() {
        return Arbitraries.integers().map(Long::valueOf);
    }
}
