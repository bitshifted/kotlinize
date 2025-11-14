/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.range;

/**
 * A range of {@code int} values. This class represents a sequence of integers from a start value to
 * an end value (inclusive).
 */
public final class IntRange extends Range<Integer> {

  /**
   * Creates a new {@code IntRange} with a specified step. The range includes all values from {@code
   * start} to {@code endInclusive}. If {@code start} is greater than {@code endInclusive}, the
   * range will be decreasing.
   *
   * @param start the starting value of the range
   * @param endInclusive the ending value of the range (inclusive)
   * @param step the step between each value in the range
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
   * Creates a new {@code IntRange} with a default step of 1. The range includes all values from
   * {@code start} to {@code endInclusive}. If {@code start} is greater than {@code endInclusive},
   * the range will be decreasing.
   *
   * @param start the starting value of the range
   * @param endInclusive the ending value of the range (inclusive)
   */
  public IntRange(int start, int endInclusive) {
    this(start, endInclusive, 1);
  }
}
