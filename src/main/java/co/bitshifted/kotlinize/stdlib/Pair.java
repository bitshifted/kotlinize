/*
 * Copyright © 2025, Bitshift D.O.O <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.stdlib;

import static co.bitshifted.kotlinize.NullAware.*;

import co.bitshifted.kotlinize.NullAware;
import java.util.function.Supplier;

/**
 * A generic Pair class to hold two related objects, similar to Kotlin's Pair type. Contained
 * objects are nul-safe, in that they are wrapped in {@link NullAware} to provide Kotlin-like null
 * safety features.
 *
 * @param <T> the type of the first element
 * @param <V> the type of the second element
 */
public final class Pair<T, V> {

  private final NullAware<T> first;
  private final NullAware<V> second;

  /**
   * Constructs a Pair with the given suppliers for the first and second elements. The suppliers are
   * wrapped in {@link NullAware} to provide null safety features.
   *
   * @param first supplier for the first element
   * @param second supplier for the second element
   */
  public Pair(Supplier<T> first, Supplier<V> second) {
    this.first = nullAware(first);
    this.second = nullAware(second);
  }

  /**
   * Returns the first element wrapped in {@link NullAware}.
   *
   * @return the first element
   */
  public NullAware<T> first() {
    return first;
  }

  /**
   * Returns the second element wrapped in {@link NullAware}.
   *
   * @return the second element
   */
  public NullAware<V> second() {
    return second;
  }
}
