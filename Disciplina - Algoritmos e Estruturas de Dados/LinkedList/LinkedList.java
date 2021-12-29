public class LinkedList<T> {
  private Element<T> head, tail;

  public void clear() {
    head = null;
    tail = null;
  }

  public Element<T> getHead() {
    return head;
  }

  public Element<T> getTail() {
    return tail;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public T getFirst() {
    return head == null ? null : (T) head.getData();
  }

  public T getLast() {
    return tail == null ? null : (T) tail.getData();
  }

  public void addFirst(T data) {
    Element<T> temp = new Element<T>(data, head);

    if (head == null) {
      tail = temp;
    }

    head = temp;
  }

  public void addLast(T data) {
    Element<T> temp = new Element<T>(data, null);

    if (head == null) {
      head = temp;
    } else {
      tail.setNext(temp);
    }

    tail = temp;
  }

  public void insertAfter(T data, T reference) {
    Element<T> ref = this.getElement(reference);

    if (ref != null) {
      Element<T> newElement = new Element<T>(data, ref.getNext());

      if (tail == ref) {
        tail = newElement;
      }

      ref.setNext(newElement);
    }
  }

  public void insertBefore(T data, T reference) {
    Element<T> ref = this.getElement(reference);

    if (ref != null) {
      Element<T> temp = new Element<T>(data, ref);

      if (ref == head) {
        head = temp;
      } else {
        Element<T> ptr = head;

        while (ptr != null && ptr.getNext() != ref) {
          ptr = ptr.getNext();
        }

        ptr.setNext(temp);
      }
    }
  }

  public void assign(LinkedList<T> list) {
    if (list != this) {
      this.clear();

      for (Element<T> ptr = list.head; ptr != null; ptr = ptr.getNext()) {
        this.addLast(ptr.getData());
      }
    }
  }

  public void extract(T data) {
    if (this.isEmpty()) {
      System.out.println("Lista vazia!");
      return;
    }

    Element<T> ptr = head;
    Element<T> prevPtr = null;

    while (ptr != null && ptr.getData() != data) {
      prevPtr = ptr;
      ptr = ptr.getNext();
    }

    if (ptr == head) {
      head = ptr.getNext();
    } else if (ptr == tail) {
      tail = prevPtr;
      prevPtr.setNext(null);
    } else {
      prevPtr.setNext(ptr.getNext());
    }
  }

  public Element<T> getElement(T data) {
    if (this.isEmpty()) {
      return null;
    }

    Element<T> ptr = head;

    while (ptr != null) {
      if (ptr.getData() == data) {
        return ptr;
      }

      ptr = ptr.getNext();
    }

    return null;
  }

  public boolean contains(T data) {
    return getElement(data) != null;
  }

  @Override
  public String toString() {
    String str = "[ ";

    for (Element<T> ptr = head; ptr != null; ptr = ptr.getNext()) {
      str += ptr.getData() + (ptr.getNext() != null ? ", " : "");
    }

    str += "]";

    return str;
  }
}
