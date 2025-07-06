package workshop.adder.correct;

import workshop.adder.Adder;

public class AdderImpl implements Adder {
    @Override
    public Long apply(
            Long a,
            Long b
    ) {
        return a + b;
    }
}
