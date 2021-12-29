/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 09/11/2020*
Ultima alteracao: 13/11/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */
package camadas.camada_aplicacao;

import camadas.camada_enlace.CamadaEnlaceDadosTransmissora;

import util.Conversao;
import util.Constantes;
import util.Util;

public class CamadaDeAplicacaoTransmissora {

  /* *****************************************************************************
  Metodo: camadaDeAplicacaoTransmissora*
  Funcao: Tranformar a mensagem em caracteres ASCII e enviar para a camada fisica transmissora*
  Parametros: String mensagem: mensagem a ser enviada*
  Retorno: void*
  ***************************************************************************** */
  public static void camadaDeAplicacaoTransmissora(String mensagem) {
    int[] arrayAscii = Conversao.stringParaAscii(mensagem);

    Util.imprimirNaTela(Conversao.asciiParaString(arrayAscii, Constantes.ASCII), Constantes.ASCII);

    int[] quadro = Conversao.asciiParaBits(arrayAscii);

    CamadaEnlaceDadosTransmissora.camadaEnlaceDadosTransmissora(quadro);
  }
}
