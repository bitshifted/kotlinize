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
 * A property that notifies a callback function when its value has been changed. This is similar to
 * Kotlin's {@code observable} delegate.
 *
 * @param <T> the type of the property value
 * @see Delegates#observable(BiFunction)
 * @see Delegates#observable(Object, BiFunction)
 */
public class ObservableProperty<T> {

  private T value;
  private final BiFunction<T, T, Void> callback;

  /**
   * Constructs a new {@code ObservableProperty} with an initial value and a callback.
   *
   * @param initialValue the initial value of the property
   * @param callback a function that will be called after the property value has been changed. It
   *     receives the old and new values.
   * @throws IllegalStateException if the callback is {@code null}
   */
  public ObservableProperty(T initialValue, BiFunction<T, T, Void> callback) {
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
   * Sets a new value for the property and notifies the callback. The callback is invoked after the
   * value has been updated.
   *
   * @param newValue the new value to set
   */
  public void value(T newValue) {
    T oldValue = this.value;
    this.value = newValue;
    callback.apply(oldValue, newValue);
  }
}
