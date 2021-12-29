/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
package controllers;

import models.Tails;
import view.Background;
import view.MainScreen;
import java.lang.Thread;
import java.awt.Image;

public class ThreadTails extends Thread{
  private final int UP = 0;
  private final int DOWN = 1;
  private final int LEFT = 2;
  private final int RIGHT = 3;

  private Tails tails;
  private Background background;

  private int fps = 30;
  private int sleepTime = 50;

  private boolean sonicFlag = false;

  /* **************************************************************** 
  Metodo: ThreadTails* 
  Funcao: Construtor da classe ThreadTails. Mover o objeto carro na tela* 
  Parametros: Image carsImage = sprites do objeto a ser movido
              Background background = JPanel para adicionar o objeto a 
              ser movido* 
  Retorno: void*
  *************************************************************** */
  public ThreadTails(Image carsImage, Background background){
    this.background = background;
    this.tails = new Tails(carsImage);
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
    background.addCar(tails);

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
      tails.setTurn(DOWN);//muda a animacao do carro para correr para baixo

      while(tails.getY() <= 300){//regiao G1 e fim da regiao G4, J1
        tails.setBounds(tails.getX(), tails.getY()+fps, tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }
      
      if(MainScreen.getSemaphore(33).availablePermits() < 1 && sonicFlag){
        MainScreen.getSemaphore(33).release();//up semaforo G4
      }
      else{
        sonicFlag = true;
      }

      if(MainScreen.getSemaphore(31).availablePermits() < 1)
        MainScreen.getSemaphore(31).release();//up semaforo J1

      MainScreen.getSemaphore(18).acquire();//down semaforo G1

      while(tails.getY() <= 400){//fim da regiao G1 e comeco da G2
        tails.setBounds(tails.getX(), tails.getY()+fps, tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(18).availablePermits() < 1)
        MainScreen.getSemaphore(18).release();//up semaforo G1

      MainScreen.getSemaphore(22).acquire();//down semaforo G2

      while(tails.getY() <= 500){//fim regiao G2 e comeco G3, H1, J2
        tails.setBounds(tails.getX(), tails.getY()+fps, tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(22).availablePermits() < 1)
        MainScreen.getSemaphore(22).release();//up semaforo G2

      MainScreen.getSemaphore(25).acquire();//down semaforo G3
      MainScreen.getSemaphore(26).acquire();//down semaforo H1
      MainScreen.getSemaphore(32).acquire();//down semaforo J2

      while(tails.getY() <= 550){
        tails.setBounds(tails.getX(), tails.getY()+fps, tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      tails.setTurn(RIGHT);//muda a animacao do carro para correr para direita

      while(tails.getX() <= 650){//regiao I1
        tails.setBounds(tails.getX()+fps, tails.getY(), tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(29).acquire();//down semaforo I1

      while(tails.getX() <= 780){//fim da regiao J2
        tails.setBounds(tails.getX()+fps, tails.getY(), tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(32).availablePermits() < 1)
        MainScreen.getSemaphore(32).release();//up semaforo J2

      while(tails.getX() <= 970){//fim da regiao H1
        tails.setBounds(tails.getX()+fps, tails.getY(), tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(26).availablePermits() < 1)
        MainScreen.getSemaphore(26).release();//up semaforo H1
      
      while(tails.getX() <= 1030){//fim da regiao G3 e comeco da G4
        tails.setBounds(tails.getX()+fps, tails.getY(), tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(25).availablePermits() < 1)
        MainScreen.getSemaphore(25).release();//up semaforo G3

      tails.setTurn(UP);//muda a animacao do carro para correr para cima

      MainScreen.getSemaphore(33).acquire();//down semaforo G4
      
      while(tails.getY() >= 400){//regiao H2
        tails.setBounds(tails.getX(), tails.getY()-fps, tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(27).acquire();//down semaforo H2

      while(tails.getY() >= 250){//fim da regiao compartilhada com shadow 1
        tails.setBounds(tails.getX(), tails.getY()-fps, tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      tails.setTurn(LEFT);//muda a animacao do carro para correr para esquerda

      while(tails.getX() >= 1020){//fim regiao I1
        tails.setBounds(tails.getX()-fps, tails.getY(), tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(29).availablePermits() < 1)
        MainScreen.getSemaphore(29).release();//up semaforo I1

      while(tails.getX() >= 870){//fim regiao H2
        tails.setBounds(tails.getX()-fps, tails.getY(), tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(27).availablePermits() < 1)
        MainScreen.getSemaphore(27).release();//up semaforo H2

      while(tails.getX() >= 810){//regiao I2, J1
        tails.setBounds(tails.getX()-fps, tails.getY(), tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(30).acquire();//down semaforo I2
      MainScreen.getSemaphore(31).acquire();//down semaforo J1

      while(tails.getX() >= 680){//fim da regiao I2
        tails.setBounds(tails.getX()-fps, tails.getY(), tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(30).availablePermits() < 1)
        MainScreen.getSemaphore(30).release();//up semaforo H2

      while(tails.getX() >= 560){
        tails.setBounds(tails.getX()-fps, tails.getY(), tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      while(tails.getX() >= 560){
        tails.setBounds(tails.getX()-fps, tails.getY(), tails.getWidth(), tails.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }
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
  Metodo: getTails* 
  Funcao: retorna o objeto carro* 
  Parametros: nulo* 
  Retorno: Tails*
  *************************************************************** */
  public Tails getTails(){
    return this.tails;
  }
}