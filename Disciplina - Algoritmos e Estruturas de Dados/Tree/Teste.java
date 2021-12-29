public class Teste {
  ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();
  
  public void executar() {
    // adicionando elementos
    arvore.addSemRecursao(50);
    arvore.addSemRecursao(40);
    arvore.addSemRecursao(60);
    arvore.addSemRecursao(30);
    arvore.addSemRecursao(45);
    arvore.addSemRecursao(44);
    arvore.addSemRecursao(46);
    arvore.addSemRecursao(55);
    arvore.addSemRecursao(65);
    arvore.addSemRecursao(56);
    arvore.addSemRecursao(57);

    if (arvore.estaNaArvore(70)) {
      System.out.println(arvore.buscarSemRecursao(70).getConteudo());
    }

    arvore.imprimirEmOrdem(arvore.getRaiz());
    System.out.println();

    arvore.imprimirEmLargura();

    System.out.println(arvore.buscarPaiSemRecursao(arvore.buscarSemRecursao(65)).getConteudo());

    System.out.println(arvore.buscarMenorSemRecursao(arvore.getRaiz()).getConteudo());

    arvore.removerSemRecursao(50);

    arvore.imprimirPreOrdem();
  }
}
