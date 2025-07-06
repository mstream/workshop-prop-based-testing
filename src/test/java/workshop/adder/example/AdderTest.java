package workshop.adder.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import workshop.adder.Adder;
import workshop.adder.correct.AdderImpl;
import workshop.adder.incorrect.SubtractionAdderImpl;
import workshop.adder.incorrect.TestMirroringAdderImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdderTest {

    private Adder correct() {
        return new AdderImpl();
    }

    private Adder testMirroring() {
        return new TestMirroringAdderImpl();
    }

    final Adder adder = correct();

    static Stream<Arguments> numberPairs() {
        return Stream.of(
                Arguments.of(
                        1,
                        2,
                        3
                ),
                Arguments.of(
                        2,
                        3,
                        5
                ),
                Arguments.of(
                        -1,
                        3,
                        2
                )
        );
    }

    @ParameterizedTest
    @MethodSource("numberPairs")
    void addsTwoNumbersTogether(
            long i1,
            long i2,
            long expected
    ) {
        assertEquals(
                expected,
                adder.apply(
                        i1,
                        i2
                ),
                () -> i1 + " + " + i2 + " should be equal to" + expected
        );
    }
}
