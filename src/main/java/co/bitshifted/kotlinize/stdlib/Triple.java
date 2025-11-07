/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.stdlib;

import co.bitshifted.kotlinize.NullAware;
import java.util.function.Supplier;

/**
 * A generic Triple class to hold three related objects, similar to Kotlin's Triple type. Contained
 * objects are nul-safe, in that they are wrapped in {@link NullAware} to provide Kotlin-like null
 * safety features.
 *
 * @param <T> the type of the first element
 * @param <U> the type of the second element
 * @param <V> the type of the third element
 */
public class Triple<T, U, V> {

  private NullAware<T> first;
  private NullAware<U> second;
  private NullAware<V> third;

  /**
   * Constructs a Triple with the given suppliers for the first, second, and third elements. The
   * suppliers are wrapped in {@link NullAware} to provide null safety features.
   *
   * @param first supplier for the first element
   * @param second supplier for the second element
   * @param third supplier for the third element
   */
  public Triple(Supplier<T> first, Supplier<U> second, Supplier<V> third) {
    this.first = NullAware.nullAware(first);
    this.second = NullAware.nullAware(second);
    this.third = NullAware.nullAware(third);
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
  public NullAware<U> second() {
    return second;
  }

  /**
   * Returns the third element wrapped in {@link NullAware}.
   *
   * @return the third element
   */
  public NullAware<V> third() {
    return third;
  }
}
