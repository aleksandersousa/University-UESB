/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/10/2019* 
Ultima alteracao: 12/10/2019* 
Nome: jantar dos filosofos* 
Funcao: Simular o problema classico jantar dos filosofos.
*************************************************************** */
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.IllegalArgumentException;

public class PhilosopherThread implements Runnable{
  private final int N = 5;
  private final int PENSAR = 0;
  private final int FOME = 1;
  private final int COMER = 2;

  private Background backgroundPanel = new Background();
  private BufferedImageLoader loader = new BufferedImageLoader();
  private Thread thread = new Thread(this);

  static private int thinkSpeed = 1;
  static private int eatSpeed = 1;

  private JPanel background;
  private Philosopher philosopher;
  private int id;

  /* **************************************************************** 
  Metodo: PhilosopherThread* 
  Funcao: Construtor da classe filosofo thread. Recebe o id do filosofo
          e o painel de background para adicionar os labels dos fiosofos* 
  Parametros: int id: id do filosofo
              JPanel background: painel de background*  
  Retorno: void*
  *************************************************************** */
  public PhilosopherThread(int id, JPanel background){
    this.id = id;
    this.background = background;
    this.createPhilosopher(id);

    thread.start();
  }

  /* **************************************************************** 
  Metodo: run* 
  Funcao: sobreescrever o metodo run da interface Runnable. Chama os
          metodos pickForks e dropForks, e tambem as animacoes pensar
          e comer* 
  Parametros: nulo*  
  Retorno: void*
  *************************************************************** */
  @Override
  public void run(){
    while(true){
      try{
        philosopher.setState(0);//animacao pensando
        Thread.sleep(convertThinkSpeed());
        
        pickForks(id);//pegando garfos
        
        philosopher.setState(2);//animacao comendo
        Thread.sleep(convertEatSpeed());
        
        dropForks(id);//devolvendo garfos
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  /* **************************************************************** 
  Metodo: pickForks* 
  Funcao: implementar semaforo mutex para exclusao mutua e um faz um 
          DOWN no array de semaphoros para garantir mais de uma regiao 
          critica. Metodo pega os garfos* 
  Parametros: int id: id do filosofo*  
  Retorno: void*
  *************************************************************** */
  public void pickForks(int id){
    try{
      TelaPrincipal.getMutex().acquire();
      
      TelaPrincipal.setState(id, FOME);//estado de fome
      philosopher.setState(FOME);//animacao fome
      
      testForks(id);
      
      TelaPrincipal.getMutex().release(); 
      TelaPrincipal.getSemaphore(id).acquire();
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  /* **************************************************************** 
  Metodo: testForks* 
  Funcao: testa se os garfos da esquerda e da direita estao livres e
          faz UP no array de semaforos* 
  Parametros: int id: id do filosofo*  
  Retorno: void*
  *************************************************************** */
  public void testForks(int id){
    if(TelaPrincipal.getState(id) == FOME//estado de fome 
      && TelaPrincipal.getState(Math.floorMod(id-1, N)) != COMER//se o garfo da esquerda esta livre
      && TelaPrincipal.getState(((id+1) % N)) != COMER//se o garfo da direita esta livre
    ){
      TelaPrincipal.setState(id, COMER);//estado comendo

      background.remove(backgroundPanel.getFork(Math.floorMod(id-1, N)));
      background.remove(backgroundPanel.getFork((id) % N));
      background.repaint();

      TelaPrincipal.getSemaphore(id).release();
    }
  }

  /* **************************************************************** 
  Metodo: dropForks* 
  Funcao: implementar semaforo mutex para exclusao mutua. Metodo solta
          os garfos* 
  Parametros: int id: id do filosofo*  
  Retorno: void*
  *************************************************************** */
  public void dropForks(int id){
    try{
      TelaPrincipal.getMutex().acquire();
      TelaPrincipal.setState(id, PENSAR);//estado pensando

      background.add(backgroundPanel.getFork(Math.floorMod(id-1, N)));
      background.add(backgroundPanel.getFork((id) % N));
      background.repaint();

      //philosopher.setState(PENSAR);//animacao pensando
      
      testForks(Math.floorMod(id-1, N));
      testForks((id+1) % N);
      
      TelaPrincipal.getMutex().release();
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  /* **************************************************************** 
  Metodo: releaseForks* 
  Funcao: implementar semaforo mutex para exclusao mutua. Metodo libera
          os garfos* 
  Parametros: int id: id do filosofo*  
  Retorno: void*
  *************************************************************** */
  public void releaseForks(int id){
    try{
      TelaPrincipal.getMutex().acquire();
      
      TelaPrincipal.setState(id, PENSAR);//estado pensando
      philosopher.setState(PENSAR);//animacao pensando
      
      testForks((Math.floorMod(id-1, N)));
      testForks((id + 1) % N);
      
      TelaPrincipal.getMutex().release();
    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  /* **************************************************************** 
  Metodo: createPhilosopher* 
  Funcao: ler os spritesheets dos filosofos de acordo com seu id e criar
          um label para cada filosofo e adicionado ao painel de background* 
  Parametros: int id: id do filosofo*  
  Retorno: void*
  *************************************************************** */
  public void createPhilosopher(int id){
    switch(id){
      case 0://filosofo vermelho
        BufferedImage philosopherImage0 = readPhilosopherImage(id);
        if(philosopherImage0 != null){
          philosopher = new Philosopher(philosopherImage0, id);

          background.add(philosopher);
          background.repaint();
        }
        break;
      case 1://filosofo preto
        BufferedImage philosopherImage1 = readPhilosopherImage(id);
        if(philosopherImage1 != null){
          philosopher = new Philosopher(philosopherImage1, id);

          background.add(philosopher);
          background.repaint();
        }
        break;
      case 2://filosofo azul
        BufferedImage philosopherImage2 = readPhilosopherImage(id);
        if(philosopherImage2 != null){
          philosopher = new Philosopher(philosopherImage2, id);

          background.add(philosopher);
          background.repaint();
        }
        break;
      case 3://filosofo cinza
        BufferedImage philosopherImage3 = readPhilosopherImage(id);
        if(philosopherImage3 != null){
          philosopher = new Philosopher(philosopherImage3, id);

          background.add(philosopher);
          background.repaint();
        }
        break;
      case 4://filosofo rosa
        BufferedImage philosopherImage4 = readPhilosopherImage(id);
        if(philosopherImage4 != null){
          philosopher = new Philosopher(philosopherImage4, id);

          background.add(philosopher);
          background.repaint();
        }
        break;
    }
  }

  /* **************************************************************** 
  Metodo: readPhilosopherImage* 
  Funcao: ler os spritesheets dos filosofos de acordo com seu id* 
  Parametros: int id: id do filosofo*  
  Retorno: BufferedImage: spritesheet do filosofo lido*
  *************************************************************** */
  public BufferedImage readPhilosopherImage(int id){
    try{
      switch(id){
        case 0:
          return loader.loadImage("imagens/filosofoVermelho.png");
        case 1:
          return loader.loadImage("imagens/filosofoPreto.png");
        case 2:
          return loader.loadImage("imagens/filosofoAzul.png");
        case 3:
          return loader.loadImage("imagens/filosofoCinza.png");
        case 4:
          return loader.loadImage("imagens/filosofoRosa.png");
      }
    }catch(IOException e){
      System.out.println("Erro ao ler imagem");
    }

    return null;
  }

  /* **************************************************************** 
  Metodo: setThinkSpeed* 
  Funcao: atualiza a velocidade que um filosofo fica pensando* 
  Parametros: int speedPensar: velocidade que o filosofo ficara pensando*  
  Retorno: void*
  *************************************************************** */
  public void setThinkSpeed(int thinkSpeed){
    PhilosopherThread.thinkSpeed = thinkSpeed;
  }

  /* **************************************************************** 
  Metodo: setEatSpeed* 
  Funcao: atualiza a velocidade que um filosofo fica comendo* 
  Parametros: int speedComer: velocidade que o filosofo ficara comendo*  
  Retorno: void*
  *************************************************************** */
  public void setEatSpeed(int eatSpeed){
    PhilosopherThread.eatSpeed = eatSpeed;
  }

  /* **************************************************************** 
  Metodo: convertThinkSpeed* 
  Funcao: inverte o valor do slide bar, para barra de velocidade de pen-
          sar ficar intuitiva* 
  Parametros: nulo*  
  Retorno: void*
  *************************************************************** */
  public long convertThinkSpeed(){
    switch(PhilosopherThread.thinkSpeed){
      case 1:
        PhilosopherThread.thinkSpeed = 5;
        return PhilosopherThread.thinkSpeed*1000;
      case 2:
        PhilosopherThread.thinkSpeed = 4;
        return PhilosopherThread.thinkSpeed*1000;
      case 3:
        return PhilosopherThread.thinkSpeed*1000;
      case 4:
        PhilosopherThread.thinkSpeed = 2;
        return PhilosopherThread.thinkSpeed*1000;
      case 5:
        PhilosopherThread.thinkSpeed = 1;
        return PhilosopherThread.thinkSpeed*1000;
      default:
        throw new IllegalArgumentException();
    }
  }

  /* **************************************************************** 
  Metodo: convertEatSpeed* 
  Funcao: inverte o valor do slide bar, para barra de velocidade de co-
          mer ficar intuitiva* 
  Parametros: nulo*  
  Retorno: void*
  *************************************************************** */
  public long convertEatSpeed(){
    switch(PhilosopherThread.eatSpeed){
      case 1:
        PhilosopherThread.eatSpeed = 5;
        return PhilosopherThread.eatSpeed*1000;
      case 2:
        PhilosopherThread.eatSpeed = 4;
        return PhilosopherThread.eatSpeed*1000;
      case 3:
        return PhilosopherThread.eatSpeed*1000;
      case 4:
        PhilosopherThread.eatSpeed = 2;
        return PhilosopherThread.eatSpeed*1000;
      case 5:
        PhilosopherThread.eatSpeed = 1;
        return PhilosopherThread.eatSpeed*1000;
      default:
        throw new IllegalArgumentException();
    }
  }
}