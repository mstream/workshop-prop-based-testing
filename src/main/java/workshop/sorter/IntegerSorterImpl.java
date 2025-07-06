package workshop.sorter;

import java.util.List;
import java.util.stream.Collectors;

public class IntegerSorterImpl implements Sorter<Integer> {

    @Override
    public List<Integer> apply(List<Integer> integers) {
        return integers.stream().sorted().collect(Collectors.toList());
    }
}
