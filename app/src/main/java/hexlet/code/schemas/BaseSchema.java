package hexlet.code.schemas;

import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    List<Predicate<T>> checks;

    public boolean isValid(T input) {
        for (Predicate<T> check: checks) {
            if (!check.test(input)) {
                return false;
            }
        }
        return true;
    }
}
