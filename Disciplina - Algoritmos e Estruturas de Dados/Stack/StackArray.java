public class StackArray<T> implements IStack<T> {
  private T[] store;
  private int size = 0;

  @SuppressWarnings("unchecked")
  public StackArray(int capacity) {
    store = (T[]) new Object[capacity];
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }
  
  @Override
  public boolean isFull() {
    return size == store.length;
  }

  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      store[i] = null;
    }
    size = 0;
  }

  @Override
  public void push(T value) {
    if (!isFull()) {
      store[size++] = value;
    }
  }

  @Override
  public T pop() {
    return !isEmpty() ? (T) store[--size] : null;
  }

  @Override
  public T peek() {
    return !isEmpty() ? (T) store[size - 1] : null;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("[");

    for (int i = size - 1; i >= 0; i--) {
      strBuilder.append(store[i]);

      if (i > 0) {
        strBuilder.append(", ");
      }
    }

    strBuilder.append("]");

    return strBuilder.toString();
  }
}
