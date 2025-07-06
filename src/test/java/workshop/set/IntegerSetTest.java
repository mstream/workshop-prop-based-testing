package workshop.set;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Tuple;
import net.jqwik.api.stateful.Action;
import net.jqwik.api.stateful.ActionSequence;
import workshop.set.actions.Add;
import workshop.set.actions.Clear;
import workshop.set.actions.Remove;
import workshop.set.correct.SetImpl;
import workshop.set.incorrect.NotClearingSetImpl;
import workshop.set.incorrect.UndersizedSetImpl;

import java.util.Set;

public class IntegerSetTest implements SetContract<Integer> {

    private Set<Integer> correct() {
        return new SetImpl<>();
    }

    private Set<Integer> notClearing() {
        return new NotClearingSetImpl<>();
    }

    private Set<Integer> undersized() {
        return new UndersizedSetImpl<>();
    }

    @Override
    public Set<Integer> subject() {
        return correct();
    }

    @Override
    public Arbitrary<ActionSequence<Set<Integer>>> anyActionSequence() {
        Arbitrary<Action<Set<Integer>>> addAction = Arbitraries.integers().between(
                0,
                9
        ).map(Add::new);

        Arbitrary<Action<Set<Integer>>> removeAction = Arbitraries.integers().between(
                0,
                9
        ).map(Remove::new);

        Arbitrary<Action<Set<Integer>>> clearAction = Arbitraries.of(new Clear<>());

        Arbitrary<Action<Set<Integer>>> action = Arbitraries.frequencyOf(
                Tuple.of(
                        100,
                        addAction
                ),
                Tuple.of(
                        10,
                        removeAction
                ),
                Tuple.of(
                        1,
                        clearAction
                )
        );

        return Arbitraries.sequences(action);
    }
}

