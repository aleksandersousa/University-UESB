/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
package models;

import utils.Animator;
import utils.SpriteSheet;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Amy extends JLabel{
  private final int UP = 0;
  private final int DOWN = 1;
  private final int LEFT = 2;
  private final int RIGHT = 3;

  private Image carsImage;
  private Animator upAnimator, downAnimator, leftAnimator, rightAnimator;
  private ArrayList<BufferedImage> upSprites, downSprites, leftSprites, rightSprites;

  private int speed = 50;
  private int turn = -1;
  private int widthUpDown = 50;
  private int widthLeftRight = 50;

  /* **************************************************************** 
  Metodo: Amy* 
  Funcao: Construtor da classe Amy. Inicializa os arrays de sprites e 
          posiciona o objeto na tela* 
  Parametros: Image carsImage = imagem do objeto* 
  Retorno: void*
  *************************************************************** */
  public Amy(Image carsImage){
    this.setBounds(230, 560, widthUpDown, 60);

    this.upSprites = new ArrayList<BufferedImage>();
    this.downSprites = new ArrayList<BufferedImage>();
    this.leftSprites = new ArrayList<BufferedImage>();
    this.rightSprites = new ArrayList<BufferedImage>();
    this.carsImage = carsImage;

    this.animate();
  }

  /* **************************************************************** 
  Metodo: paintComponent* 
  Funcao: sobreescrever o metodo paintComponent da superclasse. Pinta os
          sprites do objeto* 
  Parametros: Graphics g = objeto Graphics necessario para sobreescrever
              o metodo* 
  Retorno: void*
  *************************************************************** */
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);

    if(turn == UP)
      paintUp(g);
    else if(turn == DOWN)
      paintDown(g);
    else if(turn == LEFT)
      paintLeft(g);
    else if(turn == RIGHT)
      paintRight(g);
  }

  /* **************************************************************** 
  Metodo: animate* 
  Funcao: adiciona os sprites aos respectivos arrays e inicializa os
          objetos da classe Animator, reponsaveis pela animacao* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  private void animate(){
    BufferedImage spriteSheet = (BufferedImage)carsImage;
    SpriteSheet spriteSheetClass = new SpriteSheet(spriteSheet);

    upSprites.add(spriteSheetClass.grabSprite(0, 135, 19, 25));
    upSprites.add(spriteSheetClass.grabSprite(28, 135, 16, 25));
    upSprites.add(spriteSheetClass.grabSprite(52, 135, 16, 25));

    downSprites.add(spriteSheetClass.grabSprite(0, 198, 19, 27));
    downSprites.add(spriteSheetClass.grabSprite(29, 198, 16, 27));
    downSprites.add(spriteSheetClass.grabSprite(53, 198, 16, 27));

    rightSprites.add(spriteSheetClass.grabSprite(0, 167, 19, 24));
    rightSprites.add(spriteSheetClass.grabSprite(26, 167, 19, 24));
    rightSprites.add(spriteSheetClass.grabSprite(51, 167, 19, 24));

    leftSprites.add(spriteSheetClass.grabSprite(0, 230, 22, 26));
    leftSprites.add(spriteSheetClass.grabSprite(26, 230, 19, 26));
    leftSprites.add(spriteSheetClass.grabSprite(51, 230, 19, 26));

    upAnimator = new Animator(upSprites);
    upAnimator.start();
    upAnimator.setSpeed(speed);

    downAnimator = new Animator(downSprites);
    downAnimator.start();
    downAnimator.setSpeed(speed);

    leftAnimator = new Animator(leftSprites);
    leftAnimator.start();
    leftAnimator.setSpeed(speed);

    rightAnimator = new Animator(rightSprites);
    rightAnimator.start();
    rightAnimator.setSpeed(speed);
  }

  /* **************************************************************** 
  Metodo: paintUp* 
  Funcao: pinta os sprites para o objeto correr para cima* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  private void paintUp(Graphics g){
    this.setBounds(this.getX(), this.getY(), widthUpDown, 70);

    upAnimator.update(System.currentTimeMillis());
    g.drawImage(upAnimator.getSprite(), 0, 0, this.getWidth(), this.getHeight(), null);
    this.repaint();
  }

  /* **************************************************************** 
  Metodo: paintDown* 
  Funcao: pinta os sprites para o objeto correr para baixo* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  private void paintDown(Graphics g){
    this.setBounds(this.getX(), this.getY(), widthUpDown, 70);

    downAnimator.update(System.currentTimeMillis());
    g.drawImage(downAnimator.getSprite(), 0, 0, this.getWidth(), this.getHeight(), null);
    this.repaint();
  }

  /* **************************************************************** 
  Metodo: paintUp* 
  Funcao: pinta os sprites para o objeto correr para a esquerda* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  private void paintLeft(Graphics g){
    this.setBounds(this.getX(), this.getY(), widthLeftRight, 60);

    leftAnimator.update(System.currentTimeMillis());
    g.drawImage(leftAnimator.getSprite(), 0, 0, this.getWidth(), this.getHeight(), null);
    this.repaint();
  }

  /* **************************************************************** 
  Metodo: paintRight* 
  Funcao: pinta os sprites para o objeto correr para direita* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  private void paintRight(Graphics g){
    this.setBounds(this.getX(), this.getY(), widthLeftRight, 60);

    rightAnimator.update(System.currentTimeMillis());
    g.drawImage(rightAnimator.getSprite(), 0, 0, this.getWidth(), this.getHeight(), null);
    this.repaint();
  }

  /* **************************************************************** 
  Metodo: setTurn* 
  Funcao: seta a variavel turn* 
  Parametros: int turn = indica qual sprite devera ser 
              pintado(up, down, left, right)* 
  Retorno: void*
  *************************************************************** */
  public void setTurn(int turn){
    this.turn = turn;
  } 

  /* **************************************************************** 
  Metodo: setSpeed* 
  Funcao: seta a variavel speed* 
  Parametros: int speed = indica qual a velocidade de alteracao dos sprites* 
  Retorno: void*
  *************************************************************** */
  public void setSpeed(int speed){
    switch(speed){
      case 3:
        this.downAnimator.setSpeed(50);
        this.upAnimator.setSpeed(50);
        this.leftAnimator.setSpeed(50);
        this.rightAnimator.setSpeed(50);
        break;
      case 2:
        this.downAnimator.setSpeed(100);
        this.upAnimator.setSpeed(100);
        this.leftAnimator.setSpeed(100);
        this.rightAnimator.setSpeed(100);
        break;
      case 1:
        this.downAnimator.setSpeed(150);
        this.upAnimator.setSpeed(150);
        this.leftAnimator.setSpeed(150);
        this.rightAnimator.setSpeed(150);
        break;
      case 0:
        this.downAnimator.setSpeed(200);
        this.upAnimator.setSpeed(200);
        this.leftAnimator.setSpeed(200);
        this.rightAnimator.setSpeed(200);
        break;
    }
  }
}