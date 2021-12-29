/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
package controllers;

import models.Sonic;
import view.Background;
import view.MainScreen;
import java.lang.Thread;
import java.awt.Image;

public class ThreadSonic extends Thread{
  private final int UP = 0;
  private final int DOWN = 1;
  private final int LEFT = 2;
  private final int RIGHT = 3;

  private Sonic sonic;
  private Background background;

  private int sleepTime = 50;
  private int fps = 30;

  private boolean shadowFlag = false;
  private boolean tailsFlag = false;

  /* **************************************************************** 
  Metodo: ThreadSonic* 
  Funcao: Construtor da classe ThreadSonic. Mover o objeto carro na tela* 
  Parametros: Image carsImage = sprites do objeto a ser movido
              Background background = JPanel para adicionar o objeto a 
              ser movido* 
  Retorno: void*
  *************************************************************** */
  public ThreadSonic(Image carsImage, Background background){
    this.background = background;
    this.sonic = new Sonic(carsImage);
  } 

  /* **************************************************************** 
  Metodo: run* 
  Funcao: sobreescrever o metodo run da superclasse Thread. adiciona
          o objeto carro ao JPanel de background e e move o objeto* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  @Override
  public void run(){
    background.addCar(sonic);
    
    while(true)
      running();
  }

  /* **************************************************************** 
  Metodo: running* 
  Funcao: mover o objeto na tela e adicionar os semaforos para evitar
          condicoes de corrida* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  private void running(){
    try{
      if(!tailsFlag){
        MainScreen.getSemaphore(33).acquire();//down semaforo G4
        //MainScreen.getSemaphore(25).release();//up semaforo G3
        tailsFlag = true;
      }

      if(!shadowFlag){
        MainScreen.getSemaphore(5).acquire();//down semaforo A6
        shadowFlag = true;
      }

      /////////////////////////////////////////////////////////////////////////////////
      //carro subindo
      sonic.setTurn(UP);//muda a animacao do carro para correr para cima

      while(sonic.getY() >= 420){//regiao B6
        sonic.setBounds(sonic.getX(), sonic.getY()-fps, sonic.getWidth(), sonic.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(11).acquire();//down semaforo B6

      while(sonic.getY() >= 255){
        sonic.setBounds(sonic.getX(), sonic.getY()-fps, sonic.getWidth(), sonic.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }

      sonic.setTurn(LEFT);//muda a animacao do carro para correr para esquerda

      while(sonic.getX() >= 970){//fim da regiao A6
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(5).availablePermits() < 1)
        MainScreen.getSemaphore(5).release();//up semaforo A6

      while(sonic.getX() >= 860){//fim da regiao B6
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(11).availablePermits() < 1)
        MainScreen.getSemaphore(11).release();//up semaforo B6

      while(sonic.getX() >= 800){//regiao A1 e D2
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(0).acquire();//down semaforo A1
      MainScreen.getSemaphore(6).acquire();//down semaforo D2

      while(sonic.getX() >= 680){//fim regiao A1
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(0).availablePermits() < 1)
        MainScreen.getSemaphore(0).release();//up semaforo A1

      while(sonic.getX() >= 470){//fim da regiao G4
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(33).availablePermits() < 1)
        MainScreen.getSemaphore(33).release();//up semaforo G4

      while(sonic.getX() >= 410){//regiao B9
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(35).acquire();//down semaforo B9

      while(sonic.getX() >= 365){
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      sonic.setTurn(UP);//muda a animacao do carro para correr para cima

      while(sonic.getY() >= 210){//fim da D2
        sonic.setBounds(sonic.getX(), sonic.getY()-fps, sonic.getWidth(), sonic.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(6).availablePermits() < 1)
        MainScreen.getSemaphore(6).release();//up semaforo D2

      while(sonic.getY() >= 140){
        sonic.setBounds(sonic.getX(), sonic.getY()-fps, sonic.getWidth(), sonic.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }

      sonic.setTurn(RIGHT);//muda a animacao do carro para correr para direita

      while(sonic.getX() <= 380){//fim da regiao B9
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(35).availablePermits() < 1)
        MainScreen.getSemaphore(35).release();//up semaforo D2

      while(sonic.getX() <= 650){//regiao A2
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(1).acquire();//down semaforo A2

      while(sonic.getX() <= 780){//fim regiao A2
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(1).availablePermits() < 1)
        MainScreen.getSemaphore(1).release();//up semaforo A2

      while(sonic.getX() <= 830){//regiao B7
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(12).acquire();//down semaforo B7

      while(sonic.getX() <= 970){//regiao A3 e fim da regiao B7
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(12).availablePermits() < 1)
        MainScreen.getSemaphore(12).release();//up semaforo B7

      MainScreen.getSemaphore(2).acquire();//down semaforo A3

      while(sonic.getX() <= 1030){
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      sonic.setTurn(UP);//muda a animacao do carro para correr para cima
      while(sonic.getY() >= 45){
        sonic.setBounds(sonic.getX(), sonic.getY()-fps, sonic.getWidth(), sonic.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }

      sonic.setTurn(LEFT);//muda a animacao do carro para correr para esquerda

      while(sonic.getX() >= 970){//regiao B8
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(13).acquire();//down semaforo B8

      while(sonic.getX() >= 680){//fim da A3
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(2).availablePermits() < 1)
        MainScreen.getSemaphore(2).release();//up semaforo A3

      while(sonic.getX() >= 320){//fim da regiao B8
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(13).availablePermits() < 1)
        MainScreen.getSemaphore(13).release();//up semaforo B8

      while(sonic.getX() >= 230){
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      ///////////////////////////////////////////////////////////////////////////////////
      //carro descendo

      sonic.setTurn(DOWN);//muda a animacao do carro para correr para baixo

      while(sonic.getY() <= 180){//regiao B2 e D1
        sonic.setBounds(sonic.getX(), sonic.getY()+fps, sonic.getWidth(), sonic.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(7).acquire();//down semaforo B2
      MainScreen.getSemaphore(17).acquire();//down semaforo D1

      while(sonic.getY() <= 330){
        sonic.setBounds(sonic.getX(), sonic.getY()+fps, sonic.getWidth(), sonic.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }

      sonic.setTurn(RIGHT);//muda a animacao do carro para correr para direita
      
      while(sonic.getX() <= 410){//fim da regiao B2 e D1
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(7).availablePermits() < 1)
        MainScreen.getSemaphore(7).release();//up semaforo B2

      if(MainScreen.getSemaphore(17).availablePermits() < 1)
        MainScreen.getSemaphore(17).release();//up semaforo D1

      while(sonic.getX() <= 460){//regiao G1
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(18).acquire();//down semaforo G1

      while(sonic.getX() <= 600){//fim regiao G1
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(18).availablePermits() < 1)
        MainScreen.getSemaphore(18).release();//up semaforo G1

      while(sonic.getX() <= 650){//regiao A4 e D3
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(3).acquire();//down semaforo A4
      MainScreen.getSemaphore(19).acquire();//down semaforo D3

      while(sonic.getX() <= 780){//fim da regiao A4 e D3
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(3).availablePermits() < 1)
        MainScreen.getSemaphore(3).release();//up semaforo A4

      if(MainScreen.getSemaphore(19).availablePermits() < 1)
        MainScreen.getSemaphore(19).release();//up semaforo D3

      while(sonic.getX() <= 850){//regiao B5
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(10).acquire();//down semaforo B5

      while(sonic.getX() <= 900){
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      sonic.setTurn(DOWN);//muda a animacao do carro para correr para baixo

      while(sonic.getY() <= 450){
        sonic.setBounds(sonic.getX(), sonic.getY()+fps, sonic.getWidth(), sonic.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }

      sonic.setTurn(LEFT);//muda a animacao do carro para correr para direita

      while(sonic.getX() >= 850){//fim da regiao regiao B5
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(10).availablePermits() < 1)
        MainScreen.getSemaphore(10).release();//up semaforo B5

      while(sonic.getX() >= 800){//regiao A5 e D4
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(4).acquire();//down semaforo A5
      MainScreen.getSemaphore(20).acquire();//down semaforo D4

      while(sonic.getX() >= 680){//fim regiao A5 e D4
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(4).availablePermits() < 1)
        MainScreen.getSemaphore(4).release();//up semaforo A5

      if(MainScreen.getSemaphore(20).availablePermits() < 1)
        MainScreen.getSemaphore(20).release();//up semaforo D4

      while(sonic.getX() >= 620){//regiao G2
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(22).acquire();//down semaforo G2

      while(sonic.getX() >= 470){//fim regiao G2
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(22).availablePermits() < 1)
        MainScreen.getSemaphore(22).release();//up semaforo G2

      while(sonic.getX() >= 410){//regiao B3
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(8).acquire();//down semaforo B3

      while(sonic.getX() >= 280){//fim da regiao B3 e comeco da D5
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(8).availablePermits() < 1)
        MainScreen.getSemaphore(8).release();//up semaforo B3

      MainScreen.getSemaphore(21).acquire();//down semaforo D5

      while(sonic.getX() >= 230){
        sonic.setBounds(sonic.getX()-fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      sonic.setTurn(DOWN);//muda a animacao do carro para correr para baixo

      while(sonic.getY() <= 560){
        sonic.setBounds(sonic.getX(), sonic.getY()+fps, sonic.getWidth(), sonic.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }

      sonic.setTurn(RIGHT);//muda a animacao do carro para correr para direita

      while(sonic.getX() <= 280){//regiao B4
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(9).acquire();//down semaforo B4

      while(sonic.getX() <= 480){//regiao G3
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(25).acquire();//down semaforo G3

      while(sonic.getX() <= 650){//regiao A6
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(5).acquire();//down semaforo A6

      while(sonic.getX() <= 780){//fim da regiao D5
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(21).availablePermits() < 1)
        MainScreen.getSemaphore(21).release();//up semaforo D5

      while(sonic.getX() <= 950){//fim da regiao B4 e comeco G4
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(9).availablePermits() < 1)
        MainScreen.getSemaphore(9).release();//up semaforo B4

      MainScreen.getSemaphore(33).acquire();//down semaforo G4

      while(sonic.getX() <= 1030){//fim da regiao G3
        sonic.setBounds(sonic.getX()+fps, sonic.getY(), sonic.getWidth(), sonic.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(25).availablePermits() < 1)
        MainScreen.getSemaphore(25).release();//up semaforo G3

    }catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  /* **************************************************************** 
  Metodo: setSleepTime* 
  Funcao: alterar o tempo de sleep deste thread para que este thread
          mova o objeto de forma mais rapida ou mais lenta* 
  Parametros: int sleepTime = tempo de sleep* 
  Retorno: void*
  *************************************************************** */
  public void setSleepTime(int sleepTime){
    switch(sleepTime){
      case 3:
        this.sleepTime = 50;
        break;
      case 2:
        this.sleepTime = 100;
        break;
      case 1:
        this.sleepTime = 300;
        break;
      case 0:
        this.sleepTime = 400;
        break;
    }
  }

  /* **************************************************************** 
  Metodo: getSonic* 
  Funcao: retorna o objeto carro* 
  Parametros: nulo* 
  Retorno: Sonic*
  *************************************************************** */
  public Sonic getSonic(){
    return this.sonic;
  }
}