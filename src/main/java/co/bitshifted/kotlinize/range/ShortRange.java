/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.range;

public final class ShortRange extends Range<Short> {

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

  public ShortRange(short start, short endInclusive) {
    this(start, endInclusive, (short) 1);
  }
}
