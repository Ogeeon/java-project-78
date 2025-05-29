package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Number> {
    private boolean isPositive = false;
    private Number min;
    private Number max;

    public NumberSchema() {
        super.addCheck(input -> !(isPositive && input.doubleValue() <= 0));
        super.addCheck(input -> (min == null
                || (input.doubleValue() >= min.doubleValue() && input.doubleValue() <= max.doubleValue())));
    }

    public NumberSchema required() {
        super.setRequired();
        return this;
    }

    public NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public NumberSchema range(Number low, Number high) {
        if (low == null || high == null) {
            throw new IllegalArgumentException("Range bounds cannot be null");
        }
        if (low.doubleValue() > high.doubleValue()) {
            throw new IllegalArgumentException("Range lower bound cannot be greater than higher bound");
        }
        this.min = low;
        this.max = high;
        return this;
    }

}
