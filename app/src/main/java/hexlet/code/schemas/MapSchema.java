package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public final class MapSchema extends BaseSchema<Map> {
    private int minSize = 0;
    private Map<String, BaseSchema<String>> rules;

    public MapSchema() {
        super.addCheck(input -> !(minSize > 0 && input.size() < minSize));
        super.addCheck(input -> {
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
        });
    }

    public MapSchema required() {
        super.setRequired();
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
}
