package lista_encadeada;

public class Elemento<T> {
  private Elemento<T> proximo;
  private T conteudo;

  Elemento(T conteudo, Elemento<T> proximo) {
    this.conteudo = conteudo;
    this.proximo = proximo;
  }

  public void setProximo(Elemento<T> proximo) {
    this.proximo = proximo;
  }

  public Elemento<T> getProximo() {
    return proximo;
  }

  public T getConteudo() {
    return conteudo;
  }
}
