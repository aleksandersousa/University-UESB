public interface IClassifiedLinkedList<T> {
  T search(T object);
  T get(int i);
  ICursor<T> getPosition(T object);
  void add(T object);
  void remove(T object);
  boolean contains(T object);
  boolean isFull();
  boolean isEmpty();
}
