package workshop.set.actions;

import net.jqwik.api.stateful.Action;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Add<T> implements Action<Set<T>> {

    private final T elementToAdd;

    public Add(T elementToAdd) {
        this.elementToAdd = elementToAdd;
    }

    @Override
    public Set<T> run(Set<T> subject) {
        Set<T> subjectStateSnapshot = Set.copyOf(subject);
        boolean wasElementInSet = subject.contains(elementToAdd);

        subject.add(elementToAdd);

        if (wasElementInSet) {
            assertEquals(
                    subjectStateSnapshot,
                    subject,
                    () -> "set should not change"
            );
        } else {
            assertEquals(
                    subjectStateSnapshot.size() + 1,
                    subject.size(),
                    () -> "set size should increase by 1"
            );

            assertTrue(
                    subject.contains(elementToAdd),
                    () -> "set should contain " + elementToAdd
            );
        }

        return subject;
    }

    @Override
    public String toString() {
        return "add " + elementToAdd;
    }
}
