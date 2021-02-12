import net.jqwik.api.*;
import net.jqwik.api.stateful.Action;
import net.jqwik.api.stateful.ActionSequence;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

public class IntegerSetTest {

    @Property
    void check(@ForAll("anyActionSequence") ActionSequence<Set<Integer>> actions) {
        actions.run(new HashSet<>());
    }

    @Provide
    Arbitrary<ActionSequence<Set<Integer>>> anyActionSequence() {
        Arbitrary<Action<Set<Integer>>> addAction =
                Arbitraries
                        .integers()
                        .between(0, 9)
                        .map(AddAction::new);

        Arbitrary<Action<Set<Integer>>> removeAction =
        Arbitraries
                .integers()
                .between(0, 9)
                .map(RemoveAction::new);

        Arbitrary<Action<Set<Integer>>> clearAction =
                Arbitraries.constant(new ClearAction());

        Arbitrary<Action<Set<Integer>>> action =
                Arbitraries.frequencyOf(
                        Tuple.of(100, addAction),
                        Tuple.of(10, removeAction),
                        Tuple.of(1, clearAction)
                );

        return Arbitraries.sequences(action);
    }
}

class AddAction implements Action<Set<Integer>> {

    private final Integer elementToAdd;

    AddAction(Integer elementToAdd) {
        this.elementToAdd = elementToAdd;
    }

    @Override
    public Set<Integer> run(Set<Integer> subject) {
        Set<Integer> subjectStateSnapshot = Set.copyOf(subject);
        int sizeBefore = subject.size();
        boolean wasElementInSet = subject.contains(elementToAdd);

        subject.add(elementToAdd);

        if (wasElementInSet) {
            Assertions.assertEquals(
                    subjectStateSnapshot,
                    subject);
        } else {
            Assertions.assertEquals(
                    sizeBefore + 1,
                    subject.size());

            Assertions.assertTrue(subject.contains(elementToAdd));
        }

        return subject;
    }

    @Override
    public String toString() {
        return "add " + elementToAdd;
    }
}

class RemoveAction implements Action<Set<Integer>> {

    private final Integer elementToRemove;

    RemoveAction(Integer elementToRemove) {
        this.elementToRemove = elementToRemove;
    }

    @Override
    public Set<Integer> run(Set<Integer> subject) {
        Set<Integer> subjectStateSnapshot = Set.copyOf(subject);
        int sizeBefore = subject.size();
        boolean wasElementInSet = subject.contains(elementToRemove);

        subject.remove(elementToRemove);

        if (wasElementInSet) {
            Assertions.assertEquals(
                    sizeBefore -1,
                    subject.size());

            Assertions.assertFalse(subject.contains(elementToRemove));

        } else {
            Assertions.assertEquals(
                    subjectStateSnapshot,
                    subject);
        }

        return subject;
    }

    @Override
    public String toString() {
        return "remove " + elementToRemove;
    }
}

class ClearAction implements Action<Set<Integer>> {

    @Override
    public Set<Integer> run(Set<Integer> subject) {
        subject.clear();

        Assertions.assertTrue(subject.isEmpty());

        return subject;
    }
}