package co.bitshifted.kotlinize.delegates;

import co.bitshifted.kotlinize.Functions;

import java.util.function.BiFunction;

public class VetoableProperty<T> {

    private T value;
    private final BiFunction<T, T, Boolean> callback;

    public VetoableProperty(T initialValue, BiFunction<T, T, Boolean> callback) {
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
        var allowed = callback.apply(this.value, newValue);
        if(allowed){
            this.value= newValue;
        }
    }
}
