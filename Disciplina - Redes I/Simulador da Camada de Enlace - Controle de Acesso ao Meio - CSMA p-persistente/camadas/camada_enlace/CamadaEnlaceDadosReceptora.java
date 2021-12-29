/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package camadas.camada_enlace;

import camadas.camada_enlace.controle_de_erro.CamadaEnlaceDadosReceptoraControleDeErro;
import camadas.camada_enlace.controle_de_fluxo.CamadaEnlaceDadosReceptoraControleFluxo;
import camadas.camada_enlace.controle_de_fluxo.CamadaEnlaceDadosTransmissoraControleFluxo;
import util.Constantes;
import util.Util;
import util.Conversao;

public class CamadaEnlaceDadosReceptora {
  /* **************************************************************
  Metodo: camadaEnlaceDadosReceptora*
  Funcao: construtor da classe camadaEnlaceDadosReceptora.*
  Parametros: Quadro quadro, int destino, CamadaEnlaceDadosTransmissoraControleFluxo ctrlFluxo*
  Retorno: void*
  *************************************************************** */
  public static void camadaEnlaceDadosReceptora(Quadro quadro, int destino, CamadaEnlaceDadosTransmissoraControleFluxo ctrlFluxo) {
    String strBits = Conversao.bitsParaString(quadro.bits);
    Util.imprimirNaTelaRecepcao(strBits, Constantes.BIT_RECEBIDO, destino);

    try {
      quadro = CamadaEnlaceDadosReceptoraControleDeErro.camadaEnlaceDadosReceptoraControleDeErro(quadro, destino);
    } catch(Exception e) {
      System.out.println("array bugado .-.");
    }

    ctrlFluxo.mutexQuadro.release();
    
    CamadaEnlaceDadosReceptoraControleFluxo.camadaEnlaceDadosReceptoraControleFluxo(quadro, destino, ctrlFluxo);
  }
}
