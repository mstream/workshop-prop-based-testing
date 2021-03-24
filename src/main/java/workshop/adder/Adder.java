package workshop.adder;

import java.util.function.BinaryOperator;

/**
 * Adds two non-null longs to each other.
 */

@FunctionalInterface
public interface Adder extends BinaryOperator<Long> {
}
