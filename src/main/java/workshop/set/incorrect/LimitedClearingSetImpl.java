package workshop.set.incorrect;

import java.util.HashSet;
import java.util.List;

public class LimitedClearingSetImpl<T> extends HashSet<T> {
    @Override
    public void clear() {
        List<T> elementsToRemove = stream().limit(5).toList();
        elementsToRemove.forEach(this::remove);
    }
}
