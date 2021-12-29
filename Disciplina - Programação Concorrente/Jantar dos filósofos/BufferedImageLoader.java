/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/10/2019* 
Ultima alteracao: 12/10/2019* 
Nome: jantar dos filosofos* 
Funcao: Simular o problema classico jantar dos filosofos.
*************************************************************** */
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;
import java.io.IOException;

public class BufferedImageLoader{

  /* **************************************************************** 
  Metodo: loadImage* 
  Funcao: ler as imagens do disco que serao utilizadas* 
  Parametros: String pathRelativeToThis: path da imagem* 
  Retorno: BufferedImage: objeto da imagem que foi lida*
  *************************************************************** */
  public BufferedImage loadImage(String pathRelativeToThis) throws IOException{
    URL url = this.getClass().getResource(pathRelativeToThis);
    BufferedImage image = ImageIO.read(url);

    return image;
  }

}