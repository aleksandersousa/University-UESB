import linked_list.*;

public class LinkedQueue<T> implements IQueue<T> {
  private LinkedList<T> linkedList;
  private int size;

  public LinkedQueue() {
    linkedList = new LinkedList<>();
    size = 0;
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
  public void queue(T object) {
    linkedList.addLast(object);
    size++;
  }

  @Override
  public T dequeue() {
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
    return 0;
  }
  
  @Override
  public String toString() {
    return linkedList.toString();
  }
}
