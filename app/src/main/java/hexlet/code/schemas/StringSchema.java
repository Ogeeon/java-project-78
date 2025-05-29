package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        super.setRequired();
        super.addCheck(input -> !(super.isRequired() && input.isEmpty()));
        return this;
    }

    public StringSchema minLength(int length) {
        super.addCheck(input -> input.length() >= length);
        return this;
    }

    public StringSchema contains(String s) {
        super.addCheck(input -> input.contains(s));
        return this;
    }
}
