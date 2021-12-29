/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package camadas;

import util.Constantes;
import util.Util;

public class AplicacaoReceptora {
  /* *****************************************************************************
  Metodo: aplicacaoReceptora*
  Funcao: Imprimir a mensagem decodificada na tela*
  Parametros: String mensagem: mensagem a ser impressa*
  Retorno: void*
  ***************************************************************************** */
  public static void aplicacaoReceptora(String mensagem, int destino) {
    Util.imprimirNaTelaRecepcao(mensagem, Constantes.MENSAGEM_DECODIFICADA, destino);
  }
}
