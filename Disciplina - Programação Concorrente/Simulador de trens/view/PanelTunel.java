/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/09/2019* 
Ultima alteracao: 15/09/2019* 
Nome: Simulador de corrida de trens* 
Funcao: Simular processo de exclusao mutua utilizando trens.*
*************************************************************** */

package view;

import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;

public class PanelTunel extends JPanel{

  private Image tunelImage;

  /* **************************************************************** 
  Metodo: PanelTunel* 
  Funcao: Construtor da classe PanelTunel. Recebe as coordenadas de
          posicionamento da tela e a imagem de background* 
  Parametros: int x1 = coodernada x de localizacao
              int y1 = coodernada y de localizacao
              int x2 = largura
              int x1 = altura
              Image tunelImage = imagem d background* 
  Retorno: void*
  *************************************************************** */
  public PanelTunel(int x1, int y1, int x2, int y2, Image tunelImage){
    this.tunelImage = tunelImage;

    this.setBounds(x1, y1, x2, y2);
    this.setLayout(null);
  }
  
  /* **************************************************************** 
  Metodo: paintComponent* 
  Funcao: sobrescreve o metodo paintComponent* 
  Parametros: Graphics g = objeto do tipo Graphics necessario para
              sobrescrever o metodo* 
  Retorno: void*
  *************************************************************** */
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    g.drawImage(tunelImage, 0, 0, this.getWidth(), this.getHeight(), null);
  }
}
