/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/09/2019* 
Ultima alteracao: 15/09/2019* 
Nome: Simulador de corrida de trens* 
Funcao: Simular processo de exclusao mutua utilizando trens.*
*************************************************************** */

package view;

import controller.RotateImage;
import controller.Trem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BackgroundPanel extends JPanel{
  private static final int LARG_SIZE = 80;
  private static final int X_LOCATION = 20;
  private static final int Y_LOCATION = 20;
  private static final int RAIL_VERTICAL_SIZE = 400;
  private static final int RAIL_HORIZONTAL_SIZE = 750;

  private static JLabel labelFlag1, labelFlag2, labelTrem;
  private static ArrayList<PanelTunel> tunelArray = new ArrayList<PanelTunel>();

  private RotateImage rotateImage = new RotateImage();
  private Image backgroundImage, railVerticalImage, railHorizontalImage;
  private ArrayList<JLabel> tremArray = new ArrayList<JLabel>();
  private ArrayList<JSlider> sliderArray = new ArrayList<JSlider>();
  private int x_label_location, y_label_location, y_label_size;

  /* **************************************************************** 
  Metodo: BackgroundPanel* 
  Funcao: Construtor da classe BackgroundPanel. Recebe e inicializa 
          as imagens do background e dos trilhos* 
  Parametros: Image backgroundImage = imagem do background
              Image railHorizontalImage = imagem horizontal do trilho* 
  Retorno: void*
  *************************************************************** */
  public BackgroundPanel(Image backgroundImage, Image railVerticalImage, Image railHorizontalImage){
    this.backgroundImage = backgroundImage;
    this.railHorizontalImage = railHorizontalImage;
    this.railVerticalImage = railVerticalImage;

    this.setLayout(null);
  }

  /* **************************************************************** 
  Metodo: BackgroundPanel* 
  Funcao: Construtor vazio da classe BackgroundPanel* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public BackgroundPanel(){

  }

  /* **************************************************************** 
  Metodo: paintComponent* 
  Funcao: sobreescreve o metodo paintComponent* 
  Parametros: Graphics g = objeto do tipo Graphics necessario para 
              sobreescrever o metodo paintComponent* 
  Retorno: void*
  *************************************************************** */
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);

    //trilhos verticais
    //primeiro trilho
    g.drawImage(railVerticalImage, X_LOCATION, Y_LOCATION, LARG_SIZE, RAIL_VERTICAL_SIZE, null);

    //terceiro
    x_label_location = X_LOCATION+RAIL_HORIZONTAL_SIZE+LARG_SIZE;
    g.drawImage(railVerticalImage, x_label_location, Y_LOCATION, LARG_SIZE, RAIL_VERTICAL_SIZE, null);

    //trilho do meio
    x_label_location = (X_LOCATION + LARG_SIZE/2) + RAIL_HORIZONTAL_SIZE/2;
    y_label_location = LARG_SIZE+10;
    y_label_size = RAIL_VERTICAL_SIZE - LARG_SIZE*2 + Y_LOCATION;
    g.drawImage(railVerticalImage, x_label_location, y_label_location, LARG_SIZE, y_label_size, null);

    //trihos horizontais
    //segundo trilho
    x_label_location = X_LOCATION+LARG_SIZE;
    g.drawImage(railHorizontalImage, x_label_location, Y_LOCATION, RAIL_HORIZONTAL_SIZE, LARG_SIZE, null);

    //quarto trilho
    x_label_location = X_LOCATION+LARG_SIZE;
    y_label_location = Y_LOCATION+(RAIL_VERTICAL_SIZE-LARG_SIZE);
    g.drawImage(railHorizontalImage, x_label_location, y_label_location, RAIL_HORIZONTAL_SIZE, LARG_SIZE, null);

  }

  /* **************************************************************** 
  Metodo: setTunelCoordinates* 
  Funcao: Posicionar na tela e criar os tuneis* 
  Parametros: int x1 = coodernada x de localizacao
              int y1 = coodernada y de localizacao
              int x2 = largura
              int x1 = altura
              Image tunelImage = imagem do tunel* 
  Retorno: void*
  *************************************************************** */
  public void setTunelCoordinates(int x1, int y1, int x2, int y2, Image tunelImage){
    PanelTunel tunel = new PanelTunel(x1, y1, x2, y2, tunelImage);
    tunelArray.add(tunel);

    this.add(tunel);
    this.repaint();
    this.revalidate();

    addFlag();
  }

  /* **************************************************************** 
  Metodo: addFlag* 
  Funcao: Posicionar na tela e criar as bandeiras* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void addFlag(){
    Image flagImage = null;
    Image flagImage2 = null;

    try{
      flagImage = ImageIO.read(new File("Imagens/BandeiraInvertida.png"));

      BufferedImage image = (BufferedImage) flagImage;
      image = rotateImage.rotate(image, 270.0);

      ImageIcon iconFlagImage = new ImageIcon(image);

      labelFlag1 = new JLabel(iconFlagImage);
      labelFlag1.setBounds(30, 0, 20, 40);

      tunelArray.get(0).add(labelFlag1);
      tunelArray.get(0).repaint();
      tunelArray.get(0).revalidate();

      if(tunelArray.size() > 1){
        flagImage2 = ImageIO.read(new File("Imagens/Bandeira.png"));

        BufferedImage image2 = (BufferedImage) flagImage2;
        image2 = rotateImage.rotate(image2, 270.0);

        ImageIcon iconFlagImage2 = new ImageIcon(image2);

        labelFlag2 = new JLabel(iconFlagImage2);
        labelFlag2.setBounds(30, 0, 20, 40);

        tunelArray.get(1).add(labelFlag2);
        tunelArray.get(1).repaint();
        tunelArray.get(1).revalidate();
      }
    }catch(IOException e){
      e.printStackTrace();
    }
  }

  /* **************************************************************** 
  Metodo: rotateFlag* 
  Funcao: rotacionar as imagens das bandeiras* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void rotateFlag(){
    ImageIcon icon = (ImageIcon) labelFlag2.getIcon();
    BufferedImage image = (BufferedImage)((Image) icon.getImage());
    
    image = rotateImage.rotate(image, 90.0);
    
    ImageIcon rotatedIcon = new ImageIcon(image);
    
    labelFlag1.setIcon(rotatedIcon);
    labelFlag2.setIcon(rotatedIcon);
  }

  /* **************************************************************** 
  Metodo: undoRotateFlag* 
  Funcao: volta as bandeiras ao estado original* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void undoRotateFlag(){
    ImageIcon icon = (ImageIcon) labelFlag2.getIcon();
    BufferedImage image = (BufferedImage)((Image) icon.getImage());
    
    image = rotateImage.rotate(image, -90.0);
    
    ImageIcon rotatedIcon = new ImageIcon(image);
    
    labelFlag1.setIcon(rotatedIcon);
    labelFlag2.setIcon(rotatedIcon);
  }

  /* **************************************************************** 
  Metodo: createTrain* 
  Funcao: posicionar na tela e criar os trens* 
  Parametros: int x1 = coodernada x de localizacao
              int y1 = coodernada y de localizacao
              int x2 = largura
              int x1 = altura
              Image tremImage = imagem do trem* 
  Retorno: void*
  *************************************************************** */
  public void createTrain(int x1, int y1, int x2, int y2, Image tremImage){
    ImageIcon icon = new ImageIcon(tremImage);

    labelTrem = new JLabel(icon);
    labelTrem.setBounds(x1, y1, x2, y2);

    tremArray.add(labelTrem);

    this.add(labelTrem);
    this.repaint();
    this.revalidate();
  }

  /* **************************************************************** 
  Metodo: removeTrain* 
  Funcao: remover o ultimo trem adicionado da tela* 
  Parametros: int index = index do ultimo trem adicionado* 
  Retorno: void*
  *************************************************************** */
  public void removeTrain(int index){
    this.remove(tremArray.get(index));
    this.repaint();
    this.revalidate();
    tremArray.remove(index);
  }

  /* **************************************************************** 
  Metodo: initThread* 
  Funcao: inicializar os threads* 
  Parametros: int index = index do array de trens para indicar qual
              objeto esta associado ao thread
              int speed = velocidade de deslocamento do trem
              int route = numero da rota do trem* 
  Retorno: void*
  *************************************************************** */
  public void initThread(int index, int speed, int route){
    Trem threadTrem = new Trem(tremArray.get(index), speed, route);
    sliderArray.get(index).addChangeListener(e -> threadTrem.setSpeed(sliderArray.get(index).getValue()));
  }

  /* **************************************************************** 
  Metodo: speedBar* 
  Funcao: adicionar as barras de velocidade a tela* 
  Parametros: int x1 = coodernada x de localizacao
              int y1 = coodernada y de localizacao
              int width = largura
              int height = altura* 
  Retorno: void*
  *************************************************************** */
  public void speedBar(int x, int y, int width, int height){

    JLabel labelSpeedBar = new JLabel("Barra de velocidade", SwingConstants.CENTER);
    labelSpeedBar.setBounds(1000, 100, 300, 50);
    labelSpeedBar.setFont(new Font("speed", Font.BOLD+Font.ITALIC, 30));
    labelSpeedBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    labelSpeedBar.setForeground(Color.black);
    labelSpeedBar.setBackground(Color.white);
    labelSpeedBar.setOpaque(true);
    add(labelSpeedBar);

    for(int i=0; i<6; i++){
      JSlider slider = new JSlider(0, 100, 25);

      if(i == 0)
        slider.setBounds(x, y, width, height);
      else
        y += 40;
        slider.setBounds(x, y, width, height);
      
      slider.setMaximum(3);
      slider.setMinimum(0);
      slider.setValue(1);
      
      sliderArray.add(slider);

      add(slider);
      repaint();
      revalidate();
    }

    sliderArray.get(0).setBackground(Color.black);
    sliderArray.get(1).setBackground(Color.red);
    sliderArray.get(2).setBackground(Color.blue);
    sliderArray.get(3).setBackground(Color.yellow);
    sliderArray.get(4).setBackground(Color.pink);
    sliderArray.get(5).setBackground(Color.gray);
  }
}
