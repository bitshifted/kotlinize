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

public class CharRangeTest {

  @Test
  void shouldInitializeRangeCorrectly() {
    var range = new CharRange('a', 'e');
    assertEquals('a', range.start());
    assertEquals('e', range.end());

    range = new CharRange('a', 'h', (char) 2);
    assertEquals('a', range.start());
    assertEquals('h', range.end());
    assertEquals('a', range.first());
    assertEquals('g', range.last());
  }

  @Test
  void shouldInitializeDecrementingRangeCorrectly() {
    var range = new CharRange('h', 'a', (char) 2);
    assertEquals('h', range.start());
    assertEquals('a', range.end());
    assertEquals('h', range.first());
    assertEquals('b', range.last());
  }

  @Test
  void shouldIterateCorrectly() {
    var range = new CharRange('a', 'e');
    char expected = 'a';
    for (char value : range) {
      assertEquals(expected, value);
      expected++;
    }
    assertEquals('f', expected); // Ensure we iterated through all values
  }

  @Test
  void shouldContainRequiredValues() {
    var range = new CharRange('a', 'e');
    for (char i = 'a'; i <= 'e'; i++) {
      assertTrue(range.contains(i));
    }
    assertFalse(range.contains('m'));
    assertFalse(range.contains('h'));
  }

  @Test
  void shouldReturnCorrectResultForAnyMatch() {
    var range = new CharRange('a', 'e');
    assertTrue(range.any(x -> x == 'c'));
    assertFalse(range.any(x -> x > 'n'));
  }

  @Test
  void shouldReturnCorrectResultForAllMatch() {
    var range = new CharRange('a', 'e');
    assertTrue(range.all(x -> x >= 'a' && x <= 'e'));
    assertFalse(range.all(x -> x < 'a'));
  }

  @Test
  void shouldReturnIterableForRange() {
    var range = new CharRange('c', 'g');
    Iterable<Character> iterable = range.asIterable();
    char expected = 'c';
    for (char value : iterable) {
      assertEquals(expected, value);
      expected++;
    }
    assertEquals('h', expected); // Ensure we iterated through all values
  }

  @Test
  void shouldReturnCorrectCount() {
    var range = new CharRange('a', 'h', (char) 2);
    assertEquals(4, range.count()); // a, c, e, g
  }

  @Test
  void shouldReturnDistinctElements() {
    var range = new CharRange('a', 'e');
    assertEquals(5, range.distinct().size());
  }
}
