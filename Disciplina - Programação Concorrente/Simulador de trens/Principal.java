/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/09/2019* 
Ultima alteracao: 15/09/2019* 
Nome: Simulador de corrida de trens* 
Funcao: Simular processo de exclusao mutua utilizando trens.
*************************************************************** */

import view.TelaPrincipal;
import javax.swing.SwingUtilities;

public class Principal{
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable(){
      @Override
      public void run(){
        new TelaPrincipal();
      }
    });
  }
}
