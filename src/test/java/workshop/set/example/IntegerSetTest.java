package workshop.set.example;

import org.junit.jupiter.api.Test;
import workshop.set.correct.SetImpl;
import workshop.set.incorrect.LimitedClearingSetImpl;
import workshop.set.incorrect.UndersizedSetImpl;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegerSetTest {

    private Set<Integer> correct() {
        return new SetImpl<>();
    }

    private Set<Integer> limitedClearing() {
        return new LimitedClearingSetImpl<>();
    }

    private Set<Integer> undersized() {
        return new UndersizedSetImpl<>();
    }

    final Set<Integer> subject = correct();

    @Test
    public void testClearing() {
        subject.add(1);
        subject.add(2);
        subject.add(3);
        subject.clear();

        assertTrue(
                subject.isEmpty(),
                () -> "set should be empty"
        );
    }

    @Test
    public void testAdding() {
        subject.add(1);
        subject.add(2);
        subject.add(3);

        assertEquals(
                3,
                subject.size(),
                () -> "set size should increase by 1"
        );
    }
}
