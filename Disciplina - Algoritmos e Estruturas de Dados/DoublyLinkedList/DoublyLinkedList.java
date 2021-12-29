public class DoublyLinkedList<T> {
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
    return head == null ? true : false;
  }

  public T getFirst() {
    return head == null ? null : (T) head.getData();
  }

  public T getLast() {
    return tail == null ? null : (T) tail.getData();
  }

  public void addFirst(T data) {
    Element<T> newElement = new Element<T>(data);
    newElement.setPrev(null);

    if (head == null) {
      tail = newElement;
      tail.setNext(null);
    } else {
      head.setPrev(newElement);
      newElement.setNext(head);
    }

    head = newElement;
  }

  public void addLast(T data) {
    Element<T> newElement = new Element<T>(data);
    newElement.setNext(null);

    if (head == null) {
      head = newElement;
      head.setPrev(null);
    } else {
      tail.setNext(newElement);
      newElement.setPrev(tail);
    }

    tail = newElement;
  }

  public void insertAfter(T data, T reference) {
    Element<T> referenceElement = this.getElement(reference);

    if (referenceElement != null) {
      Element<T> newElement = new Element<T>(data);
      newElement.setNext(referenceElement.getNext());
      newElement.setPrev(referenceElement);

      if (tail == referenceElement) {
        newElement.setPrev(tail);
        tail = newElement;
      }

      referenceElement.setNext(newElement);
    }
  }

  public void insertBefore(T data, T reference) {
    Element<T> referenceElement = this.getElement(reference);

    if (referenceElement != null) {
      Element<T> newElement= new Element<T>(data);
      newElement.setNext(referenceElement);
      newElement.setPrev(referenceElement.getPrev());

      if (referenceElement == head) {
        head.setPrev(newElement);
        head = newElement;
      } else {
        Element<T> ptr = head;

        while (ptr != null && ptr.getNext() != referenceElement) {
          ptr = ptr.getNext();
        }

        ptr.setNext(newElement);
        referenceElement.setPrev(newElement);
      }
    }
  }

  public void assign(DoublyLinkedList<T> list) {
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

    while (ptr != null && ptr.getData() != data) {
      ptr = ptr.getNext();
    }

    if (ptr == head) {
      head = ptr.getNext();

      if (head != null) {
        head.setPrev(null);
      }
    } else if (ptr == tail) {
      tail = ptr.getPrev();
      
      if (tail != null) {
        tail.setNext(null);
      }
    } else {
      if (ptr.getPrev() != null) {
        ptr.getPrev().setNext(ptr.getNext());
      }

      if (ptr.getNext() != null) {
        ptr.getNext().setPrev(ptr.getPrev());
      }
    }
  }

  public Element<T> getElement(T data) {
    if (this.isEmpty()) {
      return null;
    }

    Element<T> ptr = head;

    while (ptr != null && ptr.getData() != data) {
      ptr = ptr.getNext();
    }

    return ptr;
  }

  public boolean contains(T data) {
    if (this.isEmpty()) {
      return false;
    }

    Element<T> ptr = head;

    while (ptr != null && ptr.getData() != data) {
      ptr = ptr.getNext();
    }

    return ptr.getData() == data;
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
