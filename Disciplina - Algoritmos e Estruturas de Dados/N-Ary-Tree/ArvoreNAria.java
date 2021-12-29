import generic_compare.EqualTo;
import lista_encadeada.ListaEncadeada;

public class ArvoreNAria<T extends Comparable<T>> {
  private final int K;
  private ListaEncadeada<No<T>> raiz;
  private ListaEncadeada<ListaEncadeada<No<T>>> resultado; //usado para imprimir em preordem
  private EqualTo<T> equalTo = new EqualTo<>();

  public ArvoreNAria(int N) {
    this.K = N - 1;
    raiz = new ListaEncadeada<>(K);
    resultado = new ListaEncadeada<>();
  }

  public void adicionar(T conteudo) {
    if (!raiz.estaCheia()) { //vai adicioando ate estar cheia
      raiz.inserirOrdenado(new No<T>(conteudo));
    } else {
      adicionarRecursivamente(conteudo, raiz);
    }
  }

  public void adicionarRecursivamente(T conteudo, ListaEncadeada<No<T>> noAtual) {
    if (!noAtual.estaCheia()) { //vai adicionando ate estar cheia
      noAtual.inserirOrdenado(new No<T>(conteudo));
    } else {
      for (int i = 0; i < noAtual.tamanho(); i++) {
        No<T> no = noAtual.getElemento(i).getConteudo();
        if (equalTo.compare(conteudo, no.getConteudo()) < 0) { //compara se o valor eh menor
          if (no.getEsquerdo() == null) { //se nao tiver filho a esquerda, cria um novo no
            no.setEsquerdo(new ListaEncadeada<No<T>>(K));
            no.getEsquerdo().inserirOrdenado(new No<T>(conteudo));
            break;
          } else { //se tiver filho, passa o no da esquerda para preencher o vetor
            adicionarRecursivamente(conteudo, no.getEsquerdo());
            break;
          }
        } else if (equalTo.compare(conteudo, no.getConteudo()) > 0) {//compar se o valor eh menor
          if (i == noAtual.tamanho() - 1) { //se for o ultimo no
            if (no.getDireito() == null) { //se nao tiver filho da direita, cria um novo no
              no.setDireito(new ListaEncadeada<No<T>>(K));
              no.getDireito().inserirOrdenado(new No<T>(conteudo));
            } else { //se tiver filho, passa o no da direita para preencher o vetor
              adicionarRecursivamente(conteudo, no.getDireito());
            }
          } else { //se nao for o ultimo no, so vai para o proximo elemento
            continue;
          }
        }
      }
    }
  }

  private No<T> buscarNaLista(T conteudo, ListaEncadeada<No<T>> lista) {
    No<T> no = null;
    for (int i = 0; i < lista.tamanho(); i++) {
      no = lista.getElemento(i).getConteudo();
      if (equalTo.compare(conteudo, no.getConteudo()) < 0) {
        break;
      } else if (equalTo.compare(conteudo, no.getConteudo()) == 0) {
        return no;
      }
    }
    return no;
  }

  public No<T> buscar(T conteudo, ListaEncadeada<No<T>> noAtual) {
    if (noAtual != null && !noAtual.estaVazia()) {
      No<T> resultado = buscarNaLista(conteudo, noAtual);
      if (resultado.getConteudo().compareTo(conteudo) == 0) {
        return resultado;
      } else {
        if (resultado.getEsquerdo() != null) {
          return buscar(conteudo, resultado.getEsquerdo());
        } else if (resultado.getDireito() != null) {
          return buscar(conteudo, resultado.getDireito());
        } else {
          System.out.println("Elemento nao esta na arvore!");
          return null;
        }
      }
    }
    return null;
  }

  public void remover(T conteudo) {
    
  }

  public void preOrdem(ListaEncadeada<No<T>> noAtual) {
    if (noAtual != null) {
      resultado.addFinal(noAtual);

      for (int i = 0; i < noAtual.tamanho(); i++) {
        No<T> filho = noAtual.getElemento(i).getConteudo();
        preOrdem(filho.getEsquerdo());
        preOrdem(filho.getDireito());
      }
    }    
  }

  public void imprimirPreOrdem() {
    resultado.limpar();
    preOrdem(raiz);
    for (int i = 0; i < resultado.tamanho(); i++) {
      ListaEncadeada<No<T>> noAtual = resultado.getElemento(i).getConteudo();
      System.out.print("{ ");
      for (int j = 0; j < noAtual.tamanho(); j++) {
        No<T> no = noAtual.getElemento(j).getConteudo();

        if (j != noAtual.tamanho() - 1) {
          System.out.print(no.getConteudo() + ", ");
        } else {
          System.out.print(no.getConteudo());
        }
      }
      System.out.print(" };");
      System.out.println();
    }
  }

  public ListaEncadeada<No<T>> getRaiz() {
    return this.raiz;
  }
}
