package workshop.sorter.nice;

import java.util.List;
import java.util.stream.Collectors;

import workshop.sorter.Sorter;

class NiceIntegerSorter implements Sorter<Integer> {

  @Override
  public List<Integer> apply(List<Integer> integers) {
    return integers
           .stream()
           .sorted()
           .collect(Collectors.toList());
  }
}
