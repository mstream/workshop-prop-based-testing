package workshop.sorter;

import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Sorts in non-descending order.
 * Returns a new instance of list which can be modified.
 */

@FunctionalInterface
public interface Sorter<T extends Comparable<? super T>>
  extends UnaryOperator<List<T>> {
}
