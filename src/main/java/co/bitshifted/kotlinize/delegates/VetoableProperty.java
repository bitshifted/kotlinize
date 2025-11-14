/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.delegates;

import co.bitshifted.kotlinize.Functions;
import java.util.function.BiFunction;

/**
 * A property that allows a callback to veto changes to its value. This is similar to Kotlin's
 * {@code vetoable} delegate.
 *
 * @param <T> the type of the property value
 * @see Delegates#vetoable(BiFunction)
 * @see Delegates#vetoable(Object, BiFunction)
 */
public class VetoableProperty<T> {

  private T value;
  private final BiFunction<T, T, Boolean> callback;

  /**
   * Constructs a new {@code VetoableProperty} with an initial value and a callback.
   *
   * @param initialValue the initial value of the property
   * @param callback a function that will be called before the property value is changed. It
   *     receives the old and new values and should return {@code true} to allow the change or
   *     {@code false} to veto it.
   * @throws IllegalStateException if the callback is {@code null}
   */
  public VetoableProperty(T initialValue, BiFunction<T, T, Boolean> callback) {
    if (callback == null) {
      Functions.error("Callback cannot be null");
    }
    this.value = initialValue;
    this.callback = callback;
  }

  /**
   * Returns the current value of the property.
   *
   * @return the current value
   */
  public T value() {
    return value;
  }

  /**
   * Sets a new value for the property if the change is not vetoed by the callback. The callback is
   * invoked before the value is changed.
   *
   * @param newValue the new value to set
   */
  public void value(T newValue) {
    var allowed = callback.apply(this.value, newValue);
    if (allowed) {
      this.value = newValue;
    }
  }
}
