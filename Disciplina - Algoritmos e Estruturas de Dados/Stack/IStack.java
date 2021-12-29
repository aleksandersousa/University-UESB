public interface IStack<T> {
  boolean isEmpty();
  boolean isFull();
  void clear();
  void push(T value);
  T pop();
  T peek();
  int getSize();
}
