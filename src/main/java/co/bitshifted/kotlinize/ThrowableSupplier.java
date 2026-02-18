package co.bitshifted.kotlinize;

@FunctionalInterface
public interface ThrowableSupplier<T> {
  T get() throws Throwable;
}
