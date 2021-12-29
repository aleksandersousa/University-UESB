public class Test {
  QueueArray<Integer> queueArray = new QueueArray<>(10);
  LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();

  public void performArray() {
    //populando a fila
    for (int i = 0; i < 10; i++) {
      queueArray.queue(i);
    }

    System.out.println(queueArray.toString());

    System.out.println("desenfileirando: " + queueArray.dequeue());

    System.out.println(queueArray.toString());

    System.out.println("desenfileirando: " + queueArray.dequeue());

    System.out.println(queueArray.toString());
  }

  public void performLinkedList() {
    //populando a fila
    for (int i = 0; i < 10; i++) {
      linkedQueue.queue(i);
    }

    System.out.println(linkedQueue.toString());

    System.out.println("desenfileirando: " + linkedQueue.dequeue());

    System.out.println(linkedQueue.toString());

    System.out.println("desenfileirando: " + linkedQueue.dequeue());

    System.out.println(linkedQueue.toString());
  }
}
