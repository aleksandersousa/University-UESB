/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
package view;

import controllers.*;
// import models.*;
import images.Images;
import utils.RotateImage;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Background extends JPanel{
  private RotateImage rotate;
  private Images images;
  private Image backgroundImage, roadImage, carsImage;

  private ArrayList<JSlider> sliders;
  private ArrayList<JLabel> circuitLabels;
  private ArrayList<ImageIcon> circuitIcons;

  private int xImage, yImage;

  /* **************************************************************** 
  Metodo: Background* 
  Funcao: Construtor da classe Background. Inicializa os arrays dos
          labels de circuito, dos icons e dos slider. Instacia os objetos
          rotate e images e recebe as imagens dos objetos dos carros e de
          background* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public Background(){
    this.setLayout(null);

    this.sliders = new ArrayList<JSlider>();
    this.circuitLabels = new ArrayList<JLabel>();
    this.circuitIcons = new ArrayList<ImageIcon>();
    this.rotate = new RotateImage();
    this.images = new Images();

    this.backgroundImage = images.getBackgroundImage();
    this.roadImage = images.getRoadImage();
    this.carsImage = images.getCarsImage();

    this.createCircuitMenu();
  }

  /* **************************************************************** 
  Metodo: paintComponent* 
  Funcao: sobreescrever o metodo paintComponent do java. Pinta o
          background* 
  Parametros: Graphics g = objeto Graphics necessario para sobreescrever
              o metodo* 
  Retorno: void*
  *************************************************************** */
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
    
    xImage = 220;
    yImage = 45;

    g.drawImage(roadImage, xImage, 50, 50, 600, null);
    g.drawImage(rotate.rotate((BufferedImage)roadImage, 90), xImage+30, yImage, 840, 50, null);
    g.drawImage(roadImage, xImage+840, 50, 50, 600, null);
    g.drawImage(rotate.rotate((BufferedImage)roadImage, 90), xImage+30, yImage+545, 815, 50, null);

    xImage = 350;
    yImage = 150;

    for(int i=0; i<4; i++){
      g.drawImage(rotate.rotate((BufferedImage)roadImage, 90), 250, yImage, 815, 50, null);
      yImage += 110;
    }

    for(int i=0; i<4; i++){
      g.drawImage(roadImage, xImage, 86, 50, 524, null);
      xImage += 190;
    }
  }

  /* **************************************************************** 
  Metodo: addCar* 
  Funcao: adiciona o objeto carro a este JPanel* 
  Parametros: JLabel car = objeto a ser adicionado* 
  Retorno: void*
  *************************************************************** */
  public void addCar(JLabel car){
    this.add(car);
    this.revalidate();
    this.repaint();
  }

  /* **************************************************************** 
  Metodo: initThreads* 
  Funcao: instacia os objetos threads, cria a barra de velocidade 
          e inicializa os threads* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void initThreads(){
    ThreadSonic sonic = new ThreadSonic(carsImage, this);
    ThreadShadow shadow = new ThreadShadow(carsImage, this);
    ThreadAmy amy = new ThreadAmy(carsImage, this);
    ThreadKnuckles knuckles = new ThreadKnuckles(carsImage, this);
    ThreadTails tails = new ThreadTails(carsImage, this);

    this.createSpeedBar(sonic, shadow, amy, knuckles, tails);

    sonic.start();
    shadow.start();
    amy.start();
    knuckles.start();
    tails.start();
  }

  /* **************************************************************** 
  Metodo: createSpeedBar* 
  Funcao: cria a barra de velocidade* 
  Parametros: ThreadSonic sonic = objeto que recebe a alteracao de velocidade
              ThreadShadow shadow = objeto que recebe a alteracao de velocidade
              ThreadAmy amy = objeto que recebe a alteracao de velocidade
              ThreadKnuckles knuckles = objeto que recebe a alteracao de velocidade* 
              ThreadTails tails = objeto que recebe a alteracao de velocidade
  Retorno: void*
  *************************************************************** */
  private void createSpeedBar(
    ThreadSonic sonic, ThreadShadow shadow,
    ThreadAmy amy, ThreadKnuckles knuckles, ThreadTails tails
  )
  {
    int x = 1130;
    int y = 120;
    int width = 230;
    int height = 25;

    JLabel labelSpeedBar = new JLabel("Barras de velocidade", SwingConstants.CENTER);
    labelSpeedBar.setBounds(x, 45, 230, 50);
    labelSpeedBar.setFont(new Font("speed", Font.BOLD+Font.ITALIC, 20));
    labelSpeedBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    labelSpeedBar.setForeground(Color.black);
    labelSpeedBar.setBackground(Color.white);
    labelSpeedBar.setOpaque(true);
    this.add(labelSpeedBar);

    for(int i=0; i<5; i++){
      JSlider slider = new JSlider(0, 100, 25);

      if(i==0)
        slider.setBounds(x, y, width, height);
      else{
        y+=50;
        slider.setBounds(x, y, width, height);
      }

      slider.setMinimum(0);
      slider.setMaximum(3);
      slider.setValue(3);

      sliders.add(slider);

      this.add(slider);
    }

    sliders.get(0).setBackground(Color.blue);
    sliders.get(1).setBackground(Color.black);
    sliders.get(2).setBackground(Color.pink);
    sliders.get(3).setBackground(Color.red);
    sliders.get(4).setBackground(Color.yellow);

    this.addListener(sonic, shadow, amy, knuckles, tails);
  }

  /* **************************************************************** 
  Metodo: createCircuitMenu* 
  Funcao: cria os circuitos e os adiciona a tela* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  private void createCircuitMenu(){
    final int SPACE = 110;
    int x = 10;
    int y = 100;
    int width = 180;
    int height = 100;

    JLabel labelCircuit = new JLabel("Circuitos", SwingConstants.CENTER);
    labelCircuit.setBounds(10, 45, 180, 50);
    labelCircuit.setFont(new Font("circuit", Font.BOLD+Font.ITALIC, 20));
    labelCircuit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    labelCircuit.setForeground(Color.black);
    labelCircuit.setBackground(Color.white);
    labelCircuit.setOpaque(true);
    this.add(labelCircuit);

    for(int i=0; i<5; i++){
      circuitIcons.add(new ImageIcon(images.getCircuitImages(i)));
      circuitLabels.add(new JLabel(circuitIcons.get(i)));

      circuitLabels.get(i).setBounds(x, y, width, height);
      y += SPACE;

      this.add(circuitLabels.get(i));
    }
  }

  /* **************************************************************** 
  Metodo: addListener* 
  Funcao: adiciona as acoes aos sliders* 
  Parametros: ThreadSonic sonic = objeto que recebe a acao do slider
              ThreadShadow shadow = objeto que recebe a acao do slider
              ThreadAmy amy = objeto que recebe a acao do slider
              ThreadKnuckles knuckles = objeto que recebe a acao do slider
              ThreadTails tails = objeto que recebe a acao do slider* 
  Retorno: void*
  *************************************************************** */
  private void addListener(
    ThreadSonic sonic, ThreadShadow shadow,
    ThreadAmy amy, ThreadKnuckles knuckles, ThreadTails tails
  ){
    sliders.get(0).addChangeListener(new ChangeListener (){
      public void stateChanged(ChangeEvent e){
        JSlider slider = (JSlider)e.getSource();
        sonic.setSleepTime(slider.getValue());
        sonic.getSonic().setSpeed(slider.getValue());
      }
    });

    sliders.get(1).addChangeListener(new ChangeListener (){
      public void stateChanged(ChangeEvent e){
        JSlider slider = (JSlider)e.getSource();
        shadow.setSleepTime(slider.getValue());
        shadow.getShadow().setSpeed(slider.getValue());
      }
    });

    sliders.get(2).addChangeListener(new ChangeListener (){
      public void stateChanged(ChangeEvent e){
        JSlider slider = (JSlider)e.getSource();
        amy.setSleepTime(slider.getValue());
        amy.getAmy().setSpeed(slider.getValue());
      }
    });

    sliders.get(3).addChangeListener(new ChangeListener (){
      public void stateChanged(ChangeEvent e){
        JSlider slider = (JSlider)e.getSource();
        knuckles.setSleepTime(slider.getValue());
        knuckles.getKnuckles().setSpeed(slider.getValue());
      }
    });

    sliders.get(4).addChangeListener(new ChangeListener (){
      public void stateChanged(ChangeEvent e){
        JSlider slider = (JSlider)e.getSource();
        tails.setSleepTime(slider.getValue());
        tails.getTails().setSpeed(slider.getValue());
      }
    });
  }
}