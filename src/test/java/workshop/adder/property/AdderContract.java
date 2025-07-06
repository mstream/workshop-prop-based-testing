package workshop.adder.property;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import workshop.adder.Adder;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface AdderContract {

    Adder subject();

    @Provide
    Arbitrary<Long> anyLongWithinRangeOfInteger();

    //@Disabled
    @Property
    default void isDistributive(
            @ForAll("anyLongWithinRangeOfInteger") Long a,
            @ForAll("anyLongWithinRangeOfInteger") Long b,
            @ForAll("anyLongWithinRangeOfInteger") Long c
    ) {
        Long b_plus_c = subject().apply(
                b,
                c
        );

        Long a_times_bc = a * b_plus_c;
        Long a_times_b = a * b;
        Long a_times_c = a * c;

        Long a_times_b__plus__a_times_c = subject().apply(
                a_times_b,
                a_times_c
        );

        assertEquals(
                a_times_bc,
                a_times_b__plus__a_times_c,
                () -> format(
                        "%d * ( %d + %d ) = %d * %d + %d * %d%n%d * %d = %d + %d%n%d = %d%n",
                        a,
                        b,
                        c,
                        a,
                        b,
                        a,
                        c,
                        a,
                        b_plus_c,
                        a_times_b,
                        a_times_c,
                        a_times_bc,
                        a_times_b__plus__a_times_c
                )
        );

    }

    //@Disabled
    @Property
    default void isAssociative(
            @ForAll("anyLongWithinRangeOfInteger") Long a,
            @ForAll("anyLongWithinRangeOfInteger") Long b,
            @ForAll("anyLongWithinRangeOfInteger") Long c
    ) {
        Long a_plus_b = subject().apply(
                a,
                b
        );
        Long b_plus_c = subject().apply(
                b,
                c
        );
        Long a_plus_b__plus__c = subject().apply(
                a_plus_b,
                c
        );
        Long a__plus__b_plus_c = subject().apply(
                a,
                b_plus_c
        );

        assertEquals(
                a_plus_b__plus__c,
                a__plus__b_plus_c,
                () -> format(
                        "%d + ( %d + %d ) = ( %d + %d ) + %d%n%d + %d = %d + %d%n%d = %d%n",
                        a,
                        b,
                        c,
                        a,
                        b,
                        c,
                        a,
                        b_plus_c,
                        a_plus_b,
                        c,
                        a__plus__b_plus_c,
                        a_plus_b__plus__c
                )
        );
    }

    //@Disabled
    @Property
    default void isCommutative(
            @ForAll("anyLongWithinRangeOfInteger") Long a,
            @ForAll("anyLongWithinRangeOfInteger") Long b
    ) {
        Long ab = subject().apply(
                a,
                b
        );
        Long ba = subject().apply(
                b,
                a
        );

        assertEquals(
                ab,
                ba,
                () -> format(
                        "%d + %d = %d + %d\n%d = %d",
                        a,
                        b,
                        b,
                        a,
                        ab,
                        ba
                )
        );
    }

    //@Disabled
    @Property
    default void itsIdentityElementIsZero(
            @ForAll("anyLongWithinRangeOfInteger") Long a
    ) {
        Long a0 = subject().apply(
                a,
                0L
        );

        assertEquals(
                a,
                a0,
                () -> format(
                        "%d + 0 = %d\n%d = %d",
                        a,
                        a,
                        a0,
                        a
                )
        );
    }

    //@Disabled
    @Property
    default void successorIsGreaterThanPredecessor(
            @ForAll("anyLongWithinRangeOfInteger") Long a
    ) {
        Long a1 = subject().apply(
                a,
                1L
        );

        assertTrue(
                a1 > a,
                () -> format(
                        "%d + 1 > %d\n%d > %d",
                        a,
                        a,
                        a1,
                        a
                )
        );
    }
}
