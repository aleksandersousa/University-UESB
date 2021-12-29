/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/10/2019* 
Ultima alteracao: 12/10/2019* 
Nome: jantar dos filosofos* 
Funcao: Simular o problema classico jantar dos filosofos.
*************************************************************** */
import javax.swing.JFrame;
import javax.swing.JSlider;
import java.awt.Image;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.io.IOException;

public class TelaPrincipal extends JFrame{
  static private final int N = 5;
  static private final Semaphore mutex = new Semaphore(1);
  static private final Semaphore[] arraySemaphores = new Semaphore[N];
  static private final int[] states = new int[N];

  private ArrayList<JSlider> sliderArray = new ArrayList<JSlider>();
  private ArrayList<Image> forkImages = new ArrayList<Image>();
  private BufferedImageLoader loader = new BufferedImageLoader();

  private Image backgroundImage, tableImage, forkImage;
  private Background backgroundPanel;

  /* **************************************************************** 
  Metodo: TelaPrincipal* 
  Funcao: Construtor da classe TelaPrincipal. Le as imagens de back-
          ground, mesa e garfos do disco e inicializa a tela principal
          e inicializa o vetor de semaforos* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public TelaPrincipal(){
    for(int i=0; i<N; i++)
      arraySemaphores[i] = new Semaphore(0);

    try{
      backgroundImage = loader.loadImage("imagens/background.png");
      tableImage = loader.loadImage("imagens/mesa.png");
      
      for(int i=1; i<=N; i++){
        forkImage = loader.loadImage("imagens/garfo"+i+".png");
        forkImages.add(forkImage);
      }

      backgroundPanel = new Background(backgroundImage, tableImage, forkImages);
    }catch(IOException e){
      e.printStackTrace();
    }

    initGUIComponents();
  }

  /* **************************************************************** 
  Metodo: intitThreads* 
  Funcao: Inicializar o threads e adicionar os sliders de velocidade
          a seus respectivos objetos* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void initThreads(){
    sliderArray = backgroundPanel.getSliderArray();

    for(int i=0; i<N; i++){
      PhilosopherThread threadPhilosopher = new PhilosopherThread(i, backgroundPanel);

      sliderArray.get(i).addChangeListener(e -> threadPhilosopher.setThinkSpeed(((JSlider)e.getSource()).getValue()));
      sliderArray.get(i+N).addChangeListener(e -> threadPhilosopher.setEatSpeed(((JSlider)e.getSource()).getValue()));
    }
  }

  /* **************************************************************** 
  Metodo: intitGUIComponents* 
  Funcao: Inicializa a tela principal e chama o metodo init threads* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */

  static public void setState(int i, int state){
    states[i] = state;
  }

  static public int getN(){
    return N;
  }
  
  static public int getState(int i){
    return states[i];
  }

  static public Semaphore getMutex(){
    return mutex;
  }

  static public Semaphore getSemaphore(int i){
    return arraySemaphores[i];
  }

  public void initGUIComponents(){
    add(backgroundPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(170,80);
    setSize(1050, 650);
    setVisible(true);

    initThreads();
  }
}