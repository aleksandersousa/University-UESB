package syntactic_compiler;

import java.util.ArrayList;
import java.util.HashMap;

import utils.CSVReader;

public class SLRTable {
  private ArrayList<String[]> table = null;
  private HashMap<String, Integer> mappedVariableValues = new HashMap<>();
  private HashMap<String, Integer> mappedStateValues = new HashMap<>();
  private final String csvFile = "./sources/TabelaSLR.csv"; 
  private final String[] variablesRow;

  public SLRTable() {
    table = CSVReader.read(csvFile);
    variablesRow = table.get(1);
    variablesRow[26] = this.removeDoubleQuotes(variablesRow[26]); //remove as aspas duplas do simbolo ,
  }
  
  public int getVariableColumn(String lexemme) { //recupera coluna da tabela
    if (mappedVariableValues.containsKey(lexemme)) { //recupera coluna sem percorrer a tabela se a mesma ja foi visitada
      return mappedVariableValues.get(lexemme);
    }

    int variableColumn = 0;
    for (int i = 1; i < variablesRow.length; i++) {
      if (variablesRow[i].equals(lexemme)) {
        variableColumn = i;
        mappedVariableValues.put(lexemme, variableColumn); //salva coluna pra nao ter que percorrer a tabela novamente
        return variableColumn;
      }
    }

    return 0;
  }

  public int getStateRow(String lexemme) {
    if (mappedStateValues.containsKey(lexemme)) { //recupera linha sem percorrer a tabela se a mesma ja foi visitada
      return mappedStateValues.get(lexemme);
    }

    int stateRow = 0;
    for (int i = 2; i < table.size(); i++) {
      if (table.get(i)[0].equals(lexemme)) {
        stateRow = i;
        mappedStateValues.put(lexemme, stateRow); //salva linha pra nao ter que percorrer a tabela novamente
        return stateRow;
      }
    }

    return 0;
  }

  public String getAction(int row, int column) { //pega acao da linha e coluna passadas
    return table.get(row)[column];
  }

  public String matchTokens(String token) { //substitui numeros, palavras, char e strings por seus respectivos tokens
    if (token.equals("TK_IDENTIFICADOR")) {
      return "ID";
    } else if (token.equals("TK_INTEIRO") || token.equals("TK_REAL")) {
      return "INT";
    } else if (token.equals("TK_OP_CHAR") || token.equals("TK_OP_STRING")) {
      return "LIT";
    } else {
      return null;
    }
  }

  public boolean needToMatch(String token) { //verifica se precisa substituir o lexema por seu token
    return matchTokens(token) != null;
  }

  private String removeDoubleQuotes(String token) { //remove aspas duplas
    String newToken = "";
    if (token.charAt(0) == '\"' && token.charAt(token.length() - 1) == '\"') {
      for (int i = 1; i < token.length() - 1; i++) {
        newToken += token.charAt(i);
      }
    }
    return newToken;
  }
}
