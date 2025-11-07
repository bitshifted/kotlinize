/*
 * Copyright © 2025, Bitshift <https://bitshifted.co>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package co.bitshifted.kotlinize.range;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * A base class for ranges.
 *
 * @param <T> the type of the range elements
 */
public sealed class Range<T> implements Iterable<T>
    permits ByteRange, ShortRange, IntRange, LongRange, CharRange {

  protected final T start;
  protected final T end;
  protected final T step;

  protected final List<T> elements = new ArrayList<>();

  /**
   * Creates a new Range. Subclasses should populate the elements list.
   *
   * @param start start value
   * @param endInclusive end value (inclusive)
   * @param step step value
   */
  protected Range(T start, T endInclusive, T step) {
    this.start = start;
    this.end = endInclusive;
    this.step = step;
  }

  /**
   * Gets the start value of the range.
   *
   * @return the start value
   */
  public T start() {
    return start;
  }

  /**
   * Gets the end value of the range.
   *
   * @return the end value
   */
  public T end() {
    return end;
  }

  /**
   * Gets the first value of the range.
   *
   * @return the first value
   */
  public T first() {
    return elements.get(0);
  }

  /**
   * Gets the last value of the range.
   *
   * @return the last value
   */
  public T last() {
    return elements.get(elements.size() - 1);
  }

  /**
   * Checks if any element in the range matches the given predicate.
   *
   * @param predicate the predicate to test elements
   * @return true if any element matches, false otherwise
   */
  public boolean any(Predicate<T> predicate) {
    for (T element : elements) {
      if (predicate.test(element)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if all elements in the range match the given predicate.
   *
   * @param predicate the predicate to test elements
   * @return true if all elements match, false otherwise
   */
  public boolean all(Predicate<T> predicate) {
    for (T element : elements) {
      if (!predicate.test(element)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the elements of the range as an Iterable.
   *
   * @return an Iterable of the range elements
   */
  public Iterable<T> asIterable() {
    return elements;
  }

  /**
   * Checks if the range contains the specified value.
   *
   * @param value the value to check
   * @return true if the value is in the range, false otherwise
   */
  public boolean contains(T value) {
    return elements.contains(value);
  }

  /**
   * Returns the count of elements in the range.
   *
   * @return the number of elements in the range
   */
  public int count() {
    return elements.size();
  }

  /**
   * Returns a list of distinct elements in the range.
   *
   * @return a List of distinct elements
   */
  public List<T> distinct() {
    return elements.stream().distinct().toList();
  }

  /**
   * Returns an iterator over the elements in the range.
   *
   * @return an Iterator of the range elements
   */
  @Override
  public Iterator<T> iterator() {
    return elements.iterator();
  }
}
