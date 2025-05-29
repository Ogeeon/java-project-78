package hexlet.code.schemas;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    @Getter
    private List<Predicate<T>> checks;
    @Getter
    private boolean isRequired = false;

    public final void setRequired() {
        this.isRequired = true;
    }

    public final void addCheck(Predicate<T> check) {
        if (checks == null) {
            checks = new ArrayList<>();
        }
        checks.add(check);
    }

    public final boolean isValid(T input) {
        if (input == null) {
            return !isRequired;
        }
        if (checks == null) {
            return true;
        }
        for (Predicate<T> check: checks) {
            if (!check.test(input)) {
                return false;
            }
        }
        return true;
    }
}
