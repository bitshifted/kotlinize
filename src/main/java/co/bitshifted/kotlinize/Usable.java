/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize;

import static co.bitshifted.kotlinize.NullAware.*;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A utility class that manages the lifecycle of AutoCloseable resources, ensuring they are properly
 * closed after use. It provides a method to use the resource within a block of code, similar to
 * Kotlin's {@code use} function.
 *
 * @param <T> the type of the AutoCloseable resource
 */
public class Usable<T extends AutoCloseable> {

  private final T resource;

  private Usable(T resource) {
    this.resource = resource;
  }

  /**
   * Executes the provided block of code with the AutoCloseable resource, ensuring that the resource
   * is closed after the block is executed, regardless of whether the block completes normally or
   * throws an exception.
   *
   * @param block function to execute with the AutoCloseable resource
   * @return a NullAware wrapping the result of the block execution
   * @throws Exception if the block throws an exception
   * @param <R> type of the result produced by the block
   */
  public <R> NullAware<R> use(Function<T, R> block) throws RuntimeException {
    try (T res = resource) {
      return nullAware(() -> block.apply(res));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Creates a {@code Usable} instance for the given {@code AutoCloseable} resource.
   *
   * @param supplier supplier that provides the AutoCloseable resource
   * @return a Usable instance managing the provided resource
   * @param <T> the type of the AutoCloseable resource
   */
  public static <T extends AutoCloseable> Usable<T> usable(Supplier<T> supplier) {
    return new Usable<>(supplier.get());
  }
}
