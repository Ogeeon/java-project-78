package hexlet.code.schemas;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    @Getter
    private Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    @Getter
    private boolean isRequired = false;

    public final void setRequired() {
        this.isRequired = true;
    }

    public final void addCheck(String kind, Predicate<T> check) {
        checks.put(kind, check);
    }

    public final boolean isValid(T input) {
        if (input == null) {
            return !isRequired;
        }
        var kinds = checks.keySet();
        for (String kind: kinds) {
            Predicate<T> check = checks.get(kind);
            if (!check.test(input)) {
                return false;
            }
        }
        return true;
    }
}
