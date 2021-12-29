/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/09/2019* 
Ultima alteracao: 15/09/2019* 
Nome: Simulador de corrida de trens* 
Funcao: Simular processo de exclusao mutua utilizando trens.*
*************************************************************** */

package view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.imageio.ImageIO;

public class TelaPrincipal extends JFrame{

  private int count = 2;
  private Image tremImage, invertedTremImage, railVerticalImage, railHorizontalImage, 
    backgroundImage, tunelImage, invertedTunelImage, imageRoute1, imageRoute2, imageRoute3, imageRoute4;
  private BackgroundPanel panel;

  /* **************************************************************** 
  Metodo: TelaPrincipal* 
  Funcao: Construtor da classe TelaPrincipal. Le as imagens, instan-
          cia a classe BlackgroundPanel e incializa a tela* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public TelaPrincipal(){
    try{
      this.tremImage = ImageIO.read(new File("Imagens/trens/Trem.png"));
      this.invertedTremImage = ImageIO.read(new File("Imagens/trens/TremVermelhoInvertido.png"));
      this.railVerticalImage = ImageIO.read(new File("Imagens/TrilhoVertical.png"));
      this.railHorizontalImage = ImageIO.read(new File("Imagens/TrilhoHorizontal.png"));
      this.backgroundImage = ImageIO.read(new File("Imagens/Grama.jpg"));
      this.tunelImage = ImageIO.read(new File("Imagens/Tunel.png"));
      this.invertedTunelImage = ImageIO.read(new File("Imagens/TunelInvertido.png"));
      this.imageRoute1 = ImageIO.read(new File("Imagens/Rota1.png"));
      this.imageRoute2 = ImageIO.read(new File("Imagens/Rota2.png"));
      this.imageRoute3 = ImageIO.read(new File("Imagens/Rota3.png"));
      this.imageRoute4 = ImageIO.read(new File("Imagens/Rota4.png"));
    }catch(Exception e){
      e.printStackTrace();
    }

    panel = new BackgroundPanel(backgroundImage, railVerticalImage, railHorizontalImage);

    initComponents();
  }

  /* **************************************************************** 
  Metodo: makeScenario* 
  Funcao: constroi o cenario todo o cenario da aplicacao* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void makeScenario(){

    this.add(panel);
    repaint();
    revalidate();

    //Criando tuneis
    panel.setTunelCoordinates(435, 88, 80, 60, invertedTunelImage);
    panel.setTunelCoordinates(435, 292, 80, 60, tunelImage);

    //Criando trem
    panel.createTrain(30, 20, 60, 150, tremImage);
    panel.createTrain(860, 20, 60, 150, invertedTremImage);

    //Criando speed bar
    panel.speedBar(1000, 180, 300, 20);

    try{
      panel.initThread(0, 1, 1);
      Thread.sleep(1);
      panel.initThread(1, 1, 2);
    }catch(Exception e){
      e.printStackTrace();
    }

    addTrains();
  }

  /* **************************************************************** 
  Metodo: addTrains* 
  Funcao: Adiciona os trens a tela e cria o menu de selecao de rota* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void addTrains(){
    JFrame menuRoute = new JFrame();

    JDialog menuRouteDialog = new JDialog(menuRoute){
      {
        setLocation(250, 220);
        setSize(850,500);
        setLayout(null);
        setResizable(false);
      }
    };

    ImageIcon route1Icon = new ImageIcon(imageRoute1);
    ImageIcon route2Icon = new ImageIcon(imageRoute2);
    ImageIcon route3Icon = new ImageIcon(imageRoute3);
    ImageIcon route4Icon = new ImageIcon(imageRoute4);

    JLabel route1 = new JLabel(route1Icon, JLabel.CENTER);
    JLabel route2 = new JLabel(route2Icon, JLabel.CENTER);
    JLabel route3 = new JLabel(route3Icon, JLabel.CENTER);
    JLabel route4 = new JLabel(route4Icon, JLabel.CENTER);

    route1.setBounds(10, 0, 400, 200);
    route2.setBounds(440, 0, 400, 200);
    route3.setBounds(10, 250, 400, 200);
    route4.setBounds(440, 250, 400, 200);

    route1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    route2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    route3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
    route4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

    MouseListener addTrain1 = new MouseAdapter(){
      /* **************************************************************** 
      Metodo: mouseClicked* 
      Funcao: sobreescrever o metodo mouseClicked. Cria um objeto trem
              e inicia o thread relacionado na rota 1* 
      Parametros: MouseEvent e = Objeto necessario para sobreescrever o
                  metodo* 
      Retorno: void*
      *************************************************************** */
      public void mouseClicked(MouseEvent e){
        if(count <= 5){
          route1.requestFocus();

          if(count == 2){
            try{
              tremImage = ImageIO.read(new File("Imagens/trens/TremAzul.png"));
              panel.createTrain(30, 20, 60, 150, tremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 3){
            try{
              tremImage = ImageIO.read(new File("Imagens/trens/TremAmarelo.png"));
              panel.createTrain(30, 20, 60, 150, tremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 4){
            try{
              tremImage = ImageIO.read(new File("Imagens/trens/TremRosa.png"));
              panel.createTrain(30, 20, 60, 150, tremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 5){
            try{
              tremImage = ImageIO.read(new File("Imagens/trens/TremCinza.png"));
              panel.createTrain(30, 20, 60, 150, tremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }

          panel.initThread(count, 1, 1);
          menuRouteDialog.dispose();

        }else{
          JOptionPane.showMessageDialog(null, "Alerta!", "Numero maximo de trens atingidos!", JOptionPane.WARNING_MESSAGE);
        }
        
        if(count <= 5)
          count++;
      }
    };

    MouseListener addTrain2 = new MouseAdapter(){
      /* **************************************************************** 
      Metodo: mouseClicked* 
      Funcao: sobreescrever o metodo mouseClicked. Cria um objeto trem
              e inicia o thread relacionado na rota 2* 
      Parametros: MouseEvent e = Objeto necessario para sobreescrever o
                  metodo* 
      Retorno: void*
      *************************************************************** */
      public void mouseClicked(MouseEvent e){
        if(count <= 5){
          route2.requestFocus();

          if(count == 2){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremAzulInvertido.png"));
              panel.createTrain(860, 20, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 3){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremAmareloInvertido.png"));
              panel.createTrain(860, 20, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 4){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremRosaInvertido.png"));
              panel.createTrain(860, 20, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 5){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremCinzaInvertido.png"));
              panel.createTrain(860, 20, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }

          panel.initThread(count, 1, 2);
          menuRouteDialog.dispose();

        }else{
          JOptionPane.showMessageDialog(null, "Alerta!", "Numero maximo de trens atingidos!", JOptionPane.WARNING_MESSAGE);
        }

        if(count <= 5)
          count++;
      }
    };

    MouseListener addTrain3 = new MouseAdapter(){
      /* **************************************************************** 
      Metodo: mouseClicked* 
      Funcao: sobreescrever o metodo mouseClicked. Cria um objeto trem
              e inicia o thread relacionado na rota 3* 
      Parametros: MouseEvent e = Objeto necessario para sobreescrever o
                  metodo* 
      Retorno: void*
      *************************************************************** */
      public void mouseClicked(MouseEvent e){
        if(count <= 5){
          route2.requestFocus();

          if(count == 2){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremAzul2.png"));
              panel.createTrain(30, 250, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 3){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremAmarelo2.png"));
              panel.createTrain(30, 250, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 4){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremRosa2.png"));
              panel.createTrain(30, 250, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 5){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremCinza2.png"));
              panel.createTrain(30, 250, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }

          panel.initThread(count, 1, 3);
          menuRouteDialog.dispose();

        }else{
          JOptionPane.showMessageDialog(null, "Alerta!", "Numero maximo de trens atingidos!", JOptionPane.WARNING_MESSAGE);
        }

        if(count <= 5)
          count++;
      }
    };

    MouseListener addTrain4 = new MouseAdapter(){
       /* **************************************************************** 
      Metodo: mouseClicked* 
      Funcao: sobreescrever o metodo mouseClicked. Cria um objeto trem
              e inicia o thread relacionado na rota 4* 
      Parametros: MouseEvent e = Objeto necessario para sobreescrever o
                  metodo* 
      Retorno: void*
      *************************************************************** */
      public void mouseClicked(MouseEvent e){
        if(count <= 5){
          route2.requestFocus();

          if(count == 2){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremAzul2Invertido.png"));
              panel.createTrain(860, 250, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 3){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremAmarelo2Invertido.png"));
              panel.createTrain(860, 250, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 4){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremRosa2Invertido.png"));
              panel.createTrain(860, 250, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }else if(count == 5){
            try{
              invertedTremImage = ImageIO.read(new File("Imagens/trens/TremCinza2Invertido.png"));
              panel.createTrain(860, 250, 60, 150, invertedTremImage);
            }catch(Exception ex){
              ex.printStackTrace();
            }
          }

          panel.initThread(count, 1, 4);
          menuRouteDialog.dispose();

        }else{
          JOptionPane.showMessageDialog(null, "Alerta!", "Numero maximo de trens atingidos!", JOptionPane.WARNING_MESSAGE);
        }

        if(count <= 5)
          count++;
      }
    };

    route1.addMouseListener(addTrain1);
    route2.addMouseListener(addTrain2);
    route3.addMouseListener(addTrain3);
    route4.addMouseListener(addTrain4);
    
    menuRouteDialog.add(route1);
    menuRouteDialog.add(route2);
    menuRouteDialog.add(route3);
    menuRouteDialog.add(route4);

    ActionListener showMenu = new ActionListener(){
      /* **************************************************************** 
      Metodo: actionPerformed* 
      Funcao: sobreescrever o metodo actionPerformed. torna a tela de sele-
              cao de rotas visel* 
      Parametros: ActionEvent e = objeto necessario para sobrescreever o
                  metodo* 
      Retorno: void*
      *************************************************************** */
      public void actionPerformed(ActionEvent e){
        menuRouteDialog.setVisible(true);
      }
    };

    ActionListener removeTrain = new ActionListener(){
      /* **************************************************************** 
      Metodo: actionPerformed* 
      Funcao: sobreescrever o metodo actionPerformed. Remove o ultimo trem
              adicionado* 
      Parametros: ActionEvent e = objeto necessario para sobrescreever o
                  metodo* 
      Retorno: void*
      *************************************************************** */
      public void actionPerformed(ActionEvent e){
        if(count > 2){
          panel.removeTrain(count-1);
        }else{
          JOptionPane.showMessageDialog(null, "Alerta!", "Numero minimo de trens atingido!", JOptionPane.WARNING_MESSAGE);
        }

        count--;
      }
    };

    JButton addButton = new JButton("Adicionar trem");
    JButton remove = new JButton("Remover trem");

    addButton.setBounds(200, 500, 200, 100);
    addButton.setFont(new Font("botao", Font.BOLD, 20));
    addButton.addActionListener(showMenu);

    remove.setBounds(600, 500, 200, 100);
    remove.setFont(new Font("botao", Font.BOLD, 20));
    remove.addActionListener(removeTrain);
    
    panel.add(addButton);
    panel.add(remove);
  }

  /* **************************************************************** 
  Metodo: initComponents* 
  Funcao: incializa a tela* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void initComponents(){
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(200,150);
    setSize(950, 550);
    setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    setVisible(true);

    makeScenario();
  }
}
