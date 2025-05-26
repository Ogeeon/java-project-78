package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public final class MapSchema extends BaseSchema<Map> {
    private boolean isRequired = false;
    private int minSize = 0;
    private Map<String, BaseSchema<String>> rules;

    public MapSchema required() {
        this.isRequired = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        this.minSize = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> validationSchemas) {
        this.rules = validationSchemas;
        return this;
    }

    @Override
    public boolean isValid(Map input) {
        if (input == null) {
            return !isRequired;
        }
        if (minSize > 0 && input.size() < minSize) {
            return false;
        }
        if (rules == null) {
            return true;
        }
        Set<String> keySet = rules.keySet();
        for (String key: keySet) {
            if (!input.containsKey(key)) {
                return false;
            }
            if (!rules.get(key).isValid(input.get(key).toString())) {
                return false;
            }
        }
        return true;
    }
}
