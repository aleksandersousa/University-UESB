public interface IClassifiedList<T> {
  <U extends Comparable<U>> T search(U object);
  T get(int i);
  ICursor<T> getPosition(T object);
  <U extends Comparable<U>> void add(U object);
  void remove(T object);
  <U extends Comparable<U>> boolean contains(U object);
  boolean isFull();
  boolean isEmpty();
}
