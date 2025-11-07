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

public class ByteRangeTest {

  @Test
  void shouldInitializeRangeCorrectly() {
    var range = new ByteRange((byte) 1, (byte) 5);
    assertEquals((byte) 1, range.start());
    assertEquals((byte) 5, range.end());

    range = new ByteRange((byte) 2, (byte) 10, (byte) 3);
    assertEquals((byte) 2, range.start());
    assertEquals((byte) 10, range.end());
    assertEquals((byte) 2, range.first());
    assertEquals((byte) 8, range.last());
  }

  @Test
  void shouldInitializeDecrementingRangeCorrectly() {
    var range = new ByteRange((byte) 10, (byte) 2, (byte) 2);
    assertEquals((byte) 10, range.start());
    assertEquals((byte) 2, range.end());
    assertEquals((byte) 10, range.first());
    assertEquals((byte) 4, range.last());
  }

  @Test
  void shouldIterateCorrectly() {
    var range = new ByteRange((byte) 1, (byte) 5);
    int expected = 1;
    for (int value : range) {
      assertEquals(expected, value);
      expected++;
    }
    assertEquals(6, expected); // Ensure we iterated through all values
  }

  @Test
  void shouldContainRequiredValues() {
    var range = new ByteRange((byte) 1, (byte) 5);
    for (byte i = 1; i <= 5; i++) {
      assertTrue(range.contains(i));
    }
    assertFalse(range.contains((byte) 0));
    assertFalse(range.contains((byte) 6));
  }

  @Test
  void shouldReturnCorrectResultForAnyMatch() {
    var range = new ByteRange((byte) 1, (byte) 5);
    assertTrue(range.any(x -> x == 3));
    assertFalse(range.any(x -> x > 5));
  }

  @Test
  void shouldReturnCorrectResultForAllMatch() {
    var range = new ByteRange((byte) 1, (byte) 5);
    assertTrue(range.all(x -> x >= 1 && x <= 5));
    assertFalse(range.all(x -> x < 5));
  }

  @Test
  void shouldReturnIterableForRange() {
    var range = new ByteRange((byte) 3, (byte) 7);
    Iterable<Byte> iterable = range.asIterable();
    int expected = 3;
    for (int value : iterable) {
      assertEquals(expected, value);
      expected++;
    }
    assertEquals(8, expected); // Ensure we iterated through all values
  }

  @Test
  void shouldReturnCorrectCount() {
    var range = new ByteRange((byte) 1, (byte) 10, (byte) 2);
    assertEquals(5, range.count()); // 1, 3, 5, 7, 9
  }

  @Test
  void shouldReturnDistinctElements() {
    var range = new ByteRange((byte) 1, (byte) 5);
    assertEquals(5, range.distinct().size());
  }
}
