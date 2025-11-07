/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.range;

/** A range of characters. */
public final class CharRange extends Range<Character> {

  /**
   * Creates a new CharRange.
   *
   * @param start start value
   * @param endInclusive end value (inclusive)
   * @param step step value
   */
  public CharRange(char start, char endInclusive, char step) {
    super(start, endInclusive, step);
    var decreasing = start > endInclusive;
    char current = start;
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
   * Creates a new CharRange with a default step of 1.
   *
   * @param start start value
   * @param endInclusive end value (inclusive)
   */
  public CharRange(char start, char endInclusive) {
    this(start, endInclusive, (char) 1);
  }
}
