/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
package images;

import utils.BufferedImageLoader;
import java.util.ArrayList;
import java.awt.Image;
import java.io.IOException;

public class Images{
  private ArrayList<Image> circuitImages;
  private BufferedImageLoader loader;
  private Image backgroundImage, roadImage, carsImage;

  /* **************************************************************** 
  Metodo: Images* 
  Funcao: Construtor da classe Images. carrega as imagens* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public Images() {
    try{
      this.loader = new BufferedImageLoader();
      this.circuitImages = new ArrayList<Image>();
      this.backgroundImage = loader.loadImage("../images/background.png");
      this.roadImage = loader.loadImage("../images/road.png");
      this.carsImage = loader.loadImage("../images/cars.png");

      for (int i = 1; i <= 5; i++) {
        circuitImages.add(loader.loadImage("../images/circuit"+i+".png"));
      }
    }catch(IOException e) {
      e.printStackTrace();
    }
  }

  /* **************************************************************** 
  Metodo: getBackgroundImage* 
  Funcao: retorna a imagem de background* 
  Parametros: nulo* 
  Retorno: Image*
  *************************************************************** */
  public Image getBackgroundImage(){
    return this.backgroundImage;
  }

  /* **************************************************************** 
  Metodo: getRoadImage* 
  Funcao: retorna a imagem da pista* 
  Parametros: nulo* 
  Retorno: Image*
  *************************************************************** */
  public Image getRoadImage(){
    return this.roadImage;
  }

  /* **************************************************************** 
  Metodo: getCarsImage* 
  Funcao: retorna a imagem com os sprites* 
  Parametros: nulo* 
  Retorno: Image*
  *************************************************************** */
  public Image getCarsImage(){
    return this.carsImage;
  }

  /* **************************************************************** 
  Metodo: getCircuitImages* 
  Funcao: retorna a imagem do circuito do array de imagens* 
  Parametros: int index = index da imagem* 
  Retorno: Image*
  *************************************************************** */
  public Image getCircuitImages(int index){
    return this.circuitImages.get(index);
  }
}