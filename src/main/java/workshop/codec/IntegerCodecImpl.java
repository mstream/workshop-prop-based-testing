package workshop.codec;

import java.util.Optional;

public class IntegerCodecImpl implements Codec<Integer> {

    @Override
    public Optional<Integer> decode(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public String encode(Integer i) {
        return Integer.toString(i);
    }
}
