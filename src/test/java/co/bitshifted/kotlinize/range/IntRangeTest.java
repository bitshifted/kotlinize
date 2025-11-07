/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.range;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IntRangeTest {

  @Test
  void shouldInitializeRangeCorrectly() {
    IntRange range = new IntRange(1, 5);
    assertEquals(1, range.start());
    assertEquals(5, range.end());

    range = new IntRange(2, 10, 3);
    assertEquals(2, range.start());
    assertEquals(10, range.end());
    assertEquals(2, range.first());
    assertEquals(8, range.last());
  }

  @Test
  void shouldInitializeDecrementingRangeCorrectly() {
    IntRange range = new IntRange(10, 2, 2);
    assertEquals(10, range.start());
    assertEquals(2, range.end());
    assertEquals(10, range.first());
    assertEquals(4, range.last());
  }

  @Test
  void shouldIterateCorrectly() {
    IntRange range = new IntRange(1, 5);
    int expected = 1;
    for (int value : range) {
      assertEquals(expected, value);
      expected++;
    }
    assertEquals(6, expected); // Ensure we iterated through all values
  }

  @Test
  void shouldContainRequriredValues() {
    IntRange range = new IntRange(1, 5);
    for (int i = 1; i <= 5; i++) {
      assertTrue(range.contains(i));
    }
    assertFalse(range.contains(0));
    assertFalse(range.contains(6));
  }

  @Test
  void shouldReturnCorrectResultForAnyMatch() {
    IntRange range = new IntRange(1, 5);
    assertTrue(range.any(x -> x == 3));
    assertFalse(range.any(x -> x > 5));
  }

  @Test
  void shouldReturnCorrectResultForAllMatch() {
    IntRange range = new IntRange(1, 5);
    assertTrue(range.all(x -> x >= 1 && x <= 5));
    assertFalse(range.all(x -> x < 5));
  }

  @Test
  void shouldReturnIterableForRange() {
    IntRange range = new IntRange(3, 7);
    Iterable<Integer> iterable = range.asIterable();
    int expected = 3;
    for (int value : iterable) {
      assertEquals(expected, value);
      expected++;
    }
    assertEquals(8, expected); // Ensure we iterated through all values
  }

  @Test
  void shouldReturnCorrectCount() {
    IntRange range = new IntRange(1, 10, 2);
    assertEquals(5, range.count()); // 1, 3, 5, 7, 9
  }

  @Test
  void shouldReturnDistinctElements() {
    IntRange range = new IntRange(1, 5);
    assertEquals(5, range.distinct().size());
  }
}
