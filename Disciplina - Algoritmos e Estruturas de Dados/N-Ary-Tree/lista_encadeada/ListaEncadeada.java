package lista_encadeada;

import generic_compare.EqualTo;

public class ListaEncadeada<T extends Comparable<T>>implements Comparable<ListaEncadeada<T>> {
  EqualTo<T> equal = new EqualTo<>();
  private Elemento<T> cabeca, cauda;
  private int tamanho;
  private int capacidade;

  public ListaEncadeada(int capacidade) {
    this.capacidade = capacidade;
  }

  public ListaEncadeada(){};

  @Override
  public int compareTo(ListaEncadeada<T> o) {
    return 0;
  }

  public void limpar() {
    cabeca = null;
    cauda = null;
  }

  public Elemento<T> getCabeca() {
    return cabeca;
  }

  public Elemento<T> getCauda() {
    return cauda;
  }

  public boolean estaVazia() {
    return cabeca == null;
  }

  public boolean estaCheia() {
    return tamanho == capacidade;
  }

  public T getPrimeiro() {
    return cabeca == null ? null : (T) cabeca.getConteudo();
  }

  public T getUtimo() {
    return cauda == null ? null : (T) cauda.getConteudo();
  }

  public void inserirOrdenado(T conteudo) {
    if (this.estaVazia()) {
      this.addFinal(conteudo);
    } else {
      boolean appropriateReference = false;

      for (Elemento<T> ptr = this.cabeca; ptr != null; ptr = ptr.getProximo()) {
        int result = equal.compare(conteudo, ptr.getConteudo());

        if (result < 0 || result == 0) {
          appropriateReference = true;
          this.inserirAntes(conteudo, ptr.getConteudo());
          break;
        }
      }

      if (!appropriateReference) {
        this.addFinal((T) conteudo);
      }
    }
  }

  public void addInicio(T conteudo) {
    Elemento<T> novoElemento = new Elemento<T>(conteudo, cabeca);

    if (cabeca == null) {
      cauda = novoElemento;
    }

    cabeca = novoElemento;
    tamanho++;
  }

  public void addFinal(T conteudo) {
    Elemento<T> novoElemento = new Elemento<T>(conteudo, null);

    if (cabeca == null) {
      cabeca = novoElemento;
    } else {
      cauda.setProximo(novoElemento);
    }

    cauda = novoElemento;
    tamanho++;
  }

  public void inserirDepois(T conteudo, T referencia) {
    Elemento<T> ref = this.getElemento(referencia);

    if (ref != null) {
      Elemento<T> novoElemento = new Elemento<T>(conteudo, ref.getProximo());

      if (cauda == ref) {
        cauda = novoElemento;
      }

      ref.setProximo(novoElemento);
    }
    tamanho++;
  }

  public void inserirAntes(T conteudo, T referencia) {
    Elemento<T> ref = this.getElemento(referencia);

    if (ref != null) {
      Elemento<T> temp = new Elemento<T>(conteudo, ref);

      if (ref == cabeca) {
        cabeca = temp;
      } else {
        Elemento<T> ptr = cabeca;

        while (ptr != null && ptr.getProximo() != ref) {
          ptr = ptr.getProximo();
        }

        ptr.setProximo(temp);
      }
    }
    tamanho++;
  }

  public void atribuir(ListaEncadeada<T> lista) {
    if (lista != this) {
      this.limpar();

      for (Elemento<T> ptr = lista.cabeca; ptr != null; ptr = ptr.getProximo()) {
        this.addFinal(ptr.getConteudo());
      }
    }
  }

  public void remover(T conteudo) {
    if (this.estaVazia()) {
      System.out.println("Lista vazia!");
      return;
    }

    Elemento<T> ptr = cabeca;
    Elemento<T> prevPtr = null;

    while (ptr != null && ptr.getConteudo() != conteudo) {
      prevPtr = ptr;
      ptr = ptr.getProximo();
    }

    if (ptr == cabeca) {
      cabeca = ptr.getProximo();
    } else if (ptr == cauda) {
      cauda = prevPtr;
      prevPtr.setProximo(null);
    } else {
      prevPtr.setProximo(ptr.getProximo());
    }
    tamanho--;
  }

  public Elemento<T> getElemento(T conteudo) {
    if (this.estaVazia()) {
      return null;
    }

    Elemento<T> ptr = cabeca;

    while (ptr != null) {
      if (ptr.getConteudo() == conteudo) {
        return ptr;
      }

      ptr = ptr.getProximo();
    }

    return null;
  }

  public Elemento<T> getElemento(int index) {
    if (this.estaVazia() || index >= tamanho) {
      return null;
    }

    Elemento<T> ptr = cabeca;

    if (index == 0) {
      return ptr;
    } else {
      int indexAuxiliar = 0;
      while (indexAuxiliar < index) {  
        ptr = ptr.getProximo();
        indexAuxiliar++;
      }
      return ptr;
    }
  }

  public boolean contem(T conteudo) {
    return getElemento(conteudo) != null;
  }

  public int tamanho() {
    return this.tamanho;
  }

  @Override
  public String toString() {
    String str = "[ ";

    for (Elemento<T> ptr = cabeca; ptr != null; ptr = ptr.getProximo()) {
      str += ptr.getConteudo() + (ptr.getProximo() != null ? ", " : "");
    }

    str += "]";

    return str;
  }
}
