public class Teste {
  ArvoreAVL<Integer> arvore = new ArvoreAVL<>();
  
  public void executar() {
    // adicionando elementos
    arvore.add(50);
    arvore.add(51);
    arvore.add(52);
    arvore.add(53);
    arvore.add(54);
    arvore.add(55);
    arvore.add(56);
    arvore.add(57);
    arvore.add(58);
    arvore.add(59);
    arvore.add(60);
    arvore.imprimirPreOrdem();

    arvore.remover(53);
    arvore.imprimirPreOrdem();
  }
}
