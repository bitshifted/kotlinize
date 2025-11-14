# Kotlin `use` function

The `use` function provides a convenient way to work with `AutoCloseable` resources, ensuring that the resource is closed correctly after i
t has been used. It is inspired by Kotlin's standard library function `use` and serves a similar purpose to Java's 
try-with-resources statement, but in a more functional style.

## How it works

The `Usable.usable()` static factory method takes a `Supplier` that provides an `AutoCloseable` resource. This returns a `Usable` instance. You can then call the `use()` method on this instance, passing a `Function` that contains the code to execute with the resource.

The `use` method will:
1.  Acquire the resource from the supplier.
2.  Execute the function you provide, passing the resource as an argument.
3.  Close the resource automatically, even if an exception occurs within your function.
4.  Return the result of your function wrapped in a `NullAware` container.

## Basic Usage

Here is an example of how to use `Usable.use` to read from a resource that implements `AutoCloseable`.

First, let's define a simple resource:

```java
class MyResource implements AutoCloseable {
    public MyResource() {
        System.out.println("Resource created");
    }

    public String readData() {
        return "some data";
    }

    @Override
    public void close() {
        System.out.println("Resource closed");
    }
}
```

Now, we can use it with `Usable.use`:

```java
import static co.bitshifted.kotlinize.Usable.usable;

// ...

try {
    // The resource is created, used, and then closed automatically.
    var result = usable(() -> new MyResource())
        .use(resource -> resource.readData()).value();
} catch (Exception e) {
    e.printStackTrace();
}
```

**Output:**
```
Resource created
some data
Resource closed
```

Function `use` returns value wrapped in `NullAware` instance, so chained operations can be applied to it.

## Exception Handling

If an exception is thrown inside the `use` block, the resource is still closed correctly before the exception is propagated.

```java
try {
    usable(() -> new MyResource()).use(resource -> {
        System.out.println("Inside use block");
        throw new RuntimeException("Something went wrong");
    });
} catch (Exception e) {
    System.err.println("Caught exception: " + e.getMessage());
}
```

**Output:**
```
Resource created
Inside use block
Resource closed
Caught exception: Something went wrong
```

As you can see, `Resource closed` is printed before the exception is caught, demonstrating that the resource was properly managed. This makes `Usable.use` a safe and concise way to handle resources.
