public class Test {
  ClassifiedLinkedList<Integer> linkedList = new ClassifiedLinkedList<Integer>();
  ClassifiedListArray<Integer> arrayList = new ClassifiedListArray<>(10);

  public void performArrayList() {
    //populando lista com números aleatórios
    for (int i = 1; i <= 8; i++) {
      int random = (int) (Math.random() * 20 ) + 1;
      // System.out.println(random); //imprime a ordem que os números foram gerados
      arrayList.add(random);
    }

    System.out.println("Elementos: " + arrayList.toString());

    //adicionando outro elemento a lista
    arrayList.add(5);
    System.out.println("Elementos: " + arrayList.toString());
    
    //adicionando outro elemento a lista
    arrayList.add(1);
    System.out.println("Elementos: " + arrayList.toString());

    System.out.println("Contem elemento 5? " + arrayList.contains(5));
    
    System.out.println("Contem elemento 22? " + arrayList.contains(22));

    System.out.println("Pesquisando elemento 1: " + arrayList.search(1));

    System.out.println("Pesquisando elemento 22: " + arrayList.search(22));

    //pegando posição 2 do cursor
    ClassifiedListArray<Integer>.Cursor cursor = arrayList.getPosition(2);

    //inserindo antes da posição do cursor
    cursor.addBefore(3);
    System.out.println("Elementos: " + arrayList.toString());

    //inserindo após a posição do cursor
    cursor.addAfter(6);
    System.out.println("Elementos: " + arrayList.toString());
  }

  public void performLinkedList() {
    //populando lista
    for (int i = 1; i <= 5; i++) {
      linkedList.add(i);
    }
    System.out.println("Elementos: " + linkedList.toString());

    //pegando posição 2 do cursor
    ClassifiedLinkedList<Integer>.Cursor cursor = linkedList.getPosition(2);

    //inserindo antes da posição do cursor
    cursor.addBefore(3);
    System.out.println("Elementos: " + linkedList.toString());
  }
}
