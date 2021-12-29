/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package camadas.camada_enlace.controle_de_acesso_ao_meio;

import java.util.Random;

import camadas.MeioDeComunicacao;
import camadas.camada_enlace.Quadro;
import camadas.camada_enlace.controle_de_fluxo.CamadaEnlaceDadosTransmissoraControleFluxo;
import util.EventoColisao;
import view.Erro;

public class CamadaEnlaceDadosTransmissoraControleAcessoAoMeio {
  double probabilidadeP = 0.6;
  double probabilidadeQ = 1 - probabilidadeP;
  int tempoDeEspera = 1;

  /* *****************************************************************************
  Metodo: CamadaEnlaceDadosTransmissoraControleAcessoAoMeio*
  Funcao: construtor da classe CamadaEnlaceDadosTransmissoraControleAcessoAoMeio*
  Parametros: Quadro quadro, int destino, 
              CamadaEnlaceDadosTransmissoraControleFluxo ctrlFluxo*
  Retorno: void*
  ***************************************************************************** */
  public CamadaEnlaceDadosTransmissoraControleAcessoAoMeio(Quadro quadro, int destino, 
  CamadaEnlaceDadosTransmissoraControleFluxo ctrlFluxo) {
    this.camadaEnlaceDadosTransmissoraControleAcessoAoMeioPPersistente(quadro, destino, ctrlFluxo);
  }

  /* *****************************************************************************
  Metodo: camadaEnlaceDadosTransmissoraControleAcessoAoMeioPPersistente*
  Funcao: camadaEnlaceDadosTransmissoraControleAcessoAoMeioPPersistente*
  Parametros: Quadro quadro, int destino, 
              CamadaEnlaceDadosTransmissoraControleFluxo ctrlFluxo*
  Retorno: void*
  ***************************************************************************** */
  public void camadaEnlaceDadosTransmissoraControleAcessoAoMeioPPersistente(Quadro quadro, int destino, 
    CamadaEnlaceDadosTransmissoraControleFluxo ctrlFluxo) {

    Random random1 = new Random();
    
    if (MeioDeComunicacao.evento == EventoColisao.COLIDIU) {
      Erro.tratarErroCRC();
      tempoDeEspera = random1.nextInt(5); //espera um tempo aleatorio

      //espera um tempo aleatorio para enviar
      try {
        System.out.println("Colisao. Esperando tempo aleatorio para enviar para: " + destino);
        Thread.sleep(tempoDeEspera * 100); 
      } catch (Exception e) {
        System.out.println("Erro transmissao, acesso ao meio");
      }
      
    } else {
      tempoDeEspera = 2;
    }

    while (true) {
      if (MeioDeComunicacao.meio.size() == 0) { //se o meio estiver livre
        Random random = new Random();

        if (probabilidadeP > random.nextDouble()) {
          if (probabilidadeQ > random.nextDouble()) {
            System.out.println("Enviando para: " + destino);
            //chama a proxima camada
            MeioDeComunicacao.meioDeComunicacao(quadro, destino, ctrlFluxo);
            break;
          }
        }

        //se nao enviou
        try {
          System.out.println("Esperando o proximo slott para enviar para: " + destino);
          Thread.sleep(tempoDeEspera * 50); //espera o proximo slott
        } catch (Exception e) {
          System.out.println("Erro transmissao, acesso ao meio");
        }
      } else {
        try {
          Thread.sleep(tempoDeEspera * 50); //espera o proximo slott
        } catch (Exception e) {
          System.out.println("Erro transmissao, acesso ao meio");
        }
      }
    }
  }
}
