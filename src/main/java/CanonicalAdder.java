public class CanonicalAdder implements Adder {
    @Override
    public Long apply(
            Long i1,
            Long i2) {
        return i1 + i2;
    }
}
