/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
package controllers;

import models.Knuckles;
import view.Background;
import view.MainScreen;
import java.lang.Thread;
import java.awt.Image;

public class ThreadKnuckles extends Thread{
  private final int UP = 0;
  private final int DOWN = 1;
  private final int LEFT = 2;
  private final int RIGHT = 3;

  private Knuckles knuckles;
  private Background background;

  private int fps = 30;
  private int sleepTime = 50;

  /* **************************************************************** 
  Metodo: ThreadKnuckles* 
  Funcao: Construtor da classe ThreadKnuckles. Mover o objeto carro na tela* 
  Parametros: Image carsImage = sprites do objeto a ser movido
              Background background = JPanel para adicionar o objeto a 
              ser movido* 
  Retorno: void*
  *************************************************************** */
  public ThreadKnuckles(Image carsImage, Background background){
    this.background = background;
    this.knuckles = new Knuckles(carsImage);
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
    background.addCar(knuckles);

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
      knuckles.setTurn(DOWN);//muda a animacao do carro para correr para baixo

      while(knuckles.getY() <= 70){//regiao E1, B9 e fim da regiao B8
        knuckles.setBounds(knuckles.getX(), knuckles.getY()+fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(13).availablePermits() < 1)
        MainScreen.getSemaphore(13).release();//up semaforo B8

      MainScreen.getSemaphore(28).acquire();//down semaforo E1
      MainScreen.getSemaphore(35).acquire();//down semaforo B9

      while(knuckles.getY() <= 220){
        knuckles.setBounds(knuckles.getX(), knuckles.getY()+fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      knuckles.setTurn(LEFT);//muda a animacao do carro para correr para esquerda

      while(knuckles.getX() >= 280){//comeco da B2 e fim da B9
        knuckles.setBounds(knuckles.getX()-fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(35).availablePermits() < 1)
        MainScreen.getSemaphore(35).release();//up semaforo B9

      MainScreen.getSemaphore(7).acquire();//down semaforo B2

      while(knuckles.getX() >= 230){//regiao compartilhada com sonic 4
        knuckles.setBounds(knuckles.getX()-fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      knuckles.setTurn(DOWN);//muda a animacao do carro para correr para baixo

      while(knuckles.getY() <= 340){
        knuckles.setBounds(knuckles.getX(), knuckles.getY()+fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      knuckles.setTurn(RIGHT);//muda a animacao do carro para correr para direita

      while(knuckles.getX() <= 270){//fim da regiao E1
        knuckles.setBounds(knuckles.getX()+fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(28).availablePermits() < 1)
        MainScreen.getSemaphore(28).release();//up semaforo E1

      while(knuckles.getX() <= 320){
        knuckles.setBounds(knuckles.getX()+fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      knuckles.setTurn(DOWN);//muda a animacao do carro para correr para baixo

      while(knuckles.getY() <= 400){//fim da regiao B2 e E1 e comeco da B3
        knuckles.setBounds(knuckles.getX(), knuckles.getY()+fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(7).availablePermits() < 1)
        MainScreen.getSemaphore(7).release();//up semaforo B2

      MainScreen.getSemaphore(8).acquire();//down semaforo B3

      while(knuckles.getY() <= 500){//fim da regiao B3 e comeco da B4 e E2
        knuckles.setBounds(knuckles.getX(), knuckles.getY()+fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(8).availablePermits() < 1)
        MainScreen.getSemaphore(8).release();//up semaforo B3

      MainScreen.getSemaphore(9).acquire();//down semaforo B4
      MainScreen.getSemaphore(23).acquire();//down semaforo E2

      while(knuckles.getY() <= 560){
        knuckles.setBounds(knuckles.getX(), knuckles.getY()+fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      knuckles.setTurn(RIGHT);//muda a animacao do carro para correr para direita

      while(knuckles.getX() <= 450){//regiao H1
        knuckles.setBounds(knuckles.getX()+fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(26).acquire();//down semaforo H1

      while(knuckles.getX() <= 650){//regiao C1
        knuckles.setBounds(knuckles.getX()+fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      MainScreen.getSemaphore(14).acquire();//down semaforo C1
      
      while(knuckles.getX() <= 780){//fim da regiao E2
        knuckles.setBounds(knuckles.getX()+fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(23).availablePermits() < 1)
        MainScreen.getSemaphore(23).release();//up semaforo E2
      
      while(knuckles.getX() <= 910){
        knuckles.setBounds(knuckles.getX()+fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      /////////////////////////////////////////////////////////////////////////////
      //carro subindo
      knuckles.setTurn(UP);

      while(knuckles.getY() >= 530){//fim da regiao B4, C1, H1 e comeco da B5
        knuckles.setBounds(knuckles.getX(), knuckles.getY()-fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(9).availablePermits() < 1)
        MainScreen.getSemaphore(9).release();//up semaforo B4

      if(MainScreen.getSemaphore(14).availablePermits() < 1)
        MainScreen.getSemaphore(14).release();//up semaforo C1

      if(MainScreen.getSemaphore(26).availablePermits() < 1)
        MainScreen.getSemaphore(26).release();//up semaforo H1

      MainScreen.getSemaphore(10).acquire();//down semaforo B5

      while(knuckles.getY() >= 340){
        knuckles.setBounds(knuckles.getX(), knuckles.getY()-fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      knuckles.setTurn(RIGHT);//muda a animacao do carro para correr para direita

      while(knuckles.getX() <= 970){//fim da regiao B5 e comeco da B6, C2, H2
        knuckles.setBounds(knuckles.getX()+fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(10).availablePermits() < 1)
        MainScreen.getSemaphore(10).release();//up semaforo B5

      MainScreen.getSemaphore(11).acquire();//down semaforo B6
      MainScreen.getSemaphore(15).acquire();//down semaforo C2
      MainScreen.getSemaphore(27).acquire();//down semaforo H2

      while(knuckles.getX() <= 1030){//correndo para a direita
        knuckles.setBounds(knuckles.getX()+fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      knuckles.setTurn(UP);
      while(knuckles.getY() >= 260){//fim da regiao compartilhada com shadow 2
        knuckles.setBounds(knuckles.getX(), knuckles.getY()-fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      knuckles.setTurn(LEFT);//muda a animacao do carro para correr para esquerda

      while(knuckles.getX() >= 950){//fim da regiao C2
        knuckles.setBounds(knuckles.getX()-fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(15).availablePermits() < 1)
        MainScreen.getSemaphore(15).release();//up semaforo C2

      while(knuckles.getX() >= 930){

        knuckles.setBounds(knuckles.getX()-fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      knuckles.setTurn(UP);//muda animacao do carro para correr para cima

      while(knuckles.getY() >= 210){//fim da regiao B6, H2 e comeco da B7
        knuckles.setBounds(knuckles.getX(), knuckles.getY()-fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(11).availablePermits() < 1)
        MainScreen.getSemaphore(11).release();//up semaforo B6i

      if(MainScreen.getSemaphore(27).availablePermits() < 1)
        MainScreen.getSemaphore(27).release();//up semaforo B6

      MainScreen.getSemaphore(12).acquire();//down semaforo B7

      while(knuckles.getY() >= 100){//fim da regiao B7 e comeco da B8 e C3
        knuckles.setBounds(knuckles.getX(), knuckles.getY()-fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(12).availablePermits() < 1)
        MainScreen.getSemaphore(12).release();//up semaforo B7

      MainScreen.getSemaphore(13).acquire();//down semaforo B8
      MainScreen.getSemaphore(16).acquire();//down semaforo C3

      while(knuckles.getY() >= 40){
        knuckles.setBounds(knuckles.getX(), knuckles.getY()-fps, knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      knuckles.setTurn(LEFT);//muda a animacao do carro para correr para esquerda

      while(knuckles.getX() >= 700){//fim da regiao C3
        knuckles.setBounds(knuckles.getX()-fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
        background.repaint();

        Thread.sleep(sleepTime);
      }

      if(MainScreen.getSemaphore(16).availablePermits() < 1)
        MainScreen.getSemaphore(16).release();//up semaforo C3

      while(knuckles.getX() >= 360){//fim da regiao compartilhada com sonic 2
        knuckles.setBounds(knuckles.getX()-fps, knuckles.getY(), knuckles.getWidth(), knuckles.getHeight());
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
  Metodo: getKnuckles* 
  Funcao: retorna o objeto carro* 
  Parametros: nulo* 
  Retorno: Knuckles*
  *************************************************************** */
  public Knuckles getKnuckles(){
    return this.knuckles;
  }
}