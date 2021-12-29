/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package util;

import camadas.camada_enlace.Quadro;
import view.TelaPrincipal;

public class Util {
  public static StringBuilder bufferMensagemDecodificada1 = new StringBuilder();
  public static StringBuilder bufferMensagemDecodificada2 = new StringBuilder();
  public static StringBuilder bufferMensagemDecodificada3 = new StringBuilder();
  public static StringBuilder bufferMensagemDecodificada4 = new StringBuilder();

  public static StringBuilder bufferAsciiDecodificado1 = new StringBuilder();
  public static StringBuilder bufferAsciiDecodificado2 = new StringBuilder();
  public static StringBuilder bufferAsciiDecodificado3 = new StringBuilder();
  public static StringBuilder bufferAsciiDecodificado4 = new StringBuilder();
  /*
   * ************************************************************** 
   * Metodo: imprimirNaTela
   * Funcao: Imprimir as informacoes na tela* 
   * Parametros: String strMensagem: texto a ser impresso 
   *             int tipoDeImpressao: em qual caixa de texto sera impresso* 
   * Retorno: void*
  */
  public static void imprimirNaTelaTransmissao(String strMensagem, int tipoDeImpressao, int origem) {
    try {
      switch (tipoDeImpressao) {
        case Constantes.ASCII:
          switch (origem) {
            case 1:
              TelaPrincipal.computador1.painelEsquerdo.getCaixaDeTexto(0).setText(strMensagem);
              TelaPrincipal.computador1.painelEsquerdo.getCaixaDeTexto(0).update(TelaPrincipal.computador1.painelEsquerdo.getCaixaDeTexto(0).getGraphics());
              break;
            case 2:
              TelaPrincipal.computador2.painelEsquerdo.getCaixaDeTexto(0).setText(strMensagem);
              TelaPrincipal.computador2.painelEsquerdo.getCaixaDeTexto(0).update(TelaPrincipal.computador2.painelEsquerdo.getCaixaDeTexto(0).getGraphics());
              break;
            case 3:
              TelaPrincipal.computador3.painelEsquerdo.getCaixaDeTexto(0).setText(strMensagem);
              TelaPrincipal.computador3.painelEsquerdo.getCaixaDeTexto(0).update(TelaPrincipal.computador3.painelEsquerdo.getCaixaDeTexto(0).getGraphics());
              break;
            case 4:
              TelaPrincipal.computador4.painelEsquerdo.getCaixaDeTexto(0).setText(strMensagem);
              TelaPrincipal.computador4.painelEsquerdo.getCaixaDeTexto(0).update(TelaPrincipal.computador4.painelEsquerdo.getCaixaDeTexto(0).getGraphics());
              break;
          }
          break;
        case Constantes.BIT_BRUTO:
          switch (origem) {
            case 1:
              TelaPrincipal.computador1.painelEsquerdo.getCaixaDeTexto(2).setText(strMensagem);
              TelaPrincipal.computador1.painelEsquerdo.getCaixaDeTexto(2).update(TelaPrincipal.computador1.painelEsquerdo.getCaixaDeTexto(2).getGraphics());
              break;
            case 2:
              TelaPrincipal.computador2.painelEsquerdo.getCaixaDeTexto(2).setText(strMensagem);
              TelaPrincipal.computador2.painelEsquerdo.getCaixaDeTexto(2).update(TelaPrincipal.computador2.painelEsquerdo.getCaixaDeTexto(2).getGraphics());
              break;
            case 3:
              TelaPrincipal.computador3.painelEsquerdo.getCaixaDeTexto(2).setText(strMensagem);
              TelaPrincipal.computador3.painelEsquerdo.getCaixaDeTexto(2).update(TelaPrincipal.computador3.painelEsquerdo.getCaixaDeTexto(2).getGraphics());
              break;
            case 4:
              TelaPrincipal.computador4.painelEsquerdo.getCaixaDeTexto(2).setText(strMensagem);
              TelaPrincipal.computador4.painelEsquerdo.getCaixaDeTexto(2).update(TelaPrincipal.computador4.painelEsquerdo.getCaixaDeTexto(2).getGraphics());
              break;
          }
          break;        
        case Constantes.CRC_CODIFICADO: 
          switch (origem) {
            case 1:
              TelaPrincipal.computador1.painelEsquerdo.getCaixaDeTexto(3).setText(strMensagem);
              TelaPrincipal.computador1.painelEsquerdo.getCaixaDeTexto(3).update(TelaPrincipal.computador1.painelEsquerdo.getCaixaDeTexto(3).getGraphics());
              break;
            case 2:
              TelaPrincipal.computador2.painelEsquerdo.getCaixaDeTexto(3).setText(strMensagem);
              TelaPrincipal.computador2.painelEsquerdo.getCaixaDeTexto(3).update(TelaPrincipal.computador2.painelEsquerdo.getCaixaDeTexto(3).getGraphics());
              break;
            case 3:
              TelaPrincipal.computador3.painelEsquerdo.getCaixaDeTexto(3).setText(strMensagem);
              TelaPrincipal.computador3.painelEsquerdo.getCaixaDeTexto(3).update(TelaPrincipal.computador3.painelEsquerdo.getCaixaDeTexto(3).getGraphics());
              break;
            case 4:
              TelaPrincipal.computador4.painelEsquerdo.getCaixaDeTexto(3).setText(strMensagem);
              TelaPrincipal.computador4.painelEsquerdo.getCaixaDeTexto(3).update(TelaPrincipal.computador4.painelEsquerdo.getCaixaDeTexto(3).getGraphics());
              break;
          }
          break;
      }

      Thread.sleep(600);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void imprimirNaTelaRecepcao(String strMensagem, int tipoDeImpressao, int destino) {
    try {
      switch (tipoDeImpressao) {
        case Constantes.ASCII_DECODIFICADO:
          switch (destino) {
            case 1:
              bufferAsciiDecodificado1.append(strMensagem + ", ");
              TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(1).setText(bufferAsciiDecodificado1.toString());
              TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(1).update(TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(1).getGraphics());
              break;
            case 2:
              bufferAsciiDecodificado2.append(strMensagem + ", ");
              TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(1).setText(bufferAsciiDecodificado2.toString());
              TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(1).update(TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(1).getGraphics());
              break;
            case 3:
              bufferAsciiDecodificado3.append(strMensagem + ", ");
              TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(1).setText(bufferAsciiDecodificado3.toString());
              TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(1).update(TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(1).getGraphics());
              break;
            case 4:
              bufferAsciiDecodificado4.append(strMensagem + ", ");
              TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(1).setText(bufferAsciiDecodificado4.toString());
              TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(1).update(TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(1).getGraphics());
              break;
          }
          break;
          case Constantes.MENSAGEM_DECODIFICADA:
          switch (destino) {
            case 1:
              bufferMensagemDecodificada1.append(strMensagem);
              TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(4).setText(bufferMensagemDecodificada1.toString());
              TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(4).update(TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(4).getGraphics());
              break;
            case 2:
              bufferMensagemDecodificada2.append(strMensagem);
              TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(4).setText(bufferMensagemDecodificada2.toString());
              TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(4).update(TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(4).getGraphics());
              break;
            case 3:
              bufferMensagemDecodificada3.append(strMensagem);
              TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(4).setText(bufferMensagemDecodificada3.toString());
              TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(4).update(TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(4).getGraphics());
              break;
            case 4:
              bufferMensagemDecodificada4.append(strMensagem);
              TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(4).setText(bufferMensagemDecodificada4.toString());
              TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(4).update(TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(4).getGraphics());
              break;
          }
          break;
        case Constantes.CRC_DECODIFICADO:
          switch (destino) {
            case 1:
              TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(5).setText(strMensagem);
              TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(5).update(TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(5).getGraphics());
              break;
            case 2:
              TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(5).setText(strMensagem);
              TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(5).update(TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(5).getGraphics());
              break;
            case 3:
              TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(5).setText(strMensagem);
              TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(5).update(TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(5).getGraphics());
              break;
            case 4:
              TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(5).setText(strMensagem);
              TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(5).update(TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(5).getGraphics());
              break;
          }
          break;        
        case Constantes.BIT_RECEBIDO:
          switch (destino) {
            case 1:
              TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(6).setText(strMensagem);
              TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(6).update(TelaPrincipal.computador1.painelDireito.getCaixaDeTexto(6).getGraphics());
              break;
            case 2:
              TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(6).setText(strMensagem);
              TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(6).update(TelaPrincipal.computador2.painelDireito.getCaixaDeTexto(6).getGraphics());
              break;
            case 3:
              TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(6).setText(strMensagem);
              TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(6).update(TelaPrincipal.computador3.painelDireito.getCaixaDeTexto(6).getGraphics());
              break;
            case 4:
              TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(6).setText(strMensagem);
              TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(6).update(TelaPrincipal.computador4.painelDireito.getCaixaDeTexto(6).getGraphics());
              break;
          }
          break;
      }

      Thread.sleep(600);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
   * ************************************************************** 
   * Metodo: bitsErroParaString
   * Funcao: concatena os bits do array de quadros em uma unica string
   * Parametros: nulo
   * Retorno: void
   */
  public static String bitsErroParaString(Quadro[] quadros) {
    StringBuilder strBuilder = new StringBuilder();
    
    for (Quadro quadro : quadros) {
      strBuilder.append(Conversao.bitsParaString(quadro.bits) + "\n");
    }

    return strBuilder.toString();
  }
}
