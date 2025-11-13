# Scope functions

Kotlinize provides implementations of Kotlin's scope functions: `let`, `also`, and `with`. These functions allow you to 
execute a block of code within the context of an object, providing a more concise and readable way to work with that object.

Due to Kotlin's null-safety features, these scope functions are often used in conjunction with null-safe calls. Kotlinize 
enables you to use these scope functions in Java, even when working with potentially null values, by using `Nullaware` class.

## `let` function

The `let` function allows you to execute a block of code with the object as its argument. It is often used for null-safe calls.

```java
import static co.bitshifted.kotlinize.Nullaware.nullAware;

var cityNameLength = nullAware(() -> person.workAddress().city().name())
                        .let(name -> name.length());
```

Function `let` can return the same type or a different type as it's context object, depending on the logic inside the block.

## `apply` function

The `apply` function allows you to configure an object within a block of code. The object is available as `this` inside the block.

```java
import static co.bitshifted.kotlinize.NullAware.nullAware;

class Coordinates {
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

var coords = nullAware(() -> new Coordinates())
                .apply(c -> {
                    c.setLatitude(37.7749);
                    c.setLongitude(-122.4194);
                });
```

This code creates a new `Coordinates` object and configures its properties within the `apply` block. It is useful for initializing or mutating  
objects in a concise way.
