/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.range;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ShortRangeTest {

  @Test
  void shouldInitializeRangeCorrectly() {
    var range = new ShortRange((short) 1, (short) 5);
    assertEquals((short) 1, range.start());
    assertEquals((short) 5, range.end());

    range = new ShortRange((short) 2, (short) 10, (short) 3);
    assertEquals((short) 2, range.start());
    assertEquals((short) 10, range.end());
    assertEquals((short) 2, range.first());
    assertEquals((short) 8, range.last());
  }

  @Test
  void shouldInitializeDecrementingRangeCorrectly() {
    var range = new ShortRange((short) 10, (short) 2, (short) 2);
    assertEquals((short) 10, range.start());
    assertEquals((short) 2, range.end());
    assertEquals((short) 10, range.first());
    assertEquals((short) 4, range.last());
  }

  @Test
  void shouldIterateCorrectly() {
    var range = new ShortRange((short) 1, (short) 5);
    int expected = 1;
    for (int value : range) {
      assertEquals(expected, value);
      expected++;
    }
    assertEquals(6, expected); // Ensure we iterated through all values
  }

  @Test
  void shouldContainRequiredValues() {
    var range = new ShortRange((short) 1, (short) 5);
    for (short i = 1; i <= 5; i++) {
      assertTrue(range.contains(i));
    }
    assertFalse(range.contains((short) 0));
    assertFalse(range.contains((short) 6));
  }

  @Test
  void shouldReturnCorrectResultForAnyMatch() {
    var range = new ShortRange((short) 1, (short) 5);
    assertTrue(range.any(x -> x == 3));
    assertFalse(range.any(x -> x > 5));
  }

  @Test
  void shouldReturnCorrectResultForAllMatch() {
    var range = new ShortRange((short) 1, (short) 5);
    assertTrue(range.all(x -> x >= 1 && x <= 5));
    assertFalse(range.all(x -> x < 5));
  }

  @Test
  void shouldReturnIterableForRange() {
    var range = new ShortRange((short) 3, (short) 7);
    Iterable<Short> iterable = range.asIterable();
    int expected = 3;
    for (int value : iterable) {
      assertEquals(expected, value);
      expected++;
    }
    assertEquals(8, expected); // Ensure we iterated through all values
  }

  @Test
  void shouldReturnCorrectCount() {
    var range = new ShortRange((short) 1, (short) 10, (short) 2);
    assertEquals(5, range.count()); // 1, 3, 5, 7, 9
  }

  @Test
  void shouldReturnDistinctElements() {
    var range = new ShortRange((short) 1, (short) 5);
    assertEquals(5, range.distinct().size());
  }
}
