package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {
    private Predicate<String> sizeCheck;
    public StringSchema required() {
        super.setRequired();
        super.addCheck(input -> !(super.isRequired() && input.isEmpty()));
        return this;
    }

    public StringSchema minLength(int length) {
        Predicate<String> newCheck = input -> input.length() >= length;
        super.replaceCheck(sizeCheck, newCheck);
        sizeCheck = newCheck;
        return this;
    }

    public StringSchema contains(String s) {
        super.addCheck(input -> input.contains(s));
        return this;
    }
}
