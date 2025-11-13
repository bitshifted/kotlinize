/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.example;

import static co.bitshifted.kotlinize.Functions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

public class StandardLibrary {

  @Test
  void exampleArray() {
    var array = arrayOf("foo", "bar", "baz");
    assertEquals(3, array.length);
    assertArrayEquals(new String[] {"foo", "bar", "baz"}, array);

    var nullArray = arrayOfNulls(3, Integer.class);
    assertArrayEquals(new Integer[] {null, null, null}, nullArray);
  }

  @Test
  void exampleErrorFunction() {
    assertThrows(IllegalStateException.class, () -> error("This is an error"));
  }

  @Test
  void exampleLazy() {
    var lazyValue =
        lazy(
            () -> {
              System.out.println("Computing lazy value");
              return 42;
            });

    System.out.println("Before accessing lazy value");
    assertEquals(42, lazyValue.value());
    System.out.println("After accessing lazy value");
    assertEquals(42, lazyValue.value()); // should not recompute
  }

  @Test
  void exampleRepeat() {
    var counter = new AtomicInteger(0);
    repeat(5, () -> counter.incrementAndGet());
    assertEquals(5, counter.get());
  }

  @Test
  void exampleRunCatchingFold() {
    var successResult =
        runCatching(() -> testFunction(5))
            .fold(value -> "Success: " + value, ex -> "Error: " + ex.getMessage());
    assertEquals("Success: 10", successResult);

    var failureResult =
        runCatching(() -> testFunction(15))
            .fold(value -> "Success: " + value, ex -> "Error: " + ex.getMessage());
    assertEquals("Error: Value must be less than 10", failureResult);
  }

  int testFunction(int value) {
    if (value < 10) {
      return value * 2;
    }
    throw new IllegalArgumentException("Value must be less than 10");
  }
}
