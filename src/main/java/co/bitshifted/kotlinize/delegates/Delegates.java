/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.delegates;

import java.util.function.BiFunction;

public final class Delegates {

  private Delegates() {}

  public static <T> ObservableProperty<T> observable(BiFunction<T, T, Void> callback) {
    return observable(null, callback);
  }

  public static <T> ObservableProperty<T> observable(
      T initialValue, BiFunction<T, T, Void> callback) {
    return new ObservableProperty<>(initialValue, callback);
  }

  public static <T> VetoableProperty<T> vetoable(BiFunction<T, T, Boolean> callback) {
    return vetoable(null, callback);
  }

  public static <T> VetoableProperty<T> vetoable(
      T initialValue, BiFunction<T, T, Boolean> callback) {
    return new VetoableProperty<>(initialValue, callback);
  }
}
