package co.bitshifted.kotlinize;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsableTest {

    static class TestResource implements AutoCloseable {
        private boolean closed = false;
        private final String value;
        TestResource(String value) { this.value = value; }
        String read() { return value; }
        boolean isClosed() { return closed; }
        @Override
        public void close() { closed = true; }
    }

    @Test
    void usableInitializesAndUseClosesResource() throws Exception {
        TestResource res = new TestResource("hello");
        var usable = Usable.usable(() -> res);

        // inside the block the resource must not be closed
        var out = usable.use(r -> {
            assertFalse(r.isClosed());
            return r.read();
        });

        // the returned NullAware should contain the value
        assertEquals("hello", out.value());

        // resource must be closed after use returns
        assertTrue(res.isClosed());
    }

    @Test
    void usePropagatesExceptionAndClosesResource() {
        TestResource res = new TestResource("hello");
        var usable = Usable.usable(() -> res);

        var ex = assertThrows(RuntimeException.class, () ->
            usable.use(r -> { throw new RuntimeException("boom"); })
        );
        assertEquals("boom", ex.getMessage());

        // resource must be closed even when the block throws
        assertTrue(res.isClosed());
    }

}
