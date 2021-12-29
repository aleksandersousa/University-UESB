/* **************************************************************** 
Autor: Aleksander Santos Sousa*
Matricula: 201810825* 
Inicio: 26/08/2019* 
Ultima alteracao: 28/08/2019* 
Nome: Arvore genealogica* 
Funcao: Criar uma arvore genealogica utilizando threads.
*************************************************************** */

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class TelaArvore extends JFrame{

  private JTextPane txtTimeLine;
  private ArrayList<Line> listaPosicoes = new ArrayList<Line>();

  public TelaArvore() {
    this.initGUIComponents();
    this.createTree();
  }

  /* **************************************************************** 
  Metodo: createTxtTimeLine* 
  Funcao: Cria e formata o painel de texto responsavel por exibir o 
          timer* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void createTxtTimeLine(){

    txtTimeLine = new JTextPane(){
      {
        setBounds(0,20,1000,50);
        setEditable(false);
        setFont(new Font("txtTimeLine", Font.BOLD + Font.ITALIC, 30));
        setForeground(Color.red);
      }
    };

    StyledDocument document = txtTimeLine.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
    document.setParagraphAttributes(0, document.getLength(), center, false);

  }

  //Inicializa o contador do temporizador
  private int cont = 0;

  /* **************************************************************** 
  Metodo: temporizador* 
  Funcao: Cria um timer para ser exibido na tela principal* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void temporizador(){
    createTxtTimeLine();
    Timer timer = new Timer();

    TimerTask timerTask = new TimerTask(){
      public void run(){

        //Condicao de parada do timer. O contador vai ate 91 para ser exibido o valor
        //90 na tela.
        if(cont == 91){
          timer.cancel();
          timer.purge();

          int result = JOptionPane.showConfirmDialog(null, "Finalizado!", "Sucesso!", JOptionPane.DEFAULT_OPTION);
          if(result == 0)
            System.exit(0);
        }

        //Imprimindo o contador na tela
        String tempo = String.format("%02d", cont);
        txtTimeLine.setText("Linha do tempo do pai: "+tempo+" anos");
        cont++;

      }
    };

    //Iniciando o timer
    timer.scheduleAtFixedRate(timerTask, 0, 1000);
  }

  /* **************************************************************** 
  Metodo: temporizador* 
  Funcao: Cria um timer para ser exibido na tela principal* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void initGUIComponents(){

    temporizador();

    add(txtTimeLine);
    setLayout(null);
    setLocation(180,100);
    setSize(1000,600);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  /* **************************************************************** 
  Metodo: createTree* 
  Funcao: Inicializa os threads responsaveis pela ramificacao* 
  Parametros: nulo* 
  Retorno: void*
  *************************************************************** */
  public void createTree(){
    try{

      new Node("Pai", this);

      Thread.sleep(22000);
      new Node("Filho 1", this);

      Thread.sleep(3000);
      new Node("Filho 2", this);

      Thread.sleep(7000);
      new Node("Filho 3", this);

      Thread.sleep(6000);
      new Node("Neto 1", this);

      Thread.sleep(7000);
      new Node("Neto 2", this);

      Thread.sleep(23000);
      new Node("Bisneto", this);

    }catch(InterruptedException ex){
      System.out.println("Thread interrompido!");
    }
  }

  /* **************************************************************** 
  Metodo: addLine* 
  Funcao: Salva em uma lista as coordenadas da criacao das linhas* 
  Parametros: int x1 = parametro x da posicao da linha na tela
              int y1 = parametro y da posicao da linha na tela
              int x2 = parametro x do tamanho da linha
              int y2 = parametro y do tamanho da linha* 
  Retorno: Line, retorna um objeto da classe Line que contem as coor-
           denadas das linhas*
  *************************************************************** */
  public Line addLine(int x1, int y1, int x2, int y2){
    Line line = new Line(x1, y1, x2, y2);
    listaPosicoes.add(line);
    
    return line;
  }

  /* **************************************************************** 
  Metodo: removeLine* 
  Funcao: Remove as coordenadas da linha* 
  Parametros: Line line = um objeto da classe Line que contem os parame-
              tros da linha* 
  Retorno: void*
  *************************************************************** */
  public void removeLine(Line line){
    listaPosicoes.remove(line);
  }

  /* **************************************************************** 
  Metodo: paint* 
  Funcao: Desenhar as linhas na tela* 
  Parametros: Graphics graphics = objeto do tipo Graphics que e' respon-
              savel pro desenhar as linhas* 
  Retorno: void*
  *************************************************************** */
  @Override
  public void paint(Graphics graphics){
    super.paint(graphics);

    for(Line line : listaPosicoes){
      int x1 = line.x1;
      int y1 = line.y1;
      int x2 = line.x2;
      int y2 = line.y2;

      int difY = (y1-y2)/2;

      graphics.drawLine(x1, y2+difY, x2, y2+difY);
      graphics.drawLine(x2, y2, x2, y2 + difY);
      graphics.drawLine(x1, y1, x1, y1 - difY);
    }
  }
}