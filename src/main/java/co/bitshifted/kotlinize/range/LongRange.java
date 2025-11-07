/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.range;

/** A range of long integers. */
public final class LongRange extends Range<Long> {

  /**
   * Creates a new LongRange.
   *
   * @param start start value
   * @param endInclusive end value (inclusive)
   * @param step step value
   */
  public LongRange(long start, long endInclusive, long step) {
    super(start, endInclusive, step);
    var decreasing = start > endInclusive;
    long current = start;
    while (!decreasing && current <= endInclusive) {
      elements.add(current);
      current += step;
    }
    while (decreasing && current > endInclusive) {
      elements.add(current);
      current -= step;
    }
  }

  /**
   * Creates a new LongRange with a default step of 1.
   *
   * @param start start value
   * @param endInclusive end value (inclusive)
   */
  public LongRange(long start, long endInclusive) {
    this(start, endInclusive, 1L);
  }
}
