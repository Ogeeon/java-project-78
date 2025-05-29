package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public final class MapSchema extends BaseSchema<Map> {
    public MapSchema required() {
        super.setRequired();
        return this;
    }

    public MapSchema sizeof(int size) {
        super.addCheck(input -> input.size() >= size);
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
