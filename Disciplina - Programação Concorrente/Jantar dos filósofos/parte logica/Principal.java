import java.lang.Thread;
import java.util.concurrent.Semaphore;

public class Principal{
  static public final int N = 5;
  static public final Semaphore mutex = new Semaphore(1);
  static public final Semaphore[] arraySemaphores = new Semaphore[N];
  static public final int[] estado = new int[N];

  public static void main(String[] args){
    for(int i=0; i<N; i++)
      arraySemaphores[i] = new Semaphore(0);

    Filosofo filosofo;
    for(int i=0; i<5; i++){
      filosofo = new Filosofo(i);
      filosofo.start();
    }
  }
}

class Filosofo extends Thread{
  int index;

  public Filosofo(int index){
    this.index = index;
  }

  @Override
  public void run(){
    filosofo(index);
  }

  public void pegaGarfos(int i){
    try{
      Principal.mutex.acquire();
      Principal.estado[i] = 1; //estado de fome

      testaGarfos(i);

      Principal.mutex.release();
      Principal.arraySemaphores[i].acquire(); 
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void devolverGarfos(int i){
    try{
      Principal.mutex.acquire();
      Principal.estado[i] = 0; //estado pensando

      testaGarfos(Math.floorMod(i-1, Principal.N));
      testaGarfos((i+1) % Principal.N);

      Principal.mutex.release();
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void testaGarfos(int i){
    try{
      if(Principal.estado[i] == 1 
        && Principal.estado[(Math.floorMod(i-1, Principal.N))] != 2 
        && Principal.estado[((i+1) % Principal.N)] != 2
      ){
        Principal.estado[i] = 2; //estado comendo
        Principal.arraySemaphores[i].release();
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void liberarGarfo(int i){
    try{
      Principal.mutex.acquire();
      Principal.estado[i] = 0; //estado pensando

      testaGarfos((Math.floorMod(i-1, Principal.N)));
      testaGarfos((i + 1) % Principal.N);

      Principal.mutex.release();
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  public void pensar(){
    System.out.println("Filosofo "+index+" Pensando");
  }

  public void comer(){
    System.out.println("Filosofo "+index+" Comendo**");
  }

  public void filosofo(int i){
    for(int j=0; j<10; j++){
      try{
        pensar();
        Thread.sleep(1000);
        pegaGarfos(i);
        comer();
        Thread.sleep(1000);
        devolverGarfos(i);
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}