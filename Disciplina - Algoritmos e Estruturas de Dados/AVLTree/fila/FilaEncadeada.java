package fila;

import lista_encadeada.*;

public class FilaEncadeada<T> implements IFila<T> {
  private ListaEncadeada<T> lista;
  private int tamanho;

  public FilaEncadeada() {
    lista = new ListaEncadeada<>();
    tamanho = 0;
  }

  @Override
  public boolean estaVazia() {
    return tamanho == 0;
  }

  //metodo nao utilizado
  @Override
  public boolean estaCheia() {
    return false;
  }

  @Override
  public boolean contem(T objeto) {
    return lista.contem(objeto);
  }

  @Override
  public void limpar() {
    lista.limpar();
    tamanho = 0;
  }

  @Override
  public void add(T object) {
    lista.addFinal(object);
    tamanho++;
  }

  @Override
  public T remover() {
    if (estaVazia()) {
      return null;
    }

    T result = lista.getPrimeiro();
    lista.remover(result);
    tamanho--;

    return result;
  }

  @Override
  public T espiar() {
    return !estaVazia() ? lista.getPrimeiro() : null;
  }

  @Override
  public int getTamanho() {
    return 0;
  }
  
  @Override
  public String toString() {
    return lista.toString();
  }
}
