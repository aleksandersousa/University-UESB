/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 09/11/2020*
Ultima alteracao: 13/11/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */
package camadas.camada_enlace;

import camadas.MeioDeComunicacao;
import camadas.camada_enlace.controle_de_erro.CamadaEnlaceDadosTransmissoraControleDeErro;
import util.Constantes;
import util.Util;

public class CamadaEnlaceDadosTransmissora {
  public static void camadaEnlaceDadosTransmissora(int[] quadro) {
    Util.imprimirNaTela(util.Conversao.bitsParaString(quadro), Constantes.BIT_BRUTO);

    quadro = CamadaEnlaceDadosTransmissoraControleDeErro.camadaEnlaceDadosTransmissoraControleDeErro(quadro);
    
    MeioDeComunicacao.meioDeComunicacao(quadro);
  }
}
