/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package camadas.camada_enlace;

import camadas.camada_enlace.controle_de_erro.CamadaEnlaceDadosTransmissoraControleDeErro;
import camadas.camada_enlace.controle_de_fluxo.CamadaEnlaceDadosTransmissoraControleFluxo;
import util.Constantes;
import util.Conversao;
import util.Util;

public class CamadaEnlaceDadosTransmissora {
  /* **************************************************************
  Metodo: CamadaEnlaceDadosTransmissora*
  Funcao: construtor da classe CamadaEnlaceDadosTransmissora.*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  public CamadaEnlaceDadosTransmissora(int[] quadro, int origem, int destino) {
    this.camadaEnlaceDadosTransmissora(quadro, origem, destino);
  }

  /* **************************************************************
  Metodo: camadaEnlaceDadosTransmissora*
  Funcao: camadaEnlaceDadosTransmissora.*
  Parametros: int[] quadro, int origem, int destino*
  Retorno: void*
  *************************************************************** */
  public void camadaEnlaceDadosTransmissora(int[] quadro, int origem, int destino) {
    String strBits = Conversao.bitsParaString(quadro);
    Util.imprimirNaTelaTransmissao(strBits, Constantes.BIT_BRUTO, origem);

    Quadro[] quadros = camadaEnladeTransmissoraEnquadramento(quadro);

    quadros = CamadaEnlaceDadosTransmissoraControleDeErro.camadaEnlaceDadosTransmissoraControleDeErro(quadros, origem, destino);
    
    new CamadaEnlaceDadosTransmissoraControleFluxo(quadros, origem, destino);
  }

  /* **************************************************************
  Metodo: camadaEnlaceDadosTransmissora*
  Funcao: camadaEnlaceDadosTransmissora.*
  Parametros: int[] quadro*
  Retorno: void*
  *************************************************************** */
  public static Quadro[] camadaEnladeTransmissoraEnquadramento(int[] quadro) {
    quadro = Conversao.bitsParaAscii(quadro);

    Quadro[] quadros = new Quadro[quadro.length];

    for (int i = 0; i<quadro.length; i++) {
      quadros[i] = new Quadro();
      int[] temp = {quadro[i]};
      quadros[i].bits = temp;
    }

    return quadros;
  }
}
