package utils;
/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;
import java.io.IOException;

public class BufferedImageLoader {

  /* **************************************************************** 
  Metodo: loadImage* 
  Funcao: ler as imagens do disco que serao utilizadas* 
  Parametros: String pathRelativeToThis: path da imagem* 
  Retorno: BufferedImage: objeto da imagem que foi lida*
  *************************************************************** */
  public BufferedImage loadImage(String pathRelativeToThis) throws IOException {
    URL url = this.getClass().getResource(pathRelativeToThis);
    BufferedImage image = ImageIO.read(url);
    return image;
  }

}