public class QueueArray<T> implements IQueue<T> {
  private T[] store;
  private int head, tail, size;

  @SuppressWarnings("unchecked")
  public QueueArray(int capacity) {
    store = (T[]) new Object[capacity];
    tail = capacity - 1;
    head = 0;
    size = 0;
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
    while (size > 0) {
      store[head] = null;
      head++;

      if (head == store.length) {
        head = 0;
      }

      size--;
    }
  }

  @Override
  public void queue(T object) {
    if (isFull()) {
      System.out.println("limite excedido!");
      return;
    }

    tail++;

    if (tail == store.length) {
      tail = 0;
    }

    store[tail] = object;
    size++;
  }

  @Override
  public T dequeue() {
    if (isEmpty()) {
      return null;
    }

    T result = store[head];
    store[head] = null;
    head++;

    if (head == store.length) {
      head = 0;
    }

    size--;

    return result;
  }

  @Override
  public T peek() {
    return !isEmpty() ? store[head] : null;
  }

  @Override
  public int getSize() {
    return this.size;
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("[");

    for (int i = 0, j = head; i < size; i++, j++) {
      strBuilder.append(store[j]);

      if (i < size - 1) {
        strBuilder.append(", ");
      }
    }

    strBuilder.append("]");

    return strBuilder.toString();
  }
 }
