package workshop.adder.property;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Example;
import org.junit.jupiter.api.Assertions;
import workshop.adder.Adder;
import workshop.adder.correct.AdderImpl;
import workshop.adder.incorrect.AlwaysZeroAdderImpl;
import workshop.adder.incorrect.MultiplicationAdderImpl;
import workshop.adder.incorrect.SubtractionAdderImpl;
import workshop.adder.incorrect.TestMirroringAdderImpl;

public class AdderTest implements AdderContract {

    private Adder correct() {
        return new AdderImpl();
    }

    private Adder alwaysZero() {
        return new AlwaysZeroAdderImpl();
    }

    private Adder multiplication() {
        return new MultiplicationAdderImpl();
    }

    private Adder subtraction() {
        return new SubtractionAdderImpl();
    }

    private Adder testMirroring() {
        return new TestMirroringAdderImpl();
    }

    @Override
    public Adder subject() {
        return correct();
    }

    @Override
    public Arbitrary<Long> anyLongWithinRangeOfInteger() {
        return Arbitraries.integers().map(Long::valueOf);
    }

    @Example
    public void oneAndTwo() {
        Assertions.assertEquals(
                3L,
                subject().apply(
                        1L,
                        2L
                )
        );
    }
}
