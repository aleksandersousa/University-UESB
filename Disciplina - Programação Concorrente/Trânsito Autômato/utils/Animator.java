/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
package utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animator{

  private volatile boolean running = false;
  private ArrayList<BufferedImage> frames;
  private BufferedImage sprite;
  private long previousTime, speed;
  private int currentFrame;

  /* **************************************************************** 
  Metodo: Animator* 
  Funcao: Construtor da classe Animator. Recebe um arraylist com os
          sprites dos filosofos* 
  Parametros: ArrayList<BufferedImage> frames: ArrayList contendo os 
              sprites dos filosofos* 
  Retorno: void*
  *************************************************************** */
  public Animator(ArrayList<BufferedImage> frames){
    this.frames = frames;
  }

  /* **************************************************************** 
  Metodo: setSpeed* 
  Funcao: seta a velocidade das animacoes* 
  Parametros: long speed: velocidade da animacao* 
  Retorno: void*
  *************************************************************** */
  public void setSpeed(long speed){
    this.speed = speed;
  }

  /* **************************************************************** 
  Metodo: getSprite* 
  Funcao: retorna o sprite atual da animacao* 
  Parametros: nulo* 
  Retorno: BufferedImage: sprite da animacao*
  *************************************************************** */
  public BufferedImage getSprite(){
    return this.sprite;
  }

  /* **************************************************************** 
  Metodo: update* 
  Funcao: pegar o proximo sprite do arraylist de sprites* 
  Parametros: nulo* 
  Retorno: BufferedImage: sprite da animacao*
  *************************************************************** */
  public void update(long time){
    if(running){
      if(time - previousTime >= speed){

        currentFrame++;
        try{
          sprite = frames.get(currentFrame);
        }catch(Exception e){
          currentFrame = 0;
          sprite = frames.get(currentFrame);
        }

        previousTime = time;
      }
    }
  }

  /* **************************************************************** 
  Metodo: start* 
  Funcao: inicializar as variaveis desta classe para comecar a animacao* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void start(){
    this.running = true;
    this.previousTime = 0;
    this.currentFrame = 0;
    this.sprite = frames.get(currentFrame);
  }
}