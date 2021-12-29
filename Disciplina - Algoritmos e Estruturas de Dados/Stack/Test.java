public class Test {
  StackArray<Integer> stack = new StackArray<>(10);
  LinkedStack<Integer> linkedStack = new LinkedStack<>();

  public void performArray() {
    //populando a pilha
    for (int i = 0; i < 5; i++) {
      stack.push(i);
    }
    System.out.println(stack.toString());

    System.out.println("o tamanho da pilha eh: " + stack.getSize());

    System.out.println("o topo da pilha eh: " + stack.peek());

    //retirando elemento da pilha
    stack.pop();
    System.out.println(stack.toString());
  }

  public void performLinkedList() {
    //populando a pilha
    for (int i = 0; i < 5; i++) {
      linkedStack.push(i);
    }
    System.out.println(linkedStack.toString());

    System.out.println("o tamanho da pilha eh: " + linkedStack.getSize());

    System.out.println("o topo da pilha eh: " + linkedStack.peek());

    //retirando elemento da pilha
    linkedStack.pop();
    System.out.println(linkedStack.toString());
  }
}
