package syntactic_compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import models.Token;

public class Parser {
  ArrayList<Token> tokens = new ArrayList<>();
  Stack<String> symbols = new Stack<>();
  Stack<Integer> states = new Stack<>();
  SLRTable table = new SLRTable();
  Grammar grammar = new Grammar();

  public Parser(String filename) {
    File file = new File(filename);

    try {
      String line = " ";
      BufferedReader br = new BufferedReader(new FileReader(file));

      final String TOKEN_SEPARATOR = "type=";
      final String LEXEMME_SEPARATOR = "lexemme= ";
      final String LINE_SEPARATOR = "line=";

      while ((line = br.readLine()) != null) {
        if (this.isLexicalError(line)) { //se tiver erro lexico nao faz analise sintatica
          System.out.println("ERRO LEXICO!");
          break;
        }
        String type = StringUtils.substringBetween(line, TOKEN_SEPARATOR, ",");
        String lexemme = StringUtils.substringBetween(line, LEXEMME_SEPARATOR, ">").toUpperCase();
        int lineNumber = Integer.parseInt(StringUtils.substringBetween(line, LINE_SEPARATOR, ","));
        tokens.add(new Token(type, lexemme, lineNumber));
      }

      tokens.add(new Token("", "$", -1)); //adiciona simbolo para reconhecimento

      br.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public void run() {
    states.push(0); //inicia a pilha de estados com o estado 0
    
    int i = 0;
    int variableColumn = 0;
    int stateRow = 0;
    boolean isReduce = false;
    String action = "";
    String rule = "";
    String ruleDerivation = "";
    String symbol = "";
    String[] tempArr = null;

    while (i < tokens.size()) { //percorre a fita de entrada
      if (table.needToMatch(tokens.get(i).getToken())) {
        symbol = table.matchTokens(tokens.get(i).getToken());
      } else {
        symbol = tokens.get(i).getLexemme();
      }

      if (isReduce) { //se tiver reduzido, olha a pilha
        variableColumn = table.getVariableColumn(symbols.peek()); //pega coluna do simbolo terminal
      } else { //se nao tiver, olha a fita
        variableColumn = table.getVariableColumn(symbol);
      }
      stateRow = table.getStateRow(String.valueOf(states.peek())); //pega linha do estado

      action = table.getAction(stateRow, variableColumn); //pega a acao a ser realizada

      if (action.equals("acc")) { //aceitacao
        System.out.println("OK!");
        break;
      } else if (action.equals("")) { //erro
        System.out.println(tokens.get(i).getToken() + " " + symbol + " inesperado(a) na linha " + tokens.get(i).getLine());
        break;
      } else if (action.contains("s")) { //regra shift
        symbols.push(symbol);
        states.push(Integer.parseInt(StringUtils.substringAfterLast(action, "s")));
        isReduce = false;
        i++; //avanca a fita de entrada
      } else if (action.contains("r")) { //regra reduce
        rule = grammar.getGrammar(Integer.parseInt(StringUtils.substringAfterLast(action, "r")));
        ruleDerivation = StringUtils.substringAfterLast(rule, "-> ");
        
        tempArr = ruleDerivation.split(" ");
        ArrayUtils.reverse(tempArr); //inverte o array por conta da analise bottom up
        for (int j = 0; j < tempArr.length; j++) {
          if (symbols.peek().equals(tempArr[j])) { // desempilha a producao
            symbols.pop();
            states.pop();
          }
        }

        symbols.push(StringUtils.substringBefore(rule, " ->")); //empilha a variavel
        isReduce = true;
      } else { //acao da tabela goto
        states.push(Integer.parseInt(action));
        isReduce = false;
      }
    }
  }

  private boolean isLexicalError(String line) { //verifica se tem erro lexico
    return (line.contains("TK_ERROR"));
  }
}
