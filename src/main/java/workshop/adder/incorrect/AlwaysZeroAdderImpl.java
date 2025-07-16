package workshop.adder.incorrect;

import workshop.adder.Adder;

public class AlwaysZeroAdderImpl implements Adder {
    @Override
    public Long apply(
            Long a,
            Long b
    ) {
        return 0L;
    }
}
