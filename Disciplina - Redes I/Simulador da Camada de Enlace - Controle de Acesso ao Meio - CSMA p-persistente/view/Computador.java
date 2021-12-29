/* ***************************************************************
Autor: Aleksander Santos Sousa*
Matricula: 201810825*
Inicio: 07/12/2020*
Ultima alteracao: 10/12/2020*
Nome: Simulador de Redes*
Funcao: Simular o envio de uma mensagem de texto.
*************************************************************** */

package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Constantes;

@SuppressWarnings("serial")
public class Computador extends JPanel {
  public PainelEsquerdo painelEsquerdo;
  public PainelDireito painelDireito;
  private JPanel painelTitulo;
  private JPanel painelCorpo;
  private JComboBox<String> comboBoxComputadores;
  private int id;

  /* **************************************************************
  Metodo: Computador*
  Funcao: Construtor da classe Computador.*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  public Computador (int id) {
    this.id = id;

    painelEsquerdo = new PainelEsquerdo();
    painelDireito = new PainelDireito();

    painelEsquerdo.setOrigem(id);

    switch (id) {
      case 1:
        painelEsquerdo.setDestino(2);
        break;
      case 2:
        painelEsquerdo.setDestino(3);
        break;
      case 3:
        painelEsquerdo.setDestino(4);
        break;
      case 4:
        painelEsquerdo.setDestino(1);
        break;
    }

    this.initGUIComponents();
  }

  /* **************************************************************
  Metodo: initGUIComponents*
  Funcao: inicializar os componentes da tela.*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  private void initGUIComponents () {
    this.iniciarComboBox();
    this.inciarPaineis();

    this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(painelTitulo);
    this.add(painelCorpo);
  }

  /* **************************************************************
  Metodo: iniciarComboBox*
  Funcao: inicializar o comboBox.*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  private void iniciarComboBox () {
    String[] computadores = new String[3];
    int index = 0;

    switch (id) {
      case 1:
        computadores[0] = "Computador 2";
        computadores[1] = "Computador 3";
        computadores[2] = "Computador 4";
        index = 0;
        break;
      case 2:
        computadores[0] = "Computador 1";
        computadores[1] = "Computador 3";
        computadores[2] = "Computador 4";
        index = 1;
        break;
      case 3:
        computadores[0] = "Computador 1";
        computadores[1] = "Computador 2";
        computadores[2] = "Computador 4";
        index = 2;
        break;
      case 4:
        computadores[0] = "Computador 1";
        computadores[1] = "Computador 2";
        computadores[2] = "Computador 3";
        index = 0;
        break;
    }

    comboBoxComputadores = new JComboBox<>(computadores);
    comboBoxComputadores.setForeground(Color.ORANGE);
    comboBoxComputadores.setSelectedIndex(index);
    comboBoxComputadores.setPreferredSize(new Dimension((int) (Constantes.LARGURA_COMPONENTES / 2.8), 20));
    comboBoxComputadores.addActionListener(new ActionListener() {
      @Override
      @SuppressWarnings("unchecked")
      public void actionPerformed(ActionEvent e) {
        JComboBox<String> combo = (JComboBox<String>) e.getSource();
        String selectedItem = (String) combo.getSelectedItem();

        painelEsquerdo.setOrigem(id);

        if (selectedItem.equals("Computador 1")) {
          painelEsquerdo.setDestino(1);
        } else if (selectedItem.equals("Computador 2")) {
          painelEsquerdo.setDestino(2);
        } else if (selectedItem.equals("Computador 3")) {
          painelEsquerdo.setDestino(3);
        } else {
          painelEsquerdo.setDestino(4);
        } 
      }
    });
  }

  /* **************************************************************
  Metodo: inciarPaineis*
  Funcao: inicializar os paneis.*
  Parametros: nulo*
  Retorno: void*
  *************************************************************** */
  private void inciarPaineis () {
    JLabel labelId = new JLabel("Computador: " + Integer.toString(id), JLabel.CENTER);
    labelId.setForeground(Color.red);
    labelId.setPreferredSize(new Dimension(0, 40));
    labelId.setBackground(this.getBackground());
    labelId.setAlignmentX(Component.CENTER_ALIGNMENT);

    painelTitulo = new JPanel() {
      {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
        this.add(labelId);
        this.add(comboBoxComputadores);
      }
    };

    painelCorpo = new JPanel() {
      {
        this.setLayout(new GridLayout(0, 2));
        this.add(painelEsquerdo);
        this.add(painelDireito);
      }
    };
  }
}
