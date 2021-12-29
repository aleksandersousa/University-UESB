package scanner.lexical_compiler;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import scanner.utils.States;
import scanner.utils.Util;

//CLASSE SCANNER
public class Scanner {
  private ArrayList<Character> content; // Conteudos do codigo fonte
  private States state; // Estados
  private int pos; // variavel auxiliar posiçao
  private int lineNumber = 1;

  // CONSTRUTOR, RECEBE O ARQUIVO E TRANSFORMA EM UMA CADEIA DE CHAR
  public Scanner(String filename) {
    String txtContent = null;
    try {
      txtContent = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
      content = new ArrayList<>();
      for (char c : txtContent.toCharArray()) {
        content.add(c);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    //checa se tem espaco no final da string
    if (!Util.isSpace(txtContent.charAt(txtContent.length() - 1))) { 
      content.add('\n'); //se nao tiver, adiciona um
    } 
    pos = 0;
  }

  // LE TOKEN A TOKEN transformando no tipo <tipo, valor>
  public Token nextToken() {
    if (isEOF()) {
      return null;
    }

    String term = "";
    char currentChar;
    state = States.Q0;

    while (true) {
      if (isEOF()) {
        break;
      }

      currentChar = nextChar();

      switch (state) {
        case Q0:
          if (Util.isChar(currentChar)) {
            term += currentChar;
            state = States.A0;
          } else if (Util.isDigit(currentChar)) {
            term += currentChar;
            state = States.A1;
          } else if (Util.isAritmeticOperator(currentChar)) {
            term += currentChar;
            if (currentChar != '/') {
              state = States.A5;
            } else {
              state = States.A6;
            }
          } else if (Util.isStringOperator(currentChar)) {
            term += currentChar;
            state = States.C0;
          } else if (Util.isCharOperator(currentChar)) {
            term += currentChar;
            state = States.C1;
          } else if (Util.isOperator(currentChar)) {
            term += currentChar;
            if (currentChar == '<') {
              state = States.A9;
            } else if (currentChar == '>') {
              state = States.A10;
            }
          } else if (Util.isSpecialSymbol(currentChar)) {
            term += currentChar;
            if (currentChar == ':') {
              state = States.A12;
            } else {
              state = States.A13;
            }
          } else if (Util.isEnd(currentChar)) {
            term += currentChar;
            state = States.A15;
          } else if (Util.isSpace(currentChar)) {
            state = States.Q0;
          } else {
            return new Token(Token.TK_ERROR, String.valueOf(currentChar), lineNumber);
          }
          break;
        //Comentarios
        case B0:
          if (currentChar == '*') {
            state = States.B1;
          } else {
            state = States.B0;
          }
          break;
        case B1:
          if (currentChar == '/') {
            state = States.Q0;
          } else {
            state = States.B0;
          }
          break;
        //String e Char
        case C0:
          term += currentChar;
          if (currentChar == '"') {
            state = States.A7;
          } else {
            state = States.C0;
            if (currentChar == '\n' || currentChar == '\r' || isEOF()) {
              return new Token(Token.TK_ERROR, term, lineNumber);
            }
          }
          break;
        case C1:
          term += currentChar;
          if (currentChar == '\'') {
            state = States.A8;
          } else {
            state = States.C1;
            if (currentChar == '\n' || currentChar == '\r' || isEOF()) {
              return new Token(Token.TK_ERROR, term, lineNumber); 
            }
          }
          break;
        //Identificadores e Palavras reservadas
        case A0:
          if (Util.isChar(currentChar) || Util.isDigit(currentChar)) {
            term += currentChar;
            state = States.A0;
          } else {
            back();
            if (Util.isKeyword(term)) {
              return new Token(Token.TK_KEYWORD, term, lineNumber);
            } else if (Util.isLogicOperator(term)) {
              return new Token(Token.TK_OP_LOG, term, lineNumber);
            } else {
              return new Token(Token.TK_ID, term, lineNumber);
            }
          }
          break;
        //Dígitos
        case A1:
          if (Util.isDigit(currentChar)) {
            term += currentChar;
            state = States.A1;
          } else if (currentChar == 'e' || currentChar == 'E') {
            term += currentChar;
            state = States.A2;
          } else if (currentChar == '.') {
            term += currentChar;
            state = States.A3;
          } else {
            back();
            return new Token(Token.TK_INT, term, lineNumber);
          }
          break;
        case A2:
          if (Util.isDigit(currentChar)) {
            term += currentChar;
            state = States.A2;
          } else {
            back();
            return new Token(Token.TK_REAL, term, lineNumber);
          }
          break;
        case A3:
          if (Util.isDigit(currentChar)) {
            term += currentChar;
            state = States.A3;
          } else if (currentChar == 'e' || currentChar == 'E') {
            term += currentChar;
            state = States.A4;
          } else if (currentChar == '.') {
            back();
            back();
            return new Token(Token.TK_INT, term.substring(0, term.length() - 1), lineNumber);
          } else {
            back();
            return new Token(Token.TK_REAL, term, lineNumber);
          }
          break;
        case A4:
          if (Util.isDigit(currentChar)) {
            term += currentChar;
            state = States.A4;
          } else {
            back();
            return new Token(Token.TK_REAL, term, lineNumber);
          }
          break;
        //Operadores Aritmeticos e comentarios
        case A5:
          back();
          return new Token(Token.TK_OP_ARIT, term, lineNumber);
        //String e Char
        case A6:
          if (currentChar == '*') {
            term = term.substring(0, term.length() - 1); //retira o /
            state = States.B0;
          } else {
            back();
            return new Token(Token.TK_OP_ARIT, term, lineNumber);
          }
          break;
        case A7:
          back();
          return new Token(Token.TK_OP_STR, term, lineNumber);
        case A8:
            back();
            return new Token(Token.TK_OP_CHAR, term, lineNumber);
        //Operadores relacionais
        case A9:
          if (currentChar == '=' || currentChar == '>') {
            term += currentChar;
            state = States.A11;
          } else {
            back();
            return new Token(Token.TK_OP_REL, term, lineNumber);
          }
          break;
        case A10:
          if (currentChar == '=') {
            term += currentChar;
            state = States.A11;
          } else {
            back();
            return new Token(Token.TK_OP_REL, term, lineNumber);
          }
          break;
        case A11:
          back();
          return new Token(Token.TK_OP_REL, term, lineNumber);
        //Simbolos especiais / Atribuicao
        case A12:
          if (currentChar == '=') {
            term += currentChar;
            state = States.A14;
          } else {
            back();
            return new Token(Token.TK_SIMB_ESP, term, lineNumber);
          }
          break;
        case A13:
          back();
          return new Token(Token.TK_SIMB_ESP, term, lineNumber);
        case A14:
          back();
          return new Token(Token.TK_AT, term, lineNumber);
        case A15:
          if (Util.isEnd(currentChar)) {
            term += currentChar;
            state = States.A16;
          } else {
            back();
            return new Token(Token.TK_END, term, lineNumber);
          }
        case A16:
          return new Token(Token.TK_ARRAY, term, lineNumber);
        case A17:
          if (Util.isEnd(currentChar)) {
            term += currentChar;
            state = States.A16;
          } else {
            back();
            return new Token(Token.TK_ERROR, term, lineNumber);
          }
        default:
          return new Token(Token.TK_ERROR, term, lineNumber);
      }

      if (currentChar == '\n') {
        lineNumber++;
      }
    }

    return null;
  }

  private char nextChar() {
    return content.get(pos++);
  }

  private boolean isEOF() {
    return pos == content.size();
  }

  private void back() {
    pos--;
  }
}
