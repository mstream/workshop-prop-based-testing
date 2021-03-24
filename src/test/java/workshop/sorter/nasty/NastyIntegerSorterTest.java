package workshop.sorter.nasty;

import java.util.List;

import org.junit.jupiter.api.Assertions;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Example;
import net.jqwik.api.Tag;
import workshop.sorter.Sorter;
import workshop.sorter.SorterContract;

@Tag("sorter")
public class NastyIntegerSorterTest
  implements SorterContract<Integer> {

  @Override
  public Sorter<Integer> subject() {
    return new NastyIntegerSorter();
  }

  @Override
  public Arbitrary<Integer> anyT() {
    return Arbitraries.integers();
  }

  @Override
  public Arbitrary<List<Integer>> anyListOfT() {
    return Arbitraries.integers().list();
  }

  @Example
  public void sortsUnsortedThreeElementsList() {
    List<Integer> listToSort = List.of(3, 1, 2);
    List<Integer> sortedList = subject().apply(listToSort);
    Assertions.assertEquals(
      List.of(1, 2, 3),
      sortedList);
  }
}
