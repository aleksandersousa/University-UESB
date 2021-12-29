/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/10/2019* 
Ultima alteracao: 12/10/2019* 
Nome: jantar dos filosofos* 
Funcao: Simular o problema classico jantar dos filosofos.
*************************************************************** */
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Image;

public class Fork extends JLabel{
  private Image forkImage;

  /* **************************************************************** 
  Metodo: Fork* 
  Funcao: Construtor da classe Fork. recebe a imagem do garfo e posi-
          ciona o label na tela com as posicoes recebidas* 
  Parametros: Image forkImage: imagem do garfo
              int x: posicao x na tela
              int y: posicao y na tela
              int width: largura do garfo
              int height: altura do garfo* 
  Retorno: void*
  *************************************************************** */
  public Fork(Image forkImage, int x, int y, int width, int height){
    this.forkImage = forkImage;
    this.setBounds(x, y, width, height);
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
    g.drawImage(forkImage, 0, 0, this.getWidth(), this.getHeight(), null);
  }
}