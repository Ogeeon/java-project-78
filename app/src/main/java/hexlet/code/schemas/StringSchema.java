package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema<String> {
    private boolean isRequired = false;
    private Integer minLength;
    private List<String> contentRestrictions = new ArrayList<>();

    public StringSchema required() {
        this.isRequired = true;
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

    public boolean isValid(String input) {
        if (isRequired && (input == null || input.isEmpty())) {
            return false;
        }
        if (minLength != null && input.length() < minLength) {
            return false;
        }
        for (String r: contentRestrictions) {
            if (!input.contains(r)) {
                return false;
            }
        }
        return true;
    }
}
