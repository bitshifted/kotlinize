/*
 * Copyright © 2025, Bitshift D.O.O <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.delegates;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;

public class ObservablePropertyTest {

  @Test
  public void initializationShouldRetainInitialValue() {
    ObservableProperty<String> prop = new ObservableProperty<>("init", (oldV, newV) -> null);
    assertEquals("init", prop.value());
  }

  @Test
  public void settingValueShouldUpdateAndInvokeCallback() {
    AtomicReference<String> seenOld = new AtomicReference<>();
    AtomicReference<String> seenNew = new AtomicReference<>();

    BiFunction<String, String, Void> cb =
        (oldV, newV) -> {
          seenOld.set(oldV);
          seenNew.set(newV);
          return null;
        };

    ObservableProperty<String> prop = new ObservableProperty<>("first", cb);
    prop.value("second");

    assertEquals("second", prop.value());
    assertEquals("first", seenOld.get());
    assertEquals("second", seenNew.get());
  }

  @Test
  public void callbackShouldBeCalledEvenWhenOldIsNullOrNewIsNull() {
    AtomicReference<String> seenOld = new AtomicReference<>();
    AtomicReference<String> seenNew = new AtomicReference<>();

    BiFunction<String, String, Void> cb =
        (oldV, newV) -> {
          seenOld.set(oldV);
          seenNew.set(newV);
          return null;
        };

    ObservableProperty<String> prop = new ObservableProperty<>(null, cb);
    prop.value("notNull");
    assertNull(seenOld.get());
    assertEquals("notNull", seenNew.get());

    // set back to null
    prop.value(null);
    assertEquals("notNull", seenOld.get());
    assertNull(seenNew.get());
  }

  @Test
  public void constructorShouldThrowWhenCallbackIsNull() {
    IllegalStateException ex =
        assertThrows(IllegalStateException.class, () -> new ObservableProperty<>("v", null));
    assertTrue(ex.getMessage().contains("Callback cannot be null"));
  }
}
