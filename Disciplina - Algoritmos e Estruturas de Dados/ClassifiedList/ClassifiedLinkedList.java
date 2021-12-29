public class ClassifiedLinkedList<T> extends LinkedList<T> implements IClassifiedLinkedList<T> {
  LinkedList<T> list = new LinkedList<T>();
  int size = 0;

  //metodo nao utilizado
  @Override
  public boolean isFull() {
    return false;
  }

  @Override
  public void add(T object) {
    list.addLast(object);
    size++;
  }

  @Override
  public T get(int offset) {
    if (offset < 0 || offset >= size) {
      return null;
    }

    Element<T> ptr = list.getHead();

    for (int i = 0; i < offset && ptr != null; i++) {
      ptr = ptr.getNext();
    }

    return (T) ptr.getData();
  }

  @Override
  public boolean contains(T object) {
    Element<T> ptr;

    for (ptr = list.getHead(); ptr != null; ptr = ptr.getNext()) {
      if (ptr.getData() == object) {
        return true;
      }
    }

    return false;
  }

  @Override
  public T search(T object) {
    Element<T> ptr;

    for (ptr = list.getHead(); ptr != null; ptr = ptr.getNext()) {
      if (ptr.getData().equals(object)) {
        return ptr.getData();
      }
    }

    return null;
  }

  @Override
  public void remove(T object) {
    if (size == 0) {
      return;
    }

    list.extract(object);
    size--;
  }

  @Override
  public Cursor getPosition(T object) {
    Element<T> ptr;
    
    for (ptr = list.getHead(); ptr != null; ptr = ptr.getNext()) {
      T tempObject = (T) ptr.getData();

      if (tempObject.equals(object)) {
        break;
      }
    }

    return new Cursor(list, ptr);
  }

  @Override
  public String toString() {
    return list.toString();
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  final class Cursor implements ICursor<T> {
    LinkedList<T> element;
    Element<T> element2;

    public Cursor(LinkedList<T> element, Element<T> element2) {
      this.element = element;
      this.element2 = element2;
    }

    // Metodo nao utilizado
    @Override
    public boolean isNothing() {
      return false;
    }

    // Metodo nao utilizado
    @Override
    public boolean isEnd() {
      return false;
    }

    // Metodo nao sera utilizado
    @Override
    public T get(int value) {
      return null;
    }

    @Override
    public T getData() {
      return (T) element2.getData();
    }

    @Override
    public void addBefore(T object) {
      element.insertBefore(object, element2.getData());
      size++;
    }

    @Override
    public void addAfter(T object) {
      element.insertAfter(object, element2.getData());
      size++;
    }

    @Override
    public void remove() {
      element.extract(element2.getData());
      size--;
    }
  }
}
