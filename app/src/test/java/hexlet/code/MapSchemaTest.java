package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {
    Validator v;
    MapSchema schema;
    @BeforeEach
    void prepValidator() {
        v = new Validator();
        schema = v.map();
    }

    @Test
    void testRequired() {
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    void testSizeOf() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testShape() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("k1", v.string().required());
        schemas.put("k2", v.string().required().minLength(3));
        schemas.put("k3", v.string().required().contains("name").contains("null"));
        schema.shape(schemas);
        Map<String, String> m1 = new HashMap<>();
        m1.put("k1", "X");
        m1.put("k2", "means");
        m1.put("k3", "var name is not null");
        assertTrue(schema.isValid(m1));
        Map<String, String> m2 = new HashMap<>();
        m2.put("k1", "Y");
        m2.put("k2", "abc");
        m2.put("k3", "xyz");
        assertFalse(schema.isValid(m2));
        Map<String, String> m3 = new HashMap<>();
        m3.put("k1", "Z");
        m3.put("k", "misses");
        m3.put("k3", "var name is not null");
        assertFalse(schema.isValid(m3));
    }
}
