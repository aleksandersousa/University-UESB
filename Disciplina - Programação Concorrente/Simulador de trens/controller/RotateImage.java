/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/09/2019* 
Ultima alteracao: 15/09/2019* 
Nome: Simulador de corrida de trens* 
Funcao: Simular processo de exclusao mutua utilizando trens.*
*************************************************************** */

package controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class RotateImage{

  /* **************************************************************** 
  Metodo: rotate* 
  Funcao: rotacionar uma imagem* 
  Parametros: BufferedImage image = imagem a ser rotacionada
              doubel degrees = determina a quantidade de graus da rotacao* 
  Retorno: BufferedImage rotate = imagem rotacionada*
  *************************************************************** */
  public BufferedImage rotate(BufferedImage image, double degrees){
    // Calculate the new size of the image based on the angle of rotaion
    double radians = Math.toRadians(degrees);
    double sin = Math.abs(Math.sin(radians));
    double cos = Math.abs(Math.cos(radians));
    int newWidth = (int) Math.round(image.getWidth()*cos + image.getHeight()*sin);
    int newHeight = (int) Math.round(image.getWidth()*sin + image.getHeight()*cos);

    // Create a new image
    BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = rotate.createGraphics();

    // Calculate the "anchor" point around which the image will be rotated
    int x = (newWidth - image.getWidth())/2;
    int y = (newHeight - image.getHeight())/2;

    // Transform the origin point around the anchor point
    AffineTransform at = new AffineTransform();
    at.setToRotation(radians, x + (image.getWidth()/2), y + (image.getHeight()/2));
    at.translate(x, y);
    g2d.setTransform(at);

    // Paint the originl image
    g2d.drawImage(image, 0, 0, null);
    g2d.dispose();

    return rotate;
  }
}
