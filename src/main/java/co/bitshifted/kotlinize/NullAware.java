/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A utility class that provides Kotlin-like null safety features in Java. It aims to replicate the
 * following Kotlin features:
 *
 * <ul>
 *   <li>Safe call operator ({@code ?.}): Allows safe access to properties and methods of
 *       potentially null objects.
 *   <li>Elvis operator ({@code ?:}): Provides a way to return a default value if the underlying
 *       value is null.
 *   <li>{@code apply} function: Executes a block of code with the underlying value and returns the
 *       value itself.
 *   <li>{@code let} function: Executes a block of code with the underlying value if it is not null,
 *       returning the result of the block.
 * </ul>
 *
 * @param <T> the type of the underlying value
 */
public final class NullAware<T> {
  private final T value;

  private NullAware(T value) {
    this.value = value;
  }

  /**
   * Gets the underlying value.
   *
   * @return value of this object
   */
  public T value() {
    return value;
  }

  /**
   * Replicates Kotlin's {@code apply} functionality. Executes the provided block with the
   * underlying value and returns the value itself. Function block can alter the state of the
   * underlying value if it is mutable.
   *
   * @param block function to execute with the underlying value
   * @return the underlying value
   */
  public T apply(Consumer<T> block) {
    if (value == null) {
      return null;
    }
    block.accept(value);
    return value;
  }

  /**
   * Replicates Kotlin's {@code let} functionality. If underlying value is not null, executes the
   * provided block with the value. Otherwise, returns null.
   *
   * @param block function to execute with the underlying value
   * @return result of the provided block if value is not null, otherwise null
   * @param <R> type of the result
   */
  public <R> R let(Function<T, R> block) {
    if (value == null) {
      return null;
    }
    return block.apply(value);
  }

  /**
   * Replicates Kotlin's Elvis operator ({@code ?:}) functionality. If underlying value is null,
   * executes the provided block and returns its result.
   *
   * @param block function to execute if value is null
   * @return underlying value if not null, otherwise the result of the provided block
   */
  public T ifNull(Supplier<T> block) {
    if (value == null) {
      return block.get();
    }
    return value;
  }

  /**
   * Replicates Kotlin's {@code takeIf} functionality. If the underlying value is not null and the
   * provided predicate returns true, returns this {@code NullAware} instance. Otherwise, returns a
   * new {@code NullAware} instance with a null value.
   *
   * @param block predicate to test the underlying value
   * @return this NullAware instance if value is not null and predicate is true, otherwise a
   *     NullAware with null value
   */
  public NullAware<T> takeIf(Predicate<T> block) {
    if (value != null && block.test(value)) {
      return this;
    }
    return new NullAware<>(null);
  }

  /**
   * Replicates Kotlin's {@code takeUnless} functionality. If the underlying value is not null and
   * the provided predicate returns false, returns this {@code NullAware} instance. Otherwise,
   * returns a new {@code NullAware} instance with a null value.
   *
   * @param block predicate to test the underlying value
   * @return this NullAware instance if value is not null and predicate is false, otherwise a
   *     NullAware with null value
   */
  public NullAware<T> takeUnless(Predicate<T> block) {
    if (value != null && !block.test(value)) {
      return this;
    }
    return new NullAware<>(null);
  }

  /**
   * Creates a {@code NullAware} instance by executing the provided supplier. If a
   * NullPointerException is thrown during the execution, the underlying value is set to null.
   *
   * @param supplier function to provide the value
   * @return a NullAware instance containing the value or null
   * @param <T> type of the value
   */
  public static <T> NullAware<T> nullAware(Supplier<T> supplier) {
    try {
      return new NullAware<>(supplier.get());
    } catch (NullPointerException th) {
      return new NullAware<>(null);
    }
  }

  /**
   * Executes the provided supplier and returns its value. If a {@code NullPointerException} is
   * thrown during the execution, {@code null} is returned.
   *
   * @param supplier function to provide the value
   * @return the value provided by the supplier or null if supplier throws NullPointerException
   * @param <T> type of the value
   */
  public static <T> T nullSafe(Supplier<T> supplier) {
    return nullAware(supplier).value;
  }

  /**
   * Executes the provided supplier and returns its value. If a {@code NullPointerException} is
   * thrown during the execution, the provided default value is returned.
   *
   * @param supplier function to provide the value
   * @param defaultValue value to return if supplier throws NullPointerException, or value evaluates
   *     to null
   * @return the value provided by the supplier or the default value
   * @param <T> type of the value
   */
  public static <T> T nullSafe(Supplier<T> supplier, T defaultValue) {
    var output = nullAware(supplier).value;
    return output != null ? output : defaultValue;
  }
}
