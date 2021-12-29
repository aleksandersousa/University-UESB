package pilha;

public class PilhaVetor<T> implements IPilha<T> {
  private T[] vetor;
  private int tamanho = 0;

  @SuppressWarnings("unchecked")
  public PilhaVetor(int capacidade) {
    vetor = (T[]) new Object[capacidade];
  }

  @Override
  public boolean estaVazia() {
    return tamanho == 0;
  }
  
  @Override
  public boolean estaCheia() {
    return tamanho == vetor.length;
  }

  @Override
  public boolean contem(T objeto) {
    for (int i = 0; i < tamanho; i++) {
      if (vetor[i].equals(objeto)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void limpar() {
    for (int i = 0; i < tamanho; i++) {
      vetor[i] = null;
    }
    tamanho = 0;
  }

  @Override
  public void empurrar(T conteudo) {
    if (!estaCheia()) {
      vetor[tamanho++] = conteudo;
    }
  }

  @Override
  public T estourar() {
    return !estaVazia() ? (T) vetor[--tamanho] : null;
  }

  @Override
  public T espiar() {
    return !estaVazia() ? (T) vetor[tamanho - 1] : null;
  }

  @Override
  public int getTamanho() {
    return tamanho;
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("[");

    for (int i = tamanho - 1; i >= 0; i--) {
      strBuilder.append(vetor[i]);

      if (i > 0) {
        strBuilder.append(", ");
      }
    }

    strBuilder.append("]");

    return strBuilder.toString();
  }
}
