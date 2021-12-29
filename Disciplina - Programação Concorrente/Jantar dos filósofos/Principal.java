/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 09/10/2019* 
Ultima alteracao: 12/10/2019* 
Nome: jantar dos filosofos* 
Funcao: Simular o problema classico jantar dos filosofos.
*************************************************************** */
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