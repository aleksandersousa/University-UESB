public class Test {
  LinkedList<Integer> list = new LinkedList<Integer>();
  LinkedList<Integer> list2 = new LinkedList<Integer>();

  public void perform() {
    //populando a lista
    for (int i=1; i<=4; i++) {
      list.addLast(i);
    }
    System.out.println("Esta vazia: " + list.isEmpty());
    System.out.println("Primeiro: " + list.getFirst());
    System.out.println("Segundo: " + list.getLast());
    System.out.println("Elementos: " + list.toString());

    //adicionando ao começo
    list.addFirst(5);
    System.out.println("Elementos: " + list.toString());

    //adicionando depois do elemento 2
    list.insertAfter(6, 2);
    System.out.println("Elementos: " + list.toString());

    //adicionando antes do element 3
    list.insertBefore(7, 3);
    System.out.println("Elementos: " + list.toString());

    //extraindo elemento 1
    list.extract(1);
    System.out.println("Elementos: " + list.toString());

    //checando se contém o elemento 4
    System.out.println("Contém: " + list.contains(4));

    //checando novamente o primeiro e o último após alterações
    System.out.println("Primeiro: " + list.getFirst());
    System.out.println("Último: " + list.getLast());

    //populando lista 2
    for (int i=5; i<=11; i++) {
      list2.addLast(i);
    }
    System.out.println("Elementos lista 2: " + list2.toString());

    //copiando a lista 2 na 1
    list.assign(list2);
    System.out.println("Elementos: " + list.toString());
  }
}
