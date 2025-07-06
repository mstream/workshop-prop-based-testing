package workshop.codec.property;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import workshop.codec.Codec;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface CodecContract<T> {

    Codec<T> subject();

    @Provide
    Arbitrary<T> anyT();

    @Property
    default void shouldSuccessfullyDecodeEncodedValue(@ForAll("anyT") T value) {
        Optional<T> expected = Optional.of(value);
        String encodedValue = subject().encode(value);
        Optional<T> actual = subject().decode(encodedValue);

        assertEquals(
                expected,
                actual
        );
    }
}