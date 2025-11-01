package co.bitshifted.kotlinize.stdlib;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class LazyTest {

    @Test
    void constructorNullThrows() {
        assertThrows(NullPointerException.class, () -> new Lazy<String>(null));
    }

    @Test
    void getValueInitializesOnceAndReturnsValue() {
        AtomicInteger counter = new AtomicInteger(0);
        Lazy<Integer> lazy = new Lazy<>(counter::incrementAndGet);

        assertFalse(lazy.isInitialized());
        int first = lazy.value();
        assertTrue(lazy.isInitialized());
        assertEquals(1, first);
        assertEquals(1, counter.get());

        // subsequent calls should not invoke the initializer again
        int second = lazy.value();
        assertEquals(first, second);
        assertEquals(1, counter.get());
    }

    @Test
    void initializerThrowsThenSucceeds() {
        AtomicInteger attempts = new AtomicInteger(0);
        Lazy<String> lazy = new Lazy<>(() -> {
            int a = attempts.incrementAndGet();
            if (a == 1) {
                throw new RuntimeException("boom");
            }
            return "ok";
        });

        // first attempt fails and should leave the instance uninitialized
        assertThrows(RuntimeException.class, lazy::value);
        assertFalse(lazy.isInitialized());

        // second attempt succeeds
        String v = lazy.value();
        assertEquals("ok", v);
        assertTrue(lazy.isInitialized());
        assertEquals(2, attempts.get());
    }

    @Test
    void initializerReturningNullIsAllowed() {
        Lazy<String> lazy = new Lazy<>(() -> null);
        assertFalse(lazy.isInitialized());
        assertNull(lazy.value());
        assertTrue(lazy.isInitialized());
    }

    @Test
    void concurrentInitializationInvokedOnlyOnce() throws Exception {
        final AtomicInteger initCount = new AtomicInteger(0);
        Lazy<Integer> lazy = new Lazy<>(() -> {
            // slow initializer to increase chance of concurrent contention
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
            return initCount.incrementAndGet();
        });

        int threads = 10;
        ExecutorService exec = Executors.newFixedThreadPool(threads);
        try {
            List<Future<Integer>> futures = new ArrayList<>();
            for (int i = 0; i < threads; i++) {
                futures.add(exec.submit(lazy::value));
            }

            // wait for results
            for (Future<Integer> f : futures) {
                Integer val = f.get(2, TimeUnit.SECONDS);
                assertNotNull(val);
                assertEquals(1, val.intValue());
            }

            // initializer should have run exactly once
            assertEquals(1, initCount.get());
            assertTrue(lazy.isInitialized());
        } finally {
            exec.shutdownNow();
        }
    }
}

