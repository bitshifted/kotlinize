package co.bitshifted.kotlinize;

import co.bitshifted.kotlinize.stdlib.Lazy;
import co.bitshifted.kotlinize.stdlib.Result;

import java.lang.reflect.Array;
import java.util.function.Supplier;

/**
 * A utility class that provides implementations of Kotlin standard library functions in Java. Provided functions include:
 *
 * <ul>
 *     <li>arrayOf: Creates an array from the given elements, including per-type variants</li>
 *     <li>lazy: create lazy initialized instance</li>
 *     <li>repeat: Repeats a block of code a specified number of times</li>
 *     <li>require: Checks a condition and throws IllegalArgumentException if false</li>
 *     <li>requireNonNull: Ensures an object is not null, throwing IllegalArgumentException if it is</li>
 *     <li>runCatching: Executes a block of code and captures any thrown exceptions</li>
 *     <li>TODOfunction - throws UnsupportedOperationException - </li>
 * </ul>
 */
public final class Functions {

    private Functions() {

    }

    /**
     * Creates an array containing the given elements.
     *
     * @param members the elements to include in the array
     * @param <T> the type of the elements
     * @return an array containing the given elements
     */
    public static <T> T[] arrayOf(T... members) {
        return members;
    }

    /**
     * Creates an array of the specified size, initialized with null values.
     *
     * @param size the size of the array to create
     * @param clazz the type of the array elements
     * @param <T> the type of the elements in the array
     * @return an array of the specified size, filled with nulls
     */
    public static <T> T[] arrayOfNulls(int size, Class<T> clazz) {
        return (T[]) Array.newInstance(clazz, size);
    }

    /**
     * Creates a boolean array containing the given elements.
     *
     * @param members the boolean elements to include in the array
     * @return a boolean array containing the given elements
     */
    public static boolean[] booleanArrayOf(boolean... members) {
        return members;
    }
    /**
     * Creates a Boolean array containing the given elements.
     *
     * @param members the Boolean elements to include in the array
     * @return a Boolean array containing the given elements
     */
    public static Boolean[] boxedBooleanArrayOf(Boolean... members) {
        return members;
    }

    /**
     * Creates a byte array containing the given elements.
     *
     * @param members the byte elements to include in the array
     * @return a byte array containing the given elements
     */
    public static byte[] byteArrayOf(byte... members) {
        return members;
    }

    /**
     * Creates a Byte array containing the given elements.
     *
     * @param members the Byte elements to include in the array
     * @return a Byte array containing the given elements
     */
    public static Byte[] boxedByteArrayOf(Byte... members) {
        return members;
    }

    /**
     * Creates a char array containing the given elements.
     *
     * @param members the char elements to include in the array
     * @return a char array containing the given elements
     */
    public static char[] charArrayOf(char... members) {
        return members;
    }

    /**
     * Creates a Character array containing the given elements.
     *
     * @param members the Character elements to include in the array
     * @return a Character array containing the given elements
     */
    public static Character[] boxedCharArrayOf(Character... members) {
        return members;
    }

    /**
     * Creates a short array containing the given elements.
     *
     * @param members the short elements to include in the array
     * @return a short array containing the given elements
     */
    public static short[] shortArrayOf(short... members) {
        return members;
    }

    /**
     * Creates a Short array containing the given elements.
     *
     * @param members the Short elements to include in the array
     * @return a Short array containing the given elements
     */
    public static Short[] boxedShortArrayOf(Short... members) {
        return members;
    }

    /**
     * Creates an int array containing the given elements.
     *
     * @param members the int elements to include in the array
     * @return an int array containing the given elements
     */
    public static int[] intArrayOf(int... members) {
        return members;
    }

    /**
     * Creates an Integer array containing the given elements.
     *
     * @param members the Integer elements to include in the array
     * @return an Integer array containing the given elements
     */
    public static Integer[] boxedIntArrayOf(Integer... members) {
        return members;
    }

    /**
     * Creates a long array containing the given elements.
     *
     * @param members the long elements to include in the array
     * @return a long array containing the given elements
     */
    public static long[] longArrayOf(long... members) {
        return members;
    }

    /**
     * Creates a Long array containing the given elements.
     *
     * @param members the Long elements to include in the array
     * @return a Long array containing the given elements
     */
    public static Long[] boxedLongArrayOf(Long... members) {
        return members;
    }

    /**
     * Creates a float array containing the given elements.
     *
     * @param members the float elements to include in the array
     * @return a float array containing the given elements
     */
    public static float[] floatArrayOf(float... members) {
        return members;
    }

    /**
     * Creates a Float array containing the given elements.
     *
     * @param members the Float elements to include in the array
     * @return a Float array containing the given elements
     */
    public static Float[] boxedFloatArrayOf(Float... members) {
        return members;
    }

    /**
     * Creates a double array containing the given elements.
     *
     * @param members the double elements to include in the array
     * @return a double array containing the given elements
     */
    public static double[] doubleArrayOf(double... members) {
        return members;
    }

    /**
     * Creates a Double array containing the given elements.
     *
     * @param members the Double elements to include in the array
     * @return a Double array containing the given elements
     */
    public static Double[] boxedDoubleArrayOf(Double... members) {
        return members;
    }

    /**
     * Throws an IllegalStateException with the provided message.
     *
     * @param message the message for the exception
     * @throws IllegalStateException always thrown with the provided message
     */
    public static void error(String message) {
        throw new IllegalStateException(message);
    }

    /**
     * Creates a lazy-initialized value using the provided initializer function.
     *
     * @param initializer the function to initialize the value
     * @param <T> the type of the value
     * @return a Lazy instance that initializes the value when first accessed
     */
    public static <T> Lazy<T> lazy(Supplier<T> initializer) {
        return new Lazy<>(initializer);
    }

    /**
     * Repeats the given block of code a specified number of times.
     *
     * @param times the number of times to repeat the block
     * @param block the block of code to execute
     */
    public static void repeat(int times, Runnable block) {
        for(int i = 0; i < times; i++) {
            block.run();
        }
    }

    /**
     * Checks the given condition and throws an {@code IllegalArgumentException} if the condition is false.
     *
     * @param condition the condition to check
     * @throws IllegalArgumentException if the condition is false
     */
    public static void require(boolean condition) {
        require(condition, "Requirement failed");
    }

    /**
     * Checks the given condition and throws an {@code IllegalArgumentException} with the provided message
     * if the condition is false.
     *
     * @param condition the condition to check
     * @param message the message for the exception if the condition is false
     * @throws IllegalArgumentException if the condition is false
     */
    public static void require(boolean condition, String message) {
        if(!condition) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Ensures the provided value is not null and returns it; throws IllegalArgumentException with the
     * provided message if it is null.
     *
     * @param value value to check for null
     * @param message exception message if null
     * @param <T> type of the value
     * @return the non-null value
     * @throws IllegalArgumentException if value is null
     */
    public static <T> T requireNotNull(T value, String message) {
        if(value == null) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    /**
     * Ensures the provided value is not null and returns it; throws IllegalArgumentException with a default
     * message if it is null.
     *
     * @param value value to check for null
     * @param <T> type of the value
     * @return the non-null value
     * @throws IllegalArgumentException if value is null
     */
    public static <T> T requireNotNull(T value) {
        return requireNotNull(value, "Required value was null");
    }


    /**
     * Executes the given block of code and returns a {@code Result} representing either the successful result
     * or any exception that was thrown during execution.
     *
     * @param block the block of code to execute
     * @param <T> the type of the successful result
     * @return a Result containing either the successful result or the thrown exception
     */
    public static <T> Result<T> runCatching(Supplier<T> block) {
        try {
            return new Result<>(block.get());
        } catch(Throwable th) {
            return new Result<>(th);
        }
    }

    /**
     * Placeholder method indicating that the functionality is not yet implemented.
     *
     * @throws UnsupportedOperationException always thrown to indicate unimplemented functionality
     */
    public static void TODO() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
