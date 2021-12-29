package fila;

public interface IFila<T> {
  public boolean estaVazia();
  public boolean estaCheia();
  public boolean contem(T objeto);
  public void limpar();
  public void add(T objeto); 
  public T remover();
  public T espiar();
  public int getTamanho();
}
