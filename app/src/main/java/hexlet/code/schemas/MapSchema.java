package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map> {
    private boolean isRequired = false;
    private int minSize = 0;
    private Map<String, BaseSchema<String>> rules;

    public MapSchema() {
        var checks = new ArrayList<Predicate<Map>>();
        checks.add(input -> !(input == null && isRequired));
        checks.add(input -> input == null || !(minSize > 0 && input.size() < minSize));
        checks.add(input -> {
            if (input == null || rules == null) {
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
        super.setChecks(checks);
    }

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
}
