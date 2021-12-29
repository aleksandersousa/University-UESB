public class Element<T> {
  private Element<T> next, prev;
  private T data;

  Element(T data) {
    this.data = data;
  }

  public void setNext(Element<T> next) {
    this.next = next;
  }

  public void setPrev(Element<T> prev) {
    this.prev = prev;
  }

  public Element<T> getNext() {
    return this.next;
  }

  public Element<T> getPrev() {
    return this.prev;
  }

  public T getData() {
    return data;
  }
}
