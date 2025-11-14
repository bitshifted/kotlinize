# Ranges

The `Range` classes in this library provide a way to create and work with sequences of values, similar to Kotlin's ranges. You can create ranges for different numeric types, as well as for characters.

## Integer Range

An `IntRange` represents a sequence of integers. You can create a range by specifying a start and an end value. The range is inclusive of the end value.

### Creating an Integer Range

To create a range of integers, you instantiate the `IntRange` class with the start and end values.

```java
import co.bitshifted.kotlinize.range.IntRange;

// ...

// Create a range from 1 to 5 (inclusive)
IntRange range = new IntRange(1, 5);
```

### Iterating Over a Range

Ranges are iterable, so you can use them in a for-each loop.

```java
IntRange range = new IntRange(1, 5);
for (int i : range) {
    System.out.print(i + " ");
}
// Output: 1 2 3 4 5
```

### Using a Custom Step

You can also specify a step value to skip elements in the range.

```java
IntRange range = new IntRange(1, 10, 2);
for (int i : range) {
    System.out.print(i + " ");
}
// Output: 1 3 5 7 9
```

### Creating a Decreasing Range

To create a range that counts down, ensure the start value is greater than the end value.

```java
IntRange downRange = new IntRange(5, 1);
for (int i : downRange) {
    System.out.print(i + " ");
}
// Output: 5 4 3 2 1
```

You can also use a custom step with a decreasing range.

```java
IntRange downRangeWithStep = new IntRange(10, 1, 3);
for (int i : downRangeWithStep) {
    System.out.print(i + " ");
}
// Output: 10 7 4
```

## Other Range Types

Besides `IntRange`, the library also provides:
- `ByteRange`
- `ShortRange`
- `LongRange`
- `CharRange`

These classes work in a similar way to `IntRange`, allowing you to create ranges of different data types.
