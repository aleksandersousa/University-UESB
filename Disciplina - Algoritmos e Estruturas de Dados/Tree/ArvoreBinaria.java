import pilha.PilhaEncadeada;
import fila.FilaEncadeada;

public class ArvoreBinaria<T extends Comparable<T>> {
  private No<T> raiz;
  private String codigoBusca;// codigo de busca do no (?)
  private PilhaEncadeada<No<T>> pilha;
  private FilaEncadeada<No<T>> fila;

  public void add(T conteudo) {
    if (this.raiz == null) {
      raiz = new No<T>(conteudo);
    } else {
      addNoRecursivamente(raiz, conteudo);
    }
  }

  public void addNoRecursivamente(No<T> no, T conteudo) {
    if (no.getConteudo().compareTo(conteudo) > 0) {// subarvore esquerda
      if (no.getEsquerdo() == null) {
        no.setEsquerdo(new No<T>(conteudo));
      } else {
        addNoRecursivamente(no.getEsquerdo(), conteudo);
      }
    } else {// subarvore direita
      if (no.getDireito() == null) {
        no.setDireito(new No<T>(conteudo));
      } else {
        addNoRecursivamente(no.getDireito(), conteudo);
      }
    }
  }

  public void addSemRecursao(T conteudo) {
    No<T> novoElemento = new No<T>(conteudo);
    No<T> noAtual = this.raiz;

    if (this.raiz == null) {
      raiz = new No<T>(conteudo);
    } else {
      while (true) {
        if (noAtual.getConteudo().compareTo(novoElemento.getConteudo()) > 0) {
          if (noAtual.getEsquerdo() == null) {
            noAtual.setEsquerdo(novoElemento);
            break;
          }
          noAtual = noAtual.getEsquerdo();
        } else {
          if (noAtual.getDireito() == null) {
            noAtual.setDireito(novoElemento);
            break;
          }
          noAtual = noAtual.getDireito();
        }
      }
    }
  }

  public No<T> buscar(T conteudo) {
    if (raiz.getConteudo() == null) {
      return null;
    } else {
      // codigoBusca = "";
      return buscarRecursivamente(raiz, conteudo);
    }
  }

  public No<T> buscarRecursivamente(No<T> no, T conteudo) {
    if (raiz.getConteudo().compareTo(conteudo) == 0 || no.getConteudo().compareTo(conteudo) == 0) {
      // System.out.println("Codigo do no: raiz");
      return no;
    } else {
      if (no.getConteudo().compareTo(conteudo) > 0) {// subarvore esquerda
        if (no.getEsquerdo() != null) {
          // codigoBusca += "0";
          return buscarRecursivamente(no.getEsquerdo(), conteudo);
        } else {
          codigoBusca = "No nao existente!";
          System.out.println(codigoBusca);
          return null;
        }
      } else {// subarvore direita
        if (no.getDireito() != null) {
          // codigoBusca += "1";
          return buscarRecursivamente(no.getDireito(), conteudo);
        } else {
          codigoBusca = "No nao existente!";
          System.out.println(codigoBusca);
          return null;
        }
      }
    }
  }

  public No<T> buscarSemRecursao(T conteudo) {
    No<T> ponteiro = raiz;

    if (this.raiz.getConteudo() == conteudo) {
      return this.raiz;
    } else {
      while (true) {
        if (ponteiro.getConteudo().compareTo(conteudo) == 0) {
          return ponteiro;
        } else if (ponteiro.getConteudo().compareTo(conteudo) > 0) {
          if (ponteiro.getEsquerdo() != null) {
            ponteiro = ponteiro.getEsquerdo();
          } else {
            System.out.println("Elemento nao existe!");
            break;
          }
        } else {
          if (ponteiro.getDireito() != null) {
            ponteiro = ponteiro.getDireito();
          } else {
            System.out.println("Elemento nao existe!");
            break;
          }
        }
      }
    }
    return null;
  }

  public No<T> buscarPaiRecursivamente(No<T> pai, No<T> filho) {
    if (filho == this.raiz) {
      return null;
    }

    if (pai.getEsquerdo() == filho || pai.getDireito() == filho) {// condicao de parada da recursao
      return pai;
    } else {
      if (pai.getConteudo().compareTo(filho.getConteudo()) > 0) {// subarvore esquerda
        if (pai.getEsquerdo() != null) {
          return buscarPaiRecursivamente(pai.getEsquerdo(), filho);
        } else {
          return null;
        }
      } else {// subarvore direita
        if (pai.getDireito() != null) {
          return buscarPaiRecursivamente(pai.getDireito(), filho);
        } else {
          return null;
        }
      }
    }
  }

  public No<T> buscarPaiSemRecursao(No<T> filho) {
    No<T> noAtual = this.raiz;

    if (filho == this.raiz) {
      return null;
    } else {
      while(true) {
        if (noAtual.getEsquerdo() == filho || noAtual.getDireito() == filho) {
          return noAtual;
        } else {
          if (filho.getConteudo().compareTo(noAtual.getConteudo()) < 0) {
            if (noAtual.getEsquerdo() != null) {
              noAtual = noAtual.getEsquerdo();
            } else {
              return null;
            }
          } else {
            if (noAtual.getDireito() != null) {
              noAtual = noAtual.getDireito();
            } else {
              return null;
            }
          }
        }
      }
    }
  }

  public No<T> buscarMenorComRecursao(No<T> no) {
    if (no.getDireito() != null) {
      return buscarMenorRecursivamentePelaEsquerda(no.getDireito());
    } else {
      return buscarMenorRecursimentePelaDireita(no.getEsquerdo());
    }
  }

  public No<T> buscarMenorSemRecursao(No<T> no) {
    if (no.getDireito() != null) {
      return buscarMenorPelaEsquerda(no.getDireito());
    } else {
      return buscarMenorPelaDireita(no.getEsquerdo());
    }
  }

  public No<T> buscarMenorRecursivamentePelaEsquerda(No<T> no) {
    if (no.getEsquerdo() != null) {
      return buscarMenorRecursivamentePelaEsquerda(no.getEsquerdo());
    } else {
      return no;
    }
  }

  public No<T> buscarMenorPelaEsquerda(No<T> no) {
    while(true) {
      if (no.getEsquerdo() != null) {
        no = no.getEsquerdo();
      } else {
        return no;
      }
    }
  }

  public No<T> buscarMenorRecursimentePelaDireita(No<T> no) {
    if (no.getDireito() != null) {
      return buscarMenorRecursimentePelaDireita(no.getDireito());
    } else {
      return no;
    }
  }

  public No<T> buscarMenorPelaDireita(No<T> no) {
    while(true) {
      if (no.getDireito() != null) {
        no = no.getDireito();
      } else {
        return no;
      }
    }
  }

  public void imprimirPreOrdemRecursivamente(No<T> no) {
    if (no != null) {
      System.out.print(no.getConteudo() + " ");
      imprimirPreOrdemRecursivamente(no.getEsquerdo());
      imprimirPreOrdemRecursivamente(no.getDireito());
    }
  }

  public void imprimirPosOrdemRecursivamente(No<T> no) {
    if (no != null) {
      imprimirPosOrdemRecursivamente(no.getEsquerdo());
      imprimirPosOrdemRecursivamente(no.getDireito());
      System.out.print(no.getConteudo() + " ");
    }
  }

  public void imprimirEmOrdemRecursivamente(No<T> no) {
    if (no != null) {
      imprimirEmOrdemRecursivamente(no.getEsquerdo());
      System.out.print(no.getConteudo() + " ");
      imprimirEmOrdemRecursivamente(no.getDireito());
    }
  }

  public void imprimirPreOrdem() {
    PilhaEncadeada<No<T>> pilha = new PilhaEncadeada<>();

    if (this.raiz != null) {
      pilha.empurrar(this.raiz);

      while (!pilha.estaVazia()) {
        No<T> elemento = pilha.estourar();

        System.out.print(elemento.getConteudo() + " ");

        if (elemento.getDireito() != null) {
          pilha.empurrar(elemento.getDireito());
        }
        if (elemento.getEsquerdo() != null) {
          pilha.empurrar(elemento.getEsquerdo());
        }
      }
    }

    System.out.println();// imprime sem recursividade
  }

  public void imprimirPosOrdem() {
    PilhaEncadeada<No<T>> pilha1 = new PilhaEncadeada<>();
    PilhaEncadeada<T> pilha2 = new PilhaEncadeada<>();

    if (this.raiz != null) {
      pilha1.empurrar(this.raiz);
      while (!pilha1.estaVazia()) {
        pilha2.empurrar(pilha1.espiar().getConteudo());

        No<T> temp = pilha1.estourar();

        if (temp.getEsquerdo() != null) {
          pilha1.empurrar(temp.getEsquerdo());
        }

        if (temp.getDireito() != null) {
          pilha1.empurrar(temp.getDireito());
        }
      }

      while (!pilha2.estaVazia()) {
        System.out.print(pilha2.estourar() + " ");
      }
    }
    System.out.println();
  }

  public void imprimirEmOrdem(No<T> no) {
    fila = new FilaEncadeada<>();
    pilha = new PilhaEncadeada<>();

    No<T> proximoNo = null;
    No<T> noTemporario = null;

    while (no != null) {
      if (no.getEsquerdo() != null) {
        if (!fila.contem(no.getEsquerdo())) {
          proximoNo = no.getEsquerdo();
        } else {
          if (!fila.contem(no)) {
            fila.add(no);
          }

          if (no.getDireito() != null) {
            proximoNo = no.getDireito();
          }
        }
      } else if (no.getDireito() != null) {
        if (!fila.contem(no)) {
          fila.add(no);
        }

        if (!fila.contem(no.getDireito())) {
          proximoNo = no.getDireito();
        }
      } else {
        proximoNo = null;
      }

      if (no.getDireito() != null && !pilha.contem(no) && noTemporario != no) {
        pilha.empurrar(no);
      }

      if (proximoNo != null) {
        no = proximoNo;
      } else {
        if (!fila.contem(no)) {
          fila.add(no);
        }

        if (!pilha.estaVazia()) {
          no = pilha.estourar();
          noTemporario = no;
        } else {
          no = null;
        }
      }
    }

    while (!fila.estaVazia()) {
      No<T> noFila = fila.remover();
      System.out.print(noFila.getConteudo() + " ");
    }
  }

  public void imprimirEmLargura() {
    FilaEncadeada<No<T>> noFila = new FilaEncadeada<>();
    No<T> noTemporario;

    if (raiz != null) {
      noFila.add(raiz);

      while (!noFila.estaVazia()) {
        noTemporario = noFila.remover();

        System.out.print(noTemporario.getConteudo() + " ");

        if (noTemporario.getEsquerdo() != null) {
          noFila.add(noTemporario.getEsquerdo());
        }

        if (noTemporario.getDireito() != null) {
          noFila.add(noTemporario.getDireito());
        }
      }
      System.out.println();
    }
  }

  public void removerComRecursão(T chave) {
    No<T> atual = buscar(chave);

    if (atual != null) {
      No<T> pai = buscarPaiRecursivamente(this.raiz, atual);

      if (ehFolha(atual)) {
        if (atual == this.raiz) {
          this.raiz = null;
        } else {
          if (pai.getEsquerdo() == atual) {
            pai.setEsquerdo(null);
          } else {
            pai.setDireito(null);
          }
        }
      } else if (atual.getEsquerdo() != null && atual.getDireito() != null) {
        No<T> menor = buscarMenorComRecursao(atual);
        T conteudo = menor.getConteudo();

        removerComRecursão(menor.getConteudo());

        atual.setConteudo(conteudo);
      } else {
        No<T> filho = (atual.getEsquerdo() != null) ? atual.getEsquerdo() : atual.getDireito();

        if (atual == this.raiz) {
          this.raiz = filho;
        } else {
          if (atual == pai.getEsquerdo()) {
            pai.setEsquerdo(filho);
          } else {
            pai.setDireito(filho);
          }
        }
      }
    }
  }
  
  public void removerSemRecursao(T chave) {
    No<T> atual = buscar(chave);

    while(true) {
      No<T> pai = buscarPaiSemRecursao(atual);

      if (ehFolha(atual)) {
        if (atual == this.raiz) {
          this.raiz = null;
        } else {
          if (pai.getEsquerdo() == atual) {
            pai.setEsquerdo(null);
          } else {
            pai.setDireito(null);
          }
        }
        break;
      } else if (atual.getEsquerdo() != null && atual.getDireito() != null) {
        No<T> menor = buscarMenorComRecursao(atual);
        atual.setConteudo(menor.getConteudo());
        atual = menor;
      } else {
        No<T> filho = (atual.getEsquerdo() != null) ? atual.getEsquerdo() : atual.getDireito();

        if (atual == this.raiz) {
          this.raiz = filho;
        } else {
          if (atual == pai.getEsquerdo()) {
            pai.setEsquerdo(filho);
          } else {
            pai.setDireito(filho);
          }
        }
        break;
      }
    }
  }

  public boolean ehFolha(No<T> no) {
    return no.getEsquerdo() == null && no.getDireito() == null;
  }

  public boolean estaNaArvore(T conteudo) {
    return buscarRecursivamente(raiz, conteudo) != null;
  }

  public No<T> getRaiz() {
    return this.raiz;
  }
}