import lista_encadeada.ListaEncadeada;

public class No<T extends Comparable<T>> implements Comparable<No<T>>{
  private T conteudo;
  private ListaEncadeada<No<T>> esquerdo;
  private ListaEncadeada<No<T>> direito;

  public No(T conteudo){
    this.conteudo = conteudo;
  }

  @Override
  public int compareTo(No<T> o) {
    return this.conteudo.compareTo(o.getConteudo());
  }

  public ListaEncadeada<No<T>> getDireito() {
    return direito;
  }

  public void setDireito(ListaEncadeada<No<T>> direito) {
    this.direito = direito;
  }

  public ListaEncadeada<No<T>> getEsquerdo() {
    return esquerdo;
  }

  public void setEsquerdo(ListaEncadeada<No<T>> esquerdo) {
    this.esquerdo = esquerdo;
  }

  public void setConteudo(T conteudo) {
    this.conteudo = conteudo;
  }

  public T getConteudo(){
    return this.conteudo;
  }

  @Override
  public String toString() {
    return conteudo.toString();
  }
}
