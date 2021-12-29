public interface ICursor<T>{
  T getData();
  void addAfter(T value);
  void addBefore(T value);
  void remove();
  boolean isEnd();
  boolean isNothing();
  T get(int value);
}