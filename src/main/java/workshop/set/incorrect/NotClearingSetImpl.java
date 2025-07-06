package workshop.set.incorrect;

import java.util.HashSet;

public class NotClearingSetImpl<T> extends HashSet<T> {
    @Override
    public void clear() {
        // do nothing
    }
}
