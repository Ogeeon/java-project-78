package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Number> {
    private boolean isRequired = false;
    private boolean isPositive = false;
    private Number min;
    private Number max;

    public NumberSchema() {
        var checks = new ArrayList<Predicate<Number>>();
        checks.add(input -> !(input == null && isRequired));
        checks.add(input -> input == null || !(isPositive && input.doubleValue() <= 0));
        checks.add(input -> input == null || (min == null
                || (input.doubleValue() >= min.doubleValue() && input.doubleValue() <= max.doubleValue())));
        super.setChecks(checks);
    }

    public NumberSchema required() {
        this.isRequired = true;
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
