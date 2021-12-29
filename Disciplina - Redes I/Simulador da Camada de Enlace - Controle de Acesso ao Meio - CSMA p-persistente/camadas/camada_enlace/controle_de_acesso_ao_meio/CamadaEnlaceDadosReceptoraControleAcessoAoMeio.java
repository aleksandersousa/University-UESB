/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package camadas.camada_enlace.controle_de_acesso_ao_meio;

import camadas.camada_aplicacao.CamadaDeAplicacaoReceptora;
import camadas.camada_enlace.Quadro;
import camadas.camada_enlace.controle_de_fluxo.CamadaEnlaceDadosTransmissoraControleFluxo;

public class CamadaEnlaceDadosReceptoraControleAcessoAoMeio {
  /* *****************************************************************************
  Metodo: camadaEnlaceDadosReceptoraControleAcessoAoMeio*
  Funcao: camadaEnlaceDadosReceptoraControleAcessoAoMeio
  Parametros: Quadro quadro, int destino, CamadaEnlaceDadosTransmissoraControleFluxo ctrlFluxo*
  Retorno: void*
  ***************************************************************************** */
  public static void camadaEnlaceDadosReceptoraControleAcessoAoMeio (Quadro quadro, int destino, CamadaEnlaceDadosTransmissoraControleFluxo ctrlFluxo) {
    ctrlFluxo.mutexQuadro.release();
    CamadaDeAplicacaoReceptora.camadaDeAplicacaoReceptora(quadro.bits, destino);
  }
}
