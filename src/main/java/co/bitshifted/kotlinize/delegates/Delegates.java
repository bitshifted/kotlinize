/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.delegates;

import java.util.function.BiFunction;

/**
 * A utility class that provides factory methods for creating delegated properties,
 * similar to Kotlin's standard library delegates.
 * This class provides implementations for `observable` and `vetoable` properties.
 */
public final class Delegates {

  private Delegates() {}

  /**
   * Creates an {@link ObservableProperty} with an initial value of {@code null}.
   *
   * @param callback a function that will be called after the property value has been changed.
   *                 The function receives the old and new values.
   * @param <T> the type of the property value
   * @return a new {@link ObservableProperty}
   * @see ObservableProperty
   */
  public static <T> ObservableProperty<T> observable(BiFunction<T, T, Void> callback) {
    return observable(null, callback);
  }

  /**
   * Creates an {@link ObservableProperty} with the given initial value.
   *
   * @param initialValue the initial value of the property
   * @param callback a function that will be called after the property value has been changed.
   *                 The function receives the old and new values.
   * @param <T> the type of the property value
   * @return a new {@link ObservableProperty}
   * @see ObservableProperty
   */
  public static <T> ObservableProperty<T> observable(
      T initialValue, BiFunction<T, T, Void> callback) {
    return new ObservableProperty<>(initialValue, callback);
  }

  /**
   * Creates a {@link VetoableProperty} with an initial value of {@code null}.
   *
   * @param callback a function that will be called before the property value is changed.
   *                 The function receives the old and new values and should return {@code true}
   *                 to allow the change or {@code false} to veto it.
   * @param <T> the type of the property value
   * @return a new {@link VetoableProperty}
   * @see VetoableProperty
   */
  public static <T> VetoableProperty<T> vetoable(BiFunction<T, T, Boolean> callback) {
    return vetoable(null, callback);
  }

  /**
   * Creates a {@link VetoableProperty} with the given initial value.
   *
   * @param initialValue the initial value of the property
   * @param callback a function that will be called before the property value is changed.
   *                 The function receives the old and new values and should return {@code true}
   *                 to allow the change or {@code false} to veto it.
   * @param <T> the type of the property value
   * @return a new {@link VetoableProperty}
   * @see VetoableProperty
   */
  public static <T> VetoableProperty<T> vetoable(
      T initialValue, BiFunction<T, T, Boolean> callback) {
    return new VetoableProperty<>(initialValue, callback);
  }
}
