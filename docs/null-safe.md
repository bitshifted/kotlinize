# Null safety in Java with Kotlinize

Kotlinize provides utilities to handle null safety in Java, inspired by Kotlin's null-safe calls. This allows you to work with potentially null values without running into `NullPointerException`s.
Since Java does not have native support for null-safe calls like Kotlin, Kotlinize uses wrapper methods to achieve similar functionality. 
This might make the syntax awkward and clunky compared to Kotlin, but it still provides a way to safely handle null values in Java.

Class `Nullaware` is a thin wrapper around a potentially null value. It provides methods to safely operate on the value if it is not null.

## Safely accessing nested nullable fields

Let's say you have a class `Person` with a nullable field `address`, and you want to safely access the `city` of the `address`.

```java
public record Person(String firstName, String lastName, 
                     Address homeAddress, Address workAddress) {}

public record Address(String street, City city, String country) {}

public record City(String name, String zipCode) {}

Person person = new Person("John", "Doe", 
                           new Address("123 Main St", 
                                       new City("Springfield", "12345"), 
                                       "USA"), 
                           null);
```

Trying to access the city name directly could lead to a `NullPointerException` if `workAddress` is null:

```java
// This will throw NullPointerException
String cityName = person.workAddress().city().name();
```

To avoid this, you can use method `nullsafe` from `Nullaware` class to safely navigate through the potentially null fields:

```java
import static co.bitshifted.kotlinize.Nullaware.nullSafe;   

var cityName = nullSafe(() -> person.workAddress().city().name());
```

If any of the fields in the chain are null, `cityName` will be set to `null` instead of throwing an exception.
Method `nullsafe` takes a `Supplier` argument that returns the desired value. Supplier is used instead of a direct value to 
defer the evaluation until inside the `nullsafe` method, allowing it to catch any null references along the way.

Method `nullsafe` can also provide default values if the result is null. 

```java
import static co.bitshifted.kotlinize.Nullaware.nullSafe;

var cityName = nullSafe(() -> person.workAddress().city().name(), "Unknown City");
```

## Elvis operator

Kotlin's elvis operator (`?:`) allows you to provide a default value if an expression evaluates to null. Kotlinize provides a 
similar functionality through the `orElse` method in the `Nullaware` class. In order to use it, you first need to wrap the 
potentially null value using `Nullaware.nullaware`.

```java
import static co.bitshifted.kotlinize.Nullaware.nullAware;

var cityName = nullAware(person.workAddress().city().name())
                    .orElse(() -> "Unknown City");
```

This allows you to provide a default value  if the city name is null. Method `orElse` takes a `Supplier` argument 
so it can run custom logic to provide the default value if needed.

## Conditional execution 

Class `Nullaware` provides methods `takeIfPresent` and `takeUnless` to return the instance only if certain conditions are met.

```java
import static co.bitshifted.kotlinize.Nullaware.nullAware;

var cityName = nullAware(person.homeAddress().city().name())
                    .takeI(name -> name.startsWith("S"));
```

This code will return the city name only if it starts with "S". If the city name is null or does not start with "S", the result will be null.
Result is wrapped in a `Nullaware` instance, so you can chain further calls if needed.

Method `takeUnless` works similarly, but it returns the instance only if the condition is false.

```java
import static co.bitshifted.kotlinize.Nullaware.nullAware;      

var cityName = nullAware(person.homeAddress().city().name())
                    .takeUnless(name -> name.length() < 5);
```



