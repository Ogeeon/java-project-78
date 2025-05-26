package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumberSchemaTest {
    private NumberSchema schema;
    @BeforeEach
    void prepValidator() {
        var v = new Validator();
        schema = v.number();
    }

    @Test
    void testRequired() {
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    void testPositive() {
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-1));
        schema.positive();
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-1));
    }

    @Test
    void testRange() {
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(10));
        schema.range(5, 15);
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertThrows(IllegalArgumentException.class, () -> schema.range(10, 8));
    }

    @Test
    void testRange2() {
        schema.range(5, 5);
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(10));
    }

    @Test
    void testRange3() {
        schema.range(-15, 0);
        assertFalse(schema.isValid(-20));
        assertFalse(schema.isValid(20));
        assertTrue(schema.isValid(-15));
        assertTrue(schema.isValid(0));
    }
}
