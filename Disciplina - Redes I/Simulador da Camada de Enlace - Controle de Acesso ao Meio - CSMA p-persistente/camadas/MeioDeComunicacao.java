/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package camadas;

import java.util.ArrayList;

import camadas.camada_enlace.CamadaEnlaceDadosReceptora;
import camadas.camada_enlace.Quadro;
import camadas.camada_enlace.controle_de_fluxo.CamadaEnlaceDadosTransmissoraControleFluxo;
import util.EventoColisao;

public class MeioDeComunicacao {
  public static volatile ArrayList<Quadro> meio = new ArrayList<>();
  public static EventoColisao evento = EventoColisao.NAO_COLIDIU;

  /*
   * *****************************************************************************
   * Metodo: meioDeComunicacao* Funcao: Enviar o bits recebidos da camada fisica
   * transmissora para a camada fisica receptora* Parametros: Quadro quadro
   * fluxoBrutoDeBits: vetor com os os bits* Retorno: void*
   */
  public static void meioDeComunicacao(Quadro quadro, int destino,
      CamadaEnlaceDadosTransmissoraControleFluxo ctrlFluxo) {
  
    //ocupa o meio de comunicacao
    meio.add(quadro);

    int[] fluxoBrutoDeBits = quadro.bits;

    //houve colisao
    if (meio.size() > 1) {
      evento = EventoColisao.COLIDIU;

      //gera colisao
      fluxoBrutoDeBits[0] = meio.get(0).bits[0]; 
    } else {
      evento = EventoColisao.NAO_COLIDIU;
    }

    int[] fluxoBrutoDeBitsPontoA = fluxoBrutoDeBits;
    int[] fluxoBrutoDeBitsPontoB = new int[fluxoBrutoDeBitsPontoA.length];

    int valor = 0;
    int displayMask = 1 << 31;

    // passando bit a bit de um vetor para outro
    for (int i = 0; i < fluxoBrutoDeBitsPontoA.length; i++) {
      int numero = fluxoBrutoDeBitsPontoA[i];

      for (int j = 1; j <= 32; j++) {
        valor <<= 1;
        valor |= (numero & displayMask) == 0 ? 0 : 1;
        numero <<= 1;
      }

      fluxoBrutoDeBitsPontoB[i] = valor;
      valor = 0;
    }

    quadro.bits = fluxoBrutoDeBitsPontoB;

    CamadaEnlaceDadosReceptora.camadaEnlaceDadosReceptora(quadro, destino, ctrlFluxo);

    //limpa o meio de comunicacao
    meio.clear();
  }
}
