/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
package controllers;

import models.Shadow;
import view.Background;
import view.MainScreen;
import java.lang.Thread;
import java.awt.Image;

public class ThreadShadow extends Thread{
  private final int UP = 0;
  private final int DOWN = 1;
  private final int LEFT = 2;
  private final int RIGHT = 3;

  private Shadow shadow;
  private Background background;

  private int fps = 30;
  private int sleepTime = 50;

  /* **************************************************************** 
  Metodo: ThreadShadow* 
  Funcao: Construtor da classe ThreadShadow. Mover o objeto carro na tela* 
  Parametros: Image carsImage = sprites do objeto a ser movido
              Background background = JPanel para adicionar o objeto a 
              ser movido* 
  Retorno: void*
  *************************************************************** */
  public ThreadShadow(Image carsImage, Background background){
    this.background = background;
    this.shadow = new Shadow(carsImage);
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
    background.addCar(shadow);

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
      shadow.setTurn(LEFT);//muda a animacao do carro para correr para esquerda

      while(shadow.getX() >= 1000){//regiao C3
        shadow.setBounds(shadow.getX()-fps, shadow.getY(), shadow.getWidth(), shadow.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(16).acquire();//down semaforo C3

      while(shadow.getX() >= 740){
        shadow.setBounds(shadow.getX()-fps, shadow.getY(), shadow.getWidth(), shadow.getHeight());
        background.repaint();
        
        Thread.sleep(sleepTime);
      }
  
      shadow.setTurn(DOWN);//muda a animacao do carro para correr para baixo

      while(shadow.getY() <= 80){//Regiao A2 e fim da A3 e C3
        shadow.setBounds(shadow.getX(), shadow.getY()+fps, shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(2).availablePermits() < 1)
        MainScreen.getSemaphore(2).release();//up semaforo A3

      if(MainScreen.getSemaphore(16).availablePermits() < 1)
        MainScreen.getSemaphore(16).release();//up semaforo C3

      MainScreen.getSemaphore(1).acquire();//down semaforo A2

      while(shadow.getY() <= 170){//fim regiao A2 e comeco da A1, F1, I2
        shadow.setBounds(shadow.getX(), shadow.getY()+fps, shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(30).acquire();//down semaforo I2

      if(MainScreen.getSemaphore(1).availablePermits() < 1)
        MainScreen.getSemaphore(1).release();//up semaforo A2

      MainScreen.getSemaphore(0).acquire();//down semaforo A1
      MainScreen.getSemaphore(24).acquire();//down semaforo F1

      while(shadow.getY() <= 280){//fim regiao A1, I2 e comeco da A4
        shadow.setBounds(shadow.getX(), shadow.getY()+fps, shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(0).availablePermits() < 1)
        MainScreen.getSemaphore(0).release();//up semaforo A1

      if(MainScreen.getSemaphore(30).availablePermits() < 1)
        MainScreen.getSemaphore(30).release();//up semaforo I2

      MainScreen.getSemaphore(3).acquire();//down semaforo A4

      while(shadow.getY() <= 400){//fim da regiao A4 e comeco da A5, F2
        shadow.setBounds(shadow.getX(), shadow.getY()+fps, shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(3).availablePermits() < 1)
        MainScreen.getSemaphore(3).release();//up semaforo A4

      MainScreen.getSemaphore(4).acquire();//down semaforo A5
      MainScreen.getSemaphore(34).acquire();//down semaforo F2

      while(shadow.getY() <= 500){//regiao A6, C1, I1 e fim da regiao A5, F1
        shadow.setBounds(shadow.getX(), shadow.getY()+fps, shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(4).availablePermits() < 1)
        MainScreen.getSemaphore(4).release();//up semaforo A5

      if(MainScreen.getSemaphore(24).availablePermits() < 1)
        MainScreen.getSemaphore(24).release();//up semaforo F1

      MainScreen.getSemaphore(5).acquire();//down semaforo A6
      MainScreen.getSemaphore(14).acquire();//down semaforo C1
      MainScreen.getSemaphore(29).acquire();//down semaforo I1

      while(shadow.getY() <= 560){//regiao compartilhada com sonic 3 e tails 2 e fim da amy 1
        shadow.setBounds(shadow.getX(), shadow.getY()+fps, shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      shadow.setTurn(RIGHT);//muda a animacao do carro para correr para direita

      while(shadow.getX() <= 780){//fim da regiao F2
        shadow.setBounds(shadow.getX()+fps, shadow.getY(), shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(34).availablePermits() < 1)
        MainScreen.getSemaphore(34).release();//up semaforo F1

      while(shadow.getX() <= 960){//fim da regiao C1
        shadow.setBounds(shadow.getX()+fps, shadow.getY(), shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(14).availablePermits() < 1)
        MainScreen.getSemaphore(14).release();//up semaforo C1

      while(shadow.getX() <= 1030){//correndo para a direita
        shadow.setBounds(shadow.getX()+fps, shadow.getY(), shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      shadow.setTurn(UP);//muda a animacao do carro para correr para cima

      while(shadow.getY() >= 420){//regiao C2
        shadow.setBounds(shadow.getX(), shadow.getY()-fps, shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(15).acquire();//down semaforo C2

      while(shadow.getY() >= 200){//fim da regiao A6, C2, I1
        shadow.setBounds(shadow.getX(), shadow.getY()-fps, shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(5).availablePermits() < 1)
        MainScreen.getSemaphore(5).release();//up semaforo A6

      if(MainScreen.getSemaphore(15).availablePermits() < 1)
        MainScreen.getSemaphore(15).release();//up semaforo C2

      if(MainScreen.getSemaphore(29).availablePermits() < 1)
        MainScreen.getSemaphore(29).release();//up semaforo I1

      while(shadow.getY() >= 200){//regiao A3
        shadow.setBounds(shadow.getX(), shadow.getY()-fps, shadow.getWidth(), shadow.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(2).acquire();//down semaforo A3

      while(shadow.getY() >= 30){//correndo para cima
        shadow.setBounds(shadow.getX(), shadow.getY()-fps, shadow.getWidth(), shadow.getHeight());
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
  Metodo: getShadow* 
  Funcao: retorna o objeto carro* 
  Parametros: nulo* 
  Retorno: Shadow*
  *************************************************************** */
  public Shadow getShadow(){
    return this.shadow;
  }
}