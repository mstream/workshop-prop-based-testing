package workshop.codec.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import workshop.codec.Codec;
import workshop.codec.IntegerCodecImpl;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerCodecTest {

    final Codec<Integer> codec = new IntegerCodecImpl();

    static Stream<Arguments> integers() {
        return Stream.of(
                Arguments.of(
                        1,
                        "1"
                ),
                Arguments.of(
                        123,
                        "123"
                ),
                Arguments.of(
                        -1,
                        "-1"
                )
        );
    }

    static Stream<Arguments> strings() {
        return Stream.of(
                Arguments.of(
                        "1",
                        Optional.of(1)
                ),
                Arguments.of(
                        "123",
                        Optional.of(123)
                ),
                Arguments.of(
                        "-1",
                        Optional.of(-1)
                ),
                Arguments.of(
                        "1.5",
                        Optional.empty()
                ),
                Arguments.of(
                        "abc",
                        Optional.empty()
                )

        );
    }

    @ParameterizedTest
    @MethodSource("integers")
    void encodesIntegers(
            Integer i,
            String expected
    ) {
        assertEquals(
                expected,
                codec.encode(i)
        );
    }

    @ParameterizedTest
    @MethodSource("strings")
    void decodesStrings(
            String s,
            Optional<Integer> expected
    ) {
        assertEquals(
                expected,
                codec.decode(s)
        );
    }
}
