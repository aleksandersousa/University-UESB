/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 09/11/2020*
Ultima alteracao: 13/11/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */
package camadas.camada_enlace;

import camadas.camada_aplicacao.CamadaDeAplicacaoReceptora;
import camadas.camada_enlace.controle_de_erro.CamadaEnlaceDadosReceptoraControleDeErro;
import util.Constantes;
import util.Util;
import util.Conversao;

public class CamadaEnlaceDadosReceptora {
  public static void camadaEnlaceDadosReceptora(int[] quadro) {
    Util.imprimirNaTela(Conversao.bitsParaString(quadro), Constantes.BIT_RECEBIDO);

    quadro = CamadaEnlaceDadosReceptoraControleDeErro.camadaEnlaceDadosReceptoraControleDeErro(quadro);
    
    CamadaDeAplicacaoReceptora.camadaDeAplicacaoReceptora(quadro);
  }
}
