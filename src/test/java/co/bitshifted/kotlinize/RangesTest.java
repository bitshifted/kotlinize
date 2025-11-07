/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize;

import static co.bitshifted.kotlinize.Ranges.*;
import static org.junit.jupiter.api.Assertions.*;

import co.bitshifted.kotlinize.range.ByteRange;
import org.junit.jupiter.api.Test;

public class RangesTest {

  @Test
  void shouldInitializeByteRange() {
    var range = range((byte) 1, (byte) 10, (byte) 2);
    assertInstanceOf(ByteRange.class, range);
    assertEquals((byte) 1, range.first());
    assertEquals((byte) 9, range.last());

    range = range((byte) 10, (byte) 1, (byte) 2);
    assertInstanceOf(ByteRange.class, range);
    assertEquals((byte) 10, range.first());
    assertEquals((byte) 2, range.last());
  }

  @Test
  void shouldInitializeShortRange() {
    var range = range((short) 1, (short) 10, (short) 2);
    assertInstanceOf(co.bitshifted.kotlinize.range.ShortRange.class, range);
    assertEquals((short) 1, range.first());
    assertEquals((short) 9, range.last());

    range = range((short) 10, (short) 1, (short) 2);
    assertInstanceOf(co.bitshifted.kotlinize.range.ShortRange.class, range);
    assertEquals((short) 10, range.first());
    assertEquals((short) 2, range.last());
  }

  @Test
  void shouldInitializeIntegerRange() {
    var range = range(1, 10, 2);
    assertInstanceOf(co.bitshifted.kotlinize.range.IntRange.class, range);
    assertEquals(1, range.first());
    assertEquals(9, range.last());

    range = range(10, 1, 2);
    assertInstanceOf(co.bitshifted.kotlinize.range.IntRange.class, range);
    assertEquals(10, range.first());
    assertEquals(2, range.last());
  }

  @Test
  void shouldInitializeLongRange() {
    var range = range(1L, 10L, 2L);
    assertInstanceOf(co.bitshifted.kotlinize.range.LongRange.class, range);
    assertEquals(1L, range.first());
    assertEquals(9L, range.last());

    range = range(10L, 1L, 2L);
    assertInstanceOf(co.bitshifted.kotlinize.range.LongRange.class, range);
    assertEquals(10L, range.first());
    assertEquals(2L, range.last());
  }

  @Test
  void shouldInitializeCharRange() {
    var range = range('a', 'e', (char) 1);
    assertInstanceOf(co.bitshifted.kotlinize.range.CharRange.class, range);
    assertEquals('a', range.first());
    assertEquals('e', range.last());

    range = range('e', 'a', (char) 1);
    assertInstanceOf(co.bitshifted.kotlinize.range.CharRange.class, range);
    assertEquals('e', range.first());
    assertEquals('b', range.last());
  }
}
