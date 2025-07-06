package workshop.adder.property;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import workshop.adder.Adder;
import workshop.adder.correct.AdderImpl;
import workshop.adder.incorrect.ModuloAdderImpl;
import workshop.adder.incorrect.MultiplicationAdderImpl;
import workshop.adder.incorrect.SubtractionAdderImpl;

public class AdderTest implements AdderContract {

    private Adder correct() {
        return new AdderImpl();
    }

    private Adder modulo() {
        return new ModuloAdderImpl();
    }

    private Adder multiplication() {
        return new MultiplicationAdderImpl();
    }

    private Adder subtraction() {
        return new SubtractionAdderImpl();
    }

    @Override
    public Adder subject() {
        return correct();
    }

    @Override
    public Arbitrary<Long> anyLongWithinRangeOfInteger() {
        return Arbitraries.integers().map(Long::valueOf);
    }

    @Disabled
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
