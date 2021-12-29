/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */
package view;

import util.Constantes;
import util.Formatacao;

import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class PainelDireito extends JPanel {
  private ArrayList<JTextArea> arrayCaixasDeTexto;
  private ArrayList<JPanel> arrayPaineis;
  private JTextArea txtLabelNumerosAciiDecodificados;
  private JTextArea txtLabelBitsRecebidos;
  private JTextArea txtLabelCRCRecebido;

  /*
   * ************************************************************** 
   * Metodo: PainelDireito
   * Funcao: Construtor da classe PainelDireito.
   * Parametros: nulo*
   * Retorno: void*
   */
  public PainelDireito() {
    this.arrayCaixasDeTexto = Formatacao.inicializarCaixasDeTexto();
    this.arrayPaineis = new ArrayList<>();

    this.txtLabelNumerosAciiDecodificados = new JTextArea("Numero Ascii: ");
    this.txtLabelBitsRecebidos = new JTextArea("Bits recebidos: ");
    this.txtLabelCRCRecebido = new JTextArea("CRC recebido: ");

    for (int i = 0; i < Constantes.TAMANHO_ARRAY_PAINEIS; i++) {
      arrayPaineis.add(new JPanel());
    }

    this.initGUIComponents();
  }

  public JTextArea getCaixaDeTexto (int index) {
    return this.arrayCaixasDeTexto.get(index);
  }

  /*
   * ************************************************************** Metodo:
   * initGUIComponents* Funcao: inicializar os componentes do painel.* Parametros:
   * nulo* Retorno: void*
   */
  private void initGUIComponents() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.formatarLabels();
    this.adicionarComponentes();
  }

  /*
   * ************************************************************** Metodo:
   * formatarLabels* Funcao: formata os labels.* Parametros: nulo* Retorno: void*
   */
  private void formatarLabels() {
    this.txtLabelNumerosAciiDecodificados.setBackground(this.getBackground());
    this.txtLabelBitsRecebidos.setBackground(this.getBackground());
    this.txtLabelCRCRecebido.setBackground(this.getBackground());

    Formatacao.inicializarLabels(txtLabelNumerosAciiDecodificados, Constantes.LARGURA_LABELS_DIREITO * 0.56, Constantes.ALTURA_LABELS);
    Formatacao.inicializarLabels(txtLabelBitsRecebidos, Constantes.LARGURA_LABELS_DIREITO * 0.62, Constantes.ALTURA_LABELS);
    Formatacao.inicializarLabels(txtLabelCRCRecebido, Constantes.LARGURA_LABELS_DIREITO * 0.57, Constantes.ALTURA_LABELS);
  }

  /*
   * ************************************************************** Metodo:
   * adicionarComponentes* Funcao: inicializa e adiciona os componentes ao
   * painel.* Parametros: nulo* Retorno: void*
   */
  private void adicionarComponentes() {
    this.arrayPaineis.get(0).add(Formatacao.inicializarBarraDeRolagem(
      this.arrayCaixasDeTexto.get(4), (int) (Constantes.LARGURA_COMPONENTES / 2.8), Constantes.ALTURA_COMPONENTES * 2));

    this.arrayPaineis.get(1).add(txtLabelNumerosAciiDecodificados);
    this.arrayPaineis.get(1).add(Formatacao.inicializarBarraDeRolagem(
      this.arrayCaixasDeTexto.get(1), (int) (Constantes.LARGURA_COMPONENTES / 2.8), Constantes.ALTURA_COMPONENTES * 2));

    this.arrayPaineis.get(2).add(txtLabelBitsRecebidos);
    this.arrayPaineis.get(2).add(Formatacao.inicializarBarraDeRolagem(
      this.arrayCaixasDeTexto.get(5), (int) (Constantes.LARGURA_COMPONENTES / 2.8), Constantes.ALTURA_COMPONENTES * 2));
    
    this.arrayPaineis.get(3).add(txtLabelCRCRecebido);
    this.arrayPaineis.get(3).add(Formatacao.inicializarBarraDeRolagem(
      this.arrayCaixasDeTexto.get(6), (int) (Constantes.LARGURA_COMPONENTES / 2.8), Constantes.ALTURA_COMPONENTES * 2));

    for (int i = 0; i < Constantes.TAMANHO_ARRAY_PAINEIS; i++) {
      this.add(arrayPaineis.get(i));
    }
  }

  /*
   * ************************************************************** Metodo:
   * getPreferredSize* Funcao: seta o tamanho deste painel.* Parametros: nulo*
   * Retorno: void*
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(600, 300);
  }
}
