/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/09/2019* 
Ultima alteracao: 15/09/2019* 
Nome: Simulador de corrida de trens* 
Funcao: Simular processo de exclusao mutua utilizando trens.*
*************************************************************** */

package controller;

import view.BackgroundPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.concurrent.Semaphore;

public class Trem implements Runnable{
  static volatile Semaphore lock = new Semaphore(1, true);

  private BackgroundPanel panel = new BackgroundPanel();
  private RotateImage rotateImage = new RotateImage();
  private Thread thread;
  private JLabel labelTrem;
  
  int speed, width, height, xLocation, yLocation, route;
  
  /* **************************************************************** 
  Metodo: Trem* 
  Funcao: Construtor da classe Trem. Recebe o objeto trem a ser relacio-
          nado com o thread, a velocidade de deslocamento e a rota do trem* 
  Parametros: Jlabel labelTrem = objeto que representa o thread
              int speed = velocidade de deslocamento do trem
              int route = numero da rota* 
  Retorno: void*
  *************************************************************** */
  public Trem(JLabel labelTrem, int speed, int route){
    this.labelTrem = labelTrem;
    this.speed = speed;
    this.route = route;

    xLocation = labelTrem.getX();
    yLocation = labelTrem.getY();
    width = labelTrem.getWidth();
    height = labelTrem.getHeight();

    thread = new Thread(this);
    thread.start();
  }

  /* **************************************************************** 
  Metodo: run* 
  Funcao: sobreescrever o metodo run* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  @Override
  public void run(){
    switch(route){
      case 1:
        route1();
        break;
      case 2:
        route2();
        break;
      case 3:
        route3();
        break;
      case 4:
        route4();
        break;
    }
  }

  /* **************************************************************** 
  Metodo: route1* 
  Funcao: mover o trem no trajeto da rota1* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void route1(){
    while(true){
      try{
        labelTrem.setBounds(xLocation, yLocation, width, height);

        //andando verticalmente
        while(labelTrem.getY() < 250 && labelTrem.getX() == 30){
          labelTrem.setBounds(labelTrem.getX(), labelTrem.getY()+speed, width, height);
          Thread.sleep(5);
        }

        //virando para horizontal
        if(labelTrem.getY() >= 250 && labelTrem.getY() <= 252){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, 270.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()+50, labelTrem.getY()+100, height, width);
        }

        //andando horizontalmente
        while(labelTrem.getX() < 350 && (labelTrem.getY() >= 350 || labelTrem.getY() <= 352)){
          labelTrem.setBounds(labelTrem.getX()+speed, labelTrem.getY(), height, width);
          Thread.sleep(5);
        }

        //regiao critica
        lock.acquire();

        if(labelTrem.getX() >= 350 && labelTrem.getX() <= 352){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, 270.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()+100, labelTrem.getY()-100, width, height);
        }

        panel.rotateFlag();

        while((labelTrem.getY() > 40) && (labelTrem.getX() >= 450 || labelTrem.getX() <= 452)){
          labelTrem.setBounds(labelTrem.getX(), labelTrem.getY()-speed, width, height);
          Thread.sleep(5);
        }

        if(labelTrem.getY() <= 40 && labelTrem.getY() >= 38){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, 270.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()-90, labelTrem.getY()-10, height, width);
        }

        //Saindo da regiao critica
        lock.release();
        panel.undoRotateFlag();
       
        while(labelTrem.getX() > 30 && (labelTrem.getY() <= 29 || labelTrem.getY() >= 27)){
          labelTrem.setBounds(labelTrem.getX()-speed, labelTrem.getY(), height, width);
          Thread.sleep(5);
        }

        if(labelTrem.getX() <= 30 && labelTrem.getX() >= 28){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, 270.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()-3, labelTrem.getY(), width, height);
        }

      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  /* **************************************************************** 
  Metodo: route1* 
  Funcao: mover o trem no trajeto da rota2* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void route2(){
    while(true){
      try{
        labelTrem.setBounds(xLocation, yLocation, width, height);

        //caminhando verticalmente
        while(labelTrem.getY() < 250 && labelTrem.getX() == 860){
          labelTrem.setBounds(labelTrem.getX(), labelTrem.getY()+speed, width, height);
          Thread.sleep(5);
        }

        //girando para horizontal
        if(labelTrem.getY() >= 250 && labelTrem.getY() <= 252){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, 90.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()-100, labelTrem.getY()+100, height, width);
        }

        //caminhando na horizontal
        while(labelTrem.getX() > 470 && (labelTrem.getY() == 350 || labelTrem.getY() == 351)){
          labelTrem.setBounds(labelTrem.getX()-speed, labelTrem.getY(), height, width);
          Thread.sleep(5);
        }

        //regiao critica
        lock.acquire();

        if(labelTrem.getX() == 470 || labelTrem.getX() == 469){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, 90.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()-25, labelTrem.getY()-100, width, height);
        }

        panel.rotateFlag();

        while((labelTrem.getY() > 40) && (labelTrem.getX() >= 450 || labelTrem.getX() <= 452)){
          labelTrem.setBounds(labelTrem.getX(), labelTrem.getY()-speed, width, height);
          Thread.sleep(5);
        }

        if(labelTrem.getY() <= 40 && labelTrem.getY() >= 38){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, 90.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX(), labelTrem.getY()-10, height, width);
        }

        //saindo da regiao critica
        lock.release();
        panel.undoRotateFlag();
       
        while(labelTrem.getX() < 740 && (labelTrem.getY() <= 29 || labelTrem.getY() >= 27)){
          labelTrem.setBounds(labelTrem.getX()+speed, labelTrem.getY(), height, width);
          Thread.sleep(5);
        }

        if(labelTrem.getX() >= 740 && labelTrem.getX() <= 743){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, 90.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()+120, labelTrem.getY(), width, height);
        }

      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  /* **************************************************************** 
  Metodo: route1* 
  Funcao: mover o trem no trajeto da rota3* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void route3(){
    while(true){
      try{
        labelTrem.setBounds(xLocation, yLocation, width, height);

        //andando verticalmente
        while(labelTrem.getY() > 40 && labelTrem.getX() == 30){
          labelTrem.setBounds(labelTrem.getX(), labelTrem.getY()-speed, width, height);
          Thread.sleep(5);
        }

        //virando para horizontal
        if(labelTrem.getY() >= 38 && labelTrem.getY() <= 40){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, -270.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()+60, labelTrem.getY()-13, height, width);
        }

        //andando horizontalmente
        while(labelTrem.getX() < 350 && (labelTrem.getY() >= 38 || labelTrem.getY() <= 40)){
          labelTrem.setBounds(labelTrem.getX()+speed, labelTrem.getY(), height, width);
          Thread.sleep(5);
        }

        //regiao critica
        lock.acquire();

        if(labelTrem.getX() >= 350 && labelTrem.getX() <= 352){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, -270.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()+100, labelTrem.getY()+55, width, height);
        }

        panel.rotateFlag();

        while((labelTrem.getY() < 300) && (labelTrem.getX() >= 450 || labelTrem.getX() <= 452)){
          labelTrem.setBounds(labelTrem.getX(), labelTrem.getY()+speed, width, height);
          Thread.sleep(5);
        }

        if(labelTrem.getY() >= 300 && labelTrem.getY() <= 302){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, -270.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()-90, labelTrem.getY()+30, height, width);
        }

        //Saindo da regiao critica
        lock.release();
        panel.undoRotateFlag();
       
        while(labelTrem.getX() > 30 && (labelTrem.getY() >= 260 || labelTrem.getY() <= 262)){
          labelTrem.setBounds(labelTrem.getX()-speed, labelTrem.getY(), height, width);
          Thread.sleep(5);
        }

        if(labelTrem.getX() <= 30 && labelTrem.getX() >= 28){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, -270.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()-3, labelTrem.getY(), width, height);
        }

      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  /* **************************************************************** 
  Metodo: route1* 
  Funcao: mover o trem no trajeto da rota4* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void route4(){
    while(true){
      try{
        labelTrem.setBounds(xLocation, yLocation, width, height);

        //caminhando verticalmente
        while(labelTrem.getY() > 40 && labelTrem.getX() == 860){
          labelTrem.setBounds(labelTrem.getX(), labelTrem.getY()-speed, width, height);
          Thread.sleep(5);
        }

        //girando para horizontal
        if(labelTrem.getY() <= 40 && labelTrem.getY() >= 38){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, -90.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()-100, labelTrem.getY()-13, height, width);
        }

        //caminhando na horizontal
        while(labelTrem.getX() > 470 && (labelTrem.getY() == 27 || labelTrem.getY() == 29)){
          labelTrem.setBounds(labelTrem.getX()-speed, labelTrem.getY(), height, width);
          Thread.sleep(5);
        }

        //regiao critica
        lock.acquire();

        if(labelTrem.getX() == 470 || labelTrem.getX() == 469){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, 270.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()-25, labelTrem.getY()+50, width, height);
        }

        panel.rotateFlag();

        while(labelTrem.getY() < 250 && (labelTrem.getX() >= 455 || labelTrem.getX() <= 457)){
          labelTrem.setBounds(labelTrem.getX(), labelTrem.getY()+speed, width, height);
          Thread.sleep(5);
        }

        if(labelTrem.getY() >= 250 && labelTrem.getY() <= 252){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, -90.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX(), labelTrem.getY()+100, height, width);
        }

        //saindo da regiao critica
        lock.release();
        panel.undoRotateFlag();
       
        while(labelTrem.getX() < 740 && (labelTrem.getY() <= 29 || labelTrem.getY() >= 27)){
          labelTrem.setBounds(labelTrem.getX()+speed, labelTrem.getY(), height, width);
          Thread.sleep(5);
        }

        if(labelTrem.getX() >= 740 && labelTrem.getX() <= 743){
          ImageIcon icon = (ImageIcon) labelTrem.getIcon();
          BufferedImage image = (BufferedImage)((Image) icon.getImage());

          image = rotateImage.rotate(image, -90.0);

          ImageIcon rotatedIcon = new ImageIcon(image);

          labelTrem.setIcon(rotatedIcon);
          labelTrem.setBounds(labelTrem.getX()+120, labelTrem.getY(), width, height);
        }

      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  /* **************************************************************** 
  Metodo: setSpeed* 
  Funcao: receber o valor da velocidade de deslocamento alterada pela
          barra de velocidade* 
  Parametros: int speed = velocidade de deslocamento* 
  Retorno: void*
  *************************************************************** */
  public void setSpeed(int speed){
    this.speed = speed;
  }
}
