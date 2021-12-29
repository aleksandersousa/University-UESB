import java.lang.Comparable;

@SuppressWarnings("unchecked")
public class ClassifiedListArray<T> implements IClassifiedList<T> {
  final int SMALLER = -1;
  final int EQUAL = 0;
  final int GREATER = 1;

  private Object[] store;
  private int size;

  public ClassifiedListArray(int capacity) {
    store = new Object[capacity];
  }

  @Override
  public boolean isFull() {
    return size == store.length;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public <U extends Comparable<U>> void add(U object) {
    if (isFull()) {
      return;
    }

    if (isEmpty()) {
      store[size] = object;
      size++;
    } else {
      Object[] tempArray = new Object[size + 1];
      int indexToInsert = 0;
      boolean appropriateIndex = false;

      for (int i = 0; i < size; i++) {
        int result = object.compareTo((U) store[i]);

        if (!appropriateIndex && (result == SMALLER || result == EQUAL)) {
          appropriateIndex = true;
          indexToInsert = i;
          tempArray[indexToInsert] = object;
          i--;
        } else {
          if (appropriateIndex) {
            tempArray[i + 1] = store[i];
          } else {
            tempArray[i] = store[i];
          }
        }
      }

      if (!appropriateIndex) {
        indexToInsert = size;
        tempArray[indexToInsert] = object;
      }

      for (int i = 0; i < tempArray.length; i++) {
        store[i] = tempArray[i];
      }

      size++;
    }
  }

  @Override
  public void remove(T object) {
    if (isEmpty()) {
      return;
    }

    int i = 0;
    while (i < size && store[i] != object) {
      i++;
    }

    if (i == size) {
      return;
    }

    for (; i < size - 1; i++) {
      store[i] = store[i + 1];
    }

    store[i] = null;
    size--;
  }

  public <U extends Comparable<U>> boolean contains(U object) {
    int left = 0;
    int right = size - 1;
    int keyIndex = -1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (store[mid].equals(object)) {
        keyIndex = mid;
        break;
      }

      int result = object.compareTo((U) store[mid]);
      if (result == GREATER) { //se key for maior que store[mid]
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return keyIndex != -1;
  }

  @Override
  public <U extends Comparable<U>> T search(U object) {
    int left = 0;
    int right = size - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (store[mid].equals(object)) {
        return (T) store[mid];
      }

      int result = object.compareTo((U) store[mid]);
      if (result == GREATER) { //se key for maior que store[mid]
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return null;
  }

  // metodo nao utilizado
  @Override
  public T get(int i) {
    return null;
  }

  @Override
  public Cursor getPosition(T object) {
    int i = 0;

    while (i < size && !store[i].equals(object)) {
      i++;
    }

    return new Cursor(i);
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("[");

    for (int i = 0; i < size; i++) {
      strBuilder.append(store[i]);

      if (i != size - 1) {
        strBuilder.append(", ");
      }
    }

    strBuilder.append("]");

    return strBuilder.toString();
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
  final class Cursor implements ICursor<T> {
    int offset;

    public Cursor(int offset) {
      this.offset = offset;
    }

    @Override
    public boolean isNothing() {
      return offset < 0;
    }

    @Override
    public boolean isEnd() {
      return offset >= size;
    }

    @Override
    public T getData() {
      if (isNothing() || isEnd()) {
        return null;
      }

      return (T) store[offset];
    }

    @Override
    public void addBefore(T object) {
      if (isNothing() || isEnd() || isFull()) {
        return;
      }

      for (int i = size; i > offset; i--) {
        store[i] = store[i - 1];
      }

      store[offset] = object;
      size++;
    }

    @Override
    public void addAfter(T object) {
      if (isNothing() || isEnd() || isFull()) {
        return;
      }

      int insertPosition = offset + 1;

      for (int i = size; i > insertPosition; i--) {
        store[i] = store[i - 1];
      }

      store[insertPosition] = object;
      size++;
    }

    @Override
    public void remove() {
      if (isNothing() || isEnd() || isEmpty()) {
        return;
      }

      int i = offset;

      while (i < size - 1) {
        store[i] = store[i + 1];
        i++;
      }

      store[i] = null;
      size--;
    }

    @Override
    public T get(int value) {
      if (isNothing() || isEnd()) {
        return null;
      }

      return (T) store[offset];
    }
  }
}
