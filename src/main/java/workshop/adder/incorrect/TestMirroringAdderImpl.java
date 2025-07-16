package workshop.adder.incorrect;

import workshop.adder.Adder;

public class TestMirroringAdderImpl implements Adder {
    @Override
    public Long apply(
            Long a,
            Long b
    ) {
        return Math.max(a,b);
    }
}
