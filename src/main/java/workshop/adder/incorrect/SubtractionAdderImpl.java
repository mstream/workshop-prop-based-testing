package workshop.adder.incorrect;

import workshop.adder.Adder;

public class SubtractionAdderImpl implements Adder {
    @Override
    public Long apply(
            Long a,
            Long b
    ) {
        return a - b;
    }
}
