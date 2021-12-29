package fila;

public class FilaVetor<T> implements IFila<T> {
  private T[] vetor;
  private int cabeca, cauda, tamanho;

  @SuppressWarnings("unchecked")
  public FilaVetor(int capacity) {
    vetor = (T[]) new Object[capacity];
    cauda = capacity - 1;
    cabeca = 0;
    tamanho = 0;
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
    while (tamanho > 0) {
      vetor[cabeca] = null;
      cabeca++;

      if (cabeca == vetor.length) {
        cabeca = 0;
      }

      tamanho--;
    }
  }

  @Override
  public void add(T object) {
    if (estaCheia()) {
      System.out.println("limite excedido!");
      return;
    }

    cauda++;

    if (cauda == vetor.length) {
      cauda = 0;
    }

    vetor[cauda] = object;
    tamanho++;
  }

  @Override
  public T remover() {
    if (estaVazia()) {
      return null;
    }

    T result = vetor[cabeca];
    vetor[cabeca] = null;
    cabeca++;

    if (cabeca == vetor.length) {
      cabeca = 0;
    }

    tamanho--;

    return result;
  }

  @Override
  public T espiar() {
    return !estaVazia() ? vetor[cabeca] : null;
  }

  @Override
  public int getTamanho() {
    return this.tamanho;
  }

  @Override
  public String toString() {
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("[");

    for (int i = 0, j = cabeca; i < tamanho; i++, j++) {
      strBuilder.append(vetor[j]);

      if (i < tamanho - 1) {
        strBuilder.append(", ");
      }
    }

    strBuilder.append("]");

    return strBuilder.toString();
  }
 }
