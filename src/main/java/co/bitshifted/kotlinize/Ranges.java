/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize;

import co.bitshifted.kotlinize.range.*;

/** Utility class for creating ranges of various types. */
public final class Ranges {

  private Ranges() {
    // Prevent instantiation
  }

  /**
   * Creates a range of the specified type with the given start, end (inclusive), and step values.
   *
   * @param start start value of the range
   * @param endInclusive end value of the range (inclusive)
   * @param step step value for the range
   * @return a Range of the specified type
   * @param <T> the type of the range elements
   */
  public static <T> Range<T> range(T start, T endInclusive, T step) {
    var type = start.getClass().getSimpleName();

    switch (type) {
      case "Byte":
        return (Range<T>) new ByteRange((Byte) start, (Byte) endInclusive, (Byte) step);
      case "Short":
        return (Range<T>) new ShortRange((Short) start, (Short) endInclusive, (Short) step);
      case "Integer":
        return (Range<T>) new IntRange((Integer) start, (Integer) endInclusive, (Integer) step);
      case "Long":
        return (Range<T>) new LongRange((Long) start, (Long) endInclusive, (Long) step);
      case "Character":
        return (Range<T>)
            new CharRange((Character) start, (Character) endInclusive, (Character) step);
      default:
        throw new IllegalArgumentException("Unsupported type: " + type);
    }
  }

  /**
   * Creates a range of the specified type with the given start and end (inclusive) values. The step
   * value defaults to 1.
   *
   * @param start start value of the range
   * @param endInclusive end value of the range (inclusive)
   * @return a Range of the specified type
   * @param <T> the type of the range elements
   */
  public static <T> Range<T> range(T start, T endInclusive) {
    var type = start.getClass().getSimpleName();

    switch (type) {
      case "Byte":
        return (Range<T>) new ByteRange((Byte) start, (Byte) endInclusive);
      case "Short":
        return (Range<T>) new ShortRange((Short) start, (Short) endInclusive);
      case "Integer":
        return (Range<T>) new IntRange((Integer) start, (Integer) endInclusive);
      case "Long":
        return (Range<T>) new LongRange((Long) start, (Long) endInclusive);
      case "Character":
        return (Range<T>) new CharRange((Character) start, (Character) endInclusive);
      default:
        throw new IllegalArgumentException("Unsupported type: " + type);
    }
  }
}
