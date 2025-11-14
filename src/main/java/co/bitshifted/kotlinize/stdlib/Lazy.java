/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.stdlib;

import java.util.function.Supplier;

/**
 * A simple thread-safe lazy holder. The initializer will be executed at most once (unless the
 * initializer throws), and the computed value will be returned on subsequent calls to {@link
 * #value()}.
 *
 * <p>This implementation uses double-checked locking with volatile fields to avoid unnecessary
 * synchronization after initialization.
 *
 * <p>Note: if the {@code initializer} throws an exception, the exception is propagated and the
 * instance remains uninitialized — subsequent calls will attempt initialization again.
 *
 * @param <T> the value type
 */
public final class Lazy<T> {
  private volatile T value;
  private volatile boolean initialized = false;
  private final Supplier<T> initializer;
  private final Object lock = new Object();

  /**
   * Creates a new {@code Lazy} instance.
   *
   * @param initializer supplier that provides value for initializer
   */
  public Lazy(Supplier<T> initializer) {
    if (initializer == null) {
      throw new NullPointerException("initializer must not be null");
    }
    this.initializer = initializer;
  }

  /**
   * Returns the lazily-initialized value. If not yet initialized, the {@code initializer} is
   * invoked. This method is thread-safe.
   *
   * @return the initialized value (may be null if initializer returns null)
   */
  public T value() {
    if (!initialized) {
      synchronized (lock) {
        if (!initialized) {
          T computed = initializer.get();
          value = computed;
          // Make write to value visible before setting initialized
          initialized = true;
        }
      }
    }
    return value;
  }

  /**
   * Returns true if the value has already been initialized. This reads a volatile field so it is
   * thread-safe.
   *
   * @return {@code true} if object is initialized, {@code false} otherwise
   */
  public boolean isInitialized() {
    return initialized;
  }
}
