/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 14/11/2019* 
Ultima alteracao: 23/11/2019* 
Nome: Transito automato* 
Funcao: Simular um transito automato com percursos pre-definidos.
*************************************************************** */
import view.MainScreen;
import javax.swing.SwingUtilities;

public class Principal{
  /* **************************************************************** 
  Metodo: main* 
  Funcao: metodo estatico que inicializa o programa. Chama a tela 
          principal* 
  *************************************************************** */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable(){
      @Override
      public void run(){
        new MainScreen();
      }
    });
  }
}