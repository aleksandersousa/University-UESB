/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 26/08/2019* 
Ultima alteracao: 28/08/2019* 
Nome: Arvore genealogica* 
Funcao: Criar uma arvore genealogica utilizando threads.
*************************************************************** */

public class Line{
  
  int x1, y1, x2, y2;

  /* **************************************************************** 
  Metodo: Line* 
  Funcao: Construtor da classe Line* 
  Parametros: int x1 = parametro x da posicao da linha na tela
              int y1 = parametro y da posicao da linha na tela
              int x2 = parametro x do tamanho da linha
              int y2 = parametro y do tamanho da linha* 
  Retorno: void*
  *************************************************************** */
  public Line(int x1, int y1, int x2, int y2){
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
}