package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema extends BaseSchema<String> {
    private Integer minLength;
    private List<String> contentRestrictions = new ArrayList<>();

    public StringSchema() {
        super.addCheck(input -> !(super.isRequired() && input.isEmpty()));
        super.addCheck(input -> !(minLength != null && input.length() < minLength));
        super.addCheck(input -> {
            if (input == null) {
                return true;
            }
            for (String r: contentRestrictions) {
                if (!input.contains(r)) {
                    return false;
                }
            }
            return true;
        });
    }

    public StringSchema required() {
        super.setRequired();
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String s) {
        contentRestrictions.add(s);
        return this;
    }
}
