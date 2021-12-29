public class DoublyCircularLinkedList<T> {
  private Element<T> head, tail;

  //adicionar no inicio
  public void addFirst(T data) {
    Element<T> newElement = new Element<T>(data);

    if (this.isEmpty()) {
      head = newElement;
      tail = head;
      newElement.setNext(head);
      newElement.setPrev(tail);
    } else {
      newElement.setNext(head);
      newElement.setPrev(tail);
      head.setPrev(newElement);
      tail.setNext(newElement);
      head = newElement;
    }
  }

  //adicionar no final
  public void addLast(T data) {
    Element<T> newElement = new Element<T>(data);

    if (this.isEmpty()) {
      tail = newElement;
      head = tail;
      newElement.setNext(head);
      newElement.setPrev(tail);
    } else {
      newElement.setPrev(tail);
      newElement.setNext(tail.getNext());
      tail.setNext(newElement);
      tail = newElement;
    }
  }

  //pegar um elemento usando outro como referencia
  public Element<T> getElement(T reference) {
    if (this.isEmpty()) {
      return null;
    }

    Element<T> ptr = this.getFirst();

    while (true) {
      if (ptr == null) {
        break;
      } else if (ptr == this.getLast() && ptr.getData() != reference) {
        ptr = null;
        break;
      } else if (ptr.getData() == reference) {
        break;
      } else if (ptr.getNext().getData() == reference) {
        ptr = ptr.getNext();
        break;
      }
      ptr = ptr.getNext();
    }

    return ptr;
  }

  //adicionar depois de um elemento
  public void addAfter(T data, T reference) {
    Element<T> newElement = new Element<T>(data);
    Element<T> ref = this.getElement(reference);

    if (ref != null) {
      newElement.setNext(ref.getNext());
      newElement.setPrev(ref);
      ref.getNext().setPrev(newElement);
      ref.setNext(newElement);

      if (tail == ref) {
        tail = newElement;
      }
    } else {
      System.out.println("Referencia nao existe na lista!");
    }
  }

  // adicionar antes de um elemento
  public void addBefore(T data, T reference) {
    Element<T> newElement = new Element<T>(data);
    Element<T> ref = this.getElement(reference);

    if (ref != null) {
      ref.getPrev().setNext(newElement);
      newElement.setPrev(ref.getPrev());
      newElement.setNext(ref);
      ref.setPrev(newElement);

      if (ref == head) {
        head = newElement;
      }
    } else {
      System.out.println("Referencia nao existe na lista!");
    }
  }

  // atribuir uma lista em outra
  public void assign(DoublyCircularLinkedList<T> lista) {
    if (lista != this) {
      this.clear();

      Element<T> ptr = lista.tail.getNext();

      while (true) {
        this.addLast(ptr.getData());

        if (ptr == lista.tail) {
          break;
        }

        ptr = ptr.getNext();
      }
    }
  }

  // remover um elemento
  public void remove(T data) {
    Element<T> ref = this.getElement(data);

    if (ref == null) {
      return;
    }

    if (ref == head) {
      tail.setNext(ref.getNext());
      ref.getNext().setPrev(tail);
      head = ref.getNext();
    } else if (ref == tail) {
      ref.getPrev().setNext(tail.getNext());
      ref.getPrev().setPrev(ref.getPrev().getPrev());
      head.setPrev(ref.getPrev());
      tail = ref.getPrev();
    } else {
      ref.getPrev().setNext(ref.getNext());
      ref.getNext().setPrev(ref.getPrev());
    }
  }

  // limpar a lista
  public void clear() {
    head = null;
    tail = null;
  }

  // checa se esta vazia
  public boolean isEmpty() {
    return tail == null;
  }

  // pegar o head
  public Element<T> getFirst() {
    return this.isEmpty() ? null : this.head;
  }

  // pegar o tail
  public Element<T> getLast() {
    return this.isEmpty() ? null : this.tail;
  }

  // Imprimindo a lista toda em forma de string
  @Override
  public String toString() {
    String str = "[ ";

    for (Element<T> ptr = getFirst(); ptr != getLast(); ptr = ptr.getNext()) {
      str += ptr.getData() + ", ";
    }

    str += getLast().getData() + " ]";

    return str;
  }
}
