package pilha;

public interface IPilha<T> {
  boolean estaVazia();
  boolean estaCheia();
  boolean contem(T objeto);
  void limpar();
  void empurrar(T objeto);
  T estourar();
  T espiar();
  int getTamanho();
}
