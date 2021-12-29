/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package camadas.camada_aplicacao;

import camadas.camada_enlace.CamadaEnlaceDadosTransmissora;
import util.Conversao;
import util.Constantes;
import util.Util;
import view.TelaPrincipal;

public class CamadaDeAplicacaoTransmissora {
  /* *****************************************************************************
  Metodo: camadaDeAplicacaoTransmissora*
  Funcao: Tranformar a mensagem em caracteres ASCII e enviar para a camada fisica 
          transmissora*
  Parametros: String mensagem: mensagem a ser enviada*
  Retorno: void*
  ***************************************************************************** */
  public static void camadaDeAplicacaoTransmissora(String mensagem, int origem, int destino) {
    switch (destino) {
      case 1:
        TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(4).setText("");
        Util.bufferAsciiDecodificado1 = new StringBuilder();
        Util.bufferMensagemDecodificada1 = new StringBuilder();
        break;
      case 2:
        Util.bufferAsciiDecodificado2 = new StringBuilder();
        Util.bufferMensagemDecodificada2 = new StringBuilder();
        TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(4).setText("");
        break;
      case 3:
        Util.bufferAsciiDecodificado3 = new StringBuilder();
        Util.bufferMensagemDecodificada3 = new StringBuilder();
        TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(4).setText("");
        break;
      case 4:
        Util.bufferAsciiDecodificado4 = new StringBuilder();
        Util.bufferMensagemDecodificada4 = new StringBuilder();
        TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(4).setText("");
        break;
    }

    //pega os codigos ascii
    int[] arrayAscii = Conversao.stringParaAscii(mensagem);
    String strAscii = Conversao.asciiParaString(arrayAscii, Constantes.ASCII);

    Util.imprimirNaTelaTransmissao(strAscii, Constantes.ASCII, origem);

    int[] quadro = Conversao.asciiParaBits(arrayAscii);

    new CamadaEnlaceDadosTransmissora(quadro, origem, destino);
  }
}
