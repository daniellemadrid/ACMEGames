public interface Iterator<T> {
    void reset();
    boolean hasNext();
    T next();
}
