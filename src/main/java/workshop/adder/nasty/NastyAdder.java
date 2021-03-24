package workshop.adder.nasty;

import workshop.adder.Adder;

public class NastyAdder implements Adder {
  @Override
  public Long apply(
    Long n1,
    Long n2
  ) {
    return n1 + n2;
  }
}
