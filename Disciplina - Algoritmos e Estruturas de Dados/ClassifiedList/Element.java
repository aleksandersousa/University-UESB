public class Element<T> {
  private Element<T> next;
  private T data;

  Element(T data, Element<T> next) {
    this.data = data;
    this.next = next;
  }

  public void setNext(Element<T> next) {
    this.next = next;
  }

  public Element<T> getNext() {
    return next;
  }

  public T getData() {
    return data;
  }
}
