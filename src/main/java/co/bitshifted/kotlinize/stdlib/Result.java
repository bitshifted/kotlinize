/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.stdlib;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A simple Result class to represent success or failure of an operation. Similar to Kotlin's Result
 * type.
 *
 * @param <T> the type of the successful result value
 */
public final class Result<T> {

  private final T value;
  private final Throwable exception;
  private final boolean success;

  /**
   * Creates a successful Result with the given value.
   *
   * @param value the successful result value
   */
  public Result(T value) {
    this.value = value;
    this.exception = null;
    this.success = true;
  }

  /**
   * Creates a failed Result with the given exception.
   *
   * @param exception the exception representing the failure
   */
  public Result(Throwable exception) {
    this.value = null;
    this.exception = exception;
    this.success = false;
  }

  /** Returns true if the result is successful, false if it is a failure. */
  public boolean isSuccess() {
    return success;
  }

  /** Returns the value if the result is successful, or null if it is a failure. */
  public T getOrNull() {
    if (!success) {
      return null;
    }
    return value;
  }

  /**
   * Returns the value if the result is successful, or the provided default value if it is a
   * failure.
   *
   * @param defaultValue the default value to return if the result is a failure
   * @return the result value or the default value
   */
  public T getOrDefault(T defaultValue) {
    if (!success) {
      return defaultValue;
    }
    return value;
  }

  /**
   * Returns the value if the result is successful, or the result of the provided function if it is
   * a failure.
   *
   * @param onFailure function to compute the value if the result is a failure
   * @return the result value or the computed value from the function
   */
  public T getOrElse(Function<Throwable, T> onFailure) {
    if (!success) {
      return onFailure.apply(exception);
    }
    return value;
  }

  /**
   * Returns the value if the result is successful, or throws the exception if it is a failure.
   *
   * @return the result value
   * @throws Throwable the exception if the result is a failure
   */
  public T getOrThrow() throws Throwable {
    if (!success) {
      throw exception;
    }
    return value;
  }

  /**
   * Transforms the successful result value using the provided function, or propagates the failure
   * if the result is a failure.
   *
   * @param transform function to transform the successful result value
   * @return a new Result containing the transformed value or the original exception
   * @param <R> the type of the transformed result value
   */
  public <R> Result<R> map(Function<T, R> transform) {
    if (success) {
      return new Result<>(transform.apply(value));
    } else {
      return new Result<>(exception);
    }
  }

  /**
   * Executes the provided action if the result is successful. Returns the original {@code Result}
   * unchanged.
   *
   * @param action action to execute with the successful result value
   * @return the original Result
   */
  public Result<T> onFailure(Consumer<Throwable> action) {
    if (!success) {
      action.accept(exception);
    }
    return this;
  }

  /**
   * Executes the provided action if the result is successful. Returns the original {@code Result}
   * unchanged.
   *
   * @param action action to execute with the successful result value
   * @return the original Result
   */
  public Result<T> onSuccess(Consumer<T> action) {
    if (success) {
      action.accept(value);
    }
    return this;
  }

  /** Returns the exception if the result is a failure, or null if it is successful. */
  public Throwable exceptionOrNull() {
    if (success) {
      return null;
    }
    return exception;
  }

  /**
   * Folds the result into a single value by applying the appropriate function based on whether the
   * result is a success or a failure.
   *
   * @param onSuccess function to apply if the result is successful
   * @param onFailure function to apply if the result is a failure
   * @return the result of applying the appropriate function
   * @param <R> the type of the resulting value
   */
  public <R> R fold(Function<T, R> onSuccess, Function<Throwable, R> onFailure) {
    if (success) {
      return onSuccess.apply(value);
    } else {
      return onFailure.apply(exception);
    }
  }
}
