package workshop.adder;

import org.junit.jupiter.api.Assertions;

import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

public interface AdderContract {

  Adder subject();

  @Provide
  Arbitrary<Long> anyLongWithinRangeOfInteger();

  @Property
  default void isCommutative(
    @ForAll("anyLongWithinRangeOfInteger") Long n1,
    @ForAll("anyLongWithinRangeOfInteger") Long n2) {
    Long sum12 = subject().apply(n1, n2);
    Long sum21 = subject().apply(n2, n1);

    Assertions.assertEquals(
      sum12,
      sum21
    );
  }

  @Property
  default void isAssociative(
    @ForAll("anyLongWithinRangeOfInteger") Long n1,
    @ForAll("anyLongWithinRangeOfInteger") Long n2,
    @ForAll("anyLongWithinRangeOfInteger") Long n3) {

    Long sum12 = subject().apply(n1, n2);
    Long sum23 = subject().apply(n2, n3);
    Long sum12_3 = subject().apply(sum12, n3);
    Long sum1_23 = subject().apply(n1, sum23);

    Assertions.assertEquals(
      sum1_23,
      sum12_3
    );
  }

  @Property
  default void itsIdentityElementIsZero(
    @ForAll("anyLongWithinRangeOfInteger") Long n) {

    Long sumN0 = subject().apply(n, 0L);
    Long sum0N = subject().apply(0L, n);

    Assertions.assertEquals(n, sumN0);
    Assertions.assertEquals(n, sum0N);
  }

  @Property
  default void successorIsGreaterThanPredecessor(
    @ForAll("anyLongWithinRangeOfInteger") Long n) {

    Long sumN1 = subject().apply(n, 1L);

    Assertions.assertTrue(sumN1 > n);
  }
}
