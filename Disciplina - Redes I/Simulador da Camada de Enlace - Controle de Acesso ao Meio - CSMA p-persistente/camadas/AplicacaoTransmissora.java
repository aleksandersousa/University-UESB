/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package camadas;

import camadas.camada_aplicacao.CamadaDeAplicacaoTransmissora;
import view.Erro;

public class AplicacaoTransmissora {

  /* **************************************************************
  Metodo: aplicacaoTransmissora*
  Funcao: chama a camada de aplicacao transmissora.*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  public static void aplicacaoTransmissora(String txtMensagem, int origem, int destino) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        if (txtMensagem.equals("")) {
          Erro.tratarErroMensagemVazia();
        } else {
          CamadaDeAplicacaoTransmissora.camadaDeAplicacaoTransmissora(txtMensagem, origem, destino);
        }
      }
    }).start();
  }
}
