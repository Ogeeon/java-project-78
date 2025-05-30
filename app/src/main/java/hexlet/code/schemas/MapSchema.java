package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Predicate<Map<?, ?>> sizeCheck;
    public MapSchema required() {
        super.setRequired();
        return this;
    }

    public MapSchema sizeof(int size) {
        Predicate<Map<?, ?>> newCheck = input -> input.size() >= size;
        super.replaceCheck(sizeCheck, newCheck);
        sizeCheck = newCheck;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> validationSchemas) {
        super.addCheck(input -> {
            Set<String> keySet = validationSchemas.keySet();
            for (String key: keySet) {
                if (!input.containsKey(key)) {
                    return false;
                }
                if (!validationSchemas.get(key).isValid(input.get(key).toString())) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
