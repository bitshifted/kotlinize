/*
 * Copyright © 2025, Bitshift D.O.O <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PairTest {

  @Test
  void shouldCreatePairCorrectly() {
    var pair = new Pair<>(() -> 1, () -> "one");
    assertEquals(1, pair.first().value());
    assertEquals("one", pair.second().value());
  }

  @Test
  void shouldCorrectlyHandleNullValues() {
    var pair = new Pair<>(() -> null, () -> null);
    assertEquals(null, pair.first().value());
    assertEquals(null, pair.second().value());
  }
}
