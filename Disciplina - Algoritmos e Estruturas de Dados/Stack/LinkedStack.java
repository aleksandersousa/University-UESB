import linked_list.*;

public class LinkedStack<T> implements IStack<T> {
  private LinkedList<T> linkedList;
  private int size;

  public LinkedStack() {
    this.linkedList = new LinkedList<>();
    this.size = 0;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }
  
  //metodo nao utilizado
  @Override
  public boolean isFull() {
    return false;
  }

  @Override
  public void clear() {
    linkedList.clear();
    size = 0;
  }

  @Override
  public void push(T value) {
    linkedList.addFirst(value);
    size++;
  }

  @Override
  public T pop() {
    if (isEmpty()) {
      return null;
    }

    T result = linkedList.getFirst();
    linkedList.extract(result);
    size--;

    return result;
  }

  @Override
  public T peek() {
    return !isEmpty() ? linkedList.getFirst() : null;
  }

  @Override
  public int getSize() {
    return this.size;
  }

  @Override
  public String toString() {
    return linkedList.toString();
  }
}
