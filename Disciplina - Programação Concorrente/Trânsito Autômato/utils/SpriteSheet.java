/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
package utils;

import java.awt.image.BufferedImage;

public class SpriteSheet{
  private BufferedImage spriteSheet;

  /* **************************************************************** 
  Metodo: SpriteSheet* 
  Funcao: Construtor da classe SpritSheet. Recebe um spritesheet* 
  Parametros: BufferedImage spritesheet: spritesheet*  
  Retorno: void*
  *************************************************************** */
  public SpriteSheet(BufferedImage spriteSheet){
    this.spriteSheet = spriteSheet;
  }

  /* **************************************************************** 
  Metodo: grabSprite* 
  Funcao: recorta os sprites do spritesheet* 
  Parametros: int x: posicao x do sprite
              int y: posicao y do sprite
              int width: largura do sprite
              int height: altura do sprite*  
  Retorno: BuferredImage: retorna o sprite recortado*
  *************************************************************** */
  public BufferedImage grabSprite(int x, int y, int width, int height){
    BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
    
    return sprite;
  }

}