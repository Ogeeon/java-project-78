package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {
    private boolean isRequired = false;
    private Integer minLength;
    private List<String> contentRestrictions = new ArrayList<>();

    public StringSchema() {
        var checks = new ArrayList<Predicate<String>>();
        checks.add(input -> !((input == null || input.isEmpty()) && isRequired));
        checks.add(input -> input == null || !(minLength != null && input.length() < minLength));
        checks.add(input -> {
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
        super.setChecks(checks);
    }

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
}
