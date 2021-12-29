/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {
  public static Computador computador1;
  public static Computador computador2;
  public static Computador computador3;
  public static Computador computador4;

  private JPanel painelBackground;

  /* **************************************************************
  Metodo: TelaPrincipal*
  Funcao: Construtor da classe TelaPrincipal.*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  public TelaPrincipal(){
    TelaPrincipal.computador1 = new Computador(1);
    TelaPrincipal.computador2 = new Computador(2);
    TelaPrincipal.computador3 = new Computador(3);
    TelaPrincipal.computador4 = new Computador(4);

    this.initGUIComponents();
  }

  /* **************************************************************
  Metodo: initGUIComponents*
  Funcao: inicializar os componentes da tela.*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  private void initGUIComponents() {
    this.setTitle("Simulador de Redes, controle de acesso ao meio - CSMA p-persistente");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1200, 550);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
    this.getRootPane().setDefaultButton(TelaPrincipal.computador1.painelEsquerdo.btnEnviar);
    this.setVisible(true);
    this.inicializarComponentes();
    this.add(painelBackground);
  }

  /* **************************************************************
  Metodo: inicializarComponentes*
  Funcao: inicializar os paineis que compoem a tela.*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  private void inicializarComponentes() {
    this.painelBackground = new JPanel(){
      @Override
      public Dimension getPreferredSize() {
        return new Dimension(0, 400);
      }
      {
        this.setLayout(new GridLayout(0, 4));
        this.add(computador1);
        this.add(computador2);
        this.add(computador3);
        this.add(computador4);
      }
    };
  }
}
