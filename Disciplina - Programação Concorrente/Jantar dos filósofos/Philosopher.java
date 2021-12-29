/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/10/2019* 
Ultima alteracao: 12/10/2019* 
Nome: jantar dos filosofos* 
Funcao: Simular o problema classico jantar dos filosofos.
*************************************************************** */
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Philosopher extends JLabel{
  private final int PENSAR = 0; 
  private final int FOME = 1; 
  private final int COMER = 2;

  private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
  private ArrayList<BufferedImage> spritesEating = new ArrayList<BufferedImage>();

  private Animator philosopher, philosopher2;
  private Image philosopherImage;
  private BufferedImage spriteSheet;
  private SpriteSheet spriteSheetClass;
  private int index, state;

  /* **************************************************************** 
  Metodo: Philosopher* 
  Funcao: Construtor da classe Philosopher. Le a imagem do filosofo e
          posiciona na tela de acordo com index* 
  Parametros: Image philosopherImage: imagem do filosofo
              int index: index do filosofo* 
  Retorno: void*
  *************************************************************** */ 
  public Philosopher(Image philosopherImage, int index){
    this.philosopherImage = philosopherImage;
    this.index = index;

    switch(index){
      case 0:
        this.setBounds(420, 95, 160, 200);//filosofo preto
        break;
      case 1:
        this.setBounds(250, 190, 180, 180);//filosofo vermelho
        break;
      case 2:
        this.setBounds(285, 355, 180, 200);//filosofo cinza
        break;
      case 3:
        this.setBounds(540, 340, 200, 210);//filosofo azul
        break;
      case 4:
        this.setBounds(580, 200, 200, 200);//filosofo rosa
        break;
    }

    animatePhilosofer();
  }

  /* **************************************************************** 
  Metodo: paintComponent* 
  Funcao: sobreescrever o metodo paintComponent da superclasse* 
  Parametros: Graphics g: necessario para sobreescrever o metodo*  
  Retorno: void*
  *************************************************************** */
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    
    thinking(g);
    hungry(g);
    eating(g);
  }

  /* **************************************************************** 
  Metodo: animatePhilosofer* 
  Funcao: ler o spritesheet do filosofo de acordo com o index para deixar
          a animacao pronta para iniciar* 
  Parametros: nulo*  
  Retorno: void*
  *************************************************************** */
  public void animatePhilosofer(){
    switch(index){
      case 0://filosofo vermelho
        spriteSheet = (BufferedImage)philosopherImage;
        spriteSheetClass = new SpriteSheet(spriteSheet);

        try{
          sprites.add(spriteSheetClass.grabSprite(0, 0, 199, 323));
          sprites.add(spriteSheetClass.grabSprite(199, 0, 183, 323));
          sprites.add(spriteSheetClass.grabSprite(381, 0, 220 , 323));
          sprites.add(spriteSheetClass.grabSprite(601, 0, 206, 323));
          sprites.add(spriteSheetClass.grabSprite(801, 0, 199, 323));
        }catch(Exception e){
          e.printStackTrace();
        }

        for(int i=2; i<5; i++){
          spritesEating.add(sprites.get(i));
        }

        philosopher = new Animator(sprites);
        philosopher.start();
        philosopher.setSpeed(200);

        philosopher2 = new Animator(spritesEating);
        philosopher2.start();
        philosopher2.setSpeed(200);
        break;
      case 1://filosofo preto
        spriteSheet = (BufferedImage)philosopherImage;
        spriteSheetClass = new SpriteSheet(spriteSheet);

        try{
          sprites.add(spriteSheetClass.grabSprite(0, 0, 264, 292));
          sprites.add(spriteSheetClass.grabSprite(244, 0, 264, 292));
          sprites.add(spriteSheetClass.grabSprite(532, 0, 254 , 292));
          sprites.add(spriteSheetClass.grabSprite(780, 0, 235, 292));
          sprites.add(spriteSheetClass.grabSprite(1009, 0, 238, 292));
        }catch(Exception e){
          e.printStackTrace();
        }

        for(int i=2; i<5; i++){
          spritesEating.add(sprites.get(i));
        }

        philosopher = new Animator(sprites);
        philosopher.start();
        philosopher.setSpeed(200);

        philosopher2 = new Animator(spritesEating);
        philosopher2.start();
        philosopher2.setSpeed(200);
        break;
      case 2://filosofo azul
        spriteSheet = (BufferedImage)philosopherImage;
        spriteSheetClass = new SpriteSheet(spriteSheet);

        try{
          sprites.add(spriteSheetClass.grabSprite(0, 0, 268, 300));
          sprites.add(spriteSheetClass.grabSprite(271, 0, 284, 300));
          sprites.add(spriteSheetClass.grabSprite(558, 0, 260 , 300));
          sprites.add(spriteSheetClass.grabSprite(811, 0, 236, 300));
          sprites.add(spriteSheetClass.grabSprite(1047, 0, 253, 300));
        }catch(Exception e){
          e.printStackTrace();
        }

        for(int i=2; i<5; i++){
          spritesEating.add(sprites.get(i));
        }

        philosopher = new Animator(sprites);
        philosopher.start();
        philosopher.setSpeed(200);

        philosopher2 = new Animator(spritesEating);
        philosopher2.start();
        philosopher2.setSpeed(200); 
        break;
      case 3://filosofo cinza
        spriteSheet = (BufferedImage)philosopherImage;
        spriteSheetClass = new SpriteSheet(spriteSheet);

        try{
          sprites.add(spriteSheetClass.grabSprite(1026, 0, 274, 300));
          sprites.add(spriteSheetClass.grabSprite(747, 0, 269 , 300));
          sprites.add(spriteSheetClass.grabSprite(486, 0, 262, 300));
          sprites.add(spriteSheetClass.grabSprite(250, 0, 238, 300));
          sprites.add(spriteSheetClass.grabSprite(0, 0, 250, 300));
        }catch(Exception e){
          e.printStackTrace();
        }

        for(int i=2; i<5; i++){
          spritesEating.add(sprites.get(i));
        }

        philosopher = new Animator(sprites);
        philosopher.start();
        philosopher.setSpeed(200);

        philosopher2 = new Animator(spritesEating);
        philosopher2.start();
        philosopher2.setSpeed(200);
        break;
      case 4://filosofo rosa
        spriteSheet = (BufferedImage)philosopherImage;
        spriteSheetClass = new SpriteSheet(spriteSheet);

        try{
          sprites.add(spriteSheetClass.grabSprite(1032, 0, 268, 300));
          sprites.add(spriteSheetClass.grabSprite(732, 0, 290, 300));
          sprites.add(spriteSheetClass.grabSprite(477, 0, 249 , 300));
          sprites.add(spriteSheetClass.grabSprite(258, 0, 209, 292));
          sprites.add(spriteSheetClass.grabSprite(0, 0, 264, 292));
        }catch(Exception e){
          e.printStackTrace();
        }

        for(int i=2; i<5; i++){
          spritesEating.add(sprites.get(i));
        }

        philosopher = new Animator(sprites);
        philosopher.start();
        philosopher.setSpeed(200);

        philosopher2 = new Animator(spritesEating);
        philosopher2.start();
        philosopher2.setSpeed(200); 
        break;
    }
  }

  /* **************************************************************** 
  Metodo: eating* 
  Funcao: animacao comer* 
  Parametros: Graphics g: objeto para desenhar a animacao na tela*  
  Retorno: void*
  *************************************************************** */
  public void eating(Graphics g){//animacao comendo
    if(state == COMER){
      philosopher2.update(System.currentTimeMillis());
      g.drawImage(philosopher2.getSprite(), 0, 0, this.getWidth(), this.getHeight(), null);
      repaint();
    }
  }

  /* **************************************************************** 
  Metodo: thinking* 
  Funcao: animacao pensar* 
  Parametros: Graphics g: objeto para desenhar a animacao na tela*  
  Retorno: void*
  *************************************************************** */
  public void thinking(Graphics g){//animacao pensando
    if(state == PENSAR){
      g.drawImage(philosopher.getSprite0(), 0, 0, this.getWidth(), this.getHeight(), null);
      repaint();
    }
  }

  /* **************************************************************** 
  Metodo: hungry* 
  Funcao: animacao faminto* 
  Parametros: Graphics g: objeto para desenhar a animacao na tela*  
  Retorno: void*
  *************************************************************** */
  public void hungry(Graphics g){//animacao com fome
    if(state == FOME){
      g.drawImage(philosopher.getSprite1(), 0, 0, this.getWidth(), this.getHeight(), null);
      repaint();
    }
  }

  /* **************************************************************** 
  Metodo: setState* 
  Funcao: seta o estado do filosfo* 
  Parametros: int state: numero do estado do fiolsofo*  
  Retorno: void*
  *************************************************************** */
  public void setState(int state){
    this.state = state;
  }
}