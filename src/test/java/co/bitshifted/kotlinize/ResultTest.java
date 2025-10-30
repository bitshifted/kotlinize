package co.bitshifted.kotlinize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {

    @Test
    void shouldReturnCorrectValueWhenSuccess() {
        var result = new Result<>(5);
        var out = result.fold(x -> x +1, null);
        assertEquals(6, out);
    }

    @Test
    void shouldReturnCorrectBooleanWhenFold() {
        var result = new Result<>(5);
        var out = result.fold(x -> x < 10 ? true : false, e -> false);
        assertTrue(out);
    }

    @Test
    void getOrNullAndExceptionOrNullOnSuccess() {
        var result = new Result<>("hello");
        assertEquals("hello", result.getOrNull());
        assertNull(result.exceptionOrNull());
    }

    @Test
    void getOrNullAndExceptionOrNullOnFailure() {
        var ex = new RuntimeException("boom");
        var result = new Result<String>(ex);
        assertNull(result.getOrNull());
        assertSame(ex, result.exceptionOrNull());
    }

}
