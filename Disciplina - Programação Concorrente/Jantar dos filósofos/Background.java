/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/10/2019* 
Ultima alteracao: 12/10/2019* 
Nome: jantar dos filosofos* 
Funcao: Simular o problema classico jantar dos filosofos.
*************************************************************** */
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class Background extends JPanel{
  static private ArrayList<JSlider> sliderArray = new ArrayList<JSlider>();
  static private ArrayList<Fork> forks = new ArrayList<Fork>();

  private Image backgroundImage, tableImage;
  private Fork fork;

  /* **************************************************************** 
  Metodo: Background* 
  Funcao: Construtor da classe Background. Recebe as imagens de back-
          ground, mesa e do garfo, instancia e adiciona o label do 
          garfo a este panel* 
  Parametros: Image backgroundImage: imagem de background
              Image tableImage: imagem da mesa
              ArrayList<Image> forkImages: arraylist com as imagens 
                do garfo* 
  Retorno: void*
  *************************************************************** */
  public Background(Image backgroundImage, Image tableImage, ArrayList<Image> forkImages){
    this.backgroundImage = backgroundImage;
    this.tableImage = tableImage;

    for(int i=0; i<5; i++){
      switch(i){
        case 0:
          fork = new Fork(forkImages.get(i), 400, 240, 100, 80);
          break;
        case 1:
          fork = new Fork(forkImages.get(i), 370, 310, 80, 90);
          break;
        case 2:
          fork = new Fork(forkImages.get(i), 455, 400, 100, 80);
          break;
        case 3:
          fork = new Fork(forkImages.get(i), 560, 310, 80, 90);
          break;
        case 4:
          fork = new Fork(forkImages.get(i), 510, 240, 100, 80);
          break;
      }

      forks.add(fork);
      add(forks.get(i));
    }

    setLayout(null);
    speedBar();
  }

  /* **************************************************************** 
  Metodo: Background* 
  Funcao: Construtor vazio da classe Background* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public Background(){

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

    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
    g.drawImage(tableImage, 280, 120, 450, 460, null);
  }

  /* **************************************************************** 
  Metodo: speedBar* 
  Funcao: cria e adiciona a este panel as barras de velocidade* 
  Parametros: nulo*  
  Retorno: void*
  *************************************************************** */
  public void speedBar(){
    final int X_SLIDER_SIZE = 150;
    final int Y_SLIDER_SIZE = 25;
    final int X_SLIDER_LOCATION = 150;
    final int Y_SLIDER_LOCATION = 30;
    final int CONST_LOCATION = 180;

    JLabel labelPensar = new JLabel("Pensar");
    JLabel labelComer = new JLabel("Comer");

    labelPensar.setFont(new Font("pensar", Font.BOLD+Font.ITALIC, 25));
    labelPensar.setForeground(Color.yellow);
    labelPensar.setBounds(30, Y_SLIDER_LOCATION, 100, 20);

    labelComer.setFont(new Font("comer", Font.BOLD+Font.ITALIC, 25));
    labelComer.setForeground(Color.yellow);
    labelComer.setBounds(30, Y_SLIDER_LOCATION*2, 100, 20);

    add(labelPensar);
    add(labelComer);

    for(int i=0; i<10; i++){
      JSlider slider = new JSlider(0, X_SLIDER_SIZE, Y_SLIDER_SIZE);

      if(i==0){
        slider.setBounds(X_SLIDER_LOCATION, Y_SLIDER_LOCATION, X_SLIDER_SIZE, Y_SLIDER_SIZE);
        slider.setBackground(Color.black);
      }
      else if(i==5){
        slider.setBounds(X_SLIDER_LOCATION, Y_SLIDER_LOCATION*2, X_SLIDER_SIZE, Y_SLIDER_SIZE);
        slider.setBackground(Color.black);
      }
      else if(i==1){
        slider.setBounds(X_SLIDER_LOCATION+(CONST_LOCATION*(i%5)), Y_SLIDER_LOCATION, X_SLIDER_SIZE, Y_SLIDER_SIZE);
        slider.setBackground(Color.red);
      }
      else if(i==6){
        slider.setBounds(X_SLIDER_LOCATION+(CONST_LOCATION*(i%5)), Y_SLIDER_LOCATION*2, X_SLIDER_SIZE, Y_SLIDER_SIZE);
        slider.setBackground(Color.red);
      }
      else if(i==2){
        slider.setBounds(X_SLIDER_LOCATION+(CONST_LOCATION*(i%5)), Y_SLIDER_LOCATION, X_SLIDER_SIZE, Y_SLIDER_SIZE);
        slider.setBackground(Color.pink);
      }
      else if(i==7){
        slider.setBounds(X_SLIDER_LOCATION+(CONST_LOCATION*(i%5)), Y_SLIDER_LOCATION*2, X_SLIDER_SIZE, Y_SLIDER_SIZE);
        slider.setBackground(Color.pink);
      }
      else if(i==3){
        slider.setBounds(X_SLIDER_LOCATION+(CONST_LOCATION*(i%5)), Y_SLIDER_LOCATION, X_SLIDER_SIZE, Y_SLIDER_SIZE);
        slider.setBackground(Color.gray);
      }
      else if(i==8){
        slider.setBounds(X_SLIDER_LOCATION+(CONST_LOCATION*(i%5)), Y_SLIDER_LOCATION*2, X_SLIDER_SIZE, Y_SLIDER_SIZE);
        slider.setBackground(Color.gray);
      }
      else if(i==4){
        slider.setBounds(X_SLIDER_LOCATION+(CONST_LOCATION*(i%5)), Y_SLIDER_LOCATION, X_SLIDER_SIZE, Y_SLIDER_SIZE);
        slider.setBackground(Color.blue);
      }
      else if(i==9){
        slider.setBounds(X_SLIDER_LOCATION+(CONST_LOCATION*(i%5)), Y_SLIDER_LOCATION*2, X_SLIDER_SIZE, Y_SLIDER_SIZE);
        slider.setBackground(Color.blue);
      } 

      slider.setMaximum(5);
      slider.setMinimum(1);
      slider.setValue(1);

      sliderArray.add(slider);
      add(slider);
      repaint();
      revalidate();
    }
  }

  /* **************************************************************** 
  Metodo: getSliderArray* 
  Funcao: retorna um arraylist contendo as barras de velocidade* 
  Parametros: nulo*  
  Retorno: ArrayList<JSlider>: arraylist contendo as barras de velo-
           cidade*
  *************************************************************** */
  public ArrayList<JSlider> getSliderArray(){
    return Background.sliderArray;
  }

  /* **************************************************************** 
  Metodo: getForks* 
  Funcao: retorna um arraylist contendo as imagens do garfo* 
  Parametros: nulo*  
  Retorno: ArrayList<Fork>: arraylist contendo as do garfo*
  *************************************************************** */
  public Fork getFork(int i){
    return Background.forks.get(i);
  }
}