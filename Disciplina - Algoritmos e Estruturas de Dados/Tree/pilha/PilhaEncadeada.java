package pilha;

import lista_encadeada.*;

public class PilhaEncadeada<T> implements IPilha<T> {
  private ListaEncadeada<T> lista;
  private int tamanho;

  public PilhaEncadeada() {
    this.lista = new ListaEncadeada<>();
    this.tamanho = 0;
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
  public void empurrar(T conteudo) {
    lista.addInicio(conteudo);
    tamanho++;
  }

  @Override
  public T estourar() {
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
    return this.tamanho;
  }

  @Override
  public String toString() {
    return lista.toString();
  }
}
