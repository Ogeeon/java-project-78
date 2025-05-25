package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Number> {
    private boolean isRequired = false;
    private boolean isPositive = false;
    private Number min;
    private Number max;

    public NumberSchema required() {
        this.isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(Number low, Number high) {
        if (low.doubleValue() > high.doubleValue()) {
            throw new IllegalArgumentException("Range lower bound cannot be greater than higher bound");
        }
        this.min = low;
        this.max = high;
        return this;
    }

    @Override
    public boolean isValid(Number input) {
        if (isRequired && input == null) {
            return false;
        }
        if (isPositive && input.doubleValue() <= 0) {
            return false;
        }
        if (min != null) {
            return !(input.doubleValue() < min.doubleValue())
                    && !(input.doubleValue() > max.doubleValue());
        }
        return true;
    }
}
