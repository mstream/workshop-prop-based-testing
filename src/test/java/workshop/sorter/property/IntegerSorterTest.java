package workshop.sorter.property;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Example;
import org.junit.jupiter.api.Assertions;
import workshop.sorter.IntegerSorterImpl;
import workshop.sorter.Sorter;

import java.util.List;

public class IntegerSorterTest implements SorterContract<Integer> {

    @Override
    public Sorter<Integer> subject() {
        return new IntegerSorterImpl();
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
        List<Integer> listToSort = List.of(
                3,
                1,
                2
        );
        List<Integer> sortedList = subject().apply(listToSort);
        Assertions.assertEquals(
                List.of(
                        1,
                        2,
                        3
                ),
                sortedList
        );
    }
}
