/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize;

/**
 * Implementation of {@code Supplier} interface that accepts function blocks which  throw checked exception.
 *
 * @param <T> return type of supplier
 */
@FunctionalInterface
public interface ThrowableSupplier<T> {
  T get() throws Throwable;
}
