package workshop.sorter.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import workshop.sorter.IntegerSorterImpl;
import workshop.sorter.Sorter;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerSorterTest {

    final Sorter<Integer> sorter = new IntegerSorterImpl();

    static Stream<Arguments> numberLists() {
        return Stream.of(
                Arguments.of(
                        List.of(),
                        List.of()
                ),
                Arguments.of(
                        List.of(
                                3,
                                2,
                                1
                        ),
                        List.of(
                                1,
                                2,
                                3
                        )
                ),
                Arguments.of(
                        List.of(
                                1,
                                9,
                                8,
                                3,
                                4,
                                2,
                                7,
                                5
                        ),
                        List.of(
                                1,
                                2,
                                3,
                                4,
                                5,
                                //6,
                                7,
                                8,
                                9
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("numberLists")
    void addsTwoNumbersTogether(
            List<Integer> is,
            List<Integer> expected
    ) {
        assertEquals(
                expected,
                sorter.apply(is),
                () -> "sorted " + is + " should be equal to " + expected
        );
    }
}
