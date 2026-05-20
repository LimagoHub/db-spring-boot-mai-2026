package de.db.webapp;

public interface Stapel<T> {

    void push(T obj);
    T pop();
    boolean isEmpty();
}
