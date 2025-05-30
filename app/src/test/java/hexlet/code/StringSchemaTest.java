package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    private StringSchema schema;
    @BeforeEach
    void prepValidator() {
        var v = new Validator();
        schema = v.string();
    }

    @Test
    void testRequired() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    void testMinLength() {
        assertTrue(schema.isValid("a"));
        schema.minLength(2);
        assertFalse(schema.isValid("a"));
        assertTrue(schema.isValid("ab"));
    }

    @Test
    void testContains() {
        schema.contains("abc");
        assertTrue(schema.isValid("abc"));
        assertFalse(schema.isValid("acb"));
        schema.contains("de");
        assertTrue(schema.isValid("abcdef"));
        assertFalse(schema.isValid("abc"));
    }

    @Test
    void testChain() {
        schema.required().minLength(10).minLength(6);
        assertTrue(schema.isValid("abcdef"));
        assertFalse(schema.isValid("abc"));
        schema.contains("abc").contains("de");
        assertTrue(schema.isValid("abcdef"));
        assertFalse(schema.isValid("abc"));
        assertFalse(schema.isValid("abc123"));
        schema.minLength(7).contains("X");
        assertFalse(schema.isValid("TUVWXYZ"));
        assertTrue(schema.isValid("qXdeabc"));
    }
}
