/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */
package view;

import camadas.AplicacaoTransmissora;

import util.Formatacao;
import util.Constantes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PainelEsquerdo extends JPanel {
  public ArrayList<JTextArea> arrayCaixasDeTexto;

  public JButton btnEnviar;

  private ArrayList<JPanel> arrayPaineis;
  private JTextArea txtLabelNumerosAscii;
  private JTextArea txtLabelQuadrosCodificados;
  private JTextArea txtLabelCRCCodificado;
  private JTextField txtMensagem;

  private int destino;
  private int origem;

  /*
   * ************************************************************** 
   * Metodo: PainelEsquerdo 
   * Funcao: Construtor da classe PainelEsquerdo
   * Parametros: nulo
   * Retorno: void
   */
  public PainelEsquerdo() {
    this.arrayPaineis = new ArrayList<>();
    this.txtLabelNumerosAscii = new JTextArea("Numeros Ascii: ");
    this.txtLabelQuadrosCodificados = new JTextArea("Bits: ");
    this.txtLabelCRCCodificado = new JTextArea("CRC codificado: ");

    this.arrayCaixasDeTexto = Formatacao.inicializarCaixasDeTexto();

    for (int i = 0; i < Constantes.TAMANHO_ARRAY_PAINEIS; i++) {
      arrayPaineis.add(new JPanel());
    }

    this.initGUIComponents();
  }

  /*
   * ************************************************************** Metodo:
   * initGUIComponents Funcao: inicializar os componentes do painel. Parametros:
   * nulo Retorno: void
   */
  private void initGUIComponents() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.iniciarBotaoECaixaDeTextoEnviar();
    this.formatarLabels();
    this.adicionarComponentes();
  }

  /*
   * ************************************************************** 
   * Metodo: setDestino
   * Funcao: seta o destino, qual computador vai ser transmitida a 
   *         mensagem
   * Parametros: int destino: id do computador que recebera' a
   *             mensagem
   * Retorno: void
  */
  public void setDestino (int destino) {
    this.destino = destino;
  }
  
  /*
   * ************************************************************** 
   * Metodo: setDestino
   * Funcao: seta o destino, qual computador vai ser transmitida a 
   *         mensagem
   * Parametros: int destino: id do computador que recebera' a
   *             mensagem
   * Retorno: void
  */
  public void setOrigem (int origem) {
    this.origem = origem;
  }

  /*
   * ************************************************************** 
   * Metodo: getCaixaDeTexto
   * Funcao: pega uma caixa de texo
   * Parametros: int index: qual caixa de texto vai pegar
   * Retorno: JTextArea caixa de texto
  */
  public JTextArea getCaixaDeTexto (int index) {
    return this.arrayCaixasDeTexto.get(index);
  }

  /*
   * ************************************************************** 
   * Metodo: iniciarBotaoECaixaDeTextoEnviar
   * Funcao: inicializa a caixa de texto de entrada e o botao enviar.
   * Parametros: nulo
   * Retorno: void
  */
  private void iniciarBotaoECaixaDeTextoEnviar() {
    txtMensagem = new JTextField("Digite sua mensagem") {
      @Override
      public Dimension getPreferredSize() {
        return new Dimension((int) (Constantes.LARGURA_COMPONENTES / 2.8), Constantes.ALTURA_COMPONENTES * 2);
      }

      {
        this.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
            JTextField txt = (JTextField) e.getSource();
            txt.setText("");
            txt.setForeground(new Color(50, 50, 50));
          }

          @Override
          public void focusLost(FocusEvent e) {
            JTextField txt = (JTextField) e.getSource();
            if (txt.getText().length() == 0) {
              txt.setText("Digite sua mensagem");
              txt.setForeground(new Color(150, 150, 150));
            }
          }
        });
      }
    };

    btnEnviar = new JButton("Enviar") {
      @Override
      public Dimension getPreferredSize() {
        return new Dimension(90, Constantes.ALTURA_COMPONENTES - 10);
      }

      {
        this.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            AplicacaoTransmissora.aplicacaoTransmissora(txtMensagem.getText(), origem, destino);
            repaint();
          }
        });

        this.addKeyListener(new KeyListener() {
          @Override
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              AplicacaoTransmissora.aplicacaoTransmissora(txtMensagem.getText(), origem, destino);
              repaint();
            }
          }

          @Override
          public void keyTyped(KeyEvent e) {
          }

          @Override
          public void keyReleased(KeyEvent e) {
          }
        });
      }
    };
  }

  /*
   * ************************************************************** 
   * Metodo: formatarLabels
   * Funcao: formata os labels.
   * Parametros: nulo
   * Retorno: void
  */
  private void formatarLabels() {
    this.txtLabelNumerosAscii.setBackground(this.getBackground());
    this.txtLabelQuadrosCodificados.setBackground(this.getBackground());
    this.txtLabelCRCCodificado.setBackground(this.getBackground());

    Formatacao.inicializarLabels(txtLabelNumerosAscii, Constantes.LARGURA_LABELS_ESQUERDO * 0.41, Constantes.ALTURA_LABELS);
    Formatacao.inicializarLabels(txtLabelQuadrosCodificados, Constantes.LARGURA_LABELS_ESQUERDO * 0.2, Constantes.ALTURA_LABELS);
    Formatacao.inicializarLabels(txtLabelCRCCodificado, Constantes.LARGURA_LABELS_ESQUERDO * 0.425, Constantes.ALTURA_LABELS);
  }

  /*
   * ************************************************************** 
   * Metodo: adicionarComponentes
   * Funcao: inicializa e adiciona os paineis os componentes.
   * Parametros: nulo
   * Retorno: void
  */
  private void adicionarComponentes() {
    arrayPaineis.get(0).add(txtMensagem);
    arrayPaineis.get(0).add(btnEnviar);

    arrayPaineis.get(1).add(txtLabelNumerosAscii);
    arrayPaineis.get(1).add(Formatacao.inicializarBarraDeRolagem(this.arrayCaixasDeTexto.get(0),
      (int) (Constantes.LARGURA_COMPONENTES / 2.8), Constantes.ALTURA_COMPONENTES * 2));

    arrayPaineis.get(2).add(txtLabelQuadrosCodificados);
    arrayPaineis.get(2).add(Formatacao.inicializarBarraDeRolagem(this.arrayCaixasDeTexto.get(2),
      (int) (Constantes.LARGURA_COMPONENTES / 2.8), Constantes.ALTURA_COMPONENTES * 2));
    
    arrayPaineis.get(3).add(txtLabelCRCCodificado);
    arrayPaineis.get(3).add(Formatacao.inicializarBarraDeRolagem(this.arrayCaixasDeTexto.get(3),
      (int) (Constantes.LARGURA_COMPONENTES / 2.8), Constantes.ALTURA_COMPONENTES * 2));

    for (int i = 0; i < Constantes.TAMANHO_ARRAY_PAINEIS; i++) {
      this.add(arrayPaineis.get(i));
    }
  }

  /*
   * ************************************************************** 
   * Metodo: getPreferredSize
   * Funcao: seta o tamanho deste painel.
   * Parametros: nulo*
   * Retorno: void*
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(600, 400);
  }
}
