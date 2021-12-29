package lexical_compiler;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import utils.States;
import utils.Util;

//CLASSE SCANNER
public class Scanner {
  private ArrayList<Character> content; // Conteudos do codigo fonte
  private States state; // Estados
  private int pos; // variavel auxiliar posiçao
  private int colNumber;
  private int lineNumber;
  private boolean changedLine; //variavel que verifica se mudou de linha

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

    if (changedLine) {
      colNumber = 0;
      changedLine = false;
    }

    while (true) {
      if (isEOF()) {
        break;
      }

      currentChar = nextChar();
      colNumber++;

      if (currentChar == '\n') {
        lineNumber++;
        changedLine = true;
      }

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
            return new Token("TOKEN ERROR, linha " + lineNumber + ", coluna " + colNumber, String.valueOf(currentChar));
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
              return new Token("TOKEN ERROR, line " + lineNumber + ", column " + colNumber, term); 
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
              return new Token("TOKEN ERROR, line " + lineNumber + ", column " + colNumber, term); 
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
              return new Token(Token.TK_KEYWORD, term);
            } else if (Util.isLogicOperator(term)) {
              return new Token(Token.TK_OP_LOG, term);
            } else {
              return new Token(Token.TK_ID, term);
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
            return new Token(Token.TK_INT, term);
          }
          break;
        case A2:
          if (Util.isDigit(currentChar)) {
            term += currentChar;
            state = States.A2;
          } else {
            back();
            return new Token(Token.TK_REAL, term);
          }
          break;
        case A3:
          if (Util.isDigit(currentChar)) {
            term += currentChar;
            state = States.A3;
          } else if (currentChar == 'e' || currentChar == 'E') {
            term += currentChar;
            state = States.A4;
          } else {
            back();
            return new Token(Token.TK_REAL, term);
          }
          break;
        case A4:
          if (Util.isDigit(currentChar)) {
            term += currentChar;
            state = States.A4;
          } else {
            back();
            return new Token(Token.TK_REAL, term);
          }
          break;
        //Operadores Aritmeticos e comentarios
        case A5:
          back();
          return new Token(Token.TK_OP_ARIT, term);
        //String e Char
        case A6:
          if (currentChar == '*') {
            term = term.substring(0, term.length() - 1); //retira o /
            state = States.B0;
          } else {
            back();
            return new Token(Token.TK_OP_ARIT, term);
          }
          break;
        case A7:
          back();
          return new Token(Token.TK_OP_STR, term);
        case A8:
          back();
          return new Token(Token.TK_OP_CHAR, term);
        //Operadores relacionais
        case A9:
          if (currentChar == '=' || currentChar == '>') {
            term += currentChar;
            state = States.A11;
          } else {
            back();
            return new Token(Token.TK_OP_REL, term);
          }
          break;
        case A10:
          if (currentChar == '=') {
            term += currentChar;
            state = States.A11;
          } else {
            back();
            return new Token(Token.TK_OP_REL, term);
          }
          break;
        case A11:
          back();
          return new Token(Token.TK_OP_REL, term);
        //Simbolos especiais / Atribuicao
        case A12:
          if (currentChar == '=') {
            term += currentChar;
            state = States.A14;
          } else {
            back();
            return new Token(Token.TK_SIMB_ESP, term);
          }
          break;
        case A13:
          back();
          return new Token(Token.TK_SIMB_ESP, term);
        case A14:
          back();
          return new Token(Token.TK_AT, term);
        case A15:
          back();
          return new Token(Token.TK_END, term);
        default:
          return new Token("TOKEN ERROR, line " + lineNumber + ", column " + colNumber, term);
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
