/*
 * Copyright © 2025, Bitshift D.O.O <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.delegates;

import co.bitshifted.kotlinize.Functions;
import java.util.function.BiFunction;

public class ObservableProperty<T> {

  private T value;
  private final BiFunction<T, T, Void> callback;

  public ObservableProperty(T initialValue, BiFunction<T, T, Void> callback) {
    if (callback == null) {
      Functions.error("Callback cannot be null");
    }
    this.value = initialValue;
    this.callback = callback;
  }

  public T value() {
    return value;
  }

  public void value(T newValue) {
    T oldValue = this.value;
    this.value = newValue;
    callback.apply(oldValue, newValue);
  }
}
