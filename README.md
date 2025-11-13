Kotlinize
=========

Kotlinize is a small Java utility library that brings Kotlin-style functionality to Java projects.

Key features
- Null-safe calls, functionality similar to Kotlin's `elvis`  (`?.`) operator
- Implementations of Kotlin's standard library functions:
- Implementation of Kotlin scope functions: `let`, `also`,  `with`
- Delegate primitives: lazy, observable, vetoable
- Simplified try-with-resources via `use` function
- Implementation of Kotlin range functionality

## Installation

For Maven projects, add dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>co.bitshifted</groupId>
    <artifactId>kotlinize</artifactId>
    <version>latest version</version>
</dependency>
```
For Gradle projects, add the following to your `build.gradle`:

```groovy
implementation 'co.bitshifted:kotlinize:latest version'
```

## Code samples and documentation

* [Null-safe calls](/docs/null-safe.md)
* [Scope functions](/docs/scope-functions.md)
* [Standard library functions](/docs/stdlib-functions.md)
* `use` function
* Delegates
* Ranges

## License
- This project is distributed under the Mozilla Public License 2.0 (MPL-2.0). See the `LICENSE` file for full text.

## Contributing
- Feel free to open issues or pull requests on the upstream repository.

