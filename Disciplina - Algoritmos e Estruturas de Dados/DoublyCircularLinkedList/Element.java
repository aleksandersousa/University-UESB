public class Element<T> {
  private T data;
  private Element<T> next, prev;

  public Element(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public Element<T> getNext() {
    return next;
  }

  public Element<T> getPrev() {
    return prev;
  }

  public void setNext(Element<T> next) {
    this.next = next;
  }

  public void setPrev(Element<T> prev) {
    this.prev = prev;
  }
}
