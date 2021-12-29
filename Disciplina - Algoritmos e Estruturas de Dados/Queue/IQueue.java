public interface IQueue<T> {
  public boolean isEmpty();
  public boolean isFull();
  public void clear();
  public void queue(T object); 
  public T dequeue();
  public T peek();
  public int getSize();
}
