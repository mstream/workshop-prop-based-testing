package workshop.set.property;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.stateful.ActionSequence;

import java.util.Set;

public interface SetContract<T> {

    Set<T> subject();

    @Provide
    Arbitrary<ActionSequence<Set<Integer>>> anyActionSequence();

    @Property
    default void check(@ForAll("anyActionSequence") ActionSequence<Set<T>> actions) {
        actions.run(subject());
    }
}
