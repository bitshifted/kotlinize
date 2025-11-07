/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.range;

/** A range of integers. */
public final class IntRange extends Range<Integer> {

  /**
   * Creates a new IntRange.
   *
   * @param start start value
   * @param endInclusive end value (inclusive)
   * @param step step value
   */
  public IntRange(int start, int endInclusive, int step) {
    super(start, endInclusive, step);
    var decreasing = start > endInclusive;
    int current = start;
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
   * Creates a new IntRange with a default step of 1.
   *
   * @param start start value
   * @param endInclusive end value (inclusive)
   */
  public IntRange(int start, int endInclusive) {
    this(start, endInclusive, 1);
  }
}
