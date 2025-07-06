package workshop.set.incorrect;

import java.util.HashSet;

public class UndersizedSetImpl<T> extends HashSet<T> {
    @Override
    public boolean add(T value) {
        return size() < 10 && super.add(value);
    }
}
