/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 26/08/2019* 
Ultima alteracao: 28/08/2019* 
Nome: Arvore genealogica* 
Funcao: Criar uma arvore genealogica utilizando threads.
*************************************************************** */

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Node implements Runnable{
  String name;
  Thread thread;
  TelaArvore tela;
  Line lineIndex;

  /* **************************************************************** 
  Metodo: Node* 
  Funcao: Construtor da classe Node* 
  Parametros: String name = nome do thread
              TelaArvore = objeto do tipo TelaArvore que e' um JFrame,
                           a tela principal* 
  Retorno: void*
  *************************************************************** */
  public Node(String name, TelaArvore tela){
    this.name = name;
    this.tela = tela;

    thread = new Thread(this, this.name);
    thread.start();
  }

  /* **************************************************************** 
  Metodo: run* 
  Funcao: Sobrescreve o metodo run da interface Runnable* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void run(){
    int X_SIZE = 80;
    int Y_SIZE = 60; 

    int x1, y1, x2, y2;

    //Cria o painel de texto responsavel por exibir as ramificacoes na tela
    JTextPane txtText = new JTextPane(){
      {
        setBounds(450, 80, X_SIZE, Y_SIZE);
        setEditable(false);
        setFont(new Font("txtText", Font.BOLD, 20));
        setBackground(Color.green);
      }
    };

    try{
      if(name.equals("Pai")){
        txtText.setText("Nasce "+ name);
        tela.add(txtText);
        tela.revalidate();
        txtText.repaint();

        //Tempo ate o thread morrer
        Thread.sleep(90000);

        //Quando o tempo de morte for atingido, o painel e'removido da tela
        tela.remove(txtText);
        tela.revalidate();
        tela.repaint();
     
      } else if (name.equals("Filho 1")){
        txtText.setText("Nasce "+ name);
        txtText.setBounds(250, 200, X_SIZE, Y_SIZE);
        txtText.setBackground(Color.red);

        //Coordenadas da linha
        x1 = 250+X_SIZE/2;
        y1 = 200+Y_SIZE/2;
        x2 = 450+X_SIZE/2;
        y2 = 80+(Y_SIZE*3/2);

        lineIndex = tela.addLine(x1, y1, x2, y2);
        tela.revalidate();
        tela.repaint();

        tela.add(txtText);
        tela.revalidate();
        txtText.repaint();

        //Tempo ate o thread morrer
        Thread.sleep(61000);

        //Quando o tempo de morte for atingido, o painel e'removido da tela
        tela.removeLine(lineIndex);
        tela.remove(txtText);
        tela.revalidate();
        tela.repaint();

      } else if (name.equals("Filho 2")){
        txtText.setText("Nasce "+ name);
        txtText.setBounds(450, 200, X_SIZE, Y_SIZE);
        txtText.setBackground(Color.red);

        //Coordenadas da linha
        x1 = 450+X_SIZE/2;
        y1 = 200+Y_SIZE/2;
        x2 = 450+X_SIZE/2;
        y2 = 80+(Y_SIZE*3/2);

        lineIndex = tela.addLine(x1, y1, x2, y2);
        tela.revalidate();
        tela.repaint();

        tela.add(txtText);
        tela.revalidate();
        txtText.repaint();

        //Tempo ate o thread morrer
        Thread.sleep(55000);

        //Quando o tempo de morte for atingido, o painel e'removido da tela
        tela.remove(txtText);
        tela.removeLine(lineIndex);
        tela.revalidate();
        tela.repaint();

      } else if (name.equals("Filho 3")){
        txtText.setText("Nasce "+ name);
        txtText.setBounds(650, 200, X_SIZE, Y_SIZE);
        txtText.setBackground(Color.red);

        tela.add(txtText);
        tela.revalidate();
        txtText.repaint();

        //Coordenadas da linha
        x1 = 650+X_SIZE/2;
        y1 = 200+Y_SIZE/2;
        x2 = 450+X_SIZE/2;
        y2 = 80+(Y_SIZE*3/2);

        lineIndex = tela.addLine(x1, y1, x2, y2);
        tela.revalidate();
        tela.repaint();

        //Tempo ate o thread morrer
        Thread.sleep(55000);

        //Quando o tempo de morte for atingido, o painel e'removido da tela
        tela.remove(txtText);
        tela.removeLine(lineIndex);
        tela.revalidate();
        tela.repaint();

      } else if (name.equals("Neto 1")){
        txtText.setText("Nasce "+ name);
        txtText.setBounds(250, 320, X_SIZE, Y_SIZE);
        txtText.setBackground(Color.gray);

        //Coordenadas da linha
        x1 = 250+X_SIZE/2;
        y1 = 320+Y_SIZE/2;
        x2 = 250+X_SIZE/2;
        y2 = 200+(Y_SIZE*3/2);

        lineIndex = tela.addLine(x1, y1, x2, y2);
        tela.revalidate();
        tela.repaint();

        tela.add(txtText);
        tela.revalidate();
        txtText.repaint();

        //Tempo ate o thread morrer
        Thread.sleep(35000);

        //Quando o tempo de morte for atingido, o painel e'removido da tela
        tela.remove(txtText);
        tela.removeLine(lineIndex);
        tela.revalidate();
        tela.repaint();

      } else if (name.equals("Neto 2")){
        txtText.setText("Nasce "+ name);
        txtText.setBounds(450, 320, X_SIZE, Y_SIZE);
        txtText.setBackground(Color.gray);

        //Coordenadas da linha
        x1 = 450+X_SIZE/2;
        y1 = 320+Y_SIZE/2;
        x2 = 450+X_SIZE/2;
        y2 = 200+(Y_SIZE*3/2);

        lineIndex = tela.addLine(x1, y1, x2, y2);
        tela.revalidate();
        tela.repaint();

        tela.add(txtText);
        tela.revalidate();
        txtText.repaint();

        //Tempo ate o thread morrer
        Thread.sleep(33000);

        //Quando o tempo de morte for atingido, o painel e'removido da tela
        tela.removeLine(lineIndex);
        tela.remove(txtText);
        tela.revalidate();
        tela.repaint();

      } else if (name.equals("Bisneto")){
        txtText.setText("Nasce "+ name);
        txtText.setBounds(250, 440, X_SIZE, Y_SIZE);
        txtText.setBackground(Color.orange);

        //Coordenadas da linha
        x1 = 250+X_SIZE/2;
        y1 = 440+Y_SIZE/2;
        x2 = 250+X_SIZE/2;
        y2 = 320+(Y_SIZE*3/2);

        lineIndex = tela.addLine(x1, y1, x2, y2);
        tela.revalidate();
        tela.repaint();

        tela.add(txtText);
        tela.revalidate();
        txtText.repaint();

        //Tempo ate o thread morrer
        Thread.sleep(12000);

        //Quando o tempo de morte for atingido, o painel e'removido da tela
        tela.removeLine(lineIndex);
        tela.remove(txtText);
        tela.revalidate();
        tela.repaint();
      }

    } catch (InterruptedException ex){
      System.out.println("Thread interrompido!");
    }
  }
}
