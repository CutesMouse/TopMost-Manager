package com.cutesmouse.tmm;

public class ListData<T> {
    private T t;
    private String display;
    public ListData(T item, String displayName) {
        display = displayName;
        t = item;
    }

    public T getItem() {
        return t;
    }

    @Override
    public String toString() {
        return display;
    }
}
