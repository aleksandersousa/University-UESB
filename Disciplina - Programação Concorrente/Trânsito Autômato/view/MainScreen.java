/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
package view;

import javax.swing.JFrame;
import java.util.concurrent.Semaphore;

public class MainScreen extends JFrame{
  private final int SIZE_ARRAY_SEMAPHORES = 36;
  private static Semaphore[] semaphores;
  private Background background;

  /* **************************************************************** 
  Metodo: MainScreen* 
  Funcao: Construtor da classe MainScreen. Iniacializa a tela principal,
          adiciona o painel de background a tela principal e inicializa
          o array de semaforos* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public MainScreen(){
    this.background = new Background();
    MainScreen.semaphores = new Semaphore[SIZE_ARRAY_SEMAPHORES];

    for(int i=0; i<SIZE_ARRAY_SEMAPHORES; i++)
      MainScreen.semaphores[i] = new Semaphore(1, true);
    
    this.initGUIComponents();
  }

  /* **************************************************************** 
  Metodo: initGUIComponents* 
  Funcao: inicializa a tela principal* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  private void initGUIComponents(){
    this.add(background);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(100,40);
    this.setSize(1450, 700);
    this.setVisible(true);

    background.initThreads();
  }

  /* **************************************************************** 
  Metodo: getSemaphore* 
  Funcao: retorna um objeto do array de semaforo* 
  Parametros: int index = index do objeto a ser retornado* 
  Retorno: Semaphore*
  *************************************************************** */
  public static Semaphore getSemaphore(int index){
    return semaphores[index];
  }
}