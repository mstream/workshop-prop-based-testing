package workshop.set.incorrect;

import java.util.HashSet;

public class UndersizedSetImpl<T> extends HashSet<T> {
    @Override
    public boolean add(T value) {
        return size() < 5 && super.add(value);
    }
}
