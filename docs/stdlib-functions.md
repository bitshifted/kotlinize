# Standard library functions

Kotlinize provides implementations of several Kotlin standard library functions, allowing you to use familiar functional programming

## `arrayOf`

Kotlin's `arrayOf` function creates an array containing the specified elements. Kotlinize provides a similar function for Java.

```java
import static co.bitshifted.kotlinize.Functions.arrayOf;

// creates array of integers with specified elements
var numbers = arrayOf(1, 2, 3, 4, 5);
```

## `arrayOfNulls`
Kotlin's `arrayOfNulls` function creates an array of a specified size, initialized with null values. Kotlinize provides a similar function for Java.

```java
import static co.bitshifted.kotlinize.Functions.arrayOfNulls;

// creates an array of 5 null Strings
var strings = arrayOfNulls(5, String.class);
```

## `error
Kotlin's `error` function throws an `IllegalStateException` with the provided message. Kotlinize provides a similar function for Java.

```java
import static co.bitshifted.kotlinize.Functions.error;

// throws an IllegalStateException with the specified message
error("An unexpected error occurred");
```

## `lazy`
Kotlin's `lazy` function creates a lazy-initialized property. This is the property whose value is computed only when it is 
accessed for the first time. Kotlinize provides a similar function for Java.

```java
import static co.bitshifted.kotlinize.Functions.lazy;
import co.bitshifted.kotlinize.Lazy;
import java.util.UUID;

// creates a lazy-initialized UUID
Lazy<UUID> lazyUuid = lazy(() -> UUID.randomUUID());
// the UUID will be generated only when first accessed
UUID uuid = lazyUuid.get();
```

## `repeat`
Kotlin's `repeat` function executes a given block of code a specified number of times. Kotlinize provides a similar function for Java.
```java
import static co.bitshifted.kotlinize.Functions.repeat;
// prints "Hello, World!" 5 times
repeat(5, () -> System.out.println("Hello, World!"));
```

## `runCatching`
Kotlin's `runCatching` function executes a block of code and catches any exceptions that occur, returning a `Result`
object that represents either a success or failure. Kotlinize provides a similar function for Java.

Result class is a simple container that holds either a successful value or an exception and can be used to handle errors in a 
functional style. This  example uses `fold` method to handle both success and failure cases.

```java
import static co.bitshifted.kotlinize.Functions.runCatching;
import co.bitshifted.kotlinize.Result;

var successResult = runCatching(() -> testFunction(5))
        .fold(
                value -> "Success: " + value,
                ex -> "Error: " + ex.getMessage()
        );
assertEquals("Success: 10", successResult);
```

