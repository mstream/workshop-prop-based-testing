package workshop.sorter.nasty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import workshop.sorter.Sorter;

class NastyIntegerSorter implements Sorter<Integer> {

  @Override
  public List<Integer> apply(List<Integer> integers) {
    List<Integer> l = new ArrayList<>(integers);
    Collections.sort(l);
    return l;
  }
}
