package co.bitshifted.kotlinize;

import org.junit.jupiter.api.Test;

import static co.bitshifted.kotlinize.Functions.*;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionsTest {

    @Test
    void shouldCreateArrayWhenDeclared() {
        Integer[] array = arrayOf(1, 2, 3, 4, 5);
        assertArrayEquals(new Integer[]{1,2,3,4,5}, array);
    }

    @Test
    void shouldReturnTrueOnSuccess() {
        boolean result = runCatching(() -> increment(5)).isSuccess();
        assertTrue(result);
    }

    @Test
    void shouldReturnValueOnSuccess() {
        int result = runCatching(() -> increment(5)).getOrNull();
        assertEquals(6, result);
    }

    @Test
    void shouldReturnNullOnFailure() {
        Integer result = runCatching(() -> increment(-1)).getOrNull();
        assertNull(result);
    }

    // New tests for arrayOfNulls and booleanArrayOf
    @Test
    void arrayOfNullsCreatesObjectArrayFilledWithNulls() {
        Object[] arr = arrayOfNulls(3, Object.class);
        assertNotNull(arr);
        assertEquals(3, arr.length);
        assertNull(arr[0]);
        assertNull(arr[1]);
        assertNull(arr[2]);
    }

    @Test
    void booleanArrayOfPrimitiveCreatesPrimitiveBooleanArray() {
        boolean[] arr = booleanArrayOf(true, false, true);
        assertArrayEquals(new boolean[]{true, false, true}, arr);
    }

    @Test
    void booleanArrayOfBoxedCreatesBooleanArray() {
        var arr = boxedBooleanArrayOf(Boolean.TRUE, null, Boolean.FALSE);
        assertArrayEquals(new Boolean[]{Boolean.TRUE, null, Boolean.FALSE}, arr);
    }

    // Tests for newly added primitive and boxed array factory methods
    @Test
    void shortArrayOfCreatesPrimitiveShortArray() {
        short[] arr = shortArrayOf((short)1, (short)2, (short)3);
        assertArrayEquals(new short[]{1,2,3}, arr);
    }

    @Test
    void boxedShortArrayOfCreatesShortArray() {
        Short[] arr = boxedShortArrayOf(Short.valueOf((short)1), null, Short.valueOf((short)3));
        assertArrayEquals(new Short[]{Short.valueOf((short)1), null, Short.valueOf((short)3)}, arr);
    }

    @Test
    void intArrayOfCreatesPrimitiveIntArray() {
        int[] arr = intArrayOf(1, 2, 3);
        assertArrayEquals(new int[]{1,2,3}, arr);
    }

    @Test
    void boxedIntArrayOfCreatesIntegerArray() {
        Integer[] arr = boxedIntArrayOf(1, null, 3);
        assertArrayEquals(new Integer[]{1, null, 3}, arr);
    }

    @Test
    void longArrayOfCreatesPrimitiveLongArray() {
        long[] arr = longArrayOf(1L, 2L, 3L);
        assertArrayEquals(new long[]{1L,2L,3L}, arr);
    }

    @Test
    void boxedLongArrayOfCreatesLongArray() {
        Long[] arr = boxedLongArrayOf(1L, null, 3L);
        assertArrayEquals(new Long[]{1L, null, 3L}, arr);
    }

    @Test
    void floatArrayOfCreatesPrimitiveFloatArray() {
        float[] arr = floatArrayOf(1.0f, 2.5f, -3.0f);
        assertArrayEquals(new float[]{1.0f,2.5f,-3.0f}, arr, 0.0f);
    }

    @Test
    void boxedFloatArrayOfCreatesFloatArray() {
        Float[] arr = boxedFloatArrayOf(1.0f, null, -3.0f);
        assertArrayEquals(new Float[]{1.0f, null, -3.0f}, arr);
    }

    @Test
    void doubleArrayOfCreatesPrimitiveDoubleArray() {
        double[] arr = doubleArrayOf(1.0, 2.5, -3.0);
        assertArrayEquals(new double[]{1.0,2.5,-3.0}, arr, 0.0);
    }

    @Test
    void boxedDoubleArrayOfCreatesDoubleArray() {
        Double[] arr = boxedDoubleArrayOf(1.0, null, -3.0);
        assertArrayEquals(new Double[]{1.0, null, -3.0}, arr);
    }

    @Test
    void repeatExecutesBlockSpecifiedTimes() {
        final int[] count = new int[1];
        repeat(4, () -> count[0]++);
        assertEquals(4, count[0]);
    }

    @Test
    void repeatDoesNothingForZeroOrNegative() {
        final int[] count = new int[1];
        repeat(0, () -> count[0]++);
        repeat(-3, () -> count[0]++);
        assertEquals(0, count[0]);
    }

    @Test
    void requireDoesNotThrowWhenConditionTrue() {
        // both overloads should not throw when condition is true
        require(true);
        require(true, "ok");
    }

    @Test
    void requireThrowsOnFalseWithDefaultMessage() {
        var ex = assertThrows(IllegalArgumentException.class, () -> require(false));
        assertEquals("Requirement failed", ex.getMessage());
    }

    @Test
    void requireWithMessageThrowsProvidedMessage() {
        var ex = assertThrows(IllegalArgumentException.class, () -> require(false, "custom message"));
        assertEquals("custom message", ex.getMessage());
    }

    @Test
    void requireNotNullDoesNotThrowWhenValueNonNull() {
        String v = requireNotNull("hello");
        assertEquals("hello", v);
        Integer i = requireNotNull(Integer.valueOf(5), "msg");
        assertEquals(Integer.valueOf(5), i);
    }

    @Test
    void requireNotNullThrowsWithDefaultMessageWhenNull() {
        var ex = assertThrows(IllegalArgumentException.class, () -> requireNotNull(null));
        assertEquals("Required value was null", ex.getMessage());
    }

    @Test
    void requireNotNullThrowsWithProvidedMessageWhenNull() {
        var ex = assertThrows(IllegalArgumentException.class, () -> requireNotNull(null, "custom not-null message"));
        assertEquals("custom not-null message", ex.getMessage());
    }

    @Test
    void todoThrowsUnsupportedOperationException() {
        var ex = assertThrows(UnsupportedOperationException.class, () -> TODO());
        assertEquals("Not implemented yet", ex.getMessage());
    }


    private int increment(int x) {
        if(x < 0) {
            throw new IllegalArgumentException("x must be non-negative");
        }
        return x + 1;
    }

}
