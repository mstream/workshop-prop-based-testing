package workshop.codec;

import java.util.Optional;

/**
 * Encodes and decodes values.
 */

public interface Codec<T> {
    Optional<T> decode(String s);

    String encode(T t);
}
