package hexlet.code.schemas;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    @Getter
    @Setter
    private List<Predicate<T>> checks;

    public final boolean isValid(T input) {
        for (Predicate<T> check: checks) {
            if (!check.test(input)) {
                return false;
            }
        }
        return true;
    }
}
