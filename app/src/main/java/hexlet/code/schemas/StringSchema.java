package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {
    private Predicate<String> sizeCheck;
    public StringSchema required() {
        super.setRequired();
        super.addCheck("emptiness", input -> !input.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        super.addCheck("minLength", input -> input.length() >= length);
        return this;
    }

    public StringSchema contains(String s) {
        super.addCheck("contains", input -> input.contains(s));
        return this;
    }
}
