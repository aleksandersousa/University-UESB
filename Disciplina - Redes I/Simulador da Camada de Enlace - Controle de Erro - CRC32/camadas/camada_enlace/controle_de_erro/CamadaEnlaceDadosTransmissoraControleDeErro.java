/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 09/11/2020*
Ultima alteracao: 13/11/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */
package camadas.camada_enlace.controle_de_erro;

import util.Constantes;
import util.Conversao;
import util.Util;

public class CamadaEnlaceDadosTransmissoraControleDeErro {
  public static int[] camadaEnlaceDadosTransmissoraControleDeErro(int[] quadro) {
    int[] quadroControleErro = camadaEnlaceDadosTransmissoraControleDeErroCRC(quadro);

    Util.imprimirNaTela(Conversao.bitsParaString(quadroControleErro), Constantes.CRC_CODIFICADO);

    return quadroControleErro;
  }
  public static int[] camadaEnlaceDadosTransmissoraControleDeErroCRC(int[] quadro) {
    int[] bitsPolinomioCRC = 
      {1,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,1,0,0,0,1,1,1,0,1,1,0,1,1,0,1,1,1};
    
    int[] mensagemBits = Conversao.descomprimeBits(quadro);
    int tamanho = bitsPolinomioCRC.length + mensagemBits.length - 1;
    
    int[] dividendo = new int[tamanho];
    int[] resto = new int[tamanho];

    for (int i=0; i<mensagemBits.length; i++) {
      dividendo[i] = mensagemBits[i];
      resto[i] = mensagemBits[i];  
    }

    boolean zerado = false;
    for (int i=1; i<resto.length; i++) { //encontra o CRC-32
      if (!zerado) {
        for (int j=0; j<bitsPolinomioCRC.length; j++, i++) {
          resto[i] = resto[i] ^ bitsPolinomioCRC[j];
        }

        for (int j=0; j<mensagemBits.length; j++) {
          if (resto[j] == 1) { //o dividendo ainda nao foi zerado
            i = j - 1; //comeca a comparar a partir do proximo 1
            break;
          } else if (j == mensagemBits.length - 1){
            zerado = true;
          }
        }
      } else {
        break;
      }
    }

    for (int i=0; i<dividendo.length; i++) { //concatena a mensagem com a informacao de controle
      dividendo[i] = dividendo[i] ^ resto[i];
    }

    int[] quadroControleErro = Conversao.comprimeBits(dividendo);

    return quadroControleErro;
  }
}
