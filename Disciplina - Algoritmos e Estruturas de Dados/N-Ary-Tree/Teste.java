public class Teste {
  final int N = 4;
  ArvoreNAria<Integer> arvore = new ArvoreNAria<>(N);
  
  public void executar() {
    arvore.adicionar(13);
    arvore.adicionar(54);
    arvore.adicionar(93);
    arvore.adicionar(3);
    arvore.adicionar(9);
    arvore.adicionar(22);
    arvore.adicionar(27);
    arvore.adicionar(50);

    arvore.imprimirPreOrdem();

    System.out.println(arvore.buscar(27, arvore.getRaiz()).getConteudo());
  }
}
