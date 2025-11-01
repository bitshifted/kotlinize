package co.bitshifted.kotlinize.delegates;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

public class VetoablePropertyTest {

    @Test
    public void initializationShouldRetainInitialValue() {
        VetoableProperty<String> p = new VetoableProperty<>("start", (oldV, newV) -> true);
        assertEquals("start", p.value());
    }

    @Test
    public void settingAllowedShouldUpdateValue() {
        AtomicReference<String> seenOld = new AtomicReference<>();
        AtomicReference<String> seenNew = new AtomicReference<>();

        BiFunction<String, String, Boolean> cb = (oldV, newV) -> {
            seenOld.set(oldV);
            seenNew.set(newV);
            return true;
        };

        VetoableProperty<String> p = new VetoableProperty<>("a", cb);
        p.value("b");

        assertEquals("b", p.value());
        assertEquals("a", seenOld.get());
        assertEquals("b", seenNew.get());
    }

    @Test
    public void settingVetoedShouldNotChangeValue() {
        BiFunction<Integer, Integer, Boolean> cb = (oldV, newV) -> newV >= 0; // only allow non-negative
        VetoableProperty<Integer> p = new VetoableProperty<>(5, cb);

        p.value(-10); // vetoed
        assertEquals(5, p.value());

        p.value(3); // allowed
        assertEquals(3, p.value());
    }

    @Test
    public void constructorShouldThrowWhenCallbackIsNull() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->
            new VetoableProperty<>("v", null)
        );
        assertTrue(ex.getMessage().contains("Callback cannot be null"));
    }
}
