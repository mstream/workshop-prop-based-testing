package workshop.set.actions;

import net.jqwik.api.stateful.Action;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Remove<T> implements Action<Set<T>> {

    private final T elementToRemove;

    public Remove(T elementToRemove) {
        this.elementToRemove = elementToRemove;
    }

    @Override
    public Set<T> run(Set<T> subject) {
        Set<T> subjectStateSnapshot = Set.copyOf(subject);
        boolean wasElementInSet = subject.contains(elementToRemove);

        subject.remove(elementToRemove);

        if (wasElementInSet) {
            assertEquals(
                    subjectStateSnapshot.size() - 1,
                    subject.size(),
                    () -> "set size should decrease by 1"
            );
            assertFalse(
                    subject.contains(elementToRemove),
                    () -> "set should not contain element " + elementToRemove
            );

        } else {
            assertEquals(
                    subjectStateSnapshot,
                    subject,
                    () -> "set should not change"
            );
        }

        return subject;
    }

    @Override
    public String toString() {
        return "remove " + elementToRemove;
    }
}
