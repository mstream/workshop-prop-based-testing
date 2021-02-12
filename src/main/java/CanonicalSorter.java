import java.util.List;
import java.util.stream.Collectors;

class CanonicalSorter<T extends Comparable<? super T>> implements Sorter<T> {

    @Override
    public List<T> apply(List<T> listToSort) {
        return listToSort
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
