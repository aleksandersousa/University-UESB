/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package camadas.camada_enlace.controle_de_fluxo;

import java.util.concurrent.Semaphore;

import camadas.camada_enlace.Quadro;
import camadas.camada_enlace.controle_de_acesso_ao_meio.CamadaEnlaceDadosTransmissoraControleAcessoAoMeio;
import util.Evento;

public class CamadaEnlaceDadosTransmissoraControleFluxo {
  public int proximoQuadroAEnviar; // janela do transmissor
  public int quadroEsperado; // janela do receptor
  public Evento evento;
  public Semaphore mutexQuadro;

  public CamadaEnlaceDadosTransmissoraControleFluxo (Quadro[] quadros, int origem, int destino) {
    mutexQuadro = new Semaphore(1);
    this.camadaEnlaceDadosTransmissoraControleFluxo(quadros, origem, destino);
  }

  /*
   * *****************************************************************************
   * Metodo: camadaEnlaceDadosTransmissoraControleFluxo* 
   * Funcao: Aplicar o algoritmo de transmissao controle de fluxo
   * Parametros: Quadro[] quadros: vetor com os quadros
   * Retorno: void*
  */
  public void camadaEnlaceDadosTransmissoraControleFluxo(Quadro[] quadros, int origem, int destino) {
    this.controleDeFluxoTransmissoraJanelaDeslizante1Bit(quadros, destino);
  }

  /*
   * *****************************************************************************
   * Metodo: controleDeFluxoTransmissoraJanelaDeslizante1Bit* 
   * Funcao: Aplicar o algoritmo de janela deslizante de 1 bit
   * Parametros: Quadro[] quadros: vetor com os quadros
   * Retorno: void*
   */
  public void controleDeFluxoTransmissoraJanelaDeslizante1Bit(Quadro[] quadros, int destino) {
    for (int i = 0; i < quadros.length; i++) {
      try {
        // bloqueia o envio do proximo do quadro ate receber o ack
        this.mutexQuadro.acquire();

        quadros[i].buffer = quadros[i].bits;
        quadros[i].sequencia = this.proximoQuadroAEnviar;
        quadros[i].ack = 1 - this.quadroEsperado;

        // chama o meio de comunicacao
        new CamadaEnlaceDadosTransmissoraControleAcessoAoMeio(quadros[i], destino, this);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
