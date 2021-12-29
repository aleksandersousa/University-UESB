public class Test {
  DoublyCircularLinkedList<Integer> doublyLinkedList = new DoublyCircularLinkedList<Integer>();
  DoublyCircularLinkedList<Integer> doublyLinkedList2 = new DoublyCircularLinkedList<Integer>();

  public void perform() {
    // Adicionando ao comeco
    for (int i = 1; i <= 5; i++) {
      doublyLinkedList.addFirst(i);
    }

    System.out.println("Adicioando elementos ao comeco: " + doublyLinkedList.toString());
    doublyLinkedList.clear(); //limpando a lista

    // Adicionando elementos ao final
    for (int i = 1; i <= 5; i++) {
      doublyLinkedList.addLast(i);
    }

    System.out.println("Adicionando elementos ao final: " + doublyLinkedList.toString());

    // Adicionando 6 apos o 2
    doublyLinkedList.addAfter(6, 2);
    System.out.println("Inserindo 6 apos o 2: " + doublyLinkedList.toString());

    // Adicionando 7 antes do 3
    doublyLinkedList.addBefore(7, 3);
    System.out.println("Inserindo 7 antes do 3: " + doublyLinkedList.toString());

    // Deletando ultimo elemento
    doublyLinkedList.remove(doublyLinkedList.getLast().getData());
    System.out.println("Deletando ultimo elemento: " + doublyLinkedList.toString());
    
    // Deletando um elemento
    doublyLinkedList.remove(3);
    System.out.println("Deletando um elemento: " + doublyLinkedList.toString());

    // Adicionando elementos a lista 2
    for (int i = 6; i <= 10; i++) {
      doublyLinkedList2.addLast(i);
    }

    System.out.println("Lista 2: " + doublyLinkedList2.toString());

    // Adicionando elementos da lista 2 na lista 1
    doublyLinkedList.assign(doublyLinkedList2);
    System.out.println("Elementos trocados: " + doublyLinkedList.toString());
  }
}
