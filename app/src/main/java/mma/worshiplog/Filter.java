package mma.worshiplog;

interface Filter<T,E> {
    public boolean isMatched(T object, E text);
}
