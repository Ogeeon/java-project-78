package hexlet.code.schemas;

public class BaseSchema<T> {
    public boolean isValid(T input) {
        return true;
    }
}
