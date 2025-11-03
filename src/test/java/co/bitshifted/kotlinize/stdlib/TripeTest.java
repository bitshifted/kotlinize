/*
 * Copyright © 2025, Bitshift D.O.O <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TripeTest {

  @Test
  void shouldCreateTripeCorrectly() {
    var triple = new Triple<>(() -> 1, () -> "one", null);
    assertEquals(1, triple.first().value());
    assertEquals("one", triple.second().value());
    assertNull(triple.third().value());
  }

  @Test
  void shouldCorrectlyHandleNullValues() {
    var triple = new Triple<>(() -> null, () -> null, null);
    assertEquals(null, triple.first().value());
    assertEquals(null, triple.second().value());
  }
}
