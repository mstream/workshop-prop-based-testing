package workshop.adder.incorrect;

import workshop.adder.Adder;

public class MultiplicationAdderImpl implements Adder {
    @Override
    public Long apply(
            Long a,
            Long b
    ) {
        return a * b;
    }
}
