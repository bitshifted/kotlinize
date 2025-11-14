# Delegated Properties and Lazy Initialization

This library provides implementations of common Kotlin delegated properties and the `lazy` function for use in Java. These utilities help you write more expressive and maintainable code by abstracting common patterns.

## `observable`

The `observable` delegate allows you to execute a callback function whenever the value of a property changes. This is useful for triggering side effects, such as updating a UI or logging changes.

### How it works

You create an `ObservableProperty` by providing an initial value and a callback. The callback receives the old and new values of the property, allowing you to react to the change.

### Usage

Here's an example of how to use `observable`:

```java
import co.bitshifted.kotlinize.delegates.Delegates.*;
import co.bitshifted.kotlinize.delegates.ObservableProperty;

public class User {
    private final ObservableProperty<String> name;

    public User(String initialName) {
        this.name = observable(initialName, (oldValue, newValue) -> {
            System.out.println("Name changed from '" + oldValue + "' to '" + newValue + "'");
            return null; // The return value is ignored
        });
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String newName) {
        name.setValue(newName);
    }

    public static void main(String[] args) {
        User user = new User("John Doe");
        user.setName("Jane Doe");
        user.setName("Jane Smith");
    }
}
```

**Output:**
```
Name changed from 'John Doe' to 'Jane Doe'
Name changed from 'Jane Doe' to 'Jane Smith'
```

## `vetoable`

The `vetoable` delegate is similar to `observable`, but it gives you the power to reject a new value before it is assigned. The callback for a `vetoable` property must return a `boolean`. If the callback returns `true`, the new value is accepted. If it returns `false`, the change is rejected, and the property retains its old value.

### How it works

You create a `VetoableProperty` with an initial value and a callback that validates the new value. This is useful for enforcing constraints on your properties.

### Usage

Here's an example where we only accept non-negative numbers:

```java
import co.bitshifted.kotlinize.delegates.Delegates.*;
import co.bitshifted.kotlinize.delegates.VetoableProperty;

public class Configuration {
    private final VetoableProperty<Integer> port;

    public Configuration(int initialPort) {
        this.port = vetoable(initialPort, (oldValue, newValue) -> {
            if (newValue >= 0) {
                System.out.println("Accepting new port: " + newValue);
                return true; // Accept the new value
            } else {
                System.out.println("Rejecting invalid port: " + newValue);
                return false; // Reject the new value
            }
        });
    }

    public int getPort() {
        return port.getValue();
    }

    public void setPort(int newPort) {
        port.setValue(newPort);
    }

    public static void main(String[] args) {
        Configuration config = new Configuration(8080);
        System.out.println("Current port: " + config.getPort());

        config.setPort(9000);
        System.out.println("Current port: " + config.getPort());

        config.setPort(-1);
        System.out.println("Current port: " + config.getPort());
    }
}
```

**Output:**
```
Current port: 8080
Accepting new port: 9000
Current port: 9000
Rejecting invalid port: -1
Current port: 9000
```

## `lazy`

The `lazy` function provides a way to delay the initialization of a property until it is accessed for the first time. The value is computed only once and then cached for all subsequent accesses. This is useful for expensive computations or for resources that should only be initialized when needed.

### How it works

You provide a `Supplier` (a function that takes no arguments and returns a value) to the `lazy` function. The first time you call `getValue()` on the returned `Lazy` object, the supplier is executed, and its result is stored. Subsequent calls to `getValue()` will return the cached result without re-executing the supplier.

### Usage

Here's an example of a property that is expensive to initialize:

```java
import co.bitshifted.kotlinize.Functions;
import co.bitshifted.kotlinize.Functions.lazy;
import co.bitshifted.kotlinize.stdlib.Lazy;

public class Application {
    private final Lazy<String> configuration;

    public Application() {
        this.configuration = Functions.lazy(() -> {
            System.out.println("Loading configuration...");
            // Simulate an expensive operation
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Loaded Configuration Data";
        });
    }

    public String getConfiguration() {
        return configuration.getValue();
    }

    public static void main(String[] args) {
        Application app = new Application();
        System.out.println("Application created.");

        // First access to configuration
        System.out.println(app.getConfiguration());

        // Second access to configuration
        System.out.println(app.getConfiguration());
    }
}
```

**Output:**
```
Application created.
Loading configuration...
Loaded Configuration Data
Loaded Configuration Data
```
As you can see, "Loading configuration..." is printed only once, demonstrating that the initialization logic was executed only on the first access.
