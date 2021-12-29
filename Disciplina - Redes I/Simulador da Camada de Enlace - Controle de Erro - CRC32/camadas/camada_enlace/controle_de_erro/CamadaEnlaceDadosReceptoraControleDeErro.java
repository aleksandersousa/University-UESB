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
import view.Erro;

public class CamadaEnlaceDadosReceptoraControleDeErro {
  public static int[] camadaEnlaceDadosReceptoraControleDeErro(int[] quadro) {
    int[] quadroControleErro = camadaEnlaceDadosReceptoraControleDeErroCRC(quadro);

    Util.imprimirNaTela(Conversao.bitsParaString(quadroControleErro), Constantes.CRC_DECODIFICADO);

    return quadroControleErro;
  }
  
  public static int[] camadaEnlaceDadosReceptoraControleDeErroCRC(int[] quadro) {
    int[] bitsPolinomioCRC = 
      {1,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,1,0,0,0,1,1,1,0,1,1,0,1,1,0,1,1,1};
    
    int[] resto = Conversao.descomprimeBits(quadro);
    int tamanhoBitsMensagem = resto.length - bitsPolinomioCRC.length + 1;

    boolean zerado = false;
    for (int i=1; i<resto.length; i++) { //encontra o CRC-32
      if (!zerado) {
        for (int j=0; j<bitsPolinomioCRC.length; j++, i++) {
          resto[i] = resto[i] ^ bitsPolinomioCRC[j];
        }

        for (int j=0; j<tamanhoBitsMensagem; j++) {
          if (resto[j] == 1) { //o dividendo ainda nao foi zerado
            i = j - 1; //comeca a comparar a partir do proximo 1
            break;
          } else if (j == tamanhoBitsMensagem - 1){
            zerado = true;
          }
        }
      } else {
        break;
      }
    }

    for (int i=0; i<resto.length; i++) {
      if (resto[i] != 0) {
        Erro.tratarErroCRC();
        break;
      }
    }

    //tirando o resto da divisao
    resto = Conversao.descomprimeBits(quadro);
    int[] bitsMensagem = new int[tamanhoBitsMensagem];
    for (int i=0; i<bitsMensagem.length; i++) {
      bitsMensagem[i] = resto[i];
    }

    int[] quadroControleErro = Conversao.comprimeBits(bitsMensagem);

    Util.imprimirNaTela(Conversao.bitsParaString(quadroControleErro), Constantes.CRC_DECODIFICADO);

    return quadroControleErro;
  }
}
