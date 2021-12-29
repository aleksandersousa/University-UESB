/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 09/11/2020*
Ultima alteracao: 13/11/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package view;

import javax.swing.JOptionPane;

import camadas.AplicacaoTransmissora;

public class Erro {
  public static void tratarErroMensagemVazia() {
    JOptionPane.showMessageDialog(null, "Caixa de texto vazia!", "Alerta!", JOptionPane.ERROR_MESSAGE);
    PainelEsquerdo.mutexEnvio.release();
  }
  
  public static void tratarErroMensagemEmAdamento() {
    JOptionPane.showMessageDialog(null, "Mensagem em andamento!", "Alerta!", JOptionPane.ERROR_MESSAGE);
    PainelEsquerdo.mutexEnvio.callReducePermits(PainelEsquerdo.mutexEnvio.getQueueLength());
  }

  public static void tratarErroCRC() {
    String[] options = {"Sim", "Nao"};
    int option = JOptionPane.showOptionDialog(
      null, 
      "Mensagem com erro! Reenviar mensagem? Caso a mensagem seja reenviada, a probabilidade de erro continuará a escolhida.", 
      "ALERTA!", 
      JOptionPane.DEFAULT_OPTION, 
      JOptionPane.ERROR_MESSAGE, 
      null, 
      options, 
      options[0]
    );

    if (option == JOptionPane.OK_OPTION) { //reenvia a mensagem
      PainelEsquerdo.mutexEnvio.release();
      AplicacaoTransmissora.aplicacaoTransmissora(AplicacaoTransmissora.buffer);
    }
  }
}
