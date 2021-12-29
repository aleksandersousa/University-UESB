public class No<T> {
  private T conteudo;
  private No<T> esquerdo, direito;
  private int altura;

  public No(T conteudo) {
    this.conteudo = conteudo;
    this.altura = 1;
  }

  public void setEsquerdo(No<T> esquerdo) {
    this.esquerdo = esquerdo;
  }

  public void setDireito(No<T> direito) {
    this.direito = direito;
  }

  public void setConteudo(T conteudo) {
    this.conteudo = conteudo;
  }

  public No<T> getEsquerdo() {
    return this.esquerdo;
  }

  public No<T> getDireito() {
    return this.direito;
  }

  public T getConteudo() {
    return this.conteudo;
  }

  public void setAltura(int altura) {
    this.altura = altura;
  }

  public int getAltura() {
    return this.altura;
  }
}
