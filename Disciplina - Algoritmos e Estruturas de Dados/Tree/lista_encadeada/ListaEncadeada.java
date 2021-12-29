package lista_encadeada;

public class ListaEncadeada<T> {
  private Elemento<T> cabeca, cauda;

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

  public T getPrimeiro() {
    return cabeca == null ? null : (T) cabeca.getConteudo();
  }

  public T getUtimo() {
    return cauda == null ? null : (T) cauda.getConteudo();
  }

  public void addInicio(T conteudo) {
    Elemento<T> novoElemento = new Elemento<T>(conteudo, cabeca);

    if (cabeca == null) {
      cauda = novoElemento;
    }

    cabeca = novoElemento;
  }

  public void addFinal(T conteudo) {
    Elemento<T> novoElemento = new Elemento<T>(conteudo, null);

    if (cabeca == null) {
      cabeca = novoElemento;
    } else {
      cauda.setProximo(novoElemento);
    }

    cauda = novoElemento;
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

  public boolean contem(T conteudo) {
    return getElemento(conteudo) != null;
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
