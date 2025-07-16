package workshop.set.property.actions;

import net.jqwik.api.stateful.Action;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Clear<T> implements Action<Set<T>> {

    @Override
    public Set<T> run(Set<T> subject) {
        subject.clear();

        assertTrue(
                subject.isEmpty(),
                () -> "set should be empty"
        );

        return subject;
    }

    @Override
    public String toString() {
        return "clear";
    }
}
