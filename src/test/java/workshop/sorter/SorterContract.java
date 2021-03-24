package workshop.sorter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

public interface SorterContract<T extends Comparable<? super T>> {

  Sorter<T> subject();

  @Provide
  Arbitrary<T> anyT();

  @Provide
  Arbitrary<List<T>> anyListOfT();


  @Property
  default void returnsNewMutableInstanceOfList(
    @ForAll("anyListOfT") List<T> listToSort,
    @ForAll("anyT") T elementToAdd) {
    List<T> snapshotOfListToSort = List.copyOf(listToSort);
    List<T> sortedList = subject().apply(listToSort);
    List<T> snapshotOfSortedList = List.copyOf(sortedList);
    sortedList.add(elementToAdd);

    Assertions.assertEquals(
      snapshotOfListToSort,
      listToSort);
    Assertions.assertEquals(
      Stream.concat(
        snapshotOfSortedList.stream(),
        Stream.of(elementToAdd))
      .collect(Collectors.toList()),
      sortedList);
  }

  @Property
  default void sortsInNonDescendingOrder(
    @ForAll("anyListOfT") List<T> listToSort) {
    List<T> sortedList = subject().apply(listToSort);

    Assertions.assertTrue(
      IntStream
      .range(0, sortedList.size() - 1)
      .allMatch(i -> sortedList
                .get(i)
                .compareTo(sortedList.get(i + 1)) <= 0));
  }

  @Property
  default void preservesElements(
    @ForAll("anyListOfT") List<T> listToSort) {
    List<T> sortedList = subject().apply(listToSort);

    Assertions.assertEquals(
      frequencies(listToSort),
      frequencies(sortedList));
  }

  private static <T> Map<T, Long> frequencies(
    Collection<T> elements) {
    return elements
           .stream()
           .collect(Collectors.groupingBy(
                      Function.identity(),
                      Collectors.counting()));
  }
}
