package workshop.codec.property;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import workshop.codec.Codec;
import workshop.codec.IntegerCodecImpl;

public class IntegerCodecTest implements CodecContract<Integer> {

    @Override
    public Codec<Integer> subject() {
        return new IntegerCodecImpl();
    }

    @Override
    public Arbitrary<Integer> anyT() {
        return Arbitraries.integers();
    }

}
