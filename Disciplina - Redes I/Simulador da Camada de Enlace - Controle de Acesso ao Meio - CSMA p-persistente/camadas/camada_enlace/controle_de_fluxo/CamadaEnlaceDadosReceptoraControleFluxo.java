/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package camadas.camada_enlace.controle_de_fluxo;

import camadas.camada_enlace.Quadro;
import camadas.camada_enlace.controle_de_acesso_ao_meio.CamadaEnlaceDadosReceptoraControleAcessoAoMeio;

public class CamadaEnlaceDadosReceptoraControleFluxo {
  /*
   * *****************************************************************************
   * Metodo: camadaEnlaceDadosReceptoraControleFluxo* 
   * Funcao: Aplicar o algoritmo de recepcao de controle de fluxo
   * Parametros: Quadro quadro: quadro manipulado pelo algoritmo
   * Retorno: void*
  */
  public static void camadaEnlaceDadosReceptoraControleFluxo(Quadro quadro, int destino, CamadaEnlaceDadosTransmissoraControleFluxo ctrlFluxo) {
    CamadaEnlaceDadosReceptoraControleAcessoAoMeio.camadaEnlaceDadosReceptoraControleAcessoAoMeio(quadro, destino, ctrlFluxo);
  }
}
