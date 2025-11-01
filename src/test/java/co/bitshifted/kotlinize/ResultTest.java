package co.bitshifted.kotlinize;

import co.bitshifted.kotlinize.stdlib.Result;
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

    @Test
    void getOrDefaultReturnsValueOnSuccess() {
        var result = new Result<>("value");
        assertEquals("value", result.getOrDefault("default"));
    }

    @Test
    void getOrDefaultReturnsDefaultOnFailure() {
        var ex = new RuntimeException("boom");
        var result = new Result<String>(ex);
        assertEquals("default", result.getOrDefault("default"));
    }

    @Test
    void getOrElseUsesFunctionOnFailure() {
        var ex = new RuntimeException("boom");
        var result = new Result<String>(ex);
        var out = result.getOrElse(e -> {
            assertSame(ex, e);
            return "computed";
        });
        assertEquals("computed", out);
    }

    @Test
    void getOrElseDoesNotCallFunctionOnSuccess() {
        var result = new Result<>("ok");
        var out = result.getOrElse(e -> {
            fail("getOrElse function should not be called on success");
            return "x";
        });
        assertEquals("ok", out);
    }

    @Test
    void getOrThrowReturnsValueOnSuccess() {
        var result = new Result<>("ok");
        var value = assertDoesNotThrow(result::getOrThrow);
        assertEquals("ok", value);
    }

    @Test
    void getOrThrowThrowsExceptionOnFailure() {
        var ex = new RuntimeException("boom");
        var result = new Result<String>(ex);
        var thrown = assertThrows(Throwable.class, result::getOrThrow);
        assertSame(ex, thrown);
    }

    @Test
    void mapTransformsOnSuccess() {
        var res = new Result<>(5);
        var mapped = res.map(x -> x * 2);
        assertEquals(10, mapped.getOrNull());
        assertTrue(mapped.isSuccess());
    }

    @Test
    void mapPropagatesFailure() {
        var ex = new RuntimeException("boom");
        var res = new Result<Integer>(ex);
        var mapped = res.map(x -> x * 2);
        assertNull(mapped.getOrNull());
        assertSame(ex, mapped.exceptionOrNull());
    }

    @Test
    void onFailureCallsActionOnFailure() {
        var ex = new RuntimeException("boom");
        var res = new Result<String>(ex);
        final Throwable[] captured = new Throwable[1];
        var returned = res.onFailure(e -> captured[0] = e);
        assertSame(res, returned);
        assertSame(ex, captured[0]);
    }

    @Test
    void onFailureDoesNotCallOnSuccess() {
        var res = new Result<>("ok");
        final boolean[] called = new boolean[1];
        res.onFailure(e -> called[0] = true);
        assertFalse(called[0]);
    }

    @Test
    void onSuccessCallsActionOnSuccess() {
        var res = new Result<>("ok");
        final String[] captured = new String[1];
        var returned = res.onSuccess(v -> captured[0] = v);
        assertSame(res, returned);
        assertEquals("ok", captured[0]);
    }

    @Test
    void onSuccessDoesNotCallOnFailure() {
        var ex = new RuntimeException("boom");
        var res = new Result<String>(ex);
        final boolean[] called = new boolean[1];
        res.onSuccess(v -> called[0] = true);
        assertFalse(called[0]);
    }

}
