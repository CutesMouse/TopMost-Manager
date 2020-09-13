package com.cutesmouse.tmm;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof ListData)) return false;
        return display.equals(((ListData) o).display);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t, display);
    }

    @Override
    public String toString() {
        return display;
    }
}
