/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.range;

/**
 * A range of {@code short} values. This class represents a sequence of short integers from a start
 * value to an end value (inclusive).
 */
public final class ShortRange extends Range<Short> {

  /**
   * Creates a new {@code ShortRange} with a specified step. The range includes all values from
   * {@code start} to {@code endInclusive}. If {@code start} is greater than {@code endInclusive},
   * the range will be decreasing.
   *
   * @param start the starting value of the range
   * @param endInclusive the ending value of the range (inclusive)
   * @param step the step between each value in the range
   */
  public ShortRange(short start, short endInclusive, short step) {
    super(start, endInclusive, step);
    var decreasing = start > endInclusive;
    short current = start;
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
   * Creates a new {@code ShortRange} with a default step of 1. The range includes all values from
   * {@code start} to {@tcode endInclusive}. If {@code start} is greater than {@code endInclusive},
   * the range will be decreasing.
   *
   * @param start the starting value of the range
   * @param endInclusive the ending value of the range (inclusive)
   */
  public ShortRange(short start, short endInclusive) {
    this(start, endInclusive, (short) 1);
  }
}
