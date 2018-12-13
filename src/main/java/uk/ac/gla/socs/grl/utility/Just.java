package uk.ac.gla.socs.grl.utility;

public final class Just<T> extends Maybe<T> {
    private T value;

    public Just(T value) {
        this.value = value;
    }

    public T getValue(){ return this.value; }
}
