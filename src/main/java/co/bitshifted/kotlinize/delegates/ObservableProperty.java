package co.bitshifted.kotlinize.delegates;

import co.bitshifted.kotlinize.Functions;

import java.util.function.BiFunction;

public class ObservableProperty<T> {

    private T value;
    private final BiFunction<T, T, Void> callback;

    public ObservableProperty(T initialValue, BiFunction<T, T, Void> callback) {
        if(callback == null) {
            Functions.error("Callback cannot be null");
        }
        this.value = initialValue;
        this.callback = callback;
    }

    public T value() {
        return value;
    }

    public void value(T newValue) {
        T oldValue = this.value;
        this.value = newValue;
        callback.apply(oldValue, newValue);
    }
}
