package co.bitshifted.kotlinize;

import java.util.function.Function;

/**
 * A simple Result class to represent success or failure of an operation.
 * Similar to Kotlin's Result type.
 *
 * @param <T> the type of the successful result value
 */
public final class Result<T> {

    private final T value;
    private final Throwable exception;
    private final boolean success;

    /**
     * Creates a successful Result with the given value.
     *
     * @param value the successful result value
     */
    public Result(T value) {
        this.value = value;
        this.exception = null;
        this.success = true;
    }

    /**
     * Creates a failed Result with the given exception.
     *
     * @param exception the exception representing the failure
     */
    public Result(Throwable exception) {
        this.value = null;
        this.exception = exception;
        this.success = false;
    }

    /**
     * Returns true if the result is successful, false if it is a failure.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns the value if the result is successful, or null if it is a failure.
     */
    public T getOrNull() {
        if(!success) {
            return null;
        }
        return value;
    }

    /**
     * Returns the exception if the result is a failure, or null if it is successful.
     */
    public Throwable exceptionOrNull() {
        if(success) {
            return null;
        }
        return exception;
    }

    /**
     * Folds the result into a single value by applying the appropriate function
     * based on whether the result is a success or a failure.
     *
     * @param onSuccess function to apply if the result is successful
     * @param onFailure function to apply if the result is a failure
     * @return the result of applying the appropriate function
     * @param <R> the type of the resulting value
     */
    public <R> R fold(Function<T,R> onSuccess, Function<Throwable,R> onFailure) {
        if(success) {
            return onSuccess.apply(value);
        } else {
            return onFailure.apply(exception);
        }
    }
}
