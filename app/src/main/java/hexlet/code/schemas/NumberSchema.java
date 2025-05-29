package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Number> {
    public NumberSchema required() {
        super.setRequired();
        return this;
    }

    public NumberSchema positive() {
        super.addCheck(input -> input.doubleValue() > 0);
        return this;
    }

    public NumberSchema range(Number low, Number high) {
        if (low == null || high == null) {
            throw new IllegalArgumentException("Range bounds cannot be null");
        }
        if (low.doubleValue() > high.doubleValue()) {
            throw new IllegalArgumentException("Range lower bound cannot be greater than higher bound");
        }
        super.addCheck(input -> input.doubleValue() >= low.doubleValue() && input.doubleValue() <= high.doubleValue());
        return this;
    }

}
